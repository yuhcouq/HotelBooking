
$(function(){
	var list = ["huy","haha"];
	/* <c:forEach items="${listhotels}" var="hotel">
		list.push('${hotel.hotel_name}');
	</c:forEach> */
	console.log(list);
	$("#searchFormInput").keyup(function(){
		$("#searchFormInput").autocomplete({
             source: list
          });
	});
	 
});