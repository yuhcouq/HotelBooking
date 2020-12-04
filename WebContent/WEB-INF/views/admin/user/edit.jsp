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
								<c:choose>
									<c:when test="${role == 2}">
										<span><strong>CHỈNH SỬA ĐỐI TÁC</strong></span>
									</c:when>
									<c:when test="${role == 3}">
										<span><strong>CHỈNH SỬA NHÂN VIÊN</strong></span>
									</c:when>
									<c:otherwise>
										<span><strong>CHỈNH SỬA NGƯỜI DÙNG</strong></span>
									</c:otherwise>
								</c:choose>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px;">
							<form
								action="${pageContext.request.contextPath }/admin/user/edit/${user.id_user}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<c:if test="${user.role_id != 4}">
									<div class="form-group-inner">
										<label>Tên khách sạn </label>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="chosen-select-single mg-b-20">
												<select data-placeholder="Choose a hotel..." name="hotel_id"
													class="form-control"
													style="border-radius: 5px; width: 700px;"
													>
													<c:if test="${not empty checkprofile}">
														<option value="${hotel.id_hotel}" selected="selected">${hotel.hotel_name }</option>
													</c:if>
													<c:if test="${empty checkprofile || user.role_id ==1}">
														<c:forEach items="${listHotels}" var="ht">
															<option
																<c:if test="${hotel.id_hotel == ht.id_hotel }">selected="selected"</c:if>
																value="${ht.id_hotel}">${ht.hotel_name }</option>
														</c:forEach>
													</c:if>
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								
								<div class="form-group-inner">
									<label>Tên tài khoản</label> <input type="text" name="username"
										class="form-control" placeholder="Vui lòng nhập tên tài khoản"
										style="border-radius: 5px;" value="${user.username}" required />
								</div>
								<div class="form-group-inner">
									<label>Mật khẩu</label> <input type="password" name="password"
										class="form-control" placeholder="Vui lòng nhập mật khẩu"
										style="border-radius: 5px;" value="" />
								</div>
								<div class="form-group-inner">
									<label>Ảnh đại diện</label> <input type="file" name="picture"
										class="form-control" placeholder="Vui lòng chọn ảnh đại diện"
										style="border-radius: 5px;" />
								</div>
								<div class="form-group-inner">
									<label>Tên người dùng</label> <input type="text"
										name="firstname" class="form-control"
										placeholder="Vui lòng nhập tên người dùng"
										style="border-radius: 5px;" value="${user.firstname}" required />
								</div>
								<div class="form-group-inner">
									<label>Họ người dùng</label> <input type="text" name="lastname"
										class="form-control" placeholder="Vui lòng nhập họ người dùng"
										style="border-radius: 5px;" value="${user.lastname}" required />
								</div>
								<div class="form-group-inner">
									<label>BNgày sinh</label> <input type="text" name="birthday"
										class="form-control" placeholder="Vui lòng nhập ngày sinh"
										style="border-radius: 5px;" value="${user.birthday}" required />
								</div>
								<div class="form-group-inner">
									<label>Số điện thoại</label> <input type="text" name="phone"
										class="form-control" placeholder="Vui lòng nhập sô điện thoại"
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
									<label>Địa chỉ</label> <input type="text" name="address"
										class="form-control" placeholder="Vui lòng nhập địa chỉ"
										style="border-radius: 5px;" value="${user.address}" required />
								</div>
								<div class="">
									<label>Giới tính</label><br> <input type="radio" id="male"
										name="gender" value="1" style="margin-left: 40px;"
										checked="checked"> <label for="male">Nam</label> <input
										type="radio" id="female" name="gender" value="0"
										style="margin-left: 40px;"> <label for="female">Nữ</label>
								</div>
								<div class="form-group-inner">
									<label>Địa chỉ email</label> <input type="email" name="email"
										class="form-control" placeholder="Vui lòng nhập địa chỉ email"
										style="border-radius: 5px;" value="${user.email}" required />
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