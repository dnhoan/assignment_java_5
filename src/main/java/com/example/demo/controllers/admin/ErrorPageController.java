package com.example.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
	@GetMapping("403")
	public String forbidden() {
		return "errors/403";
	}
	@GetMapping("400")
	public String badRequest() {
		return "errors/403";
	}
	@GetMapping("404")
	public String notFoud() {
		return "errors/404";
	}
	@GetMapping("500")
	public String server() {
		return "errors/500";
	}
}
