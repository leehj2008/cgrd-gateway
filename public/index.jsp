<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="common.jsp" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>CERIS Adaptor Test</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="CERIS Adaptor Test">
<meta http-equiv="description" content="GE HCD SI portal">
<link rel="stylesheet" type="text/css" href="demo.css">

<script type="text/javascript">
	function loadOrderData(){
		$.get("testOrder.json",function(data){
			$("#newOrderJSON").val(JSON.stringify(data));
			alert("载入成功");
		});

	}
	function createNewOrder(){
		$.post("/createNewOrder",
			{orderData:$("#newOrderJSON").val()},	
			function(data){
			
				alert(JSON.stringify(data));
		});

	}
	
	function loadSchData(){
		$.get("testSchedule.json",function(data){
			$("#schJSON").val(JSON.stringify(data));
			alert("载入成功");
		});

	}
	function newSch(){
		$.post("/schedule",
			{orderData:$("#schJSON").val()},	
			function(data){
				alert(JSON.stringify(data));
		});

	}
	
	function loadArrData(){
		$.get("testArrive.json",function(data){
			$("#arrJSON").val(JSON.stringify(data));
			alert("载入成功");
		});

	}
	function arrive(){
		$.post("/arrive",
			{orderData:$("#arrJSON").val()},	
			function(data){
				alert(JSON.stringify(data));
		});

	}
</script>

</head>
<body>
	<label style="font-size: 20;">GE RISCE 接口测试</label><br>
	<textarea rows="20" cols="100" id="newOrderJSON"></textarea><br>
	<button id="loadDataBtn" onclick="javascript:loadOrderData()">载入测试数据</button>
	<button id="createOrderbtn"onclick="javascript:createNewOrder()">提交申请单</button><br>
	<textarea rows="20" cols="100" id="schJSON"></textarea><br>
	<button id="loadDataBtn" onclick="javascript:loadSchData()">载入测试数据</button>
	<button id="createOrderbtn"onclick="javascript:newSch()">提交预约</button><br>
	<textarea rows="20" cols="100" id="arrJSON"></textarea><br>
	<button id="loadDataBtn" onclick="javascript:loadArrData()">载入测试数据</button>
	<button id="createOrderbtn"onclick="javascript:arrive()">提交到检</button><br>
</body>
</html>
