<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<table class="table mt-3 table-striped table-hover">
	<thead>
		<tr>
			<th>Họ và tên</th>
			<th>Giới tính</th>
			<th>Số điện thoại</th>
			<th>Email</th>
			<th>Địa chỉ</th>
			<th>Vai trò</th>
			<th colspan="2">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="st" items="${items.content }" varStatus="loop">
			<tr>
				<td>${st.fullname }</td>
				<td>${st.gender == 1 ? "Nam" : "Nữ" }</td>
				<td>${st.phoneNumber }</td>
				<td>${st.email }</td>
				<td>${st.address }</td>
				<td>${st.role == 1 ? "Admin" : "Người dùng" }</td>
				<td style="width:50px;">
					<button type="button" class="btn btn-danger" data-toggle="modal"
						data-target="#delete${st.id }">
						<i class="bi bi-trash3-fill"></i>
					</button>
					<div class="modal fade" id="delete${st.id }" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered" role="document">
							<div class="modal-content">
								<div class="modal-body text-center font-18">
									<h4 class="padding-top-30 mb-30 weight-500">Bạn có muốn
										xóa người dùng này không?</h4>
									<div class="padding-bottom-30 row"
										style="max-width: 170px; margin: 0 auto;">
										<div class="col-6">
											<button type="button" class="btn btn-secondary "
												data-dismiss="modal">
												<i class="fa fa-times"></i>
											</button>
										</div>
										<div class="col-6">
											<a class="btn btn-primary"
												href="/assignment_java_5/admin/users/delete/${st.id }"><i
												class="fa fa-check"></i></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</td>
				<td style="width:50px;"><a class="btn btn-primary"
					href="/assignment_java_5/admin/users/edit/${st.id }?page=${items.number}&size=${items.size}"><i
						class="bi bi-pencil-square"></i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<nav aria-label="Page navigation example"
	class="d-flex justify-content-center">
	<ul class="pagination">
		<c:if test="${!items.first}">
			<li class="page-item"><a class="page-link"
				href="/assignment_java_5/admin/users/index?page=${ items.number - 1}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:forEach var="index" begin="0" end="${items.totalPages - 1 }">
			<li class="page-item ${items.number == index ? "
				active" : "" }" aria-current="page"><a class="page-link"
				href="/assignment_java_5/admin/users/index?page=${ index }">${index + 1 }</a>
			</li>
		</c:forEach>
		<c:if test="${!items.last}">
			<li class="page-item"><a class="page-link"
				href="/assignment_java_5/admin/users/index?page=${ items.number + 1}"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>
</nav>