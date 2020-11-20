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
								<span><strong>Chỉnh sửa đánh giá</strong></span>
							</h3>
						</div>
						<div class="row" style="margin: 50px 20px; margin-bottom: 120px;">
							<form
								action="${pageContext.request.contextPath }/admin/hotelreview/edit/${hotelreview.id_review}"
								method="Post" style="margin-left: 10px;"
								enctype="multipart/form-data">

								<div class="form-group-inner">
									<label>User name</label> <input type="text" name="name"
										class="form-control" placeholder="city name"
										style="border-radius: 5px;" value="${hotelreview.firstname} ${hotelreview.lastname}" disabled="disabled"/>
								</div>
								<div class="form-group-inner">
									<label>Hotel name</label>
									<div class="chosen-select-single mg-b-20"
										style="width: 700px; margin-left: 15px;">
										<select data-placeholder="Choose a Country..." disabled="disabled"
											class="chosen-select" tabindex="-1" name="city">
											<c:forEach items="${listHotels}" var="hotel">
												<option value="${hotel.id_hotel}"
													<c:if test="${hotelreview.hotel_id == hotel.id_hotel }">selected="selected"</c:if>>${hotel.hotel_name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Title</label>
									<textarea name="title" class="form-control" rows="3"
										style="border-radius: 5px; width: 700px; margin-left: 15px;">${hotelreview.title}</textarea>
								</div>
								<div class="form-group-inner">
									<label>Content</label>
									<textarea name="content" class="form-control" rows="3"
										style="border-radius: 5px; width: 700px; height: 195px; margin-left: 15px;">${hotelreview.content}</textarea>
								</div>
								<div class="form-group-inner">
									<label>Rating</label> <input type="text" name="rating"
										class="form-control" placeholder="rating"
										style="border-radius: 5px;" value="${hotelreview.rating}"/>
								</div>
								<div class="">
									<input type="reset" value="Clear" class="button-clear" style="margin-left: 42.8%;"/> <input
										type="submit" value="Cập nhật" class="button-success" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>