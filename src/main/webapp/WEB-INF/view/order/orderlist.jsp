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
	<div>
		<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" onclick="exportExcel();">导出</a>
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


function exportExcel(){
	var orderNo='200678910';
	//调用后台方法
    $.ajax({
        type: "POST",
        url: '<c:url value="/order/exportExcel"/>',
        data: {orderNo:orderNo},
        dataType: 'json',
        success: function (response) {
            if (response.success) {
                var url = "<c:url value='/resources/temp/" + response.filename+"'/>";
                window.location.href = url;
                $.messager.alert('提示', response.Msg);
            } else {
                $.messager.alert('错误', response.Msg);
            }
        },
        error: function (xhr) {
            $.messager.alert('错误', "发生错误 " + xhr.status);
        }
    });
}
</script>