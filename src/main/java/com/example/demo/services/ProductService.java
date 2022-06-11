package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
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

import com.example.demo.entities.Categories;
import com.example.demo.entities.Products;
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
			String filename = attach.getOriginalFilename();
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

	public void importFileExcel(Part excelPart, Categories category, RedirectAttributes redirectAttributes) {
		List<Products> products = new ArrayList<>();
		try {
//			Part excelPart = request.getPart("file_excel");

			InputStream fis = excelPart.getInputStream();

			XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			for (Row row : mySheet) {
				System.out.println("row" + row.getRowNum());
				if (row.getRowNum() > 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					Products product = new Products();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						System.out.println("index " + cell.getRowIndex());
						switch (cell.getColumnIndex()) {
							case 0:
								product.setName(cell.getStringCellValue());
								break;
							case 1:
								product.setStock((int) cell.getNumericCellValue());
								break;
							case 2:
								product.setPrice((int) cell.getNumericCellValue());
								break;
							case 3:
								product.setColor(cell.getStringCellValue());
								break;
							case 4:
								product.setSize(cell.getCellTypeEnum() == CellType.NUMERIC ? (int) cell.getNumericCellValue() + ""
										: cell.getStringCellValue());
								break;
							case 5:
								product.setDescription(cell.getStringCellValue());
								break;
						}
					}
					product.setStatus("1");
					product.setCategory(category);
					System.out.println(product);
					products.add(product);
				}
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", "Lỗi file excel");
			e.printStackTrace();
		}
		if (!products.isEmpty()) {
			try {
				this.productRepository.saveAll(products);
				redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("error", "Thêm mới thất bại");
				e.printStackTrace();
			}
		}
	}

}
