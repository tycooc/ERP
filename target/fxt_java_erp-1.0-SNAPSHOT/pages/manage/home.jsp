<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8">
		<!-- 指定以IE8的方式来渲染 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
		<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
		<link href="css/admin.css" type="text/css" rel="stylesheet">
		<title>首页说明</title>
		<style type="text/css">
			<!--
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
		  		background-color: #EAF2FD;
			}
			.STYLE1 {font-size: 12px}
			.STYLE4 {
				font-size: 12px;
				color: #1F4A65;
				font-weight: bold;
			}
			
			a:link {
				font-size: 12px;
				color: #06482a;
				text-decoration: none;
			
			}
			a:visited {
				font-size: 12px;
				color: #06482a;
				text-decoration: none;
			}
			a:hover {
				font-size: 12px;
				color: #FF0000;
				text-decoration: underline;
			}
			a:active {
				font-size: 12px;
				color: #FF0000;
				text-decoration: none;
			}
			.STYLE7 {font-size: 12}
			-->
		</style>
	</head>
	<body>
		<table cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<tr height=28>
	    		<td width="1101" class="STYLE4">
        			&nbsp;&nbsp;<img src="<%=path%>/images/preferences1.png" width="16" height="16" /> 
        			<span >系统管理</span>
        		</td>
			</tr>
		  	<tr>
		    	<td bgColor=#b1ceef height=1></td>
		    </tr>
		  	<tr height="5px">
		    	<td background=images/shadow_bg.jpg></td>
		    </tr>
		</table>
		<p style="font-size: 12px;color: #1F4A65;padding: 10px">
				&nbsp;&nbsp;&nbsp;&nbsp;
				系统管理是管理系统中的基础数据设置信息，包括供应商、资产类型、资产名称、用户管理等，通过系统管理，可以为系统提供基础数据支持。日志管理主要包括供应商、资产类型、资产名称、用户管理等的增删改查等功能点。
		</p>
    </body>
</html>