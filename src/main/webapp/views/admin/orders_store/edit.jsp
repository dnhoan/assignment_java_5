<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />
<div class="row">
	<div class="col-lg-8">
		<h3 class="mb-4">Sản phẩm đơn hàng</h3>
		<table class="table">
			<thead>
				<tr>
					<th>Ảnh</th>
					<th>Tên sản phẩm</th>
					<th>Giá</th>
					<th>SL</th>
					<th>Tổng tiền</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderDetail" items="${orderEdit.orderDetailStores }">
					<tr>
						<td><img alt="" width="50"
							src="/assignment_java_5/files/${orderDetail.product.image }"></td>
						<td>${orderDetail.product.name }</td>
						<td>
							<p class="text-danger text-uppercase fs-5 fw-bolder">
								<fmt:setLocale value="vi-VN" />
								<fmt:formatNumber value="${orderDetail.product.price}"
									type="currency" minFractionDigits="0" />
							</p>
						</td>
						<td><p class="text-uppercase fs-5 fw-bolder">${orderDetail.amount}</p></td>
						<td><p class="text-danger text-uppercase fs-5 fw-bolder">
								<fmt:setLocale value="vi-VN" />
								<fmt:formatNumber
									value="${orderDetail.amount * orderDetail.product.price}"
									type="currency" minFractionDigits="0" />
							</p></td>
						<td>
							<button type="button" class="btn btn-danger" data-toggle="modal"
								data-target="#delete${orderDetail.product.id }">
								<i class="bi bi-trash3-fill"></i>
							</button>
							<div class="modal fade" id="delete${orderDetail.product.id }"
								tabindex="-1" role="dialog" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered" role="document">
									<div class="modal-content">
										<div class="modal-body text-center font-18">
											<h4 class="padding-top-30 mb-30 weight-500">Bạn có muốn
												xóa sản phẩm này khỏi đơn hàng không?</h4>
											<div class="padding-bottom-30 row"
												style="max-width: 170px; margin: 0 auto;">
												<div class="col-6">
													<button type="button" class="btn btn-secondary "
														data-dismiss="modal">
														<i class="fa fa-times"></i>
													</button>
												</div>
												<div class="col-6">
													<a class="btn btn-primary"
														href="/assignment_java_5/admin/ordersStore/delete/${orderEdit.id }/${orderDetail.id }"><i
														class="fa fa-check"></i></a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<h3 class="card-text text-danger fs-3 fw-bolder float-end">
			Tổng tiền:
			<fmt:setLocale value="vi-VN" />
			<fmt:formatNumber value="${orderEdit.totalMoney}" type="currency"
				minFractionDigits="0" />
		</h3>
	</div>
	<div class="col-lg-4">
		<h3 class="mb-3">Thông tin đơn hàng</h3>
		<form:form method="post"
			action="/assignment_java_5/admin/ordersStore/updateInfo/${orderEdit.id }"
			modelAttribute="orderStoreInfo">
			<div class="row">
				<div class="">
					<div class="mt-3">
						<label class="form-label" for="nameCustomer">Họ tên khách
							hàng</label>
						<form:input type="text" class="form-control" id="nameCustomer"
							path="nameCustomer" />
						<span class="text-danger"> <form:errors path="nameCustomer"
								element="li" delimiter="<li>" />
						</span>
					</div>
					<div class="mt-3">
						<label class="form-label" for="phoneNumber">SĐT nhận hàng</label>
						<form:input type="text" class="form-control" id="phoneNumber"
							path="phoneNumber" />
						<span class="text-danger"> <form:errors path="phoneNumber"
								element="li" delimiter="<li>" />
						</span>
					</div>
					<div class="mt-3">
						<label class="form-label" for="deliveryAddress">Địa chỉ
							giao hàng</label>
						<form:input type="text" class="form-control" id="deliveryAddress"
							path="deliveryAddress" />
						<span class="text-danger"> <form:errors
								path="deliveryAddress" element="li" delimiter="<li>" />
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

			</div>
			<div class="float-end">
				<button class="btn btn-primary mt-3">Lưu</button>
			</div>
		</form:form>
	</div>
</div>
