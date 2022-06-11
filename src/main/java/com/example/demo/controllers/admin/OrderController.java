package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Orders;
import com.example.demo.services.OrderService;

@Controller
@RequestMapping("admin/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("index")
	public String index(Model model,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page
		) {
		this.orderService.index(model, size, page);
		return "index";
	}

	@RequestMapping("updateStatus/{id}")
	public String updateStatus(@PathVariable("id") Orders order,
							   @RequestParam("status") int newStatus
	) {
		this.orderService.updateStatus(order, newStatus);
		return "redirect:/admin/orders/index";
	}
}
