<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<section class="ftco-section">
	<div class="container">
		<div class="row">
			<div class="col-xl-8 ftco-animate" style="margin-left: 200px;">
				<form
					action="${pageContext.request.contextPath}/public/my-info/update"
					class="billing-form ftco-bg-dark p-3 p-md-5" id="infuser"
					method="post">
					<h3 class="mb-4 billing-heading" style="text-align: center;">Thông
						tin cá nhân</h3>
					<c:if test="${not empty error}">
						<div class='alert alert-danger' role='alert'>${msg}</div>
					</c:if>
					<c:if test="${not empty success}">
						<div class='alert alert-success' role='alert'>${success}</div>
					</c:if>
					<div class="row align-items-end">
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Tên tài khoản *</label> <input
									type="text" class="form-control" name="username"
									placeholder="Vui lòng nhập tên tài khoản" required="required"
									value="${userPublic.username}" disabled="disabled">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Mật khẩu *</label> <input type="text"
									class="form-control" name="password" placeholder=""  value="">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Tên *</label> <input type="text"
									class="form-control" name="firstname"
									placeholder="Vui lòng nhập tên của bạn" required="required"
									value="${userPublic.firstname}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Họ *</label> <input type="text"
									class="form-control" name="lastname"
									placeholder=" Vui lòng nhập họ của bạn" required="required"
									value="${userPublic.lastname}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="lastname">Số điện thoại *</label> <input type="text"
									name="phone" class="form-control"
									placeholder="Vui lòng nhập số điện thoại của bạn"
									required="required" value="${userPublic.phone}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="email">Địa chỉ email *</label> <input type="email"
									class="form-control"
									placeholder="Vui lòng nhập địa chỉ email của bạn"
									required="required" value="${userPublic.email}" name="email">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="streetaddress">Giới tính *</label> <br> <input
									type="radio" class="" required="required" value="1"
									name="gender" style="margin-left: 20px;" checked="checked">
								Nam <input type="radio" class="" required="required" value="0"
									name="gender" style="margin-left: 40px;"
									<c:if test="${userPublic.gender == 0}">checked="checked"</c:if>>
								Nữ
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="birthday">Ngày sinh *</label> <input type="date"
									class="form-control"
									placeholder="Vui lòng nhập ngày sinh của bạn"
									required="required" value="${userPublic.birthday}"
									name="birthday">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="lastname">Thành phố *</label> <input type="text"
									name="city" class="form-control"
									placeholder="Vui lòng nhập tỉnh / thành phố của bạn"
									required="required" value="${userPublic.city}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="streetaddress">Địa chi *</label> <input type="text"
									class="form-control"
									placeholder="Vui lòng nhập địa chỉ của bạn" required="required"
									value="${userPublic.address}" name="address">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" name="id_user" value="${userPublic.id_user}"
									style="display: none;">
							</div>
						</div>
					</div>
					<!-- END -->
					<div class="col-md-6">

						<p>
							<input type="submit" class="btn btn-primary py-3 px-4"
								value="Cập nhật"
								style="margin-left: 500px; border-radius: 5px; background-color: #1CC3B2; border: 1px solid #1CC3B2;" />
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>