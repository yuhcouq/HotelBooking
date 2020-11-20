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
								<span><strong>Edit Slide</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px; margin-bottom: 120px;">
							<form
								action="${pageContext.request.contextPath }/admin/slide/edit/${slide.id_slide}"
								method="Post" style="margin-left: 10px;" enctype="multipart/form-data">
								<div class="form-group-inner">
									<label>Status</label>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="chosen-select-single mg-b-20">
											<select data-placeholder="Choose a City..." name="status"
												class="form-control"
												style="border-radius: 5px; width: 700px;">
													<option value="1" <c:if test="${slide.status == 1}">selected="selected"</c:if>>ON</option>
													<option value="0" <c:if test="${slide.status == 0}">selected="selected"</c:if>>OFF</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Image</label> <input type="file" name="picture"
										class="form-control" placeholder="image"
										style="border-radius: 5px;" />
									<p style="margin-left: 15px;">
										<c:choose>
											<c:when test="${not empty slide.image }">
												<img Style="border-radius: 5px;" width="700px"
													height="100px"
													src="${pageContext.request.contextPath }/uploads/${slide.image}" />
											</c:when>
											<c:otherwise>
												<p>không có hình ảnh</p>
											</c:otherwise>
										</c:choose>
									</p>
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear"
										style="margin-left: 45.5%;" /> <input type="submit"
										value="Edit" class="button-success" />
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>