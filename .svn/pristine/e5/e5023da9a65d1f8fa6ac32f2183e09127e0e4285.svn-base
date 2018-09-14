<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
	table{ 
		width: 325px;
		margin: -13px 6px;
	}
	table tr td{
		border:1px solid rgb(32,103,152);
		background:rgb(2,30,48)
	}
	table tr td:nth-child(1){
		width:88px;
		padding-left:5px;
		height:25px; 
	}
	table tr:nth-child(1) td:nth-child(1){
		background: none;
	}
	table tr td:nth-child(2){
		padding-left:5px;
		color:#00fdf6;
	
	}
</style>
</head>
<body>
<div id="orgInfoName"></div>
<div class="pwAll">
	<div class="h35"></div>
	<div class="close_pwAll" onclick="closePwAll()"></div>
	<%--  console.log(${ orgInfos})  --%>
		<table id="orgPopWin" data-org='${orgJson}' cellpadding="0" cellspacing="0">
			 <tr><td>定位点</td><td id="name"></td></tr>
			 <tr>
				<td>经度</td>
				<td id="lng"></td>
			</tr> 
			<tr>
				<td>纬度</td>
				<td  id="lat" ></td>
			</tr>
			<tr><td  colspan="2" style="text-align:center;">线路情况</td></tr>
			<tr>
				<td>铁路线总数</td>
				<td>${orgInfos.railCount}</td>
			</tr>
			<tr>
				<td>总里程数</td>
				<td  id="orgLength">${orgInfos.orgRailLen}</td>
			</tr>
			<tr>
				<td>高铁线数</td>
				<td>${orgInfos.Pub01Data }</td>
			</tr>
			<tr>
				<td>普通干线</td>
				<td>${orgInfos.Pub03Data }</td>
			</tr>
			<tr>
				<td>其它线路数</td>
				<td>${orgInfos.Pub05Data }</td>
			</tr>
			<tr>
				<td>车站</td>
				<td>${orgInfos.base_info_part_station}</td>
			</tr>
			<tr>
				<td>包含铁路</td>
				<td colspan="3" >
					<c:forEach items="${orgInfos.railNames}" var="orgIdList">
						${orgIdList.railName}
					</c:forEach>
				</td>
			</tr> 
		</table>
		<div class="pwAll_bottom"></div>
	</div>
</body>
</html>