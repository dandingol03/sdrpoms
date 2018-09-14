'use strict';
/**
 *  地图初始化区
 */
//首都护路办卫星地图
//卫星图层
var BJMapLayer = createWMSMapLayer('http://58.56.96.171:10903/geoserver/gwc/service/wms', 'BJMap:BJMap','',null);

//路标图层
var BJMapDBLayer = createWMSMapLayer('http://58.56.96.171:10903/geoserver/gwc/service/wms', 'BJMapDB:BJMapDB','',null);
BJMapDBLayer.setVisible(true);

//北京边界
var BJBorderSource = new ol.source.Vector({
	 format: new ol.format.GeoJSON(),
	 url: function(extent) {
		 return 'http://58.56.96.171:10903/geoserver/wfs?service=WFS&' +
         'version=1.1.0&request=GetFeature&typename=BJBorder:BJBorder&' +
         'outputFormat=application/json&srsname=EPSG:4326&'+
         'bbox=115.41,39.429,117.51,41.067, EPSG:4326';
	 },
	 strategy: ol.loadingstrategy.bbox
});
var BJBorderLayer = new ol.layer.Vector({
	source : BJBorderSource,
	type: 'border',
	style : function(feature,resolution){
		return new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : "#ffffff",
				width : 20,
			}),
			fill : new ol.style.Fill({
				color : "rgba(0,0,0,0)"
			}),
			text: new ol.style.Text({
				font : '5px "Microsoft YaHei",sans-serif',
				text : feature.getProperties().Name,
				fill : new ol.style.Fill({
					color : "rgba(0,0,0,0.5)",
					width : 2
				}),
				stroke : new ol.style.Stroke({
					color : "rgba(255,255,255,1)",
					width : 2
				})
			})
		});
	},
});

var starPoint = new ol.Feature({
	geometry: new ol.geom.Point([116.39132678116391, 39.90726883140051]),
	OrgID:"110",
});
starPoint.setStyle(starStyle);
BJBorderLayer.getSource().addFeature(starPoint);


//街道边界
var BJJDBorderSource = new ol.source.Vector({
	 format: new ol.format.GeoJSON(),
	 url: function(extent) {
		 return 'http://58.56.96.171:10903/geoserver/wfs?service=WFS&' +
        'version=1.1.0&request=GetFeature&typename=BJBorder:BJBorderJD&' +
        'outputFormat=application/json&srsname=EPSG:4326&'+
        'bbox=115.41,39.429,117.51,41.067, EPSG:4326';
	 },
	 strategy: ol.loadingstrategy.bbox
});
var BJJDBorderLayer = new ol.layer.Vector({
	source : BJJDBorderSource,
	style : null,
});
BJJDBorderLayer.setVisible(true);

//在线干部
var  BJAppLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJAppLayer.setVisible(true);

//边界限制======================================================================================================
var bounds = [ 115.41, 39.429, 117.51, 41.067 ];
var centerGeom = [ 116.46, 40.248 ];
var mousePositionControl = new ol.control.MousePosition({
	className : 'custom-mouse-position',
	target : document.getElementById('location'),
	coordinateFormat : ol.coordinate.createStringXY(6),
	undefinedHTML : '&nbsp;'
});

var projection = ol.proj.get('EPSG:4326');
var mapView = new ol.View({
	projection : projection,
	maxZoom : 18,
	minZoom :9
});

//初始化地图
var map = new ol.Map({
	controls: ol.control.defaults({
					zoomOptions : {
						zoomInTipLabel : '放大',
						zoomOutTipLabel : '缩小'
					},
					attribution : false
			  }).extend([
				  mousePositionControl
			  ]),
	target: 'bjRailMapB',
	layers: [  BJMapLayer,
	           BJMapDBLayer,
	           BJBorderLayer,				//边界图层
	           BJJDBorderLayer,
	           BJAppLayer,                  //在线干部
           ],
	view : mapView
});

map.on('moveend', function(evt) {
	console.log('==moveend==');
	console.log('moveend: ', evt);
	// extent：map范围
	//evt.frameState.extent: [minx, miny, maxx, maxy]
	console.log('moveend: ' + evt.frameState.extent);
	// [minx, maxy]
	// [maxx, miny]
	// android 端操作
	// 将回传到android 
	if(window.mapCtrl && evt.frameState && evt.frameState.extent) {
		mapCtrl.setMapExtent(evt.frameState.extent + "");
	}
	
	var coor = map.getCoordinateFromPixel([412, 0]);
	console.log('rightTop = ' + coor);
	
	var coor2 = map.getCoordinateFromPixel([0, 732]);
	console.log('leftBottom = ' + coor2);
});

////鼠标移动
//map.on('pointermove', function(evt) {
//	if (evt.dragging) {
//		return;
//	}
//	//坐标
//	var coordinate = evt.coordinate;
//	
//	var pixel = map.getEventPixel(evt.originalEvent);
//	var feature = map.forEachFeatureAtPixel(pixel, function(feature) {
//		return feature;
//	});
//	
//	if(feature==null){
//		map.getTargetElement().style.cursor ='';
//		return;
//	}
//	if(feature.get('id')) {
//		map.getTargetElement().style.cursor = map.hasFeatureAtPixel(evt.pixel) ? 'pointer' : '';
//		return;
//	}
//});

////鼠标点击
//map.on('singleclick', function(evt) {
//	
//	var features = []
//	map.forEachFeatureAtPixel(evt.pixel,function(feature, layer) {
//		console.log('JDSKH FKSDH ISDH KJDSH KJFSHKDJH K',feature.getProperties());
//		features.push(feature);
////		return feature;
//	});
//	
//	if(!features.length) {
//		return;
//	}
//	
//	//获取点击点的经纬度
//	/*var coordinate = evt.coordinate;*/
//	/*console.log('coordinate',coordinate);
//	coordinate[0] = coordinate[0]-1;
//	var center2 = coordinate[1]-1;
//	map.getView().setCenter(center);*/
//	/*var london = ol.proj.fromLonLat(coordinate);*/
//	
//
//	//所有操作都在feature基础上
//	var feature = features[0];
//	//获取底层数据
//	var lastFeature = features[features.length-1];
//	console.log('feature========',feature)
//	// 鼠标点击哪，就将该点作为中心点
//	/*map.getView().setCenter(coordinate);*/
//	var coordinate = evt.coordinate;
////	if(feature instanceof ol.geom.Point ) {
////		console.log('This is point', '================================');
////		coordinate = feature.getGeometry().getFirstCoordinate();
////	} else if(feature instanceof ol.geom.LineString ) {
////		console.log('This is LineString');
////	    coordinate = feature.getGeometry().getCoordinateAt(0.5);
////	}
//	console.log('======before======', coordinate);
//	
//	coordinate = feature.getGeometry().getClosestPoint(coordinate);
//	
//	console.log('======After======', coordinate);
//	
//	/*if(feature.get('geomType') == 'LineString'){
//		coordinate = feature.getGeometry().getCoordinateAt(0.5);
//	}*/
//	
//	
//	mapView.animate({
//        center: coordinate,
//        duration: 800
//    });
//	
//	
//	if(feature.get("id") && feature.get("url")) {
//		// getBJOverLayersInfo(feature.get("id"), feature.get("url") ,coordinate);
//		return;
//	}
//	
////	if(feature.getProperties().OrgID != null) {
////		$('.ol-popup-closer').click();
////		$('.management').hide();
////		var orgId = feature.getProperties().OrgID;
////		var orgName = feature.getProperties().Name;
////		var lat = feature.getProperties().lat;
////		var coordinate = evt.coordinate;
////		var lng=coordinate[0];
////		var lat=coordinate[1];
////	
////		return;
////	}
//});


//显示区域设定 地图比例尺
map.getView().on('change:resolution', function(evt) {
	var resolution = evt.target.get('resolution');
	var units = map.getView().getProjection().getUnits();
	var dpi = 25.4 / 0.28;
	var mpu = ol.proj.METERS_PER_UNIT[units];
	var scale = resolution * mpu * 39.37 * dpi; 
	if (scale >= 9500 && scale <= 950000) {
		scale = Math.round(scale / 1000) + "公里";
	} else if (scale >= 950000) {
		scale = Math.round(scale / 1000000) + "100公里";
	} else {
		scale = Math.round(scale) + "米";
	}
	var zoom = map.getView().getZoom();
	console.log('zoom===================：' + zoom);
});

