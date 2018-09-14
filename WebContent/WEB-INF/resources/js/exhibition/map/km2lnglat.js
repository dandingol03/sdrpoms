function locationRailKm() {	
	var  k = document.getElementById('locationRailK').value;
	k = parseFloat(k);
	if(isNaN(k)) {
		alert("请输入数字");
		return;
	}
	k = isNaN(k) ? 0 : k;
	var m = document.getElementById('locationRailM').value ;
	m = parseFloat(m);
	if(isNaN(m)) {
		alert("请输入数字");
		return;
	}
	m = isNaN(m) ? 0 : m;
	
	var  z = k * 1000 + m;
	var railId = $('#locationRail').val();
	var railName=$('#locationRail option:selected').text();
	$.ajax({
		url : '/sdrpoms/pubMapRailData/CalculationLnglat',
		type : 'post',
		data: {
			'kilometerMark': z,
			'baseInfoRailId': railId,
		},
		dataType : 'json',
		error : ajaxErrorCb,
		success : function(data) {
			if(data) {
				KmLocationLayer.getSource().clear();
				/*hideAllRail();*/
				showRail({"id": railId, "name": railName, "classify": data.classify,});
				var f = new ol.Feature({
					geometry : new ol.geom.Point([parseFloat(data.lng), parseFloat(data.lat) ]),
					type: "kmLocation",
					lnglat: data.lng + "," + data.lat,
					km: z,
					railId: railId,
					id: railId,
					railName:railName,
					url: "map/getKmLocationPopWin"
				});
				
				var lnglat = f.getProperties().lnglat;
				f.setStyle(locationRailStyle);
				
				/*map.getView().setZoom(13.5);*/
				KmLocationLayer.getSource().addFeature(f);
				/*map.getView().setCenter([parseFloat(data.lng)+0.07, parseFloat(data.lat)]);*/
				$.ajax({
					url : "map/getKmLocationPopWin",
					type : "GET",
					dataType : 'html',
					data : {
						"railId" : f.get("id"),
						"lnglat": f.get("lnglat"),
						"km": f.get("km")
					},
					error: ajaxErrorCb,
					success : function(dataHTML) {
//						console.log("======================dataHTML",dataHTML)
						mapContent.innerHTML = dataHTML;
						mapOverlay.setPosition([parseFloat(data.lng), parseFloat(data.lat)]);
						var p = map.getPixelFromCoordinate([parseFloat(data.lng), parseFloat(data.lat)]);
						var features =[];
						map.forEachFeatureAtPixel(p, function(feature, layer) {
							console.log(feature.getProperties());
							features.push(feature);
						});
						var lastFeature = features[features.length-1];
						if(lastFeature.get('Name')) {
							$("#orgKmLocationName").html(lastFeature.get('Name'));
						}
						var KmOrgName = lastFeature.getProperties().Name;
						$('#KmName').html(KmOrgName);
						$('.ol-popup').css('bottom','27px');
					}
				});
			}
		}
	});
}



