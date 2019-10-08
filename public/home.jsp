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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/public.css">
	<script type="text/javascript" src="<%=basePath %>/static/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	var win=null;
		function openPlan(){
			h=700;
			w=1400;
			left=(screen.width-w)/2;
			topPx=(screen.height-h)/2;
			//top=(screen.height-h)/2;
			win = window.open("plan.jsp","TEST","status=no,toolbar=no,menubar=no,titlebar=no,location=no,resizable=no,height="+h+",width="+w+",top="+topPx+",left="+left,false);
		}
		function logout(){
			if(win){
				win.close();
			}
			window.location="index.jsp";
		}
	</script>
  </head>
  
  <body>
  	<img style="z-index: -1;position: absolute;left: 0px;top:0px;" src="static/img/UICOVER.jpg" width="100%" height="100%">
  	
	<img style="z-index: 0;position: absolute;right: 63%;top:14%;" src="static/img/UILOGO.png" >
	<img style="z-index: 0;position: absolute;right: 63%;top:51%;" src="static/img/W2C.png" >
	<div style="z-index: 0;position: absolute;right: 63%;top:61%; " >
		
		<label style="color:white;font-size:20px;">User Name: <%=((User)session.getAttribute(SecurityFilter.SESSION_KEY)).getUsername() %></label>
		<a href="javascript:logout()" class="lnkbtnDark" id="logoutBtn">&nbsp;登&nbsp;出&nbsp;</a>
		<br><br>
		<label style="color:white;font-size:20px;">Institution: Accuray Hospital</label><br><br>
		<label style="color:white;font-size:20px;">Expired Date: 1st May 2020</label>
	</div>
	
	<div style="z-index: 0;position: absolute;left: 50%;top:30%; " >
		<table border="0">
			<tr>
				<td><div class="appicon" style="background:#052E5A;background-image: url('static/img/Icon_@Plan.png');" onclick="javascript:openPlan()">
					<div style="position: relative;top:80%;">@Plan</div></div>
					</td>
				<td><div class="appicon" style="background:#052E5A;background-image: url('static/img/Icon-@Consultant.png');"
					onclick="window.location='<%=basePath %>/consultant.do'">
					<div style="position: relative;top:80%;">@Consultant</div></div>
					</td>
				<td><div class="appicon" style="background:#052E5A;background-image: url('static/img/Icon-@QA.png');">
					<div style="position: relative;top:80%;">@QA</div></div>
					</td>
			</tr>
			<tr>
				<td><div class="appicon" style="background:#052E5A;background-image: url('static/img/Icon-@Research.png');">
					<div style="position: relative;top:80%;">@Research</div></div>
					</td>
				<td><div class="appicon" style="background:#052E5A;background-image: url('static/img/Icon-@Education.png');">
					<div style="position: relative;top:80%;">@Education</div></div>
					</td>
				<td><div class="appicon" style="background:#052E5A;background-image: url('static/img/Icon-@Management.png');">
					<div style="position: relative;top:80%;">@Management</div></div>
					</td>
					
			</tr>
		</table>
	</div>
	</body>
</html>