<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!-- Breadcrumb Area Start -->
<head>
<style type="text/css">
	.find-city{
		position: relative;
	    z-index: 100;
	    width: 100%;
	    -webkit-transition-duration: 500ms;
	    -o-transition-duration: 500ms;
	    transition-duration: 500ms;
	    background-color: #1cc3b2;
	    margin-bottom: 30px;
	}
	.find-city input{
	    background-color: transparent;
	    width: 100%;
	    height: 60px;
	    border: none;
	    font-size: 14px;
	    color: #ffffff;
	}
	.submit-search{
		    position: absolute;
	    width: 60px;
	    height: 60px;
	    top: 0;
	    right: 0;
	    z-index: 10;
	    border: none;
	    background-color: transparent;
	    cursor: pointer;
	    font-size: 18px;
	    color: #ffffff;
	    text-align: right;
	}
	.list-hotel-city::-webkit-scrollbar { 
    	display: none; 
	}
	.list-hotel-city:hover{
		background-color: #afb4bf;
		
	}
</style>

<script  type="text/javascript">
	$(window).on('click',function(){
		$(".list-hotel-city").hide();
	});
	
	$(function(){
		
		
		$("#searchcity").on('keyup',function(){
			$(".list-hotel-city").html('');
			var value = $(this).val().toUpperCase();
			$.ajax({
		        url : "${pageContext.request.contextPath }/public/listcity", 
		        type : "GET", 
		        dataType:"json", 
		        success : function (result){
					if(value!=''){
						$.each(result,function(i,val){
							if(val.city_name.toUpperCase().indexOf(value)!=-1){
								var t = '<li class="li-hotel"><a class="hotel" href="#" data-id="'+val.id_city+'">'+val.city_name+'</a></li>';
								$(".list-hotel-city").append(t);
							} 
						});
						$(".hotel").on("click",function(){
							$("#searchcity").val($(this).html());
							$(".find-city").find("form").attr('action',"${pageContext.request.contextPath }/public/hotelcitys/"+$(this).data("id"));
						});
						$(".list-hotel-city").show();
					}
		        }
		    });
			
		});
	 
	});
	
</script>
	
</head>
<div class="breadcrumb-area bg-img bg-overlay jarallax"
	style="background-image: url(${pageContext.request.contextPath }/templates/public/img/bg-img/16.jpg);">
	<div class="container h-100">
		<div class="row h-100 align-items-center">
			<div class="col-12">
				<div class="breadcrumb-content text-center">
					<h2 class="page-title">Our hotel <c:choose>
					<c:when test="${not empty city}">
						in ${city.city_name}
					</c:when>
				</c:choose></h2>
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
				<div class="find-city">
					<div class="search-form d-flex align-items-center">
						<div class="container">
							<form action="${pageContext.request.contextPath }/public/hotelcitys/-1"
								method="post" autocomplete="off">
								<div style="display:block ">
									<div style="margin-bottom:0px">
										<input type="search" name="searchcity" id="searchcity"
										placeholder="tìm kiếm theo thành phố">
									</div>
									
								</div>
								
								<button class="submit-search" type="submit">
									<i class="icon_search"></i>
								</button>
							</form>
							<div>
								<ul class="list-hotel-city" style="border-radius:3%; width:auto; max-height: 200px; background-color:#f8f9fa; margin-top:0px; overflow: scroll;position: absolute;z-index: 2000000 ">
										      
						    	</ul>
							</div>
							
						</div>
					</div>
				</div>
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
						<div>
							<div class="room-content">
								<h3><a href="${pageContext.request.contextPath }/public/single_hotel/${hotel.id_hotel}">${hotel.hotel_name}</a></h3>
							</div>
							<div class="room-content">
								<h4><i class="fa fa-map-marker" style="font-size:24px"></i> ${hotel.address}, ${hotel.city_name}</h4>
							</div>
							<div class="room-content">${hotel.description}</div>
							<div class="room-content" style="padding-top: 20px">
								<a
									href="${pageContext.request.contextPath }/public/rooms/${hotel.id_hotel}"
									class="btn view-detail-btn">View Rooms<i
									class="fa fa-long-arrow-right" aria-hidden="true"></i>
								</a>
							</div>
							
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
								href="<c:choose>
					<c:when test="${empty city_id}">
						${pageContext.request.contextPath }/public/hotels/${i}
					</c:when>
					<c:when test="${not empty city_id}">
						${pageContext.request.contextPath }/public/hotelcitys/${i}/${ city_id}
					</c:when>
				</c:choose>">${i}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link"
							href="<c:choose>
					<c:when test="${empty city_id}">
						${pageContext.request.contextPath }/public/hotels/${sumPage}
					</c:when>
					<c:when test="${not empty city_id}">
						${pageContext.request.contextPath }/public/hotelcitys/${sumPage}/${ city_id}
					</c:when>
				</c:choose>">End
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