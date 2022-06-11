<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
    <fmt:setLocale value="${sessionScope.language }" scope="request" />
<fmt:setBundle basename="global" scope="request" />
    
<div class="col-6 m-auto mt-5">
	<h2 class="text-center"><fmt:message key="menu.login" /></h2>
	<form action="/Ph17963_DaoNgocHoan_ASM_Java4/login" method="post">
		<div class="mt-3">
			<label class="form-label" for="email">Email </label> <input
				type="email" class="form-control" id="email" name="email" required>
		</div>
		<div class="mt-3">
			<label class="form-label" for="password"><fmt:message key="menu.password" /></label> <input
				type="password" class="form-control" id="password" name="password" required>
		</div>
		<div class="mt-3">
			<button class="btn btn-primary"><fmt:message key="menu.login" /></button>
		</div>
	</form>
</div>