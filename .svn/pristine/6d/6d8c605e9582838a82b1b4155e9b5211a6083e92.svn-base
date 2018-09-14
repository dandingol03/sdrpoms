'use strict';
//创建分割图层===================================================================================================
function createWMSMapLayer(url, layers, STYLES, CQL) {
	var wmsSource = new ol.source.TileWMS({
		url : url,
		params : {
			'FORMAT' : formatIP,
			'VERSION' : '1.1.1',
			LAYERS : layers,
			STYLES : STYLES,
			CQL_FILTER : CQL
		}
	});
	return new ol.layer.Tile({
		source : wmsSource
	});
}
// 创建图像图层=================================================================================================
function createWMSIMGMapLayer(url, layers, STYLES, CQL) {
	var wmsSource = new ol.source.ImageWMS({
		ratio : 1,
		url : url,
		params : {
			'FORMAT' : formatIP,
			'VERSION' : '1.1.1',
			LAYERS : layers,
			STYLES : STYLES,
			CQL_FILTER : CQL
		}
	});
	return new ol.layer.Image({
		source : wmsSource
	});
}


//地图展示过滤===========================================================================================
function setBJBorder(orgId) {
	// 根据orgId设置显示区域style
	BJBorderLayer.setStyle(function(feature, resolution) {
		// 天安门出始终显示五角星
		if (feature.getProperties().OrgID == "110") {
			return starStyle;
		}
		// 全北京
		if (orgId == "110") { // 显示北京城所有区域
			return new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : "rgba(30,255,0,0.5)",//
					width : 2,
					lineDash: [10, 10, 1, 10],
	                lineCap: 'square',
				}),
				fill : new ol.style.Fill({
					color : "rgba(255,255,255,0)"
				}),
				text : new ol.style.Text({
					font : '18px "Microsoft YaHei",sans-serif',
					text : feature.getProperties().Name,
					fill : new ol.style.Fill({
						color : "rgba(255,255,255,1)",
						width : 3
					}),
					stroke : new ol.style.Stroke({
						color : "rgba(0,0,0,0.6)",
						width : 2
					})
				})
			});
		}
		// 显示北京城某个区域
		if (feature.getProperties().OrgID == orgId) {
			return new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : "rgba(246,0,141,0.8)",
					width : 3,
				}),
				fill : new ol.style.Fill({
					color : "rgba(255,255,255,0.1)"
				}),
				text : new ol.style.Text({
					font : '20px "Microsoft YaHei",sans-serif',
					text : feature.getProperties().Name,
					fill : new ol.style.Fill({
						color : "rgba(246,0,141,1)",
						width : 3
					}),
					stroke : new ol.style.Stroke({
						color : "rgba(255,255,255,1)",
						width : 3
					})
				})
			});
		} else { // 其他区域不显示
			return noStyle;
		}
	});
	
	// 改变View center 和 zoom
	//代表是显示全北京区域
	if(orgId == '110') {
		// 那么将重心设置在天安门
		map.getView().setZoom(9.8);
		map.getView().setCenter(centerGeom);
	} else {
		var features = BJBorderLayer.getSource().getFeatures();
		for(var i = 0; i < features.length; i++) {
			var feature = features[i];
			if(feature.getProperties().OrgID == orgId) {
				map.getView().setZoom(feature.getProperties().Zoom);
				map.getView().setCenter([ feature.getProperties().X, feature.getProperties().Y ]);
				break;
			}
		}
	}
}