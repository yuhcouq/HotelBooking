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
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
								<span><strong>Chỉnh sửa
										thông tin phòng</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px;">
							<form
								action="${pageContext.request.contextPath }/admin/room/edit/${room.id_room}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<div class="form-group-inner">
									<label>Tên khách sạn </label>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="chosen-select-single mg-b-20">
											<select data-placeholder="Choose a hotel..." name="hotel_id"
												class="form-control"
												style="border-radius: 5px; width: 700px;"
												disabled="disabled">
												<c:forEach items="${listHotels}" var="hotel">
													<option
														<c:if test="${room.hotel_id == hotel.id_hotel }">selected="selected"</c:if>
														value="${hotel.id_hotel}">${hotel.hotel_name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Tên phòng <span style="color: red;">*</span></label> <input
										type="text" name="name" class="form-control"
										placeholder="Vui lòng nhập tên phòng"
										style="border-radius: 5px;" value="${room.name}" required />
								</div>
								<div class="form-group-inner">
									<label>Số phòng <span style="color: red;">*</span></label> <input
										type="number" name="room_number" class="form-control"
										placeholder="Vui lòng nhập số phòng"
										style="border-radius: 5px;" value="${room.room_number}"
										required />
								</div>
								<div class="form-group-inner">
									<label>Số lượng người lớn <span style="color: red;">*</span></label>
									<input type="number" name="adult_number" class="form-control"
										placeholder="Vui lòng nhập số lượng người lớn"
										style="border-radius: 5px;" value="${room.adult_number}"
										required />
								</div>
								<div class="form-group-inner">
									<label>Số lượng trẻ em <span style="color: red;">*</span></label>
									<input type="number" name="children_number"
										class="form-control"
										placeholder="Vui lòng nhập số lượng trẻ em"
										style="border-radius: 5px;" value="${room.children_number}"
										required />
								</div>
								<div class="form-group-inner">
									<label>Số lượng giường <span style="color: red;">*</span></label>
									<input type="number" name="bed_number" class="form-control"
										placeholder="Vui lòng nhập số lượng giường"
										style="border-radius: 5px;" value="${room.bed_number}"
										required />
								</div>
								<div class="form-group-inner">
									<label>Hình ảnh hiển thị <span style="color: red;">*</span></label>
									<input type="file" name="picture" class="form-control"
										placeholder="Vui lòng chọn ảnh phòng"
										style="border-radius: 5px;" />
								</div>
								<div class="form-group-inner">
									<label>Giá phòng <span style="color: red;">*</span></label> <input
										type="number" name="price" class="form-control"
										placeholder="Vui lòng nhập giá phòng"
										style="border-radius: 5px;" value="${room.price}" required />
								</div>
								<div class="form-group-inner">
									<label>Diện tích <span style="color: red;">*</span></label> <input
										type="number" name="acreage" class="form-control"
										placeholder="Vui lòng nhập diện tích"
										style="border-radius: 5px;" value="${room.acreage}" />
								</div>
								<div class="">
									<label>Dịch vụ</label><br> <input type="checkbox"
										name="wifi" value="1" style="margin-left: 50px;"
										checked="checked" /> Wifi <input type="checkbox"
										name="television" value="1" style="margin-left: 50px;"
										checked="checked" /> TV <input type="checkbox"
										name="conditioning" value="1" checked="checked"
										style="margin-left: 50px;" /> Điều hòa <input type="checkbox"
										name="drinks" value="1" checked="checked"
										style="margin-left: 50px;" /> Nhà hàng <input type="checkbox"
										name="restaurant" value="1" checked="checked"
										style="margin-left: 50px;" /> Nước uống<br> <br>
								</div>
								<div class="form-group-inner">
									<label>Thanh toán trước <span style="color: red;">*</span></label>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="chosen-select-single mg-b-20">
											<select data-placeholder="Choose a prepayment..."
												name="prepayment" class="form-control"
												style="border-radius: 5px; width: 700px;">
												<c:forEach items="${listPrepayments}" var="prepayment">
													<option
														<c:if test="${prepayment.percent == room.prepayment }">selected="selected"</c:if>
														value="${prepayment.percent}">${prepayment.percent}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Mô tả <span style="color: red;">*</span></label>
									<textarea name="detail" class="form-control" rows="3"
										style="border-radius: 5px; width: 700px; margin-left: 15px;"
										required>${room.description}</textarea>
								</div>
								<div class="form-group-inner">
									<label>Chi tiết <span style="color: red;">*</span></label>
									<textarea name="description" class="form-control" rows="3"
										style="border-radius: 5px; width: 700px; height: 195px; margin-left: 15px;"
										required>${room.detail}</textarea>
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 43.2%;" /> <input type="submit"
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