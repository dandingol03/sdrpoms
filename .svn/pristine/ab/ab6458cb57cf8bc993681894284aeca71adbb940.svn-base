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

//道口 
var junctionStyle = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
	    	anchor : [ 0.5, 0.5 ],
			src : '../resources/image/exhibition/map/icon/ex_map_junction.png',
        })
    });
}

// 巡防轨迹style
var patrolTrackStyle = function(feature, resolution) {
	//console.log('resolution ======= == == == =', resolution);
    var styles = [
    	new ol.style.Style({
          stroke: new ol.style.Stroke({
              color: "blue",
              width: 2,
              lineDash: [20, 20],
              lineCap: 'square',
          }),
          text: new ol.style.Text({
              font: '12px "Microsoft YaHei",sans-serif',
              text: feature.get('name'),
              placement: 'line',
             /* scale: map.getView().getZoom() / 10,*/
              fill: new ol.style.Fill({
                  color: "rgba(211,4,89,1)",
                  width: 2
              }),
              stroke: new ol.style.Stroke({
                  color: "rgba(255,255,255,1)",
                  width: 2
              })
          })
      })
    ];

    return styles;
};

//隐患点
var dangerPonintStyle01 = function(feature, resolution) {
    return new ol.style.Style({
        image: new ol.style.Icon({
        	anchor : [ 0.5, 0.5 ],
    		src : '../resources/image/exhibition/map/icon/danger_tbdo.png',
        })
    });
}






