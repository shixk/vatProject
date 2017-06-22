<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/view/common.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<title>orderlist</title>
</head>
<body>
	<div>
		<input type="button" value="test" onclick="test()" >
	</div>
</body>
</html>
<script>
function test(){
		
	$.ajax({
		   type: "POST",
		   url: '<c:url value="/order/list"/>',
		   dataType:"json" , 
		   success: function(data){
		     alert(data.order.custName );
		   }
		});
}
</script>