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
								<c:choose>
									<c:when test="${role == 2}">
										<span><strong>THÊM MỚI ĐỐI TÁC</strong></span>
									</c:when>
									<c:when test="${role == 3}">
										<span><strong>THÊM MỚI NHÂN VIÊN</strong></span>
									</c:when>
									<c:otherwise>
										<span><strong>THÊM MỚI NGƯỜI DÙNG</strong></span>
									</c:otherwise>
								</c:choose>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px;">
							<form action="${pageContext.request.contextPath }/admin/user/add"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<c:if test="${role == 2}">
									<div class="form-group-inner">
										<label>Chọn khách sạn cần quản lý</label>
										<div class="chosen-select-single mg-b-20"
											style="width: 700px; margin-left: 15px;">
											<select data-placeholder="Choose a Country..."
												class="chosen-select" tabindex="-1" name="hotel_id">
												<option value="0">-- Chọn --</option>
												<c:forEach items="${listHotel}" var="hotel">
													<option value="${hotel.id_hotel }">${hotel.hotel_name }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</c:if>
								<c:if test="${role == 3}">
									<input type="text" name="hotel_id" style="display: none;"
										value="${userAdmin.hotel_id}" />
								</c:if>
								<div class="form-group-inner">
									<label>Tên tài khoản <span style="color: red;">*</span></label>
									<input type="text" name="username" class="form-control"
										placeholder="Vui lòng nhập username"
										style="border-radius: 5px;" value="${user.username}" required />
								</div>
								<div class="form-group-inner">
									<label>Mật khẩu <span style="color: red;">*</span></label> <input
										type="password" name="password" class="form-control"
										placeholder="Vui lòng nhập password"
										style="border-radius: 5px;" value="${user.password}" required />
								</div>
								<div class="form-group-inner">
									<label>Ảnh đại diện <span style="color: red;">*</span></label>
									<input type="file" name="picture" class="form-control"
										placeholder="Vui lòng chọn avatar" style="border-radius: 5px;"
										required />
								</div>
								<div class="form-group-inner">
									<label>Tên <span style="color: red;">*</span></label> <input
										type="text" name="firstname" class="form-control"
										placeholder="Vui lòng nhập firstname"
										style="border-radius: 5px;" value="${user.firstname}" required />
								</div>
								<div class="form-group-inner">
									<label>Họ <span style="color: red;">*</span></label> <input
										type="text" name="lastname" class="form-control"
										placeholder="Vui lòng nhập lastname"
										style="border-radius: 5px;" value="${user.lastname}" required />
								</div>
								<div class="form-group-inner">
									<label>Ngày sinh <span style="color: red;">*</span></label> <input
										type="date" name="birthday" class="form-control"
										placeholder="Vui lòng nhập ngày sinh"
										style="border-radius: 5px;" value="${user.birthday}" required />
								</div>
								<div class="form-group-inner">
									<label>Phone <span style="color: red;">*</span></label> <input
										type="text" name="phone" class="form-control"
										placeholder="Vui lòng nhập số điện thoại"
										style="border-radius: 5px;" value="${user.phone}" required />
								</div>
								<div class="form-group-inner">
									<label>Thành phố</label>
									<div class="chosen-select-single mg-b-20"
										style="width: 700px; margin-left: 15px;">
										<select data-placeholder="Choose a Country..."
											class="chosen-select" tabindex="-1" name="city">
											<c:forEach items="${listCities}" var="city">
												<option value="${city.city_name }"
													<c:if test="${user.city == city.city_name }">selected="selected"</c:if>>${city.city_name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Địa chỉ <span style="color: red;">*</span></label> <input
										type="text" name="address" class="form-control"
										placeholder="Vui lòng nhập địa chỉ"
										style="border-radius: 5px;" value="${user.address}" required />
								</div>
								<div class="">
									<label>Giới tính <span style="color: red;">*</span></label><br>
									<input type="radio" id="male" name="gender" value="1"
										style="margin-left: 40px;" checked="checked"> <label
										for="male">Nam</label> <input type="radio" id="female"
										name="gender" value="0" style="margin-left: 40px;"> <label
										for="female">Nữ</label>
								</div>
								<div class="form-group-inner">
									<label>Địa chỉ email <span style="color: red;">*</span></label>
									<input type="email" name="email" class="form-control"
										placeholder="Vui lòng nhập địa chỉ email"
										style="border-radius: 5px;" value="${user.email}"
										onclick="getLocation();" required />
								</div>
								<div class="form-group-inner" style="display: none;">
									<input type="text" name="latitude" id="latitude"
										class="form-control" placeholder="latitude"
										style="border-radius: 5px;" value="" />
								</div>
								<div class="form-group-inner" style="display: none;">
									<input type="text" name="longitude" id="longitude"
										class="form-control" placeholder="longitude"
										style="border-radius: 5px;" value="" />
								</div>
								<c:choose>
									<c:when test="${role == 2}">
										<div class="form-group-inner" style="display: none;">
											<input type="text" name="role_id" value="2" />
										</div>
									</c:when>
									<c:when test="${role == 3}">
										<div class="form-group-inner" style="display: none;">
											<input type="text" name="role_id" value="3" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="form-group-inner" style="display: none;">
											<input type="text" name="role_id" value="4" />
										</div>
									</c:otherwise>
								</c:choose>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 42.6%;" /> <input type="submit"
										value="Thêm mới" class="button-success" />
								</div>
							</form>
							<script>
							window.onload = getLocation;
								function getLocation() {
									if (navigator.geolocation) {
										navigator.geolocation
												.getCurrentPosition(setPosition);
									} else {
										alert("Geolocation không được hỗ trợ bởi trình duyệt này.");
									}
								}

								function setPosition(position) {
									document.getElementById("latitude").value = position.coords.latitude;
									document.getElementById("longitude").value = position.coords.longitude;
								}
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>