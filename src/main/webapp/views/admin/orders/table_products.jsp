<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />

<table class="table mt-3 table-striped table-hover">
	<thead>
		<tr>
			<th>Ảnh</th>
			<th>Tên sản phẩm</th>
			<th>Tồn kho</th>
			<th>Giá</th>
			<th>Màu sắc</th>
			<th>Kích cỡ</th>
			<th>Danh mục</th>
			<th>Thêm giỏ hàng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="product" items="${items.content }" varStatus="loop">
			<tr>
				<td><img alt="" width="50"
					src="/assignment_java_5/files/${product.image }"></td>
				<td>${product.name }</td>
				<td>
					<p class="fs-6 fw-bolder">${product.stock }</p>
				</td>
				<td>
					<p class="card-text text-danger  fs-5 fw-bolder">
						<fmt:setLocale value="vi-VN" />
						<fmt:formatNumber value="${product.price }" type="currency"
							minFractionDigits="0" />
					</p>
				</td>
				<td>${product.color}</td>
				<td>${product.size }</td>
				<td>${product.category.name }</td>
				<td style="width: 50px;">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#add${product.id }">
						<i class="bi bi-bag-plus-fill"></i>
					</button>
					<div class="modal fade" id="add${product.id }" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-xl modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-body text-center font-18 p-0">
									<div class="product-detail-wrap mb-30 m-0">
										<div class="row">
											<div class="col-lg-12 col-md-12 col-sm-12 p-0">
												<div
													class="product-detail-desc pd-20 card-box height-100-p d-flex">
													<div class="col-lg-6 col-md-12 col-sm-12 m-auto ">
														<img style="max-width: 85%;"
															src="/assignment_java_5/files/${product.image }"
															class="img-fluid rounded-start" alt="...">
													</div>
													<div
														class="col-lg-6 col-md-12 col-sm-12 text-start">
														<form
															action="/assignment_java_5/admin/orders/update/${orderEdit.id }/${product.id }"
															method="post">
															<h4 class="mb-20 pt-20">${product.name }</h4>

															<p>Màu sắc: ${product.color }</p>
															<p>Kích thước: ${product.size }</p>
															<p>Danh mục: ${product.category.name }</p>
															<p>Số lượng: ${product.stock}</p>
															<p>Mô tả: ${product.description }</p>
															<div class="price">
																<p
																	class="card-text text-danger text-uppercase fs-3 fw-bolder">
																	<fmt:setLocale value="vi-VN" />
																	<fmt:formatNumber value="${product.price  }"
																		type="currency" minFractionDigits="0" />
																</p>
															</div>
															<div class="mx-w-150">
																<div class="form-group">
																	<label class="text-blue">Số lượng</label> <input
																		id="quantity" class="form-control" type="number"
																		name="quantity" min="1" value="1"
																		max="${product.stock }" />
																</div>
															</div>
															<div class="row">
																<div class="col-12">
																	<button class="btn btn-primary btn-block">Thêm
																		vào đơn hàng</button>
																</div>
															</div>
														</form>
													</div>
												</div>
											</div>
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
<nav aria-label="Page navigation example"
	class="d-flex justify-content-center">
	<ul class="pagination">
		<c:if test="${!items.first}">
			<li class="page-item"><a class="page-link"
				href="/assignment_java_5/admin/products/index?page=${ items.number - 1}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach var="index" begin="0" end="${items.totalPages - 1 }">
			<li class="page-item ${items.number == index ? "
				active" : "" }" aria-current="page"><a class="page-link"
				href="/assignment_java_5/admin/products/index?page=${ index }">${index + 1 }</a>
			</li>
		</c:forEach>
		<c:if test="${!items.last}">
			<li class="page-item"><a class="page-link"
				href="/assignment_java_5/admin/products/index?page=${ items.number + 1}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>
</nav>