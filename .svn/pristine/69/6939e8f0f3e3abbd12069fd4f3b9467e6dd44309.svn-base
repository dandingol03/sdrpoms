<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<title>首都铁路护路联防综合业务信息管理系统</title>
	    <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="JHVersion1.1-layout">
		<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<!--  360浏览器专用 -->
		<meta name="renderer" content="ie-comp"/>
		<link rel="shortcut icon" href="${ctx}/resources/image/project.ico" type="image/x-icon" />
		<link rel="stylesheet" href="${ctx}/resources/openlayers4.5.0/ol.css" type="text/css">
		<script>
			function autodivheight() { //函数：获取尺寸  
			    //获取浏览器窗口高度  
			    var winHeight=0;  
			    if (window.innerHeight)  {
			        winHeight = window.innerHeight;  
			    } else if ((document.body) && (document.body.clientHeight))  {
			        winHeight = document.body.clientHeight; 
			    }
			    
			    //通过深入Document内部对body进行检测，获取浏览器窗口高度  
			    if (document.documentElement && document.documentElement.clientHeight)  {
			        winHeight = document.documentElement.clientHeight;  
			    }
			    
			    console.log('winHeight = ' + winHeight);
			    
			    //DIV高度为浏览器窗口的高度  
			    document.getElementById("bjRailMapB").style.height= winHeight +"px";  
			}  
			window.onresize=autodivheight; //浏览器窗口发生变化时同时变化DIV高度 
		
			/**
			 * 展示巡防的轨迹，安卓端调用
			 * @param pt 巡防轨迹对象，包含巡防轨迹的各种信息
			 */
			function showHiddenDangers(hiddenDanger) {
				console.log("1： " + hiddenDanger);
				// 如果不存在，那就直接return
				if(!hiddenDanger) {
					return;
				}
				var hiddenDangerCenter = [hiddenDanger[0].lng, hiddenDanger[0].lat]
				var hiddenDangerPoints = hiddenDanger.map(function(item)  {
					console.log('item ', item);
					
			 		var f = new ol.Feature({
				        geometry: new ol.geom.Point([ item.lng, item.lat ]),
				        type: 'HiddenDanger',
				        id: item.id,
				        name: item.name
				    });

					 f.setStyle(junctionStyle);
				 	 
					 return f;
				});
			
				BJAppLayer.getSource().addFeatures(hiddenDangerPoints);
				BJAppLayer.setVisible(true);
				
				 /*  map.getView().animate({
			        center: hiddenDangerCenter,
			        duration: 800,
			    	zoom: 214
			    }); */
			}
		</script>
		<style>
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
				width: 100%;
				height: 100%;
			    max-width: 100%;
    			overflow-x: hidden;
			}
			.ex_main {
				width: 100%;
				min-height: 640px;
				position: relative;
				z-index: 1;
				background: url(${ctx}/resources/image/exhibition/main/ex_mainbg.png) rgb(4,25,41) no-repeat; 
				background-size: 100% 100%;
				overflow:hidden;
			}
		</style>
	</head>
	<body>
		<!-- 地图内容 -->
		<div class="ex_main" id="bjRailMapB"></div>
		<script type="text/javascript">
			/**
			在创建地图之前现将根据设备设置map容器大小
			 */
			autodivheight();
		</script>
		<script type="text/javascript" src="${ctx}/resources/openlayers4.5.0/ol.js"></script>
		<script type="text/javascript" src="${ctx}/resources/mobile/js/map/style.js"></script>
		<script type="text/javascript" src="${ctx}/resources/mobile/js/map/function.js"></script>
		<script type="text/javascript" src="${ctx}/resources/mobile/js/map/map.js"></script>
		<script type="text/javascript">
		
			window.onload = function() {
				console.log('onload app');
				/* setTimeout(function() {
					showHiddenDangers([
						{lng:115.7393645036232,lat:40.08779782114046,name:'hiddenDanger',id: '0'},
						{lng:116.4167020413483,lat:39.90105015131823,name:'hiddenDanger',id: '1'},
						{lng:115.7406235665984,lat:40.08776609588067,name:'hiddenDanger',id: '2'}
					]);
			 	}, 100); */
				setBJBorder("110");
			 	
			 	/* setTimeout(function() {
			 		console.log('[1080, 1920]: ' + map.getCoordinateFromPixel([1080, 1920]));
			 	}); */
			}
		</script>
	</body>
</html>