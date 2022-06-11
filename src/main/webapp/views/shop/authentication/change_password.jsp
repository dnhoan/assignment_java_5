
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
<div class="col-6 m-auto mt-5">
	<h2 class="text-center">Đổi mật khẩu</h2>
	<form action="/Ph17963_DaoNgocHoan_ASM_Java4/changePassword"
		method="post">
		<div class="mt-3">
			<label class="form-label" for="password">Mật khẩu hiện tại</label> <input
				type="password" class="form-control" id="password" name="password"
				required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="new_password">Mật khẩu mới</label> <input
				type="password" class="form-control" id="new_password"
				name="new_password" required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="confirm_new_password">Xác nhận
				mật khẩu mới</label> <input type="password" class="form-control"
				id="confirm_new_password" name="confirm_new_password" required>
		</div>
		<div class="mt-3">
			<button class="btn btn-primary">Đổi mật khẩu</button>
		</div>
	</form>
</div>