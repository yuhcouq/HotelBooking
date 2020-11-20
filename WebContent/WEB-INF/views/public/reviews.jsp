<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp"%>
<section class="ftco-section">
	<div class="container">
		<div class="row">
			<div class="col-xl-8 ftco-animate" style="margin-left: 200px;">
				<form
					action="${pageContext.request.contextPath}/public/reviews/${id_room}/${userPublic.id_user}/${id_booking}"
					class="billing-form ftco-bg-dark p-3 p-md-5" id="infuser"
					method="post">
					<h3 class="mb-4 billing-heading" style="text-align: center;">Đánh
						giá chất lượng</h3>
					<div class="row align-items-end">
						<div class="col-md-6">
							<div class="form-group">
								<label for="firstname">Tên người đánh giá </label> <input
									type="text" class="form-control" name="username"
									placeholder="Vui lòng nhập tên tài khoản" required="required"
									value="${userPublic.firstname}" disabled="disabled">
							</div>
						</div>
						<div class="col-md-6">
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
						<div class="col-md-6">
							<div class="form-group">
								<label for="message">Nội dung đánh giá </label>
								<textarea rows="5" cols="85" name="content"
									style="border: 1px solid #CED4DA; width: 635px;"></textarea>
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

						<p>
							<input type="submit" class="btn btn-primary py-3 px-4"
								value="Đánh giá"
								style="margin-left: 500px; border-radius: 5px; background-color: #1CC3B2; border: 1px solid #1CC3B2;" />
						</p>
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
</section>