<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>

<script  type="text/javascript">
$(document).ready(function(){
	$(".reviewhotel").on("click",function(){
		$(this).addClass("btn-secondary").removeClass("btn-primary");
		$(".reviewroom").addClass("btn-primary").removeClass("btn-secondary");
		$(".h3").html("Review về khách sạn của tôi");
		$(".list-hotelreview").show();
		$(".list-roomreview").hide();
	});
	$(".reviewroom").on("click",function(){
		$(this).addClass( "btn-secondary").removeClass("btn-primary");
		$(".reviewhotel").addClass("btn-primary").removeClass("btn-secondary");
		$(".h3").html("Review về phòng của tôi");
		$(".list-hotelreview").hide();
		$(".list-roomreview").show();
	});
});
</script>
<div style="text-align: center; margin-top: 50px;">
	<button type="button" class=" reviewhotel btn btn-secondary">Khách sạn</button>
	<button type="button" class=" reviewroom btn btn-primary">Phòng</button>
</div>
<h3 Class="h3" style="text-align: center; margin-top: 50px;">Review về khách sạn của tôi</h3>
<section class="ftco-section ftco-cart" style="margin-top: 50px;">
	<div class="container">
		<div class="row">
			<div class="col-md-12 ftco-animate">
				<div class="cart-list">
					<div class = "list-hotelreview">
					<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>Khách sạn</th>
									<th>Nội dung</th>
									<th>Đánh giá</th>
									<th>Ngày tạo</th>
								</tr>
							</thead>
							<tbody>
								<c:set value="${0}" var="tongtien"></c:set>
								<c:forEach var="hotelreview" items="${listHotelReview}">
									<tr class="text-center">
										<td class="product-name">
											<h5>${hotelreview.hotel_name}</h5>
										</td>
										<td class="product-name"><span>${hotelreview.content}</span>
										</td>
										<td class="quantity">
											<div class="reviwer-rating">
												<c:choose>
													<c:when test="${hotelreview.rating == 5}">
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<!-- <i class="fa fa-star-half-o"></i>
														<i class="fa fa-star-o"></i> -->
													</c:when>
													<c:when test="${hotelreview.rating == 4}">
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star-o"></i>
													</c:when>
													<c:when test="${hotelreview.rating == 3}">
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star-o"></i>
														<i class="fa fa-star-o"></i>
													</c:when>
													<c:when test="${hotelreview.rating == 2}">
														<i class="fa fa-star"></i>
														<i class="fa fa-star"></i>
														<i class="fa fa-star-o"></i>
														<i class="fa fa-star-o"></i>
														<i class="fa fa-star-o"></i>
													</c:when>
													<c:otherwise>
														<i class="fa fa-star"></i>
														<i class="fa fa-star-o"></i>
														<i class="fa fa-star-o"></i>
														<i class="fa fa-star-o"></i>
														<i class="fa fa-star-o"></i>
													</c:otherwise>
												</c:choose>
											</div>
										</td>
										<td class="quantity">${hotelreview.create_time}</td>
									</tr>
									<!-- END TR-->
								</c:forEach>
							</tbody>
						</table>
					</div>
						
					<div class = "list-roomreview" style="display:none">
						<table class="table">
						<thead class="thead-primary">
							<tr class="text-center">
								<th>Phòng</th>
								<th>Số phòng</th>
								<th>Khách sạn</th>
								<th>Nội dung</th>
								<th>Đánh giá</th>
								<th>Ngày tạo</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="roomreview" items="${listRoomReview}">
								<tr class="text-center">
									<td class="product-name">
										<h5>${roomreview.name}</h5>
									</td>
									<td class="product-name">
										<h5>${roomreview.room_number}</h5>
									</td>
									<td class="product-name">
										<h5>${roomreview.hotel_name}</h5>
									</td>
									<td class="product-name"><span>${roomreview.content}</span>
									</td>
									<td class="quantity">
										<div class="reviwer-rating">
											<c:choose>
												<c:when test="${roomreview.rating == 5}">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<!-- <i class="fa fa-star-half-o"></i>
													<i class="fa fa-star-o"></i> -->
												</c:when>
												<c:when test="${roomreview.rating == 4}">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star-o"></i>
												</c:when>
												<c:when test="${roomreview.rating == 3}">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star-o"></i>
													<i class="fa fa-star-o"></i>
												</c:when>
												<c:when test="${roomreview.rating == 2}">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star-o"></i>
													<i class="fa fa-star-o"></i>
													<i class="fa fa-star-o"></i>
												</c:when>
												<c:otherwise>
													<i class="fa fa-star"></i>
													<i class="fa fa-star-o"></i>
													<i class="fa fa-star-o"></i>
													<i class="fa fa-star-o"></i>
													<i class="fa fa-star-o"></i>
												</c:otherwise>
											</c:choose>
										</div>
									</td>
									<td class="quantity">${roomreview.create_time}</td>
								</tr>
								<!-- END TR-->
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


