	var lng=$("input[name='lng']").val();
    var lat=$("input[name='lat']").val();
    if(lng){
        if(lat){
	        var map = new AMap.Map('container',{
	                resizeEnable: true,
	                zoom: 17,
	                center: [lng,lat]
	        });
	        AMap.plugin(['AMap.ToolBar','AMap.Scale','AMap.OverView'],function(){
	            map.addControl(new AMap.ToolBar());
	            map.addControl(new AMap.Scale());
	            // map.addControl(new AMap.OverView({isOpen:true}));
	        })
	        AMap.plugin('AMap.Geocoder',function(){
	            var geocoder = new AMap.Geocoder({
	                city: "010"//城市，默认：“全国”
	            });
	            var marker = new AMap.Marker({
	                map:map,
	                bubble:true
	            })
	            map.on('click',function(e){
	                marker.setPosition(e.lnglat);
	                geocoder.getAddress(e.lnglat,function(status,result){
	                    
	                  if(status=='complete'){
	
	                   var alue = result.regeocode.formattedAddress
	                     geocoder.getLocation(alue, function(status, result) {
	                        if (status === 'complete' && result.info === 'OK') {
	                            //TODO:获得了有效经纬度，可以做一些展示工作
	                            //比如在获得的经纬度上打上一个Marker
	                             $("#lng").val(result.geocodes[0].location.lng)
	                             $("#lat").val(result.geocodes[0].location.lng)
	                        }else{
	                            //获取经纬度失败
	                        	 $.alert({
	            					 label: '获取经纬度失败'
	            				 });
	                        }
	                    }); 
	                  }
	                })
	            })
	        });
        }
    }