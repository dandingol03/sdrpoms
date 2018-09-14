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
<div class="ex_main_rightheader_pw4_header">
	<img alt="" src="/sdrpoms/resources/image/exhibition/map/legend/ex_map_keyperson.png">
	<span>${keyperson.name}</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<div id="igs">
   		<c:choose>
		   <c:when  test="${empty keyperson.designProfiles}"> 
			   	<img src="${ctx}/resources/image/opaction.png"style="width: 100%;height: 100%;background: red;">
		   </c:when>
		   <c:otherwise>
		   		<c:forEach items="${keyperson.designProfiles}"  var="designProfiles">
		   			<a class="ig" href="#">
						<img src="${ctx}/file/showPicFile?fileId=${designProfiles}"/>
						<div class="imgNumber">${keyperson.photosNum}</div>
					</a>
		   		</c:forEach>
		   		<div class="btn btn1"></div>
   				<div class="btn btn2"></div>
		   </c:otherwise>
		</c:choose>
   	</div>
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>位置:</p><span>${keyperson.presentAddress }</span></li>
		<li><p>姓名:</p><span>${keyperson.name }</span></li>
		<li><p>性别:</p><span>${keyperson.gender eq 0?"男":"女"}</span></li>
		<li><p>单位:</p><span></span></li>
		<li><p>职务:</p><span></span></li>
		<li><p>联系电话:</p><span>${keyperson.telephone }</span></li>
		<li><p>家庭联系电话:</p><span>${keyperson.familyTelephone}</span></li>
		<li><p>上线时间:</p><span></span></li>
	</ul>
</div>
<div class="imgAll">
       <div class="banner">
           <div class="large_box">
               <ul style="width:100%;">
               	<c:forEach items="${keyperson.designProfiles}"  var="designProfiles">
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
                   <c:forEach items="${keyperson.designProfiles}"  var="designProfiles">
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