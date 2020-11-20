<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!-- Breadcrumb Area Start -->
<div class="breadcrumb-area bg-img bg-overlay jarallax"
	style="background-image: url(img/bg-img/16.jpg);">
	<div class="container h-100">
		<div class="row h-100 align-items-center">
			<div class="col-12">
				<div class="breadcrumb-content text-center">
					<h2 class="page-title">Our Room</h2>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb justify-content-center">
							<li class="breadcrumb-item"><a href="index.html">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Room</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Breadcrumb Area End -->

<!-- Rooms Area Start -->
<div class="roberto-rooms-area section-padding-100-0">
	<div class="container">
		<div class="row">
			<div class="col-12 col-lg-8">
				<!-- Single Room Area -->
				<c:choose>
					<c:when test="${empty listRooms}">
						<p>Không có phòng nào.</p>
					</c:when>
				</c:choose>
				<c:forEach items="${listRooms}" var="room">
					<div
						class="single-room-area d-flex align-items-center mb-50 wow fadeInUp"
						data-wow-delay="100ms">
						<!-- Room Thumbnail -->
						<div class="room-thumbnail">
							<img
								src="${pageContext.request.contextPath }/uploads/${room.image}"
								alt="">
						</div>
						<!-- Room Content -->
						<div class="room-content">
							<h2>Room View Sea</h2>
							<h4>
								${defines.formatNumber(room.price)} <span>/ Day</span>
							</h4>
							<div class="room-feature">
								<h6>
									Diện tích: <span>${defines.formatNumber(room.acreage)}
										m2</span>
								</h6>
								<c:set var="size"
									value="${room.adult_number + room.children_number}"></c:set>
								<h6>
									Số lượng: <span>${size} Người</span>
								</h6>
								<h6>
									Giường: <span>${room.bed_number}</span>
								</h6>
								<h6>
									Dịch vụ: <span>Wifi, TV . . .</span>
								</h6>
							</div>
							<a
								href="${pageContext.request.contextPath }/public/single_room/${room.id_room}"
								class="btn view-detail-btn">View Details <i
								class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
						</div>
					</div>
				</c:forEach>
				<!-- Pagination -->
				<nav class="roberto-pagination wow fadeInUp mb-100"
					data-wow-delay="1000ms">
					<ul class="pagination">
						<c:forEach var="i" begin="1" end="${sumPage}">
							<li class="page-item"><a class="page-link"
								<c:if test="${page == i }">style="background-color: #24C5B5"</c:if>
								href="${pageContext.request.contextPath }/public/search/${i}/${hotel_name}">${i}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath }/public/search/${sumPage}/${hotel_name}">>
								<i class="fa fa-angle-right"></i>
						</a></li>
					</ul>
				</nav>
			</div>

			<div class="col-12 col-lg-4">
				<!-- Hotel Reservation Area -->
				<div class="hotel-reservation--area mb-100">
					<form
						action="${pageContext.request.contextPath }/public/check_room"
						method="post" style="margin-bottom: 300px;">
						<div class="form-group mb-30">
							<label for="checkInDate">Date</label>
							<div class="input-daterange" id="datepicker">
								<div class="row no-gutters">
									<div class="col-6">
										<input type="text" class="input-small form-control"
											id="checkInDate" name="checkin" placeholder="Check In">
									</div>
									<div class="col-6">
										<input type="text" class="input-small form-control"
											name="checkout" placeholder="Check Out">
									</div>
								</div>
							</div>
						</div>
						<div class="form-group mb-30">
							<label for="guests">Guests</label>
							<div class="row">
								<div class="col-6">
									<select name="adults" id="guests" class="form-control">
										<option value="00">Adults</option>
										<option value="01"
											<c:if test="${check.adults == 01 }">selected="selected"</c:if>>01</option>
										<option value="02"
											<c:if test="${check.adults == 02 }">selected="selected"</c:if>>02</option>
										<option value="03"
											<c:if test="${check.adults == 03 }">selected="selected"</c:if>>03</option>
										<option value="04"
											<c:if test="${check.adults == 04 }">selected="selected"</c:if>>04</option>
										<option value="05"
											<c:if test="${check.adults == 05 }">selected="selected"</c:if>>05</option>
										<option value="06"
											<c:if test="${check.adults == 06 }">selected="selected"</c:if>>06</option>
									</select>
								</div>
								<div class="col-6">
									<select name="children" id="children" class="form-control">
										<option value="00">Children</option>
										<option value="01"
											<c:if test="${check.children == 01 }">selected="selected"</c:if>>01</option>
										<option value="02"
											<c:if test="${check.children == 02 }">selected="selected"</c:if>>02</option>
										<option value="03"
											<c:if test="${check.children == 03 }">selected="selected"</c:if>>03</option>
										<option value="04"
											<c:if test="${check.children == 04 }">selected="selected"</c:if>>04</option>
										<option value="05"
											<c:if test="${check.children == 05 }">selected="selected"</c:if>>05</option>
										<option value="06"
											<c:if test="${check.children == 06 }">selected="selected"</c:if>>06</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group mb-30">
							<label for="guests">Price (vnd)</label>
							<div class="row">
								<div class="col-6">
									<select name="min_price" id="guests" class="form-control">
										<option value="0">Min</option>
										<option value="500000"
											<c:if test="${check.min_price == 500000 }">selected="selected"</c:if>>500.000</option>
										<option value="1000000"
											<c:if test="${check.min_price == 1000000 }">selected="selected"</c:if>>1.000.000</option>
										<option value="1500000"
											<c:if test="${check.min_price == 1500000 }">selected="selected"</c:if>>1.500.000</option>
										<option value="2000000"
											<c:if test="${check.min_price == 2000000 }">selected="selected"</c:if>>2.000.000</option>
									</select>
								</div>
								<div class="col-6">
									<select name="max_price" id="children" class="form-control">
										<option value="10000000">Max</option>
										<option value="2500000"
											<c:if test="${check.max_price == 2500000 }">selected="selected"</c:if>>2.500.000</option>
										<option value="3000000"
											<c:if test="${check.max_price == 3000000 }">selected="selected"</c:if>>3.000.000</option>
										<option value="5000000"
											<c:if test="${check.max_price == 5000000 }">selected="selected"</c:if>>5.000.000</option>
										<option value="7000000"
											<c:if test="${check.max_price == 7000000 }">selected="selected"</c:if>>7.000.000</option>
									</select>
								</div>
							</div>
						</div>

						<div class="form-group">
							<button type="submit" class="btn roberto-btn w-100">Check</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Rooms Area End -->

<!-- Call To Action Area Start -->
<section class="roberto-cta-area">
	<div class="container">
		<div class="cta-content bg-img bg-overlay jarallax"
			style="background-image: url(img/bg-img/1.jpg);">
			<div class="row align-items-center">
				<div class="col-12 col-md-7">
					<div class="cta-text mb-50">
						<h2>Contact us now!</h2>
						<h6>Contact (+12) 345-678-9999 to book directly or for advice</h6>
					</div>
				</div>
				<div class="col-12 col-md-5 text-right">
					<a href="#" class="btn roberto-btn mb-50">Contact Now</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Call To Action Area End -->

<!-- Partner Area Start -->
<div class="partner-area">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div
					class="partner-logo-content d-flex align-items-center justify-content-between wow fadeInUp"
					data-wow-delay="300ms">
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img src="img/core-img/p1.png"
						alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img src="img/core-img/p2.png"
						alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img src="img/core-img/p3.png"
						alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img src="img/core-img/p4.png"
						alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img src="img/core-img/p5.png"
						alt=""></a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Partner Area End -->