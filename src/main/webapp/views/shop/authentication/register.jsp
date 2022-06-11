<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="col-6 m-auto mt-3">
	<h2 class="text-center">Đăng ký</h2>
	<form action="/Ph17963_DaoNgocHoan_ASM_Java4/register" method="post">
		<div class="mt-3">
			<label class="form-label" for="fullname">Họ và tên </label> <input
				type="text" class="form-control" id="fullname" name="fullname"
				required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="email">Email: </label> <input
				type="email" class="form-control" id="email" name="email" required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="password">Mật khẩu </label> <input
				type="password" class="form-control" id="password" name="password"
				required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="confirmPassword">Xác nhận mật khẩu </label> <input
				type="password" class="form-control" id="confirmPassword"
				name="confirmPassword" required>
		</div>
		<div class="mt-3">
			<label for="gender" class="form-label">Giới tính</label> <br> <input
				type="radio" id="male" value="1" name="gioiTinh" checked> <label
				for="male">Nam</label> <input type="radio" id="female" value="0"
				name="gioiTinh"> <label for="female">Nữ</label>
		</div>
		<div class="mt-3">
			<label class="form-label" for="phoneNumber">Số điện thoại </label> <input
				type="text" class="form-control" id="phoneNumber" name="phoneNumber"
				required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="address">Địa chỉ </label> <input
				type="text" class="form-control" id="address" name="address"
				required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="image">Ảnh </label> <input type="file"
				class="form-control" id="image" name="image">
		</div>
		<div class="mt-3">
			<button class="btn btn-primary">Đăng ký</button>
		</div>
	</form>
</div>