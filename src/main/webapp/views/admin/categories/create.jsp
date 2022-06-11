<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form action="/assignment_java_5/admin/categories/store"
	modelAttribute="category" method="post">
	<div class="form-group row">
		<label class="col-sm-12 col-md-2 col-form-label">Tên danh mục</label>
		<div class="col-sm-12 col-md-10">
			<form:input path="name" class="form-control" />
			<span class="text-danger"> <form:errors path="name"
					element="li" delimiter="<li>" />
			</span>
		</div>
	</div>
	<div class="mt-3 d-flex justify-content-end">
		<button class="btn btn-primary me-2">
			<i class="bi bi-plus-square"></i>
		</button>
		<a class="btn btn-secondary"
			href="/assignment_java_5/admin/categories/index"><i
			class="bi bi-arrow-clockwise"></i></a>
	</div>
</form:form>