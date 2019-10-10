<%@page import="com.app.login.User"%>
<%@page import="com.app.login.SecurityFilter"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>HOME</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="description" content="Broker">
	<meta http-equiv="pragma" content="no-cache" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/themes/gray/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/public.css">
	<script type="text/javascript" src="<%=basePath %>/static/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/home.js"></script>
	<script type="text/javascript">
	var win=null;
		
		function logout(){
			if(win){
				win.close();
			}
			window.location="/logout";
		}
	</script>
  </head>
  <body class="easyui-layout">
    <div data-options="region:'north',title:'',split:true" style="height:35px;">
    		 <div style="z-index: 0;position: absolute;right: 5%;top:1%; " >
		
		<label style="font-size:16px;">User Name: <%=((User)session.getAttribute(SecurityFilter.SESSION_KEY)).getUsername() %></label>
		<a href="javascript:logout()" class="l-btn" id="logoutBtn">&nbsp;登&nbsp;出&nbsp;</a>
		<br><br>
		
	</div>
    </div>
    <!-- <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
    <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>
    <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div> -->
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    		<div id="taskTable" style="width:80%;height:60%;position: absolute;right: 10%;top:20%;background: blue;"></div>
	
    </div>
</body>
  <body>
	
	
	</body>
</html>