<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-white">
		<div class="container-fluid d-flex">
			<a class="navbar-brand" href="/assignment_java_5/shop/products">
				<img
				src="https://www.freepnglogos.com/uploads/adidas-logo-photo-png-3.png"
				alt="" width="100" />
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/assignment_java_5/shop/products">Nam</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/assignment_java_5/shop/products">Nữ</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/assignment_java_5/shop/cart/index">
							Giỏ hàng
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Tài khoản </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<c:if test="${sessionScope.user == null }">
								<li><a class="dropdown-item"
									href="/assignment_java_5/login">Đăng nhập</a></li>
								<li><a class="dropdown-item"
									href="/assignment_java_5/register">Đăng ký</a></li>
							</c:if>
							<c:if test="${sessionScope.user != null }">
								<li><a class="dropdown-item"
									href="/assignment_java_5/shop/orders/index">Đơn hàng</a></li>
								<li><a class="dropdown-item"
									href="/assignment_java_5/shop/personal">Cá nhân</a></li>
								<li><a class="dropdown-item"
									href="/assignment_java_5/changePassword">Đổi mật khẩu</a></li>
								<li><a class="dropdown-item"
									href="/assignment_java_5/logout">Đăng xuất</a></li>
							</c:if>
						</ul></li>
				</ul>
				<form class="d-flex" method="get"
					action="/assignment_java_5/shop/products">
					<input class="form-control me-2" type="search" name="name"
						placeholder="Tìm kiếm sản phẩm" aria-label="Search" required>
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
</header>
