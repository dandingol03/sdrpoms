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
//边界图层选中
function setBJBorder(orgId,userOrgId) {
	if(userOrgId.length==3){
		if(orgId.length==3){
			BJBorderLayer.setVisible(true);
			BJJDBorderLayer.setStyle(null);
			BJBorderLayer.setStyle(function(feature, resolution) {
				// 天安门出始终显示五角星
				if(feature.get("ORG_ID") == "bjStar") {
					return starStyle;
				}
				bjBorderStyle.getText().setText(feature.get('ORG_NAME'));
				return bjBorderStyle;
			});
		}
		if(orgId.length==6){
			BJBorderLayer.setVisible(true);
			BJJDBorderLayer.setVisible(true);
			BJBorderLayer.setStyle(function(feature, resolution) {
				// 天安门出始终显示五角星
				if(feature.get("ORG_ID") == "bjStar") {
					return starStyle;
				}
				if(feature.get("ORG_ID") == orgId){
					bjChoosenStyle.getText().setText(feature.get('ORG_NAME'));
					return bjChoosenStyle;
				}
				return null;
			});
			BJJDBorderLayer.setStyle(function(feature, resolution) {
				if(feature.get("ORG_ID").substr(0,6)==orgId){
					bjBorderStyle.getText().setText(feature.get('ORG_NAME'));
					return bjBorderStyle;
				}
				return null;
			});
		}
		if(orgId.length==9){
			BJBorderLayer.setStyle(null);
			BJJDBorderLayer.setVisible(true);
			BJJDBorderLayer.setStyle(function(feature, resolution) {
				// 天安门出始终显示五角星
				if(feature.get("ORG_ID") == "bjStar") {
					return starStyle;
				}
				if(feature.get("ORG_ID") == orgId){
					bjChoosenStyle.getText().setText(feature.get('ORG_NAME'));
					return bjChoosenStyle;
				}
				return null;
			});
			
		}
	}
	if(userOrgId.length==6){
		if(orgId.length==6){
			BJBorderLayer.setStyle(null);
			BJBorderLayer.setStyle(function(feature, resolution) {
				// 天安门出始终显示五角星
				if(feature.get("ORG_ID") == "bjStar") {
					return starStyle;
				}
				if(feature.get("ORG_ID") == orgId){
					bjChoosenStyle.getText().setText(feature.get('ORG_NAME'));
					return bjChoosenStyle;
				}
				return null;
			});
			BJJDBorderLayer.setVisible(true);
			BJJDBorderLayer.setStyle(function(feature, resolution) {
				// 天安门出始终显示五角星
				if(feature.get("ORG_ID") == "bjStar") {
					return starStyle;
				}
				if(feature.get("ORG_ID").substr(0,6)==orgId){
					
					bjBorderStyle.getText().setText(feature.get('ORG_NAME'));
					return bjBorderStyle;
				}
				return null;
			});
		}
		if(orgId.length==9){
			BJBorderLayer.setStyle(null);
			BJJDBorderLayer.setVisible(true);
			BJJDBorderLayer.setStyle(function(feature, resolution) {
				// 天安门出始终显示五角星
				if(feature.get("ORG_ID") == "bjStar") {
					return starStyle;
				}
				if(feature.get("ORG_ID")==orgId){
					bjChoosenStyle.getText().setText(feature.get('ORG_NAME'));
					return bjChoosenStyle;
				}
				return null;
			});
		}
	}
	if(userOrgId.length==9){
		BJBorderLayer.setStyle(null);
		BJJDBorderLayer.setVisible(true);
		BJJDBorderLayer.setStyle(function(feature, resolution) {
			// 天安门出始终显示五角星
			if(feature.get("ORG_ID") == "bjStar") {
				return starStyle;
			}
			if(feature.get("ORG_ID")==orgId){
				bjChoosenStyle.getText().setText(feature.get('ORG_NAME'));
				return bjChoosenStyle;
			}
			return null;
		});
	}
}
//创建铁路图层==================================================================================================
function setBJRailWayLayer(orgId,railIdList){
	var zoom = map.getView().getZoom();
	var railId="";
	for(var i=0;i<railIdArray.length;i++){
		railId+="&railId="+railIdArray[i];
	}
	$.ajax({
		url:"map/getBJRailWayInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJHightSpeedRailLayer.getSource().clear();
			BJHeavyLoadRailLayer.getSource().clear();
			BJOrdinaryRailLayer.getSource().clear();
			BJOtherRailLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var format = new ol.format.WKT();
				var RailWayFeature = format.readFeature(data.result[i].Geom);
				if(zoom>=15){
					if(data.result[i].railStreamId){
						RailWayFeature.setProperties({"id":data.result[i].railStreamId});
						RailWayFeature.setProperties({"name":data.result[i].railName});
					}else{
						RailWayFeature.setProperties({"id":data.result[i].id});
						RailWayFeature.setProperties({"name":data.result[i].name});
					}
				}else{
					RailWayFeature.setProperties({"id":data.result[i].id});
					RailWayFeature.setProperties({"name":data.result[i].name});
				}
				//RailWayFeature.setProperties({"id":data.result[i].id});
				/*RailWayFeature.setProperties({"name":data.result[i].name+""+data.result[i].streamName});*/
				RailWayFeature.setProperties({"classify":data.result[i].classify});
				switch(data.result[i].classify){
					case "01":
						RailWayFeature.setStyle(railWayHightSpeedStyle);
						BJHightSpeedRailLayer.getSource().addFeature(RailWayFeature);
					break;
					case "02":
						RailWayFeature.setStyle(railWayHeavyLoadStyle);
						BJHeavyLoadRailLayer.getSource().addFeature(RailWayFeature);
					break;
					case "03":
						RailWayFeature.setStyle(railWayOrdinaryStyle);
						BJOrdinaryRailLayer.getSource().addFeature(RailWayFeature);
					break;
					case "04":
						RailWayFeature.setStyle(railWayOtherStyle);
						BJOtherRailLayer.getSource().addFeature(RailWayFeature);
					break;
				}
			}
		}
	});
}
//创建安保区图层============================================================================
function setBJSecurityAreaLayer(orgId){
	$.ajax({
		url:"map/getSecurityareaBJBorderInfoList?orgId="+orgId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJSecurityAreaLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var format = new ol.format.WKT();
				console.log('选中安保区-------------------',data)
				var SecurityAreaFeature = format.readFeature(data.result[i].Geom);
				SecurityAreaFeature.setStyle(securityAreaStyle);
				BJSecurityAreaLayer.getSource().addFeature(SecurityAreaFeature);
			}
			BJSecurityAreaLayer.setVisible(true);
		}
	})
}
//创建桥涵图层==================================================================================================
function setBJBridgeAndCulvertLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJBridgeCulvertInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJBridgeAndCulvertLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				/*桥梁 (范围+特大桥)*/
				if(data.result[i].type=='bridge'){
					var BridgeFeature="";
						BridgeFeature = new ol.Feature({
							geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
							id:data.result[i].id,
							name:data.result[i].name,
							oversize:data.result[i].oversize,
							type:"bridge"
						});
					BridgeFeature.setStyle(bridgeStyle);
					BJBridgeAndCulvertLayer.getSource().addFeature(BridgeFeature);
					BJBridgeAndCulvertLayer.setVisible(true);
				}else if(data.result[i].type=='bridgeYard'){
					//特大桥
					if(data.result[i].oversize=="1"){
						/*var TD = [];*/
						var format = new ol.format.WKT();
						var BridgeYard = format.readFeature(data.result[i].Geom);
						var router = BridgeYard.getGeometry().getCoordinates();
						var bridgeStart = new ol.Feature({
							geometry: new ol.geom.Point(router[0][0]),
							type:"bridgeYard",
							oversize:data.result[i].oversize,
							id: data.result[i].id,
							startType:"startType"
						});
						var bridgeEnd = new ol.Feature({
							geometry: new ol.geom.Point(router[0][router[0].length-1]),
							type:"bridgeYard",
							oversize:data.result[i].oversize,
							id: data.result[i].id,
							endType:"endType"
						});
						bridgeStart.setStyle(bridgeInitialStyle);
						bridgeEnd.setStyle(bridgeInitialStyle);
						BridgeYard.setProperties({"id":data.result[i].id});
						BridgeYard.setProperties({"name":data.result[i].name});
						BridgeYard.setProperties({"type":"bridgeYard"});
						BridgeYard.setProperties({"oversize":data.result[i].oversize});
						BJBridgeAndCulvertLayer.getSource().addFeature(BridgeYard);
						BJBridgeAndCulvertLayer.getSource().addFeature(bridgeStart);
						BJBridgeAndCulvertLayer.getSource().addFeature(bridgeEnd);
						BridgeYard.setStyle(bridgeInitialStyle);
					}else if(data.result[i].oversize=="0"){
						var format = new ol.format.WKT();
						var BridgeYard = format.readFeature(data.result[i].Geom);
						BridgeYard.setProperties({"id":data.result[i].id});
						BridgeYard.setProperties({"type":data.result[i].type});
						BridgeYard.setProperties({"bridgeId":data.result[i].id});
						BridgeYard.setProperties({"name":data.result[i].name});
						BridgeYard.setProperties({"oversize":data.result[i].oversize});
						BJBridgeAndCulvertLayer.getSource().addFeature(BridgeYard);
						BridgeYard.setStyle(bridgeInitialStyle);
					}
				}
				/*涵洞 （范围）*/
				if(data.result[i].type=='culvert'){
					var CulvertFeature = new ol.Feature({
						geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
						id:data.result[i].id,
						name:data.result[i].name,
						type:"culvert"
					});
					CulvertFeature.setStyle(culvertStyle);
					BJBridgeAndCulvertLayer.getSource().addFeature(CulvertFeature);
					BJBridgeAndCulvertLayer.setVisible(true);
				}else if(data.result[i].type=='culvertYard'){
					var format = new ol.format.WKT();
					var CulvertYard = format.readFeature(data.result[i].Geom);
					CulvertYard.setProperties({"id":data.result[i].id});
					CulvertYard.setProperties({"type":data.result[i].type});
					CulvertYard.setProperties({"culvertId":data.result[i].id});
					CulvertYard.setProperties({"name":data.result[i].name});
					BJBridgeAndCulvertLayer.getSource().addFeature(CulvertYard);
					CulvertYard.setStyle(culvertYardStyle);
				}
			}
			/*BJBridgeAndCulvertLayer.setVisible(true);*/
		}
	});
}
//创建道口图层==================================================================================================
function setJunctionLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJJunctionInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJJunctionLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				if(data.result[i].type=='junction'){
					var junctionFeature = new ol.Feature({
						geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
						id:data.result[i].id,
						name:data.result[i].name,
						type:"junction"
					});
					junctionFeature.setStyle(junctionStyle);
					BJJunctionLayer.getSource().addFeature(junctionFeature);
					BJJunctionLayer.setVisible(true);
				}else if(data.result[i].type=='yard'){
					var format = new ol.format.WKT();
					var junctionYard = format.readFeature(data.result[i].Geom);
					junctionYard.setProperties({"id":data.result[i].id});
					junctionYard.setProperties({"type":data.result[i].type});
					junctionYard.setProperties({"junctionId":data.result[i].id});
					junctionYard.setProperties({"name":data.result[i].name});
					BJJunctionLayer.getSource().addFeature(junctionYard);
					junctionYard.setStyle(junctionYardStyle);
				}
			}
			BJJunctionLayer.declutter = true;
			/*BJJunctionLayer.setVisible(true);*/
		}
	});
}

//创建隧道图层==================================================================================================
function setTunnelLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJTunneltInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJTunnelLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				if(data.result[i].type=="tunnel"){
					var TunnelFeature = new ol.Feature({
						geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
						id:data.result[i].id,
						name:data.result[i].name
					});
					TunnelFeature.setStyle(tunnelStyle);
					BJTunnelLayer.getSource().addFeature(TunnelFeature);
				}else if(data.result[i].type=="yard"){
					var format = new ol.format.WKT();
					var TunnelYard = format.readFeature(data.result[i].Geom);
					var router = TunnelYard.getGeometry().getCoordinates();
					var tunnelStart = new ol.Feature({
						geometry: new ol.geom.Point(router[0][0]),
						type:data.result[i].type,
						id: data.result[i].id,
						startType:"startType"
					});
					var tunnelEnd = new ol.Feature({
						geometry: new ol.geom.Point(router[0][router[0].length-1]),
						type:data.result[i].type,
						id: data.result[i].id,
						endType:"endType"
					});
					tunnelStart.setStyle(tunnelInitialStyle);
					tunnelEnd.setStyle(tunnelInitialStyle);
					TunnelYard.setProperties({"id":data.result[i].id});
					TunnelYard.setProperties({"name":data.result[i].name});
					TunnelYard.setProperties({"type":data.result[i].type});
					BJTunnelLayer.getSource().addFeature(tunnelStart);
					BJTunnelLayer.getSource().addFeature(tunnelEnd);
					BJTunnelLayer.getSource().addFeature(TunnelYard);
					TunnelYard.setStyle(tunnelInitialStyle);
				}
			}
			BJTunnelLayer.declutter = true;
			BJTunnelLayer.setVisible(true);
		}
	});
}
//创建车站图层==================================================================================================
function setPartStationLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJStationInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJPartStationLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				if(data.result[i].type=='station'){
					if(data.result[i].nature=='04'){
						console.log(111)
						var PartStationFeature = new ol.Feature({
							geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
							id:data.result[i].id,
							name:data.result[i].name,
							type:"station"
						});	
						PartStationFeature.setStyle(partStationYardStyle);
						BJPartStationLayer.getSource().addFeature(PartStationFeature);
						BJPartStationLayer.setVisible(true);
					}else{
						var PartStationFeature = new ol.Feature({
							geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
							id:data.result[i].id,
							name:data.result[i].name,
							type:"station"
						});	
						PartStationFeature.setStyle(partStationStyle);
						BJPartStationLayer.getSource().addFeature(PartStationFeature);
						BJPartStationLayer.setVisible(true);
					}
				}else if(data.result[i].type=='yard'){
					if(data.result[i].nature=='04'){
						var format = new ol.format.WKT();
						var PartStationYard = format.readFeature(data.result[i].Geom);
						PartStationYard.setProperties({"id":data.result[i].id});
						PartStationYard.setProperties({"type":data.result[i].type});
						PartStationYard.setProperties({"stationId":data.result[i].id});
						PartStationYard.setProperties({"name":data.result[i].name});
						BJPartStationLayer.getSource().addFeature(PartStationYard);
						PartStationYard.setStyle(parStationYardStyle);
					}else{
						var format = new ol.format.WKT();
						var PartStationYard = format.readFeature(data.result[i].Geom);
						PartStationYard.setProperties({"id":data.result[i].id});
						PartStationYard.setProperties({"type":data.result[i].type});
						PartStationYard.setProperties({"stationId":data.result[i].id});
						PartStationYard.setProperties({"name":data.result[i].name});
						BJPartStationLayer.getSource().addFeature(PartStationYard);
						PartStationYard.setStyle(parStationYardStyle);
					}
				}else if(data.result[i].type=='stationYard'){
					var format = new ol.format.WKT();
					var PartStationYard = format.readFeature(data.result[i].Geom);
					PartStationYard.setProperties({"id":data.result[i].id});
					PartStationYard.setProperties({"type":data.result[i].type});
					PartStationYard.setProperties({"stationId":data.result[i].id});
					PartStationYard.setProperties({"name":data.result[i].name});
					BJPartStationLayer.getSource().addFeature(PartStationYard);
					PartStationYard.setStyle(parStationYardStyle);
				}
				
			}
			BJPartStationLayer.declutter = true;
		}
	});
}
/*创建进站信号机*/
function setPartStationX(stationId){
	$.ajax({
		url:"map/getBJStationInfoList",
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJPartStationSignalLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				if(stationId==data.result[i].stationId){
					if(data.result[i].type=='signal'){
						var PartStationX = new ol.Feature({
							geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
							id:data.result[i].id,
							stationId:data.result[i].stationId,
							name:data.result[i].name,
							type:"signal"
						});
						BJPartStationSignalLayer.getSource().addFeature(PartStationX);
						PartStationX.setStyle(SignalMachineStyle);
					}
				}
			}
			BJPartStationSignalLayer.declutter = true;
			BJPartStationSignalLayer.setVisible(true);
		}
	});
}
//创建行人易穿行图层==================================================================================================
function setTrajectionPolygonLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJTrajectionInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJTrajectionPolygonLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var TrajectionPolygonFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name,
				});
				TrajectionPolygonFeature.setStyle(trajectionStyle);
				BJTrajectionPolygonLayer.getSource().addFeature(TrajectionPolygonFeature);
				
				var TrajectionPolygonFeatureT = new ol.Feature({
						geometry: new ol.geom.Polygon([JSON.parse(data.result[i].region)]),
						id:data.result[i].id,
						name:data.result[i].name,
						type:'yard'
					});
				BJTrajectionPolygonLayer.getSource().addFeature(TrajectionPolygonFeatureT);
				TrajectionPolygonFeatureT.setStyle(trajectionPolygonStyleT);
			}
			BJTrajectionPolygonLayer.setStyle(function(feature){
				trajectionLableStyle.getText().setText(feature.get('name'));
				return trajectionStyle;
			});
			/*BJTrajectionPolygonLayer.declutter = true;
			BJTrajectionPolygonLayer.setStyle(function(feature){
				trajectionLableStyle.getText().setText(feature.get('name'));
				return trajectionStyle;
			});*/
			BJTrajectionPolygonLayer.declutter = true;
			BJTrajectionPolygonLayer.setVisible(true);
		}
	});
}

//创建重点人图层==================================================================================================
function setKeyPersonLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJKeypersonInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJKeyPersonLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var KeyPersonFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				KeyPersonFeature.setStyle(keyPersonStyle);
				BJKeyPersonLayer.getSource().addFeature(KeyPersonFeature);
			}
			BJKeyPersonLayer.setVisible(true);
		}
	});
}


//联防点位========================================================
//工作站
function setGuardStationLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJGuardStationInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJGuardStationLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var GuardStationFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				GuardStationFeature.setStyle(guardStationStyle);
				BJGuardStationLayer.getSource().addFeature(GuardStationFeature);
			}
			BJGuardStationLayer.setVisible(true);
		}
	});
}
//宣传点
function setPropaGandaLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJPropagandaInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJPropaGandaLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var PropaGandaFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				PropaGandaFeature.setStyle(propagandaStyle);
				BJPropaGandaLayer.getSource().addFeature(PropaGandaFeature);
			}
			BJPropaGandaLayer.setVisible(true);
		}
	});
}
//警示柱
function setBroadCastLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJBroadcastInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJBroadCastLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				//01 在线 02 离线 03 故障
				if(data.result[i].status == "01"){
					var BroadCastFeature = new ol.Feature({
						geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
						id:data.result[i].id,
						name:data.result[i].name
					});
					BroadCastFeature.setStyle(broadcastOnLineStyle);
					BJBroadCastLayer.getSource().addFeature(BroadCastFeature);
				}else if(data.result[i].status == "02"){
					var BroadCastFeature = new ol.Feature({
						geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
						id:data.result[i].id,
						name:data.result[i].name
					});
					BroadCastFeature.setStyle(broadcastOffLineStyle);
					BJBroadCastLayer.getSource().addFeature(BroadCastFeature);
				}else if(data.result[i].status == "03"){
					var BroadCastFeature = new ol.Feature({
						geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
						id:data.result[i].id,
						name:data.result[i].name
					});
					BroadCastFeature.setStyle(broadcastFaultStyle);
					BJBroadCastLayer.getSource().addFeature(BroadCastFeature);
				}
			}
			BJBroadCastLayer.setVisible(true);
		}
	});
}

//摄像头
function setMonitorLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJVideoMonitorInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJMonitorLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var MonitorFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				MonitorFeature.setStyle(monitorStyle);
				BJMonitorLayer.getSource().addFeature(MonitorFeature);
			}
			BJMonitorLayer.setVisible(true);
		}
	});
}


//派出所
function setPoliceStationLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJPoliceHouseInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJPoliceStationLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var PoliceStationFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				PoliceStationFeature.setStyle(policeStationStyle);
				BJPoliceStationLayer.getSource().addFeature(PoliceStationFeature);
			}
			BJPoliceStationLayer.setVisible(true);
		}
	});
}

//警务站
function setPolicePtationLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJPoliceStationInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJPolicePtationLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var PolicePtationFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				PolicePtationFeature.setStyle(PolicePtationStyle);
				BJPolicePtationLayer.getSource().addFeature(PolicePtationFeature);
			}
			BJPolicePtationLayer.setVisible(true);
		}
	});
}
//专职队员
function setFullTimeMemberLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJTeamMemberInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJFullTimeMemberLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var FullTimeMemberFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				FullTimeMemberFeature.setStyle(fullTimeMemberStyle);
				BJFullTimeMemberLayer.getSource().addFeature(FullTimeMemberFeature);
			}
			BJFullTimeMemberLayer.setVisible(true);
		}
	});
}
//在线干部
function setAppLayer(orgId,railIdList){
	var railId="";
		for(var i=0;i<railIdArray.length;i++){
			railId+="&railId="+railIdArray[i];
		}
	$.ajax({
		url:"map/getBJCardeInfoList?orgId="+orgId+railId,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJAppLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var AppFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				AppFeature.setStyle(policeStationStyle);
				BJAppLayer.getSource().addFeature(AppFeature);
			}
			BJAppLayer.setVisible(true);
		}
	});
}
//隐患点==========================================================================
function setDangerPointLayer(orgId,railIdList,type){
	var railId="";
	for(var i=0;i<railIdArray.length;i++){
		railId+="&railId="+railIdArray[i];
	}
	$.ajax({
		url:"map/getBJDangerInfoList?orgId="+orgId+railId+"&dangerType="+type,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJDangerPointLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var DangerPointFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				switch(data.result[i].handleStatus){
					case "01":
						DangerPointFeature.setStyle(dangerPonintStyle01);
						break;
					case "02":
						DangerPointFeature.setStyle(dangerPonintStyle02);
						break;
					case "03":
						DangerPointFeature.setStyle(dangerPonintStyle03);
						break;
					case "04":
						DangerPointFeature.setStyle(dangerPonintStyle04);
						break;
				}
				BJDangerPointLayer.getSource().addFeature(DangerPointFeature);
			}
			BJDangerPointLayer.setVisible(true);
		}
	});
}

//周边场所================================================================================================
function setPeripheralPlaceLayer(orgId,railIdList,perPlaceType,isImportant){
	var railId="";
	for(var i=0;i<railIdArray.length;i++){
		railId+="&railId="+railIdArray[i];
	}
	
	$.ajax({
		url:"map/getUserPeripheralPlaceInfoList?orgId="+orgId+railId+"&perPlaceId="+perPlaceType+"&isImportant="+isImportant,
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJPeripheralPlaceLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var PeripheralPlaceFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				switch(data.result[i].type){
				case "01":
					PeripheralPlaceFeature.setStyle(residenceStyle);
					break;
				case "02":
					PeripheralPlaceFeature.setStyle(companyStyle);
					break;
				case "03":
					PeripheralPlaceFeature.setStyle(marketStyle);
					break;
				case "04":
					PeripheralPlaceFeature.setStyle(schoolStyle);
					break;
				case "05":
					PeripheralPlaceFeature.setStyle(miningStyle);
					break;
				case "06":
					PeripheralPlaceFeature.setStyle(constructionSiteStyle);
					break;
				case "07":
					PeripheralPlaceFeature.setStyle(leisureTimeStyle);
					break;
				case "08":
					PeripheralPlaceFeature.setStyle(commercialStyle);
					break;
				case "09":
					PeripheralPlaceFeature.setStyle(roomAndBoardStyle);
					break;
				case "10":
					PeripheralPlaceFeature.setStyle(breedStyle);
					break;
				case "11":
					PeripheralPlaceFeature.setStyle(plantStyle);
					break;
				case "12":
					PeripheralPlaceFeature.setStyle(gasStationStyle);
					break;
				case "13":
					PeripheralPlaceFeature.setStyle(riversAndLakesStyle);
					break;
				case "14":
					PeripheralPlaceFeature.setStyle(otherStyle);
					break;
				}
				BJPeripheralPlaceLayer.getSource().addFeature(PeripheralPlaceFeature);
			}
			BJPeripheralPlaceLayer.setVisible(true);
		}
	});
}
//地图要素feature点击事件===============================================================================
function setFeatrueClick(feature,layer,coordinate){
	//桥涵
	if(layer==BJBridgeAndCulvertLayer){
		var tempId = feature.getProperties().id;
		var tempIdIntercept = feature.getProperties().id.substr(0,4);
		if(tempIdIntercept=="brid"){
			var tempFeatrues = BJBridgeAndCulvertLayer.getSource().getFeatures();
			for(var i=0;i<tempFeatrues.length;i++){
				if(tempId==tempFeatrues[i].get("id")){
					if(tempFeatrues[i].get("oversize")=="1" && tempFeatrues[i].get("type")=="bridgeYard"){
						tempFeatrues[i].setStyle(bridgeBgstyle);
					}
					if(tempFeatrues[i].get("type")=="bridgeYard"){
						if(tempFeatrues[i].get("bridgeId")==feature.getProperties().id){
							tempFeatrues[i].setStyle(bridgeCkStyle);
						}
					}
				}
			}
			getBJOverLayersInfo(tempId,"map/baseInfoPartBridge");
		}else if(tempIdIntercept=="culv"){
			var tempCulvertFeatrues = BJBridgeAndCulvertLayer.getSource().getFeatures();
			for(var i=0;i<tempCulvertFeatrues.length;i++){
				if(tempId==tempCulvertFeatrues[i].get("id")){
					if(tempCulvertFeatrues[i].get("type")=="culvertYard"){
						if(tempCulvertFeatrues[i].get("id")==feature.getProperties().id){
							tempCulvertFeatrues[i].setStyle(culvertCkStyle);
						}
					}	
				}
			}
			getBJOverLayersInfo(tempId,"map/baseInfoPartCulvert");
		}
	}
	//重点人
	if(layer==BJKeyPersonLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/keyperson");
	}
	//道口
	if(layer==BJJunctionLayer){
		/*BJJunctionLayer.getSource().clear();*/
		if(feature.getProperties().type=="yard"){
			return;
		}
		var tempFeatrues = BJJunctionLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				if(tempFeatrues[i].get("junctionId")==feature.getProperties().id){
					tempFeatrues[i].setStyle(junctionYardAllStyle);
				}
			}
		}
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoPartJunction");
	}
	//隧道
	if(layer==BJTunnelLayer){
			/*if(feature.getProperties().type=="yard"){
				return;
			}*/
			var tempId = feature.getProperties().id;
			var tempFeatrues = BJTunnelLayer.getSource().getFeatures();
			for(var i=0;i<tempFeatrues.length;i++){
				if(tempFeatrues[i].get("type")=="yard"){
					if(tempFeatrues[i].get("id")==feature.getProperties().id){
						tempFeatrues[i].setStyle(tunnelBgstyle);
					}
				}
			}
			getBJOverLayersInfo(tempId,"map/baseInfoPartTunnel");
		}
	//工作站测试
	if(layer==ceshiGZZLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/CSPoliceHouse");
		
	}
	//监控测试
	if(layer==ceshiJKLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/video");
		
	}
	//车站
	if(layer==BJPartStationLayer){
		BJPartStationSignalLayer.getSource().clear();
		if(feature.getProperties().type=="yard"){
			return;
		}
		var tempFeatrues = BJPartStationLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				if(tempFeatrues[i].get("stationId")==feature.getProperties().id){
					tempFeatrues[i].setStyle(parStationYardAllStyle);
				}
			}else if(tempFeatrues[i].get("type")=="stationYard"){
				if(tempFeatrues[i].get("stationId")==feature.getProperties().id){
					tempFeatrues[i].setStyle(parStationYardAllStyle);
				}
			}
		}
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoPartStation");
	}
	//易穿行
	if(layer==BJTrajectionPolygonLayer){
		BJPartStationSignalLayer.getSource().clear();
		if(feature.getProperties().type=="yard"){
			return;
		}
		var tempFeatrues = BJTrajectionPolygonLayer.getSource().getFeatures();
		for(var i=0;i<tempFeatrues.length;i++){
			if(tempFeatrues[i].get("type")=="yard"){
				if(tempFeatrues[i].get("id")==feature.getProperties().id){
					tempFeatrues[i].setStyle(trajectionPolygonStyleC);
				}
			}
		}
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoHiddenTrajection");
	}
	//隐患点
	if(layer==BJDangerPointLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/dangerInfo");
	}
	//周边场所==================================================
	//工作站
	if(layer==BJGuardStationLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoDefenceGuardStation");
	}
	//宣传点
	if(layer==BJPropaGandaLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoDefencePropaganda");
	}
	//警示柱
	if(layer==BJBroadCastLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoDefenceBroadcast");
	}
	//摄像头
	if(layer==BJMonitorLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/videoMonitorInfo");
		var strHtml = "<iframe id='monitor' src='map/videoMonitorInfo?id=" + tempId + "' frameborder='0' style='height:100%;width:100%;'></iframe>";
		$("#emrhPw").html(strHtml);
		/*$('#monitor').onload(function(){
   	 		
   	 	})*/
	}
	//派出所
	if(layer==BJPoliceStationLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoDefencePoliceHouse");
		
	}
	//警务站
	if(layer==BJPolicePtationLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoPoliceStation");
	}
	//专职队员
	if(layer==BJFullTimeMemberLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/teamMemberInfo");
	}
	//在线干部
	if(layer==BJAppLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/cardeInfo");
	}
	//周边场所======================================
	if(layer==BJPeripheralPlaceLayer){
		var tempId = feature.getProperties().id;
		getBJOverLayersInfo(tempId,"map/baseInfoPeripheralPlace");
	}
	//高铁======================================
	if(layer==BJHightSpeedRailLayer){
		var tempId = feature.getProperties().id;
		var tempBorderFeatures = BJBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempJDBorderFeatures = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempBorderName =tempBorderFeatures[0].get("ORG_NAME");
		var tempJDBordertName =tempJDBorderFeatures[0].get("ORG_NAME");
		getBJRailLayersInfo(tempId,"map/baseInfoRail",tempBorderName,tempJDBordertName,coordinate);
	}
	//重载======================================
	if(layer==BJHeavyLoadRailLayer){
		var tempId = feature.getProperties().id;
		var tempBorderFeatures = BJBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempJDBorderFeatures = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempBorderName =tempBorderFeatures[0].get("ORG_NAME");
		var tempJDBordertName =tempJDBorderFeatures[0].get("ORG_NAME");
		getBJRailLayersInfo(tempId,"map/baseInfoRail",tempBorderName,tempJDBordertName,coordinate);
	}
	//普通======================================
	if(layer==BJOrdinaryRailLayer){
		var tempId = feature.getProperties().id;
		var tempBorderFeatures = BJBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempJDBorderFeatures = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempBorderName =tempBorderFeatures[0].get("ORG_NAME");
		var tempJDBordertName =tempJDBorderFeatures[0].get("ORG_NAME");
		getBJRailLayersInfo(tempId,"map/baseInfoRail",tempBorderName,tempJDBordertName,coordinate);
	}
	//其它======================================
	if(layer==BJOtherRailLayer){
		var tempId = feature.getProperties().id;
		var tempBorderFeatures = BJBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempJDBorderFeatures = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
		var tempBorderName =tempBorderFeatures[0].get("ORG_NAME");
		var tempJDBordertName =tempJDBorderFeatures[0].get("ORG_NAME");
		getBJRailLayersInfo(tempId,"map/baseInfoRail",tempBorderName,tempJDBordertName,coordinate);
	}
	
	//边界
	if(layer==BJBorderLayer){
		return ;
	}
	if(feature.getProperties().name){
		coordinate = feature.getGeometry().getClosestPoint(coordinate);
		$('#popup-content').html(feature.getProperties().name);
		overlay.setPosition(coordinate);
	}
}
//地图弹出图层======================================================================================
function getBJOverLayersInfo(id, url) {
	$.ajax({
		url : url,
		type : "GET",
		dataType : 'html',
		data : {
			"id" : id,
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
		}, 
		success : function(data) {
			$(".ex_main_rightheader_pw4").html(data);
			$(".ex_main_rightheader_pw4").fadeIn("6000");
		}
	});
}
//铁路线弹出层=====================================================
function getBJRailLayersInfo(id, url,countryName,streetName,lnglat) {
	$.ajax({
		url : url,
		type : "GET",
		dataType : 'html',
		data : {
			"id" : id,
			"orgId":adminDivisionOrgId,
			"lnglat":lnglat
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
		}, 
		success : function(data) {
			$(".ex_main_rightheader_pw4").html(data);
			$(".ex_main_rightheader_pw4").slideDown('slow');
			$('#countryName').html(countryName);
			$('#streetName').html(streetName);
		}
	});
}
//专题图过滤=============================================================================================
function updateThemeSTYLEFilter(lay, CQL_Para, STYLE) {
	var filterParams = {
		'CQL_FILTER' : CQL_Para,
		'STYLES' : STYLE
	};
	filterParams["CQL_FILTER"] = CQL_Para;
	lay.getSource().updateParams(filterParams);
}

//根据featrue不同类型进行线段表示切分 包含类型LineString MultiLineString 
function CreateMutilLine(feature,resolution,dashPixel){
	var tempGeom = feature.getGeometry();
	if(tempGeom instanceof ol.geom.LineString){
		var mutilLineArray = CutLineForMutilLine(tempGeom,resolution,150);
		if(mutilLineArray.length>0){
			return new ol.geom.MultiLineString(mutilLineArray);
		}else{
			return null;
		}
	}
	if(tempGeom instanceof ol.geom.MultiLineString){
		var mutilLineArray = new Array();
		var tempLineStringList = tempGeom.getLineStrings();
		for(var i=0;i<tempLineStringList.length;i++){
			var tempArray = CutLineForMutilLine(tempLineStringList[i],resolution,150);
			for(var j=0;j<tempArray.length;j++){
				mutilLineArray.push(tempArray[j]);
			}
		}
		if(mutilLineArray.length>0){
			return new ol.geom.MultiLineString(mutilLineArray);
		}else{
			return null;
		}
	}
}

//根据一条线创建自适应线段数组进行文字显示
//lineString ol.Geom.LineString
//resolution 分辨率
//dashPixel 间隔像素长度
function CutLineForMutilLine(lineString,resolution,dashPixel){
	var tempCoorArray = lineString.getCoordinates();
	//计算间隔
	var dash = 0;
	//计算起点
	var tempStartCoordinate = tempCoorArray[0];
	//返回线段
	var resultMultiLineString = new Array();
	//临时数组
	var tempArray = new Array();
	tempArray.push(tempCoorArray[0]);
	for(var i=1;i<tempCoorArray.length;i++){
		tempArray.push(tempCoorArray[i]);
		if(((CaculateDistance(tempStartCoordinate,tempCoorArray[i])/resolution)+0.5)>dashPixel){
			if(tempArray[tempArray.length-1][0]<tempArray[0][0]){
				tempArray.reverse();
			}
			if(dash%2==0){
				resultMultiLineString.push(tempArray);
			}
			//重新加载起点 清空临时数组
			tempStartCoordinate = tempCoorArray[i];
			tempArray = new Array();
			tempArray.push(tempCoorArray[i]);
			dash++;
		}
	}
	return resultMultiLineString;
}
//两点欧式距离
function CaculateDistance(coordinate1,coordinate2){
	var X1 = coordinate1[0];
	var Y1 = coordinate1[1];
	var X2 = coordinate2[0];
	var Y2 = coordinate2[1];
	return Math.sqrt((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1));
}

/*工作站 测试*/
function setGzzLayer(){
	$.ajax({
		url:"map/getBJCSPoliceHouseInfoList",
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJGuardStationLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var GuardStationFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				switch(data.result[i].buildingSituation){
				case "已建成":
					GuardStationFeature.setStyle(ceshiGZZyjcStyle);
					break;
				case "已选址未建":
					GuardStationFeature.setStyle(ceshiGZZyxzStyle);
					break;
				case "租赁":
					GuardStationFeature.setStyle(ceshiGZZzlStyle);
					break;
				}
				ceshiGZZLayer.getSource().addFeature(GuardStationFeature);
			}
			ceshiGZZLayer.setVisible(true);
		}
	});
}

/*监控 测试*/
function setJkLayer(){
	$.ajax({
		url:"map/getBJVideoInfoList",
		type:"POST",
		dataType:'json',
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			console.log("异常情况:["+textStatus+"]");
			},
		success:function(data){
			BJGuardStationLayer.getSource().clear();
			for(var i=0;i<data.result.length;i++){
				var GuardStationFeature = new ol.Feature({
					geometry: new ol.geom.Point([data.result[i].lng, data.result[i].lat]),
					id:data.result[i].id,
					name:data.result[i].name
				});
				GuardStationFeature.setStyle(ceshiJKStyle);
				ceshiJKLayer.getSource().addFeature(GuardStationFeature);
			}
			ceshiJKLayer.setVisible(true);
		}
	});
}


