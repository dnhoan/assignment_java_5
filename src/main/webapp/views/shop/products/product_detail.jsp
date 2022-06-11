<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />
<div class="product-detail-wrap mb-30">
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12">
			<div class="product-detail-desc pd-20 card-box height-100-p d-flex">
				<div class="col-lg-6 col-md-12 col-sm-12 m-auto ">
					<img style="max-width: 85%;"
						src="/assignment_java_5/files/${product.image }"
						class="img-fluid rounded-start" alt="...">
				</div>
				<div
					class="col-lg-6 col-md-12 col-sm-12 d-flex justify-content-center">
					<form
						action="/assignment_java_5/shop/cart/add?product_id=${product.id }"
						method="post">
						<h4 class="mb-20 pt-20">${product.name }</h4>

						<p>Màu sắc: ${product.color }</p>
						<p>Kích thước: ${product.size }</p>
						<p>Danh mục: ${product.category.name }</p>
						<p>Số lượng: ${product.stock}</p>
						<p>Mô tả: ${product.description }</p>
						<div class="price">
							<p class="card-text text-danger text-uppercase fs-3 fw-bolder">
								<fmt:setLocale value="vi-VN" />
								<fmt:formatNumber value="${product.price  }" type="currency"
									minFractionDigits="0" />
							</p>
						</div>
						<div class="mx-w-150">
							<div class="form-group">
								<label class="text-blue">Số lượng</label> <input id="quantity"
									class="form-control" type="number" name="quantity" min="1" value="1"
									max="${product.stock }" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 col-6">
								<button class="btn btn-primary btn-block">Thêm vào giỏ
									hàng</button>
							</div>
							<div class="col-md-6 col-6">
								<a
									href="/assignment_java_5/shop/cart/add?product_id=${product.id }&quantity=1"
									class="btn btn-outline-primary btn-block">Mua ngay</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
