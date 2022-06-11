package com.example.demo.controllers.shop;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.beans.OrderDeliveryInfo;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Users;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderService;


@Controller
@RequestMapping("shop/orders")
public class ShopOrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;

	@GetMapping("index")
	public String index(Model model,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page
		) {
		this.orderService.indexOrdersUser(model, size, page);
		return "shop_index";
	}
	
	@RequestMapping("updateStatus/{id}")
	public String updateStatus(@PathVariable("id") @Valid Orders order,
							   @RequestParam(value = "status") int newStatus
	) {
		this.orderService.updateStatus(order, newStatus);
		return "redirect:/shop/orders/index";
	}
	
	@PostMapping("store") 
	public String store(
			@Valid OrderDeliveryInfo orderDeliveryInfo,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
	) {
		this.cartService.validateFormDeliveryInfo(bindingResult, orderDeliveryInfo);
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDeliveryInfo", bindingResult);
			redirectAttributes.addFlashAttribute("orderDeliveryInfo", orderDeliveryInfo);
			return "redirect:/shop/cart/index";
		} else {
			this.orderService.storeOrder(orderDeliveryInfo);
			redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công");
		}
		return "redirect:/shop/orders/index";
	}
}
