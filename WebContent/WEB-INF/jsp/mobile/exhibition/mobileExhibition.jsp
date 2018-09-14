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
		<!-- 自定义滚动条 -->
		<script type="text/javascript" src="${ctx}/resources/jquery/jquery-3.1.1.min.js"></script>
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
			function showPatrolTrack(pt) {
				// 测试
				var track = JSON.parse(pt.track);
			 	console.log(track);
				
		 		var tempRailFeature = new ol.Feature({
			        geometry: new ol.geom.LineString(track, 'XY'),
			        type: 'patrolTrack',
			        name: "patrolTrack"
			    });
				 
				 tempRailFeature.setStyle(patrolTrackStyle);
				
				BJAppLayer.getSource().addFeature(tempRailFeature);
				BJAppLayer.setVisible(true);
				
				map.getView().animate({
			        center: track[0],
			        duration: 800,
			    	zoom: 214
			    });
				
			 	// 巡防轨迹 + 动画
				showAndAnimatePatrolTrack(track);
			}
			
			/**
			 * 在地图上显示巡防轨迹
			 * @param routeCoords [[],[]] 路径
			 */
			function showAndAnimatePatrolTrack(routeCoords) {
				BJAppLayer.setVisible(true);
				
				BJAppLayer.getSource().clear();
				
			    var patrolTrajectoryStyles = {
		            'route': new ol.style.Style({
		              stroke: new ol.style.Stroke({
		                width: 6, color: [237, 212, 0, 0.8]
		              })
		            }),
		            'icon': new ol.style.Style({
		              image: new ol.style.Icon({
		                anchor: [0.5, 1],
		                src: 'https://openlayers.org/en/v4.6.5/examples/data/icon.png'
		              })
		            }),
		            'geoMarker': new ol.style.Style({
		              image: new ol.style.Circle({
		                radius: 7,
		                snapToPixel: false,
		                fill: new ol.style.Fill({color: 'black'}),
		                stroke: new ol.style.Stroke({
		                  color: 'white', width: 2
		                })
		              })
		            })
		          };
				
				var animating = false;
				var speed = 2, now;
				var route = new ol.geom.LineString(routeCoords, 'XY');
				var routeLength = routeCoords.length;

				var routeFeature = new ol.Feature({
					type : 'route',
					geometry : route
				});
				var geoMarker = new ol.Feature({
					type : 'geoMarker',
					geometry : new ol.geom.Point(routeCoords[0])
				});
				var startMarker = new ol.Feature({
					type : 'icon',
					geometry : new ol.geom.Point(routeCoords[0])
				});
				var endMarker = new ol.Feature({
					type : 'icon',
					geometry : new ol.geom.Point(routeCoords[routeLength - 1])
				});

				BJAppLayer.getSource().addFeatures([ routeFeature, geoMarker, startMarker, endMarker ]);
				
				BJAppLayer.setStyle(function(feature) {
					// hide geoMarker if animation is active
					if (animating && feature.get('type') === 'geoMarker') {
						return null;
					}
					return patrolTrajectoryStyles[feature.get('type')];
				});

				var moveFeature = function(event) {
					var vectorContext = event.vectorContext;
					var frameState = event.frameState;

					if (animating) {
						var elapsedTime = frameState.time - now;
						// on lineString coordinates
						var index = Math.round(speed * elapsedTime / 1000);

						if (index >= routeLength) {
							stopAnimation(true);
							return;
						}

						var currentPoint = new ol.geom.Point(routeCoords[index]);
						var feature = new ol.Feature(currentPoint);
						vectorContext
								.drawFeature(feature, patrolTrajectoryStyles.geoMarker);
					}
					// tell OpenLayers to continue the postcompose animation
					map.render();
				};

				function startAnimation() {
					// clickTarget.dataset.isRuning = true;
					if (animating) {
						stopAnimation(false);
					} else {
						animating = true;
						now = new Date().getTime();
						// hide geoMarker
						geoMarker.setStyle(null);
						// just in case you pan somewhere else
						map.getView().setCenter(routeCoords[0]);
						// zoom [1, 15]
						map.getView().setZoom(14);   
						map.on('postcompose', moveFeature);
						map.render();
					}
				}

				function stopAnimation(ended) {
					// clickTarget.dataset.isRuning = false;
					animating = false;
					// if animation cancelled set the marker at the beginning
					var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
					/** @type {ol.geom.Point} */
					(geoMarker.getGeometry()).setCoordinates(coord);
					// remove listener
					map.un('postcompose', moveFeature);
				}
				startAnimation();
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
		
			$(document).ready(function() {
				setBJBorder("110");
				
				var f = new ol.Feature({
					geometry: new ol.geom.Point([ 116.4167020413483, 39.90105015131823 ]),//new ol.geom.Point(point),
					id: '12',
					name: 'ok'
				});
				f.setStyle(junctionStyle);
				BJAppLayer.getSource().addFeature(f);
				BJAppLayer.setVisible(true);
				console.log('ready app');
			});
			
			/* window.onload = function() {
				console.log('onload app');
				var pt = [[115.7393645036232,40.08779782114046],[115.7399757335781,40.08776219988481],[115.7406235665984,40.08776609588067],[115.7412183986107,40.08780437496016],[115.74404130696,40.08802474830688],[115.7452789853715,40.08812808908738],[115.7458105457198,40.08815480160926],[115.7462060499087,40.08816305556087],[115.7465431186335,40.08817077591382],[115.746993378097,40.08815655673824],[115.7475230540427,40.08810174025938],[115.7479743346759,40.08803996842955],[115.7487937542806,40.08782454664203],[115.750490197688,40.08719812874283],[115.751580998748,40.08629118283258],[115.7531822457559,40.08488821169321],[115.7580258432722,40.08204154760124],[115.7585631012498,40.08135759999136],[115.7589746363101,40.08072016065154],[115.7594068293208,40.07992354198715],[115.7609674886704,40.07791614965264],[115.7611605900231,40.07775794853329],[115.762247951901,40.07714661609052],[115.7632508245981,40.07673120381814],[115.7641946531624,40.07646175430462],[115.7647576609399,40.07634746613149],[115.7653406761518,40.07628540069159],[115.7660819806476,40.07623830126644],[115.7677648665447,40.07627757500884],[115.7700219409143,40.07635722108577],[115.7706602404183,40.07639559030085],[115.7722522392258,40.07659343900563],[115.773339787095,40.07665922996612],[115.7741840721106,40.07673356929577],[115.7763001653342,40.07643695535405],[115.7774502836493,40.07593654132128],[115.7790050589479,40.07432849504331],[115.7797645291019,40.07355742207888],[115.7814167115822,40.07169773759231],[115.7825236145216,40.06548770324007],[115.782466776475,40.06506524409587],[115.7824332967711,40.06448444830949],[115.7824308273776,40.06414722667567],[115.7824661432289,40.06287378056515],[115.7823976963985,40.06244925884485],[115.7818288728017,40.0598430189895],[115.7815678278124,40.05904631134298],[115.7812935540653,40.05774793696087],[115.781164429464,40.05738045346197],[115.7810577650776,40.05609212387384],[115.7810632951922,40.05564152301891],[115.7810908078574,40.05523296185957],[115.7814336376493,40.05440831451718]];
				setTimeout(function() {
					showAndAnimatePatrolTrack(pt)
			 	}, 100);
			} */
			
		</script>
	</body>
</html>