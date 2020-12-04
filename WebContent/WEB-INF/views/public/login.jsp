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
					<h2 class="page-title">Our Room</h2>
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb justify-content-center">
							<li class="breadcrumb-item"><a href="index.html">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Login</li>
						</ol>
					</nav>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Breadcrumb Area End -->

<!-- Rooms Area Start -->
<div style="padding: 40px; background-color: #E9EBEE;"></div>
<form action="${pageContext.request.contextPath }/public/login"
	method="post" class="form">
	<div class="login-wrap">
		<div class="login-html">
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
				for="tab-1" class="tab">Sign In</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"><label for="tab-2"
				class="tab">Sign Up</label>
			<div class="login-form">
				<div class="sign-in-htm">
					<c:if test="${not empty msg}">
						<p style="color: red;">${msg}</p>
					</c:if>
					<div class="group">
						<label for="pass" class="label">Email Address*</label> <input
							id="" type="text" name="emailSignIn" class="input"
							value="${signin.emailSignIn}"
							placeholder="Vui lòng nhập địa chỉ email của bạn">
					</div>
					<div class="group">
						<label for="pass" class="label">Password *</label> <input
							id="pass" type="password" name="passwordSignIn" class="input"
							data-type="password" value="${signin.passwordSignIn}"
							placeholder="Vui lòng nhập mật khẩu của bạn">
					</div>
					<div class="group">
						<input id="check" type="checkbox" class="check" checked> <label
							for="check"><span class="icon"></span> Keep me Signed in</label>
					</div>
					<div class="group">
						<input type="submit" class="button" value="Sign In"
							onclick="setValueSignIn();">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<a href="#forgot">Forgot Password?</a>
					</div>
				</div>
				<div class="sign-up-htm">
					<div class="group">
						<label for="user" class="label">First name *</label> <input
							id="user" type="text" name="firstnameSignUp" class="input"
							value="${signup.firstnameSignUp}"
							placeholder="Vui lòng nhập tên của bạn">
					</div>
					<div class="group">
						<label for="user" class="label">Last name *</label> <input
							id="user" type="text" name="lastnameSignUp" class="input"
							value="${signup.lastnameSignUp}"
							placeholder="Vui lòng nhập họ của bạn">
					</div>
					<div class="group">
						<label for="pass" class="label">Email Address*</label> <input
							id="" type="text" name="emailSignUp" class="input"
							value="${signup.emailSignUp}"
							placeholder="Vui lòng nhập địa chỉ email của bạn">
					</div>
					<div class="group">
						<label for="pass" class="label">Password</label> <input id="pass"
							type="password" class="input" data-type="password"
							name="passwordSignUp" onclick="getLocation();"
							placeholder="Vui lòng nhập mật khẩu mới">
					</div>
					<div class="group" style="display: none;">
						<input type="text" id="setValueCheck" name="check" value="">
					</div>
					<div class="form-group-inner" style="display: none;">
						<input type="text" name="latitude" id="latitude"
							class="form-control" placeholder="latitude"
							style="border-radius: 5px;" value="" />
					</div>
					<div class="form-group-inner" style="display: none;">
						<input type="text" name="longitude" id="longitude"
							class="form-control" placeholder="longitude"
							style="border-radius: 5px;" value="" />
					</div>
					<div class="group">
						<input type="submit" class="button" value="Sign Up"
							onclick="setValueSignUp();">
					</div>
					<div class="hr"></div>
					<div class="foot-lnk">
						<label for="tab-1">Already Member?</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		window.onload = getLocation;
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(setPosition);
			} else {
				alert("Geolocation không được hỗ trợ bởi trình duyệt này.");
			}
		}

		function setPosition(position) {
			document.getElementById("latitude").value = position.coords.latitude;
			document.getElementById("longitude").value = position.coords.longitude;
		}

		function setValueSignIn() {
			document.getElementById("setValueCheck").value = 0;
		}

		function setValueSignUp() {
			document.getElementById("setValueCheck").value = 1;
		}
	</script>
</form>

<!-- Rooms Area End -->
<div style="clear: both; margin-bottom: 50px;"></div>
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
	</div>
</section>
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