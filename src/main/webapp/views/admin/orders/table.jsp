<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />
<table class="table mt-3 table-striped table-hover">
	<thead>
		<tr>
			<th>Mã đơn hàng</th>
			<th>Họ và tên</th>
			<th>Giới tính</th>
			<th>Số điện thoại</th>
			<th>Địa chỉ nhận hàng</th>
			<th>Tổng tiền</th>
			<th>Trạng thái đơn hàng</th>
			<th>Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="order" items="${items.content }" varStatus="loop">
			<tr>
				<td>
					<div class="dropdown">
						<button type="button" class="btn btn-light" data-toggle="dropdown">${order.orderId }</button>
						<div
							class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
							<a class="btn dropdown-item" data-bs-toggle="modal"
								data-bs-target="#show_${order.orderId }" href="#"> <i
								class="dw dw-eye"></i> Xem
							</a> <a class="dropdown-item"
								href="/assignment_java_5/admin/orders/edit/${order.id }"><i
								class="dw dw-edit2"></i> Edit</a>
						</div>
					</div>
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
									<div class="row ">
										<h3
											class="card-text text-danger fs-3 fw-bolder d-flex justify-content-end p-4">
											Tổng tiền:
											<fmt:setLocale value="vi-VN" />
											<fmt:formatNumber value="${order.totalMoney}" type="currency"
												minFractionDigits="0" />
										</h3>
									</div>
									<div class="row">
										<div class="col-6">
											<h4 class="mb-3">Thông tin khách hàng</h4>
											<p>
												<b>Họ tên: </b>${order.user.fullname }</p>
											<p>
												<b>Email: </b>${order.user.email }</p>
											<p>
												<b>Số điện thoại: </b>${order.user.phoneNumber}</p>
											<p>
												<b>Địa chỉ: </b>${order.user.address}</p>
										</div>
										<div class="col-6">
											<h4 class="mb-3">Thông tin đơn hàng</h4>
											<p>
												<b>Số điện thoại: </b>${order.phoneNumber }</p>
											<p>
												<b>Địa chỉ: </b>${order.deliveryAddress }</p>
											<p>
												<b>Ghi chú: </b>${order.note}</p>
											<p>
												<b>Tạo lúc: </b>
												<fmt:formatDate pattern="dd-MM-yyyy HH:mm"
													value="${order.ctime}" var="ctime" />
												<c:out value="${ctime}" />
											</p>
											<p>
												<b>Cập nhật lúc: </b>
												<fmt:formatDate pattern="dd-MM-yyyy HH:mm"
													value="${order.mtime}" var="mtime" />
												<c:out value="${mtime}" />
											</p>
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
				<td>${order.user.gender == 1 ? "Nam" : "Nữ" }</td>
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
								<span class="badge text-bg-primary">Đã xác nhận</span>
							</c:when>
							<c:when test="${order.orderStatus == '0'}">
								<span class="badge text-bg-secondary">Chưa xác nhận</span>
							</c:when>
							<c:when test="${order.orderStatus == '2'}">
								<span class="badge text-bg-danger">Đã hủy</span>
							</c:when>
							<c:when test="${order.orderStatus == '3'}">
								<span class="badge text-bg-warning">Đang giao</span>
							</c:when>
							<c:when test="${order.orderStatus == '4'}">
								<span class="badge text-bg-success">Giao thành công</span>
							</c:when>
						</c:choose>
					</h5>
				</td>
				<td style="width: 200px;"><c:if
						test="${ !(order.orderStatus ==  '2' || order.orderStatus ==  '4')}">
							<button type="button" class="btn btn-danger" data-toggle="tooltip" data-placement="top" title="Hủy đơn hàng"
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
											action="/assignment_java_5/admin/orders/updateStatus/${order.id }?status=2">
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
					</c:if> <c:if test="${order.orderStatus == '0'}">
							<button type="button" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="Xác nhận đơn hàng"
								data-bs-toggle="modal" data-bs-target="#confirm${order.id }">
								<i class="icon-copy ion-archive"></i>
							</button>
						<div class="modal fade" id="confirm${order.id }" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="close"></button>
									</div>
									<div class="modal-body">Bạn có muốn Xác nhận đơn hàng này
										không?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Không</button>
										<a class="btn btn-primary"
											href="/assignment_java_5/admin/orders/updateStatus/${order.id }?status=1">Xác
											nhận</a>
									</div>
								</div>
							</div>
						</div>
					</c:if> <c:if test="${order.orderStatus == '1'}">
							<button type="button" class="btn btn-warning" data-toggle="tooltip" data-placement="top" title="Đang giao"
								data-bs-toggle="modal" data-bs-target="#confirm${order.id }">
								<i class="icon-copy fa fa-car" aria-hidden="true"></i>
							</button>
						<div class="modal fade" id="confirm${order.id }" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="close"></button>
									</div>
									<div class="modal-body">Bạn có muốn chuyển trạng thái đơn
										hàng sang đang giao không?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Không</button>
										<a class="btn btn-primary"
											href="/assignment_java_5/admin/orders/updateStatus/${order.id }?status=3">Xác
											nhận</a>
									</div>
								</div>
							</div>
						</div>
					</c:if> <c:if
						test="${ !(order.orderStatus ==  '2' || order.orderStatus ==  '4')}">
							<button type="button" class="btn btn-success" data-toggle="tooltip" data-placement="top" title="Giao hàng thành công"
								data-bs-toggle="modal" data-bs-target="#success${order.id }">
								<i class="bi bi-check-circle"></i>
							</button>
						<div class="modal fade" id="success${order.id }" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="close"></button>
									</div>
									<div class="modal-body">Bạn có muốn chuyển trạng thái đơn
										hàng sang giao thành công không?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Không</button>
										<a class="btn btn-primary"
											href="/assignment_java_5/admin/orders/updateStatus/${order.id }?status=4">Xác
											nhận</a>
									</div>
								</div>
							</div>
						</div>
					</c:if></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<nav aria-label="Page navigation example"
	class="d-flex justify-content-center">
	<ul class="pagination">
		<c:if test="${!items.first}">
			<li class="page-item"><a class="page-link"
				href="/assignment_java_5/admin/orders/index?page=${ items.number - 1}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach var="index" begin="0" end="${items.totalPages - 1 }">
			<li class="page-item ${items.number == index ? "
				active" : "" }" aria-current="page"><a class="page-link"
				href="/assignment_java_5/admin/orders/index?page=${ index }">${index + 1 }</a>
			</li>
		</c:forEach>
		<c:if test="${!items.last}">
			<li class="page-item"><a class="page-link"
				href="/assignment_java_5/admin/orders/index?page=${ items.number + 1}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>
</nav>
