<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 <c:if test="${ !empty sessionScope.error }">
	<div class="mt-2 alert alert-danger">${sessionScope.error }</div>
	<c:remove var="error" scope="session" />
</c:if>
<c:if test="${ !empty sessionScope.message }">
	<div class="mt-2  alert alert-success">${sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>
<div class="m-auto">
<div class="d-flex justify-content-center">
	<img src="https://cdn-icons-png.flaticon.com/512/1656/1656507.png" width="500px" alt="...">
</div>
	<h3 class="text-center mt-2">Giỏ hàng trống vui lòng thêm sản phẩm vào giỏ hàng</h3>
<div class="d-flex justify-content-center">
	<a class="mt-2 m-auto btn btn-primary"  href="/assignment_java_5/shop/products" role="button">Thêm sản phẩm</a>
</div>
</div>
