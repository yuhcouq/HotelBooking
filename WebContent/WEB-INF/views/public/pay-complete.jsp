<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<section class="ftco-section ftco-cart" style="margin-top: 50px;">
	<div class="container" id="cartbooking_delete">
		<div style="text-align: center; margin-bottom: 100px;">
			<c:choose>
				<c:when test="${not empty complete}">
					${complete}
				</c:when>
				<c:otherwise>
					<h4 style="color: #1CC3B2; font-size: 30px">Đặt phòng thành
						công.</h4>
					<span style="font-size: 15px">Xem đơn đặt phòng tại <a>My
							booking</a>. Mã đơn hàng <span style="color: #1CC3B2;"><u>${code}</u></span>
					</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</section>

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