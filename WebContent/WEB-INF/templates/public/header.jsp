<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">



<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script  type="text/javascript">
	$(window).on('click',function(){
		$(".list-hotel").hide();
	});
	
	$(function(){
		<c:forEach items="${listRoomTopRe10}" var="room">
			console.log(${room.id_room});
		</c:forEach>
		
		$("#searchFormInput").on('keyup',function(){
			$(".list-hotel").html('');
			var value = $(this).val().toUpperCase();
			$.ajax({
		        url : "${pageContext.request.contextPath }/public/listhotel", 
		        type : "GET", 
		        dataType:"json", 
		        success : function (result){
		        	console.log(result);
					if(value!=''){
						$.each(result,function(i,val){
							if(val.hotel_name.toUpperCase().indexOf(value)!=-1){
								var t = '<li class="li-hotel"><a class="hotel" href="#">'+val.hotel_name+'</a></li>';
								$(".list-hotel").append(t);
							} 
						});
						$(".hotel").on("click",function(){
							$("#searchFormInput").val($(this).html());
						});
						$(".list-hotel").show();
					}
		        }
		    });
			
		});
	 
	});
</script>

<!-- Title -->
<title>Roberto - Hotel &amp; Resort HTML Template</title>

<!-- Favicon -->
<link rel="icon" href="${defines.urlPublic}/img/core-img/favicon.png">

<!-- Stylesheet -->
<link rel="stylesheet" href="${defines.urlPublic}/style.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel='stylesheet prefetch'
	href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/templates/style.css">
<style type="text/css">
.login-html {
	width: 100%;
	height: 100%;
	position: absolute;
	padding: 90px 70px 50px 70px;
	background: rgba(40, 57, 101, .9);
}
.list-hotel::-webkit-scrollbar { 
    display: none; 
}
.li-hotel:hover{
	background-color: #afb4bf;
	
}
.hotel{
	font-size: 14px;
}
</style>
</head>
<body>
	<!-- Preloader -->
	<div id="preloader">
		<div class="loader"></div>
	</div>
	<!-- /Preloader -->

	<!-- Header Area Start -->
	<header class="header-area">
		<!-- Search Form -->
		<div class="search-form d-flex align-items-center">
			<div class="container">
				<form action="${pageContext.request.contextPath }/public/search"
					method="post" autocomplete="off">
					<div style="display:block ">
						<div style="margin-bottom:0px">
							<input type="search" name="search" id="searchFormInput"
							placeholder="Tìm theo khách sạn . . .">
						</div>
						
											</div>
					
					<button type="submit">
						<i class="icon_search"></i>
					</button>
				</form>
				<div>
					<ul class="list-hotel" style="border-radius:3%; width:auto; max-height: 200px; background-color:#f8f9fa; margin-top:0px; overflow: scroll;position: absolute;z-index: 2000000 ">
							      
			    	</ul>
				</div>
				
			</div>
		</div>
		
		
		<!-- Top Header Area Start -->
		<div class="top-header-area">
			<div class="container">
				<div class="row">

					<div class="col-6">
						<div class="top-header-content">
							<a href="#"><i class="icon_phone"></i> <span>(+84) 983800048</span></a> <a href="#"><i class="icon_mail"></i> <span>yuhcouq10@gmail.com</span></a>
						</div>
					</div>

					<div class="col-6">
						<div class="top-header-content">
							<!-- Top Social Area -->
							<div class="top-social-area ml-auto">
								<a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
								<a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
								<a href="#"><i class="fa fa-tripadvisor" aria-hidden="true"></i></a>
								<a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- Top Header Area End -->

		<!-- Main Header Start -->
		<div class="main-header-area">
			<div class="classy-nav-container breakpoint-off">
				<div class="container">
					<!-- Classy Menu -->
					<nav class="classy-navbar justify-content-between" id="robertoNav">

						<!-- Logo -->
						<a class="nav-brand"
							href="${pageContext.request.contextPath }/public/index">
							<%-- <img
							src="${defines.urlPublic}/img/core-img/logo.png" alt=""> --%>
							<h4 style="    font-weight: 900;color: #5ba59e;font-size: 38px;font-style: italic;font-family: cursive;
}">Luxury Hotel</h4>
						</a>

						<!-- Navbar Toggler -->
						<div class="classy-navbar-toggler">
							<span class="navbarToggler"><span></span><span></span><span></span></span>
						</div>

						<!-- Menu -->
						<div class="classy-menu">
							<!-- Menu Close Button -->
							<div class="classycloseIcon">
								<div class="cross-wrap">
									<span class="top"></span><span class="bottom"></span>
								</div>
							</div>
							<!-- Nav Start -->
							<div class="classynav">
								<ul id="nav">
									<li class="active"><a
										href="${pageContext.request.contextPath }/public/index">Home</a></li>
									<li><a href="${pageContext.request.contextPath }/public/hotels">Hotel</a>
										<%-- <ul class="dropdown">
											<c:forEach items="${listHotels}" var="hotel">
												<li><a
													href="${pageContext.request.contextPath }/public/hotel/${hotel.id_hotel}">-
														${hotel.hotel_name}</a></li>
											</c:forEach>
										</ul></li> --%>
									<li><a
										href="${pageContext.request.contextPath }/public/rooms/-1">Rooms</a></li>
									<li><a href="${pageContext.request.contextPath }/public/contact">Contact</a></li>
									<c:choose>
										<c:when test="${not empty userPublic}">
											<li><a href="#">${userPublic.firstname}</a>
												<ul class="dropdown">
													<li><a
														href="${pageContext.request.contextPath }/public/my-booking/${userPublic.id_user}">-
															My booking </a><a
														href="${pageContext.request.contextPath }/public/my-review/${userPublic.id_user}">-
															My review </a><a
														href="${pageContext.request.contextPath }/public/my-info">-
															 My info</a><a
														href="${pageContext.request.contextPath }/public/logout">-
															Logout </a></li>
												</ul></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pageContext.request.contextPath }/public/login"><i
													class="fa fa-sign-in" style="font-size: 20px"
													aria-hidden="true"></i> Login</a></li>
										</c:otherwise>
									</c:choose>
								</ul>

								<!-- Search -->
								<div class="search-btn ml-4">
									<i class="fa fa-search" aria-hidden="true"></i>
								</div>

								<!-- Book Now -->
								<div class="book-now-btn ml-3 ml-lg-5">
									<a  style="background-color: #6c757d "
										href="${pageContext.request.contextPath}/public/booking/index"><small
										id="quantity_booking"> (<c:choose>
												<c:when test="${not empty soluong}">${soluong}</c:when>
												<c:otherwise>0</c:otherwise>
											</c:choose>)
									</small>Book Now <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
								</div>
							</div>
							<!-- Nav End -->
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- Header Area End -->