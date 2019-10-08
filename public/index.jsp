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
    <title>Login</title>
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
  </head>
  
  <body>
  	<img style="z-index: -1;position: absolute;left: 0px;top:0px;" src="static/img/UICOVER.jpg" width="100%" height="100%">
  	
<!-- 	<div style="filter:alpha(opacity:50); float:top; margin:0 0;z-index: 9999 ;width: 1px;height: 280px;"> -->
<!-- 	<img style="z-index: -1;position: absolute;left: 0px;top:0px;" src="static/img/UICOVER.jpg" width="100%" height="100%"></div> -->
<!-- 	<div style="float:left;margin:0 0;z-index: 9999 ;width: 100px;height: 1px;"></div>  -->
	
<!-- 	<div style="float:left; margin:0 0;z-index: 9999 ;width: 600px;height: 180px;" align="right" > -->
	<img style="z-index: 0;position: absolute;right: 63%;top:14%;" src="static/img/UILOGO.png" >
	<img style="z-index: 0;position: absolute;right: 63%;top:51%;" src="static/img/W2C.png" >
	<div id="loginDiv" style="position:absolute;right:63%;top:62%">
		<form id="ff" method="post">
			<label  style="color:#FF0c0c;"><%=request.getAttribute("errorMsg")==null?"":request.getAttribute("errorMsg") %></label>
		    <table border="0" >
		    	<tr height="30">
			    	<td align="center"><label for="name" style="color:#FFFEFF;font-family: AB">用&nbsp;户&nbsp;名&nbsp;&nbsp;&nbsp;</label></td>
					<td><input class="easyui-validatebox" style="height: 20px;width:200px;" type="text" id="loginName" data-options="required:true" /></td>
		   		</tr>
			    <tr height="30">
					<td align="center"><label for="psw"  style="color:#FFFEFF;">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;</label></td>
					<td><input class="easyui-validatebox" style="height: 20px;width:200px;" type="password" id="password" data-options="required:true" /></td>
			    </tr>
		    	<tr height="40px">
		    		<td colspan="2" align="right">
			    	<a href="javascript:login()" class="lnkbtn" id="loginBtn">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			    	<a href="javascript:clear()" class="lnkbtnDark" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
			    	<!-- easyui-linkbutton -->
			    	</td>
		    	</tr>
		    </table>
		</form>
	</div>
	<script type="text/javascript">		
		$(function(){
			$("#loginName").focus();
			$(document).keyup(function(event){
				  if(event.keyCode ==13){
					  login();
				  }
				});
		});
		
	
		
		function login(){
			$.post("<%=basePath%>/login",
					{
						"username":$("#loginName").val(),
						"password":$("#password").val()
					},
					function(result){
						if("true"==result){
							window.location="<%=basePath %>/home.do";
						}else{
							alert("Authentication Failed!",result);
						}
			  		}
			);
			
		}
		function clear(){
			$("#loginName").val("");
			$("#password").val("");
		}
		
		</script>
	</body>
</html>