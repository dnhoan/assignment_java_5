<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<button class="btn btn-primary" data-bs-toggle="modal"
	data-bs-target="#import_excel">Nhập Excel</button>
<div class="modal fade" id="import_excel" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<form
			action="/assignment_java_5/admin/products/importExcel"
			method="post" enctype="multipart/form-data">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Thêm sản phẩm
						hàng loạt</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="close"></button>
				</div>
				<div class="modal-body">
					<div class="mt-3">
						<label class="form-label" for="category">Danh mục </label> <select
							class="form-select" name="category_id" id="category">
							<c:forEach var="category" items="${categories }">
								<option value="${category.id }">${category.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mt-3">
						<label class="form-label" for="file"> Chọn file excel ( <a
							href="/assignment_java_5/admin/products/downloadFile">Tải
								mẫu excel</a>)
						</label> <input type="file"
							class="form-control-file form-control height-auto"
							id="file_excel" name="file_excel" required />
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-primary"
						data-bs-dismiss="modal">Thêm</button>
				</div>
			</div>
		</form>
	</div>
</div>
<form:form modelAttribute="product"
	action="/assignment_java_5/admin/products/store" method="post"
	enctype="multipart/form-data">
	<div class="mt-3">
		<label class="form-label" for="name">Tên sản phẩm </label>
		<form:input type="text" class="form-control" id="name" path="name" />
		<span class="text-danger"> <form:errors path="name"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="stock">Tồn kho</label>
		<form:input type="number" path="stock" class="form-control" id="stock"
			min="0" max="99999" />
		<span class="text-danger"> <form:errors path="stock"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="price">Giá</label>
		<form:input type="number" path="price" class="form-control" id="price"
			min="0" />
		<span class="text-danger"> <form:errors path="price"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="color">Màu sắc</label>
		<form:input path="color" type="text" class="form-control" id="color" />
		<span class="text-danger"> <form:errors path="color"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="size">Kích cỡ</label>
		<form:input path="size" type="text" class="form-control" id="size" />
		<span class="text-danger"> <form:errors path="size"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mb-3">
		<label for="description" class="form-label">Mô tả</label>
		<form:textarea path="description" class="form-control"
			id="description" rows="3" />
		<span class="text-danger"> <form:errors path="description"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="image">Ảnh </label> <input type="file"
			class="form-control-file form-control height-auto" id="image"
			name="avatar" />

		<c:if test="${!empty errorAvt }">
			<li class="text-danger">${errorAvt }</li>
		</c:if>
	</div>
	<div class="mt-3">
		<label class="form-label" for="category">Danh mục </label>
		<form:select class="form-select" path="category">
			<form:options items="${categories }" itemLabel="name" />
		</form:select>
		<span class="text-danger"> <form:errors path="category"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3 d-flex justify-content-end">
		<button type="submit" class="btn btn-primary me-2">
			<i class="bi bi-plus-square"></i>
		</button>
		<a class="btn btn-secondary"
			href="/assignment_java_5/admin/products/index?size=${items.number }&page=${items.size}"><i
			class="bi bi-arrow-clockwise"></i> </a>
	</div>
</form:form>