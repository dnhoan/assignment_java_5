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
import org.springframework.validation.BindingResult;

import com.example.demo.beans.OrderDeliveryInfo;
import com.example.demo.beans.OrderStoreInfo;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.OrderDetailStore;
import com.example.demo.entities.Orders;
import com.example.demo.entities.OrdersStore;
import com.example.demo.entities.Products;
import com.example.demo.entities.Users;
import com.example.demo.repositories.OrderDetailRepository;
import com.example.demo.repositories.OrderDetailStoreRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.OrderStoreRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class OrderStoreService {

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private OrderStoreRepository orderStoreRepository;
	@Autowired
	private OrderDetailStoreRepository orderDetailStoreRepository;
	@Autowired
	private ProductRepository productRepository;
//	private OrderDetail orderDetailStore;

	public void index(Model model, int size, int page) {
		Pageable pageable = PageRequest.of(page, size);
		Page<OrdersStore> items = this.orderStoreRepository.findByOrderByCtimeDesc(pageable);
		model.addAttribute("items", items);
		model.addAttribute("content", "admin/content.jsp");
		model.addAttribute("menu", "admin/menu.jsp");
		model.addAttribute("showForm", false);
		model.addAttribute("table", "orders_store/table.jsp");
	}

	public void edit(Model model, OrdersStore order) {
		if(!model.containsAttribute("orderStoreInfo")) {
			OrderStoreInfo orderStoreInfo = new OrderStoreInfo(order.getNameCustomer(),order.getDeliveryAddress(),
					order.getPhoneNumber(), order.getNote());
			model.addAttribute("orderStoreInfo", orderStoreInfo);
		}
		Pageable pageable = PageRequest.of(0, 10);
		Page<Products> items = this.productRepository.findAll(pageable);
		model.addAttribute("orderEdit", order);
		model.addAttribute("items", items);

		model.addAttribute("form", "orders_store/edit.jsp");
		model.addAttribute("table", "orders_store/table_products.jsp");
		
	}
	public void create(Model model) {
		if(!model.containsAttribute("orderStoreInfo")) {
			model.addAttribute("orderStoreInfo", new OrderStoreInfo());
		}
		OrdersStore ordersStore = (OrdersStore) this.httpSession.getAttribute("ordersStore");
		if( ordersStore == null) {
			ordersStore = new OrdersStore();
			ordersStore.setOrderDetailStores(new ArrayList<OrderDetailStore>());
			this.httpSession.setAttribute("ordersStore", ordersStore);
		}
		Pageable pageable = PageRequest.of(0, 10);
		Page<Products> items = this.productRepository.findAll(pageable);
		model.addAttribute("items", items);
		model.addAttribute("form", "orders_store/create.jsp");
		model.addAttribute("table", "orders_store/table_products.jsp");
		
	}
	
	public void addProductToCreateOrderStore(Products product, int quantity) {
		OrdersStore ordersStore = (OrdersStore) this.httpSession.getAttribute("ordersStore");
		List<OrderDetailStore> orderDetailStores = new ArrayList<>();
		OrderDetailStore orderDetailStore = new OrderDetailStore();

		if (ordersStore != null) {
			if (ordersStore.getOrderDetailStores() != null) {
				orderDetailStores = ordersStore.getOrderDetailStores();
			}
		} else {
			ordersStore = new OrdersStore();
		}
		if (!this.hasProduct(orderDetailStores, product.getId())) {
			orderDetailStore.setPrice(product.getPrice());
			orderDetailStore.setProduct(product);
			orderDetailStore.setAmount(quantity);

			orderDetailStores.add(orderDetailStore);
		}
		ordersStore.setOrderDetailStores(orderDetailStores);

		ordersStore.setTotalMoney(this.calculateTotalMoney(orderDetailStores));
		this.httpSession.setAttribute("ordersStore", ordersStore);
	}

	public void removeProductToCreateOrderStore(int product_id) {
		OrdersStore ordersStore = (OrdersStore) this.httpSession.getAttribute("ordersStore");
		if (ordersStore != null) {
			if (ordersStore.getOrderDetailStores() != null) {
				OrderDetailStore orderDetailRemove = new OrderDetailStore();
				for (OrderDetailStore o : ordersStore.getOrderDetailStores()) {
					if (o.getProduct().getId() == product_id) {
						orderDetailRemove = o;
					}
				}
				ordersStore.getOrderDetailStores().remove(orderDetailRemove);
				if (ordersStore.getOrderDetailStores().size() > 0) {
					ordersStore.setTotalMoney(this.calculateTotalMoney(ordersStore.getOrderDetailStores()));
				} else {
					ordersStore.setTotalMoney(0);
				}
			}
		}
		if(ordersStore.getTotalMoney() == 0) {
			this.httpSession.removeAttribute("ordersStore");
		} else {
			this.httpSession.setAttribute("ordersStore", ordersStore);
		}
	}
	
	private boolean hasProduct(List<OrderDetailStore> orderDetailStores, int product_id) {
		for (OrderDetailStore orderDetailStore : orderDetailStores) {
			if (orderDetailStore.getProduct().getId() == product_id) {
				return true;
			}
		}
		return false;
	}
	public void deleteOrderDetail(OrdersStore order, OrderDetailStore orderDetail) {
		double currentTotalMoney = order.getTotalMoney();
		order.setTotalMoney(currentTotalMoney - orderDetail.getPrice() * orderDetail.getAmount());
		this.orderDetailStoreRepository.delete(orderDetail);
		this.orderStoreRepository.save(order);
	}
	public void validateFormDeliveryInfo(BindingResult bindingResult, OrderStoreInfo orderInfo) {
		String address = orderInfo.getDeliveryAddress();
		if(address.trim().length() < 10 || address.trim().length() > 225) {
			bindingResult.rejectValue("deliveryAddress", "length.orderInfo.address", "Địa chỉ nhận hàng từ 10-225 ký tự");
		}
		String note = orderInfo.getNote();
		if(note.trim().length() > 225) {
			bindingResult.rejectValue("note", "length.orderInfo.note", "Ghi chú tối đa 225 ký tự");
		}
		String name = orderInfo.getNameCustomer();
		if(name.trim().length() < 10 || name.trim().length() > 225) {
			bindingResult.rejectValue("nameCustomer", "length.orderInfo.nameCustomer", "Họ và tên nhận hàng từ 10-225 ký tự");
		}
	}
	public void addToOrder(OrdersStore order, Products product, int quantity) {
		List<OrderDetailStore> orderDetails = this.orderDetailStoreRepository.findByOrdersStoreEquals(order);
		System.out.println("size detail " + order.getOrderDetailStores().size());
		System.out.println("order id " + order.getId());
		OrderDetailStore orderDetail = this.checkOrderDetailStore(orderDetails, product.getId());
		if (orderDetail == null) {
			orderDetail = new OrderDetailStore();
			orderDetail.setPrice(product.getPrice());
			orderDetail.setOrdersStore(order);
			orderDetail.setAmount(quantity);
			orderDetail.setProduct(product);
		} else {
			orderDetails.remove(orderDetail);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setAmount(quantity);
		}
		orderDetails.add(orderDetail);
		order.setOrderDetailStores(orderDetails);
		order.setTotalMoney(this.calculateTotalMoney(orderDetails));
		this.orderDetailStoreRepository.save(orderDetail);
		this.orderStoreRepository.save(order);
		
	}

	private double calculateTotalMoney(List<OrderDetailStore> orderDetails) {
		double totalMoney = 0;
		for (OrderDetailStore o : orderDetails) {
			totalMoney += o.getPrice() * o.getAmount();
		}
		return totalMoney;
	}

	private OrderDetailStore checkOrderDetailStore(List<OrderDetailStore> orderDetails, int productId) {
		for (OrderDetailStore o : orderDetails) {
			if (o.getProduct().getId() == productId) {
				return o;
			}
		}
		return null;
	}

	public void updateInfoOrder(OrderStoreInfo t, OrdersStore order) {
		order.setDeliveryAddress(t.getDeliveryAddress());
		order.setNote(t.getNote());
		order.setPhoneNumber(t.getPhoneNumber());
		order.setNameCustomer(t.getNameCustomer());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setMtime(timestamp);
		this.orderStoreRepository.save(order);
	}
	
	public void storeOrder(OrderStoreInfo t) {
		OrdersStore order = (OrdersStore) this.httpSession.getAttribute("ordersStore");
		order.setDeliveryAddress(t.getDeliveryAddress());
		order.setNote(t.getNote());
		order.setPhoneNumber(t.getPhoneNumber());
		order.setNameCustomer(t.getNameCustomer());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String order_id = timestamp.getTime() + "";
		order.setOrderId(order_id);
		order.setOrderStatus(1);
		this.orderStoreRepository.save(order);
		this.orderStoreRepository.flush();
		for (int i = 0; i < order.getOrderDetailStores().size(); i++) {
			OrderDetailStore orderDetail = order.getOrderDetailStores().get(i);
			orderDetail.setOrdersStore(order);
			this.orderDetailStoreRepository.save(orderDetail);
		}
		this.httpSession.removeAttribute("ordersStore");
	}

	public void updateStatus(OrdersStore order, int status) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		order.setMtime(timestamp);
		order.setOrderStatus(status);
		this.orderStoreRepository.save(order);
	}

//	public void indexOrdersUser(Model model, int size, int page) {
//		Users user = (Users) httpSession.getAttribute("user");
//		Pageable pageable = PageRequest.of(page, size);
//		Page<OrdersStore> items = this.orderStoreRepository.findByUserIdOrderByCtimeDesc(user.getId(), pageable);
//		model.addAttribute("items", items);
//		if (items.getContent().size() > 0) {
//			model.addAttribute("viewContent", "orders/table.jsp");
//		} else {
//			model.addAttribute("viewContent", "orders/nodata.jsp");
//		}
//	}

}

