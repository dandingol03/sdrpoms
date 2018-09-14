<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>在线干部</title>
<style>
 	.ex_main_rightheader_pw4_header{
	 	background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_header.png) no-repeat;
	 	background-size:100% 100%;
	 	height:190px !important;
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
 	.ex_main_rightheader_pw4_header_left{
 		width:40%;
 		height:100%;
 		background:url(${ctx}/resources/image/exhibition/map/ex_mapPw_member.png) center no-repeat;
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
	.ex_mapPw_chat{
		width:100%;
		height:200px;
		background: url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_content.png) no-repeat;
		display:none;
		background-size:100% 100%;
	}
 	.ex_mapPw_chat_bottom{
 		width:100%;
 		height:30px;
 		background:black;
 		float:left;
 	}
 	.ex_mapPw_chat_bottom input{
 		border:none;
 		width:60%;
 		float:left;
 		height:30px;
 	}
	.ex_mapPw_chat_bottom button{
		width:74px;
		height:30px;
		color:#fff;
		background:#000;
		float:left;
		line-height:30px;
		border:none;
		cursor: pointer;
		margin:0 1px;
	}
	.ex_mapPw_chat_left{
		width:40%;
		height:100%;
		float:left;
	}
	.ex_mapPw_chat_right{
		width:60%;
		height:100%;
		float:right;
		overflow:auto;
		color:#fff;
	}
	video{
	width:100%;height:100%;
	background:url(${ctx}/resources/image/exhibition/map/ex_mapPw_menber.png) center no-repeat;
	}
	button{
		width: 50px;
	    height: 19px;
	    line-height: 16px;
	    color: #fff;
	    font-size: 14px;
	    float: left;
	    margin: 0 5px;
	    background:#000;
    }
</style>
</head>
<body>
<%-- console.log(${ cardeInfo.id}) --%>
<div class="ex_main_rightheader_pw4_header">
	<div class="ex_main_rightheader_pw4_header_left">
		<video id="M"></video>
	</div>
	<div class="ex_main_rightheader_pw4_header_right">
		<ul>
			<li>姓名:${cardeInfo.name}</li>
			<li>性别:${cardeInfo.userGender==1?"男":"女"}</li>
			<li>单位:${cardeInfo.userDepartmentName}</li>
			<li>职务:${cardeInfo.userDuty}</li>
			<li>联系电话:${cardeInfo.userTelephone}</li>
			<li>上线时间:</li>
			<li><p style="float:left">实时通讯:</p><button onclick="chat()">开启</button><button onclick="closeChat()">关闭</button></li>
		</ul>
	</div>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<!-- 视频通讯窗口 -->
<div class="ex_mapPw_chat">
	
		
	<!-- 他人视频窗口 -->
	<div class="ex_mapPw_chat_left">
		<video class="ex_mapPw_chat_text"  id="O"></video>
	</div>
	<!-- 聊天文字/分享图片窗口 -->
	<div class="ex_mapPw_chat_right" id="textDiv"></div>
	<div class="ex_mapPw_chat_bottom">
		<input type="text" id="sendIpt"><button id="sendOut" onclick="sendOut()">发送信息</button><button id="imgShare" onclick="imgShare()">图片分享</button>
	</div>
</div> 
<script type="text/javascript">
		//在线队员刷新数据
		 function chat(){
			 $.ajax({
					url : ' /sdrpoms/mobile/org/comTree?pid=110',
					type : 'post',
					dataType : 'json',
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("响应状态:[" + XMLHttpRequest.status + "]-");
						console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
						console.log("异常情况:[" + textStatus + "]");
					},
					success : function(data) {
						console.log('-------------------',data)
					}
				});	
             mytool.openRoom('${cardeInfo.id}'); 
			 $('.ex_mapPw_chat').show();
		} 
		function sendOut(){
			//这里是消息发送
			var value = $('#sendIpt').val();
			mytool.sendMessage(value);
			$('.ex_mapPw_chat_right').append("<p>"+value+"</p>"); 
			$('#sendIpt').val('');
			var scrollHeight = $('#textDiv').prop("scrollHeight");
			$('#textDiv').scrollTop(scrollHeight,200);

		}
		
		function imgShare(){
			//这里是文件分享
			 var fileSelector = new FileSelector();
	            fileSelector.selectSingleFile(function (file) {
	                mytool.sendFile(file);
	            });
		}
		function closeChat(){
			$('.ex_mapPw_chat').hide();
            if (mytool.connection.isInitiator) {
                
                mytool.connection.closeEntireSession(function () {

                });
            } else {
                mytool.connection.leave();
            }
            mytool.connection.attachStreams.forEach(function (localStream) {
                localStream.stop();
            });
		}
</script>
</body>
</html>