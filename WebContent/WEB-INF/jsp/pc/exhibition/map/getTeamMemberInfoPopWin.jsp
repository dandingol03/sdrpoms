<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>重点人</title>
<style>
	/* .ex_main_rightheader_pw4_header{
	 	background:url(/sdrpoms/resources/image/exhibition/map/legend/ex_map_keyperson.png) left 10px top 28px #00b9f6 no-repeat
 	} */
 	.ex_main_rightheader_pw4_header{
	 	background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_header.png) no-repeat;
	 	background-size:100% 100%;
	 	height:160px !important;
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
 	.ex_main_rightheader_pw4_header_left{
 		width:40%;
 		height:100%;
 		background:red;
 		float:left;
 	}
 	.ex_main_rightheader_pw4_header_right{
 		width:60%;
 		float:left;
 	}
 	.ex_main_rightheader_pw4_header_right ul li{
 		color: #fff;
	    text-indent: 10px;
	    font-size: 15px;
	    margin:8px 0;
	    font-family: monospace;
 	}
 	
</style>
</head>
<body>

<div class="ex_main_rightheader_pw4_header">
	<div class="ex_main_rightheader_pw4_header_left">
	
	</div>
	<div class="ex_main_rightheader_pw4_header_right">
		<ul>
			<li>姓名:${teamMemberInfo.name}</li>
			<li>性别:${teamMemberInfo.userGender==1?"男":"女"}</li>
			<li>队员编号:${teamMemberInfo.idNumber}</li>
			<li>联系电话:${teamMemberInfo.userTelephone}</li>
			<li>所属巡防队:${teamMemberInfo.managementUnit}</li>
			<li>职务:${teamMemberInfo.registrationName}</li>
		</ul>
	</div>
	<%-- <img alt="" src="/sdrpoms/resources/image/exhibition/map/legend/ex_map_keyperson.png">
	<span>${teamMemberInfo.name}</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div> --%>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>上岗时间:</p><span>${teamMemberInfo.address }</span></li>
		<li><p>巡防轨迹:</p></li>
		<li>
			<ul id="StartEnd"></ul>
		</li>
		<li><p>实时动态:</p><span></span></li>
		<li><p>上报情况:</p><span></span></li>
		<li><p>呼叫:</p><span>${teamMemberInfo.telephone }</span></li>
	</ul>
</div>
<script type="text/javascript">
	var aa = ${teamMemberInfo.trackList}
	var tempHtml="";
	for(var i=0;i<aa.length;i++){
		tempHtml += "<li style='font-size:14px;cursor: pointer;margin:5px 0;' id="+ aa[i].id +" onclick='showPatrolTrajectory(this.id)' data-coords=" + aa[i].track + ">"
		  		+"开始:" + aa[i].startTime +","+"结束:" + aa[i].endTime + 
				"</li>"
	}
	$('#StartEnd').html(tempHtml)
</script>
</body>
</html>