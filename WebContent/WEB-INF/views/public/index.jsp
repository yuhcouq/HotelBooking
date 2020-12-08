<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!-- Welcome Area Start -->
<section class="welcome-area">
	<div class="welcome-slides owl-carousel">
		<c:forEach items="${listSlide}" var="slide">
			<!-- Single Welcome Slide -->
			<div class="single-welcome-slide bg-img bg-overlay"
				style="background-image: url(${pageContext.request.contextPath }/uploads/${slide.image});"
				data-img-url="${pageContext.request.contextPath }/uploads/${slide.image}">
				<!-- Welcome Content -->
				<div class="welcome-content h-100">
					<div class="container h-100">
						<div class="row h-100 align-items-center">
							<!-- Welcome Text -->
							<div class="col-12">
								<div class="welcome-text text-center">
									<h6 data-animation="fadeInLeft" data-delay="200ms">Hotel
										&amp; Resort</h6>
									<h2 data-animation="fadeInLeft" data-delay="500ms">Welcome
										To Luxury Booking</h2>
									<a href="#" class="btn roberto-btn btn-2"
										data-animation="fadeInLeft" data-delay="800ms">Discover
										Now</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<!-- Welcome Area End -->

<!-- About Us Area Start -->
<section class="roberto-about-area section-padding-100-0">
	<!-- Hotel Search Form Area -->
	<div class="hotel-search-form-area">
		<div class="container-fluid">
			<div class="hotel-search-form">
				<form
					action="${pageContext.request.contextPath }/public/check_index"
					method="post">
					<div class="row justify-content-between align-items-end">
						<div class="col-6 col-md-2 col-lg-3">
							<label for="checkIn">checkIn</label> <input type="date"
								class="form-control" id="checkin" name="checkin">
						</div>
						<div class="col-6 col-md-2 col-lg-3">
							<label for="checkOut">checkOut</label> <input type="date"
								class="form-control" id="checkout" name="checkout">
						</div>
						<div class="col-4 col-md-1">
							<label for="adults">adults</label> <select name="adults"
								id="adults" class="form-control">
								<option value="00">-</option>
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
							</select>
						</div>
						<div class="col-4 col-md-2 col-lg-1">
							<label for="children">children</label> <select name="children"
								id="children" class="form-control">
								<option value="00">-</option>
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
							</select>
						</div>
						<div class="col-12 col-md-3">
							<button type="submit" class="form-control btn roberto-btn w-100">Check</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="container mt-100">
		<div class="row align-items-center">
			<div class="col-12 col-lg-6">
				<!-- Section Heading -->
				<div class="section-heading wow fadeInUp" data-wow-delay="100ms">
					<h6>About the Luxury Hotel</h6>
					<h2>
						Welcome to <br>Luxury Hotel
					</h2>
				</div>
				<div class="about-us-content mb-100">
					<h5 class="wow fadeInUp" data-wow-delay="300ms">With a large hotel system, Luxury Hotel confidently can provide you with the right hotels according to your requirements. Wherever you are, Luxury will provide you. .</h5>
					<p class="wow fadeInUp" data-wow-delay="400ms"
						style="font-family: Trio; font-weight: bold;">
						Manager: <span>Quốc Huy</span>
					</p>
				</div>
			</div>

			<div class="col-12 col-lg-6">
				<div class="about-us-thumbnail mb-100 wow fadeInUp"
					data-wow-delay="700ms">
					<div class="row no-gutters">
						<div class="col-6">
							<div class="single-thumb">
								<img src="${defines.urlPublic}/img/bg-img/13.jpg" alt="">
							</div>
							<div class="single-thumb">
								<img src="${defines.urlPublic}/img/bg-img/14.jpg" alt="">
							</div>
						</div>
						<div class="col-6">
							<div class="single-thumb">
								<img src="${defines.urlPublic}/img/bg-img/15.jpg" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- About Us Area End -->

<!-- Service Area Start -->
<div class="roberto-service-area">
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div
					class="service-content d-flex align-items-center justify-content-between">
					<!-- Single Service Area -->
					<div class="single-service--area mb-100 wow fadeInUp"
						data-wow-delay="100ms">
						<img src="${defines.urlPublic}/img/core-img/icon-1.png" alt="">
						<h5>Transportion</h5>
					</div>

					<!-- Single Service Area -->
					<div class="single-service--area mb-100 wow fadeInUp"
						data-wow-delay="300ms">
						<img src="${defines.urlPublic}/img/core-img/icon-2.png" alt="">
						<h5>Reiseservice</h5>
					</div>

					<!-- Single Service Area -->
					<div class="single-service--area mb-100 wow fadeInUp"
						data-wow-delay="500ms">
						<img src="${defines.urlPublic}/img/core-img/icon-3.png" alt="">
						<h5>Spa Relaxtion</h5>
					</div>

					<!-- Single Service Area -->
					<div class="single-service--area mb-100 wow fadeInUp"
						data-wow-delay="700ms">
						<img src="${defines.urlPublic}/img/core-img/icon-4.png" alt="">
						<h5>Restaurant</h5>
					</div>

					<!-- Single Service Area -->
					<div class="single-service--area mb-100 wow fadeInUp"
						data-wow-delay="900ms">
						<img src="${defines.urlPublic}/img/core-img/icon-1.png" alt="">
						<h5>Bar &amp; Drink</h5>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Service Area End -->

<!-- Our Room Area Start -->
<section class="roberto-rooms-area">
	<div class="row">
				<!-- Section Heading -->
				<div class="col-12">
					<div class="section-heading text-center wow fadeInUp"
						data-wow-delay="100ms">
						<h2>Room is suggested for you</h2>
					</div>
				</div>
			</div>
	<div class="rooms-slides owl-carousel">
		<c:forEach items="${listRoomTop10}" var="room">
			<!-- Single Room Slide -->
			<div class="single-room-slide d-flex align-items-center">
				<!-- Thumbnail -->
				<div class="room-thumbnail h-100 bg-img"
					style="background-image: url(${pageContext.request.contextPath }/uploads/${room.image});"></div>
				<!-- Content -->
				<div class="room-content">
					<h2 data-animation="fadeInUp" data-delay="100ms">${room.name}<br>
						<span style="font-size: 15px;">Thành phố : </span><span
							style="font-size: 25px; color: #1CC3B2;">${room.city_name}</span>
					</h2>
					<h3 data-animation="fadeInUp" data-delay="300ms">
						${defines.formatNumber(room.price)} <span>vnd / Ngày</span>
					</h3>
					<ul class="room-feature" data-animation="fadeInUp"
						data-delay="500ms">
						<li><span><i class="fa fa-check"></i> Diện tích</span> <span>:
								${defines.formatNumber(room.acreage)} m2</span></li>
						<c:set var="size"
							value="${room.adult_number + room.children_number}"></c:set>
						<li><span><i class="fa fa-check"></i> Số lượng</span> <span>:
								${size} Người</span></li>
						<li><span><i class="fa fa-check"></i> Giường</span> <span>:
								${room.bed_number}</span></li>
						<li><span><i class="fa fa-check"></i> Dịch vụ</span> <span>:
								Wifi, TV . . .</span></li>
					</ul>
					<a
						href="${pageContext.request.contextPath }/public/single_room/${room.id_room}"
						class="btn roberto-btn mt-30" data-animation="fadeInUp"
						data-delay="700ms">View Details</a>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<!-- Our Room Area End -->

<!-- Testimonials Area Start -->
<section class="roberto-testimonials-area section-padding-100-0">
	<div class="container">
		<div class="row align-items-center">
			<div class="col-12 col-md-6">
				<div class="testimonial-thumbnail owl-carousel mb-100">
					<img src="${defines.urlPublic}/img/bg-img/10.jpg" alt=""> <img
						src="${defines.urlPublic}/img/bg-img/11.jpg" alt="">
				</div>
			</div>

			<div class="col-12 col-md-6">
				<!-- Section Heading -->
				<div class="section-heading">
					<h6>Testimonials</h6>
					<h2>Our Guests Love Us</h2>
				</div>
				<!-- Testimonial Slide -->
				<div class="testimonial-slides owl-carousel mb-100">

					<!-- Single Testimonial Slide -->
					<div class="single-testimonial-slide">
						<h5>“This can be achieved by applying search engine
							optimization or popularly known as SEO. This is a marketing
							strategy which increases the quality and quantity of traffic flow
							to a particular website via search engines.”</h5>
						<div class="rating-title">
							<div class="rating">
								<i class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i>
							</div>
							<h6>
								Robert Downey <span>- CEO Deercreative</span>
							</h6>
						</div>
					</div>

					<!-- Single Testimonial Slide -->
					<div class="single-testimonial-slide">
						<h5>“Lorem ipsum dolor sit amet, consectetur adipisicing
							elit. Necessitatibus delectus facilis molestias, error vitae
							praesentium quos eaque qui ea, tempore blanditiis sint
							reprehenderit, quaerat. Commodi ab architecto sit suscipit
							exercitationem!”</h5>
						<div class="rating-title">
							<div class="rating">
								<i class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i>
							</div>
							<h6>
								Akash Downey <span>- CEO Deercreative</span>
							</h6>
						</div>
					</div>

					<!-- Single Testimonial Slide -->
					<div class="single-testimonial-slide">
						<h5>“Lorem ipsum dolor sit amet, consectetur adipisicing
							elit. Dolor, ex quos. Alias a rem maiores, possimus dicta sit
							distinctio quis iusto!”</h5>
						<div class="rating-title">
							<div class="rating">
								<i class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i>
							</div>
							<h6>
								Downey Sarah <span>- CEO Deercreative</span>
							</h6>
						</div>
					</div>

					<!-- Single Testimonial Slide -->
					<div class="single-testimonial-slide">
						<h5>“Lorem ipsum dolor sit amet, consectetur adipisicing
							elit. Labore sequi laboriosam fugit suscipit aspernatur, minima
							minus voluptatum, id ab atque similique ex earum. Magni.”</h5>
						<div class="rating-title">
							<div class="rating">
								<i class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i> <i class="icon_star"></i> <i
									class="icon_star"></i>
							</div>
							<h6>
								Robert Brown <span>- CEO Deercreative</span>
							</h6>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</section>
<!-- Testimonials Area End -->

<!-- Projects Area Start -->
<section class="roberto-project-area">
	<!-- Projects Slide -->
	<div class="row">
			<!-- Section Heading -->
			<div class="col-12">
				<div class="section-heading text-center wow fadeInUp"
					data-wow-delay="100ms">
					<h2>Hot Rooms</h2>
				</div>
			</div>
		</div>
	<div class="projects-slides owl-carousel">
		<!-- Single Project Slide -->
		<c:forEach items="${listRoomTopHot10}" var="room">
			<div class="single-project-slide active bg-img"
				style="background-image: url(${pageContext.request.contextPath }/uploads/${room.image});">
				<!-- Project Text -->
				<div class="project-content">
					<div class="text">
						<h6>${room.name}</h6>
						<h5>${defines.formatNumber(room.price)}
							<span>vnd / Ngày</span>
						</h5>
					</div>
				</div>
				<!-- Hover Effects -->
				<div class="hover-effects">
					<div class="text">
						<h6>${room.name}</h6>
						<h5>${defines.formatNumber(room.price)}
							<span>vnd / Ngày</span>
						</h5>
						<p>${room.detail}</p>
					</div>
					<a
						href="${pageContext.request.contextPath }/public/single_room/${room.id_room}"
						class="btn project-btn">Views detail <i
						class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
				</div>
			</div>
		</c:forEach>
	</div>
</section>
<!-- Projects Area End -->

<!-- Blog Area Start -->
<section class="roberto-blog-area section-padding-100-0">
	<div class="container">
		<div class="row">
			<!-- Section Heading -->
			<div class="col-12">
				<div class="section-heading text-center wow fadeInUp"
					data-wow-delay="100ms">
					<h6>Our Blog</h6>
					<h2>Latest News &amp; Event</h2>
				</div>
			</div>
		</div>

		<div class="row">
			<!-- Single Post Area -->
			<c:forEach items="${listRoomTopreate10}" var="room">
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
<!-- Blog Area End -->

<!-- Call To Action Area Start -->
<%-- <section class="roberto-cta-area">
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
</section> --%>
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