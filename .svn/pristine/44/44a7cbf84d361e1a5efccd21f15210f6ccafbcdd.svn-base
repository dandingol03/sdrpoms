/**
 * 在地图上显示巡防轨迹
 */
function showPatrolTrajectory() {
	BJPatrolTrajectoryLayer.setVisible(true);
	var clickTarget = event.target;
	if (clickTarget.dataset.isRuning) {
		return;
	}
	var target = event.target;
	if(target.tagName === 'SPAN' || target.tagName === 'span') {
		target = target.parentNode;
	}
	var routeCoords = target.dataset.coords;
	console.log("寻访经纬度数据+++++++++++++++++",JSON.parse(routeCoords));
	routeCoords = JSON.parse(routeCoords);
	if (!routeCoords || routeCoords.length == 0) {
		return;
	}
	BJPatrolTrajectoryLayer.getSource().clear();
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

	BJPatrolTrajectoryLayer.getSource().addFeatures(
			[ routeFeature, geoMarker, startMarker, endMarker ]);
	BJPatrolTrajectoryLayer.setStyle(function(feature) {
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
			vectorContext.drawFeature(feature, patrolTrajectoryStyles.geoMarker);
		}
		// tell OpenLayers to continue the postcompose animation
		map.render();
	};
	function startAnimation() {
		clickTarget.dataset.isRuning = true;
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
		clickTarget.dataset.isRuning = false;
		animating = false;
		// if animation cancelled set the marker at the beginning
		var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
		/** @type {ol.geom.Point} */
		(geoMarker.getGeometry()).setCoordinates(coord);
		// remove listener
		map.un('postcompose', moveFeature);
	}

	$('#patrolTrajectoryClose').click(function() {
		stopAnimation(false);
		// 巡防轨迹
		BJPatrolTrajectoryLayer.getSource().clear();
		BJPatrolTrajectoryLayer.setVisible(false);
	});
	startAnimation();
}