<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>HotelBooking</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- favicon
		============================================ -->
<link rel="shortcut icon" type="image/x-icon"
	href="${defines.urlAdmin}/img/favicon.ico">
<!-- Google Fonts
		============================================ -->
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,700,900"
	rel="stylesheet">
<!-- Bootstrap CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/bootstrap.min.css">
<!-- Bootstrap CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/font-awesome.min.css">
<!-- owl.carousel CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/owl.carousel.css">
<link rel="stylesheet" href="${defines.urlAdmin}/css/owl.theme.css">
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/owl.transitions.css">
<!-- animate CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/animate.css">
<!-- normalize CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/normalize.css">
<!-- meanmenu icon CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/meanmenu.min.css">
<!-- main CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/main.css">
<!-- educate icon CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/educate-custon-icon.css">
<!-- morrisjs CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/morrisjs/morris.css">
<!-- mCustomScrollbar CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/scrollbar/jquery.mCustomScrollbar.min.css">
<!-- metisMenu CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/metisMenu/metisMenu.min.css">
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/metisMenu/metisMenu-vertical.css">
<!-- calendar CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/calendar/fullcalendar.min.css">
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/calendar/fullcalendar.print.min.css">

<link rel="stylesheet" href="${defines.urlAdmin}/css/editor/select2.css">
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/editor/datetimepicker.css">
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/editor/x-editor-style.css">
<!-- normalize CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/data-table/bootstrap-table.css">

<!-- style CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/style.css">
<!-- responsive CSS
		============================================ -->
<link rel="stylesheet" href="${defines.urlAdmin}/css/responsive.css">

<link rel="stylesheet"
	href="${defines.urlAdmin}/css/select2/select2.min.css">
<!-- chosen CSS
		============================================ -->
<link rel="stylesheet"
	href="${defines.urlAdmin}/css/chosen/bootstrap-chosen.css">
<!-- modernizr JS
		============================================ -->
<script
	src="${defines.urlAdmin}/${defines.urlAdmin}/js/vendor/modernizr-2.8.3.min.js"></script>
<link rel="stylesheet"
	href="${defines.urlAdmin}/${defines.urlAdmin}/css/summernote/summernote.css">
<style type="text/css">
.text-align th {
	text-align: center;
}

.text-align td p {
	text-align: center;
}

.table-id {
	width: 3%;
}

.table-name {
	width: 15%;
}

.table-image {
	width: 11.5%;
}

.table-address {
	width: 15%;
}

.table-city {
	width: 10%;
}

.table-rating {
	width: 2%;
}

.table-detail {
	width: 27%;
}

.table-slide {
	width: 50%;
}

.form-group-inner input {
	margin-left: 15px;
}
</style>
</head>

<body>
	<!--[if lt IE 8]>
		<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
	<![endif]-->
	<!-- Start Left menu area -->
	<div class="left-sidebar-pro">
		<nav id="sidebar" class="">
			<div class="sidebar-header">
				<a href="index.html"><img class="main-logo"
					src="${defines.urlAdmin}/img/logo/loder.jpg" alt="" /></a> <strong><a
					href="index.html"><img
						src="${defines.urlAdmin}/img/logo/logosn.png" alt="" /></a></strong>
			</div>
			<div class="left-custom-menu-adp-wrap comment-scrollbar">
				<nav class="sidebar-nav left-sidebar-menu-pro">
					<ul class="metismenu" id="menu1">
						<li class="active">
							<a class="has-arrow" href="index.html">
									<span class="educate-icon educate-home icon-wrap"></span> 
									<span class="mini-click-non">Home</span>
							</a>
							<ul class="submenu-angle" aria-expanded="true">
								<li><a title="Dashboard v.1"
									href="${pageContext.request.contextPath}/admin/hotel/index"><span
										class="mini-sub-pro">hotel</span></a></li>
								<li><a title="Dashboard v.2"
									href="${pageContext.request.contextPath}/admin/room/index"><span
										class="mini-sub-pro">Room</span></a></li>
								<li><a title="Dashboard v.3"
									href="${pageContext.request.contextPath}/admin/city/index"><span
										class="mini-sub-pro">City</span></a></li>
								<li><a title="Widgets"
									href="${pageContext.request.contextPath}/admin/hotelreview/index"><span
										class="mini-sub-pro">Hotel Reviews</span></a></li>
								<li><a title="Widgets"
									href="${pageContext.request.contextPath}/admin/roomreview/index"><span
										class="mini-sub-pro">Room Reviews</span></a></li>
								<li><a title="Widgets"
									href="${pageContext.request.contextPath}/admin/slide/index"><span
										class="mini-sub-pro">Slide</span></a></li>
								<li><a title="Widgets"
									href="${pageContext.request.contextPath}/admin/booking/index"><span
										class="mini-sub-pro">Booking</span></a></li>
								<li><a title="Widgets"
									href="${pageContext.request.contextPath}/admin/statistical/index"><span
										class="mini-sub-pro">Thống kê</span></a></li>
							</ul>
						</li>
						<li>
							<a class="has-arrow" href="all-students.html"
							aria-expanded="false"><span
								class="educate-icon educate-student icon-wrap"></span> 
								<span class="mini-click-non">Tài khoản</span>
							</a>
							<ul class="submenu-angle" aria-expanded="false">
								<c:if test="${userAdmin.role_id == 1}">
									<li><a title="Quản lý đối tác"
										href="${pageContext.request.contextPath}/admin/user/index/2"><span
											class="mini-sub-pro">Đối tác</span></a></li>
									<li><a title="All Students"
										href="${pageContext.request.contextPath}/admin/user/index/4"><span
											class="mini-sub-pro">Người dùng</span></a></li>
								</c:if>
								<c:if test="${userAdmin.role_id == 2}">
									<li><a title="Quản lý nhân viên"
										href="${pageContext.request.contextPath}/admin/user/index/3"><span
											class="mini-sub-pro">Nhân viên</span></a></li>
								</c:if>
							</ul>
						</li>
						<%-- <li><a title="Landing Page" href="events.html"
							aria-expanded="false"><span
								class="educate-icon educate-event icon-wrap sub-icon-mg"
								aria-hidden="true"></span> <span class="mini-click-non">Event</span></a>
						</li>
						<li><a class="has-arrow" href="all-professors.html"
							aria-expanded="false"><span
								class="educate-icon educate-professor icon-wrap"></span> <span
								class="mini-click-non">Professors</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<li><a title="All Professors" href="all-professors.html"><span
										class="mini-sub-pro">All Professors</span></a></li>
								<li><a title="Add Professor" href="add-professor.html"><span
										class="mini-sub-pro">Add Professor</span></a></li>
								<li><a title="Edit Professor" href="edit-professor.html"><span
										class="mini-sub-pro">Edit Professor</span></a></li>
								<li><a title="Professor Profile"
									href="professor-profile.html"><span class="mini-sub-pro">Professor
											Profile</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="all-students.html"
							aria-expanded="false"><span
								class="educate-icon educate-student icon-wrap"></span> <span
								class="mini-click-non">Tài khoản</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<c:if test="${userAdmin.role_id == 1}">
									<li><a title="Quản lý đối tác"
										href="${pageContext.request.contextPath}/admin/user/index/2"><span
											class="mini-sub-pro">Đối tác</span></a></li>
									<li><a title="All Students"
										href="${pageContext.request.contextPath}/admin/user/index/4"><span
											class="mini-sub-pro">Người dùng</span></a></li>
								</c:if>
								<c:if test="${userAdmin.role_id == 2}">
									<li><a title="Quản lý nhân viên"
										href="${pageContext.request.contextPath}/admin/user/index/3"><span
											class="mini-sub-pro">Nhân viên</span></a></li>
								</c:if>
							</ul></li>
						<li><a class="has-arrow" href="all-courses.html"
							aria-expanded="false"><span
								class="educate-icon educate-course icon-wrap"></span> <span
								class="mini-click-non">Courses</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<li><a title="All Courses" href="all-courses.html"><span
										class="mini-sub-pro">All Courses</span></a></li>
								<li><a title="Add Courses" href="add-course.html"><span
										class="mini-sub-pro">Add Course</span></a></li>
								<li><a title="Edit Courses" href="edit-course.html"><span
										class="mini-sub-pro">Edit Course</span></a></li>
								<li><a title="Courses Profile" href="course-info.html"><span
										class="mini-sub-pro">Courses Info</span></a></li>
								<li><a title="Product Payment" href="course-payment.html"><span
										class="mini-sub-pro">Courses Payment</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="all-courses.html"
							aria-expanded="false"><span
								class="educate-icon educate-library icon-wrap"></span> <span
								class="mini-click-non">Library</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<li><a title="All Library" href="library-assets.html"><span
										class="mini-sub-pro">Library Assets</span></a></li>
								<li><a title="Add Library" href="add-library-assets.html"><span
										class="mini-sub-pro">Add Library Asset</span></a></li>
								<li><a title="Edit Library" href="edit-library-assets.html"><span
										class="mini-sub-pro">Edit Library Asset</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="all-courses.html"
							aria-expanded="false"><span
								class="educate-icon educate-department icon-wrap"></span> <span
								class="mini-click-non">Departments</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<li><a title="Departments List" href="departments.html"><span
										class="mini-sub-pro">Departments List</span></a></li>
								<li><a title="Add Departments" href="add-department.html"><span
										class="mini-sub-pro">Add Departments</span></a></li>
								<li><a title="Edit Departments" href="edit-department.html"><span
										class="mini-sub-pro">Edit Departments</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="mailbox.html"
							aria-expanded="false"><span
								class="educate-icon educate-message icon-wrap"></span> <span
								class="mini-click-non">Mailbox</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<li><a title="Inbox" href="mailbox.html"><span
										class="mini-sub-pro">Inbox</span></a></li>
								<li><a title="View Mail" href="mailbox-view.html"><span
										class="mini-sub-pro">View Mail</span></a></li>
								<li><a title="Compose Mail" href="mailbox-compose.html"><span
										class="mini-sub-pro">Compose Mail</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="mailbox.html"
							aria-expanded="false"><span
								class="educate-icon educate-interface icon-wrap"></span> <span
								class="mini-click-non">Interface</span></a>
							<ul class="submenu-angle interface-mini-nb-dp"
								aria-expanded="false">
								<li><a title="Google Map" href="google-map.html"><span
										class="mini-sub-pro">Google Map</span></a></li>
								<li><a title="Data Maps" href="data-maps.html"><span
										class="mini-sub-pro">Data Maps</span></a></li>
								<li><a title="Pdf Viewer" href="pdf-viewer.html"><span
										class="mini-sub-pro">Pdf Viewer</span></a></li>
								<li><a title="X-Editable" href="x-editable.html"><span
										class="mini-sub-pro">X-Editable</span></a></li>
								<li><a title="Code Editor" href="code-editor.html"><span
										class="mini-sub-pro">Code Editor</span></a></li>
								<li><a title="Tree View" href="tree-view.html"><span
										class="mini-sub-pro">Tree View</span></a></li>
								<li><a title="Preloader" href="preloader.html"><span
										class="mini-sub-pro">Preloader</span></a></li>
								<li><a title="Images Cropper" href="images-cropper.html"><span
										class="mini-sub-pro">Images Cropper</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="mailbox.html"
							aria-expanded="false"><span
								class="educate-icon educate-charts icon-wrap"></span> <span
								class="mini-click-non">Charts</span></a>
							<ul class="submenu-angle chart-mini-nb-dp" aria-expanded="false">
								<li><a title="Bar Charts" href="bar-charts.html"><span
										class="mini-sub-pro">Bar Charts</span></a></li>
								<li><a title="Line Charts" href="line-charts.html"><span
										class="mini-sub-pro">Line Charts</span></a></li>
								<li><a title="Area Charts" href="area-charts.html"><span
										class="mini-sub-pro">Area Charts</span></a></li>
								<li><a title="Rounded Charts" href="rounded-chart.html"><span
										class="mini-sub-pro">Rounded Charts</span></a></li>
								<li><a title="C3 Charts" href="c3.html"><span
										class="mini-sub-pro">C3 Charts</span></a></li>
								<li><a title="Sparkline Charts" href="sparkline.html"><span
										class="mini-sub-pro">Sparkline Charts</span></a></li>
								<li><a title="Peity Charts" href="peity.html"><span
										class="mini-sub-pro">Peity Charts</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="mailbox.html"
							aria-expanded="false"><span
								class="educate-icon educate-data-table icon-wrap"></span> <span
								class="mini-click-non">Data Tables</span></a>
							<ul class="submenu-angle" aria-expanded="false">
								<li><a title="Peity Charts" href="static-table.html"><span
										class="mini-sub-pro">Static Table</span></a></li>
								<li><a title="Data Table" href="data-table.html"><span
										class="mini-sub-pro">Data Table</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="mailbox.html"
							aria-expanded="false"><span
								class="educate-icon educate-form icon-wrap"></span> <span
								class="mini-click-non">Forms Elements</span></a>
							<ul class="submenu-angle form-mini-nb-dp" aria-expanded="false">
								<li><a title="Basic Form Elements"
									href="basic-form-element.html"><span class="mini-sub-pro">Bc
											Form Elements</span></a></li>
								<li><a title="Advance Form Elements"
									href="advance-form-element.html"><span class="mini-sub-pro">Ad
											Form Elements</span></a></li>
								<li><a title="Password Meter" href="password-meter.html"><span
										class="mini-sub-pro">Password Meter</span></a></li>
								<li><a title="Multi Upload" href="multi-upload.html"><span
										class="mini-sub-pro">Multi Upload</span></a></li>
								<li><a title="Text Editor" href="tinymc.html"><span
										class="mini-sub-pro">Text Editor</span></a></li>
								<li><a title="Dual List Box" href="dual-list-box.html"><span
										class="mini-sub-pro">Dual List Box</span></a></li>
							</ul></li>
						<li><a class="has-arrow" href="mailbox.html"
							aria-expanded="false"><span
								class="educate-icon educate-apps icon-wrap"></span> <span
								class="mini-click-non">App views</span></a>
							<ul class="submenu-angle app-mini-nb-dp" aria-expanded="false">
								<li><a title="Notifications" href="notifications.html"><span
										class="mini-sub-pro">Notifications</span></a></li>
								<li><a title="Alerts" href="alerts.html"><span
										class="mini-sub-pro">Alerts</span></a></li>
								<li><a title="Modals" href="modals.html"><span
										class="mini-sub-pro">Modals</span></a></li>
								<li><a title="Buttons" href="buttons.html"><span
										class="mini-sub-pro">Buttons</span></a></li>
								<li><a title="Tabs" href="tabs.html"><span
										class="mini-sub-pro">Tabs</span></a></li>
								<li><a title="Accordion" href="accordion.html"><span
										class="mini-sub-pro">Accordion</span></a></li>
							</ul></li>
						<li id="removable"><a class="has-arrow" href="#"
							aria-expanded="false"><span
								class="educate-icon educate-pages icon-wrap"></span> <span
								class="mini-click-non">Pages</span></a>
							<ul class="submenu-angle page-mini-nb-dp" aria-expanded="false">
								<li><a title="Login" href="login.html"><span
										class="mini-sub-pro">Login</span></a></li>
								<li><a title="Register" href="register.html"><span
										class="mini-sub-pro">Register</span></a></li>
								<li><a title="Lock" href="lock.html"><span
										class="mini-sub-pro">Lock</span></a></li>
								<li><a title="Password Recovery"
									href="password-recovery.html"><span class="mini-sub-pro">Password
											Recovery</span></a></li>
								<li><a title="404 Page" href="404.html"><span
										class="mini-sub-pro">404 Page</span></a></li>
								<li><a title="500 Page" href="500.html"><span
										class="mini-sub-pro">500 Page</span></a></li>
							</ul></li> --%>
					</ul>
				</nav>
			</div>
		</nav>
	</div>
	<!-- End Left menu area -->