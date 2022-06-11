package com.example.demo.controllers.authentication;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.beans.UserLogin;
import com.example.demo.beans.UserRegister;
import com.example.demo.services.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private HttpSession httpSession;
	

	@GetMapping("login")
	public String login(Model model) {
		if (!model.containsAttribute("userLogin")) {
			model.addAttribute(new UserLogin());
		}
		return "account/login";
	}

	@PostMapping("login")
	public String login(@Valid UserLogin userLogin, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		boolean isLogin = this.accountService.login(userLogin, bindingResult);
		if (!isLogin) {
			redirectAttributes.addFlashAttribute("error", "Đăng nhập sai");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogin",
					bindingResult);
			redirectAttributes.addFlashAttribute("userLogin", userLogin);
			return "redirect:/login";
		} else {
			int role = (int) this.httpSession.getAttribute("role");
			if (role == 1) {
				return "redirect:/admin/users/index";
			}
			return "redirect:/shop/products";
		}
			
	}

	@GetMapping("register")
	public String register(Model model) {
		if (!model.containsAttribute("userRegister")) {
			model.addAttribute("userRegister", new UserRegister());
		}
		return "account/register";
	}

	@PostMapping("register")
	public String register(@Valid UserRegister userRegister, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		this.accountService.validateLengthString(bindingResult, userRegister);
		this.accountService.validatePassword(bindingResult, userRegister);
		this.accountService.checkEmailExist(bindingResult, userRegister);
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);
			redirectAttributes.addFlashAttribute("userRegister", userRegister);
			return "redirect:/register";
		} else {
			this.accountService.create(userRegister);
			redirectAttributes.addFlashAttribute("message", "Đăng ký thành công thành công");
		}
		return "redirect:/login";
	}

	@GetMapping("forgotPassword")
	public String forgotPassword() {
		return "account/forgot_password";
	}

	@GetMapping("logout")
	public String logout() {
		this.httpSession.removeAttribute("user");
		this.httpSession.removeAttribute("role");
		this.httpSession.removeAttribute("order");
		return "redirect:/login";
	}
}
