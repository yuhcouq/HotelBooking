<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<h3 style="text-align: center; margin-top: 50px;">Manager booking</h3>
<section class="ftco-section ftco-cart" style="margin-top: 50px;">
	<div class="container" id="cartbooking_delete">
		<div class="row">
			<div class="col-md-12 ftco-animate">
				<div class="cart-list">
					<c:set value="${0}" var="tongtien"></c:set>
					<c:set value="${0}" var="prepayment"></c:set>
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
								</tr>
							</thead>
							<tbody>
								<c:forEach var="booking" items="${listBooking}">
									<c:set value="${tongtien + booking.total_price}" var="tongtien"></c:set>
									<c:set
										value="${prepayment + (booking.prepayment*booking.price)/100}"
										var="prepayment"></c:set>
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
									</tr>
									<!-- END TR-->
								</c:forEach>
								<tr class="text-center">
									<td class="quantity" colspan="7"></td>
									<td class="quantity" colspan="7"><p class="d-flex"
											style="font-size: 25px;">
											<span>Totals : <span
												style="color: #1CC3B2; font-weight: bold;">
													${defines.formatNumber(tongtien)} </span><u>VND</u></span>
										</p></td>
								</tr>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</section>
<h3 style="text-align: center; margin-top: 50px;">Thông tin khách
	hàng</h3>
<section class="ftco-section ftco-cart" style="margin-top: 50px;">
	<div class="container" id="cartbooking_delete">
		<div class="row">
			<div class="col-md-12 ftco-animate">
				<div class="cart-list">
					<table class="table">
						<thead class="thead-primary">
							<tr class="text-center">
								<th>Fullname</th>
								<th>Phone</th>
								<th>Email</th>
								<th>Gender</th>
								<th>Birthday</th>
								<th>City</th>
								<th>Address</th>
							</tr>
						</thead>
						<tbody>
							<tr class="text-center">
								<td class="product-name">
									<h5>${infoCustomer.lastname}${infoCustomer.firstname}</h5>
								</td>
								<td class="product-name"><span>${infoCustomer.phone}</span></td>
								<td class="price"><span>${infoCustomer.email} </span></td>
								<td class=""><c:choose>
										<c:when test="${infoCustomer.gender == 0}">Nữ</c:when>
										<c:otherwise>Nam</c:otherwise>
									</c:choose></td>
								<td class="image-prod">${infoCustomer.birthday}</td>
								<td class="city">${infoCustomer.city}</td>
								<td class="">${infoCustomer.address}</td>
							</tr>
							<!-- END TR-->
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>


<div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate"
	style="margin-left: 1040px;">
	<div class="cart-total mb-3">
		<h3>Thanh Toán</h3>
		<p class="d-flex total-price">
			<span>Tổng tiền : <span
				style="color: #1CC3B2; font-weight: bold;">
					${defines.formatNumber(tongtien)} </span><u>VND</u></span>
		</p>
		<hr>
		
		 <c:choose>
			<c:when test="${prepayment != 0}">
				<form>
					<p class="d-flex total-price">
						<c:set value="${defines.VNDToUSD(prepayment)}" var="payment"></c:set>
						<span>Tiền phải thanh toán : <span
							style="color: #1CC3B2; font-weight: bold;">${defines.formatNumber(prepayment)}</span><u>VND</u>
							(<span>${payment}</span> $)
						</span>
					</p>
					<div>
						<span style="margin-left: 10px;">. Thanh toán qua paypal</span><br />
						<div style="margin-right: 10px; margin-left: 15px;"
							id="paypal-button-container"></div>
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<form>
					<div>
						<span style="margin-left: 10px;">. Thanh toán Sau khi nhận
							phòng</span><br />
						<p class="">
							<a href="${pageContext.request.contextPath}/public/booking/paycomplete"
								class="btn btn-primary"
								style="background-color: #1CC3B2; border: 1px solid #1CC3B2; margin-left: 20px;">Đặt phòng >></a>
						</p>
					</div>
				</form>
			</c:otherwise>
		</c:choose> 
	</div>
</div>
<section class="ftco-section" style="margin-top: 100px;">
	<div class="container">
		<div class="row justify-content-center mb-5 pb-3">
			<div class="col-md-7 heading-section ftco-animate text-center">
				<span class="subheading">Discover</span>
				<h2 class="mb-4">Related products</h2>
				<p>Far far away, behind the word mountains, far from the
					countries Vokalia and Consonantia, there live the blind texts.</p>
			</div>
		</div>
	</div>
</section>
<script src="https://www.paypalobjects.com/api/checkout.js"></script>
<script>
	paypal.Button
			.render(
					{

						env : 'sandbox', // sandbox | production

						// PayPal Client IDs - replace with your own
						// Create a PayPal app: https://developer.paypal.com/developer/applications/create
						client : {
							sandbox : 'AW1q-4bdOWJScF7k8d246gGA80H57ru18n_z98ynxHNv8IWkcZl97bqWFHneQOVEquZggwQWhneFDppW',
							production : '<insert production client id>'
						},

						// Show the buyer a 'Pay Now' button in the checkout flow
						commit : true,

						// payment() is called when the button is clicked
						payment : function(data, actions) {

							// Make a call to the REST api to create the payment
							return actions.payment.create({
								payment : {
									transactions : [ {
										amount : {
											total : '${payment}',
											currency : 'USD'
										}
									} ]
								}
							});
						},

						// onAuthorize() is called when the buyer approves the payment
						onAuthorize : function(data, actions) {

							// Make a call to the REST api to execute the payment
							return actions.payment
									.execute()
									.then(
											function() {
												/* window
														.alert('Payment Complete!'); */
												window.location.href = "${pageContext.request.contextPath}/public/booking/paycomplete"
											});
						}

					}, '#paypal-button-container');
</script>