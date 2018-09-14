function showPatrolTrajectory(id) {
	BJPatrolTrajectoryLayer.getSource().clear();
	BJPatrolTrajectoryLayer.setVisible(false);
	BJPatrolTrajectoryLayer.setVisible(true);
	var target = event.target;
	var routeCoords = target.dataset.coords;
	routeCoords = JSON.parse(routeCoords);
	var routeLength = routeCoords.length;
	//数据结束============================================================
	var animating = false;
	//speed 数字越小越慢
	var speed = 2, now;
	//绘制线
	var route = new ol.geom.LineString(routeCoords, 'XY');
	//获取点击Btn
	var startButton = document.getElementById(id);
	//设置Feature
	var routeFeature = new ol.Feature({
	  type: 'route',
	  geometry: route
	}); 
	var geoMarker = new ol.Feature({
	  type: 'geoMarker',
	  geometry: new ol.geom.Point(routeCoords[0])
	});
	var startMarker = new ol.Feature({
	  type: 'icon',
	  geometry: new ol.geom.Point(routeCoords[0])
	});
	var endMarker = new ol.Feature({
	  type: 'icon',
	  geometry: new ol.geom.Point(routeCoords[routeLength - 1])
	});
	 //添加Feature
	BJPatrolTrajectoryLayer.getSource().addFeatures([routeFeature, geoMarker, startMarker, endMarker]);
	
	BJPatrolTrajectoryLayer.setStyle(function(feature) {
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
	    var index = Math.round(speed * elapsedTime / 1000);
	    if (index >= routeLength) {
	      stopAnimation(true);
	      return;
	    }
	    var currentPoint = new ol.geom.Point(routeCoords[index]);
	    var feature = new ol.Feature(currentPoint);
	    vectorContext.drawFeature(feature, patrolTrajectoryStyles.geoMarker);
	  }
	  map.render();
	};
	//开始动画
	function startAnimation() {
	  BJPatrolTrajectoryLayer.setVisible(true);
	  if (animating) {
	    stopAnimation(false);
	  } else {
	    animating = true;
	    speed = 5;
		now = new Date().getTime();
		geoMarker.setStyle(null);
		/*startButton.textContent = 'Cancel Animation';*/
		map.getView().setCenter(routeCoords[0]);
		map.getView().setZoom(14);   
		map.on('postcompose', moveFeature);
		map.render();
	  }
	}
	//结束动画
	function stopAnimation(ended) {
	  animating = false;
	 /* startButton.textContent = 'Start Animation';*/
	  var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
	  (geoMarker.getGeometry()).setCoordinates(coord);
	  map.un('postcompose', moveFeature);
	}
	startAnimation();
	//为Btn 添加点击事件
	startButton.addEventListener('click', startAnimation, false);
	$('.ex_main_rightheader_closepw2_tjy').click(function() {
		stopAnimation(false);
		// 巡防轨迹
		BJPatrolTrajectoryLayer.getSource().clear();
		BJPatrolTrajectoryLayer.setVisible(false);
	});
	$('.ex_main_rightheader_closepw4').click(function() {
		stopAnimation(false);
		// 巡防轨迹
		BJPatrolTrajectoryLayer.getSource().clear();
		BJPatrolTrajectoryLayer.setVisible(false);
	});
}
