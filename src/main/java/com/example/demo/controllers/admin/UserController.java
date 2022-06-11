package com.example.demo.controllers.admin;

import com.example.demo.entities.Users;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("admin/users")
public class UserController {

	@Autowired
	private UserService userSerice;

	@GetMapping("index")
	public String index(Model model, @RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.userSerice.index(model, size, page);
		return "index";
	}

	@PostMapping("store")
	public String store(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		this.userSerice.validateLengthString(bindingResult, user);
		this.userSerice.validatePassword(bindingResult, user);
		this.userSerice.checkEmailExist(bindingResult, user);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
			redirectAttributes.addFlashAttribute("user", user);
		} else {
			this.userSerice.create(user);
			redirectAttributes.addFlashAttribute("message", "Thêm thành công");
		}
		return "redirect:/admin/users/index";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") int id,@Valid Users user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		this.userSerice.validateLengthString(bindingResult, user);
		this.userSerice.checkEmailWhenUpdate(bindingResult, user, id);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
			redirectAttributes.addFlashAttribute("user", user);
		} else {
			this.userSerice.update(id, user);
			redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
		}
		return "redirect:/admin/users/edit/" + id;
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Users user,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.userSerice.edit(model, user, size, page);
		return "index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Users user) {
		this.userSerice.delete(user);
		return "redirect:/admin/users/index";
	}
}
