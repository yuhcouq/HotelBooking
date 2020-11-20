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
							<h3
								style="margin-left: 10px; text-align: center;">Quản
								lý đánh giá</h3>
						</div>
						<div class="datatable-dashv1-list custom-datatable-overright">
							<c:if test="${not empty error}">
								<div class='alert alert-danger' role='alert'>${error}</div>
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
										<th class="table-id">ID</th>
										<th class="table-name">Tên người dùng</th>
										<th class="table-name">Tên khách sạn</th>
										<th class="table-raing">Số phòng</th>
										<th class="table-detail">Nội dung</th>
										<th class="table-rating">Xếp hạng</th>
										<th class="table-rating">Thời gian tạo</th>
										<th class="table-function">Chức năng</th>
									</tr>
								</thead>
								<tbody class="text-align">
									<c:forEach items="${listRoomReviews}" var="review">
										<c:set var="editUrl"
											value="${pageContext.request.contextPath }/admin/roomreview/edit/${review.id_review}"></c:set>
										<c:set var="delUrl"
											value="${pageContext.request.contextPath }/admin/roomreview/del/${review.id_review}"></c:set>
										<tr>
											<td><p>${review.id_review}</p></td>
											<td><p>${review.firstname}${review.lastname}</p></td>
											<td><p>${review.hotel_name}</p></td>
											<td><p>${review.room_number}</p></td>
											<td><p>${review.content}</p></td>
											<td><p>${review.rating}</p></td>
											<td><p>${review.create_time}</p></td>
											<td><p>
													<a href="${editUrl}"
														class="btn btn-primary a-btn-slide-text"
														style="background-color: #F5F5F5;"> <span
														class="glyphicon glyphicon-edit" aria-hidden="true"></span>
														<span><strong>Edit</strong></span>
													</a> <a
														onclick="return confirm('Bạn có chắc chắn muốn xóa item này?')"
														href="${delUrl}" class="btn btn-primary a-btn-slide-text"
														style="background-color: #F5F5F5;"> <span
														class="glyphicon glyphicon-remove" aria-hidden="true"></span>
														<span><strong>Delete</strong></span>
													</a>
												</p></td>
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