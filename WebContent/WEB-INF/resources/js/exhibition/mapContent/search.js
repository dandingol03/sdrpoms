'use strict';
$(document).keypress(function(e) {
	// 回车键事件
    if(e.which == 13) {
	   	jQuery(".ex_map_searchbtn").click();
    }
 });
function searchData(page) {
	if (!page) {
		page = 1;		
	}
	var pageRows = 12;
	
	var keyword = $('#keyword').val();
	if(!keyword) {
		return;
	}
	
	$.ajax({
		url: '/sdrpoms/baseInfo/dataRetrievalQueryList',
		type: "GET",
		dataType: 'json',
		data: {
			'name': keyword,
			'orgId': orgId,
			'page' : page,
			'rows' : pageRows
		},
		error: ajaxErrorCb,
		success: function(data) {
			console.log('query data = ', data);
			var tempHtml = "";
			var rows = data.rows;
			if (rows && rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					tempHtml += "<li data-type='" + rows[i].dataType + "' data-id='" + rows[i].id + "' data-url='" + rows[i].url + "' data-lnglat='[" +[rows[i].lng, rows[i].lat]+ "]' onclick='retrievalDataClick();'>" + rows[i].name + "</li>"
				}
				$("#searchContent").html(tempHtml);
			}
			if (!data.total || data.total <= pageRows) {
				return;
			}
			$("#searchContentPage").paging({
				pageNo : page,
				totalPage : Math.ceil(data.total / pageRows),
				totalSize : data.total,
				callback : function(num) {
					searchData(num);
				}
			})
		}
	});
}

function retrievalDataClick() {
	var $el = $(event.target);
	var type = $el.data('type');
	$.ajax({
		url: $el.data('url'),
		type: "GET",
		dataType: 'html',
		data: {
			"id": $el.data('id')
		},
		error: ajaxErrorCb,
		success: function(data) {
			mapContent.innerHTML = data;
			var lnglat = $el.data('lnglat');
			console.log('lnglat = ', lnglat);
			if(Array.isArray(lnglat)) {
				mapOverlay.setPosition(lnglat);
				searchCbs[type].call(this);
				map.getView().setCenter(lnglat);
			}
		}
	});
}
/*
 * "行人易穿行部位": function() {
		HiddenTrajectionLayer.setVisible(true);
		HiddenTrajectionLayer.getSource().clear();
		var ht = $('#hiddenTrajectionPopWin').data('hiddenTrajection');
		if(!ht || !ht.region) {
			return ;
		}
		var lines = JSON.parse(ht.region);
		var tempTrajectionpolygonFeature = new ol.Feature({
			geometry: new ol.geom.Polygon([lines]),
			trajectionId: ht.id,
			id: ht.id,
			name: ht.name,
			type: 'hiddenTrajection',
			url: 'map/getHiddenTrajectionPopWin'
		});
		tempTrajectionpolygonFeature.setStyle(trajectionpolygonStyle);
		HiddenTrajectionLayer.getSource().addFeature(tempTrajectionpolygonFeature);
	},
 * 
 * 
 * */
var searchCbs = {
	"车站": function() {
		BJPartStationLayer.getSource().clear();
		BJPartStationLayer.setVisible(true);
		var station = $('#partStationPopWin').data('partStation');
		if(!station) {
			return;
		}
		var tempPartStation = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(station.lng), parseFloat(station.lat)]),
			partStationId: station.id,
			id: station.id,
			url: 'map/getPartStationPopWin',
			type: 'partStation'
		});
		tempPartStation.setStyle(partStationStyle);
		BJPartStationLayer.getSource().addFeature(tempPartStation);
		$.ajax({
			url : "map/getBJPartStationYardStationInfo",
			type : "GET",
			dataType : 'JSON',
			data : {
				"stationId" :  station.id,
			},
			error: ajaxErrorCb,
			success : function(data) {
				StationYardTwoLayer.getSource().clear();
				var features = data.filter(function(item) {
					return item.stationYardRange;
				}).map(function(item) {
					var lines = JSON.parse(item.stationYardRange);
					var f = createPolygonFeature(lines, item.stationYardId, item.stationYardName, 'stationTwoYard', 'map/getPartStationYardPopWin');
					f.setStyle(stationYardTwoStyle);
					return f;
				});
				map.getView().setZoom(15);
				StationYardTwoLayer.getSource().addFeatures(features);
			}
		});
		
	},
	"桥梁": function() {
		BJBridgeLayer.getSource().clear();
		BJBridgeLayer.setVisible(true);
		var bridge = $('#bridgePopWin').data('bridge');
		if(!bridge || isNaN(parseFloat(bridge.lng)) || isNaN(parseFloat(bridge.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(bridge.lng), parseFloat(bridge.lat)]),
			bridgeId: bridge.id,
			id: bridge.id,
			url: 'map/getBridgePopWin',
			type: 'bridge'
		});
		f.setStyle(bridgeStyle);
		BJBridgeLayer.getSource().addFeature(f);
	},
	"道口": function() {
		BJJunctionLayer.getSource().clear();
		BJJunctionLayer.setVisible(true);
		var junction = $('#junctionPopWin').data('junction');
		if(!junction || isNaN(parseFloat(junction.lng)) || isNaN(parseFloat(junction.lat))) {
			return;
		}
		
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(junction.lng), parseFloat(junction.lat)]),
			junctionId: junction.id,
			id: junction.id,
			url: 'map/getJunctionPopWin',
			type: 'junction'
		});
		f.setStyle(junctionStyle);
		BJJunctionLayer.getSource().addFeature(f);
	},
	"隧道": function() {
		BJTunnelLayer.getSource().clear();
		BJTunnelLayer.setVisible(true);
		var tunnel = $('#tunnelPopWin').data('tunnel');
		if(!tunnel || isNaN(parseFloat(tunnel.lng)) || isNaN(parseFloat(tunnel.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(tunnel.lng), parseFloat(tunnel.lat)]),
			tunnelId: tunnel.id,
			id: tunnel.id,
			url: 'map/getTunnelPopWin',
			type: 'tunnel'
		});
		f.setStyle(tunnelStyle);
		BJTunnelLayer.getSource().addFeature(f);
	},
	"涵洞": function() {
		
	},
	"公跨铁": function() {
		
	},
	"检修口": function() {
		
	},
	"基站": function() {
		
	},
	"应急疏散通道": function() {
		
	},
	"学校": function() {
		BJSchoolLayer.getSource().clear();
		BJSchoolLayer.setVisible(true);
		var school = $('#schoolPopWin').data('school');
		if(!school || isNaN(parseFloat(school.lng)) || isNaN(parseFloat(school.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(school.lng), parseFloat(school.lat)]),
			schoolId: school.id,
			id: school.id,
			url: 'map/getSchoolPopWin',
			type: 'school'
		});
		f.setStyle(schoolStyle);
		BJSchoolLayer.getSource().addFeature(f);
	},
	"社区": function() {
		
	},
	"普通单位": function() {
		
	},
	"危爆场所": function() {
		BJPlaceDangerousLayer.getSource().clear();
		BJPlaceDangerousLayer.setVisible(true);
		var placeDangerous = $('#placeDangerPopWin').data('placeDanger');
		if(!placeDangerous || isNaN(parseFloat(placeDangerous.lng)) || isNaN(parseFloat(placeDangerous.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(placeDangerous.lng), parseFloat(placeDangerous.lat) ]),
			placedangerousId: placeDangerous.id,
			id: placeDangerous.id,
			url: 'map/getPlaceDangerousPopWin',
			type: 'placeDanger'
		});
		f.setStyle(placeDangerousStyle);
		BJPlaceDangerousLayer.getSource().addFeature(f);
	},
	"行人易穿行部位": function() {
		HiddenTrajectionLayer.getSource().clear();
		HiddenTrajectionLayer.setVisible(true);
		var hiddenTrajection = $('#hiddenTrajectionPopWin').data('hiddenTrajection');
		if(!hiddenTrajection || !hiddenTrajection.region) {
			return;
		}
		var lines = JSON.parse(hiddenTrajection.region);
		var f = createPolygonFeature(lines, hiddenTrajection.id, hiddenTrajection.name, 'hiddenTrajection', 'map/getHiddenTrajectionPopWin');
		f.setStyle(trajectionpolygonStyle);
		HiddenTrajectionLayer.getSource().addFeature(f);
	},
	"重点人员": function() {
		BJKeyplaceLayer.getSource().clear();
		BJKeyplaceLayer.setVisible(true);
		var keyplace = $('#keypersonPopWin').data('keyperson');
		if(!keyplace || isNaN(parseFloat(keyplace.lng)) || isNaN(parseFloat(keyplace.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(keyplace.lng), parseFloat(keyplace.lat) ]),
			keypersonId: keyplace.id,
			id: keyplace.id,
			url: 'map/getKeypersonPopWin',
			type: 'keyPerson'
		});
		f.setStyle(keyPersonStyle);
		BJKeyplaceLayer.getSource().addFeature(f);
	},
	"护路工作站": function() {
		BJGuardStationLayer.getSource().clear();
		BJGuardStationLayer.setVisible(true);
		var guardStation = $('#guardStationPopWin').data('guardStation');
		if(!guardStation || isNaN(parseFloat(guardStation.lng)) || isNaN(parseFloat(guardStation.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(guardStation.lng), parseFloat(guardStation.lat)]),
			guardStationId: guardStation.id,
			id: guardStation.id,
			url: 'map/getGuardStationPopWin',
			type: 'guardStation'
		});
		f.setStyle(guardStationStyle);
		BJGuardStationLayer.getSource().addFeature(f);
	},
	"广播警示柱": function() {
		BJBroadcastLayer.getSource().clear();
		BJBroadcastLayer.setVisible(true);
		var broadcast = $('#broadcastPopWin').data('broadcast');
		if(!broadcast || isNaN(parseFloat(broadcast.lng)) || isNaN(parseFloat(broadcast.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(broadcast.lng), parseFloat(broadcast.lat)]),
			broadcastId: broadcast.id,
			id: broadcast.id,
			url: 'map/getBroadcastPopWin',
			type: 'broadcast'
		});
		f.setStyle(broadcastStyle);
		BJBroadcastLayer.getSource().addFeature(f);
	},
	"护路宣传点": function() {
		BJPropagandaLayer.getSource().clear();
		BJPropagandaLayer.setVisible(true);
		var propaganda = $('#propagandaPopWin').data('propaganda');
		if(!propaganda || isNaN(parseFloat(propaganda.lng)) || isNaN(parseFloat(propaganda.lat))) {
			return;
		}
		var f = new ol.Feature({
			geometry: new ol.geom.Point([parseFloat(propaganda.lng), parseFloat(propaganda.lat)]),
			propagandaId: propaganda.id,
			id: propaganda.id,
			url: 'map/getPropagandaPopWin',
			type: 'propaganda'
		});
		f.setStyle(propagandaStyle);
		BJPropagandaLayer.getSource().addFeature(f);
	},
	"铁路": function() {
		
	}
};