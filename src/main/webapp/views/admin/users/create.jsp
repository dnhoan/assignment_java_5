<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<form:form modelAttribute="user"
	action="/assignment_java_5/admin/users/store" method="post">

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
		<label class="form-label" for="password">Mật khẩu </label>
		<form:input type="password" class="form-control" id="password"
			path="password" />
		<span class="text-danger"> <form:errors path="password"
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
		<button class="btn btn-primary me-2">
			<i class="bi bi-plus-square"></i>
		</button>
		<a class="btn btn-secondary"
			href="/assignment_java_5/admin/users/index"><i
			class="bi bi-arrow-clockwise"></i></a>
	</div>
</form:form>