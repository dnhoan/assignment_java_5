<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form method="post" action="/assignment_java_5/shop/orders/store"
	modelAttribute="orderDeliveryInfo">
	<div class="row">
		<div class="col-7">
			<div class="mt-3">
				<label class="form-label" for="phoneNumber">Số điện thoại</label>
				<form:input type="text" class="form-control" id="phoneNumber"
					path="phoneNumber" />
				<span class="text-danger"> <form:errors path="phoneNumber"
						element="li" delimiter="<li>" />
				</span>
			</div>
			<div class="mt-3">
				<label class="form-label" for="deliveryAddress">Địa chỉ giao hàng</label>
				<form:input type="text" class="form-control" id="deliveryAddress"
					path="deliveryAddress" />
				<span class="text-danger"> <form:errors path="deliveryAddress"
						element="li" delimiter="<li>" />
				</span>
			</div>
			<div class="mt-3">
				<label class="form-label" for="note">Ghi chú</label>
				<form:input type="text" class="form-control" id="note" path="note" />
				<span class="text-danger"> <form:errors path="note"
						element="li" delimiter="<li>" />
				</span>
			</div>
		</div>
		<div class="col-5">
			<h3 class="card-text text-danger fs-3 fw-bolder">
				Tổng tiền:
				<fmt:setLocale value="vi-VN" />
				<fmt:formatNumber value="${order.totalMoney}" type="currency"
					minFractionDigits="0" />
			</h3>
		</div>
	</div>
	<button class="btn btn-primary mt-3">Đặt hàng</button>
</form:form>