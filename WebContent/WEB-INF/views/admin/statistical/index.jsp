<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<%@page import="java.util.List"%>
<%@page import="hotelbooking.model.Booking"%>
<div class="data-table-area mg-b-15">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="sparkline13-list">
					<div class="sparkline13-graph">
						<c:if test="${empty find}">
							<div class="row ">
								<h3 class="tk" style="margin-left: 10px; text-align: center;">Thống kê theo tuần</h3>
							</div>
							<div class="form-group-inner" style="margin-bottom: 50px">
								<label>Thống kê theo</label>
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="chosen-select-single mg-b-20">
										<select data-placeholder="Choose a hotel..." name="check"
											class="form-control w-m"
											style="border-radius: 5px; width: 355px;" >
											<option value="1"
												<c:if test="${check.check == 1}">selected="selected"</c:if>>Tuần</option>
											<option value="2"
												<c:if test="${check.check == 2}">selected="selected"</c:if>>Tháng</option>
										</select>
									</div>
								</div>
							</div>
							<div id="chart_div" style="width: 100%; height: 800px;"></div>
						</c:if>
						<div class="row ">
								<h3 style="margin-left: 10px; text-align: center;">Lịch sử đặt phòng</h3>
							</div>
						<div class="datatable-dashv1-list custom-datatable-overright">
							<c:if test="${not empty error}">
								<div class='alert alert-danger' role='alert'>${msg}</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class='alert alert-success' role='alert'>${success}</div>
							</c:if>
							<form
								action="${pageContext.request.contextPath }/admin/statistical/check"
								method="Post">
								<c:if test="${userAdmin.role_id == 1}">
									<div class="form-group-inner">
										<label>Khách sạn</label>
										<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
											<div class="chosen-select-single mg-b-20">
												<select data-placeholder="Choose a hotel..." name="hotel_id"
													class="form-control"
													style="border-radius: 5px; width: 355px;">
													<option value="0">- All -</option>
													<c:forEach items="${listHotels}" var="hotel">
														<option
															<c:if test="${check.hotel_id == hotel.id_hotel }">selected="selected"</c:if>
															value="${hotel.id_hotel}">${hotel.hotel_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<div class="row align-items-end">
									<div class="col-md-6" style="width: 200px;">
										<div class="form-group">
											<label for="firstname">Từ ngày</label> <input type="Date"
												class="form-control" value="${check.checkin}" name="checkin"
												style="margin-left: 15px; border-radius: 5px;">
										</div>
									</div>
									<div class="col-md-6" style="width: 200px;">
										<div class="form-group">
											<label for="firstname">Đến này</label> <input type="Date"
												class="form-control checkout" value="${check.checkout}"
												name="checkout" style="border-radius: 5px;">
										</div>
									</div>
								</div>
								<div class="form-group-inner">
									<label>Thống kê theo</label>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="chosen-select-single mg-b-20">
											<select data-placeholder="Choose a hotel..." name="check"
												class="form-control"
												style="border-radius: 5px; width: 355px;">
												<option value="0">- All -</option>
												<option value="1"
													<c:if test="${check.check == 1}">selected="selected"</c:if>>Phòng
													co thu nhập giảm dần</option>
												<option value="2"
													<c:if test="${check.check == 2}">selected="selected"</c:if>>Phòng
													thu nhập tăng dần</option>
											</select>
										</div>
									</div>
								</div>
								<div class="">
									<input type="submit" value="Kiểm tra" class="button-success"
										style="margin-left: 15px; color: white;" />
								</div>
							</form>
							<table id="table" data-toggle="table" data-pagination="true"
								data-search="true" data-show-columns="true"
								data-show-pagination-switch="true" data-show-refresh="true"
								data-key-events="true" data-show-toggle="true"
								data-resizable="true" data-cookie="true"
								data-cookie-id-table="saveId" data-show-export="true"
								data-click-to-select="true" data-toolbar="#toolbar">
								<thead class="text-align">
									<tr>
										<th class="table-id">STT</th>
										<c:if test="${userAdmin.role_id == 1}">
											<th class="table-id">Khách sạn</th>
										</c:if>
										<c:if test="${check.check == 0}">
											<th class="table-id">Mã đơn</th>
											<th class="table-function">Tên khách hàng</th>
											<th class="table-function">Số điện thoại</th>
										</c:if>
										<th class="table-function">Tên phòng</th>
										<th class="table-function">Số phòng</th>
										<c:if test="${check.check == 0}">
											<th class="table-function">Hình ảnh</th>
											<th class="table-function">Ngày nhận phòng</th>
											<th class="table-function">Ngày trả phòng</th>
										</c:if>
										<th class="table-function">Tổng tiền</th>
									</tr>
								</thead>
								<tbody class="text-align">
									<c:set var="STT" value="${0}"></c:set>
									<c:set var="tongtien" value="${0}"></c:set>
									<c:forEach items="${listBooking}" var="booking">
										<c:set var="STT" value="${STT + 1}"></c:set>
										<c:set var="tongtien"
											value="${tongtien + booking.total_price}"></c:set>
										<tr>
											<td><p>${STT}</p></td>
											<c:if test="${userAdmin.role_id == 1}">
												<td><p>${booking.hotel_name}</p></td>
											</c:if>
											<c:if test="${check.check == 0}">
												<td><p>${booking.code}</p></td>
												<td><p>${booking.lastname}<span>
															${booking.firstname}</span>
													</p></td>
												<td><p>${booking.phone}</p></td>
											</c:if>
											<td><p>${booking.room.name}</p></td>
											<td><p>${booking.room.room_number}</p></td>
											<c:if test="${check.check == 0}">
												<td><p>
														<c:choose>
															<c:when test="${not empty booking.room.image }">
																<img
																	Style="border-radius: 5px; width: 120px; height: 80px;"
																	src="${pageContext.request.contextPath }/uploads/${booking.room.image}" />
															</c:when>
															<c:otherwise>
																<p>không có hình ảnh</p>
															</c:otherwise>
														</c:choose>
													</p></td>
												<td><p>${booking.checkin}</p></td>
												<td><p>${booking.checkout}</p></td>
											</c:if>
											<td><p>${defines.formatNumber(booking.total_price)}<span> VND</span></p></td>
										</tr>
									</c:forEach>
									<tr>
										<c:choose>
											<c:when test="${userAdmin.role_id == 1}">
												<c:if test="${check.check == 0}">
													<td colspan="9"></td>
												</c:if>
												<c:if test="${check.check != 0}">
													<td colspan="3"></td>
												</c:if>
											</c:when>
											<c:otherwise>
												<c:if test="${check.check == 0}">
													<td colspan="8"></td>
												</c:if>
												<c:if test="${check.check != 0}">
													<td colspan="2"></td>
												</c:if>
											</c:otherwise>
										</c:choose>
										<td><p style="font-size: 18px;">Tổng tiền :</p></td>
										<td><p style="font-size: 18px; color: red;">${defines.formatNumber(tongtien)}<span>
													VND</span>
											</p></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	console.log(${check.checkout});
	var sl = 1;
	$('.w-m').on('change',function(){
		  sl = $(this).val();
		  google.charts.load('current', {'packages':['corechart']});
		  if(sl == 2){
			  $('.tk').html('Thống kê theo tháng');
			  google.charts.setOnLoadCallback(drawChart11);
		  }else{
			  google.charts.setOnLoadCallback(drawChart);
		  }
		  
	});
  
  function drawChart11() {
	  var now = new Date();
	  while(1){
		  if(now.getDate() == 1) {
			  now = new Date(now.getTime()-86400000);
			  break;
		  }
		  now = new Date(now.getTime()-86400000);
	  }
	  console.log(now.getDate());
	  var weekday = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	  var weekday_totalPrice = new Array(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
	  <c:forEach items="${listBooking}" var="booking">
  	  	var time = new Date('${booking.created_time}');
		if(time.getTime() > now.getTime()){
			weekday_totalPrice[time.getDate()] = weekday_totalPrice[time.getDate()] + parseInt('${booking.total_price}');
			weekday[time.getDate()] = weekday[time.getDate()] + 1;
	 	}
		
	  </c:forEach>
	  
	  var weekList = [
	      ['Ngày trong tháng', 'Tổng thu nhập', 'Tổng lượt đặt']
	  ];
	  for(var i = 1; i<=31; i++){
		  var weekListday = ['Ngày '+i , weekday_totalPrice[i], weekday[i]] ;
		  weekList.push(weekListday);
	  }
	  var data = google.visualization.arrayToDataTable(weekList);
	
	  var options = {
	      title: 'Biểu đồ đặt phòng',
	      hAxis: {title: 'Ngày trong tháng',  titleTextStyle: {color: '#333'}},
	      vAxis: {minValue: 0}
	  };
	
	  var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
	  chart.draw(data, options);
  }
  
  
  google.charts.load('current', {'packages':['corechart']});
  google.charts.setOnLoadCallback(drawChart);
  
  function drawChart() {
	  var now = new Date();
	  while(1){
		  if(now.getDay() == 1) {
			  now = new Date(now.getTime()-86400000);
			  break;
		  }
		  now = new Date(now.getTime()-86400000);
	  }
	  console.log(now.getDate());
	  var weekday = new Array(0,0,0,0,0,0,0);
	  var weekday_totalPrice = new Array(0,0,0,0,0,0,0);
	  <c:forEach items="${listBooking}" var="booking">
  	  	var time = new Date('${booking.created_time}');
		if(time.getTime() > now.getTime()){
			weekday_totalPrice[time.getDay()] = weekday_totalPrice[time.getDay()] + parseInt('${booking.total_price}');
			weekday[time.getDay()] = weekday[time.getDay()] + 1;
			console.log(weekday_totalPrice[time.getDay()])
	 	}
		
	  </c:forEach>
	  
	  var weekList = [
	      ['Ngày trong tuần', 'Tổng thu nhập', 'Tổng lượt đặt'],
	      ['Thứ hai',  weekday_totalPrice[1],      weekday[1]],
	      ['Thứ ba',  weekday_totalPrice[2],      weekday[2]],
	      ['Thứ tư',  weekday_totalPrice[3],       weekday[3]],
	      ['Thứ năm',  weekday_totalPrice[4],      weekday[4]],
	      ['Thứ sáu',  weekday_totalPrice[5],       weekday[5]],
	      ['Thứ bảy',  weekday_totalPrice[6],       weekday[6]],
	      ['Chủ nhật',  weekday_totalPrice[0],       weekday[0]],
	  ];
	  var data = google.visualization.arrayToDataTable(weekList);
	
	  var options = {
	      title: 'Biểu đồ đặt phòng',
	      hAxis: {title: 'Ngày trong tuần',  titleTextStyle: {color: '#333'}},
	      vAxis: {minValue: 0}
	  };
	
	  var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
	  chart.draw(data, options);
  }
</script>

