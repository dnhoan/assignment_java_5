<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />

<div class="">
	<!-- <c:if test="${ !empty categories }">
		<div class="row">
			<form action="/assignment_java_5/shop/products" method="get"
				enctype="multipart/form-data">
				<div class="mt-3 d-flex row">
					<div class="col-4">
						<select class="form-select" name="category_id" id="category">
							<option value="" disabled selected>Chọn danh mục</option>
							<c:forEach var="category" items="${categories }">
								<option value="${category.id }">${category.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<button class="btn btn-primary">Lọc</button>
					</div>
				</div>
			</form>
		</div>
	</c:if>-->
	<div class="product-list">
		<ul class="row">
			<c:forEach var="product" items="${items.content }">
				<li class="col-lg-4 col-md-6 col-sm-12">
					<div class="product-box">
						<div class="producct-img">
							<img src="/assignment_java_5/files/${product.image }" alt="">
						</div>
						<div class="product-caption">
							<h4>
								<a href="/assignment_java_5/shop/products/${product.id }">${product.name }</a>
							</h4>
							<div class="price">
								<p class="card-text text-danger text-uppercase fs-3 fw-bolder">
									<fmt:setLocale value="vi-VN" />
									<fmt:formatNumber value="${product.price  }" type="currency"
										minFractionDigits="0" />
								</p>
							</div>
							<a href="/assignment_java_5/shop/cart/add?product_id=${product.id }&quantity=1" class="btn btn-outline-primary">Mua ngay</a>
						</div>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<nav aria-label="Page navigation example"
		class="d-flex justify-content-center">
		<ul class="pagination">
			<c:if test="${!items.first}">
				<li class="page-item"><a class="page-link"
					href="/assignment_java_5/shop/products?page=${ items.number - 1}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>
			<c:forEach var="index" begin="0" end="${items.totalPages - 1 }">
				<li class="page-item ${items.number == index ? "
					active" : "" }" aria-current="page"><a class="page-link"
					href="/assignment_java_5/shop/products?page=${ index }">${index + 1 }</a>
				</li>
			</c:forEach>
			<c:if test="${!items.last}">
				<li class="page-item"><a class="page-link"
					href="/assignment_java_5/shop/products?page=${ items.number + 1}"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
	</nav>
</div>