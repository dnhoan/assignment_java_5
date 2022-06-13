package com.example.demo.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.beans.OrderDeliveryInfo;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.Orders;
import com.example.demo.entities.Products;
import com.example.demo.entities.Users;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private ProductRepository productRepository;
//	private OrderDetail orderDetailStore;

	public void index(Model model, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Orders> items = this.orderRepository.findByOrderByCtimeDesc(pageable);
		model.addAttribute("items", items);
		model.addAttribute("content", "admin/content.jsp");
		model.addAttribute("menu", "admin/menu.jsp");
		model.addAttribute("showForm", false);
		model.addAttribute("table", "orders/table.jsp");
	}

	public void edit(Model model, Orders order) {
		if(!model.containsAttribute("orderDeliveryInfo")) {
			OrderDeliveryInfo orderDeliveryInfo = new OrderDeliveryInfo(order.getDeliveryAddress(),
					order.getPhoneNumber(), order.getNote());
			model.addAttribute("orderDeliveryInfo", orderDeliveryInfo);
		}
		Pageable pageable = PageRequest.of(0, 10);
		Page<Products> items = this.productRepository.findAll(pageable);
		model.addAttribute("orderEdit", order);
		model.addAttribute("items", items);

		model.addAttribute("form", "orders/edit.jsp");
		model.addAttribute("table", "orders/table_products.jsp");
		
	}

	public void deleteOrderDetail(Orders order, OrderDetail orderDetail) {
		double currentTotalMoney = order.getTotalMoney();
		order.setTotalMoney(currentTotalMoney - orderDetail.getPrice() * orderDetail.getAmount());
		this.orderDetailRepository.delete(orderDetail);
		this.orderRepository.save(order);
	}

	public void addToOrder(Orders order, Products product, int quantity) {
		List<OrderDetail> orderDetails = this.orderDetailRepository.findByOrderIdEquals(order.getId());
		System.out.println("size detail " + order.getOrderDetails().size());
		System.out.println("order id " + order.getId());
		OrderDetail orderDetail = this.checkOrderDetail(orderDetails, product.getId());
		if (orderDetail == null) {
			orderDetail = new OrderDetail();
			orderDetail.setPrice(product.getPrice());
			orderDetail.setOrder(order);
			orderDetail.setAmount(quantity);
			orderDetail.setProduct(product);
		} else {
			orderDetails.remove(orderDetail);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setAmount(quantity);
		}
		orderDetails.add(orderDetail);
		order.setOrderDetails(orderDetails);
		order.setTotalMoney(this.calculateTotalMoney(orderDetails));
		this.orderDetailRepository.save(orderDetail);
		this.orderRepository.save(order);
		
	}

	private double calculateTotalMoney(List<OrderDetail> orderDetails) {
		double totalMoney = 0;
		for (OrderDetail o : orderDetails) {
			totalMoney += o.getPrice() * o.getAmount();
		}
		return totalMoney;
	}

	private OrderDetail checkOrderDetail(List<OrderDetail> orderDetails, int productId) {
		for (OrderDetail o : orderDetails) {
			if (o.getProduct().getId() == productId) {
				return o;
			}
		}
		return null;
	}

	public void updateInfoOrder(OrderDeliveryInfo t, Orders order) {
		order.setDeliveryAddress(t.getDeliveryAddress());
		order.setNote(t.getNote());
		order.setPhoneNumber(t.getPhoneNumber());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setMtime(timestamp);
		this.orderRepository.save(order);
	}
	
	public void storeOrder(OrderDeliveryInfo t) {
		Orders order = (Orders) this.httpSession.getAttribute("order");
		System.out.println("size" + order.getOrderDetails().size());
		order.setDeliveryAddress(t.getDeliveryAddress());
		order.setNote(t.getNote());
		order.setPhoneNumber(t.getPhoneNumber());
		Users users = (Users) this.httpSession.getAttribute("user");
		order.setUser(users);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String order_id = timestamp.getTime() + "";
		order.setOrderId(order_id);
		order.setOrderStatus(0);
		this.orderRepository.save(order);
		this.orderRepository.flush();
		for (int i = 0; i < order.getOrderDetails().size(); i++) {
			OrderDetail orderDetail = order.getOrderDetails().get(i);
			orderDetail.setOrder(order);
			this.orderDetailRepository.save(orderDetail);
		}
		this.httpSession.setAttribute("order", new Orders());
	}

	public void updateStatus(Orders order, int status) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setMtime(timestamp);
		order.setOrderStatus(status);
		this.orderRepository.save(order);
	}

	public void indexOrdersUser(Model model, int size, int page) {
		Users user = (Users) httpSession.getAttribute("user");
		Pageable pageable = PageRequest.of(page, size);
		Page<Orders> items = this.orderRepository.findByUserIdOrderByCtimeDesc(user.getId(), pageable);
		model.addAttribute("items", items);
		if (items.getContent().size() > 0) {
			model.addAttribute("viewContent", "orders/table.jsp");
		} else {
			model.addAttribute("viewContent", "orders/nodata.jsp");
		}
	}

}
