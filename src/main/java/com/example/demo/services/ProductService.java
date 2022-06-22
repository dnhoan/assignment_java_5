package com.example.demo.services;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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
import com.example.demo.entities.Users;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class ProductService implements ICrudService<Products, Integer> {

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
			File file = new File(dirFile,filename);
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
