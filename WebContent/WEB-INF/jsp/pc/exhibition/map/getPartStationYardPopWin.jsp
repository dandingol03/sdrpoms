<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>站场</title>
<style>
	.pwAll_firstUl li{height:auto;float:left;margin:2px 5px;width:100%;}
	.pwAll_firstUl li{height:auto;float:left;margin:2px 5px;width:100%;}
	.pwAll_firstUl li p:nth-child(1){width:23% !important;float:left;}
	.pwAll_firstUl li p:nth-child(2){width:77% !important;word-break: break-all;}
	.ex_main_rightheader_pw4_header{
	 	background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_header.png) no-repeat;
	 	background-size:100% 100%;
 	}
 	.ex_main_rightheader_closepw4{
 		background:url(${ctx}/resources/image/exhibition/main/closeBtn.png) no-repeat !important;
 		background-size:100% 100%;
 		width:40px!important;
 		height:40px!important;
 		position: absolute!important;
 		right:0!important;
 		top:0!important;
 	}
</style>
</head>
<body>
<%--  <div>${stationYard.name }</div>
 <div class="pwAll">
	<div class="pwAll_header">${stationYard.name }</div>
	<div class="pwAll_img"><img src=""></div>
	<ul class="pwAll_firstUl">
		<li><p>地址:</p><p>${stationYard.address}</p></li>
		<li style="width:100%;"><p>所属单位:</p><p>${stationYard.policeSubstation}</p></li>
		<li><p>联系电话:</p><p>${stationYard.telephone}</p></li>
		<li><p >所在线路:</p>
			<c:forEach items="${railList}" var="railList">
					<p>${railList.name}</p>
			</c:forEach>
		</li>
	</ul>
</div> --%>111
</body>
</html>