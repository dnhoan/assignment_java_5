<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form
	action="/assignment_java_5/admin/categories/update/${category.id }"
	method="post" modelAttribute="category">
	<div class="mt-3 ">
		<label class="form-label" for="name">Tên danh mục</label>
		<form:input path="name" type="text" class="form-control" id="name" />
		<span class="text-danger"> <form:errors path="name"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3 d-flex justify-content-end">
		<button class="btn btn-primary me-2">Cập nhật</button>
		<a class="btn btn-secondary  me-2"
			href="/assignment_java_5/admin/categories/index"><i
			class="bi bi-arrow-clockwise"></i></a>
		<button type="button" class="btn btn-danger" data-toggle="modal"
			data-target="#delete${category.id }">
			<i class="bi bi-trash3-fill"></i>
		</button>
		<div class="modal fade" id="delete${category.id }" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-body text-center font-18">
						<h4 class="padding-top-30 mb-30 weight-500">Bạn có muốn xóa
							danh mục này không?</h4>
						<div class="padding-bottom-30 row"
							style="max-width: 170px; margin: 0 auto;">
							<div class="col-6">
								<button type="button" class="btn btn-secondary "
									data-dismiss="modal">
									<i class="fa fa-times"></i>
								</button>
							</div>
							<div class="col-6">
								<a class="btn btn-primary" data-dismiss="modal"
									href="/assignment_java_5/admin/categories/delete/${ca.id }"><i
									class="fa fa-check"></i></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>