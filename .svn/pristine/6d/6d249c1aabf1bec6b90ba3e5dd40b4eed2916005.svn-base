<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>山东省综治委铁路护路联防办公室</title>
	<script type="text/javascript" src="${ctx}/resources/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/exhibition/ocxfun.js"></script>
	<style type="text/css">
		body,div,dl,dt,dd,ul,ol,li,h2,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td,h1,h2,h3,h4,h5,h6,from{margin: 0;padding: 0;}
		.pwAll_header{
		    width: 100%;
		    text-align: left;
		    font-size: 18px;
		    text-indent:2.5em;
		    height: 45px;
		    line-height: 45px;
		    background: url(${ctx}/resources/image/exhibition/map/pwAll_header.png) no-repeat;
		    background-size:100%;
		}
		.pwAllBottom {padding:7px;}
		.pwAllBottom ul li{
		   	width:100%;
		   	height:auto;
		   	overflow:hidden;
		   	margin:5px 0;
	   }
	   .pwAllBottom ul li p{float:left;}
	   .pwAllBottom ul li span{color:#fff;float:left;}
	   .pwAllBottom ul li p:nth-child(1){
	   		width:28%;
	   		text-align:justify;
	   		text-align-last:justify;
	   		font-size:16px;
	   		font-weight:bold;
	   		color:#fff;
	   }
	    .pwAllBottom ul li p:last-child{
	   		width:70%;
	   		word-wrap: break-word;
	   		color:rgb(58,174,238);
	   }
	   .close_pwAll{	
			position: absolute;
			right:0px;
			top:-2px;
			width:50px;
			height:50px;
			cursor: pointer;
		  	background: url(${ctx}/resources/image/exhibition/map/ex_map_closesearch.png) no-repeat;
		}
		
		#obj{
			width:300px;
			height:200px;
			border:1px solid red;
		}
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
	<script language = 'javascript'>
		window.onload=function() {
			ShowMessage();
		}
		function ShowMessage() {
			try {
			  	var  obj = new ActiveXObject("SDHLMONITOR.SDHLMonitorCtrl.1"); 
				  //TestOCX.StartMonitorScreen();
				  console.log("obj===========================",obj);
			} catch(e) {
					console.log("e=========================",e)
				 alert("没有注册");
				 window.open("/sdrpoms/resources/Monitor.exe");
			}
		}
	    function AddFun() {
			TestOCX.StartMonitorScreen();
	    }
		function AddFun1() {
			 //$("#imgMonitor").css({'display':'none'});
			 //$("#monitor").css({'display':'block'});
			 console.log(TestOCX);
			 $("#coverD").slideUp('fast', function() {
				 //112.233.243.247 56637
				 /* TestOCX.AddMonitor("${video.ip}","${video.username}","${video.pwd}","${video.port}",parseInt('${video.channel}'),parseInt('${video.stream}'));//调用ActiveX控件的AddFun方法。 */
				 TestOCX.AddMonitor("58.58.116.222","admin","admin","11122",0,0);
			 });
		}
		 
		function viewHistory() {
			window.parent.viewHistory();
		}
		
		function stopView() {
			 $("#coverD").slideDown('fast', function() {
				 TestOCX.CloseAllMonitor();
			 });
		}
		function closePwAll(){
			alert(111);
			var aa = $('.ol-popup-closer');
			alert(aa);
			$('.ol-popup-closer').click();
		}
		/* 开始 */
		function ButtonStartRealplayByWndNo_onclick() 
		{
			ButtonLogin_onclick();
		    /* var obj = document.getElementById("DPSDK_OCX");
			var nWndNo = obj.DPSDK_GetSelWnd(0);
			console.log('nWndNo===========================',nWndNo)
			var nRet = obj.DPSDK_StartRealplayByWndNo(0, nWndNo, '1000000$1$0$0', 1, 1, 1);
			debugger */
		}
		/* 停止 */
		function ButtonStopRealplayByWndNo_onclick() 
		{
		    var obj = document.getElementById("DPSDK_OCX");
			var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
		    ShowCallRetInfo(obj.DPSDK_StopRealplayByWndNo(gWndId, nWndNo), "停止播放视频");
		}
	</script>
</head>
<body onload="init()">
<div id="text">1111111111</div>
<div id="pwAllM">
<div class="pwAll_header">${station.name }</div>
<div class="close_pwAll" onclick="closePwAll()"></div>
<div  style="text-align:center;height:150px;margin:0 auto;overflow:hidden;">
	<c:choose>
		<c:when test="${ orgId!=null }">
			<div id="coverD" style="height:150px;background:url('${ctx}/resources/image/img_monitor.png') center center no-repeat;">
			</div>
		</c:when>
		<c:otherwise>
			<div id="coverD" style="height:150px;background:url('${ctx}/resources/image/img_monitor.png') center center no-repeat;">
			</div>
		</c:otherwise>
	</c:choose>
	<!-- 
	<object id="TestOCX" width="320" height="150" classid="CLSID:C8016DE4-0DE5-4741-9241-3D5045629980" >
	</object> -->	
	<div id="obj">
		<object id="DPSDK_OCX" classid="CLSID:D3E383B6-765D-448D-9476-DFD8B499926D" style="width: 100%; height: 80%" codebase="DpsdkOcx.cab#version=1.0.0.0"></object>
	</div>
</div>
<div style="font-size:12px;font-weight:bold;margin-top:10px;padding-top:10px;border-top:solid 1px #eaeaea;text-align:center;">${orgName }</div>
<div style="margin-top:10px;text-align:center;">
	<input type='button' onclick='ButtonStartRealplayByWndNo_onclick()' value='开始监控'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' onclick='ButtonStopRealplayByWndNo_onclick()' value='停止监控'>
	<!--  <input type='button' onclick='viewHistory()' value='查看历史'>-->
</div>		
	<div class="pwAllBottom">																								
	<ul>					
		<li><p>位置</p><span>:</span><p>111111111111111111111111</p></li>
		<li><p>临近线路</p><span>:</span><p>111111111111111111111111111</p></li>
		<li><p>权属单位</p><span>:</span><p>111111111111111111111111</p></li>
		<li><p>功能描述</p><span>:</span><p>11111111111111111111111111111111</p></li>
	</ul>
</div>
</div>
</body>
</html>