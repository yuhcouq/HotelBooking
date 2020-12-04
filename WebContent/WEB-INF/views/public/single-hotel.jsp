<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!-- Breadcrumb Area Start -->
  <head>
    <title>Add Map</title>
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB0frQA7xZx2sAPhk1-dws7hZEBHuJ7A6o&callback=initMap&libraries=&v=weekly"
      defer
    ></script>
    <style type="text/css">
      /* Set the size of the div element that contains the map */
      #map {
        height: 200px;
        /* The height is 400 pixels */
        width: 30%;
        /* The width is the width of the web page */
      }
    </style>
    <script>
      // Initialize and add the map
      function initMap() {
        // The location of Uluru
        const uluru = { lat: ${hotel.latitude}, lng: ${hotel.longitude} };
        // The map, centered at Uluru
        const map = new google.maps.Map(document.getElementById("map"), {
          zoom: 7,
          center: uluru,
        });
        // The marker, positioned at Uluru
        const marker = new google.maps.Marker({
          position: uluru,
          map: map,
        });
      }
    </script>
  </head>
<div class="breadcrumb-area bg-img bg-overlay jarallax"
	style="background-image: url(${defines.urlPublic}/img/bg-img/16.jpg);">
	<div class="container h-100">
		<div class="row h-100 align-items-end">
			<div class="col-12">
				<div
					class="breadcrumb-content d-flex align-items-center justify-content-between pb-5">
					<h2 class="room-title">${hotel.hotel_name}</h2>
					<%-- <h2 class="room-price">
						${defines.formatNumber(hotel.price)}<span>vnđ / Ngày</span>
					</h2> --%>
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
										src="${pageContext.request.contextPath }/uploads/${hotel.hotel_image}"
										class="d-block w-100" alt="" id="picture-detail-height">
								</div>
								<%-- <c:forEach items="${listImages}" var="image">
									<div class="carousel-item">
										<img
											src="${pageContext.request.contextPath }/uploads/${image.image}"
											class="d-block w-100" alt="" id="picture-detail-height">
									</div>
								</c:forEach> --%>
							</div>

							<ol class="carousel-indicators">
								<li data-target="#room-thumbnail--slide" data-slide-to="0"
									class="active"><img
									src="${pageContext.request.contextPath }/uploads/${hotel.hotel_image}"
									class="d-block w-100" alt="" id="picture-height"></li>
								<c:set var="index" value="1"></c:set>
								<%-- <c:forEach items="${listImages}" var="image">
									<li data-target="#room-thumbnail--slide"
										data-slide-to="${index }"><img
										src="${pageContext.request.contextPath }/uploads/${image.image}"
										class="d-block w-100" alt="" id="picture-height"></li>
									<c:set var="index" value="${index + 1}"></c:set>
								</c:forEach> --%>
							</ol>
						</div>
					</div>

					<!-- Room Features -->
					<%-- <div class="room-features-area d-flex flex-wrap mb-50">
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
					</div> --%>
					
					<div style ="display: flex">
						<div style = "float :left">
						Chất lượng
						<div class="reviwer-rating"
							style="color: #FACA51; font-size: 30px;">
							<c:choose>
								<c:when test="${ hotel.rating > 4.7}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
								</c:when>
								<c:when test="${hotel.rating > 4.3}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star-half-o"></i>
									<!-- <i class="fa fa-star-half-o"></i>
													<i class="fa fa-star-o"></i> -->
								</c:when>
								<c:when test="${hotel.rating > 3.7}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star-o"></i>
								</c:when>
								<c:when test="${hotel.rating > 3.3}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star-half-o"></i>
									<i class="fa fa-star-o"></i>
								</c:when>
								<c:when test="${hotel.rating > 2.7}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star-o"></i>
									<i class="fa fa-star-o"></i>
								</c:when>
								<c:when test="${hotel.rating > 2.3}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star-half-o"></i>
									<i class="fa fa-star-o"></i>
									<i class="fa fa-star-o"></i>
								</c:when>
								<c:when test="${hotel.rating > 1.7}">
									<i class="fa fa-star"></i>
									<i class="fa fa-star"></i>
									<i class="fa fa-star-o"></i>
									<i class="fa fa-star-o"></i>
									<i class="fa fa-star-o"></i>
								</c:when>
								<c:when test="${hotel.rating > 1.3}">
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
						<h4>${hotel.hotel_name}</h4>
						<p>${hotel.detail}</p>
						<p>${hotel.description}</p>
						</div>
						<div id="map"  style = "margin-left: 100px;"></div>
					</div>
					
					
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
					<div class="" style="width: 100%">
						<c:if test="${not empty error}">
							<div class='alert alert-danger' role='alert'>${error}</div>
						</c:if>
						<c:if test="${not empty success}">
							<div class='alert alert-success' role='alert'>${success}</div>
						</c:if>
						<form
							action="${pageContext.request.contextPath}/public/reviews/${hotel.id_hotel}/${userPublic.id_user}"
							class="billing-form ftco-bg-dark p-3" id="infuser"
							method="post">
							<h3 class="mb-4 billing-heading" style="text-align: center;">Đánh
								giá chất lượng</h3>
							<div class="row align-items-end" style="width:100%;margin: auto;">
								<div class="">
									<div class="form-group">
										<label for="firstname">Tên người đánh giá </label> <input
											type="text" class="form-control" name="username"
											placeholder="Vui lòng nhập tên tài khoản" required="required"
											value="${userPublic.firstname}" disabled="disabled">
									</div>
								</div>
								<div class="">
									<div class="form-group">
										<label for="firstname">Chất lượng </label>
										<div class="reviwer-rating">
											<i class="fa fa-star" style="color: #FACA51; font-size: 30px;"
												onclick="danhgiachatluong(1);" id="motsao"></i> <i
												class="fa fa-star" style="color: #FACA51; font-size: 30px;"
												onclick="danhgiachatluong(2);" id="haisao"></i> <i
												class="fa fa-star" style="color: #FACA51; font-size: 30px;"
												onclick="danhgiachatluong(3);" id="basao"></i> <i
												class="fa fa-star" style="color: #FACA51; font-size: 30px;"
												onclick="danhgiachatluong(4);" id="bonsao"></i> <i
												class="fa fa-star" style="color: #FACA51; font-size: 30px;"
												onclick="danhgiachatluong(5);" id="namsao"></i>
											<!-- <i class="fa fa-star"
												style="color: #FFCF00; font-size: 30px;"
												onclick="danhgiachatluong(5);"></i> -->
										</div>
									</div>
								</div>
								<div class="" style="width:100%">
									<div class="form-group">
										<label for="message">Nội dung đánh giá </label>
										<textarea rows="5" cols="85" name="content"
											style="border: 1px solid #CED4DA; width: 100%;"></textarea>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" name="rating"
											placeholder="" required="required" value=""
											style="display: none;" id="chatluong">
									</div>
								</div>
							</div>
							<!-- END -->
							<div class="col-md-6">
								<c:if test="${not empty userPublic}">
									<c:if test="${checkUserBooking != 0 }">
										<p>
											<input type="submit" class="btn btn-primary py-3 px-4"
												value="Đánh giá"
												style="border-radius: 5px; background-color: #1CC3B2; border: 1px solid #1CC3B2;" />
										</p>
									</c:if>
									<c:if test="${checkUserBooking == 0 }">
										<p style="color:red;width: 350px;text-align: center">Bạn chưa đặt phòng của khách sạn này</p>
									</c:if>
								</c:if>
								<c:if test="${empty userPublic}">
									<p style="color:red;width: 350px;text-align: center">Bạn chưa đăng nhập</p>
								</c:if>
							</div>
						</form>
						<script type="text/javascript">
							function danhgiachatluong(soluong) {
								if (soluong == 1) {
									document.getElementById("motsao").style.color = "#FACA51";
									document.getElementById("haisao").style.color = "#EFF0F5";
									document.getElementById("basao").style.color = "#EFF0F5";
									document.getElementById("bonsao").style.color = "#EFF0F5";
									document.getElementById("namsao").style.color = "#EFF0F5";
									document.getElementById("chatluong").value = 1;
								} else if (soluong == 2) {
									document.getElementById("motsao").style.color = "#FACA51";
									document.getElementById("haisao").style.color = "#FACA51";
									document.getElementById("basao").style.color = "#EFF0F5";
									document.getElementById("bonsao").style.color = "#EFF0F5";
									document.getElementById("namsao").style.color = "#EFF0F5";
									document.getElementById("chatluong").value = 2;
								} else if (soluong == 3) {
									document.getElementById("motsao").style.color = "#FACA51";
									document.getElementById("haisao").style.color = "#FACA51";
									document.getElementById("basao").style.color = "#FACA51";
									document.getElementById("bonsao").style.color = "#EFF0F5";
									document.getElementById("namsao").style.color = "#EFF0F5";
									document.getElementById("chatluong").value = 3;
								} else if (soluong == 4) {
									document.getElementById("motsao").style.color = "#FACA51";
									document.getElementById("haisao").style.color = "#FACA51";
									document.getElementById("basao").style.color = "#FACA51";
									document.getElementById("bonsao").style.color = "#FACA51";
									document.getElementById("namsao").style.color = "#EFF0F5";
									document.getElementById("chatluong").value = 4;
								} else if (soluong == 5) {
									document.getElementById("motsao").style.color = "#FACA51";
									document.getElementById("haisao").style.color = "#FACA51";
									document.getElementById("basao").style.color = "#FACA51";
									document.getElementById("bonsao").style.color = "#FACA51";
									document.getElementById("namsao").style.color = "#FACA51";
									document.getElementById("chatluong").value = 5;
								}
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
			<c:forEach items="${listHotelTop9}" var="hotel9">
				<div class="col-12 col-md-6 col-lg-4">
					<div class="single-post-area mb-100 wow fadeInUp"
						data-wow-delay="300ms">
						<a href="#" class="post-thumbnail"><img
							src="${pageContext.request.contextPath }/uploads/${hotel9.hotel_image}"
							alt=""></a>
						<!-- Post Meta -->
						<div class="post-meta">
							<%-- <a href="#" class="post-date">${room.CreatedAt}</a> <a href="#"
								class="post-catagory">Event</a> --%>
						</div>
						<!-- Post Title -->
						<a href="#" class="post-title">${hotel9.hotel_name}</a>
						<p>${hotel9.detail}</p>
						<a
							href="${pageContext.request.contextPath }/public/single_hotel/${hotel9.id_hotel}"
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