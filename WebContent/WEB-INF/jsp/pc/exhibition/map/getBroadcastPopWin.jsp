<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style>
	#management {
		background: #00b9f6;
	    width: 90px;
	    height: 35px;
	    line-height: 35px;
	    border: none;
	    float: left;
	    border: none;
	    outline: none;
	    color: #fff;
	    letter-spacing: 1px;
	    text-align: center;
	    cursor: pointer;
	    margin-left:110px; 
	}
	.ex_main_rightheader_pw4_thirdul{display:none;}
	.ex_main_rightheader_pw4_thirdul li{
		height:auto !important;
		width:auto !important;
		overflow:hidden;
	}
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
 	button {
	    width: 30px;
	    height: 20px;
	    line-height: 20px;
	    float:left;
 	}
</style>
<script type="text/javascript" src="${ctx}/resources/js/exhibition/pwLo.js"></script>
</head>
<body>
<div class="ex_main_rightheader_pw4_header">
	<c:if test="${defenceBroadcast.status == 01 }">
		<img src="${ctx}/resources/image/exhibition/map/legend/ex_map_broadcast_on.png">
	</c:if> 
	<c:if test="${defenceBroadcast.status == 02 }">
		<img src="${ctx}/resources/image/exhibition/map/legend/ex_map_broadcast_off.png">
	</c:if> 
	<c:if test="${defenceBroadcast.status == 03 }">
		<img src="${ctx}/resources/image/exhibition/map/legend/ex_map_broadcast.png">
	</c:if> 
	<span>${defenceBroadcast.name}</span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>位置:</p><span>${defenceBroadcast.adress }</span></li>
		<li><p>临近线路:</p>
			<span id="aa">
				<c:forEach items="${nearByRails}" var="nearByRails" varStatus="index">
					${nearByRails.railName}
					<c:if test="${index.last==false}">
						、
					</c:if>
				</c:forEach>
			</span>
		</li>
		<li><p>管理单位:</p><span>${defenceBroadcast.orgName}</span></li>
	</ul>
	<div id="igs">
   		<c:choose>
		   <c:when  test="${empty defenceBroadcast.designProfiles}"> 
			   	<img src="${ctx}/resources/image/opaction.png"style="width: 100%;height: 100%;background: red;">
		   </c:when>
		   <c:otherwise>
		   		<c:forEach items="${defenceBroadcast.designProfiles}"  var="designProfiles">
		   			<a class="ig" href="#">
						<img src="${ctx}/file/showPicFile?fileId=${designProfiles}"/>
						<div class="imgNumber">${defenceBroadcast.photosNum}</div>
					</a>
		   		</c:forEach>
		   		<div class="btn btn1"></div>
   				<div class="btn btn2"></div>
		   </c:otherwise>
		</c:choose>
   	</div>
	<ul class="ex_main_rightheader_pw4_secondul">
		<li><p>工作状态:</p>
			<c:if test="${defenceBroadcast.statusName == '故障'}">
				<span id="clickGZ">${defenceBroadcast.statusName}</span>
			</c:if>
			<c:if test="${defenceBroadcast.statusName == '在线'}">
				<span>${defenceBroadcast.statusName}</span>
			</c:if>
			<c:if test="${defenceBroadcast.statusName == '离线'}">
				<span>${defenceBroadcast.statusName}</span>
			</c:if>
		</li>
			<li><p>当前广播语:</p>
			<button onclick="audio('PLAY')">播放</button>
			<button onclick="audio('PREV_SONG')">上</button>
			<button onclick="audio('NEXT_SONG')">下</button>
			<button onclick="audio('ADD_VOLUMN')">+</button>
			<button onclick="audio('MINUS_VOLUMN')">-</button>
		</li>
		<li><p>功能型号:<span>${defenceBroadcast.broTypeName}</span></li>
	</ul>
	<ul class="ex_main_rightheader_pw4_thirdul">
		<li><p>故障时间：</p><span></span></li>
		<li><p>报修时间：</p><span></span></li>
		<li><p>报修人：</p><span></span></li>
		<li><button id="management" onclick="panagementPw()">我要报修</button></li>
	</ul>
</div>
<div class="imgAll">
       <div class="banner">
           <div class="large_box">
               <ul style="width:100%;">
               	<c:forEach items="${defenceBroadcast.designProfiles}"  var="designProfiles">
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
                   <c:forEach items="${defenceBroadcast.designProfiles}"  var="designProfiles">
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
<script type="text/javascript">
	$('#clickGZ').click(function(){
		$('.ex_main_rightheader_pw4_thirdul').fadeIn("6000");
	})
	var number = "${defenceBroadcast.number}"
	function audio(o){
		$.ajax({
			url :"http://58.56.96.170:8009/sdrpoms/exhibition/map/adjustmentBroadcast?number="+number+"&&commands="+o,
			type : 'GET',
		});
	}

</script>
</body>
</html>