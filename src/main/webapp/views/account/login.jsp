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
					<li><a href="/assignment_java_5/register">Đăng ký</a></li>
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
					<div class="login-box bg-white box-shadow border-radius-10">
						<div class="login-title">
							<h2 class="text-center text-primary">Đăng nhập</h2>
						</div>
						<c:if test="${ !empty error }">
							<div class="alert alert-danger" role="alert">${error }</div>
						</c:if>
						<c:if test="${ !empty message }">
							<div class="alert alert-success" role="alert">${message }</div>
						</c:if>
						<form:form method="post" action="/assignment_java_5/login"
							modelAttribute="userLogin">
							<div class="mb-4">
								<div>
									<form:input type="text" class="form-control form-control"
										placeholder="Email" path="email" />
								</div>
								<span class="text-danger"> <form:errors path="email"
										element="li" delimiter="<li>" />
								</span>
							</div>
							<div class="">
								<div>
									<form:input type="password" class="form-control form-control"
										placeholder="**********" path="password" />
								</div>
								<span class="text-danger"> <form:errors path="password"
										element="li" delimiter="<li>" />
								</span>
							</div>
							<div class="row pb-30">
								<div class="col-6 mt-2">
									<div class="forgot-password" style="text-align: left">
										<a href="">Quên mật khẩu</a>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group mb-0">
										<button class="btn btn-primary btn-lg btn-block">
											Đăng nhập</button>
									</div>
									<div class="font-16 weight-600 pt-10 pb-10 text-center"
										data-color="#707373">Hoặc</div>
									<div class="input-group mb-0">
										<a class="btn btn-outline-primary btn-lg btn-block"
											href="/assignment_java_5/register">Tạo tài khoản</a>
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
