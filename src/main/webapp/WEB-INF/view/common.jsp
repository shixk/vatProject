<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		
		<link rel="stylesheet" href="<c:url value='/resources/style.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/js/themes/default/easyui.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/js/themes/icon.css'/>" />
		<link rel="stylesheet" href="<c:url value='/resources/css/search.css'/>" />

		<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
		<script src="<c:url value='/resources/js/jquery.easyui.min.js'/>"></script>
		<script src="<c:url value='/resources/js/laydate.js'/>"></script>
		<script src="<c:url value='/resources/js/locale/easyui-lang-zh_CN.js'/>"></script>
		<script src="<c:url value='/resources/js/common.js'/>"></script>
		<script src="<c:url value='/resources/js/getData.js'/>"></script>
		<script src="<c:url value='/resources/js/validate/jquery.validate.min.js'/>"></script>
		<script src="<c:url value='/resources/js/validate/messages_zh.min.js'/>"></script>
		<script src="<c:url value='/resources/js/ajaxfileupload.js'/>"></script>
		<script src="<c:url value='/resources/js/jquery.tmpl.min.js'/>"></script>
		
		<script src="<c:url value='/resources/js/common/common.js'/>"></script>
		<script src="<c:url value='/resources/js/common/commonCode.js'/>"></script>
		<script src="<c:url value='/resources/js/common/LodopFuncs.js'/>"></script>
		
</head>
<c:set var="webRoot" value="<%=basePath%>" />
<script type="text/javascript">
	var webRoot ='${webRoot}';
	var vatRoot='${webRoot}'+'//view//';
</script>