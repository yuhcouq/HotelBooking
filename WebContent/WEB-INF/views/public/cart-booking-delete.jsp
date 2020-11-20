<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<div class="container" id="cartbooking_delete">
	<div class="row">
		<div class="col-md-12 ftco-animate">
			<div class="cart-list">
				<c:set value="${0}" var="tongtien"></c:set>
				<c:if test="${soluong > 0}">
					<table class="table">
						<thead class="thead-primary">
							<tr class="text-center">
								<th>Hotel</th>
								<th>Room number</th>
								<th>Image</th>
								<th>Price</th>
								<th>CheckIn</th>
								<th>CheckOut</th>
								<th>Day</th>
								<th>ToTal Price</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
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
									<td class="price"><span>${defines.formatNumber(booking.price)}
											<u>vnd</u>
									</span></td>
									<td class="quantity">${booking.checkin}</td>
									<td class="quantity">${booking.checkout}</td>
									<td class="quantity">${booking.day}</td>
									<td class="quantity">${defines.formatNumber(booking.total_price)}</td>
									<td><a href="javascript:;"
										class="btn btn-primary a-btn-slide-text"
										style="background-color: #F5F5F5; color: black;"
										onclick="deletebooking(${booking.room_id});"> <span
											class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											<span><strong>Delete</strong></span>
									</a></td>
								</tr>
								<!-- END TR-->
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<div class="row justify-content-end">
		<div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate">
			<c:if test="${tongtien !=0}">
				<div class="cart-total mb-3">
					<h3>Cart Totals</h3>
					<p class="d-flex">
						<span>Subtotal : <span style="color: #1CC3B2">
								${defines.formatNumber(tongtien)} </span><u>vnd</u></span>
					</p>
					<hr>
					<p class="d-flex total-price">
						<span>Total : <span
							style="color: #1CC3B2; font-weight: bold;">
								${defines.formatNumber(tongtien)} </span><u>vnd</u></span>
					</p>
				</div>
				<p class="text-center">
					<a href="${pageContext.request.contextPath}/public/booking/info"
						class="btn btn-primary py-3 px-4"
						style="background-color: #1CC3B2; border: 1px solid #1CC3B2;">Next
						>></a>
				</p>
			</c:if>
		</div>
	</div>
	<c:if test="${soluong == 0}">
		<div style="text-align: center; margin-bottom: 100px;">
			<span style="color: red; font-size: 30px">Chưa có sản phẩm
				nào.</span>
		</div>
	</c:if>
</div>