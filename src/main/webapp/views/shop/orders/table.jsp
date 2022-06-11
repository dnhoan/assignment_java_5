<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<table class="table mt-3 table-striped table-hover">
	<thead>
		<tr>
			<th>Mã đơn hàng</th>
			<th>Họ và tên</th>
			<th>SDT</th>
			<th>Địa chỉ nhận hàng</th>
			<th>Tổng tiền</th>
			<th>Trạng thái đơn</th>
			<th>TG tạo</th>
			<th>TG Sửa</th>
			<th>Hủy</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="order" items="${items.content }" varStatus="loop">
			<tr>
				<td>
					<button type="button" class="btn btn-light" data-bs-toggle="modal"
						data-bs-target="#show_${order.orderId }">${order.orderId }</button>
					<div class="modal fade bd-example-modal-xl"
						id="show_${order.orderId }" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-xl">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Chi tiết
										đơn hàng</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="close"></button>
								</div>
								<div class="modal-body">
									<div class="row">
										<table class="table">
											<thead>
												<tr>
													<th>Ảnh</th>
													<th>Tên sản phẩm</th>
													<th>Giá</th>
													<th>Số lượng</th>
													<th>Thành tiền</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="orderDetail" items="${order.orderDetails }">
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
														<td><p
																class="text-danger text-uppercase fs-5 fw-bolder">
																<fmt:setLocale value="vi-VN" />
																<fmt:formatNumber
																	value="${orderDetail.amount * orderDetail.product.price}"
																	type="currency" minFractionDigits="0" />
															</p></td>

													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div>
										<div class="row">
											<div class="col-7">
												<div class="mt-3">
													<label class="form-label" for="phoneNumber">Số điện
														thoại </label> <input type="text" class="form-control"
														id="phoneNumber" name="phoneNumber" disabled required
														value="${order.phoneNumber }">
												</div>
												<div class="mt-3">
													<label class="form-label" for="address">Địa chỉ
														giao hàng </label> <input type="text" class="form-control"
														id="address" name="address" disabled required
														value="${order.deliveryAddress }">
												</div>
												<div class="mt-3">
													<label class="form-label" for="note">Ghi chú</label> <input
														type="text" class="form-control" id="note" disabled
														value="${order.note }" name="note">
												</div>
											</div>
											<div class="col-5">
												<h3 class="card-text text-danger fs-3 fw-bolder">
													Tổng tiền:
													<fmt:setLocale value="vi-VN" />
													<fmt:formatNumber value="${order.totalMoney}"
														type="currency" minFractionDigits="0" />
												</h3>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										data-bs-dismiss="modal" aria-label="close">Đóng</button>
								</div>
							</div>
						</div>
					</div>
				</td>
				<td>${order.user.fullname }</td>
				<td>${order.user.phoneNumber }</td>
				<td>${order.deliveryAddress }</td>
				<td><p class="text-danger text-uppercase fs-5 fw-bolder">
						<fmt:setLocale value="vi-VN" />
						<fmt:formatNumber value="${order.totalMoney}" type="currency"
							minFractionDigits="0" />
					</p></td>
				<td>
					<h5>
						<c:choose>
							<c:when test="${order.orderStatus == '1'}">
								<span class="badge text-bg-success">Đã xác nhận</span>
							</c:when>
							<c:when test="${order.orderStatus == '0'}">
								<span class="badge text-bg-warning">Chưa xác nhận</span>
							</c:when>
							<c:when test="${order.orderStatus == '2'}">
								<span class="badge text-bg-danger">Đã hủy</span>
							</c:when>
						</c:choose>
					</h5>
				</td>
				<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
						value="${order.ctime}" var="ctime" /> <c:out value="${ctime}" /></td>
				<td><fmt:formatDate pattern="dd-MM-yyyy HH:mm"
						value="${order.mtime}" var="mtime" /> <c:out value="${mtime}" /></td>
				<td style="width: 85px"><c:if
						test="${order.orderStatus == '0'}">
						<button type="button" class="btn btn-danger"
							data-bs-toggle="modal" data-bs-target="#cancel${order.id }">
							<i class="bi bi-x-circle"></i>
						</button>
						<div class="modal fade" id="cancel${order.id }" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Hủy</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="close"></button>
									</div>
									<div class="modal-body">
										<form method="post"
											action="/assignment_java_5/shop/orders/updateStatus/${order.id }?status=2">
											<div class="mb-3">
												<label for="cancel_note" class="form-label">Nhập lý
													do hủy</label>
												<textarea class="form-control" id="cancel_note"
													name="cancel_note" rows="3" required></textarea>
											</div>
											<div class="mt-3">
												<button type="submit" class="btn btn-primary">Hủy</button>
											</div>
										</form>
									</div>
									<div class="modal-footer"></div>
								</div>
							</div>
						</div>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:if test="${items.content != null }">
	<nav aria-label="Page navigation example"
		class="d-flex justify-content-center">
		<ul class="pagination">
			<c:if test="${!items.first}">
				<li class="page-item"><a class="page-link"
					href="/assignment_java_5/shop/orders/index?page=${ items.number - 1}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:forEach var="index" begin="0" end="${items.totalPages - 1 }">
				<li class="page-item ${items.number == index ? "
					active" : "" }" aria-current="page"><a class="page-link"
					href="/assignment_java_5/shop/orders/index?page=${ index }">${index + 1 }</a>
				</li>
			</c:forEach>
			<c:if test="${!items.last}">
				<li class="page-item"><a class="page-link"
					href="/assignment_java_5/shop/orders/index?page=${ items.number + 1}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
	</nav>
</c:if>