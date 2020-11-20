<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<h3 style="text-align: center; margin-top: 50px;">Manager booking</h3>
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
								<th>Price</th>
								<th>CheckIn</th>
								<th>CheckOut</th>
								<th>Day</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							<c:set value="${0}" var="tongtien"></c:set>
							<c:forEach var="room" items="${listRoomsBooking}">
								<c:set value="${room.quantity * room.price}" var="thanhtien"></c:set>
								<c:set value="${tongtien + thanhtien}" var="tongtien"></c:set>
								<tr class="text-center">
									<td class="product-name">
										<h5>${room.hotel_name}</h5>
									</td>
									<td class="product-name"><span>${room.room_number}</span>
									</td>
									<td class="image-prod"><img Style="border-radius: 5px;"
										width="120px" height="90px"
										src="${pageContext.request.contextPath }/uploads/${room.image}" /></td>
									<td class="price"><span>${defines.formatNumber(room.price)}
											<u>vnd</u>
									</span></td>
									<td class="quantity">${room.checkin}</td>
									<td class="quantity">${room.checkout}</td>
									<td class="quantity">${room.quantity}</td>
									<td><a href="javascript:;"
										class="btn btn-primary a-btn-slide-text"
										style="background-color: #F5F5F5; color: black;"
										onclick="deletebooking(${room.id_room});"> <span
											class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											<span><strong>Delete</strong></span>
									</a></td>
								</tr>
								<!-- END TR-->
							</c:forEach>
						</tbody>
					</table>
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
							<span>Total : <span style="color: #1CC3B2; font-weight: bold;">
									${defines.formatNumber(tongtien)} </span><u>vnd</u></span>
						</p>
					</div>
					<p class="text-center">
						<a
							href="${pageContext.request.contextPath}/public/news/checkout/${tongtien}"
							class="btn btn-primary py-3 px-4" style="background-color: #1CC3B2; border: 1px solid #1CC3B2;">Next >></a>
					</p>
				</c:if>
			</div>
		</div>
		<c:if test="${soluong == 0}">
			<div style="text-align: center;">
				<span style="color: red; font-size: 30px">Chưa có sản phẩm
					nào.</span>
			</div>
		</c:if>
	</div>
	<script>
						function deletebooking(id_room) {
							$
								.ajax({
									url : '${pageContext.request.contextPath}/public/booking/updateQuantity',
									type : 'GET',
									cache : false,
									data : {
									//Dữ liệu gửi đi
									},
									success : function(data) {
										// Xử lý thành công
										$('#quantity_booking').html(data);
									},
									error : function() {
										alert('lỗi!');
									}
								});
							 $
								.ajax({
									url : '${pageContext.request.contextPath}/public/booking/delete/'
											+ id_room,
									type : 'GET',
									cache : false,
									data : {
									//Dữ liệu gửi đi
									},
									success : function(data) {
										// Xử lý thành công
										$('#cartbooking_delete').html(data);
									},
									error : function() {
										alert('lỗi!');
									}
								});
						}
					</script>
</section>

<section class="ftco-section">
	<div class="container">
		<div class="row justify-content-center mb-5 pb-3">
			<div class="col-md-7 heading-section ftco-animate text-center">
				<span class="subheading">Discover</span>
				<h2 class="mb-4">Related products</h2>
				<p>Far far away, behind the word mountains, far from the
					countries Vokalia and Consonantia, there live the blind texts.</p>
			</div>
		</div>
		<div class="row">
			<c:forEach var="food" items="${listFoodRand}">
				<div class="col-md-3">
					<div class="menu-entry">
						<a href="#" class="img"
							style="background-image: url(${pageContext.request.contextPath }/uploads/${food.image});"></a>
						<div class="text text-center pt-4">
							<h3>
								<a href="#">${food.name}</a>
							</h3>
							<p>${food.previews}</p>
							<p class="price">
								<span>${defines.formatNumber(food.price)} <u>đ</u></span>
							</p>
							<p>
								<a href="javascript:;" onclick="addcart(${food.stt});"
									class="btn btn-primary btn-outline-primary">Add to Cart</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>
<script>
						function addcart(stt) {
							alert(' Đã thêm sản phẩm vào giỏ hàng.'),
							 $
									.ajax({
										url : '${pageContext.request.contextPath}/public/news/addcart/'
												+ stt,
										type : 'GET',
										cache : false,
										data : {
										//Dữ liệu gửi đi
										},
										success : function(data) {
											// Xử lý thành công
											$('#addcart').html(data);
										},
										error : function() {
											alert('lỗi!');
										}
									});
						}
					</script>
