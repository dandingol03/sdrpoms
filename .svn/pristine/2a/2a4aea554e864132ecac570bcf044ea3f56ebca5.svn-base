<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>隧道</title>
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
</style>
<script type="text/javascript" src="${ctx}/resources/js/exhibition/pwLo.js"></script>
</head>
<body>
<div class="ex_main_rightheader_pw4_header">
	<img src="/sdrpoms/resources/image/exhibition/map/legend/ex_map_tunnel.png">
	<span>${tunnel.name}</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<div id="igs">
   		<c:choose>
		   <c:when  test="${empty tunnel.designProfiles}"> 
			   	<img src="${ctx}/resources/image/opaction.png"style="width: 100%;height: 100%;background: red;">
		   </c:when>
		   <c:otherwise>
		   		<c:forEach items="${tunnel.designProfiles}"  var="designProfiles">
		   			<a class="ig" href="#">
						<img src="${ctx}/file/showPicFile?fileId=${designProfiles}"/>
						<div class="imgNumber">${tunnel.photosNum}</div>
					</a>
		   		</c:forEach>
		   		<div class="btn btn1"></div>
   				<div class="btn btn2"></div>
		   </c:otherwise>
		</c:choose>
   	</div>
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>位置:</p><span>${tunnel.descOrgName}${tunnel.orgName}</span></li>
		<li><p>管理单位:</p><span>${tunnel.orgName}</span></li>	
		<li><p>联系电话:</p><span>${tunnel.telephone}</span></li>
		<li><p>所在线路:</p>
			<span>
				<c:forEach items="${nearByRails}" var="nearByRails" varStatus="index">
					${nearByRails.railName}
					<c:if test="${index.last==false}">
						、
					</c:if>
				</c:forEach>
			</span>
		</li>
		<li><p>长度:</p><span>${tunnel.lengthStr}</span></li>
	</ul>
</div>
<div class="imgAll">
       <div class="banner">
           <div class="large_box">
               <ul style="width:100%;">
               	<c:forEach items="${tunnel.designProfiles}"  var="designProfiles">
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
                   <c:forEach items="${tunnel.designProfiles}"  var="designProfiles">
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