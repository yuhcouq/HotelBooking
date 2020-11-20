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
							<c:if test="${not empty error}">
								<div class='alert alert-danger' role='alert'>${msg}</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class='alert alert-success' role='alert'>${success}</div>
							</c:if>
							<div class="row align-items-end">
								<div class="col-md-6" style="width: 200px;">
									<div class="form-group">
										<label for="firstname">Tên khách hàng</label> <input
											type="text" class="form-control" value="${customer_name}"
											disabled="disabled">
									</div>
								</div>
								<div class="col-md-6" style="width: 200px;">
									<div class="form-group">
										<label for="firstname">Số điện thoại</label> <input
											type="text" class="form-control" value="${customer_phone}"
											disabled="disabled">
									</div>
								</div>
							</div>
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
										<th class="table-function">Tên phòng</th>
										<th class="table-function">Hình ảnh</th>
										<th class="table-function">Số phòng</th>
										<th class="table-function">Ngày nhận phòng</th>
										<th class="table-function">Ngày trả phòng</th>
										<th class="table-function">Ngày</th>
										<th class="table-function">Giá phòng</th>
										<th class="table-function">Tổng tiền</th>
										<th class="table-function">Ngày đặt phòng</th>
										<th class="table-function">lưu ý của khách hàng</th>
										<c:if test="${userAdmin.role_id != 1}">
											<th style="width: 170px;">Chức năng</th>
										</c:if>
									</tr>
								</thead>
								<tbody class="text-align">
									<c:set var="STT" value="${0}"></c:set>
									<c:set var="tongtien" value="${0}"></c:set>
									<c:set var="dathanhtoan" value="${0}"></c:set>
									<c:forEach items="${listBookingDetail}" var="booking">
										<c:set var="editUrl"
											value="${pageContext.request.contextPath }/admin/booking/booking-edit/${booking.id_booking}"></c:set>
										<c:set var="delUrl"
											value="${pageContext.request.contextPath }/admin/booking/cancel/${booking.code}/${booking.id_booking}"></c:set>
										<c:set var="STT" value="${STT + 1}"></c:set>
										<c:set var="tongtien"
											value="${tongtien + booking.total_price}"></c:set>
										<c:set var="dathanhtoan"
											value="${dathanhtoan + (booking.paid*booking.room.price)/100}"></c:set>
										<c:set var="code" value="${booking.code}"></c:set>
										<tr>
											<td><p>${STT}</p></td>
											<td><p>${booking.code}</p></td>
											<td><p>${booking.room.name}</p></td>
											<td><p>
													<c:choose>
														<c:when test="${not empty booking.room.image }">
															<img
																Style="border-radius: 5px; width: 120px; height: 80px;"
																src="${pageContext.request.contextPath }/uploads/${booking.room.image}" />
														</c:when>
														<c:otherwise>
															<p>không có hình ảnh</p>
														</c:otherwise>
													</c:choose>
												</p></td>
											<td><p>${booking.room.room_number}</p></td>
											<td><p>${booking.checkin}</p></td>
											<td><p>${booking.checkout}</p></td>
											<td><p>${booking.day}</p></td>
											<td><p>${defines.formatNumber(booking.room.price)}<span>
														VND</span>
												</p></td>
											<td><p>${defines.formatNumber(booking.total_price)}<span>
														VND</span>
												</p></td>
											<td><p>${booking.created_time}</p></td>
											<td><p>${booking.note}</p></td>
											<c:if test="${userAdmin.role_id != 1}">
												<td><p>
														<a href="${editUrl}"
															class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-edit" aria-hidden="true"></span>
															<span><strong>Chỉnh sửa</strong></span>
														</a> <a
															onclick="return confirm('Bạn có chắc chắn muốn hủy item này?')"
															href="${delUrl}" class="btn btn-primary a-btn-slide-text"
															style="background-color: #F5F5F5;"> <span
															class="glyphicon glyphicon-remove" aria-hidden="true"></span>
															<span><strong>Hủy</strong></span>
														</a>
													</p></td>
											</c:if>
										</tr>
									</c:forEach>
									<tr>
										<c:choose>
											<c:when test="${userAdmin.role_id == 1}">
												<td colspan="8"></td>
											</c:when>
											<c:otherwise>
												<td colspan="9"></td>
											</c:otherwise>
										</c:choose>

										<td><p style="font-size: 20px;">
												<b>Tổng tiền : </b>${defines.formatNumber(tongtien)}<span>
													VND</span>
											</p></td>
										<td><p style="font-size: 20px;">
												<b>Đã thanh toán : </b><span style="color: #1DB5A7;">${defines.formatNumber(dathanhtoan)}</span><span>
													VND</span>
											</p></td>
										<td><p style="font-size: 20px;">
												<b>Còn lại : </b><span style="color: red;"><b>${defines.formatNumber(tongtien-dathanhtoan)}</b></span><span>
													VND</span>
											</p>
										<td><a
											href="${pageContext.request.contextPath }/admin/booking/payment/${code}"
											class="btn btn-primary a-btn-slide-text"
											style="background-color: #1CC3B2; color: white; margin-left: 10px; padding: 12px 35px;">
												<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
												<span><strong>Trả phòng</strong></span>
										</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>