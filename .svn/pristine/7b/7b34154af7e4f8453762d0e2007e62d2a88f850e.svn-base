(function($) {
	$(window).ready(function() {
		$(".administrativedivision_Pw").mCustomScrollbar({
			mouseWheelPixels : 200
		});
		$(".ex_map_content_Railwayline_content").mCustomScrollbar({
			mouseWheelPixels : 200
		});
		$(".ex_map_content_Keyfacilities_content").mCustomScrollbar({
			mouseWheelPixels : 200
		});
		$("#AdministrativedivisionAuto").mCustomScrollbar({
			mouseWheelPixels : 200
		});
		
		
		
		
		
		//重点设施  勾选框
//		var aLi = document.querySelectorAll('.ex_map_content_Keyfacilities_content ul li');
//		for (var i = 0; i < aLi.length; i++) {
//			aLi[i].ifCheck = false;
//			aLi[i].nowClass = aLi[i].className;
//			aLi[i].onclick = function() {
//				if (this.ifCheck) {
//					this.className = this.nowClass
//				} else {
//					this.className += ' on'
//				}
//				this.ifCheck = !this.ifCheck;
//			};
//		}

		//菜单按钮
		/*$(".ex_main_ce_btn").click(function(e) {
			if ($(".ex_main_ce_content").hasClass("show")) {
				$(".ex_main_ce_content").hide().removeClass("show");
				$(".ex_main_ceBtn_hover").removeClass().addClass('ex_main_ce_btn');
			} else {
				$(".ex_main_ce_content").show().addClass("show");
				$(".ex_main_ce_btn").removeClass().addClass('ex_main_ceBtn_hover');
			}
		});*/
		// 右侧筛选框 
		$('.ex_main_content_screenbtn').click(function() {
			$('.ex_main_content_screen').css({
				'right' : '-708px',
				'display' : 'block'
			}).animate({
				"right" : "0px"
			}, 500).show();
			$('.ex_main_content_screenbtn').css('display', 'none');
			//数据检索
			$('.ex_main_content_search').css('display', 'none');
			//巡防管理 -巡防队伍 
			$('.ex_main_map_patrolTeamPopWin').css('display', 'none');
			//巡防管理 -巡防人员
			$('.ex_main_map_patrolTeammemberPopWin').css('display', 'none');
			//巡防管理-巡防轨迹
			$('.ex_main_map_patrolTrajectoryPopWin').css('display', 'none');
			//铁路定位
			$('.ex_main_map_railPopWin').css('display', 'none');
			$('#map-popup-closer').click();
			KmLocationLayer.getSource().clear();
		})
		$('.ex_main_content_screenclosebtn').click(function() {
			$('.ex_main_content_screen').css({
				'right' : '0px',
				'display' : 'none'
			}).animate({
				"right" : "-708px"
			}, 500).show();
			$('.ex_main_content_screenbtn').css('display', 'block').animate({
				"right" : "2px"
			}, 500).show();
			$('#map-popup-closer').click();
			KmLocationLayer.getSource().clear();
		})
		//数据检索 
		$('.ex_main_map_dataretrieval').click(function() {
			$('.ex_main_content_search').css('display', 'block');
			$('.ex_main_content_screen').css({
				'display' : 'none'
			});
			$('.ex_main_content_screenbtn').css('display', 'block');
			//巡防管理 -巡防队伍 Patrolteam
			$('.ex_main_map_patrolTeamPopWin').css('display', 'none');
			//巡防管理 -巡防人员
			$('.ex_main_map_patrolTeammemberPopWin').css('display', 'none');
			//巡防管理-巡防轨迹
			$('.ex_main_map_patrolTrajectoryPopWin').css('display', 'none');
			//铁路定位
			$('.ex_main_map_railPopWin').css('display', 'none');
			$('#map-popup-closer').click();
		});
		//数据检索关闭按钮 
		$('.ex_main_content_closesearch').click(function() {
			$('.ex_main_content_search').css('display', 'none');
			$('#keyword').val('');
			$('#searchContent').html('');
			$('#map-popup-closer').click();
		});
		//巡防管理 -巡防队伍 Patrolteam 关闭按钮 
		$('.ex_main_map_close_patrolTeamPopWin').click(function() {
			$('.ex_main_map_patrolTeamPopWin').css('display', 'none');
			//关闭窗口 信息置空
			$('#patrolTeam').html("");
			$('#patrolTeamPage').html("");
			$('#map-popup-closer').click();
		});
		// 巡防管理 -巡防队员 Patrolteammember 关闭按钮
		$('.ex_main_map_close_patrolTeammemberPopWin').click(function() {
			//巡防队伍 
			$('.ex_main_map_patrolTeamPopWin').css('display', 'block');
			//巡防队员 
			$('.ex_main_map_patrolTeammemberPopWin').css('display', 'none');
			//关闭窗口 信息置空
			$('#patrolTeammember').html("");
			$('#patrolTeammemberPage').html("");
			$('#map-popup-closer').click();
		});
		//巡防管理 -巡防轨迹 patrolTrajectory 关闭按钮
		$('.ex_main_map_close_patrolTrajectoryPopWin').click(function() {
			//巡防队伍 
			$('.ex_main_map_patrolTrajectoryPopWin').css('display', 'none');
			//巡防队员
			$('.ex_main_map_patrolTeammemberPopWin').css('display', 'block');
			
			//关闭窗口 信息置空
			$('#patrolTrajectory').html("");
			$('#patrolTrajectoryPage').html("");
			$('#map-popup-closer').click();

		});
	});
})(jQuery);
//巡防队伍
function patrolTeam(page) {
	if (!page)
		page = 1;
	var pageRows = 12;
	//巡防队伍弹窗
	$('.ex_main_map_patrolTeamPopWin').css('display', 'block');
	//巡防队员弹窗关闭
	$('.ex_main_map_patrolTeammemberPopWin').css('display', 'none');
	//巡防轨迹弹窗关闭
	$('.ex_main_map_patrolTrajectoryPopWin').css('display', 'none');
	//数据检索 
	$('.ex_main_content_search').css('display', 'none');
	//筛选框 
	$('.ex_main_content_screen').css('display', 'none');
	$('.ex_main_content_screenbtn').css('display', 'block');
	//铁路定位
	$('.ex_main_map_railPopWin').css('display', 'none');
	
	$('#map-popup-closer').click();

	$.ajax({
		url : '/sdrpoms/baseInfo/PatrolManagementTaskQueryList',
		type : 'post',
		dataType : 'json',
		data : {
			page : page,
			rows : pageRows,
			orgId: orgId
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:[" + XMLHttpRequest.status + "]-");
			console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
			console.log("异常情况:[" + textStatus + "]");
		},
		success : function(data) {
//			console.log('1111', data);
			var tempHtml = "";
			var rows = data.rows;
			if (rows && rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					tempHtml += "<li onclick='Patrolteammember(&quot;" + rows[i].id + "&quot;)'>" + rows[i].name + "</li>"
				}
				$("#patrolTeam").html(tempHtml);
			}
			if (!data.total || data.total <= pageRows) {
				return;
			}
			$("#patrolTeamPage").paging({
				pageNo : page,
				totalPage : Math.ceil(data.total / pageRows),
				totalSize : data.total,
				callback : function(num) {
					patrolTeam(num);
				}
			})
		}
	});
}

/*巡防队伍 查询*/
$('.search_box').on("keyup", function() {
	var pageRows = 12;
	var page = 1;
	var searchName = $('.search_box').val();
	$.ajax({
		url : '/sdrpoms/baseInfo/PatrolManagementTaskQueryList',
		type : 'post',
		dataType : 'json',
		data : {
			name : searchName,
			page : page,
			rows : pageRows,
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:[" + XMLHttpRequest.status + "]-");
			console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
			console.log("异常情况:[" + textStatus + "]");
		},
		success : function(data) {
			var tempHtml = "";
			var rows = data.rows;
			if (rows && rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					tempHtml += "<li onclick='Patrolteammember(&quot;" + rows[i].id + "&quot;)'>" + rows[i].name + "</li>"
				}
				$("#patrolTeam").html(tempHtml);
			}
			$('#patrolTeamPage').html('');
			if (!data.total || data.total <= pageRows) {
				return;
			}
			$("#patrolTeamPage").paging({
				pageNo : page,
				totalPage : Math.ceil(data.total / pageRows),
				totalSize : data.total,
				callback : function(num) {
					patrolTeam(num);
				}
			})
		}
	});
});
	/*function searchBox(page){
		if (!page)
			page = 1;
	
		
		
		
		
	}*/
	
	
	


//巡防队员 
function Patrolteammember(id, page) {
	if (!page)
		page = 1; //显示第几页
	var pageRows = 12; //每页多少条记录
	$('.ex_main_map_patrolTeamPopWin').css('display', 'none');
	//巡防队员 
	$('.ex_main_map_patrolTeammemberPopWin').css('display', 'block');
	
	$('#map-popup-closer').click();
	//巡防队员
	$.ajax({
		url : '/sdrpoms/baseInfo/PartolTeamUserRelationQueryList?teamInfoId=' + id,
		type : 'post',
		dataType : 'json',
		data : {
			page : page,
			rows : pageRows
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:[" + XMLHttpRequest.status + "]-");
			console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
			console.log("异常情况:[" + textStatus + "]");
		},
		success : function(data) {
			console.log('巡防队员===========================', data)
			var tempHtml = "";
			var rows = data.rows;
			if (rows && rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					tempHtml += "<li onclick='patrolTrajectory(&quot;" + rows[i].userId + "&quot;)'>" + rows[i].userName + "</li>"
				}
				$("#patrolTeammember").html(tempHtml);
			}

			if (!data.total || data.total <= pageRows) {
				return;
			}
			$("#patrolTeammemberPage").paging({
				pageNo : page,
				totalPage : Math.ceil(data.total / pageRows),
				totalSize : data.total,
				callback : function(num) {
					Patrolteammember(id, num);
				}
			})
		}
	});
}
//巡防轨迹 
function patrolTrajectory(id, page) {
	if (!page)
		page = 1; //显示第几页
	var pageRows = 10; //每页多少条记录
	$('.ex_main_map_patrolTrajectoryPopWin').css('display', 'block');
	//巡防队伍 
	$('.ex_main_map_patrolTeammemberPopWin').css('display', 'none');
	
	$('#map-popup-closer').click();
	$.ajax({
		url : '/sdrpoms/baseInfo/PatrolTrackInfoQueryList?userId=' + id,
		type : 'post',
		dataType : 'json',
		data : {
			page : page,
			rows : pageRows
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("响应状态:[" + XMLHttpRequest.status + "]-");
			console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
			console.log("异常情况:[" + textStatus + "]");
		},
		success : function(data) {
			console.log('巡防轨迹==============', data);
			var tempHtml = "";
			var rows = data.rows;
			if (rows && rows.length > 0) {
				for (var i = 0; i < rows.length; i++) {
					tempHtml += "<li onclick='showPatrolTrajectory()' data-coords=" + rows[i].track + ">" +
						"<span> " + "开始时间:&nbsp;&nbsp;" + rows[i].startTime + "</span>" +
						"<span >" + "结束时间:&nbsp;&nbsp;" + rows[i].endTime + "</span>" +
						"</li>"
				}
				$("#patrolTrajectory").html(tempHtml);
			}
			if (!data.total || data.total <= pageRows) {
				return;
			}
			$("#patrolTrajectoryPage").paging({
				pageNo : page,
				totalPage : Math.ceil(data.total / pageRows),
				totalSize : data.total,
				callback : function(num) {
					patrolTrajectory(id, num);
				}
			})
		}
	});
}

var $openbtn= $('.ex_main_ce_btn');
var $opencontent= $('.ex_main_ce_content');
var $openul = $('.ex_main_ce_content ul')
var $close = $('.closeDiv')
$openbtn.mouseover(function(){
	$opencontent.animate({"width":"1600px"},1000);
	$('.openopacity').css({"background":"#012035","opacity":"0.6"})
 	$('.ex_map_popWin').css('display','block');
	$('.ex_main_content_screen').css({'display' : 'none'})
	$('.ex_main_content_screenbtn').css('display', 'block');
})
	 $('.ex_map_popWin').mouseover(function(){
		$opencontent.animate({"width":"104px"},1000);
	$('.ex_map_popWin').css('display','none');
	$('.openopacity').css({"background":"none"})
}) 
/*线路定位*/
$('.ex_main_content_closerail').click(function() {
	$('.ex_main_map_railPopWin').css('display', 'none');
	KmLocationLayer.getSource().clear();
	$('#map-popup-closer').click();
	/* map.getView().setZoom(9.8); */
});

$('.ex_main_map_rail').click(function() {
	$('.ex_main_map_railPopWin').css('display', 'block');
	//数据检索 
	$('.ex_main_content_search').css('display', 'none');
	$('.ex_main_content_screenbtn').css('display', 'block');
	
	//筛选框 
	$('.ex_main_content_screen').css('display', 'none');
	//巡防队伍弹窗
	$('.ex_main_map_patrolTeamPopWin').css('display', 'none');
	$('.ex_main_map_patrolTrajectoryPopWin').css('display', 'none');
	$('.ex_main_map_patrolTeammemberPopWin').css('display', 'none');
	
});

/*$('.ex_map_echartsBtn').click(function(){
	$('.ex_map_echartsContent').css('display','block');
})*/

$('.close_map_ecartsConent').click(function(){
	$('.ex_map_echartsContent').css('display','none');
})

/* 行政区域  select*/
var $aa = $('.ex_map_content_Administrativedivision_quan');
var $li = $('.ex_map_content_Administrativedivision_content ul li');
var $ul = $('.ex_map_content_Administrativedivision_content ul')
$aa.click(function(){
	$ul.css('display', 'block');
})

 $(".ex_map_content_Administrativedivision_content ul li").each(function(){
	$(this).click(function(){
	   $aa.html($(this).text());
	   $ul.css('display', 'none');
})
}) 
