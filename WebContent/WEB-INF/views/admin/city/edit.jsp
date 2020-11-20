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
										thông tin thành phố</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px; margin-bottom: 120px;">
							<form
								action="${pageContext.request.contextPath }/admin/city/edit/${city.id_city}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">

								<div class="form-group-inner">
									<label>City name</label> <input type="text" name="city_name"
										class="form-control" placeholder="Vui lòng nhập tên thành phố"
										style="border-radius: 5px;" value="${city.city_name}" required />
								</div>
								<div class="form-group-inner">
									<label>Country</label> <input type="text" name="country"
										class="form-control" placeholder="country"
										style="border-radius: 5px;" disabled="disabled"
										value="${city.country}" />
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 43.2%;" /> <input type="submit"
										value="Cập nhật" class="button-success"
										style="background-color: #4267B2; color: white; border: 1px solid #4267B2;" />
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>