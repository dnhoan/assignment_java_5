package com.example.demo.controllers.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Products;
import com.example.demo.services.CartService;

@Controller
@RequestMapping("shop/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("index")
	public String index(
		Model model
		) {
		this.cartService.indexCart(model);
		return "shop_index";
	}
	
	@RequestMapping("add")	
	public String addToCart(
			@RequestParam("product_id") Products product,
			@RequestParam("quantity") int quantity
	) {
		System.out.println(product.toString());
		this.cartService.addToCart(product, quantity);
		return "redirect:/shop/cart/index";
	}
	
	@GetMapping("remove/{product_id}") 
	public String removeProductToCart(Model model, @PathVariable("product_id") int productId) {
		this.cartService.removeProductToCart(model, productId);
		return "redirect:/shop/cart/index";
	}
}
