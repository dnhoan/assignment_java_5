<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form modelAttribute="user"
	action="/assignment_java_5/admin/users/update/${user.id }"
	method="post">
	<div class="mt-3">
		<label class="form-label" for="fullname">Họ và tên </label>
		<form:input type="text" class="form-control" id="fullname"
			path="fullname" />
		<span class="text-danger"> <form:errors path="fullname"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="email">Email: </label>
		<form:input type="email" class="form-control" id="email" path="email" />
		<span class="text-danger"> <form:errors path="email"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label for="gender" class="form-label">Giới tính</label> <br>
		<div class="form-group">
			<div class="d-flex">
				<div class="custom-control custom-radio mb-5 mr-20">
					<form:radiobutton class="custom-control-input" id="customRadio4"
						path="gender" value="1" />
					<label class="custom-control-label weight-400" for="customRadio4">Nam</label>
				</div>
				<div class="custom-control custom-radio mb-5">
					<form:radiobutton class="custom-control-input" id="customRadio5"
						path="gender" value="0" />
					<label class="custom-control-label weight-400" for="customRadio5">Nữ</label>
				</div>
			</div>
		</div>
	</div>
	<div class="mt-3">
		<label class="form-label" for="phoneNumber">Số điện thoại </label>
		<form:input type="text" class="form-control" id="phoneNumber"
			path="phoneNumber" />
		<span class="text-danger"> <form:errors path="phoneNumber"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label class="form-label" for="address">Địa chỉ </label>
		<form:input type="text" class="form-control" id="address"
			path="address" />
		<span class="text-danger"> <form:errors path="address"
				element="li" delimiter="<li>" />
		</span>
	</div>
	<div class="mt-3">
		<label for="role" class="form-label">Vai trò</label> <br>
		<div class="form-group">
			<div class="d-flex">
				<div class="custom-control custom-radio mb-5 mr-20">
					<form:radiobutton class="custom-control-input" path="role"
						value="1" id="customRadio6" />
					<label class="custom-control-label weight-400" for="customRadio6">Quản
						lý</label>
				</div>
				<div class="custom-control custom-radio mb-5">
					<form:radiobutton class="custom-control-input" path="role"
						value="0" id="customRadio7" />
					<label class="custom-control-label weight-400" for="customRadio7">Người
						dùng</label>
				</div>
			</div>
		</div>
	</div>
	<div class="mt-3 d-flex justify-content-end">
		<button class="btn btn-primary me-2">Cập nhật</button>
		<a class="btn btn-secondary  me-2" 
			href="/assignment_java_5/admin/users/index">Làm mới</a>
		<button type="button" class="btn btn-danger" data-bs-toggle="modal"
			data-bs-target="#delete${st.id }">
			<i class="bi bi-trash3-fill"></i>
		</button>
		<div class="modal fade" id="delete${st.id }" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Xác nhận xóa</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="close"></button>
					</div>
					<div class="modal-body">Bạn có muốn xóa tài khoản này không?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Không</button>
						<a class="btn btn-primary"
							href="/assignment_java_5/admin/users/delete/${user.id }">Xóa</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>