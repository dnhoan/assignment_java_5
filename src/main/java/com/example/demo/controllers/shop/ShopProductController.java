package com.example.demo.controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.services.ProductService;

@Controller
@RequestMapping("shop")
public class ShopProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("products")
	public String getProducts(Model model,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.productService.getProductsSell(model, size, page);
		return "shop_index";
	}
	
	@GetMapping("products/{id}")
	public String getProductDetail(
			Model model,
			@PathVariable("id") int id,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page 
	) {
		this.productService.getProductDetail(model, id, size, page);
		return "shop_index";
	}
}
