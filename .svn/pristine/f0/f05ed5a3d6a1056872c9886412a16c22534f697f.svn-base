<%@ page language="java" pageEncoding="UTF-8" import="com.pc.bsp.common.util.PhoneUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
	if(PhoneUtils.isFromMobile(request)) {
		response.sendRedirect("mobileIndex.jsp");
	}
%>
<!doctype html>
<html>
  <head>
    <title>首都铁路护路联防综合业务信息管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="JHVersion1.1-layout"> 
	<link rel="shortcut icon" href="${ctx}/resources/image/project.ico" type="image/x-icon" />
	<!--  360浏览器专用 -->
	<meta name="renderer" content="webkit"/>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/reset.css">
    <link rel="stylesheet" href="${ctx}/resources/css/exhibition/animite/idangerous.swiper2.7.6.css">
    <link rel="stylesheet" href="${ctx}/resources/css/exhibition/animite/animate.min.css">
    <link rel="stylesheet" href="${ctx}/resources/css/exhibition/animite/style.css">
    <style type="text/css">
   		.h120{height: 80px}
		.h50{height: 50px;}
		.h200{height: 50px;}
		.h0{
			height: 40px;
			
		}
		.ex_main{
			width: 100%;
			height: 100%;
			position: fixed;
			z-index: 1;
			background: url(${ctx}/resources/image/login/ex_main_backgrounds.png) no-repeat;
			background-size: 100% 100%;
		}
		.ex_main_sectionall{
			width: 100%;
			height: 690px;
			margin: 0 auto;
			overflow: hidden;
			position: absolute;
			margin-top: -230px;
			top: 30%;
			left: 0;
			right: 0;
		}
		.ex_main_header_img{
			width:173px;
			height:166px;
			margin: 0 auto;
			background: url(${ctx}/resources/image/login/logos.png) center no-repeat; 
			background-size: 100% 100%;
		}
		.ex_main_header_font{
			width:999px;
			height:47px;
			margin: 0 auto;
			background: url(${ctx}/resources/image/login/ex_main_header_font.png) center no-repeat;
			background-size:100% 100%; 
			
		}
		.ex_main_content{
			width: 100%;
			height: 306px;
			/* background: url(${ctx}/resources/image/login/ex_main_logo_xian.png) 0 no-repeat; */
			background-size: 100% 5%;
			overflow: hidden;
		}
		.ex_main_from{
			width: 700px;
			height:300px;
			background: url(${ctx}/resources/image/login/ex_main_logofrombg.png) no-repeat; 
			background-size: 100% 100%;
			margin: 0 auto;
			position: relative;
		}
		.ex_main_footer{
			width: 957px;
			height:36px;
			/* background: url(${ctx}/resources/image/login/ex_main_footer2.png) no-repeat; */
			background-size: 100% 100%;
			margin: 0 auto;
		}
		.ex_main_from table{
			width: 575px;
			height: 200px;
			margin: 20px auto;
		}
	
		.ex_main_from table tr td input{
			width: 365px;
		    height: 35px;
		    outline: none;
		    background:url(${ctx}/resources/image/login/ex_main_login_fromtdbg.png);
		    color:#fff;
		    border:none;
		    background-size:100% 100%;
		    padding-left:10px;
		}
		.ex_main_from table tr td:nth-child(1){
			width: 100px;
			height: 27px;
		}
			table tr:nth-child(1) td:nth-child(1){
			width: 141px;
			height: 27px;
			background: url(/sdrpoms/resources/image/login/username.png) top 8px left 55px no-repeat;
		
		}
		table tr:nth-child(3) td:nth-child(1){
			width: 141px;
			height: 27px;
			background: url(/sdrpoms/resources/image/login/password.png) top 8px left 55px  no-repeat;
		}
		.btn{
		  	width: 145px;
		    height: 40px;
		    background: url(/sdrpoms/resources/image/login/ex_mian_logofrombtn.png) no-repeat;
		    cursor: pointer;
		    background-size: 100% 100%;
		    border: none;
		    outline: none;
		    cursor: pointer;
		    margin:0 30px;
		}
		.btn:hover{
		  	width: 145px;
		    height: 40px;
		    background: url(/sdrpoms/resources/image/login/ex_mian_logofrombtn_hover.png) no-repeat;
		    cursor: pointer;
		    background-size: 100% 100%;
		    border: none;
		    outline: none;
		    cursor: pointer;
		    margin:0 30px;
		}
		.clearbtn{
		  	width: 145px;
		    height: 40px;
		    background: url(/sdrpoms/resources/image/login/ex_mian_logofromclearbtn.png) no-repeat;
		    cursor: pointer;
		    background-size: 100% 100%;
		    border: none;
		    outline: none;
		    cursor: pointer;
		    margin:0 30px;
		}
		.clearbtn:hover{
		  	width: 145px;
		    height: 40px;
		    background: url(/sdrpoms/resources/image/login/ex_mian_logofromclearbtn_hover.png) no-repeat;
		    cursor: pointer;
		    background-size: 100% 100%;
		    border: none;
		    outline: none;
		    cursor: pointer;
		    margin:0 30px;
		}
		#loginForm{
			position: absolute;
		    top: 0;
		    left: 0;
		    right: 0;
		    bottom: 0;
		    width: 600px;
		    height: 200px;
		    margin: auto;
		}
		.ex_main_footer{
			width:1366px;
			height:26px;
		 	background: url(/sdrpoms/resources/image/login/ex_main_footerbg.png)no-repeat;
	   		background-size: 100% 100%;
		}
		.ex_main_header_logoimg{
			width:200px;
			height:192px;
		 	position: absolute;
		    left: 0;
		    right: 0;
		    margin:0 auto;
		}
		.ex_main_header_fontimg{
			width: 1285px;
		    height: 60px;
		    margin: 0 auto;
		    position: absolute;
		    left: 0;
		    right: 0;
		    top:220px;
		}
		.Basicdata:hover{
			background:url(${ctx}/resources/image/exhibition/animite/Basicdata_hover.png);
		}
    </style>
    <script type="text/javascript">
	//支持Enter键登录
	document.onkeydown = function(e){
		if(!e) e = window.event;
		if((e.keyCode || e.which) == 13){
			var obtnLogin=document.getElementById("submitBtn");
			obtnLogin.focus();
		}
	};
	$(function(){
		$('.username').focus();
	});
	</script>
  </head>
<body>
<div class="swiper-container">
<!-- <a class="arrow-left" href="#"></a> 
<a class="arrow-right" href="#"></a> -->
  <div class="swiper-wrapper">
  		  
    <div class="swiper-slide slide1">
        <div class="inner">
        <!-- zoomInDown -->
          <!-- flipInY -->
       		<div class="ani img s1" 
		        swiper-animate-effect="fadeInDown" 
                swiper-animate-duration="1.5s" 
                swiper-animate-delay="1.2s" style="margin:150px auto;">
	  			<img  src="${ctx }/resources/image/exhibition/animite/logos.png" 
	               class="ex_main_header_logoimg">
	          	<img  src="${ctx }/resources/image/exhibition/animite/ex_main_header_font.png" 
	               class="ex_main_header_fontimg">
  			</div>
       		<div class="ani img s2" 
		        swiper-animate-effect="fadeInUp" 
                swiper-animate-duration="2s" 
                swiper-animate-delay="1.5s"
                style="top:230px;"
                >
                <ul>
                <!-- Basicdata-selected -->
                  <li data-img="Basicdata" class="Basicdata iconimg"></li>
                  <li data-img="Videosurveillance" class="Videosurveillance iconimg"></li>
                  <li data-img="Patrolmanagement" class="Patrolmanagement iconimg"></li>
                  <li data-img="Hiddendanger_disposal" class="Hiddendanger_disposal iconimg"></li>
                  <li data-img="officeOa" class="officeOa iconimg"></li>
                  <li data-img="training" class="training iconimg"></li>
                  <li data-img="Judgment_analysis" class="Judgment_analysis iconimg"></li>
                  <li data-img="Videoconferencng" class="Videoconferencng iconimg"></li>
                  <li data-img="MochaITOM" class="MochaITOM iconimg"></li>
                </ul>
          </div>
          <img  src="${ctx }/resources/image/exhibition/animite/bgmap.png" style="top:25px"
                class="ani img s3" 
                swiper-animate-effect="zoomIn"
                swiper-animate-duration="2s" 
                swiper-animate-delay="0.5s">
          <div class="loginfrom" style="display:block" >
			<div class="ex_main_from" style="margin:20px auto;">
				<form id="loginForm" action="${ctx}/j_spring_security_check" method="post" >
					<table>
						<tr>
							<td ></td>
							<td><input type="text" name="j_username"></td>
						</tr>
						<tr><td style="height:5px;" colspan="3"></td></tr>
						<tr>
							<td ></td>
							<td><input type="password" name="j_password"></td>
						</tr>
						<tr><td style="height:15px;" colspan="3"></td></tr>
						<tr>
							<td style="height:35px;" colspan="2">
								<button class="btn" id="submitBtn" style="margin-left:143px;"></button>
								<button class="clearbtn" id="Btn" type="reset"></button>
							</td>
						</tr> 
						<tr style="height:15px;" >
							<td id="loginMessage" colspan="2" style=" color:#fff;text-align:right;font-weight:bold">您已退出系统，请重新登录！</td>
						</tr>
					</table>
				</form> 
			</div>
          </div>
        </div>
      </div>
  </div>
  <div class="pagination" style="display: none;"></div>
</div>
	<script src="${ctx}/resources/js/animite/jquery-1.10.1.min.js"></script>
	<script src="${ctx}/resources/js/animite/idangerous.swiper2.7.6.min.js"></script>
	<script src="${ctx}/resources/js/animite/swiper.animate1.0.2.min.js"></script>
    <script type="text/javascript">
       /*  $('.iconimg').mouseover(function(){
            var sibs = $(this).siblings('.iconimg');
            for (var i = sibs.length - 1; i >= 0; i--) {
              var $sib = $(sibs[i]);
              var img = $sib.data('img');
              $sib.css({"background":"url(${ctx }/resources/image/exhibition/animite/" + img +".png)","background-size":"100% 100%"});
            }
            var img = $(this).data('img');
            $(this).css({"background":"url(${ctx }/resources/image/exhibition/animite/" + img +"_hover.png)","background-size":"100% 100%"});
           
        }); */
        /* $('.iconimg').click(function(){
            $('.loginfrom').css({display:"block"})
            $('.s2').animate({margin:"830px auto"})
        }); */
    </script>
    <script>        
      var mySwiper = new Swiper ('.swiper-container', {
    	  pagination: '.pagination',
    	  paginationClickable :true,
    	  autoplay : 10000,
          speed:1,
          onlyExternal:true,
         //autoplayDisableOnInteraction : false,
      onInit: function(swiper){ //Swiper2.x的初始化是onFirstInit
        swiperAnimateCache(swiper); //隐藏动画元素 
        swiperAnimate(swiper); //初始化完成开始动画
      }, 
      onSlideChangeEnd: function(swiper){ 
        swiperAnimate(swiper); //每个slide切换结束时也运行当前slide动画
      } 
      })    
      $('.arrow-left').on('click', function(e){
        e.preventDefault()
        mySwiper.swipePrev()
      })
      $('.arrow-right').on('click', function(e){
        e.preventDefault()
        mySwiper.swipeNext()
      })    
	</script> 
	<script  type="text/javascript">
		//提交表单
		$('#submitBtn').click(function(){
			if($('.username').val() == ''){
				//阻塞状态弹出
				$('#loginMessage').html("请填写用户名！");
				$('.username').focus();
			}else if($('.password').val() == ''){
				//阻塞状态弹出
				$('#loginMessage').html("请填写登录密码！");
				$('.password').focus();
			}else{
				$('#loginForm').submit();
			}
		});	
		$('#clearBtn').click(function(){
			$('.username').val('');
			$('.password').val('');
		});
	</script>
  </body>
</html>