package com.example.demo.controllers.admin;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entities.Categories;
import com.example.demo.services.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private HttpSession httpSession;

	@GetMapping("index")
	public String index(Model model, @RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.categoryService.index(model, size, page);
		return "index";
	}

	@PostMapping("store")
	public String store( @ModelAttribute("category") @Valid  Categories category,
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes
			) {
		this.categoryService.validateStringFiedLength(bindingResult, category);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
			redirectAttributes.addFlashAttribute("category", category);
		} else {
			this.categoryService.create(category);
			redirectAttributes.addFlashAttribute("message", "Thêm thành công");
		}
		System.out.println("session " + this.httpSession.getAttribute("error"));
		return "redirect:/admin/categories/index";
	}

	@PostMapping("update/{id}")
	public String update(
			@PathVariable("id") @NotNull int  id, 
			@ModelAttribute("category") @Valid  Categories category, 
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes
		) {
		this.categoryService.validateStringFiedLength(bindingResult, category);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", bindingResult);
			redirectAttributes.addFlashAttribute("category", category);
		} else {
			this.categoryService.update(id, category);
			redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
		}
		return "redirect:/admin/categories/edit/" + id;
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Categories category,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.categoryService.edit(model, category, size, page);
		return "index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Categories category) {
		this.categoryService.delete(category);
		return "redirect:/admin/categories/index";
	}

}
