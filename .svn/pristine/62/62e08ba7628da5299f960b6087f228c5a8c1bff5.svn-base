'use strict';
/**
 *  地图初始化区
 */
//首都护路办卫星地图
//卫星图层
var BJMapLayer = createWMSMapLayer('http://221.214.68.13:10903/geoserver/gwc/service/wms', 'BJMap:BJMap','',null);

//路标图层
var BJMapDBLayer = createWMSMapLayer('http://221.214.68.13:10903/geoserver/gwc/service/wms', 'BJMapDB:BJMapDB','',null);
BJMapDBLayer.setVisible(false);
//首都五角星标志
var bjStarPoint = new ol.Feature({
	geometry: new ol.geom.Point([116.39132678116391, 39.90726883140051]),
	ORG_ID:"bjStar",
	style:starStyle
});
//北京边界 行政区边界
var BJBorderSource = new ol.source.Vector({
	 format: new ol.format.GeoJSON(),
	 url: function(extent) {
		 return 'http://221.214.68.13:10903/geoserver/wfs?service=WFS&' +
         'version=1.1.0&request=GetFeature&typename=BJBorder:BJBorder&' +
         'outputFormat=application/json&srsname=EPSG:4326&'+
         'bbox=115.41,39.429,117.51,41.067, EPSG:4326';
	 },
	 strategy: ol.loadingstrategy.bbox
});
var BJBorderLayer = new ol.layer.Vector({
	source : BJBorderSource,
});
BJBorderLayer.getSource().addFeature(bjStarPoint);

//街道边界
var BJJDBorderSource = new ol.source.Vector({
	 format: new ol.format.GeoJSON(),
	 url: function(extent) {
		 return 'http://221.214.68.13:10903/geoserver/wfs?service=WFS&' +
        'version=1.1.0&request=GetFeature&typename=	BJBorder:BJBorderJD&' +
        'outputFormat=application/json&srsname=EPSG:4326&'+
        'bbox=115.41,39.429,117.51,41.067, EPSG:4326';
	 },
	 strategy: ol.loadingstrategy.bbox
});
var BJJDBorderLayer = new ol.layer.Vector({
	source : BJJDBorderSource,
});
BJJDBorderLayer.getSource().addFeature(bjStarPoint);
/*BJJDBorderLayer.setVisible(true);*/

//地图安保区
/*var BJSecurityAreaSource = new ol.source.Vector({
	 format: new ol.format.GeoJSON(),
	 url: function(extent) {
		 return 'http://221.214.68.13:10903/geoserver/wfs?service=WFS&' +
        'version=1.1.0&request=GetFeature&typename=	BJSecurityarea:BJSecurityarea&' +
        'outputFormat=application/json&srsname=EPSG:4326&'+
        'bbox=115.41,39.429,117.51,41.067, EPSG:4326';
	 },
	 strategy: ol.loadingstrategy.bbox
});*/
//地图路网区
var BJLWSource = new ol.source.Vector({
	 format: new ol.format.GeoJSON(),
	 url: function(extent) {
		 return 'http://221.214.68.13:10903/geoserver/wfs?service=WFS&' +
	        'version=1.1.0&request=GetFeature&typename=	BJLuwang:BJLuwang&' +
	        'outputFormat=application/json&srsname=EPSG:4326&'+
	        'bbox=115.41,39.429,117.51,41.067, EPSG:4326';
	 },
	 strategy: ol.loadingstrategy.bbox
});
var BJLWLayer = new ol.layer.Vector({
	source : BJLWSource,
	style : function(feature,resolution){
		return new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : "#0e6ef6",
				width : 2,
			}),
			fill : new ol.style.Fill({
				color : "rgba(0,0,0,0)"
			}),
			/*text: new ol.style.Text({
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
			})*/
		});
	},
});
BJLWLayer.getSource().addFeature(bjStarPoint);
BJLWLayer.setVisible(false);




//铁路线路样式
//var BJRailMapLayer = createWMSMapLayer('http://221.214.68.13:10903/geoserver/gwc/service/wms', 'BJRail:BJRail','',null);
//铁路线路---高铁
var BJHightSpeedRailLayer = new ol.layer.Vector({
	source : new ol.source.Vector()
});
//铁路线路---重载
var BJHeavyLoadRailLayer = new ol.layer.Vector({
	source : new ol.source.Vector()
});
//铁路线路---普通
var BJOrdinaryRailLayer = new ol.layer.Vector({
	source : new ol.source.Vector()
});
//铁路线路---其它	
var BJOtherRailLayer = new ol.layer.Vector({
	source : new ol.source.Vector()
});
var BJSecurityAreaLayer = new ol.layer.Vector({
	source : new ol.source.Vector()
});

//重点部位========================================================================================
//桥梁涵洞图层
var BJBridgeAndCulvertLayer = new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJBridgeAndCulvertLayer.setVisible(false);

//道口图层
var BJJunctionLayer = new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJJunctionLayer.setVisible(false);

//隧道
var  BJTunnelLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJTunnelLayer.setVisible(false);

//车站
var  BJPartStationLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJPartStationLayer.setVisible(false);

//进站信号机
var  BJPartStationSignalLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJPartStationSignalLayer.setVisible(false);

//行人易穿行
var  BJTrajectionPolygonLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJTrajectionPolygonLayer.setVisible(false);

//重点人
var  BJKeyPersonLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJKeyPersonLayer.setVisible(false);

//隐患点
var  BJDangerPointLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJDangerPointLayer.setVisible(false);

//联防点位========================================================================================
//工作站
var  BJGuardStationLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJGuardStationLayer.setVisible(false);

//宣传点
var  BJPropaGandaLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJPropaGandaLayer.setVisible(false);

//警示柱
var  BJBroadCastLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJBroadCastLayer.setVisible(false);

//摄像头
var  BJMonitorLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJMonitorLayer.setVisible(false);

//派出所
var  BJPoliceStationLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJPoliceStationLayer.setVisible(false);

//警务站
var  BJPolicePtationLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJPolicePtationLayer.setVisible(false);

//专职队员
var  BJFullTimeMemberLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJFullTimeMemberLayer.setVisible(false);

//在线干部
var  BJAppLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJAppLayer.setVisible(false);

//工作站测试================================================
var  ceshiGZZLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
ceshiGZZLayer.setVisible(false);

//监控测试==================================================
var  ceshiJKLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
ceshiJKLayer.setVisible(false);
//周边场所
var  BJPeripheralPlaceLayer= new ol.layer.Vector({
	source : new ol.source.Vector(),
});
BJPeripheralPlaceLayer.setVisible(false);

//巡防轨迹
var BJPatrolTrajectoryLayer = new ol.layer.Vector({
  source : new ol.source.Vector(),
});
BJPatrolTrajectoryLayer.setVisible(false);

//线路定位
var BJRailLocationLayer = new ol.layer.Vector({
  source : new ol.source.Vector(),
});
BJRailLocationLayer.setVisible(false);
//经纬度定位
var BJRailGPSLocationLayer = new ol.layer.Vector({
  source : new ol.source.Vector(),
});
BJRailGPSLocationLayer.setVisible(false);
//测量图层
var measureSource = new ol.source.Vector();
var measureLayer = new ol.layer.Vector({
	source: measureSource,
	style: new ol.style.Style({
		fill: new ol.style.Fill({
			color: 'rgba(255, 255, 255, 0.2)'
		}),
		stroke: new ol.style.Stroke({
			color: '#ffcc33',
			width: 2
		}),
		image: new ol.style.Circle({
			radius: 7,
			fill: new ol.style.Fill({
				color: '#ffcc33'
			})
		})
	})
});
//测量监听接口 全局量 global so we can remove it later
var measureDraw; 
//当前测量元素（特征）  Currently drawn feature.  @type {ol.Feature}
var measureSketch;
//测量提示DOM The measure tooltip element. @type {Element}
var measureTooltipElement;
//测量结果 Overlayer Overlay to show the measurement. @type {ol.Overlay}
var measureTooltip;
//测量结果ID
var measureId;
//边界限制======================================================================================================
var bounds = [ 115.41, 39.429, 117.51, 41.067 ];
var centerGeom = [ 116.46, 40.248 ];
var mousePositionControl = new ol.control.MousePosition({
	className : 'custom-mouse-position',
	target : document.getElementById('location'),
	coordinateFormat : ol.coordinate.createStringXY(6),
	undefinedHTML : '&nbsp;'
});
//比例尺
var scaleLineControl = new ol.control.ScaleLine();
scaleLineControl.setUnits('metric');
var projection = ol.proj.get('EPSG:4326');
var mapView = new ol.View({
	projection : projection,
	maxZoom : 19,
	minZoom :9
});
var container = document.getElementById('popup');
var content = document.getElementById('popup-content');
var closer = document.getElementById('popup-closer');
var overlay = new ol.Overlay({
  element: container,
  autoPan: true,
  autoPanAnimation: {
    duration: 250
  }
});
closer.onclick = function() {
	overlay.setPosition(undefined);
	closer.blur();
	return false;
};
//初始化地图
var map = new ol.Map({
	controls : ol.control.defaults({
		zoomOptions : {
			zoomInTipLabel : '放大',
			zoomOutTipLabel : '缩小'
		},
		attribution : false
	}).extend([mousePositionControl,scaleLineControl]),
		target: 'bjRailMapB',
		overlays: [overlay],
		layers: [  BJMapLayer,
		           BJMapDBLayer,
		           BJBorderLayer,				//行政区边界
		           BJJDBorderLayer,				//街道边界
		           BJOtherRailLayer,            //其它铁路
		           BJOrdinaryRailLayer,         //普通铁路
		           BJHeavyLoadRailLayer,        //重在干线
		           BJHightSpeedRailLayer,       //铁路高铁
		           BJSecurityAreaLayer,         //安保区
		           BJLWLayer,                   //路网
		           BJBridgeAndCulvertLayer,     //桥梁涵洞
		           BJJunctionLayer,             //道口
		           BJTunnelLayer,               //隧道
		           BJPartStationLayer,          //车站
		           BJPartStationSignalLayer,    //进站信号机
		           BJTrajectionPolygonLayer,    //行人易穿行
		           BJKeyPersonLayer,            //重点人
		           BJDangerPointLayer,          //隐患点
		           BJGuardStationLayer,         //工作站
		           BJPropaGandaLayer,           //宣传点
		           BJBroadCastLayer,            //警示柱
		           BJMonitorLayer,              //摄像头
		           BJPoliceStationLayer,        //派出所
		           BJPolicePtationLayer,        //警务站
		           BJFullTimeMemberLayer,       //专职队员
		           BJAppLayer,                  //在线干部
		           BJPeripheralPlaceLayer,      //周边场所
		           BJPatrolTrajectoryLayer,     //巡防轨迹
		           measureLayer,                //测量图层
		           BJRailLocationLayer,         //线路定位
		           BJRailGPSLocationLayer,      //线路定位GPS
		           ceshiGZZLayer,               //工作站测试
		           ceshiJKLayer                 //监控测试
		           
	           ],
		view : mapView
});

//鼠标移动
map.on('pointermove', function(evt) {
	if (evt.dragging) {
		return;
	}
	//坐标
	var coordinate = evt.coordinate;
	//设置坐标和行政区域
	var tempBorderFeatures = BJBorderSource.getFeaturesAtCoordinate(coordinate);
	var tempJDBorderFeatures = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
	if(tempBorderFeatures.length>0&&tempJDBorderFeatures.length>0){
		var tempBorderName =tempBorderFeatures[0].get("ORG_NAME");
		var tempJDBordertName =tempJDBorderFeatures[0].get("ORG_NAME");
		var lng=coordinate[0].toFixed(4);
		var lat=coordinate[1].toFixed(4);
		$('#tempBorderName').html(tempBorderName);
		$('#tempJDBordertName').html(tempJDBordertName);
		$('#tempLng').html(lng);
		$('#tempLat').html(lat);
	}else{
		//清空
		$('#tempBorderName').html('');
		$('#tempJDBordertName').html('');
		$('#tempLng').html('');
		$('#tempLat').html('');
	}
	//设置手型光标
	var pixel = map.getEventPixel(evt.originalEvent);
	//console.log(pixel);
	//console.log(mapView.getCenter());
	//console.log(map.getPixelFromCoordinate(mapView.getCenter()));
	//var resolution = evt.target.get('resolution');
	var feature = map.forEachFeatureAtPixel(pixel, function(feature,layer) {
		return feature;
	});
	if(feature&&feature.getProperties().hasOwnProperty("name")){
		map.getTargetElement().style.cursor = 'pointer';
	}else{
		map.getTargetElement().style.cursor = '';
	}
	//map.getTargetElement().style.cursor = map.hasFeatureAtPixel(evt.pixel) ? 'pointer' : '';
	//if(feature==null){
	//	map.getTargetElement().style.cursor ='';
	//	return;
	//}
});

//鼠标点击
map.on('singleclick', function(evt) {
	/*var centerCoord = mapView.getCenter();
	map.getView().setCenter(centerCoord);*/
	overlay.setPosition(undefined);
	closer.blur();
	//清空站场选中状态
	if(BJPartStationLayer.getVisible()){
		var tempFeatrues = BJPartStationLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				tempFeatrues[i].setStyle(parStationYardStyle);
			}else if(tempFeatrues[i].get("type")=="stationYard"){
				tempFeatrues[i].setStyle(parStationYardStyle);
			}
		}
	}
	//清空桥场选中状态
	if(BJBridgeAndCulvertLayer.getVisible()){
		var tempFeatrues = BJBridgeAndCulvertLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("oversize")=="1" && tempFeatrues[i].get("type")=="bridgeYard"){
				tempFeatrues[i].setStyle(bridgeInitialStyle);
			}
			if(tempFeatrues[i].get("type")=="bridgeYard"){
				tempFeatrues[i].setStyle(bridgeInitialStyle);
			}
			if(tempFeatrues[i].get("type")=="culvertYard"){
				tempFeatrues[i].setStyle(culvertYardStyle);
			}
		}
	}
	//清空道口选中状态
	if(BJJunctionLayer.getVisible()){
		var tempFeatrues = BJJunctionLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				tempFeatrues[i].setStyle(junctionYardStyle);
			}
		}
	}
	//清空隧道选中状态
	if(BJTunnelLayer.getVisible()){
		var tempFeatrues = BJTunnelLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				tempFeatrues[i].setStyle(tunnelInitialStyle);
			}
		}
	}
	
	if(BJTrajectionPolygonLayer.getVisible()){
		var tempFeatrues = BJTrajectionPolygonLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				tempFeatrues[i].setStyle(trajectionPolygonStyleT);
			}
		}
	}
	BJRailLocationLayer.setVisible(false);
	BJRailGPSLocationLayer.setVisible(false);
	//弹出层隐藏
	$('#emrhPw').hide();
	var coordinate = evt.coordinate;
	var featrues = new Array();
	var layers = new Array();
	var closeCoordinates = new Array();
	map.forEachFeatureAtPixel(evt.pixel,function(feature, layer) {
		closeCoordinates.push(feature.getGeometry().getClosestPoint(coordinate));
		featrues.push(feature);
		layers.push(layer);
	}, {hitTolerance: 5});
	if(featrues.length>0 &&!(layers[0]==BJBorderLayer||layers[0]==BJJDBorderLayer)){
		setFeatrueClick(featrues[0],layers[0],closeCoordinates[0]);
		var pixel = map.getEventPixel(evt.originalEvent);
		var centerCoord = mapView.getCenter();
		var centerPt = map.getPixelFromCoordinate(centerCoord);
		if(pixel[0]<=600){
			console.log('----------------------',1111111111111)
			centerPt[0] = pixel[0]+20;
			mapView.animate({center: map.getCoordinateFromPixel(centerPt),duration: 500});
		}
		//console.log(pixel);
		//console.log();
		//console.log(mapView.getCenter()));
		//map.getView().setCenter([ parseFloat("${orgZoomAndCenter.X}"), parseFloat("${orgZoomAndCenter.Y}") ]);
	}
	/*mapView.animate({
        center: coordinate,
        duration: 800
    });*/
});

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
	if(zoom==16)
	{  
		setBJRailWayLayer(adminDivisionOrgId,railIdArray);
	}
	if(zoom==14){
		setBJRailWayLayer(adminDivisionOrgId,railIdArray);
	}
	//路网
	if(zoom>=18){
		BJLWLayer.setVisible(true);
		$('.ex_mapLw').show().removeClass().addClass("ex_mapLw_hover");
		$('.ex_mapLw_hover').show();
	}else{
		BJLWLayer.setVisible(false);
		$('.ex_mapLw').hide();
		$('.ex_mapLw_hover').hide();
	}
	var zoomId=adminDivisionOrgId+"";
	//街道显示层
	if(adminDivisionOrgId=="110" && zoom>=16){
		//全北京
		BJJDBorderLayer.setVisible(true);
		BJJDBorderLayer.setStyle(function(feature, resolution) {
			// 天安门出始终显示五角星
			if(feature.get("ORG_ID") == "bjStar") {
				return starStyle;
			}
			bjBorderStyle.getText().setText(feature.get('ORG_NAME'));
			return bjBorderStyle;
			
			//街道
			if(zoomId.length==9){
				if(feature.get("ORG_ID")==adminDivisionOrgId){
					bjChooseStyle.getText().setText(featrue.get("ORG_NAME"));
					return bjChooseStyle;
				}
			}else{
				if(feature.get("ORG_ID")==adminDivisionOrgId){
					bjChooseStyle.getText().setText(featrue.get("ORG_NAME"));
					return bjChooseStyle;
				}
			}
		});
	}else{
		if(adminDivisionOrgId=="110" && zoom<16){
			BJJDBorderLayer.setVisible(false);
		}else{
			if(adminDivisionOrgId!="110"){
				BJJDBorderLayer.setVisible(true);
			}
		}
	}
	/*if(zoom>16){
		BJMapDBLayer.setVisible(true);
	}else{
		BJMapDBLayer.setVisible(false);
	}
	switch(zoom){
	case 9.8:
		StationYardTwoLayer.setVisible(false);
		break;
	case 10:
		StationYardTwoLayer.setVisible(false);
		break;
	case 11:
		StationYardTwoLayer.setVisible(false);
		break;
	case 12:
		StationYardTwoLayer.setVisible(false);
		break;
	case 13:
		StationYardTwoLayer.setVisible(false);
		break;
	case 14:
		StationYardTwoLayer.setVisible(false);
		break;
	case 15:
		StationYardTwoLayer.setVisible(false);
		break;
	case 16:
		StationYardTwoLayer.setVisible(false);
		break;
	case 17:
		StationYardTwoLayer.setVisible(false);
		break;
	case 18:
		StationYardTwoLayer.setVisible(true);
		break;
	}*/
});

//测量长度 Format length output.  @param {ol.geom.LineString} line The line.  @return {string} The formatted length.
var formatLength = function(line) {
	var wgs84Sphere = new ol.Sphere(6378137);  
    var coordinates = line.getCoordinates();  
    var length = 0;  
	for (var i = 0, ii = coordinates.length - 1; i < ii; ++i) {  
	    length += wgs84Sphere.haversineDistance(coordinates[i], coordinates[i+1]);  
	}
	var options=  
    {  
        projection: "EPSG:4326",
        radius:6378137
    };
	length = ol.Sphere.getLength(line,options);
	var output;
	if (length > 1000) {
		output = (Math.round(length / 1000 * 100) / 100) +	
		' ' + 'km';
	} else {
		output = (Math.round(length * 100) / 100) +
        ' ' + 'm';
	}
	return output;
};
//测量面积 Format area output. @param {ol.geom.Polygon} polygon The polygon. @return {string} Formatted area.
var formatArea = function(polygon) {
	var wgs84Sphere = new ol.Sphere(6378137);  
    var coordinates = polygon.getLinearRing(0).getCoordinates();  
    var area = Math.abs(wgs84Sphere.geodesicArea(coordinates));  
	var output;
	if (area > 10000) {
		output = (Math.round(area / 1000000 * 100) / 100) +
		' ' + 'km<sup>2</sup>';
	} else {
		output = (Math.round(area * 100) / 100) +
		' ' + 'm<sup>2</sup>';
	}
	return output;
};
//创建测量提示  Creates a new measure tooltip
function createMeasureTooltip() {
	if (measureTooltipElement) {
		measureTooltipElement.parentNode.removeChild(measureTooltipElement);
	}
	measureTooltipElement = document.createElement('div');
	measureTooltipElement.className = 'tooltip tooltip-measure';
	measureId = new Date().getTime();
	measureTooltip = new ol.Overlay({
		id:measureId,
		element: measureTooltipElement,
		offset: [0, -15],
		positioning: 'bottom-center'
	});
	map.addOverlay(measureTooltip);
};
//创建测量交互函数
function addInteraction(measureType) {
	var tempType = (measureType == '1' ? 'Polygon' : 'LineString');
	measureDraw = new ol.interaction.Draw({
		source: measureSource,
		type: tempType,
		style: new ol.style.Style({
			fill: new ol.style.Fill({
				color: 'rgba(255, 255, 255, 0.2)'
			}),
			stroke: new ol.style.Stroke({
				color: 'rgba(0, 0, 0, 0.5)',
				lineDash: [10, 10],
				width: 2
				}),
			image: new ol.style.Circle({
				radius: 5,
				stroke: new ol.style.Stroke({
					color: 'rgba(0, 0, 0, 0.7)'
				}),
				fill: new ol.style.Fill({
					color: 'rgba(255, 255, 255, 0.2)'
				})
			})
		})
	});
	map.addInteraction(measureDraw);
	createMeasureTooltip();
	var listener = null;
    measureDraw.on('drawstart',
    	function(evt) {
    		// set sketch
    		measureSketch = evt.feature;
    		measureSketch.setId(measureId);
    		/** @type {ol.Coordinate|undefined} */
    		var tooltipCoord = evt.coordinate;
    		listener = measureSketch.getGeometry().on('change', function(evt) {
    			var geom = evt.target;
    			/*console.log('geom',geom);*/
    			var output="";
    			if(geom instanceof ol.geom.Polygon){
    				output = formatArea(geom);
    				tooltipCoord = geom.getInteriorPoint().getCoordinates();
    			}else if (geom instanceof ol.geom.LineString) {
    				output = formatLength(geom);
    				tooltipCoord = geom.getLastCoordinate();
    			}
    			measureTooltipElement.innerHTML = output;
    			measureTooltip.setPosition(tooltipCoord);
    		});
    	}, this);
    
    measureDraw.on('drawend',
		function() {
    		var tempId = measureSketch.getId();
			measureTooltipElement.className = 'tooltip tooltip-static';
			var closeMeasureTooltipElement=document.createElement("div");
			closeMeasureTooltipElement.className = 'tooltip-static-close';
			measureTooltipElement.appendChild(closeMeasureTooltipElement);
			//closeMeasureTooltipElement.onclick =removeMeasureOverLayer(tempId);
			closeMeasureTooltipElement.onclick = function(){
				removeMeasureOverLayer(tempId);
			};
			measureTooltip.setOffset([0, -7]);
			// unset sketch
			measureSketch = null;
			// unset tooltip so that a new one can be created
			measureTooltipElement = null;
			createMeasureTooltip();
			ol.Observable.unByKey(listener);
			if($('.ex_main_rightheader_measure_ranging_hover').hasClass('ex_main_rightheader_measure_ranging_hover')){
				$('.ex_main_rightheader_measure_ranging_hover').removeClass().addClass('ex_main_rightheader_measure_ranging');
				//清除
				map.removeInteraction(measureDraw);
			};
			if($('.ex_main_rightheader_measure_surface_hover').hasClass('ex_main_rightheader_measure_surface_hover')){
				$('.ex_main_rightheader_measure_surface_hover').removeClass().addClass('ex_main_rightheader_measure_surface');
				//清除
				map.removeInteraction(measureDraw);
			};
		}, this);
}

function removeMeasureOverLayer(featureId){
	map.removeOverlay(map.getOverlayById(featureId));
	measureSource.removeFeature(measureSource.getFeatureById(featureId));
};
