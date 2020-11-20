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
<section class="ftco-section">
	<div class="container">
		<div class="row">
			<div class="col-xl-8 ftco-animate" style="margin-left: 200px;">
				<form
					action="${pageContext.request.contextPath}/public/booking/info"
					class="billing-form ftco-bg-dark p-3 p-md-5" id="infuser"
					method="post">
					<h3 class="mb-4 billing-heading" style="text-align: center;">Billing
						Details</h3>
					<div class="row align-items-end">
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Firstname *</label> <input type="text"
									class="form-control" name="firstname"
									placeholder="Vui lòng nhập tên của bạn" required="required"
									value="${userPublic.firstname}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Lasttname *</label> <input type="text"
									class="form-control" name="lastname"
									placeholder=" Vui lòng nhập họ của bạn" required="required"
									value="${userPublic.lastname}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="lastname">Phone *</label> <input type="text"
									name="phone" class="form-control"
									placeholder="Vui lòng nhập số điện thoại của bạn"
									required="required" value="${userPublic.phone}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="email">Email *</label> <input type="email"
									class="form-control"
									placeholder="Vui lòng nhập địa chỉ email của bạn"
									required="required" value="${userPublic.email}" name="email">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="streetaddress">Gender *</label> <br> <input
									type="radio" class="" required="required" value="1"
									name="gender" style="margin-left: 20px;" checked="checked">
								Nam <input type="radio" class="" required="required" value="0"
									name="gender" style="margin-left: 40px;"
									<c:if test="${userPublic.gender == 0}">checked="checked"</c:if>>
								Nữ
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="birthday">Birthday *</label> <input type="date"
									class="form-control"
									placeholder="Vui lòng nhập ngày sinh của bạn"
									required="required" value="${userPublic.birthday}"
									name="birthday">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="lastname">City *</label> <input type="text"
									name="city" class="form-control"
									placeholder="Vui lòng nhập tỉnh / thành phố của bạn"
									required="required" value="${userPublic.city}">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="streetaddress">Address *</label> <input type="text"
									class="form-control"
									placeholder="Vui lòng nhập địa chỉ của bạn" required="required"
									value="${userPublic.address}" name="address">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="message">Note </label>
								<textarea rows="5" cols="85" name="note"
									style="border: 1px solid #CED4DA; width: 635px;"></textarea>
							</div>
						</div>
						<%-- <div class="col-md-6">
							<div class="form-group">
								<input type="text" name="id_user" value="${userPublic.id_user}"
									style="display: none;">
							</div>
						</div> --%>
						<div class="col-md-6">
							<div class="form-group">
								<input type="text" name="id_user" value="1"
									style="display: none;">
							</div>
						</div>
					</div>
					<!-- END -->
					<div class="col-md-6">

						<p>
							<input type="submit" class="btn btn-primary py-3 px-4"
								value="Next >>"
								style="margin-left: 520px; border-radius: 5px; background-color: #1CC3B2; border: 1px solid #1CC3B2;" />
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>