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
										<th class="table-id">ID</th>
										<th class="table-name">Tên nười dùng</th>
										<th class="table-image">Tên khách sạn</th>
										<th class="table-detail">Nội dung</th>
										<th class="table-rating">Đánh giá</th>
										<c:if test="${empty checkrole}">
											<th class="table-function">Chức năng</th>
										</c:if>
										<th class="table-rating">Yêu cầu xóa</th>
									</tr>
								</thead>
								<tbody class="text-align">
									<c:forEach items="${listHotelReview}" var="review">
										<c:set var="editUrl"
											value="${pageContext.request.contextPath }/admin/hotelreview/edit/${review.id_review}"></c:set>
										<c:set var="delUrl"
											value="${pageContext.request.contextPath }/admin/hotelreview/del/${review.id_review}"></c:set>
										<tr>
											<td><p>${review.id_review}</p></td>
											<td><p>${review.firstname}${review.lastname}</p></td>
											<td><p>${review.hotel_name}</p></td>
											<td><p>${review.content}</p></td>
											<td><p>${review.rating}</p></td>
											<c:if test="${empty checkrole}">
												<td>
													<p>
														<%-- <a href="${editUrl}"
															class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-edit" aria-hidden="true"></span>
															<span><strong>Edit</strong></span>
														</a>  --%>
														<a
															onclick="return confirm('Bạn có chắc chắn muốn xóa item này?')"
															href="${delUrl}" class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-remove" aria-hidden="true"></span>
															<span><strong>Delete</strong></span>
														</a>
													</p>
												</td>
												
												<td>
													<c:if test="${review.request == 1}">
														<p><i class="fa fa-check" aria-hidden="true"></i></p>
													</c:if>
												</td>	
												
												
											</c:if>
											<c:if test="${not empty checkrole}">
												<td><p>
														<%-- <a href="${editUrl}"
															class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-edit" aria-hidden="true"></span>
															<span><strong>Chỉnh sửa</strong></span>
														</a>  --%>
														<c:if test="${review.request == 1}">
															<p><i class="fa fa-check" aria-hidden="true"></i></p>
														</c:if>
														<c:if test="${review.request == 0}">
															<a
																href="${pageContext.request.contextPath}/admin/hotelreview/request/${review.id_review}"
																class="btn btn-primary a-btn-slide-text"
																style="background-color: #F5F5F5;"> <span
																class="glyphicon glyphicon-remove" aria-hidden="true"></span>
																<span><strong>Yêu cầu xóa</strong></span>
															</a>
														</c:if>
													</p>
												</td>
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