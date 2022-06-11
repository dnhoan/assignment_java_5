package com.example.demo.controllers.admin;

import com.example.demo.entities.Categories;
import com.example.demo.entities.Products;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;


@Controller
@RequestMapping("admin/products")
public class ProductController {

	@Autowired
	private ProductService productService;
//	@Autowired
//	private RedirectAttributes redirectAttributes;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private HttpServletResponse response;

	@GetMapping("index")
	public String index(Model model, @RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.productService.index(model, size, page);
		return "index";
	}

	@PostMapping("store")
	public String store(@ModelAttribute("product") @Valid Products product, BindingResult bindingResult,
			@RequestParam("avatar") MultipartFile attach, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException {
		if (attach.isEmpty() || !Objects.requireNonNull(attach.getContentType()).split("/")[0].equalsIgnoreCase("image")) {
			bindingResult.addError(new ObjectError("errorAvt", "Vui lòng chọn ảnh"));
			redirectAttributes.addFlashAttribute("errorAvt", "Vui lòng chọn ảnh");
		}
		this.productService.validateStringFiedLength(bindingResult, product);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
			redirectAttributes.addFlashAttribute("product", product);
		} else {
			String image = this.productService.uploadFile(attach);
			product.setImage(image);
			this.productService.create(product);
			redirectAttributes.addFlashAttribute("message", "Thêm thành công");
		}
		return "redirect:/admin/products/index";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") int id, @ModelAttribute("product") @Valid Products product,
			BindingResult bindingResult, @RequestParam("avatar") MultipartFile attach,
			RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		if (!attach.isEmpty() && !Objects.requireNonNull(attach.getContentType()).split("/")[0].equalsIgnoreCase("image")) {
			bindingResult.addError(new ObjectError("errorAvt", "Vui lòng chọn ảnh"));
		}
		this.productService.validateStringFiedLength(bindingResult, product);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
			redirectAttributes.addFlashAttribute("product", product);
		} else {
			String image = this.productService.uploadFile(attach);
			Products currentProduct = this.productService.getById(id);
			product.setImage(image.isEmpty() ? currentProduct.getImage() : image);
			this.productService.update(id, product);
			redirectAttributes.addFlashAttribute("message", "Thêm thành công");
		}
		return "redirect:/admin/products/edit/" + id;
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Products product,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.productService.edit(model, product, size, page);
		return "index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Products product) {
		this.productService.delete(product);
		return "redirect:/admin/products/index";
	}

	@GetMapping("downloadFile")
	public void downloadFile() throws IOException {
		String fullPath = this.servletContext.getRealPath("/files_download/pattern_product.xlsx");
		Path path = Paths.get(fullPath);
		byte[] data = Files.readAllBytes(path);
		// Thiết lập thông tin trả về
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename=product_partern.xlsx");
		response.setContentLength(data.length);

		InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));

		// Ghi file ra response outputstream.
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		inputStream.close();
		outStream.close();
	}

	@PostMapping("importExcel")
	public String importExcel(@RequestParam("category_id") Categories categoy,
			@RequestParam("file_excel") Part excelPart, RedirectAttributes redirectAttributes) {
		this.productService.importFileExcel(excelPart, categoy, redirectAttributes);
		return "redirect:/admin/products/index";
	}

}
