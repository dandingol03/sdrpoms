<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">
<html>
  <head>
    <title>首都护路联防综合业务信息管理系统</title>
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
    <style type="text/css">
    
		.ex_mobile_main{
			width:100%;
			height:100%;
			position: fixed;
			z-index:-1;
			top:0;
			left:0;
			right:0;
			bottom:0;
			background:rgb(11,77,129);
		}
		@media screen and (max-width:320px){
		.ex_mobile_main_content table tr td{
			height:50px;
			color:#fff;
			font-size:2em;
			margin:5px 0;
		}
	}
		 /*  css[竖向定义样式]  */
	@media screen and (orientation:portrait) {
		.ex_mobile_main_content{
			width:90%;
			height:70%;
			position: absolute;
			top:0;
			bottom:0;
			left:0;
			right:0;
			margin:auto;
		}
		.ex_mobile_main_content_content{
			width:100%;
			height:100%;
		}
		.ex_mobile_main_header{
			background:yellow;
			width:359px;
		 	height:340px; 
			position:absolute;
			top:0;
			left:0;
			right:0;
			margin:auto;
			background:url(${ctx}/resources/image/mobile/login/ex_mobile_header_logo.png) no-repeat;
			
		}
		.ex_mobile_main_content table{
		    width: 100%;
		    position: absolute;
		    top:15%;
		    margin:auto;
		    height: 55%;
		}
		.ex_mobile_main_content table tr td{height:70px;color:#fff;font-size:2em;margin:5px 0;}
		.ex_mobile_main_table{
		    width: 90%;
	     	height: calc(100% - 340px);
		    overflow: hidden;
		    position: absolute;
		    bottom: 0;
		    margin: auto;
		    left: 0;
		    right: 0;
		}
		.ex_mobile_main_content table tr td input{
			border-radius:70px; 
			background:none;
			height:100%;
			float:right;
			border:none;
			outline:none;
			color:#fff;
			font-size:1em;
			width:85%;
			background-color:transparent; 
			caret-color:#fff;/*光标颜色*/
		}
		*:focus { outline: none; }
		input:-webkit-autofill , textarea:-webkit-autofill, select:-webkit-autofill {  
		    -webkit-text-fill-color: #ededed !important;  
		    -webkit-box-shadow: 0 0 0px 1000px transparent  inset !important;  
		    background-color:transparent;  
		    background-image: none;  
		    transition: background-color 50000s ease-in-out 0s;  
		}  
		.ex_mobile_main_content table tr:nth-child(1) td {
			border:3px solid  rgb(73,155,217);
			border-radius:70px;
			width:100%;
			background:url(${ctx}/resources/image/mobile/login/ex_mobile_username.png) left 20px center no-repeat;
		}
		.ex_mobile_main_content table tr:nth-child(3) td {
			border:3px solid  rgb(73,155,217);
			border-radius:70px;
			width:100%;
			margin:10px 0;
			background:url(${ctx}/resources/image/mobile/login/ex_mobile_password.png) left 20px center no-repeat;
		}
		.ex_mobile_main_content table tr:nth-child(5) td button{
			border:1px solid  rgb(243,152,0);
			border-radius:70px;
			background:rgb(243,152,0); 
			width:100%;
			height:100%;
			margin:10px 0;
			color:#fff;
			font-size:1.2em;
		}
		.ex_mobile_main_content table tr:nth-child(7) td button{
			/* border:1px solid  rgb(243,152,0); */
			border-radius:70px;
			background:rgb(2,54,94); 
			width:100%;
			height:100%;
			margin:10px 0;
			color:#fff;
			font-size:1.2em;
			border:none;
		}
		
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		  color: #99d2f6; opacity:1; 
		  font-size:35px;
		}
		
		::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color: #99d2f6;opacity:1;
		    font-size:35px;
		}
		
		input:-ms-input-placeholder{
		    color: #99d2f6;opacity:1;
		    font-size:35px;
		}
		
		input::-webkit-input-placeholder{
		    color: #99d2f6;opacity:1;
		    font-size:35px;
		}
	}
	
	/*   css[横向定义样式]  */
	@media screen and (orientation:landscape) {
    	.ex_mobile_main_content{
			width:90%;
			height:70%;
			position: absolute;
			top:0;
			bottom:0;
			left:0;
			right:0;
			margin:auto;
		}
		.ex_mobile_main_content_content{
			width:100%;
			height:100%;
		}
		.ex_mobile_main_header{
			background:yellow;
			width:359px;
		 	height:340px; 
			position:absolute;
			top:0;
			left:0;
			right:0;
			margin:auto;
			background:url(${ctx}/resources/image/mobile/login/ex_mobile_header_logo.png) no-repeat;
			
		}
		.ex_mobile_main_content table{
		    width: 100%;
		    position: absolute;
		    top:15%;
		    margin:auto;
		    height: 55%;
		}
		.ex_mobile_main_content table tr td{height:70px;color:#fff;font-size:2em;margin:5px 0;}
		.ex_mobile_main_table{
		    width: 90%;
	     	height: calc(100% - 340px);
		    overflow: hidden;
		    position: absolute;
		    bottom: 0;
		    margin: auto;
		    left: 0;
		    right: 0;
		}
		.ex_mobile_main_content table tr td input{
			border-radius:70px; 
			background:none;
			height:100%;
			float:right;
			border:none;
			outline:none;
			color:#fff;
			font-size:1em;
			width:85%;
		}
		.ex_mobile_main_content table tr:nth-child(1) td {
			border:3px solid  rgb(73,155,217);
			border-radius:70px;
			width:100%;
			background:url(${ctx}/resources/image/mobile/login/ex_mobile_username.png) left 20px center no-repeat;
		}
		.ex_mobile_main_content table tr:nth-child(3) td {
			border:3px solid  rgb(73,155,217);
			border-radius:70px;
			width:100%;
			margin:10px 0;
			background:url(${ctx}/resources/image/mobile/login/ex_mobile_password.png) left 20px center no-repeat;
		}
		.ex_mobile_main_content table tr:nth-child(5) td button{
			border:1px solid  rgb(243,152,0);
			border-radius:70px;
			background:rgb(243,152,0); 
			width:100%;
			height:100%;
			margin:10px 0;
			color:#fff;
			font-size:1.2em;
		}
		.ex_mobile_main_content table tr:nth-child(7) td button{
			/* border:1px solid  rgb(243,152,0); */
			border-radius:70px;
			background:rgb(2,54,94); 
			width:100%;
			height:100%;
			margin:10px 0;
			color:#fff;
			font-size:1.2em;
			border:none;
		}
		
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		  color: #99d2f6; opacity:1; 
		  font-size:35px;
		}
		
		::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color: #99d2f6;opacity:1;
		    font-size:35px;
		}
		
		input:-ms-input-placeholder{
		    color: #99d2f6;opacity:1;
		    font-size:35px;
		}
		
		input::-webkit-input-placeholder{
		    color: #99d2f6;opacity:1;
		    font-size:35px;
		}
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
		<div class="ex_mobile_main">
			<div class="ex_mobile_main_content">
				<div class="ex_mobile_main_content_content">
					<div class="ex_mobile_main_header"></div>
					<div class="ex_mobile_main_table">
					<form id="loginForm" action="${ctx}/j_spring_security_check" method="post">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="2"><input type="text" name="j_username" placeholder="用户名"></td>
							</tr>	
							<tr><td colspan="2" style="height:20px;"></td></tr>
							<tr>
								<td colspan="2"><input type="password" name="j_password" placeholder="密码"> </td>
							</tr>
							<tr><td colspan="2" style="height:20px;"></td></tr>
							<tr>
								<td colspan="2"><button>登录</button></td>
							</tr>
							<tr><td colspan="2" style="height:20px;"></td></tr>
							<tr>
								<td colspan="2"><button type="reset">清空</button></td>
							</tr>
						</table>
					</form>
					</div>
				</div>
			</div>
		</div>
  </body>
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
</html>