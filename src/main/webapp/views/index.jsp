<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8" />
<title>Admin</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
<script
	src="https://rawgit.com/schmich/instascan-builds/master/instascan.min.js"></script>
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
	href="/assignment_java_5/views/src/plugins/datatables/css/dataTables.bootstrap4.min.css" />
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/src/plugins/datatables/css/responsive.bootstrap4.min.css" />
<link rel="stylesheet" type="text/css"
	href="/assignment_java_5/views/vendors/styles/style.css" />

<!-- Global site tag (gtag.js) - Google Analytics -->
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
<body>
	<div class="main-container">
		<jsp:include page="admin/menu.jsp"></jsp:include>
		<jsp:include page="admin/content.jsp"></jsp:include>
	</div>
	<!-- js -->
	<script src="/assignment_java_5/views/vendors/scripts/core.js"></script>
	<script src="/assignment_java_5/views/vendors/scripts/script.min.js"></script>
	<script src="/assignment_java_5/views/vendors/scripts/process.js"></script>
	<script
		src="/assignment_java_5/views/vendors/scripts/layout-settings.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/apexcharts/apexcharts.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/jquery.dataTables.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/dataTables.responsive.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/responsive.bootstrap4.min.js"></script>
	<script src="/assignment_java_5/views/vendors/scripts/dashboard.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/apexcharts/apexcharts.min.js"></script>
	<!-- buttons for Export datatable -->
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/dataTables.buttons.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/buttons.bootstrap4.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/buttons.print.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/buttons.html5.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/buttons.flash.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/pdfmake.min.js"></script>
	<script
		src="/assignment_java_5/views/src/plugins/datatables/js/vfs_fonts.js"></script>
	<!-- Datatable Setting js -->
	<script
		src="/assignment_java_5/views/vendors/scripts/datatable-setting.js"></script>
	<script>
			const myModalEl = document.getElementById("exampleModal");
			myModalEl.addEventListener("show.bs.modal", (event) => {
				let scanner = new Instascan.Scanner({
					video: document.getElementById("preview"),
				});
				Instascan.Camera.getCameras()
					.then((cameras) => {
						console.log(cameras);
						if (cameras.length > 0) {
							scanner.start(cameras[0]);
						} else {
							alert("no carmeras found");
						}
					})
					.catch((err) => {
						console.error(err);
					});
				scanner.addListener("scan", (c) => {
					window.location.replace(
						'http://localhost:8080/assignment_java_5/admin/orders/update/49/'+ c + '?quantity=1'
					);
				});
			});
		</script>
</body>
</html>
