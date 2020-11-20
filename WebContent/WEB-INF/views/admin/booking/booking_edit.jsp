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
							<h3 style="margin-left: 20px;">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
								<span><strong>Chỉnh sửa đơn đặt phòng</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px;">
							<form
								action="${pageContext.request.contextPath }/admin/booking/booking-edit/${booking.id_booking}/${booking.code}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<div class="form-group-inner">
									<label>Tên khách sạn <span style="color: red;">*</span></label>
									<input type="text" name="name" class="form-control"
										placeholder="Vui lòng nhập tên phòng"
										style="border-radius: 5px;" value="${userAdmin.hotel_name}"
										disabled="disabled" />
								</div>
								<div class="form-group-inner">
									<label>Chọn Phòng</label>
									<div class="chosen-select-single mg-b-20"
										style="width: 700px; margin-left: 15px;">
										<select data-placeholder="Choose a Country..."
											class="chosen-select" tabindex="-1" name="room_id">
											<c:forEach items="${listRooms}" var="room">
												<option value="${room.id_room }"
													<c:if test="${booking.room_id == room.id_room}">selected="selected"</c:if>>${room.room_number}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Ngày nhận phòng <span style="color: red;">*</span></label>
									<input type="Date" name="checkin" class="form-control"
										placeholder="Vui lòng nhập ngày nhận phòng"
										style="border-radius: 5px;" value="${booking.checkin}" />
								</div>
								<div class="form-group-inner">
									<label>Ngày trả phòng <span style="color: red;">*</span></label>
									<input type="Date" name="checkout" class="form-control"
										placeholder="Vui lòng nhập ngày nhận phòng"
										style="border-radius: 5px;" value="${booking.checkout}" />
								</div>
								<div class="form-group-inner">
									<label>Lưu ý <span style="color: red;">*</span></label>
									<textarea name="note" class="form-control" rows="3"
										style="border-radius: 5px; width: 700px; height: 195px; margin-left: 15px;"
										required>${booking.note}</textarea>
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 42.7%;" /> <input type="submit"
										value="Cập nhật" class="button-success" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>