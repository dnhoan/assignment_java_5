<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />
<div class="btn-group" role="group" aria-label="Basic outlined example">
	<a class="btn btn-outline-secondary" data-toggle="tooltip" data-placement="top" title="In mã QR"
		href="/assignment_java_5/admin/products/printBatchTem"><i
		class="bi bi-printer-fill"></i></a>
	 <a class="btn btn-outline-secondary" data-toggle="tooltip" data-placement="top" title="Xuất file Excel"
		href="/assignment_java_5/admin/products/exportBatchExcel"><i
		class="bi bi-file-earmark-excel-fill"></i></a>
</div>

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
			<th colspan="3">Thao tác</th>
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
				<td style="width: 30px;">
					<button type="button" class="btn btn-danger" data-toggle="modal"
						data-target="#delete${product.id }">
						<i class="bi bi-trash3-fill"></i>
					</button>
					<div class="modal fade" id="delete${product.id }" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-body text-center font-18">
									<h4 class="padding-top-30 mb-30 weight-500">Bạn có muốn
										xóa sản phẩm này không?</h4>
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
												href="/assignment_java_5/admin/products/delete/${product.id }"><i
												class="fa fa-check"></i></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</td>
				<td style="width: 30px;"><a class="btn btn-primary"
					href="/assignment_java_5/admin/products/edit/${product.id }?page=${items.number}&size=${items.size}"><i
						class="bi bi-pencil-square"></i></a></td>
				<td style="width: 30px;"><a class="btn btn-secondary"
					href="/assignment_java_5/admin/products/printTem/${product.id }"><i
						class="bi bi-printer-fill"></i></a></td>
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