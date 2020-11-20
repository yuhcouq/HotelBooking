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
							<h3
								style="margin-left: 20px;">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
								<span><strong>Add New Slide</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px; margin-bottom: 120px;">
							<form
								action="${pageContext.request.contextPath }/admin/slide/add"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">
								<div class="form-group-inner">
									<label>Image</label> <input type="file" name="picture"
										class="form-control" placeholder="image"
										style="border-radius: 5px;" />
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear" style="margin-left: 45.5%;"/> <input
										type="submit" value="Add" class="button-success" />
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>