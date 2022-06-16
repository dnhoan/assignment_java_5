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
import com.example.demo.beans.OrderStoreInfo;
import com.example.demo.entities.OrderDetailStore;
import com.example.demo.entities.OrdersStore;
import com.example.demo.entities.Products;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderService;
import com.example.demo.services.OrderStoreService;

@Controller
@RequestMapping("/admin/ordersStore")
public class OrderStoreController {


	@Autowired
	private OrderStoreService orderStoreService;
	@Autowired
	private CartService cartService;

	@GetMapping("index")
	public String index(Model model, @RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.orderStoreService.index(model, size, page);
		return "index";
	}
	
	@GetMapping("create")
	public String create(Model model, @RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		this.orderStoreService.create(model);
		return "index";
	}
	@PostMapping("create/addProduct/{product_id}")
	public String addProduct(
			@PathVariable("product_id") Products product,
			@RequestParam("quantity") int quantity) {
		this.orderStoreService.addProductToCreateOrderStore(product, quantity);
		return "redirect:/admin/ordersStore/create";
	}
	@GetMapping("create/removeProduct/{product_id}") 
	public String removeProductToCart(
			@PathVariable("product_id") int productId) {
		this.orderStoreService.removeProductToCreateOrderStore(productId);
		return "redirect:/admin/ordersStore/create";
	}

	
	@PostMapping("store") 
	public String store(
			@Valid OrderStoreInfo orderStoreInfo,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
	) {
		this.orderStoreService.validateFormDeliveryInfo(bindingResult, orderStoreInfo);
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderStoreInfo", bindingResult);
			redirectAttributes.addFlashAttribute("orderStoreInfo", orderStoreInfo);
			return "redirect:/admin/ordersStore/create";
		} else {
			this.orderStoreService.storeOrder(orderStoreInfo);
			redirectAttributes.addFlashAttribute("message", "Đặt hàng thành công");
		}
		return "redirect:/admin/ordersStore/index";
	}
	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") OrdersStore order) {
		this.orderStoreService.edit(model, order);
		return "index";
	}

	@RequestMapping("delete/{orderId}/{orderDetailId}")
	public String deleteOrderDetail(
			@PathVariable("orderId") OrdersStore order,
			@PathVariable("orderDetailId") OrderDetailStore orderDetail
		) {
		this.orderStoreService.deleteOrderDetail(order, orderDetail);
		return "redirect:/admin/ordersStore/edit/" + order.getId();
	}
	
	@PostMapping("update/{orderId}/{productId}")
	public String addProductToOrder(
			@PathVariable("orderId") OrdersStore order,
			@PathVariable("productId") Products product,
			@RequestParam("quantity") int quantity) {
		this.orderStoreService.addToOrder(order, product, quantity);
		return "redirect:/admin/ordersStore/edit/" + order.getId();
	}
	@PostMapping("updateInfo/{id}")
	public String addProductToOrder(
			@PathVariable("id") OrdersStore order,
			@Valid OrderStoreInfo orderStoreInfo,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes
		) {
		this.orderStoreService.validateFormDeliveryInfo(bindingResult, orderStoreInfo);
		if(bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "Form chưa hợp lệ");
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderStoreInfo", bindingResult);
			redirectAttributes.addFlashAttribute("orderStoreInfo", orderStoreInfo);
		} else {
			this.orderStoreService.updateInfoOrder(orderStoreInfo, order);
			redirectAttributes.addFlashAttribute("message", "Cập nhật thành công");
		}
		return "redirect:/admin/ordersStore/edit/" + order.getId();
	}

	@RequestMapping("updateStatus/{id}")
	public String updateStatus(@PathVariable("id") OrdersStore order, @RequestParam("status") int newStatus) {
		this.orderStoreService.updateStatus(order, newStatus);
		return "redirect:/admin/ordersStore/index";
	}
}
	
	

