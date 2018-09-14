<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>定位点</title>
<style>
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
 	.ex_main_rightheader_pw4_fristul li p{
 		line-height:30px;
 	}
 	.ex_main_rightheader_pw4_fristul li div{
 		float: left;
	    text-indent: 23px;
	    line-height: 30px;
 	}
 	.ex_main_rightheader_pw4_fristul li p{
 		line-height:30px;
 	}
	.ex_main_rightheader_pw4_fristul li p:nth-child(1){
		width:auto;
		text-align:left;
	}
	.ex_main_rightheader_pw4_fristul li p:nth-child(2){
		width:260px;
		text-indent:0;
	}
	.ex_main_rightheader_pw4_fristul li div p:nth-child(1){
		width:80px;
		text-align:right;
	}
	.ex_main_rightheader_pw4_fristul li span{
		width:100%;
		display:inline-block;
		font-size:16px;
	}
</style>
<script type="text/javascript" src="${ctx}/resources/js/exhibition/pwLo.js"></script>
</head>
<body>
<div class="ex_main_rightheader_pw4_header">
	<img src="/sdrpoms/resources/image/exhibition/map/legend/kmlocation.png">
	<span id="kmLocationName"></span><span style="padding:0;" id="Lkm"></span><span style="padding:0">公里</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<ul class="ex_main_rightheader_pw4_fristul">
		<li ><p style="font-size:16px;">所在位置:</p><p id="QK" style="font-size:16px;"></p></li>
		<li style="font-size:18px;font-family: monospace;">附近情况：</li>
		<c:forEach items="${neighbourhoodList}" var="neighbourhoodList" >
		<c:if test="${neighbourhoodList.type=='broadcast'}">
				<li>
					<p ><img src="${ctx}/resources/image/exhibition/map/icon/ex_map_broadcast.png"> 警示柱:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='propaganda'}">
				<li>
					<p ><img  src="${ctx}/resources/image/exhibition/map/icon/ex_main_propaganda.png"> 宣传点:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>		
		<c:if test="${neighbourhoodList.type=='junction'}">
				<li>
					<p><img  src="/sdrpoms/resources/image/exhibition/map/icon/ex_map_junction.png"> 道口:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='danger'}">
				<li>
					<p><img  src="/sdrpoms/resources/image/exhibition/map/icon/danger_tbdo.png"> 隐患:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='bridge'}">
				<li>
					<p><img  src="${ctx}/resources/image/exhibition/map/icon/ex_map_bridge.png"> 桥梁:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='culvert'}">
				<li>
					<p><img  src="${ctx}/resources/image/exhibition/map/icon/ex_map_culvert.png"> 涵洞:</p>
					<p><span>(${neighbourhoodList.name})</span>距离:${neighbourhoodList.len}</p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='tunnel'}">
				<li>
					<p><img  src="/sdrpoms/resources/image/exhibition/map/icon/ex_map_tunnel.png"> 隧道:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='partStation'}">
				<li>
					<p><img  src="${ctx}/resources/image/exhibition/map/icon/ex_map_PartStation.png"> 车站:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
		</c:if>
		<c:if test="${neighbourhoodList.type=='guardStation'}">
				<li>
					<p><img  src="${ctx}/resources/image/exhibition/map/icon/ex_main_guardStation.png"> 工作站:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
				
		</c:if>
		<c:if test="${neighbourhoodList.type=='policeStation'}">
				<li>
					<p><img  src="${ctx}/resources/image/exhibition/map/icon/ex_map_PolicePtation.png"> 警务站:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
				
		</c:if>
		<c:if test="${neighbourhoodList.type=='policeHouse'}">
				<li>
					<p><img  src="${ctx}/resources/image/exhibition/map/icon/ex_map_policeStation.png"> 派出所:</p>
					<p><span>(${neighbourhoodList.name})</span><span>距离:${neighbourhoodList.len}米</span></p>
				</li>
				
		</c:if>
		</c:forEach>
	</ul>
</div>
</body>
</html>