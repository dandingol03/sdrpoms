<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>桥梁</title>
<style>
 /* 	.ex_main_rightheader_pw4_header{
	 	background:url(${ctx}/resources/image/exhibition/map/legend/ex_map_bridge.png) left 10px top 27px #00b9f6 no-repeat;
 	} */
	.ex_main_rightheader_pw4_header{
	 	background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_header.png) no-repeat;
	 	background-size:100% 100%;
 	}
 	.ex_main_rightheader_pw4_content{
 		background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_content.png) no-repeat;
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
<script type="text/javascript" src="${ctx}/resources/js/exhibition/pwLo.js"></script>
</head>
<body>
<div class="ex_main_rightheader_pw4_header">
	<img src="${ctx}/resources/image/exhibition/map/icon/ex_map_monitor.png">
	<span>${video.address}</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>设置地点:</p><span>${video.address}</span></li>
		<li><p>经度:</p><span>${video.lng}</span></li>	
		<li><p>纬度:</p><span>${video.lat}</span></li>
		<li><p>所属机构:</p><span id="orgId"></span><span style="display:block:inline-block;float:left" id="orgIdX"></span></li>
		<li><p>是否可调向:</p><span>${video.isYaw}</span></li>
		<li><p>视屏图像是否整合到区、街(乡镇)监控平台:</p><span>${video.isIntegration}</span>
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
		<li><p>备注:</p><span>${video.remark}</span>
	</ul>
</div>
<script>
	$(function(){
		var  descOrgName = "${video.descOrgName}";
		var  orgName = "${video.orgName}";
		if(descOrgName==orgName){
			$('#orgId').html(orgName);
		}else{
			$('#orgId').html(descOrgName);
			$('#orgIdX').html(orgName);
		}
	})
</script>
</body>
</html>