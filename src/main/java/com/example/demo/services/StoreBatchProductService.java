package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.beans.EmailDetails;
import com.example.demo.beans.NotificationExcel;
import com.example.demo.constants.Constants;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Products;
import com.example.demo.entities.Users;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class StoreBatchProductService {

	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;

	@Scheduled(fixedRate = Constants.SCHEDULE_FIXRATE_FOR_IMPORT_EXCEL, initialDelay = 20 * 1000)
	public void createBatchProductByExcel() throws IOException {
		File fileOrDir = new File(this.servletContext.getRealPath("/import_batch_products"));
		if(fileOrDir == null) {
			fileOrDir.mkdir();
		}
		Optional<File[]> files = Optional.ofNullable(fileOrDir.listFiles());
		if (files.isPresent()) {
			for (File file : files.get()) {
				int uid = Integer.parseInt(file.getName().split("_")[1]);
				System.out.println("uid "  + uid);
				String email = this.userRepository.getEmailById(uid).get(0);
				String cateId = file.getName().split("_")[3];
				Categories category = this.categoryRepository.getById(Integer.parseInt(cateId));
				String message = this.readFileExcel(file, category);
				EmailDetails mail = new EmailDetails();
				mail.setAttachment(file);
				mail.setMsgBody(message);
				mail.setSubject("Thông báo thêm sản phẩm hàng loạt bằng file Excel");
				mail.setRecipient(email);
				if (this.mailService.sendMailWithAttachment(mail)) {
					file.delete();
				}
			}
		}
	}

	private String readFileExcel(File file, Categories category) throws IOException {
		NotificationExcel notificationExcel = new NotificationExcel();
		String message = "Hệ thống thông báo hàng loạt sản phẩm thành công";
		List<Products> products = new ArrayList<>();
		List<Row> rowsNoError = new ArrayList<Row>();
		try {
			InputStream targetStream = new FileInputStream(file);
			XSSFWorkbook myWorkBook = new XSSFWorkbook(targetStream);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);
			HashMap<Row, List<String>> rowsError = new HashMap<Row, List<String>>();
			for (Row row : mySheet) {
				if (row.getRowNum() > 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					Products product = new Products();
					List<String> errors = this.validateRowExcel(product, cellIterator);
					if (!errors.isEmpty()) {
						rowsError.put(row, errors);
						message = "Một số sản phẩm lỗi vui lòng kiểm tra và sửa lại";
					} else {
						rowsNoError.add(row);
						product.setStatus("1");
						product.setCategory(category);
						products.add(product);
					}
				}
			}
			if (!rowsError.isEmpty()) {
				for (Map.Entry<Row, List<String>> entry : rowsError.entrySet()) {
					Row row = entry.getKey();
					List<String> errors = entry.getValue();
					Cell cell = row.createCell(Constants.COLUMN_ERRORS_EXCEL);
					cell.setCellValue(errors.toString());
					CellStyle cellStyle = mySheet.getWorkbook().createCellStyle();
					Font font = mySheet.getWorkbook().createFont();
					font.setColor(IndexedColors.RED.getIndex());
					cellStyle.setFont(font);
					cell.setCellStyle(cellStyle);
				}
			}
			for (int i = rowsNoError.size() - 1; i >= 0; i--) {
				XSSFRow removingRow = mySheet.getRow(rowsNoError.get(i).getRowNum());
				mySheet.removeRow(removingRow);
			}

//		File newFile = new File(this.servletContext.getRealPath("/error_files_excel/"+ file.getName())  );
//		if(!newFile.exists()) {
//			newFile.createNewFile();
//		}
//		Files.copy(file.toPath(),newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			OutputStream out = new FileOutputStream(file);
			myWorkBook.write(out);
			if (!products.isEmpty()) {
				try {
					this.productRepository.saveAll(products);
					if (rowsNoError.isEmpty()) {
						message = "Hệ thống thông báo hàng loạt sản phẩm thành công";
					} else {
						message = "Hệ thống thông báo: Một số sản phẩm lỗi vui lòng kiểm tra và sửa lại";
					}
				} catch (Exception e) {
					message = "Hệ thống thông báo lỗi thêm dữ liệu từ Server vui lòng cập nhật lại file";
					e.printStackTrace();
				}
			} else {
				message = "Hệ thống thông báo: Các sản phẩm trong file không hợp lệ \n Vui lòng kiểm tra lại";
			}
			targetStream.close();
			myWorkBook.close();
		} catch (Exception e) {
			notificationExcel.setMessage("Lỗi đọc file Excel");
			notificationExcel.setType("danger");
			e.printStackTrace();

		}
		return message;
	}

	private List<String> validateRowExcel(Products product, Iterator<Cell> cellIterator) {
		List<String> errors = new ArrayList<String>();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			switch (cell.getColumnIndex()) {
			case 0:
				try {
					String name = cell.getCellTypeEnum() == CellType.NUMERIC ? (int) cell.getNumericCellValue() + ""
							: cell.getStringCellValue();
					if (name.trim().length() < 10 || name.trim().length() > 225) {
						errors.add("Tên sản phẩm từ 10-225 ký tự");
					} else if (this.productRepository.existsByName(name.trim())) {
						errors.add("Tên sản phẩm đã tồn tại");
					}
					product.setName(name.trim());
				} catch (Exception e) {
					errors.add("Lỗi nhập tên sản phẩm");
				}
				break;
			case 1:
				try {
					int stock = (int) cell.getNumericCellValue();
					product.setStock(stock);
				} catch (Exception e) {
					errors.add("Lỗi nhập tồn kho");
				}
				break;
			case 2:
				try {
					int price = (int) cell.getNumericCellValue();
					product.setPrice(price);
				} catch (Exception e) {
					errors.add("Lỗi nhập giá");
				}
				break;
			case 3:
				try {
					String color = cell.getStringCellValue();
					if (color.trim().length() < 2 || color.trim().length() > 225) {
						errors.add("Màu sắc sản phẩm từ 2-225 ký tự");
					}
					product.setColor(color);
				} catch (Exception e) {
					errors.add("Lỗi nhập màu sắc");
				}
				break;
			case 4:
				try {
					String size = cell.getCellTypeEnum() == CellType.NUMERIC ? (int) cell.getNumericCellValue() + ""
							: cell.getStringCellValue();
					if (size.trim().length() < 1 || size.trim().length() > 225) {
						errors.add("Kích cỡ sản phẩm từ 1-225 ký tự");
					}
					product.setSize(size);
				} catch (Exception e) {
					errors.add("Lỗi nhập kích thước");
				}
				break;
			case 5:
				try {
					String description = cell.getStringCellValue();
					if (description.trim().length() < 3 || description.trim().length() > 225) {
						errors.add("Mô tả sản phẩm tối đa 225 ký tự");
					}
					product.setDescription(description);
				} catch (Exception e) {
					errors.add("Lỗi nhập mô tả");
				}
				break;
			}
		}
		return errors;
	}

}
