<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!-- Breadcrumb Area Start -->
<div class="breadcrumb-area bg-img bg-overlay jarallax"
	style="background-image: url(${pageContext.request.contextPath }/templates/public/img/bg-img/16.jpg);">
	<div class="container h-100">
		<div class="row h-100 align-items-center">
			<div class="col-12">
				<div class="breadcrumb-content text-center">
					<h2 class="page-title">Our Hotel</h2>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb justify-content-center">
							<li class="breadcrumb-item"><a href="index.html">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Hotel</li>
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
					<c:when test="${empty listHotels}">
						<p>Không có khách sạn nào.</p>
					</c:when>
				</c:choose>
				<c:forEach items="${listHotels}" var="hotel">
					<div
						class="single-room-area d-flex align-items-center mb-50 wow fadeInUp"
						data-wow-delay="100ms">
						<!-- Room Thumbnail -->
						<div class="room-thumbnail">
							<img
								src="${pageContext.request.contextPath }/uploads/${hotel.hotel_image}"
								alt="" id="picture-room-detail" style="height: 230px;">
						</div>
						<!-- Room Content -->
						<div class="room-content">
							<h3>${hotel.hotel_name}</h3>
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
								href="${pageContext.request.contextPath }/public/hotels/${i}">${i}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath }/public/hotels/${sumPage}">End
								<i class="fa fa-angle-right"></i>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
<!-- Rooms Area End -->
</div>
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