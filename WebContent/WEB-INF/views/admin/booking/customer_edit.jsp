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
								<span><strong>Chỉnh sửa thông tin khách hàng</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px;">
							<form
								action="${pageContext.request.contextPath }/admin/booking/customer-edit/${booking.code}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<div class="form-group-inner">
									<label>Tên <span style="color: red;">*</span></label> <input
										type="text" name="firstname" class="form-control"
										placeholder="Vui lòng nhập firstname"
										style="border-radius: 5px;" value="${booking.firstname}"
										required />
								</div>
								<div class="form-group-inner">
									<label>Họ <span style="color: red;">*</span></label> <input
										type="text" name="lastname" class="form-control"
										placeholder="Vui lòng nhập lastname"
										style="border-radius: 5px;" value="${booking.lastname}"
										required />
								</div>
								<div class="form-group-inner">
									<label>Phone <span style="color: red;">*</span></label> <input
										type="text" name="phone" class="form-control"
										placeholder="Vui lòng nhập số điện thoại"
										style="border-radius: 5px;" value="${booking.phone}" required />
								</div>
								<div class="form-group-inner">
									<label>Địa chỉ email <span style="color: red;">*</span></label>
									<input type="email" name="email" class="form-control"
										placeholder="Vui lòng nhập địa chỉ email"
										style="border-radius: 5px;" value="${booking.email}"
										onclick="getLocation();" required />
								</div>
								<div class="">
									<label>Giới tính <span style="color: red;">*</span></label><br>
									<input type="radio" id="male" name="gender" value="1"
										style="margin-left: 40px;" checked="checked"> <label
										for="male">Nam</label> <input type="radio" id="female"
										name="gender" value="0" style="margin-left: 40px;"
										<c:if test="${booking.gender == 0}">checked="checked"</c:if>>
									<label for="female">Nữ</label>
								</div>
								<div class="form-group-inner">
									<label>Ngày sinh <span style="color: red;">*</span></label> <input
										type="date" name="birthday" class="form-control"
										placeholder="Vui lòng nhập ngày sinh"
										style="border-radius: 5px;" value="${booking.birthday}" required />
								</div>
								<div class="form-group-inner">
									<label>Thành phố <span style="color: red;">*</span></label> <input
										type="text" name="city" class="form-control"
										placeholder="Vui lòng nhập ngày sinh"
										style="border-radius: 5px;" value="${booking.city}" required />
								</div>
								<div class="form-group-inner">
									<label>Địa chỉ <span style="color: red;">*</span></label> <input
										type="text" name="address" class="form-control"
										placeholder="Vui lòng nhập địa chỉ"
										style="border-radius: 5px;" value="${booking.address}" required />
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 42.6%;" /> <input type="submit"
										value="Câp nhật" class="button-success" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>