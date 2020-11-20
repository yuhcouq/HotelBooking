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
								phòng khách sạn</h3>
						</div>
						<div class="datatable-dashv1-list custom-datatable-overright">
							<c:if test="${userAdmin.hotel_id != -1}">
								<div class="row">
									<a href="${pageContext.request.contextPath }/admin/room/add"
										class="btn btn-primary a-btn-slide-text"
										style="background-color: #1CC3B2; margin-left: 10px; padding: 12px 35px;">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										<span><strong>Thêm mới</strong></span>
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
										<c:if test="${userAdmin.role_id == 1}">
											<th class="table-function">Khách sạn</th>
										</c:if>
										<th class="table-function">Tên Phòng</th>
										<th class="table-image">Hình ảnh</th>
										<th class="table-function">Số phòng</th>
										<th class="table-function">Người lớn</th>
										<th class="table-function">Trẻ em</th>
										<th class="table-function">Số giường</th>
										<th class="table-function">Giá tiền (VND)</th>
										<th class="table-function">Diện tích (m2)</th>
										<th class="table-function">Thanh toán trước</th>
										<th class="table-function">Hình ảnh chi tiết</th>
										<th style="width: 170px;">Chức năng</th>
									</tr>
								</thead>
								<tbody class="text-align">
									<c:set var="STT" value="${0}"></c:set>
									<c:if test="${not empty listRooms}">
									<c:forEach items="${listRooms}" var="room">
										<c:set var="editUrl"
											value="${pageContext.request.contextPath }/admin/room/edit/${room.id_room}"></c:set>
										<c:set var="delUrl"
											value="${pageContext.request.contextPath }/admin/room/del/${room.id_room}"></c:set>
										<c:set var="views_images"
											value="${pageContext.request.contextPath }/admin/room/views_image/${room.id_room}"></c:set>
										<c:set var="STT" value="${STT + 1}"></c:set>
										<tr>
											<td><p>${STT}</p></td>
											<c:if test="${userAdmin.role_id == 1}">
												<td><p>${room.hotel_name}</p></td>
											</c:if>
											<td><p>${room.name}</p></td>
											<td><p>
													<c:choose>
														<c:when test="${not empty room.image }">
															<img
																Style="border-radius: 5px; width: 120px; height: 80px;"
																src="${pageContext.request.contextPath }/uploads/${room.image}" />
														</c:when>
														<c:otherwise>
															<p>không có hình ảnh</p>
														</c:otherwise>
													</c:choose>
												</p></td>
											<td><p>${room.room_number}</p></td>
											<td><p>${room.adult_number}</p></td>
											<td><p>${room.children_number}</p></td>
											<td><p>${room.bed_number}</p></td>
											<td><p>${defines.formatNumber(room.price)}</p></td>
											<td><p>${defines.formatNumber(room.acreage)}</p></td>
											<td><p>${room.prepayment}%</p></td>
											<td><p>
													<a href="${views_images}"
														class="btn btn-primary a-btn-slide-text"
														style="background-color: #F5F5F5;"> <span
														class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
														<span><strong>Views</strong></span>
													</a>
												</p></td>
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
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>