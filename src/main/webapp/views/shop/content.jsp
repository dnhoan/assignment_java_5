<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${ !empty error }">

	<div class="mt-2 alert alert-danger">${error }</div>
</c:if>
<c:if test="${ !empty message }">

	<div class="mt-2  alert alert-success">${message }</div>

</c:if>
<c:if test="${product != null}">
	<jsp:include page="products/product_detail.jsp"></jsp:include>
	<h3 class="text-center m-5">Sản phẩm tương tự</h3>
</c:if>
<jsp:include page="${ viewContent }"></jsp:include>
<c:if test="${ showFormOrder  }">
	<jsp:include page="${ formCart }"></jsp:include>
</c:if>