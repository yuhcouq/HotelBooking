<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="data-table-area mg-b-15">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="sparkline13-list">
					<div class="sparkline13-graph">
						<div class="row">
							<h3 style="margin-left: 10px; text-align: center;">Quản lý
								Đơn đặt phòng</h3>
						</div>
						<div class="datatable-dashv1-list custom-datatable-overright">
							<c:if test="${userAdmin.role_id != 1}">
								<div class="row">
									<a
										href="${pageContext.request.contextPath }/admin/booking/booking-add"
										class="btn btn-primary a-btn-slide-text"
										style="background-color: #1CC3B2; margin-left: 10px; padding: 12px 35px;">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										<span><strong>Tạo mới</strong></span>
									</a>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class='alert alert-danger' role='alert'>${msg}</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class='alert alert-success' role='alert'>${success}</div>
							</c:if>
							<table id="table" data-toggle="table" data-pagination="true"
								data-search="true" data-show-columns="true"
								data-show-pagination-switch="true" data-show-refresh="true"
								data-key-events="true" data-show-toggle="true"
								data-resizable="true" data-cookie="true"
								data-cookie-id-table="saveId" data-show-export="true"
								data-click-to-select="true" data-toolbar="#toolbar">
								<thead class="text-align">
									<tr>
										<th class="table-id">STT</th>
										<th class="table-id">Mã đơn</th>
										<th class="table-function">Tên khách hàng</th>
										<th class="table-function">Giới tính</th>
										<th class="table-function">Số điện thoại</th>
										<th class="table-function">Email</th>
										<th class="table-function">Ngày sinh</th>
										<th class="table-function">Địa chỉ</th>
										<th class="table-function">Đơn đặt phòng</th>
										<c:if test="${userAdmin.role_id != 1}">
											<th style="width: 170px;">Chức năng</th>
										</c:if>
									</tr>
								</thead>
								<tbody class="text-align">
									<c:set var="STT" value="${0}"></c:set>
									<c:forEach items="${listBooking}" var="booking">
										<c:set var="editUrl"
											value="${pageContext.request.contextPath }/admin/booking/customer-edit/${booking.code}"></c:set>
										<c:set var="delUrl"
											value="${pageContext.request.contextPath }/admin/booking/del/${booking.code}"></c:set>
										<c:set var="views_detail"
											value="${pageContext.request.contextPath }/admin/booking/views/${booking.code}"></c:set>
										<c:set var="STT" value="${STT + 1}"></c:set>
										<tr>
											<td><p>${STT}</p></td>
											<td><p>${booking.code}</p></td>
											<td><p>${booking.lastname}<span>
														${booking.firstname}</span>
												</p></td>
											<td><c:choose>
													<c:when test="${booking.gender == 1}">
														<p>Nam</p>
													</c:when>
													<c:otherwise>
														<p>Nữ</p>
													</c:otherwise>
												</c:choose></td>
											<td><p>${booking.phone}</p></td>
											<td><p>${booking.email}</p></td>
											<td><p>${booking.birthday}</p></td>
											<td><p>${booking.address}<span> -
														${booking.city}</span>
												</p></td>
											<td><p>
													<a href="${views_detail}"
														class="btn btn-primary a-btn-slide-text"
														style="background-color: #F5F5F5;"> <span
														class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
														<span><strong>Views</strong></span>
													</a>
												</p></td>
											<c:if test="${userAdmin.role_id != 1}">
												<td><p>
														<a href="${editUrl}"
															class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-edit" aria-hidden="true"></span>
															<span><strong>Chỉnh sửa</strong></span>
														</a> <a
															onclick="return confirm('Bạn có chắc chắn muốn xóa item này?')"
															href="${delUrl}" class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-remove" aria-hidden="true"></span>
															<span><strong>Xóa</strong></span>
														</a>
													</p></td>
													
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>