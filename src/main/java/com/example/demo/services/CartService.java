package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.demo.beans.OrderDeliveryInfo;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Products;
import com.example.demo.repositories.ProductRepository;

@Service
public class CartService {

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private ProductRepository productRepository;

	public void indexCart(Model model) {
		Orders order = (Orders) this.httpSession.getAttribute("order");
		model.addAttribute("content", "shop/content.jsp");
		model.addAttribute("menu", "shop/menu.jsp");
		if (order != null) {
			if (order.getOrderDetails() == null) {
				model.addAttribute("viewContent", "cart/empty_cart.jsp");
			} else {
				if(!model.containsAttribute("orderDeliveryInfo") ) {
					model.addAttribute("orderDeliveryInfo", new OrderDeliveryInfo());
				}
				model.addAttribute("viewContent", "cart/table.jsp");
				model.addAttribute("formCart", "cart/form.jsp");
				model.addAttribute("showFormOrder", true);
			}
		} else {
			model.addAttribute("viewContent", "cart/empty_cart.jsp");
		}
	}

	public void removeProductToCart(Model model, int product_id) {
		Orders order = (Orders) this.httpSession.getAttribute("order");
		if (order != null) {
			if (order.getOrderDetails() != null) {
				OrderDetail orderDetailRemove = new OrderDetail();
				for (OrderDetail o : order.getOrderDetails()) {
					if (o.getProduct().getId() == product_id) {
						orderDetailRemove = o;
					}
				}
				order.getOrderDetails().remove(orderDetailRemove);
				if (order.getOrderDetails().size() > 0) {
					order.setTotalMoney(this.calculateTotalMoney(order.getOrderDetails()));
				} else {
					order.setTotalMoney(0);
				}
			}
		}
		if(order.getTotalMoney() == 0) {
			this.httpSession.removeAttribute("order");
		} else {
			this.httpSession.setAttribute("order", order);
		}
	}

	public void addToCart(Products product, int quantity) {
		Orders order = (Orders) this.httpSession.getAttribute("order");
		List<OrderDetail> orderDetails = new ArrayList<>();
		OrderDetail orderDetail = new OrderDetail();

		if (order != null) {
			if (order.getOrderDetails() != null) {
				orderDetails = order.getOrderDetails();
			}
		} else {
			order = new Orders();
		}
		if (!this.hasProduct(orderDetails, product.getId())) {
			orderDetail.setPrice(product.getPrice());
			orderDetail.setProduct(product);
			orderDetail.setAmount(quantity);

			orderDetails.add(orderDetail);
		}
		order.setOrderDetails(orderDetails);

		order.setTotalMoney(this.calculateTotalMoney(orderDetails));
		this.httpSession.setAttribute("order", order);
	}
	public void validateFormDeliveryInfo(BindingResult bindingResult, OrderDeliveryInfo orderDeliveryInfo) {
		String address = orderDeliveryInfo.getDeliveryAddress();
		if(address.trim().length() < 10 || address.trim().length() > 225) {
			bindingResult.rejectValue("deliveryAddress", "length.orderDeliveryInfo.address", "Địa chỉ nhận hàng từ 10-225 ký tự");
		}
		String note = orderDeliveryInfo.getNote();
		if(note.trim().length() > 225) {
			bindingResult.rejectValue("note", "length.orderDeliveryInfo.note", "Ghi chú tối đa 225 ký tự");
		}
	}
	private boolean hasProduct(List<OrderDetail> orderDetails, int product_id) {
		for (OrderDetail orderDetail : orderDetails) {
			if (orderDetail.getProduct().getId() == product_id) {
				return true;
			}
		}
		return false;
	}

	private double calculateTotalMoney(List<OrderDetail> orderDetails) {
		double totalMoney = 0;
		for (OrderDetail o : orderDetails) {
			totalMoney += o.getPrice() * o.getAmount();
		}
		return totalMoney;
	}

}
