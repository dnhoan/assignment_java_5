package com.example.demo.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
import com.example.demo.entities.Users;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrderRepository;

@Service
public class OrderService{

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	public void index(Model model, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Orders> items = this.orderRepository.findByOrderByCtimeDesc(pageable);
		model.addAttribute("items", items);
		model.addAttribute("content", "admin/content.jsp");
		model.addAttribute("menu", "admin/menu.jsp");
		model.addAttribute("showForm", false);
		model.addAttribute("table", "orders/table.jsp");
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
		if(items.getContent().size() > 0) {
			model.addAttribute("viewContent", "orders/table.jsp");
		} else {
			model.addAttribute("viewContent", "orders/nodata.jsp");
		}
	}

}
