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
								<span><strong>Thêm mới hình ảnh chi tiết</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px;">
							<form
								action="${pageContext.request.contextPath }/admin/room/add_image/${id_room}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<div class="form-group-inner">
									<label>Hình ảnh chi tiết <span style="color: red;">*</span></label> <input type="file"
										name="picture" id="image_multiple" class="form-control"
										placeholder="images" style="border-radius: 5px;"
										accept="image/jpg, image/jpeg" required/>
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 46%;" /> <input type="submit" value="Add"
										class="button-success" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>