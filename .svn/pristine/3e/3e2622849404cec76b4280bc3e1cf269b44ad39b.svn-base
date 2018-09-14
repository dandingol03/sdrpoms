<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style>
	.pwAll_firstUl{float:left;width:70px;}
	.pwAll_secondUl{float:right;width:180px;}
	.pwAll_firstUl li{float:none;width:100%;height:auto;}
	.pwAll_header{
		background:url(${ctx}/resources/image/exhibition/map/legend/ex_map_phone.png) left 10px top 22px #00b9f6 no-repeat;
	}
	
	.pwAll_secondUl p{width:auto !important;float:left !important;}
	.pwAll_secondUl span{text-indent: 7px;word-break: break-word;display: inherit; float:none !important}
</style>
</head>
<body>
<div>${user.USER_NAME }</div>
<div class="pwAll" id="userPopWin" data-user='${userJson }'>
	<div class="pwAll_header">${user.USER_NAME }<div class="colseMapPw" onclick="colseMapPw()"></div></div>
	<ul class="pwAll_firstUl">
		<li><img></li>
		<li><img></li>
	</ul>
	<ul class="pwAll_secondUl">
		<li><p>姓名:</p><span>${user.USER_NAME }</span></li>
		<li><p>性别:</p></li>
		<li><p>单位:</p><span>${user.orgName }</span></li>
		<li><p>职务:</p></li>
		<li><p>联系电话:</p><span>${user.QQ_WEIXIN }</span></li>
		<li><p>上线时间:</p></li>
	</ul>
</div>
</body>
</html>