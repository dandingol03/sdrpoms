'use strict';
/**
 *  地图功能区
 */
var formatIP = "image/png";
/**
 *  样式专区
 */
//没有样式
var noStyle = new ol.style.Style({});
//首都五角星样式
var starStyle = new ol.style.Style({
	cursor : 'pointer',
	image : new ol.style.Icon(({
		anchor : [ 0.5, 0.5 ],
		src : '../resources/image/exhibition/map/chinaStar.png'
	}))
});
//边界样式
var bjBorderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "rgba(30,255,0,0.5)",
			width : 2,
			lineDash: [10, 10, 1, 10],
            lineCap: 'square',
		}),
		fill : new ol.style.Fill({
			color : "rgba(255,255,255,0)"
		}),
		text : new ol.style.Text({
			font : '18px "Microsoft YaHei",sans-serif',
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
var bjChoosenStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "rgba(246,0,141,0.8)",
			width : 3,
		}),
		fill : new ol.style.Fill({
			color : "rgba(255,255,255,0.1)"
		}),
		text : new ol.style.Text({
			font : '20px "Microsoft YaHei",sans-serif',
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
//铁路样式
//文字  高铁
function CreateLineTextStyle(feature,resolution){
	return new ol.style.Text({
		text: feature.get("name"),
		textAlign:"center",
		font: '16px Calibri,sans-serif',
		overflow: true,
		exceedLength:true,
		maxAngle:"6.283185307179586",
		placement:"line",
		fill: new ol.style.Fill({
			color: '#ffffff',
			width: 3
		}),
		stroke: new ol.style.Stroke({
			color: 'rgba(254,0,0,1)',
			width: 1
		})
	});
}
//文字  重载
function CreateLineBTextStyle(feature,resolution){
	return new ol.style.Text({
		text: feature.get("name"),
		textAlign:"center",
		font: '16px Calibri,sans-serif',
		overflow: true,
		exceedLength:true,
		maxAngle:"6.283185307179586",
		placement:"line",
		fill: new ol.style.Fill({
			color: '#ffffff',
			width: 3
		}),
		stroke: new ol.style.Stroke({
			color: 'rgba(0,0,255,1)',
			width: 1
		})
	});
};
//文字  普通
function CreateLineCTextStyle(feature,resolution){
	return new ol.style.Text({
		text: feature.get("name"),
		textAlign:"center",
		font: '16px Calibri,sans-serif',
		overflow: true,
		exceedLength:true,
		maxAngle:"6.283185307179586",
		placement:"line",
		fill: new ol.style.Fill({
			color: '#ffffff',
			width: 3
		}),
		stroke: new ol.style.Stroke({
			color: 'rgba(0,0,255,1)',
			width: 1
		})
	});
}
//文字  其他
function CreateLineDTextStyle(feature,resolution){
	return new ol.style.Text({
		text: feature.get("name"),
		textAlign:"center",
		font: '16px Calibri,sans-serif',
		overflow: true,
		exceedLength:true,
		maxAngle:"6.283185307179586",
		placement:"line",
		fill: new ol.style.Fill({
			color: '#ffffff',
			width: 3
		}),
		stroke: new ol.style.Stroke({
			color: 'rgba(0,0,255,1)',
			width: 1
		})
	});
}
var  securityAreaStyle = function(feature, resolution){
	//背景边界
	var borderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#ff5809",
			width : 2,
		}),
	});
	//虚线
	var dashStyle = new ol.style.Style({
		fill : new ol.style.Fill({
			color : "rgba(0,0,0,0)"
		}),
	});
	return [borderStyle,dashStyle];
}
//创建铁路样式 -----高铁
var railWayHightSpeedStyle = function(feature, resolution){
	//背景边界
	var borderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#fe0000",
			width : 5,
			lineCap: 'round',
			lineJoin: 'round'
		})
	});
	//虚线
	var dashStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#ffffff",
			width : 3,
			lineCap: 'round',
			lineJoin: 'round',
			lineDash: [15,15]
		})
	});
	var tempText = CreateLineTextStyle(feature,resolution);
	var textStyle = new ol.style.Style({
		geometry: function(feature) {
			return CreateMutilLine(feature,resolution,75);
		},
		text: tempText
	});
	return [borderStyle,dashStyle,textStyle];
};
//创建铁路样式 -----重载
var railWayHeavyLoadStyle = function(feature, resolution){
	//背景边界
	var borderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#0000FE",
			width : 5,
			lineCap: 'round',
			lineJoin: 'round'
		})
	});
	//虚线
	var dashStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#ffffff",
			width : 3,
			lineCap: 'round',
			lineJoin: 'round',
			lineDash: [20,20]
		})
	});
	var tempText = CreateLineBTextStyle(feature,resolution);
	var textStyle = new ol.style.Style({
		geometry: function(feature) {
			return CreateMutilLine(feature,resolution,75);
		},
		text: tempText
	});
	return [borderStyle,dashStyle,textStyle];
};

//创建铁路样式 -----普通
var railWayOrdinaryStyle = function(feature, resolution){
	//背景边界
	var borderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#0000FE",
			width : 5,
			lineCap: 'round',
			lineJoin: 'round'
		})
	});
	//虚线
	var dashStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#ffffff",
			width : 3,
			lineCap: 'round',
			lineJoin: 'round',
			lineDash: [20,20]
		})
	});
	var tempText = CreateLineCTextStyle(feature,resolution);
	var textStyle = new ol.style.Style({
		geometry: function(feature) {
			return CreateMutilLine(feature,resolution,75);
		},
		text: tempText
	});
	return [borderStyle,dashStyle,textStyle];
};
//创建铁路样式 -----其它线路
var railWayOtherStyle = function(feature, resolution){
	//背景边界
	var borderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#0000FE",
			width : 5,
			lineCap: 'round',
			lineJoin: 'round'
		})
	});
	//虚线
	var dashStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#ffffff",
			width : 3,
			lineCap: 'round',
			lineJoin: 'round',
			lineDash: [20,20]
		})
	});
	var tempText = CreateLineDTextStyle(feature,resolution);
	var textStyle = new ol.style.Style({
		geometry: function(feature) {
			return CreateMutilLine(feature,resolution,75);
		},
		text: tempText
	});
	return [borderStyle,dashStyle,textStyle];
};


//创建铁路样式 -----其它线路
var TTTStyle = function(feature, resolution){
	//背景边界
	var borderStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : "#020c01",
			width : 5,
			lineCap: 'round',
			lineJoin: 'round'
		})
	});
	//虚线
	var dashStyle = new ol.style.Style({
		stroke : new ol.style.Stroke({
			color : '#189807',
			width : 3,
			lineCap: 'round',
			lineJoin: 'round',
			lineDash: [20,20]
		})
	});
	var tempText = CreateLineDTextStyle(feature,resolution);
	var textStyle = new ol.style.Style({
		geometry: function(feature) {
			return CreateMutilLine(feature,resolution,75);
		},
		text: tempText
	});
	return [borderStyle,dashStyle,textStyle];
};
//=========================================================================================================================
//桥梁样式
var bridgeStyle = function(feature, resolution) {
	if(feature.get("oversize")=="1"){
		var bridgeBgImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_bridgeBg.png',
	        })
	    });
		return bridgeBgImg;
	}else if(feature.get("oversize")=="0"){
		var bridgeSmImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_bridge.png',
	        })
	    });
		return bridgeSmImg;
	}
};
//桥梁范围 点击样式
var bridgeCkStyle = function(feature, resolution){
	var bridgeYardPolygonStyle = new ol.style.Style({
		fill :new ol.style.Fill({
			color : "rgba(255,255,255,0.2)",
			width : 10
		}),
		stroke : new ol.style.Stroke({
			color : "rgba(52,242,27,1)",
			width : 3,
		})
	});
	var bridgeYardLableStyle = new ol.style.Style({
		geometry: function() {
			return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
			},
		text: new ol.style.Text({
			text: feature.get("name"),
			font: '14px Calibri,sans-serif',
			overflow: true,
			fill: new ol.style.Fill({
				color: 'rgba(21,176,230,1)'
			}),
			stroke: new ol.style.Stroke({
				color: 'rgba(255,255,255,0.8)',
				width: 3
			})
		})
	});
	return [bridgeYardPolygonStyle,bridgeYardLableStyle];
};

//桥梁范围 (特大桥)初始样式
var bridgeInitialStyle = function(feature, resolution){
	if(resolution>0.00002){
		return null;
	}
	/*var bridgeYardPolygonStyle = new ol.style.Style({
		fill :new ol.style.Fill({
			color : "rgba(255,255,255,0.2)",
			width : 10
		}),
		stroke : new ol.style.Stroke({
			color : "rgba(52,242,27,1)",
			width : 3,
		})
	});
	return bridgeYardPolygonStyle;*/
	if(feature.get("startType")){
		var bridgeStartImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_beam.png',
	        })
	    });
		return bridgeStartImg;
	}else if(feature.get("endType")){
		var bridgeEndImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_beam.png',
	        })
	    });
		return bridgeEndImg;
	}else{
		var dashStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : '#009191',
				width : 4,
				lineCap: 'round',
				lineJoin: 'round',
				lineDash: [20,20]
			})
		});
		return dashStyle;
	}
};

/*特大桥样式*/
var bridgeBgstyle = function(feature, resolution){
	//起始点==================================
	if(feature.get("startType")){
		var bridgeStartImg= new ol.style.Style({
		        image: new ol.style.Icon({
			    	anchor : [ 0.5, 0.5 ],
					src : '../resources/image/exhibition/map/icon/ex_map_beam.png',
		        })
		    });
			return bridgeStartImg;
	}else if(feature.get("endType")){
		var bridgeEndImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_beam.png',
	        })
	    });
		return bridgeEndImg;
	}else{
		//虚线==========================================
		var borderStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : "#87d8d5",
				width : 7,
				lineCap: 'round',
				lineJoin: 'round'
			})
		});
		var dashStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : '#009191',
				width : 5,
				lineCap: 'round',
				lineJoin: 'round',
				lineDash: [20,20]
			})
		});
		return [borderStyle,dashStyle];
	}
};

//=================================================================================================================================

var culvertStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_culvert.png',
        })
    });
};
//涵洞 点击样式
var culvertCkStyle = function(feature, resolution){
	var culvertYardPolygonStyle = new ol.style.Style({
		fill :new ol.style.Fill({
			color : "rgba(255,255,255,0.2)",
			width : 10
		}),
		stroke : new ol.style.Stroke({
			color : "rgba(0,145,145,1)",
			width : 3,
		})
	});
	var culvertYardLableStyle = new ol.style.Style({
		geometry: function() {
			return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
		},
		text: new ol.style.Text({
			text: feature.get("name"),
			font: '14px Calibri,sans-serif',
			overflow: true,
			fill: new ol.style.Fill({
				color: 'rgba(21,176,230,1)'
			}),
			stroke: new ol.style.Stroke({
				color: 'rgba(255,255,255,0.8)',
				width: 3
			})
		})
	});
	return [culvertYardPolygonStyle,culvertYardLableStyle];
};

//涵洞 初始样式
var culvertYardStyle = function(feature, resolution){
	if(resolution>0.00002){
		return null;
	}
	var culvertYardPolygonStyle = new ol.style.Style({
		fill :new ol.style.Fill({
			color : "rgba(255,255,255,0.2)",
			width : 10
		}),
		stroke : new ol.style.Stroke({
			color : "rgba(52,242,27,1)",
			width : 3,
		})
	});
	var culvertYardLableStyle = new ol.style.Style({
		geometry: function() {
			return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
		},
		text: new ol.style.Text({
			text: feature.get("name"),
			font: '14px Calibri,sans-serif',
			overflow: true,
			fill: new ol.style.Fill({
				color: 'rgba(21,176,230,1)'
			}),
			stroke: new ol.style.Stroke({
				color: 'rgba(255,255,255,0.8)',
				width: 3
			})
		})
	});
	return [culvertYardPolygonStyle,culvertYardLableStyle];
};

//道口 ================================================================================================================
var junctionStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_junction.png',
        })
    });
};

var junctionYardAllStyle = function(feature, resolution){
	var juctionYardPolygonStyle = new ol.style.Style({
		fill :new ol.style.Fill({
			color : "rgba(255,255,255,0.2)",
			width : 10
		}),
		stroke : new ol.style.Stroke({
			color : "rgba(21,176,230,1)",
			width : 3,
		})
	});
	var juctionYardLableStyle = new ol.style.Style({
		geometry: function() {
			return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
			},
		text: new ol.style.Text({
			text: feature.get("name"),
			font: '14px Calibri,sans-serif',
			overflow: true,
			fill: new ol.style.Fill({
				color: 'rgba(21,176,230,1)'
			}),
			stroke: new ol.style.Stroke({
				color: 'rgba(255,255,255,0.8)',
				width: 3
			})
		})
	});
	return [juctionYardPolygonStyle,juctionYardLableStyle];
};

//道口初始样式
var junctionYardStyle = function(feature, resolution){
	if(resolution>0.00002){
		return null;
	}
	var junctionYardPolygonStyle = new ol.style.Style({
		fill :new ol.style.Fill({
			color : "rgba(255,255,255,0.2)",
			width : 10
		}),
		stroke : new ol.style.Stroke({
			color : "rgba(21,176,230,1)",
			width : 3,
		})
	});
	var junctionYardLableStyle = new ol.style.Style({
		geometry: function() {
			return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
		},
		text: new ol.style.Text({
			text: feature.get("name"),
			font: '14px Calibri,sans-serif',
			overflow: true,
			fill: new ol.style.Fill({
				color: 'rgba(21,176,230,1)'
			}),
			stroke: new ol.style.Stroke({
				color: 'rgba(255,255,255,0.8)',
				width: 3
			})
		})
	});
	return [junctionYardPolygonStyle,junctionYardLableStyle];
};
//========================================================================================================
//隧道
var tunnelStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_tunnel.png',
        })
    });
};

//隧道范围  初始样式
var tunnelInitialStyle = function(feature, resolution){
	if(resolution>0.00002){
		return null;
	}
	//起始点==================================
	if(feature.get("startType")){
		var bridgeStartImg= new ol.style.Style({
		        image: new ol.style.Icon({
			    	anchor : [ 0.5, 0.5 ],
					src : '../resources/image/exhibition/map/icon/ex_map_tunnle_beam.png',
		        })
		    });
			return bridgeStartImg;
	}else if(feature.get("endType")){
		var bridgeEndImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_tunnle_beam.png',
	        })
	    });
		return bridgeEndImg;
	}else{
		//虚线==========================================
		var borderStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : "#dd9072",
				width : 7,
				lineCap: 'round',
				lineJoin: 'round'
			})
		});
		var dashStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : '#db5a33',
				width : 5,
				lineCap: 'round',
				lineJoin: 'round',
				lineDash: [20,20]
			})
		});
		return [borderStyle,dashStyle];
	}
};

/*隧道点击样式*/
var tunnelBgstyle = function(feature, resolution){
	//起始点==================================
	if(feature.get("startType")){
		var bridgeStartImg= new ol.style.Style({
		        image: new ol.style.Icon({
			    	anchor : [ 0.5, 0.5 ],
					src : '../resources/image/exhibition/map/icon/ex_map_tunnle_beam.png',
		        })
		    });
			return bridgeStartImg;
	}else if(feature.get("endType")){
		var bridgeEndImg= new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_tunnle_beam.png',
	        })
	    });
		return bridgeEndImg;
	}else{
		//虚线==========================================
		var borderStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : "#dd9072",
				width : 7,
				lineCap: 'round',
				lineJoin: 'round'
			})
		});
		var dashStyle = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : '#db5a33',
				width : 5,
				lineCap: 'round',
				lineJoin: 'round',
				lineDash: [20,20]
			})
		});
		return [borderStyle,dashStyle];
	}
};

//车站
var partStationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/ex_map_PartStation.png',
        })
    });
};
//区
var partStationYardStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/ex_map_station_yard.png',
        })
    });
};
//进站信号机================================
var SignalMachineStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_basestation.png',
        })
    });
};
//站场 点击样式
var parStationYardAllStyle = function(feature, resolution){
	console.log('name========',feature.get("name"))
	if(feature.get('type')== 'yard'){
		var parStationYardPolygonStyle = new ol.style.Style({
			fill :new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 10
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(21,176,230,1)",
				width : 3,
			})
		});
		//字体样式
		var parStationYardLableStyle = new ol.style.Style({
			geometry: function() {
				return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
				},
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			})
		});
		return [parStationYardPolygonStyle,parStationYardLableStyle];
	}else if(feature.get('type')== 'stationYard'){
		var parStationYardPolygonStyle = new ol.style.Style({
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			}),
			fill :new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 10
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(0,145,145,1)",
				width : 3,
			})
		});
		var parStationYardLableStyle = new ol.style.Style({
			geometry: function() {
				return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
			},
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			})
		});
		return [parStationYardPolygonStyle,parStationYardLableStyle];
	}
	
};

//站场 初始样式
var parStationYardStyle = function(feature, resolution){
	if(resolution>0.00002){
		return null;
	}
	if(feature.get('type')== 'yard'){
		var parStationYardPolygonStyle = new ol.style.Style({
			fill :new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 10
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(21,176,230,1)",
				width : 3,
			})
		});
		var parStationYardLableStyle = new ol.style.Style({
			geometry: function() {
				return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
				},
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			})
		});
		return [parStationYardPolygonStyle,parStationYardLableStyle];
	}else if(feature.get('type')== 'stationYard'){
		var parStationYardPolygonStyle = new ol.style.Style({
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			}),
			fill :new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 10
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(0,145,145,1)",
				width : 3,
			})
		});
		var parStationYardLableStyle = new ol.style.Style({
			geometry: function() {
				return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
			},
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			})
		});
		return [parStationYardPolygonStyle,parStationYardLableStyle];
	}
};
var trajectionStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_trajection.png',
        })
    });
};
//行人易穿行区域 
var trajectionPolygonStyleT = function(feature, resolution){
	if(resolution>0.00002){
		return null;
	}
	if(feature.get('type')== 'yard'){
		var parStationYardPolygonStyle = new ol.style.Style({
			fill :new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 10
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(232,65,12,1)",
				width : 3,
			})
		});
		var parStationYardLableStyle = new ol.style.Style({
			geometry: function() {
				return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
				},
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			})
		});
		return [parStationYardPolygonStyle,parStationYardLableStyle];
	}
};

/*var trajectionLableStyle = new ol.style.Style({
	geometry: function(feature) {
		//判定多边形multiPolygon 最西边的多边形
		var geometry = feature.getGeometry();
		if (geometry.getType() == 'MultiPolygon') {
			// Only render label for the widest polygon of a multipolygon
			var polygons = geometry.getPolygons();
			var widest = 0;
			for (var i = 0, ii = polygons.length; i < ii; ++i) {
				var polygon = polygons[i];
				var width = ol.extent.getWidth(polygon.getExtent());
				if (width > widest) {
					widest = width;
					geometry = polygon;
					}
				}
			}
		return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);//ol.extent.getCenter(geometry);
		},
	text: new ol.style.Text({
		font: '12px Calibri,sans-serif',
		overflow: true,
		fill: new ol.style.Fill({
			color: '#e9524b'
		}),
		stroke: new ol.style.Stroke({
			color: 'rgba(255,255,255,0.8)',
			width: 3
		})
	})
});
var trajectionStyle = [trajectionPolygonStyle3,trajectionLableStyle];*/
//=================================================================
//易穿行 点击样式
var trajectionPolygonStyleC = function(feature, resolution){
	console.log('name========',feature.get("name"))
	if(feature.get('type')== 'yard'){
		var trajectionPolygonStyleC1 = new ol.style.Style({
			fill :new ol.style.Fill({
				color : "rgba(255,255,255,0.2)",
				width : 6
			}),
			stroke : new ol.style.Stroke({
				color : "rgba(232,65,12,1)",
				width : 4
			})
		});
		//字体样式
		var trajectionPolygonStyleC2 = new ol.style.Style({
			geometry: function() {
				return new ol.geom.Point([feature.get('lng'), feature.get('lat')]);
				},
			text: new ol.style.Text({
				text: feature.get("name"),
				font: '14px Calibri,sans-serif',
				overflow: true,
				fill: new ol.style.Fill({
					color: 'rgba(0,145,145,1)'
				}),
				stroke: new ol.style.Stroke({
					color: 'rgba(255,255,255,0.8)',
					width: 3
				})
			})
		});
		return [trajectionPolygonStyleC1,trajectionPolygonStyleC2];
	}
};

//重点人
var keyPersonStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/ex_map_keyperson.png',
        })
    });
};
//隐患点
var dangerPonintStyle01 = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/danger_tbdo.png',
        })
    });
};
var dangerPonintStyle02 = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/danger_processing.png',
        })
    });
};
var dangerPonintStyle03 = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/danger_resolved.png',
        })
    });
};
var dangerPonintStyle04 = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/danger_unsolved.png',
        })
    });
};
//联防点位==============================================================
//工作站
var guardStationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
    		anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_main_guardStation.png',
        })
    });
};
//宣传点
var propagandaStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
		    anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_main_propaganda.png',
        })
    });
};
//广播警示柱  在线  故障  离线
//故障
var broadcastFaultStyle = function(feature, resolution) {
    return new ol.style.Style({
    	image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_broadcast.png',
    	})
    });
};
//在线
var broadcastOnLineStyle = function(feature, resolution) {
    return new ol.style.Style({
    	image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_broadcast_on.png',
    	})
    });
};
//离线
var broadcastOffLineStyle = function(feature, resolution) {
    return new ol.style.Style({
    	image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_broadcast_off.png',
    	})
    });
};

//摄像头
var monitorStyle = function(feature, resolution) {
    return new ol.style.Style({
		cursor : 'pointer',
        image: new ol.style.Icon({
    		anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/ex_map_monitor.png',
        })
    });
};
//派出所  
var policeStationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/PolicePtation.png',
        })
    });
};
//警务站
var PolicePtationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/policeStation.png',
        })
    });
};
//专职队员
var fullTimeMemberStyle = function(feature, resolution) {
    return new ol.style.Style({
	        image: new ol.style.Icon({
		    	anchor : [ 0.5, 0.5 ],
				src : '../resources/image/exhibition/map/icon/ex_map_userMember.png',
				/*scale: map.getView().getZoom() / 7*/
	        })
    });
};
//在线干部
var appStyle = function(feature, resolution) {
    return new ol.style.Style({
    		cursor : 'pointer',
    		image: new ol.style.Icon({
        		anchor : [ 0.5, 0.5 ],
        		src : '../resources/image/exhibition/map/icon/ex_map_userCrad.png',
        })
    });
};
//周边场所=================================================================================
//住宅
var residenceStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/residence.png',
        })
    });
};
//单位
var companyStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/company.png',
        })
    });
};
//市场
var marketStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/market.png',
        })
    });
};
//学校
var schoolStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_school.png',
        })
    });
};
//工矿
var miningStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/mining.png',
        })
    });
};
//工地
var constructionSiteStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/constructionSite.png',
        })
    });
};
//休闲
var leisureTimeStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/leisureTime.png',
        })
    });
};
//商厦
var commercialStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/commercial.png',
        })
    });
};
//吃住
var roomAndBoardStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/roomAndBoard.png',
        })
    });
};
//养殖
var breedStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/breed.png',
        })
    });
};
//种植
var plantStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/plant.png',
        })
    });
};
//加油站
var gasStationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/gasStation.png',
        })
    });
};
//河湖
var riversAndLakesStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/hehu.png',
        })
    });
};
var otherStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/other.png',
        })
    });
};


/*工作站(测试)*/
var ceshiGZZyjcStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
	    	src : '../resources/image/exhibition/map/icon/guardStation_yjc.png',
        })
    });
};
var ceshiGZZyxzStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
	    	src : '../resources/image/exhibition/map/icon/guardStation_yxz.png',
        })
    });
};
var ceshiGZZzlStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
	    	src : '../resources/image/exhibition/map/icon/guardStation_zl.png',
        })
    });
};
/*监控(测试)*/
var ceshiJKStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
	    	src : '../resources/image/exhibition/map/icon/ex_map_monitor.png',
        })
    });
};

//线路定位
var railLocationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/legend/kmlocation.png',
        })
    });
};

var railLocationStyleS = function(feature, resolution) {
	if(resolution>0.00002){
		return null;
	}
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/legend/kmlocation.png',
        })
    });
};
//线路定位GPS
var railGPSLocationStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/legend/kmlocation.png',
        })
    });
};
//巡防轨迹
var patrolTrajectoryStyles = {
  'route' : new ol.style.Style({
    stroke : new ol.style.Stroke({
      width : 6,
      color : [ 237, 212, 0, 0.8 ]
    })
  }),
  'icon' : new ol.style.Style({
    image : new ol.style.Icon({
      anchor : [ 0.5, 1 ],
      src : '../resources/image/exhibition/map/icon/patrolTrajectory.png'
    })
  }),
  'geoMarker' : new ol.style.Style({
    image : new ol.style.Circle({
      radius : 7,
      snapToPixel : false,
      fill : new ol.style.Fill({
        color : 'black'
      }),
      stroke : new ol.style.Stroke({
        color : 'white',
        width : 2
      })
    })
  })
};



