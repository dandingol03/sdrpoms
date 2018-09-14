<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>周边场所</title>
<style></style>
<script type="text/javascript" src="${ctx}/resources/js/exhibition/pwLo.js"></script>
<style>
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
<%-- <div>${peripheralPlace.name }</div>
<div class="pwAll" id="tunnelPopWin" data-tunnel='${tunnelJson }'> --%>
  	<c:if test="${peripheralPlace.category == 01 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/residence.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 02 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/company.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 03 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/market.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 04 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/ex_map_school.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 05 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/mining.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 06 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/constructionSite.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 07 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/riversAndLakes.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 08 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/commercial.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 09 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/roomAndBoard.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 10 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/breed.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 11 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/plant.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 12 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/gasStation.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	 <c:if test="${peripheralPlace.category == 13 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/hehu.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
	<c:if test="${peripheralPlace.category == 14 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/other.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${peripheralPlace.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
	</c:if> 
<div class="ex_main_rightheader_pw4_content">
	<div id="igs">
  		<c:choose>
		   <c:when  test="${empty peripheralPlace.designProfiles}"> 
			   	<img src="${ctx}/resources/image/opaction.png"style="width: 100%;height: 100%;background: red;">
		   </c:when>
		   <c:otherwise>
		   		<c:forEach items="${peripheralPlace.designProfiles}"  var="designProfiles">
		   			<a class="ig" href="#">
						<img src="${ctx}/file/showPicFile?fileId=${designProfiles}"/>
						<div class="imgNumber">${peripheralPlace.photosNum}</div>
					</a>
		   		</c:forEach>
		   		<div class="btn btn1"></div>
   				<div class="btn btn2"></div>
		   </c:otherwise>
		</c:choose>
   	</div>
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>地址:</p><span id="aaaa">${peripheralPlace.address}</span></li>
		<li><p>责任人:</p><span>${peripheralPlace.charger}</span></li>
		<li><p>联系电话:</p><span>${peripheralPlace.telephone}</span></li>
		<li><p>临近线路:</p>
			<span>
				<c:forEach items="${nearByRails}" var="nearByRails" varStatus="index">
					${nearByRails.railName}
					<c:if test="${index.last==false}">
						、
					</c:if>
				</c:forEach>
			</span>
		</li>
		<li><p>基本情况描述:</p><span>${peripheralPlace.description}</span></li>
	</ul>
</div>
</body>
</html>