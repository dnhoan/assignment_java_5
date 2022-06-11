
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Shop</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<!-- Site favicon -->
<link rel="apple-touch-icon" sizes="180x180"
	href="/assignment_java_5/views/vendors/images/apple-touch-icon.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="/assignment_java_5/views/vendors/images/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="/assignment_java_5/views/vendors/images/favicon-16x16.png">

<!-- Mobile Specific Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap"
	rel="stylesheet">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/assignment_java_5/views/vendors/styles/core.css">
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/vendors/styles/icon-font.min.css">
<!-- Slick Slider css -->
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/src/plugins/slick/slick.css">
<!-- bootstrap-touchspin css -->
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/src/plugins/bootstrap-touchspin/jquery.bootstrap-touchspin.css">
<link rel="stylesheet" type="text/css" href="/assignment_java_5/views/vendors/styles/style.css">

<!-- Global site tag (gtag.js) - Google Analytics -->
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-119386393-1"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());

	gtag('config', 'UA-119386393-1');
</script>
</head>
<body style="background-color: #fff">
	<div class="container" style="max-width: 1499px;">
		<jsp:include page="shop/menu.jsp"></jsp:include>
		<jsp:include page="shop/content.jsp"></jsp:include>
	</div>
</body>
<!-- js -->
<script src="/assignment_java_5/views/vendors/scripts/core.js"></script>
<script src="/assignment_java_5/views/vendors/scripts/script.min.js"></script>
<script src="/assignment_java_5/views/vendors/scripts/process.js"></script>
<script src="/assignment_java_5/views/vendors/scripts/layout-settings.js"></script>
<!-- Slick Slider js -->
<script src="/assignment_java_5/views/src/plugins/slick/slick.min.js"></script>
<!-- bootstrap-touchspin js -->
<script
	src="/assignment_java_5/views/src/plugins/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
<script>
	jQuery(document).ready(function() {
		jQuery('.product-slider').slick({
			slidesToShow : 1,
			slidesToScroll : 1,
			arrows : true,
			infinite : true,
			speed : 1000,
			fade : true,
			asNavFor : '.product-slider-nav'
		});
		jQuery('.product-slider-nav').slick({
			slidesToShow : 3,
			slidesToScroll : 1,
			asNavFor : '.product-slider',
			dots : false,
			infinite : true,
			arrows : false,
			speed : 1000,
			centerMode : true,
			focusOnSelect : true
		});
		$("input[name='demo3_22']").TouchSpin({
			initval : 1
		});
	});
</script>
</html>
