<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>隐患点</title>
<style>
	button {
		background: rgb(10,40,62);
	    width: 75px;
	    height: 22px;
	    line-height: 22px;
	    border: none;
	    float: left;
	    border: none;
	    outline: none;
	    color: #fff;
	    letter-spacing: 5px;
	    text-align: center;
	    cursor: pointer;
	    margin:0 15px;
	}
	.ex_main_rightheader_pw4_fristul li{margin:15px 0!important}
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
<c:if test="${dangerInfo.handleStatus == 01 }"> 
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/danger_tbdoB.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${dangerInfo.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
</c:if> 
<c:if test="${dangerInfo.handleStatus == 02 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/danger_processingB.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${dangerInfo.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
</c:if> 
<c:if test="${dangerInfo.handleStatus == 03 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/danger_resolvedB.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${dangerInfo.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
</c:if> 
<c:if test="${dangerInfo.handleStatus == 04 }">
		<div class="ex_main_rightheader_pw4_header" style="background:url(${ctx}/resources/image/exhibition/map/legend/danger_unsolvedB.png) left 10px top 27px rgb(10,40,62) no-repeat;"><span>${dangerInfo.name }</span><div class="ex_main_rightheader_closepw4" onclick="closePw()"></div></div>
</c:if> 
<div class="ex_main_rightheader_pw4_content">
	<div id="igs">
   		<c:choose>
		   <c:when  test="${empty dangerInfo.designProfiles}"> 
			   	<img src="${ctx}/resources/image/opaction.png"style="width: 100%;height: 100%;background: red;">
		   </c:when>
		   <c:otherwise>
		   		<c:forEach items="${dangerInfo.designProfiles}"  var="designProfiles">
		   			<a class="ig" href="#">
						<img src="${ctx}/file/showPicFile?fileId=${designProfiles}"/>
						<div class="imgNumber">${dangerInfo.photosNum}</div>
					</a>
		   		</c:forEach>
		   		<div class="btn btn1"></div>
   				<div class="btn btn2"></div>
		   </c:otherwise>
		</c:choose>
   	</div>
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>位置:</p><span></span></li>
		<li><p>临近路线:</p>
			<span>
				<c:forEach items="${nearByRails}" var="nearByRails" varStatus="index">
					${nearByRails.railName}
					<c:if test="${index.last==false}">
						、
					</c:if>
				</c:forEach>
			</span>
		</li>
		<li><p>隐患类型:</p><span>${dangerInfo.dangeTypeName }</span></li>
		<li><p>入库时间:</p><span>${dangerInfo.reportTime }</span></li>
		<li><p>处置状态:</p><span style="float:left">${dangerInfo.handleStatusName }</span><button id="management" onclick="panagementPw()">处置</button></li>
	</ul>
	<ul class="ex_main_rightheader_pw4_secondul" style="border:none;">
		<li><p style="font-size:16px;">隐患描述:</p><span>${patrolDangerInfo.description}</span></li>
	</ul>
</div>

<div class="imgAll">
       <div class="banner">
           <div class="large_box">
               <ul style="width:100%;">
               	<c:forEach items="${dangerInfo.designProfiles}"  var="designProfiles">
                    <li style="height:350px;">
                        <img src="${ctx}/file/showPicFile?fileId=${designProfiles}">
                    </li>
                   </c:forEach>
               </ul>
           </div>
           <div class="small_box">
               <span class="btn3 left_btn"></span>
               <div class="small_list">
                   <ul>
                   <c:forEach items="${dangerInfo.designProfiles}"  var="designProfiles">
                       <li class="on">
                           <img src="${ctx}/file/showPicFile?fileId=${designProfiles}" width="110" height="73">
                           <div class="bun_bg"></div>
                       </li>
                       </c:forEach>
                   </ul>
               </div>
               <span class="btn3 right_btn"></span>
           </div>
       </div>
       <div class="close_imgAll"></div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/exhibition/carousel.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.ig').click(function(){
		$('.imgAll').show();
	})
	$('.close_imgAll').click(function(){
		$('.imgAll').hide();
	})
	   /* 商品轮播图（带缩略图的轮播效果） */
    $(".banner").thumbnailImg({
        large_elem: ".large_box",
        small_elem: ".small_list",
        left_btn: ".left_btn",
        right_btn: ".right_btn"
    });
})
</script>
</body>
</html>