<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div>
	<c:if test="${empty showForm }">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="card-box mb-30">
					<div class="pd-20">
						<c:if test="${ !empty error }">
							<div class="alert alert-danger" role="alert">${error }</div>
						</c:if>
						<c:if test="${ !empty message }">
							<div class="alert alert-success" role="alert">${message }</div>
						</c:if>
						<jsp:include page="${form }"></jsp:include></div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="pd-ltr-20 xs-pd-20-10">
		<div class="min-height-200px">
			<div class="card-box mb-30">
				<div class="pd-20"><jsp:include page="${table }"></jsp:include></div>
			</div>
		</div>
	</div>
</div>