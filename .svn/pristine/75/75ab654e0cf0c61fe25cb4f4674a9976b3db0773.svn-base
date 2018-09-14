<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>周边场所</title>
<style>
	.pwAll_img{border-bottom:1px solid #efefef;width:100%;height:auto;overflow:hidden;padding:0;}
	.pwAll_header{
		background:url(${ctx}/resources/image/exhibition/map/legend/ex_map_tunnel.png) left 10px top 10px #00b9f6 no-repeat;
	}
	.pwAll_firstUl li p:nth-child(1){width:23% !important;float:left;}
	.pwAll_firstUl li p:nth-child(2){width:77% !important;word-break: break-all;}
	.pwAll_firstUl p{justify-content: center;}
</style>
</head>
<body>
<div>${peripheralPlace.name }</div>
<div class="pwAll" id="tunnelPopWin" data-tunnel='${tunnelJson }'>
	<!-- <div class="close_pwAll" onclick="closePwAll()"></div> -->
	<div class="pwAll_header">${peripheralPlace.name }<div class="colseMapPw" onclick="colseMapPw()"></div></div>
	<div class="pwAll_img">
		
	
	</div>	
	<ul class="pwAll_firstUl">
		<li><p>地址:</p><p>${peripheralPlace.address}</p></li>
		<li><p>责任人:</p><p>${peripheralPlace.charger}</p></li>
		<li><p>联系电话:</p><p>${peripheralPlace.telephone}</p></li>
		<li><p>临近线路:</p>
			<p>
				<c:forEach items="${neighbourhoodList}" var="neighbourhoodList" varStatus="index">
					${neighbourhoodList.name}
					<c:if test="${index.last==false}">
						、
					</c:if>
				</c:forEach>
			</p>
		</li>
		<li><p>基本情况描述:</p><p>${peripheralPlace.remark}</p></li>
	</ul>
</div>
</body>
</html>
