<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<h3 style="text-align: center; margin-top: 50px;">Đơn đặt phòng của tôi</h3>
<section class="ftco-section ftco-cart" style="margin-top: 50px;">
	<div class="container" id="cartbooking_delete">
		<div class="row">
			<div class="col-md-12 ftco-animate">
				<div class="cart-list">

					<table class="table">
						<thead class="thead-primary">
							<tr class="text-center">
								<th>Hotel</th>
								<th>Room number</th>
								<th>Image</th>
								<th>CheckIn</th>
								<th>CheckOut</th>
								<th>Day</th>
								<th>ToTal Price</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:set value="${0}" var="tongtien"></c:set>
							<c:forEach var="booking" items="${listBooking}">
								<c:set value="${tongtien + booking.total_price}" var="tongtien"></c:set>
								<tr class="text-center">
									<td class="product-name">
										<h5>${booking.hotel_name}</h5>
									</td>
									<td class="product-name"><span>${booking.room_number}</span>
									</td>
									<td class="image-prod"><img Style="border-radius: 5px;"
										src="${pageContext.request.contextPath }/uploads/${booking.image}" /></td>
									<td class="quantity">${booking.checkin}</td>
									<td class="quantity">${booking.checkout}</td>
									<td class="quantity">${booking.day}</td>
									<td class="quantity">${defines.formatNumber(booking.total_price)}</td>
									<td class="quantity"><c:choose>
											<c:when test="${booking.status == 0}">Chưa nhận phòng</c:when>
											<c:when test="${booking.status == 2}">Chưa đánh giá <u><a
													href="${pageContext.request.contextPath }/public/reviews/${booking.room_id}/${booking.id_booking}" style="color: #1CC3B2;">Tới đánh giá</a></u>
											</c:when>
										</c:choose></td>
								</tr>
								<!-- END TR-->
							</c:forEach>
							<tr class="text-center">
								<td class="quantity" colspan="7"></td>
								<td class="quantity"><p class="d-flex"
										style="font-size: 25px;">
										<span>Totals : <span
											style="color: #1CC3B2; font-weight: bold;">
												${defines.formatNumber(tongtien)} </span><u>VND</u></span>
									</p></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
