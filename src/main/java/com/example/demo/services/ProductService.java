package com.example.demo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constants.Constants;
import com.example.demo.entities.Categories;
import com.example.demo.entities.Products;
import com.example.demo.entities.Users;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ProductService implements ICrudService<Products, Integer> {
	private static Font font_12_normer, font_20, titleFont, font_12, font_16, font_i;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HttpSession httpSession;

	@Override
	public void index(Model model, int size, int page) {
		this.getPageProucts(model, size, page);
		if (!model.containsAttribute("product")) {
			model.addAttribute("product", new Products());
		}
		this.configViews(model, "products/create.jsp");
	}

	@Override
	public void create(Products t) {
		this.productRepository.save(t);
	}

	@Override
	public void update(Integer k, Products t) {
		this.productRepository.save(t);
	}

	@Override
	public void delete(Products t) {
		this.productRepository.delete(t);
	}

	@Override
	public void edit(Model model, Products t, int size, int page) {
		this.getPageProucts(model, size, page);
		if (!model.containsAttribute("product")) {
			model.addAttribute("product", t);
		}
		this.configViews(model, "products/edit.jsp");
	}

	@Override
	public Products getById(Integer k) {
		return this.productRepository.getReferenceById(k);
	}

	public String uploadFile(MultipartFile attach) throws IllegalStateException, IOException {
		if (!attach.isEmpty()) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String filename = timestamp.getTime() + "_" + attach.getOriginalFilename();
			File dirFile = new File(this.servletContext.getRealPath("/files"));
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			File file = new File(dirFile, filename);
			attach.transferTo(file);
			return filename;
		}
		return "";
	}

	public void printTem(Products product) {
		List<Products> products = new ArrayList<Products>();
		products.add(product);
		this.print(products);
	}

	public void printBatchTem() {
		List<Products> products = this.productRepository.findAll();
		
		this.print(products);
	}

	public void exportBatchExcel() throws IOException {
		List<Products> products = this.productRepository.findAll();
		this.writeExcel(products);
	}

	private static CellStyle cellStyleFormatNumber = null;

	private void writeExcel(List<Products> products) throws IOException {
		// Create Workbook
		String date = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss").format(new Date());
		File file = new File("C:/temp/product_excel");
		if(!file.exists()) file.mkdir();
		String excelFilePath = "C:/temp/product_excel/products_" + date + ".xlsx";
		Workbook workbook = getWorkbook(excelFilePath);
		
		// Create sheet
		Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name

		int rowIndex = 0;

		// Write header
		writeHeader(sheet, rowIndex);

		// Write data
		rowIndex++;
		for (Products product : products) {
			// Create row
			Row row = sheet.createRow(rowIndex);
			writeProduct(product, row);
			rowIndex++;
		}
		// Auto resize column witdth
		int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
		autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		createOutputFile(workbook, excelFilePath);
		System.out.println("Done!!!");
	}

	// Create workbook
	private  Workbook getWorkbook(String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook();
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

		return workbook;
	}

	// Write header with format
	private static void writeHeader(Sheet sheet, int rowIndex) {
		Row row = sheet.createRow(rowIndex);

		// Create cells
		Cell cell = row.createCell(Constants.COLUMN_INDEX_NAME);
		cell.setCellValue("Tên sản phẩm");
		cell = row.createCell(Constants.COLUMN_INDEX_STOCK);
		cell.setCellValue("Tồn kho");
		cell = row.createCell(Constants.COLUMN_INDEX_PRICE);
		cell.setCellValue("Giá");
		cell = row.createCell(Constants.COLUMN_INDEX_COLOR);
		cell.setCellValue("Màu sắc");
		cell = row.createCell(Constants.COLUMN_INDEX_SIZE);
		cell.setCellValue("Kích cỡ");
		cell = row.createCell(Constants.COLUMN_INDEX_NOTE);
		cell.setCellValue("Mô tả");
		cell = row.createCell(Constants.COLUMN_INDEX_CATEGORY);
		cell.setCellValue("Phân loại");
	}

	// Write data
	private  void writeProduct(Products product, Row row) {
		if (cellStyleFormatNumber == null) {
			// Format number
			short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
			Workbook workbook = row.getSheet().getWorkbook();
			cellStyleFormatNumber = workbook.createCellStyle();
			cellStyleFormatNumber.setDataFormat(format);
		}

		Cell cell = row.createCell(Constants.COLUMN_INDEX_NAME);
		cell.setCellValue(product.getName());
		cell = row.createCell(Constants.COLUMN_INDEX_STOCK);
		cell.setCellValue(product.getStock());
		cell = row.createCell(Constants.COLUMN_INDEX_PRICE);
		cell.setCellValue(product.getPrice());
		cell = row.createCell(Constants.COLUMN_INDEX_COLOR);
		cell.setCellValue(product.getColor());
		cell = row.createCell(Constants.COLUMN_INDEX_SIZE);
		cell.setCellValue(product.getColor());
		cell = row.createCell(Constants.COLUMN_INDEX_NOTE);
		cell.setCellValue(product.getColor());
		cell = row.createCell(Constants.COLUMN_INDEX_CATEGORY);
		cell.setCellValue(product.getCategory().getName());
	}

	private  void autosizeColumn(Sheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}
	private  void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
		try (OutputStream os = new FileOutputStream(excelFilePath)) {
			workbook.write(os);
		}
	}

	private void print(List<Products> products) {
		try {
			titleFont = new Font(
					BaseFont.createFont("C:/temp/Tahoma Regular font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 25);
			font_20 = new Font(
					BaseFont.createFont("C:/temp/Tahoma Regular font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20);
			font_16 = new Font(
					BaseFont.createFont("C:/temp/Tahoma Regular font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16);
			font_12 = new Font(
					BaseFont.createFont("C:/temp/Tahoma Regular font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
			font_12_normer = new Font(
					BaseFont.createFont("C:/temp/Tahoma Regular font.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED),
					12);
			font_i = new Font(
					BaseFont.createFont("C:/temp/Tahoma Regular font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 22);
			Document document = new Document(new Rectangle(230, 350));
			document.setMargins(10, 10, 5, 5);
			File file = new File("C:/temp/product");
			if (!file.exists()) {
				file.mkdir();
			}
			String date = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss").format(new Date());
			PdfWriter.getInstance(document, new FileOutputStream("C:/temp/product/tem_" + date + ".pdf"));
			document.open();
			for (Products product : products) {
				document.add(new Paragraph("     Cửa hàng bán quần áo", font_16));
				BarcodeQRCode myCode = new BarcodeQRCode(product.getId() + "", 195, 195, null);
				Image qrimg = myCode.getImage();
				document.add(qrimg);
				document.add(new Paragraph(product.getName(), font_12_normer));
				addEmptyLine(new Paragraph(), 1);
				document.add(new Paragraph(toVnd(product.getPrice()), font_20));
				document.newPage();
			}
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static String toVnd(float dong) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		return currencyVN.format(dong);
	}

	public void getProductDetail(Model model, int id, int size, int page) {
		this.getPageProucts(model, size, page);
		Pageable pageable = PageRequest.of(page, size);
		Page<Products> items = this.productRepository.findAll(pageable);
		model.addAttribute("items", items);
		model.addAttribute("product", this.productRepository.getReferenceById(id));
		model.addAttribute("content", "shop/content.jsp");
		model.addAttribute("menu", "shop/menu.jsp");
		model.addAttribute("viewContent", "products/product_carts.jsp");
	}

	public void getProductsSell(Model model, int size, int page) {
		this.getPageProucts(model, size, page);
		model.addAttribute("viewContent", "products/product_carts.jsp");
		model.addAttribute("content", "shop/content.jsp");
		model.addAttribute("menu", "shop/menu.jsp");
	}

	public void validateStringFiedLength(BindingResult bindingResult, Products product) {
		int lengthName = product.getName().trim().length();
		if (lengthName < 10 || lengthName > 225) {
			bindingResult.rejectValue("name", "length.product.name", "Tên sản phẩm từ 10-225 ký tự");
		}
		int lengthColor = product.getColor().trim().length();
		if (lengthColor < 2 || lengthColor > 225) {
			bindingResult.rejectValue("color", "length.product.color", "Màu sắc sản phẩm từ 2 - 225 ký tự");
		}
		int lengthSize = product.getSize().trim().length();
		if (lengthSize < 1 || lengthSize > 225) {
			bindingResult.rejectValue("size", "length.product.size", "Kích cỡ sản phẩm tối đa 225 ký tự");
		}
		int lengthNote = product.getDescription().trim().length();
		if (lengthNote > 225) {
			bindingResult.rejectValue("note", "length.product.note", "Không quá 225 ký tự");
		}
	}

	private void configViews(Model model, String form) {
		model.addAttribute("content", "admin/content.jsp");
		model.addAttribute("menu", "admin/menu.jsp");
		model.addAttribute("form", form);
		model.addAttribute("table", "products/table.jsp");
	}

	private void getPageProucts(Model model, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Products> items = this.productRepository.findAll(pageable);
		model.addAttribute("items", items);
		List<Categories> categories = this.categoryRepository.findAll();
		model.addAttribute("categories", categories);
	}

	public void importFileExcel(MultipartFile excelPart, Categories category, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException {
		if (!excelPart.isEmpty()) {
			System.out.println(excelPart.getContentType());
			File dirFile = new File(this.servletContext.getRealPath("/import_batch_products"));
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			Users u = (Users) this.httpSession.getAttribute("user");
			String filename = "uid_" + u.getId() + "_cateId_" + category.getId() + "_products.xlsx";
			File file = new File(this.servletContext.getRealPath("/import_batch_products/" + filename));
			System.out.println("file " + file);
			excelPart.transferTo(file);
		}

	}

}
