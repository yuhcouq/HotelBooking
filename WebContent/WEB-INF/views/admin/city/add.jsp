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
								<span><strong>Thêm mới thành phố</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px; margin-bottom: 120px;">
							<form action="${pageContext.request.contextPath }/admin/city/add"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">

								<div class="form-group-inner">
									<label>Tên thành phố <span style="color: red;">*</span></label>
									<input type="text" name="city_name" class="form-control"
										placeholder="Vui lòng nhập tên thành phố"
										style="border-radius: 5px;" required />
								</div>
								<div class="form-group-inner">
									<label>Quốc gia <span style="color: red;">*</span></label> <input
										type="text" name="country" class="form-control"
										placeholder="country" style="border-radius: 5px;"
										disabled="disabled" value="Việt Nam" />
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 42.7%;" /> <input type="submit"
										value="Thêm mới" class="button-success" />
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>