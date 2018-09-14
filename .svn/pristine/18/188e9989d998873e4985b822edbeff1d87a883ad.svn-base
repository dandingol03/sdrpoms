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
	<img src="${ctx}/resources/image/exhibition/map/legend/ex_main_guardStation.png">
	<span>${policeHouse.address}</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>设置地点:</p><span>${policeHouse.address}</span></li>
		<li><p>经度:</p><span>${policeHouse.lng}</span></li>	
		<li><p>纬度:</p><span>${policeHouse.lat}</span></li>
		<li><p>所属机构:</p><span id="orgId"></span><span id="orgIdX" style="display:block:inline-block;float:left"></span></li>
		<li><p>建筑面积:</p><span>${policeHouse.buildingAcreage}</span></li>
		<li><p>建站情况:</p><span>${policeHouse.buildingSituation}</span></li>
		<li><p>护路站形式:</p><span>${policeHouse.form}</span></li>
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
		<li><p>备注:</p><span>${policeHouse.remark}</span></li>
	</ul>
</div>
<script>
	$(function(){
		var  descOrgName = "${policeHouse.descOrgName}";
		var  orgName = "${policeHouse.orgName}";
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