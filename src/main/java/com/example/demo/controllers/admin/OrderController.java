package com.example.demo.controllers.admin;

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
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Products;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderService;

@Controller
@RequestMapping("admin/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;

	@GetMapping("index")
	public String index(Model model, @RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.orderService.index(model, size, page);
		return "index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Orders order) {
		System.out.println("order " + order);
		this.orderService.edit(model, order);
		return "index";
	}

	@RequestMapping("delete/{orderId}/{orderDetailId}")
	public String deleteOrderDetail(
			@PathVariable("orderId") Orders order,
			@PathVariable("orderDetailId") OrderDetail orderDetail
		) {
		this.orderService.deleteOrderDetail(order, orderDetail);
		return "redirect:/admin/orders/edit/" + order.getId();
	}
	
	@PostMapping("update/{orderId}/{productId}")
	public String addProductToOrder(
			@PathVariable("orderId") Orders order,
			@PathVariable("productId") Products product,
			@RequestParam("quantity") int quantity) {
//		System.out.println("size detail " + order.getOrderDetails().get(1));
		this.orderService.addToOrder(order, product, quantity);
		return "redirect:/admin/orders/edit/" + order.getId();
	}
	@PostMapping("updateInfo/{id}")
	public String addProductToOrder(
			@PathVariable("id") Orders order,
			@Valid OrderDeliveryInfo orderDeliveryInfo,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
		) {
		this.cartService.validateFormDeliveryInfo(bindingResult, orderDeliveryInfo);
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDeliveryInfo", bindingResult);
			redirectAttributes.addFlashAttribute("orderDeliveryInfo", orderDeliveryInfo);
		} else {
			this.orderService.updateInfoOrder(orderDeliveryInfo, order);
			redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
		}
		return "redirect:/admin/orders/edit/" + order.getId();
	}

	@RequestMapping("updateStatus/{id}")
	public String updateStatus(@PathVariable("id") Orders order, @RequestParam("status") int newStatus) {
		this.orderService.updateStatus(order, newStatus);
		return "redirect:/admin/orders/index";
	}
}
