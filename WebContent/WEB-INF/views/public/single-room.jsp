<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!-- Breadcrumb Area Start -->
<div class="breadcrumb-area bg-img bg-overlay jarallax"
	style="background-image: url(${defines.urlPublic}/img/bg-img/16.jpg);">
	<div class="container h-100">
		<div class="row h-100 align-items-end">
			<div class="col-12">
				<div
					class="breadcrumb-content d-flex align-items-center justify-content-between pb-5">
					<h2 class="room-title">${room.hotel_name}</h2>
					<h2 class="room-price">
						${defines.formatNumber(room.price)}<span>vnđ / Ngày</span>
					</h2>
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
				<!-- Single Room Details Area -->
				<div class="single-room-details-area mb-50">
					<!-- Room Thumbnail Slides -->
					<div class="room-thumbnail-slides mb-50">
						<div id="room-thumbnail--slide" class="carousel slide"
							data-ride="carousel">
							<div class="carousel-inner">
								<div class="carousel-item active">
									<img
										src="${pageContext.request.contextPath }/uploads/${room.image}"
										class="d-block w-100" alt="" id="picture-detail-height">
								</div>
								<c:forEach items="${listImages}" var="image">
									<div class="carousel-item">
										<img
											src="${pageContext.request.contextPath }/uploads/${image.image}"
											class="d-block w-100" alt="" id="picture-detail-height">
									</div>
								</c:forEach>
							</div>

							<ol class="carousel-indicators">
								<li data-target="#room-thumbnail--slide" data-slide-to="0"
									class="active"><img
									src="${pageContext.request.contextPath }/uploads/${room.image}"
									class="d-block w-100" alt="" id="picture-height"></li>
								<c:set var="index" value="1"></c:set>
								<c:forEach items="${listImages}" var="image">
									<li data-target="#room-thumbnail--slide"
										data-slide-to="${index }"><img
										src="${pageContext.request.contextPath }/uploads/${image.image}"
										class="d-block w-100" alt="" id="picture-height"></li>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach>
							</ol>
						</div>
					</div>

					<!-- Room Features -->
					<div class="room-features-area d-flex flex-wrap mb-50">
						<h6>
							Diện tích <span>${defines.formatNumber(room.acreage)} m2</span>
						</h6>
						<h6>
							Người lớn <span> ${room.adult_number} </span>
						</h6>
						<h6>
							Trẻ em <span>${room.children_number}</span>
						</h6>
						<h6>
							Giường <span>${room.bed_number}</span>
						</h6>
					</div>
					Chất lượng
					<div class="reviwer-rating"
						style="color: #FACA51; font-size: 30px;">
						<c:choose>
							<c:when test="${ room.rating > 4.7}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
							</c:when>
							<c:when test="${room.rating > 4.3}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-half-o"></i>
								<!-- <i class="fa fa-star-half-o"></i>
												<i class="fa fa-star-o"></i> -->
							</c:when>
							<c:when test="${room.rating > 3.7}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o"></i>
							</c:when>
							<c:when test="${room.rating > 3.3}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-half-o"></i>
								<i class="fa fa-star-o"></i>
							</c:when>
							<c:when test="${room.rating > 2.7}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o"></i>
								<i class="fa fa-star-o"></i>
							</c:when>
							<c:when test="${room.rating > 2.3}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-half-o"></i>
								<i class="fa fa-star-o"></i>
								<i class="fa fa-star-o"></i>
							</c:when>
							<c:when test="${room.rating > 1.7}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star"></i>
								<i class="fa fa-star-o"></i>
								<i class="fa fa-star-o"></i>
								<i class="fa fa-star-o"></i>
							</c:when>
							<c:when test="${room.rating > 1.3}">
								<i class="fa fa-star"></i>
								<i class="fa fa-star-half-o"></i>
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
					<h4>${room.name}</h4>
					<p>${room.detail}</p>
					<p>${room.description}</p>
				</div>

				<!-- Room Service -->
				<div class="room-service mb-50">
					<h4>Dịch Vụ</h4>
					<ul>
						<c:choose>
							<c:when test="${wifi == 1}">
								<li><img src="${defines.urlPublic}/img/core-img/icon5.png"
									alt=""> Wifi Free</li>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${television == 1}">
								<li><img src="${defines.urlPublic}/img/core-img/icon4.png"
									alt=""> Tivi</li>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${conditioning == 1}">
								<li><img src="${defines.urlPublic}/img/core-img/icon1.png"
									alt=""> Điều hòa</li>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${drinks == 1}">
								<li><img src="${defines.urlPublic}/img/core-img/icon2.png"
									alt=""> Nước uống</li>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${restaurant == 1}">
								<li><img src="${defines.urlPublic}/img/core-img/icon3.png"
									alt=""> Nhà hàng</li>
							</c:when>
						</c:choose>
						<li><img src="${defines.urlPublic}/img/core-img/icon6.png"
							alt=""> Dịch vụ 24/24</li>
					</ul>
				</div>

				<!-- Room Review -->
				<div class="room-review-area mb-100">
					<h4>Đánh Giá</h4>
					<c:choose>
						<c:when test="${empty listReviews}">
							<li>Không có đánh giá nào.</li>
						</c:when>
					</c:choose>
					<c:forEach items="${listReviews}" var="review">
						<!-- Single Review Area -->
						<div class="single-room-review-area d-flex align-items-center">
							<div class="reviwer-thumbnail">
								<img
									src="${pageContext.request.contextPath }/uploads/${review.avatar}"
									alt="">
							</div>
							<div class="reviwer-content">
								<div
									class="reviwer-title-rating d-flex align-items-center justify-content-between">
									<div class="reviwer-title" style="width: 170px;">
										<span>${review.create_time}</span>
										<h6>${review.lastname}${review.firstname}</h6>
									</div>
									<div class="reviwer-rating" style="margin-left: 340px;">
										<c:choose>
											<c:when test="${review.rating == 5}">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<!-- <i class="fa fa-star-half-o"></i>
												<i class="fa fa-star-o"></i> -->
											</c:when>
											<c:when test="${review.rating == 4}">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star-o"></i>
											</c:when>
											<c:when test="${review.rating == 3}">
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star"></i>
												<i class="fa fa-star-o"></i>
												<i class="fa fa-star-o"></i>
											</c:when>
											<c:when test="${review.rating == 2}">
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
								</div>
								<p>${review.content}</p>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<div class="col-12 col-lg-4">
				<!-- Hotel Reservation Area -->
				<!-- <div id="message_not_date"></div> -->
				<c:if test="${not empty msg}">
					${msg}
				</c:if>
				<div class="hotel-reservation--area mb-100">
					<form action="#" method="post">
						<div class="form-group mb-30">
							<label for="checkInDate">Date</label>
							<div class="input-daterange" id="datepicker">
								<div class="row no-gutters">
									<div class="col-6">
										<input type="text" class="input-small form-control" name=""
											id="checkInDateTemplate" placeholder="Check In">
									</div>
									<div class="col-6">
										<input type="text" class="input-small form-control" name=""
											id="checkOutDateTemplate" placeholder="Check Out">
									</div>
								</div>
							</div>
						</div>
					</form>
					<form
						action="${pageContext.request.contextPath }/public/booking/add/${room.id_room}"
						method="post" style="float: left;">
						<div class="form-group mb-30" style="display: none;">
							<label for="checkInDate">Date</label>
							<div class="input-daterange" id="datepicker">
								<div class="row no-gutters">
									<div class="col-6">
										<input type="text" class="input-small form-control"
											name="checkin" id="checkInDateAdd" placeholder="Check In">
									</div>
									<div class="col-6">
										<input type="text" class="input-small form-control"
											name="checkout" id="checkOutDateAdd" placeholder="Check Out">
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<input type="submit" value="Add" class="btn roberto-btn mt-30"
								onclick="setDateAdd();" />
						</div>
					</form>
					<form
						action="${pageContext.request.contextPath }/public/booking/booknow/${room.id_room}"
						method="post" style="float: right;">
						<div class="form-group mb-30" style="display: none;">
							<label for="checkInDate">Date</label>
							<div class="input-daterange" id="datepicker">
								<div class="row no-gutters">
									<div class="col-6">
										<input type="text" class="input-small form-control"
											name="checkin" id="checkInDateBook" placeholder="Check In">
									</div>
									<div class="col-6">
										<input type="text" class="input-small form-control"
											name="checkout" id="checkOutDateBook" placeholder="Check Out">
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<input type="submit" value="Book now"
								class="btn roberto-btn mt-30" onclick="setDateBook();" />
						</div>
					</form>

					<script>
						function setDateAdd() {
							document.getElementById("checkInDateAdd").value = document
									.getElementById("checkInDateTemplate").value;
							document.getElementById("checkOutDateAdd").value = document
									.getElementById("checkOutDateTemplate").value;
						}
						function setDateBook() {
							document.getElementById("checkInDateBook").value = document
									.getElementById("checkInDateTemplate").value;
							document.getElementById("checkOutDateBook").value = document
									.getElementById("checkOutDateTemplate").value;
						}
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Rooms Area End -->
<section class="roberto-blog-area section-padding-100-0">
	<div class="container">
		<div class="row">
			<!-- Section Heading -->
			<div class="col-12">
				<div class="section-heading text-center wow fadeInUp"
					data-wow-delay="100ms">
					<h6></h6>
					<h2>Khách sạn liên quan</h2>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Single Post Area -->
			<c:forEach items="${listRoomTop9}" var="room">
				<div class="col-12 col-md-6 col-lg-4">
					<div class="single-post-area mb-100 wow fadeInUp"
						data-wow-delay="300ms">
						<a href="#" class="post-thumbnail"><img
							src="${pageContext.request.contextPath }/uploads/${room.image}"
							alt=""></a>
						<!-- Post Meta -->
						<div class="post-meta">
							<%-- <a href="#" class="post-date">${room.CreatedAt}</a> <a href="#"
								class="post-catagory">Event</a> --%>
						</div>
						<!-- Post Title -->
						<a href="#" class="post-title">${room.name}</a>
						<p>${room.detail}</p>
						<a
							href="${pageContext.request.contextPath }/public/single_room/${room.id_room}"
							class="btn continue-btn"><i class="fa fa-long-arrow-right"
							aria-hidden="true"></i></a>
					</div>
				</div>

			</c:forEach>

		</div>
	</div>
</section>

<!-- Call To Action Area Start -->
<section class="roberto-cta-area">
	<div class="container">
		<div class="cta-content bg-img bg-overlay jarallax"
			style="background-image: url(${defines.urlPublic}/img/bg-img/1.jpg);">
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
					<a href="#" class="partner-logo"><img
						src="${defines.urlPublic}/img/core-img/p1.png" alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img
						src="${defines.urlPublic}/img/core-img/p2.png" alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img
						src="${defines.urlPublic}/img/core-img/p3.png" alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img
						src="${defines.urlPublic}/img/core-img/p4.png" alt=""></a>
					<!-- Single Partner Logo -->
					<a href="#" class="partner-logo"><img
						src="${defines.urlPublic}/img/core-img/p5.png" alt=""></a>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Partner Area End -->