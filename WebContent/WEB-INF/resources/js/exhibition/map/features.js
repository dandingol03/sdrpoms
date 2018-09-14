
///*创建公里标Km的Feature*/
//
//function createLocationRailFeature(kmrail) {
//	var tempLocationRailFeature = new ol.Feature({
//		geometry : new ol.geom.Point([
//								parseFloat(kmrail.lng),
//								parseFloat(kmrail.lat) ]),
//		kmrailId : kmrail.id,
//		lnglat: ,
//		km: ,
//		railId: ,
//	});
//	tempLocationRailFeature.setStyle(locationRailStyle); // 此处的schoolStyle已在style.js中定义
//	return tempLocationRailFeature;
//}


/* 创建点Feature */
function createPointFeature(id, name, url, type, point) {
	return new ol.Feature({
		geometry: new ol.geom.Point(point),
		id: id,
		name: name,
		url: url,
		type: type
	});
}
/* 创建点Feature */


/* 创建区域Feature 例如：行人易穿行区域 */
function createPolygonFeature(region, id, name, type, url) {
	return new ol.Feature({
		geometry: new ol.geom.Polygon([region]),
		id: id,
		name: name,
		type: type,
		url: url
	});
}



/**
 * 创建 铁路线features
 * return [baseFeature, feature]
 */
function createRailLineFeatures(rail, railLines) {
    var tempRailFeature = new ol.Feature({
        geometry: new ol.geom.LineString(railLines, 'XY'),
	 /* geometry: new ol.geom.MultiLineString([
           [[116.199, 39.70], [116.207, 39.75]],
           [[116.207, 39.75], [116.207, 39.80]]
         ], 'XY'),*/
        railId: rail.id,
        id: rail.id,
        type: 'railLine',
        url: 'map/getRailPopWin',
        railName: rail.name
    });
    var tempRailBaseFeature = new ol.Feature({
        geometry: new ol.geom.LineString(railLines, 'XY'),
        railId: rail.id,
        id: rail.id,
        type: 'railLine',
        url: 'map/getRailPopWin',
        railName: rail.name
    });
    if (rail.classify == '01') { // 高铁， 设置高铁样式
    		tempRailFeature.setStyle(highSpeedRailStyle);
        tempRailBaseFeature.setStyle(
            new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: "rgba(255,255,255,1)",
                    width: 4,
                }),
            })
        );
    } else { // 非高铁， 设置非高铁样式
        tempRailFeature.setStyle(ordinaryRailStyle);
        tempRailBaseFeature.setStyle(
            new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: "rgba(255,255,255,1)",
                    width: 3,
                }),
            })
        );
    }
    return [tempRailBaseFeature, tempRailFeature];
}





/*
*
 * 创建 铁路线features
 * return [baseFeature, feature]
 *
function createRailLineFeatures(rail, railLines) {
	var tempRailFeature = new ol.Feature({
		geometry : new ol.geom.LineString(railLines, 'XY'),
		railId : rail.id,
		railName : rail.name
	});
	var tempRailBaseFeature = new ol.Feature({
		geometry : new ol.geom.LineString(railLines, 'XY'),
		railId : rail.id,
		railName : rail.name
	});
	if (rail.classify == '01') { // 高铁， 设置高铁样式
		tempRailFeature.setStyle(
			new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : "red",
					width : 5,
					lineDash : [ 20, 20 ],
					lineCap : 'square',
				}),
				text : new ol.style.Text({
					font : '12px "Microsoft YaHei",sans-serif',
					text : rail.name,
					fill : new ol.style.Fill({
						color : "rgba(255,255,255,1)",
						width : 6
					}),
					stroke : new ol.style.Stroke({
						color : "rgba(159,12,16,1)",
						width : 2
					})
				})
			})
		);
		tempRailBaseFeature.setStyle(
			new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : "rgba(255,255,255,1)",
					width : 6,
				}),
			})
		);
	} else { // 非高铁， 设置非高铁样式
		tempRailFeature.setStyle(
			new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : "blue",
					width : 4,
					lineDash : [ 20, 20 ],
					lineCap : 'square',
				}),
				text : new ol.style.Text({
					font : '12px "Microsoft YaHei",sans-serif',
					text : rail.name,
					fill : new ol.style.Fill({
						color : "rgba(0,0,255,1)",
						width : 2
					}),
					stroke : new ol.style.Stroke({
						color : "rgba(255,255,255,1)",
						width : 2
					})
				})
			})
		);
		tempRailBaseFeature.setStyle(
			new ol.style.Style({
				stroke : new ol.style.Stroke({
					color : "rgba(255,255,255,1)",
					width : 6,
				}),
			})
		);
	}
	return [ tempRailBaseFeature, tempRailFeature ];
}

*
 * HiddenTrajection
 * 创建加载行人易穿行区域feature
 *
function createHiddenTrajectionFeature(hiddenTrajection) {
	//	var tempTrajection = new ol.Feature({
	//		geometry : new ol.geom.Point([parseFloat(hiddenTrajection.lng), parseFloat(hiddenTrajection.lat) ]),
	//	});

	var datas = hiddenTrajection.region;
	var lines = JSON.parse(datas);
	var tempTrajectionpolygonFeature = new ol.Feature({
		geometry : new ol.geom.Polygon([ lines ]),
		trajectionId : hiddenTrajection.id
	});
	tempTrajectionpolygonFeature.setStyle(
		new ol.style.Style({
			fill : new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 10
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(4,247,5,1)",
				width : 3,
			}),
			text : new ol.style.Text({
				font : '12px "Microsoft YaHei",sans-serif',
				text : hiddenTrajection.name,
				fill : new ol.style.Fill({
					color : "rgba(4,247,5,1)",
					width : 3
				}),
				stroke : new ol.style.Stroke({
					color : "rgba(255,255,255,1)",
					width : 2
				})
			})
		})
	);
	return tempTrajectionpolygonFeature;
}

/
 * 创建broadcast的feature
 *
function createBroadcastFeature(broadcast) {
	var tempBroadcast = new ol.Feature({
		geometry : new ol.geom.Point([
			parseFloat(broadcast.lng),
			parseFloat(broadcast.lat) ]),
		broadcastId : broadcast.id,
	});
	tempBroadcast.setStyle(broadcastStyle); // 此处的broadcastStyle已在style.js中定义
	return tempBroadcast;
}

*
 * 创建placeDangerous的feature
 *
function createPlaceDangerousFeature(placeDangerous) {
	var tempPlaceDangerous = new ol.Feature({
		geometry : new ol.geom.Point([
			parseFloat(placeDangerous.lng),
			parseFloat(placeDangerous.lat) ]),
		placedangerousId : placeDangerous.id,
	});
	tempPlaceDangerous.setStyle(placeDangerousStyle); // 此处的placeDangerousStyle已在style.js中定义
	return tempPlaceDangerous;
}

*
 * 创建GuardStation 的Feature
 *
function createGuardStationFeature(guardStation) {
	var tempGuardStationFeature = new ol.Feature({
		geometry : new ol.geom.Point([
			parseFloat(guardStation.lng),
			parseFloat(guardStation.lat)
		]),
		guardStationId : guardStation.id,
	});
	tempGuardStationFeature.setStyle(guardStationStyle); // 此处的guardStationStyle已在style.js中定义
	return tempGuardStationFeature;
}

*
 * 创建School的Feature
 *
function createSchoolFeature(school) {
	var tempSchoolFeature = new ol.Feature({
		geometry : new ol.geom.Point([
								parseFloat(school.lng),
								parseFloat(school.lat) ]),
		schoolId : school.id,
	});
	tempSchoolFeature.setStyle(schoolStyle); // 此处的schoolStyle已在style.js中定义
	return tempSchoolFeature;
}
*/


