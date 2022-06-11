<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8" />
<title>DeskApp - Bootstrap Admin Dashboard HTML Template</title>

<!-- Site favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="/assignment_java_5/views/vendors/images/apple-touch-icon.png" />
<link rel="icon" type="image/png" sizes="32x32"
	href="/assignment_java_5/views/vendors/images/favicon-32x32.png" />
<link rel="icon" type="image/png" sizes="16x16"
	href="/assignment_java_5/views/vendors/images/favicon-16x16.png" />

<!-- Mobile Specific Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/vendors/styles/core.css" />
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/vendors/styles/icon-font.min.css" />
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/vendors/styles/style.css" />

<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-119386393-1"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag("js", new Date());

	gtag("config", "UA-119386393-1");
</script>
</head>
<body class="login-page">
	<div class="login-header box-shadow">
		<div
			class="container-fluid d-flex justify-content-between align-items-center">
			<div class="brand-logo">
				<a href="login.html"> <img
					src="/assignment_java_5/views/vendors/images/deskapp-logo.svg"
					alt="" />
				</a>
			</div>
			<div class="login-menu">
				<ul>
					<li><a href="/assignment_java_5/login">Đăng nhập</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div
		class="login-wrap d-flex align-items-center flex-wrap justify-content-center">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-md-6 col-lg-7">
					<img
						src="/assignment_java_5/views/vendors/images/login-page-img.png"
						alt="" />
				</div>
				<div class="col-md-6 col-lg-5">
					<div class="login-box bg-white box-shadow border-radius-10" style="max-width: 500px;">
						<div class="login-title">
							<h2 class="text-center text-primary">Đăng ký</h2>
						</div>
						<form:form method="post" action="/assignment_java_5/register"
							modelAttribute="userRegister">
							<div class="mt-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label" for="fullname">Họ
										và tên*</label>
									<div class="col-sm-9">
										<form:input type="text" class="form-control" id="fullname"
											path="fullname" />
										<span class="text-danger"> <form:errors path="fullname"
												element="li" delimiter="<li>" />
										</span>
									</div>
								</div>
							</div>
							<div class="mt-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label" for="email">Email*</label>
									<div class="col-sm-9">
										<form:input type="email" class="form-control" id="email"
											path="email" />
										<span class="text-danger"> <form:errors path="email"
												element="li" delimiter="<li>" />
										</span>
									</div>
								</div>
							</div>
							<div class="mt-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label" for="password">Mật
										khẩu* </label>
									<div class="col-sm-9">
										<form:input type="password" class="form-control" id="password"
											path="password" />
										<span class="text-danger"> <form:errors path="password"
												element="li" delimiter="<li>" />
										</span>
									</div>
								</div>
							</div>
							<div class="mt-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label" for="confirmPassword">Xác
										nhận mật khẩu* </label>
									<div class="col-sm-9">
										<form:input type="password" class="form-control"
											id="confirmPassword" path="confirmPassword" />
										<span class="text-danger"> <form:errors
												path="confirmPassword" element="li" delimiter="<li>" />
										</span>
									</div>
								</div>
							</div>
							<div class="mt-3">
								<div class="form-group row">
									<label for="gender" class="col-sm-3 col-form-label">Giới
										tính</label>
									<div class="col-sm-9">
										<div class="d-flex">
											<div class="custom-control custom-radio mb-5 mr-20">
												<form:radiobutton class="custom-control-input"
													id="customRadio4" path="gender" value="1" />
												<label class="custom-control-label weight-400"
													for="customRadio4">Nam</label>
											</div>
											<div class="custom-control custom-radio mb-5">
												<form:radiobutton class="custom-control-input"
													id="customRadio5" path="gender" value="0" />
												<label class="custom-control-label weight-400"
													for="customRadio5">Nữ</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="mt-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label" for="phoneNumber">Số
										điện thoại*</label>
									<div class="col-sm-9">
										<form:input type="text" class="form-control" id="phoneNumber"
											path="phoneNumber" />
										<span class="text-danger"> <form:errors
												path="phoneNumber" element="li" delimiter="<li>" />
										</span>
									</div>
								</div>
							</div>
							<div class="mt-3">
								<div class="form-group row">
									<label class="col-sm-3 col-form-label" for="address">Địa
										chỉ* </label>
									<div class="col-sm-9">
										<form:input type="text" class="form-control" id="address"
											path="address" />
										<span class="text-danger"> <form:errors path="address"
												element="li" delimiter="<li>" />
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group mb-0">
										<button class="btn btn-primary btn-lg btn-block">
											Đăng ký</button>
									</div>
									<div class="font-16 weight-600 pt-10 pb-10 text-center"
										data-color="#707373">Hoặc</div>
									<div class="input-group mb-0">
										<a class="btn btn-outline-primary btn-lg btn-block"
											href="/assignment_java_5/login">Đăng nhập</a>
									</div>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- js -->
	<script src="/assignment_java_5/views/vendors/scripts/core.js"></script>
	<script src="/assignment_java_5/views/vendors/scripts/script.min.js"></script>
	<script src="/assignment_java_5/views/vendors/scripts/process.js"></script>
	<script
		src="/assignment_java_5/views/vendors/scripts/layout-settings.js"></script>
</body>
</html>
