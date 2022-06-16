package com.example.demo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Products;
import com.example.demo.entities.Users;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService implements ICrudService<Products, Integer> {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ServletContext servletContext;
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
			String filename = "product_img_" + timestamp;
			File dirFile = new File(this.servletContext.getRealPath("/files"));
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			File file = new File(this.servletContext.getRealPath("/files/" + filename));
			attach.transferTo(file);
			return filename;
		}
		return "";
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
		if (lengthColor < 3 || lengthColor > 225) {
			bindingResult.rejectValue("color", "length.product.color", "Màu sắc sản phẩm từ 3 - 225 ký tự");
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

	@Scheduled(fixedRate = 10 * 1000)
	public void createBatchProductByExcel() {
		 File fileOrDir = new File(this.servletContext.getRealPath("/import_batch_products"));
		 Optional<File[]> files = Optional.of(fileOrDir.listFiles());
		 if(!files.isEmpty()) {
			 for (File file : files.get()) {
				String uid = file.getName().split("_")[1];
				String cateId = file.getName().split("_")[3];
				this.readFileExcel(file);
			}
		 }
	}
	private void readFileExcel(File file) {
		List<Products> products = new ArrayList<>();
		try {
			InputStream targetStream = new FileInputStream(file);
			System.out.println("file " + file);
			
			XSSFWorkbook myWorkBook = new XSSFWorkbook(targetStream);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			for (Row row : mySheet) {
				System.out.println("row" + row.getRowNum());
				if (row.getRowNum() > 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					Products product = new Products();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
							case 0:
								String name = cell.getCellTypeEnum() == CellType.NUMERIC ? (int) cell.getNumericCellValue() + ""
										: cell.getStringCellValue();
								System.out.println("name " + name);
								
								
								product.setName(name);
								break;
							case 1:
								try {
									int stock = (int) cell.getNumericCellValue();
									product.setStock(stock);
								} catch (Exception e) {
									
								}
								break;
							case 2:
								try {
									int price = (int) cell.getNumericCellValue();
									product.setPrice(price);
								} catch (Exception e) {
									
								}
								break;
							case 3:
								try {
									String color = cell.getStringCellValue();
									product.setColor(color);
								} catch (Exception e) {
									
								}
								break;
							case 4:
								String size = cell.getCellTypeEnum() == CellType.NUMERIC ? (int) cell.getNumericCellValue() + ""
										: cell.getStringCellValue();
								product.setSize(size);
								break;
							case 5:
								String description = cell.getStringCellValue();
								product.setDescription(description);
								break;
						}
					}
					System.out.println(product);
//					product.setStatus("1");
//					product.setCategory(category);
//					System.out.println(product);
//					products.add(product);
				}
			}
			targetStream.close();
			myWorkBook.close();
		} catch (Exception e) {
			e.printStackTrace();
//			redirectAttributes.addFlashAttribute("error", "Lỗi file excel");
		}
		if (!products.isEmpty()) {
			try {
//				this.productRepository.saveAll(products);
//				redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
			} catch (Exception e) {
//				redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại");
				e.printStackTrace();
			}
		}
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
			String filename = "uid_" +  u.getId() + "_cateId_" + category.getId() + "_products.xlsx";
			File file = new File(this.servletContext.getRealPath("/import_batch_products/" + filename));
			System.out.println("file " + file);
			excelPart.transferTo(file);
		}

	}

}
