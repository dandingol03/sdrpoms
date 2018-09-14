<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
	<head>
		<title>首都铁路护路联防综合业务信息管理系统</title>
	    <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="JHVersion1.1-layout"> 
		<!--  360浏览器专用 -->
		<meta name="renderer" content="ie-comp"/>
		<%@ include file="tagexlib.jsp"%>
		<link rel="shortcut icon" href="${ctx}/resources/image/project.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/map/map.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/mapcontent.css">
		<link rel="stylesheet" href="${ctx}/resources/openlayers4.5.0/ol.css" type="text/css">
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/jquery.mCustomScrollbar.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/animite/mapanimate.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/paging.css" />
		<script type="text/javascript" src="${ctx}/resources/jquery/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/jquery-ui-1.10.4.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/jquery.mCustomScrollbar.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/jquery.mousewheel.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/paging.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/mapContent/exFunc.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/mapContent/echarts.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/mapContent/search.js"></script>
		<script type="text/javascript">
			// 选中的 org
			var orgId = "${orgId}";
			var center = [+'${orgZoomAndCenter.X}', +'${orgZoomAndCenter.Y}'];
			var zoom = +'${orgZoomAndCenter.Zoom}';
			/**
			 * 选中的铁路线
			 * 全选或者全不选，size = 0
			 */
			var selectedRailIds = new Set();
			 $(function() {
				var $li= $('.ex_main_map_patrolTeammemberPopWinContent ul li');
				var $this = $(this);
				$li.click(function() {
					$li.removeClass();
					$this.addClass('select');
				});
			}); 
		</script>
		<style>
			.ol-popup{width:max-content;min-width:auto;}
			.ol-popup-closer{display:none;}
			.mom{
				position: fixed;
			    left: 185px;
			    right: 0;
			    top: 140px;
			    width: 347px;
			    overflow: hidden;
			    background-size: 100%;
			    background: #fff;
			}
			.pwAll_firstUl_mom{padding:25px;}
			.pwAll_firstUl_mom li{color:#000;margin:5px 0;}
			.mom_content{width:100%;height:120px;border-bottom:1px solid #efefef}
			.ol-popup{opacity:1 !important;}
			.mark{width:22px;height:22px;background:url(${ctx}/resources/image/exhibition/map/svg/danger.gif) no-repeat;background-size:100% 100%;}
			.broadcast{width:22px;height:22px;background:url(${ctx}/resources/image/exhibition/map/svg/broadcast.gif) no-repeat;background-size:100% 100%;}
			#aa iframe {
				display:block !important;
			}
		</style>
	</head>
	<body>
		<!-- 地图内容 -->
		<!-- id="bjRailMap" -->
		<div class="ex_main_content" id="bjRailMap">
		
		<div id="aa" style="position: absolute;
    top: 146px;
    left: 200px;
    z-index: 99999;
    background: red;"></div>
			<!-- <div class="marker" id = "goushi"></div> -->
		<!-- <div class="mapMask"></div>  -->
			<!-- 左侧echarts弹窗 start -->
				<div class="ex_map_dangerBtn" >
					<div  class="ex_map_dangerBtnContent"></div>
				</div>
				<div class="ex_map_echartsBtn" ></div>
				<div class="ex_map_echartsContent">
					<div class="ex_map_echartsContent_first" id="echartsContentFirst"></div>
					<div class="ex_map_echartsContent_second" id="echartsContentSecond" ></div>
					<div class="ex_map_echartsContent_third" id="echartsContentThird"></div>
					<div class="close_map_ecartsConent"></div>
				</div> 
			<!-- 左侧echarts弹窗 end -->
			<div class="ex_map_popWin" ></div>
			<div class="ex_main_leftheader">
				<div class="ex_main_leftheader_quan"></div>
			</div>
			<!-- 侧边栏 -->
			<div class="ex_map_ce_conent">
				<div class="ex_main_ce_btn"></div>
				<div class="openopacity"></div>
				<div class="ex_main_ce_content">
					<ul>
						<li data-org-name="基础信息" data-menu-id="0001" onclick="menubar()"class="Basicdata"></li>
						<li data-org-name="办公OA" data-menu-id="0005"  onclick="menubar()" class="officeOa iconimg"></li>
						<li data-org-name="巡防管理" data-menu-id="0003" onclick="menubar()" class="Patrolmanagement"></li>
						<li data-org-name="隐患上报与处置" data-menu-id="0004" onclick="menubar()" class="Hiddendanger_disposal "></li>
						<li data-org-name="视频监控" data-menu-id="0002" onclick="menubar()" class="Videosurveillance "></li>
						<li data-org-name="教育培训" data-menu-id="0006" onclick="menubar()" class="training iconimg"></li>
						<li data-org-name="研判分析" data-menu-id="0007" onclick="menubar()" class="Judgment_analysis "></li>
						<li data-org-name="视频会议" data-menu-id="0008" onclick="menubar()" class="Videoconferencng "></li>
						<li data-org-name="运维管理" data-menu-id="0009" onclick="menubar()" class="MochaITOM "></li>
					</ul>
				</div>
			</div>
			<!-- 弹出层 -->
			<div id="map-popup" class="ol-popup">
			    	<a href="#" id="map-popup-closer" class="ol-popup-closer"></a>
			    	<div id="map-popup-content">dashabi</div>
		    </div>
	     	<div class="ex_main_content_header"> 
		   	 	 <div class="ex_main_content_header_right" >
	   	 			<ul class="ex_main_content_header_rightul">
						<li >
							<div class="ex_main_map_dataretrieval">数据检索</div>
							<ul></ul>
						</li>
						<li style="margin:0 5px;" >
							<div onclick="patrolTeam()">巡防管理</div>
						</li>
						<li style="margin:0 5px;">
							<div class="ex_main_map_rail">线路定位</div>
						</li>
					</ul>
		   	 	</div> 
			    <!-- 退出键-->
			    <div class="ex_main_content_header_exit"  onclick="exReturn()"></div>
	    		</div>
		    <!-- 筛选框展开按钮 -->
	     	<div class="ex_main_content_screenbtn"></div>
		    <!-- 筛选框 -->
		    <div class="ex_main_content_screen">
			    	<div class="ex_map_contentContent">
			    		<div class="ex_map_content_Administrativedivision">
			    			<!-- <div class="ex_map_content_Administrativedivision_header">行政区域</div> -->
			    			<div class="ex_map_content_Administrativedivision_content">
			    				<input id="exAdministrativedivisionInput"/>
			    				<ul id="AdministrativedivisionAuto">
			    					<c:forEach var="org" items="${orgs }">
									<li class="on">
										<c:choose>
											<c:when test="${org.orgId == 'org0000'}">
												<p class="all-bj" data-org-id="${ org.orgId }" onclick="adminDivision()">北京市</p>
											</c:when>
											<c:otherwise>
												<p  data-org-id="${ org.orgId }" onclick="adminDivision()">${ org.name }</p>
											</c:otherwise>
										</c:choose>	
									</li>
			    					</c:forEach>
			    				</ul>
			    			</div>
			    			
			    			<!-- 区域详细内容插入 -->
			    			<div class="administrativedivision_Pw">
			    				<ul class="administrativedivision_PwContent"> </ul>
			    			</div>
			    		</div>
			    		<div class="ex_map_content_Railwayline">
			    			<!-- <div class="ex_map_content_Railwayline_header">铁路线路</div> -->
		    				<input id="showRailInput" class="showAllRailInput"/> <div id="showAllRail" class="showAllRail"data-checked-num="0"  onclick="toggleAllRail();"></div>
		    				<ul id="showRailList" >
		    					<li ><p onclick="showRailSelect('00')">全部线路</p> </li>
								<li ><p onclick="showRailSelect('01')">高速铁路</p> </li>
								<li ><p onclick="showRailSelect('03')">重载专线</p> </li>
								<li ><p onclick="showRailSelect('02')">普速干线</p> </li>
								<li ><p onclick="showRailSelect('05')">其他线路</p> </li>
		    				</ul>
			    			<input onkeyup="searchCity(this.value)" class="searchRail" id ="searchRailName" type="text" >
			    			<div class="ex_map_content_Railwayline_content">
			    				<ul id="baseInfoRail"></ul>
			    			</div>
			    		</div>
			    		<div class="ex_map_content_Keyfacilities">
			    			<!-- <div class="ex_map_content_Keyfacilities_header">重点设施</div> -->
			    			<div class="ex_map_content_Keyfacilities_content">
			    				<div  class="ex_map_content_Keyfacilities_content_content">
			    				</div>
			    			</div>
			    		</div>
			    	</div>
			    	<div class="ex_main_content_screenclosebtn"></div>
			</div>
			<!-- 搜索框 -->
			<div class="ex_main_content_search">
				<div class="ex_main_content_closesearch"></div>
				<div class="ex_main_content_searchinput">
					<input type="text" id="keyword">
					<div class="ex_map_searchbtn" onclick="searchData();"></div>
				</div>
				<div class="ex_main_content_searchcontent">
					<ul id="searchContent">
					
					</ul>
				</div>
				<div id="searchContentPage" class="ex_main_content_searchcontent_pagebean">
				</div>	
			</div>
			<!-- 巡防队伍 -->
			<div class="ex_main_map_patrolTeamPopWin">
				<div class="ex_main_map_close_patrolTeamPopWin"></div>
				<div class="ex_main_map_patrolTeamPopWinContent_header">巡防队伍</div>
				<input type="text" class="search_box" placeholder="请输入您要查询的巡防队伍">
				<div class="ex_main_map_patrolTeamPopWinContent" >
					<ul id="patrolTeam"></ul>
				</div>
				<div id="patrolTeamPage" class="paging"></div>
			</div>
			<!-- 巡防队员 -->
			<div class="ex_main_map_patrolTeammemberPopWin">
				<div class="ex_main_map_close_patrolTeammemberPopWin"></div>
				<div class="ex_main_map_patrolTeammemberPopWinContent_header">巡防人员</div>
				<div class="ex_main_map_patrolTeammemberPopWinContent" >
					<ul id="patrolTeammember"></ul>
				</div>
				<div id="patrolTeammemberPage" class="paging"></div>
			</div>
			<!-- 巡防队员——————轨迹 -->
			<div class="ex_main_map_patrolTrajectoryPopWin">
				<div id="patrolTrajectoryClose" class="ex_main_map_close_patrolTrajectoryPopWin" ></div>
				<div class="ex_main_map_patrolTrajectoryPopWinContent_header">巡防轨迹</div>
				<div class="ex_main_map_patrolTrajectoryPopWinContent" >
					<ul id="patrolTrajectory"></ul>
				</div>
				<div id="patrolTrajectoryPage" class="paging"></div>
			</div>
			<!-- 线路定位 -->
			<div class="ex_main_map_railPopWin">
				<div class="ex_main_map_railPopWinContent">
					<ul>
						<li><p>铁路线</p>
							<select id="locationRail"></select>	
						</li>
						<li><input type="number" id="locationRailK">&nbsp;&nbsp;k&nbsp;&nbsp;<input id="locationRailM" type="number">&nbsp;&nbsp;m</li>
						<li style="margin:25px 0;"><button onclick="locationRailKm()">定位</button></li>
					</ul>
				</div>
				<div class="ex_main_content_closerail"></div>
			</div>
			<div class="management">该功能正在开发中....<div class="close_management" onclick="closeManagementPw()"></div></div>
		</div>
		<!-- 处置PW -->
		<div id="mapContentInfo" style="display:none"></div>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/mapContent/mapRightChoose.js"></script>
		<script type="text/javascript" src="${ctx}/resources/openlayers4.5.0/ol.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/style.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/function.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/features.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/map.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/area.js"></script>
		<!-- km 到经纬度的转换 -->
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/km2lnglat.js"></script>
		<!-- 展示巡防轨迹 -->
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/patrolTrajectory.js"></script>
		<script type="text/javascript" >
			//模拟option铁路线路点击事件  =-===================================
			    var showRailInput=document.getElementById('showRailInput');  
			    var ulList=document.getElementById('showRailList');  
			    var textList=$('#showRailList li:first-child p').text();
			    document.getElementById("showRailInput").value=textList; 
			    var showRaillis=$('#showRailList li');  
			    showRailInput.onfocus=function(){  
			    	ulList.style.display='block';  
			    }  ;
			    showRailInput.onblur=function(){  
			        setTimeout(function(){  
			        	ulList.style.display='none';  
			        },200)  
			    } ;
			    for(var i=0; i<showRaillis.length;i++){  
			    	showRaillis[i].onclick=function(){  
			        	showRailInput.value=this.innerText;  
			        }  
			    }   
			//模拟option铁路线路点击事件  end=-===================================
			//模拟option点击事件   行政区域
				var ipt=document.getElementById('exAdministrativedivisionInput');  
			    var ul=document.getElementById('AdministrativedivisionAuto');  
			    var lis=ul.children;  
			    ipt.onfocus=function(){  
			        ul.style.display='block';  
			    }  ;
			    ipt.onblur=function(){  
			        setTimeout(function(){  
			            ul.style.display='none';  
			        },200)  
			    } ; 
			    
		</script>
		<script type="text/javascript">  
			var adminDivisionOrgId = $('#AdministrativedivisionAuto li.on p').data('orgId');
			var overlays=[];
			var overlaysDanger=[];
			$(document).ready(function(){
				var $li = $('#AdministrativedivisionAuto li:first-child p').html();
				$('#exAdministrativedivisionInput').val($li)
				//判定机构权限与显示区域
				setOrgMap(orgId);
				// 根据用户机构加载铁路和其他数据加载北京市铁路数据 加载指定orgId的铁路线路
				loadRailData(orgId, function() {
					$('#showAllRail').data('checked', false);
	                   showAllRail();
				});
				/* 页面初始化  区域统计*/
				/* adminDivisionOrgId=orgId;  */
				$.ajax({
		    		url:"${ctx}/exhibition/map/getOrgInfosel?id="+orgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: true,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
	    				 var tempHtml="";
	    				 tempHtml+= "<li>共计:<span>"+data.railCount+"</span>条</li>"+
						 			"<li>总里程:</li>"+
									"<li><span>"+data.orgRailLen+"</span></li>"+
						 			"<li>高速铁路:<span>"+data.Pub01Data+"</span>条</li>"+
						 			"<li>重载专线:<span>"+data.Pub03Data+"</span>条</li>"+
						 			"<li>普通干线:<span>"+data.Pub02Data+"</span>条</li>"+
						 			"<li>其它线路:<span>"+data.Pub05Data+"</span>条</li>"+
						 			"<li class='selected'>涉路行政区划</li>"+
									"<li>区:<span>"+data.pub_org+"</span>个</li>"+
									"<li>街乡镇:<span>"+data.jxz+"</span>个</li>"
									/* "<li>社区村:<span>"+2222+"</span>个</li>" */
		    			$(".administrativedivision_PwContent").html(tempHtml); 
		    		}
	    		});
				/* 页面初始化  重点场所 */
				$.ajax({
		    		url:"${ctx}/exhibition/map/getOrgInfosel?id="+orgId,
		    		type:'POST',
		    		dataType:'json',
		    		error: ajaxErrorCb,
		    		success:function(data){
		    			/* 重点部位 总数量 */
		    			var keyPartsSize = 
		    				data.base_info_keyperson + 
		    				data.base_info_station_yard + 
		    				data.bridgeCulvertCount + 
		    				data.base_info_part_junction + 
		    				data.base_info_part_tunnel+
		    				data.base_info_part_station + 
		    				data.base_info_hidden_trajection;
		    			/* 联防点位总数量 */
		    			var jointDefensepartSize = 
		    				data.base_info_guard_station +
		    				data.base_info_propaganda +
		    				data.base_info_broadcast +
		    				data.video_monitor_info
		    			
		    			/* 隐患点位总数量 */
		    			var dangerNumberAll = 
		    				data.danger01Type +
		    				data.danger02Type +
		    				data.danger03Type +
		    				data.danger04Type+
		    				data.danger05Type+
		    				data.danger06Type;
		    			
		    			/* 周边场所点位总数量 */
		    			var placeAllNumber = 
		    				data.peripheral01Type+
		    				data.peripheral02Type+
		    				data.peripheral03Type+
		    				data.peripheral04Type+
		    				data.peripheral05Type+
		    				data.peripheral06Type+
		    				data.peripheral07Type+
		    				data.peripheral08Type+
		    				data.peripheral09Type+
		    				data.peripheral10Type+
		    				data.peripheral11Type+
		    				data.peripheral12Type+
		    				data.peripheral13Type;
		    			var peripheral01Type = (data.peripheral01Type == '0' ? '':data.peripheral01Type);
		    			var peripheral02Type = (data.peripheral02Type == '0' ? '':data.peripheral02Type);
		    			var peripheral03Type = (data.peripheral03Type == '0' ? '':data.peripheral03Type);
		    			var peripheral04Type = (data.peripheral04Type == '0' ? '':data.peripheral04Type);
		    			var peripheral05Type = (data.peripheral05Type == '0' ? '':data.peripheral05Type);
		    			var peripheral06Type = (data.peripheral06Type == '0' ? '':data.peripheral06Type);
		    			var peripheral07Type = (data.peripheral07Type == '0' ? '':data.peripheral07Type);
		    			var peripheral08Type = (data.peripheral08Type == '0' ? '':data.peripheral08Type);
		    			var peripheral09Type = (data.peripheral09Type == '0' ? '':data.peripheral09Type);
		    			var peripheral10Type = (data.peripheral10Type == '0' ? '':data.peripheral10Type);
		    			var peripheral11Type = (data.peripheral11Type == '0' ? '':data.peripheral11Type);
		    			var peripheral12Type = (data.peripheral12Type == '0' ? '':data.peripheral12Type);
		    			var peripheral13Type = (data.peripheral13Type == '0' ? '':data.peripheral13Type);
    				 	var tempHtml="";
	    				 tempHtml+=
	    					 '<div class="keyParts">'+
		    					 '<div class="keyParts_header">'+
									'<div  class="allFacility" id="allFacilityFirst" data-checked-num="0" onclick="toogleAllKeyParts()">重点部位<span>'+keyPartsSize+'</span></div>'+
									'<div data-type="keyPerson" class="allFacilityPerson" onclick="tooglePartsFacility()">重点人<span>'+data.base_info_keyperson+'</span></div>'+
								 '</div>'+
								 "<ul class='facilitiesTwo' id='allFacilityFirstList' style='float:left;'>"+
									/* "<li data-type='stationYard' onclick='tooglePartsFacility()'>站场(<span>"+data.base_info_station_yard+"</span>)</li>"+ */
									"<li data-type='bridge' onclick='tooglePartsFacility()'>桥涵<span>"+data.bridgeCulvertCount+"</span></li>"+
									"<li data-type='junction' onclick='tooglePartsFacility()'>道口<span>"+data.base_info_part_junction+"</span></li>"+
									"<li data-type='tunnel' onclick='tooglePartsFacilityTunnle()'>隧道<span>"+data.base_info_part_tunnel+"</span></li>"+
								    "<li data-type='partStation' onclick='tooglePartsFacility()'>车站<span>"+data.base_info_part_station+"</span></li>"+ 
									"<li data-type='hiddenTrajection' onclick='tooglePartsFacility();'>易穿行<span>"+data.base_info_hidden_trajection+"</span></li>"+
									"<li data-type='hiddenDanger' id='danger' onclick='tooglePartsFacilityDanger();' style='width:110px;'>隐患点<span id='dangerNumber'>"+dangerNumberAll+"</span>"+
									"</li>"+
								"</ul>"+ 
								"<input  id='keyPartsInput'>"+
								"<div id='keyPartsList' >"+
									"<div data-type='全部' data-number='"+dangerNumberAll+"'>全部</div>"+
									"<div data-type='火灾' data-number='"+data.danger01Type+"'>火灾</div>"+ 
									"<div data-type='爆炸' data-number='"+data.danger02Type+"'>爆炸</div>"+
									"<div data-type='侵限' data-number='"+data.danger03Type+"'>侵限</div>"+
									"<div data-type='危树' data-number='"+data.danger04Type+"'data-type-num='data.base_info_hidden_trajection'>危树</div>"+
									"<div data-type='损坏' data-number='"+data.danger05Type+"'>损坏</div>"+
									"<div data-type='危建' data-number='"+data.danger06Type+"'>危建</div>"+
								"</div>"+
							'</div>'+ 
								
							'<div class="jointDefensepart">'+
			 				'<div class="jointDefensepart_header">'+
								'<div class="allFacility" id="allFacilitySecond" data-checked-num="0" onclick="toogleAllKeyPartsSecond()">联防点位<span>'+jointDefensepartSize+'</span></div>'+
							'</div>'+
								"<ul class='facilitiesOne' id='allFacilitySecondList'>"+
								 	"<li data-type='guardStation' onclick='tooglePartsFacilitySecond();'>工作站<span>"+data.base_info_guard_station+"</span></li>"+
									"<li data-type='propaganda' onclick='tooglePartsFacilitySecond();'>宣传点<span>"+data.base_info_propaganda+"</span></li>"+
									"<li data-type='broadcast' onclick='tooglePartsFacilitySecondBroadcast();'>警示柱<span>"+data.base_info_broadcast+"</span></li>"+
									"<li data-type='monitor' onclick='tooglePartsFacilitySecond()'>摄像头<span>"+data.video_monitor_info+"</span></li>"+
									"<li >派出所<span></span></li>"+
									"<li >警务站<span></span></li>"+
									"<li>专职队员<span id='userNum'></span></li>"+
									"<li data-type='user' onclick='tooglePartsFacilitySecond()'>在线干部</li>"+ 
								"</ul>"+ 
							'</div>'+ 
							
							'<div class="allFacilitySecondKey">'+
			 				'<div class="allFacilitySecondKey_header">'+
								'<div data-checked-num="0" class="allFacilityThird" id="allFacilityThird" onclick="toogleAllKeyPartsThird()">周边场所<span>'+placeAllNumber+'</span>(</div>'+
								'<div class="allFacilitySecond_two" id="allFacilitySecondTwo" data-checked-num="0" onclick="keyplace()">重点<span id="keyPlaceNumber"></span>&nbsp;)</div>'+
							'</div>'+
							'<div id="keyPlace">'+
								 "<ul id='allFacilityThirdList' style='float:left;'>"+
									    "<li data-type='residence' onclick='tooglePartsFacilityThird()'>住宅<span class='keyplaceHtml'>"+peripheral01Type+"</span></li>"+
										"<li data-type='company' onclick='tooglePartsFacilityThird()'>单位<span class='keyplaceHtml'>"+peripheral02Type+"</span></li>"+
										"<li data-type='market' onclick='tooglePartsFacilityThird()' >市场<span class='keyplaceHtml'>"+peripheral03Type+"</span></li>"+
										"<li data-type='school' onclick='tooglePartsFacilityThird()'>学校<span class='keyplaceHtml'>"+peripheral04Type+"</span></li>"+ 
										"<li data-type='mining' onclick='tooglePartsFacilityThird()'>工矿<span class='keyplaceHtml'>"+peripheral05Type+"</span></li>"+
										"<li data-type='constructionSite' onclick='tooglePartsFacilityThird()'>工地<span class='keyplaceHtml'>"+peripheral06Type+"</span></li>"+
										"<li data-type='leisureTime' onclick='tooglePartsFacilityThird()'>休闲<span class='keyplaceHtml'>"+peripheral07Type+"</span></li>"+
								"</ul>"+ 
							     "<div onclick='more()' class='more'>更多</div>"+
								    "<div id='more'>"+
								    	"<ul>"+
										    "<li data-type='commercial' onclick='tooglePartsFacilityThirdMore()'>商厦<span class='keyplaceHtml'>"+peripheral08Type+"</span></li>"+
									    	"<li data-type='roomAndBoard' onclick='tooglePartsFacilityThirdMore()'>吃住<span class='keyplaceHtml'>"+peripheral09Type+"</span></li>"+
											"<li data-type='breed' onclick='tooglePartsFacilityThirdMore()'>养殖<span class='keyplaceHtml'>"+peripheral10Type+"</span></li>"+
											"<li data-type='plant' onclick='tooglePartsFacilityThirdMore()'>种植(大棚)<span class='keyplaceHtml'>"+peripheral11Type+"</span></li>"+
											"<li data-type='gasStation' onclick='tooglePartsFacilityThirdMore()'>加油站<span class='keyplaceHtml'>"+peripheral12Type+"</span></li>"+
											"<li data-type='riversAndLakes' onclick='tooglePartsFacilityThirdMore()'>河湖(鱼塘)<span class='keyplaceHtml'>"+peripheral13Type+"</span></li>"+
								    	"</ul>"+
								    	"<div class='closeMore' onclick='closeMore()'>"+"</div>"+
								   "</div>"+ 
								'</div>'+
							'</div>'
			 				$(".ex_map_content_Keyfacilities_content_content").html(tempHtml);
 					    	var keyPartsInput=document.getElementById('keyPartsInput');  
 					    	var keyPartsList=document.getElementById('keyPartsList');  
    					    var keyPartstext=$('#keyPartsList div:first-child').text();
    					    var $selectDanger = $('#danger');
    					    var $selectDangerAll  = $('#allFacilityFirst');
    					    var dangerNumber = $('#dangerNumber');
    					    document.getElementById("keyPartsInput").value=keyPartstext; 
    					    var keyPartsLists=$('#keyPartsList div');  
    					    keyPartsInput.onfocus=function(){  
    					    	keyPartsList.style.display='block';  
    					    };
    					    keyPartsInput.onblur=function(){  
    					        setTimeout(function(){  
    					        	keyPartsList.style.display='none';  
    					        },200)  
    					    };
    					    for(var i=0; i<keyPartsLists.length;i++){  
    					    	keyPartsLists[i].onclick=function(){ 
   					    			PatrolDangerLayer.getSource().clear();
									FireLayer.getSource().clear();
									BlastLayer.getSource().clear();
									InvasionLayer.getSource().clear();	
									DangerTreeLayer.getSource().clear();
									DamageLayer.getSource().clear();
									WeiJianLayer.getSource().clear();
									for (var i = 0; i<overlays.length;i++){
										console.log(overlays[i]);
										map.removeOverlay(overlays[i]);
									}
    					    		if($selectDanger.data("checked",true)){
    					    			$selectDanger.data("checked", false).removeClass('on');
    					    			$selectDangerAll.data("checked", false);
    									$selectDangerAll.data("checkedNum", $selectDangerAll.data("checkedNum") - 1);
    									$selectDangerAll.removeClass().addClass('allFacility');
    					    		}else{
    					    			$selectDangerAll.data("checkedNum", $selectDangerAll.data("checkedNum") + 1);
    					    		}
									keyPartsInput.value=this.innerText; 
									var dangerText = this.innerText;
									$("#danger").data('type',dangerText);
									var $target = $(event.target);
									var $targetNumber=$target.data("number");
									dangerNumber.html($targetNumber);
    					        }  
    					    }    
		    			}
	    			});
				});
			/* 重点周边场所 */
			function keyplace(){
				$.ajax({
		    		url:"${ctx}/exhibition/map/getOrgInfo?type=1&orgId="+adminDivisionOrgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: true,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    		},
		    		success:function(data){
		    			$('#allFacilitySecondTwo').data('checkedNum',0);
		    			var keyPlaceNumber = 
		    				data.peripheral01Type+
		    				data.peripheral02Type+
		    				data.peripheral03Type+
		    				data.peripheral04Type+          
		    				data.peripheral05Type+
		    				data.peripheral06Type+
		    				data.peripheral07Type+
		    				data.peripheral08Type+
		    				data.peripheral09Type+
		    				data.peripheral10Type+
		    				data.peripheral11Type+
		    				data.peripheral12Type+
		    				data.peripheral13Type;
		    			
		    			/* 判断周边场所数目为0 则为空 */
		    			var peripheral01Type = (data.peripheral01Type == '0' ? '':data.peripheral01Type);
		    			var peripheral02Type = (data.peripheral02Type == '0' ? '':data.peripheral02Type);
		    			var peripheral03Type = (data.peripheral03Type == '0' ? '':data.peripheral03Type);
		    			var peripheral04Type = (data.peripheral04Type == '0' ? '':data.peripheral04Type);
		    			var peripheral05Type = (data.peripheral05Type == '0' ? '':data.peripheral05Type);
		    			var peripheral06Type = (data.peripheral06Type == '0' ? '':data.peripheral06Type);
		    			var peripheral07Type = (data.peripheral07Type == '0' ? '':data.peripheral07Type);
		    			var peripheral08Type = (data.peripheral08Type == '0' ? '':data.peripheral08Type);
		    			var peripheral09Type = (data.peripheral09Type == '0' ? '':data.peripheral09Type);
		    			var peripheral10Type = (data.peripheral10Type == '0' ? '':data.peripheral10Type);
		    			var peripheral11Type = (data.peripheral11Type == '0' ? '':data.peripheral11Type);
		    			var peripheral12Type = (data.peripheral12Type == '0' ? '':data.peripheral12Type);
		    			var peripheral13Type = (data.peripheral13Type == '0' ? '':data.peripheral13Type);
		    			
	    				var tempHtml="";
	    				tempHtml+= 
	    					 "<ul id='allFacilityThirdListKey' style='float:left;'>"+
	    					 		"<li data-type='residence' onclick='tooglePartsFacilityThirdKey()'>住宅<span>"+peripheral01Type+"</span></li>"+
				 					"<li data-type='company' onclick='tooglePartsFacilityThirdKey()'>单位<span>"+peripheral02Type+"</span></li>"+
									"<li data-type='market' onclick='tooglePartsFacilityThirdKey()' >市场<span>"+peripheral03Type+"</span></li>"+
									"<li data-type='school' onclick='tooglePartsFacilityThirdKey()'>学校<span>"+peripheral04Type+"</span></li>"+ 
									"<li data-type='mining' onclick='tooglePartsFacilityThirdKey()'>工矿<span>"+peripheral05Type+"</span></li>"+
									"<li data-type='constructionSite' onclick='tooglePartsFacilityThirdKey()'>工地<span>"+peripheral06Type+"</span></li>"+
									"<li data-type='leisureTime' onclick='tooglePartsFacilityThirdKey()'>休闲<span>"+peripheral07Type+"</span></li>"+
			   					"</ul>"+
			   					 "<div onclick='more()' class='more'>更多</div>"+
							    	"<div id='more'>"+
								    	"<ul>"+
										    "<li data-type='commercial' onclick='tooglePartsFacilityThirdMore()'>商厦<span>"+peripheral08Type+"</span></li>"+
									    	"<li data-type='roomAndBoard' onclick='tooglePartsFacilityThirdMore()'>吃住<span>"+peripheral09Type+"</span></li>"+
											"<li data-type='breed' onclick='tooglePartsFacilityThirdMore()'>养殖<span>"+peripheral10Type+"</span></li>"+
											"<li data-type='plant' onclick='tooglePartsFacilityThirdMore()'>种植(大棚)<span>"+peripheral11Type+"</span></li>"+
											"<li data-type='gasStation' onclick='tooglePartsFacilityThirdMore()'>加油站<span>"+peripheral12Type+"</span></li>"+
											"<li data-type='riversAndLakes' onclick='tooglePartsFacilityThirdMore()'>河湖(鱼塘)<span>"+peripheral13Type+"</span></li>"+
								    	"</ul>"+
		    							"<div class='closeMore' onclick='closeMore()'>"+"</div>"+
		   						"</div>";
		    			$("#keyPlace").html(tempHtml);
		    			$("#keyPlaceNumber").html(keyPlaceNumber);
				   		var $select = $('#allFacilityThird');
		    			var pListKey = $('#allFacilityThirdListKey li');
					   	var $selectKey = $('#allFacilitySecondTwo');
					  	if($selectKey.data("checked")) {
							$selectKey.data("checked", false).removeClass().addClass('allFacilitySecond_two');
							$select.data("checked", false).removeClass().addClass('allFacilityThird');
							RoomAndBoardLayer.setVisible(false);
							RiversAndLakesLayer.setVisible(false);    
							GasStationLayer.setVisible(false);      
							PolicePtationSLayer.setVisible(false);
							PoliceStationLayer.setVisible(false);
							FullTimeMemberLayer.setVisible(false);
							ResidenceLayer.setVisible(false);
							CompanyLayer.setVisible(false);
							MarketLayer.setVisible(false);
							MiningLayer.setVisible(false);
							ConstructionSiteLayer.setVisible(false);
							LeisureTimeLayer.setVisible(false);
							CommercialLayer.setVisible(false);
							RoomAndBoardLayer.setVisible(false);
							BreedLayer.setVisible(false);
							PlantLayer.setVisible(false);
							PlantLayer.setVisible(false);
							BJSchoolLayer.setVisible(false);
				  		} else {
					  		$selectKey.data("checked", true).removeClass().addClass('keyplace');
					  		$select.data("checked", false).removeClass().addClass('allFacilityThird');
					  	}
					  	var allFacilityCheckedKey = $selectKey.data("checked");
					  	for(var i = 0; i < pListKey.length; i++ ) {
						   var pKey = pListKey[i];
						   var pCheckedKey = $(pKey).data("checked");
						   if(allFacilityCheckedKey) {
							   if(!pCheckedKey) {
								   pKey.click();
							   }
						   } else {
							   if(pCheckedKey) {
								   pKey.click();
							   }
						   }
					   }
		    		}
	    		});
			}
			/* 铁路分类  高铁 普速......*/
			var url = "${ctx}/exhibition/map/findRail";
			function showRailSelect(classify){
				$('#showAllRail').data('checkedNum',0);
				$('#showAllRail').data("checked", false).removeClass('on');
				BJRailRouteLayer.getSource().clear();
				BJRailRouteBaseLayer.getSource().clear();
				$.ajax({
		    		url:url,
		    		type:'post',
		    		dataType:'json',
		    		data:{ 
		    			'orgId':adminDivisionOrgId,
		    			'classify':classify
		    		},
		    		error:ajaxErrorCb,
	    			success:
	    				function(data){
	    					var tempHtml = "";
		    				for(var i = 0; i<data.length;i++){
		    					var railStr = JSON.stringify({id: data[i].id, name: data[i].name,classify:data[i].classify});
		    					tempHtml +=	
	    								"<li onclick='toggleRail()' id='" + data[i].id + "' data-rail-info="+railStr+" >"+data[i].name+"</li>"
		    				}
		    				$("#baseInfoRail").html(tempHtml);
		    				$('#showAllRail').click();
		    			}
				})
			}
			/**
	         * 记录线路 features
	         * 每个铁路线有两个feature
	         * key: 铁路线
	         * value: key: rail   value: [[baseFeature0, feature0], [baseFeature0, feature0], [baseFeature0, feature0]]
	         */
			var railLineFeaturesMap = new Map();
			/**
	         * 一条铁路线可能被区域分成多段,或者存在多个stream
	         * 为了使相同区域的相同线路的不同段全部显示，规定如下：
	         * key: rail   value: [[线路1], [线路1], [线路1]]
	         */
			var railLineMap = new Map();
			// 检索铁路html（右侧选择）
			function retrievalRailHTML(data) {
				return data.map(function(item) {
					var railStr = JSON.stringify({id: item.id, name: item.name, classify: item.classify}); // key
					return ""
						+ "<li id='" + item.id + "' data-rail-info=" + railStr +" onclick='toggleRail();'>"+item.name +"</li>";
				});
			}
	        /* 显示全部铁路线 */
			function showAllRail() {
				toggleAllRail();
	    	}
	        /* 隐藏全部铁路线 */
	        function hideAllRail() {
	        	$('#showAllRail').data("checked", false).removeClass('on');
	    		railLineMap.forEach(function(value, key, map) {
		    		var r = JSON.parse(key);
		    		hideRail(r);
		    	});
	        	BJRailRouteBaseLayer.getSource().clear();
	        	BJRailRouteLayer.getSource().clear();
	        	selectedRailIds.clear();
	        } 
	       /* 显示隐藏所有铁路线 */
	       function toggleAllRail() {
	    	 	var liList = $('#baseInfoRail li');
			   	var $select = $('#showAllRail');
			  	if($select.data("checked")) {
					$select.data("checked", false).removeClass('on');
		  		} else {
			  		$select.data("checked", true).addClass('on');
			  	}
			  	var allFacilityChecked = $select.data("checked");
			  	for(var i = 0; i < liList.length; i++ ) {
				   var li = liList[i];
				   var liChecked = $(li).data("checked");
				   
				   if(allFacilityChecked) {
					   if(!liChecked) {
						   li.click();
					   }
				   } else {
					   if(liChecked) {
						   li.click();
					   }
				   }
			   }
		    }
			/**
			 *展示/隐藏铁路线（一条铁路线可能被区域分成多段）
			 */
			function toggleRail() {
			 	if(railLineMap.size == 0) {
				 	return;
		 		}
				var $rail = $(event.target);
	            var railInfo = $rail.data('railInfo');
	            var $showRail = $('#showAllRail')
	            if ($rail.data('checked')) {
            	 	$showRail.data("checkedNum",  $showRail.data("checkedNum") - 1);
	            	$showRail.removeClass('on').data('checked', false); // 取消全部选中
	                hideRail(railInfo); // 隐藏
	            } else {
	            	$showRail.data("checkedNum", $showRail.data("checkedNum") + 1);
	            	if($showRail.data("checkedNum") === $('#baseInfoRail').children().length) {
	            		$('#showAllRail').data("checked", true).addClass('on');
					} 
	                showRail(railInfo); // 显示
	            }
			}
			
	        /**
	         * 展示某条铁路线
	         * @param  {[type]} rail      铁路线信息：{"id": "", "name": "", "classify": ""}
	         * @return {[type]}           
	         */
	        function showRail(rail) {
        		var $li = $('#' + rail.id)
        		/* var $p = $li.children('p'); */
        		if($li.data('checked')) { // 如果是选中的不做处理
        			return;
        		}
        		$li.addClass('on');
        		$li.data('checked', true);
       	 		selectedRailIds.add(rail.id);
	            var railStr = JSON.stringify(rail);
	            // 该铁路的所有的铁路线段
	            var railLineFeaturesArray = railLineFeaturesMap.get(railStr);
	         	// 如果已经存在feature，那么直接添加显示
	            if(railLineFeaturesArray) {
	            		railLineFeaturesArray.forEach(function(railLineFeatures) {
	                    BJRailRouteBaseLayer.getSource().addFeature(railLineFeatures[0]);
	                    BJRailRouteLayer.getSource().addFeature(railLineFeatures[1]);
	                }, this);
	            } else {
	            		railLineFeaturesArray = new Array();
		                railLineFeaturesMap.set(railStr, railLineFeaturesArray);
	                
		            // 不存在就创建
		            var railLinesArray = railLineMap.get(railStr);
		            railLinesArray.forEach(function(railLines) {
		                var features = createRailLineFeatures(rail, railLines);
		                 // 将创建的features添加到layer上
		                BJRailRouteBaseLayer.getSource().addFeature(features[0]);
		                BJRailRouteLayer.getSource().addFeature(features[1]);
		                // 然后存储起来，下次使用
		                railLineFeaturesArray.push(features);
		            }, this);
	            }
	        }
	         // 隐藏某条铁路线
	         function hideRail(rail) {
	       	 	var $li = $('#' + rail.id)
	       	/* 	var $p = $li.children('p'); */
	       		if(!$li.data('checked')) { // 如果是未选中的不做处理
	       			return;
	       		}
	       		$li.removeClass('on');
	       		$li.data('checked', false);
	      	 		selectedRailIds.delete(rail.id);
	       	 	var railStr = JSON.stringify(rail);
	            // 该铁路的所有的铁路线段
	            var railLineFeaturesArray = railLineFeaturesMap.get(railStr);
	            if(railLineFeaturesArray && railLineFeaturesArray.length > 0) {
	          			railLineFeaturesArray.forEach(function(features) {
	                      BJRailRouteBaseLayer.getSource().removeFeature(features[0]);
	                      BJRailRouteLayer.getSource().removeFeature(features[1]);
	                  	}, this);
	              	}
	         }
			//================================= 铁路信息 start ======================================
			/* ajax请求数据结构如下：
	         	[{
	     			id: "rail_1500000000001",
					name: "周支线.周良联络线",
					classify:0, 			// 类型 0 普通 1高铁
					router: [
						{lat: '', lng: '', stream: 0, orderNumber: 0 },
						{lat: '', lng: '', stream: 0, orderNumber: 0 }
					]
	     		}]
			 */
			var allRails = [];
		  	function searchCity(searchCityName) {
		  		hideAllRail();
				var reg  = new RegExp(searchCityName, 'i');
				var filteredRails = allRails.filter(function(item) {
					return reg.test(item.name);
				});
				var html = retrievalRailHTML(filteredRails);
				$('#baseInfoRail').html(html);
				toggleAllRail();
				$('#showAllRail').data('checkedNum',0);
		  	}
		  	
			function loadRailData(selectedOrgId, cb) {
				railLineMap.clear();
				railLineFeaturesMap.clear();
				// 根据用户机构加载铁路和其他数据加载北京市铁路数据 加载指定orgId的铁路线路
				$.ajax({
					url: 'map/getBJRailInfo',
					type: "GET",
					dataType: 'json',
					data: {
						"orgId": selectedOrgId
					},
					error: ajaxErrorCb,
					success: function(data) { /* 遍历data,将每条铁路点遍历出来，点动成线 */
						allRails = data;
						parseRails(data);
                        var tmpHtmlArray = retrievalRailHTML(data);
                        $("#baseInfoRail").html(tmpHtmlArray.join(""));
                        var html = locationRail(data);
        					$('#locationRail').html(html);
                        if(cb) {
                  			cb();
                        }
					}
				});
			}
			// 根据km定位html
			function locationRail(data) {
				return data.map(function(item) {
					return "<option value='" + item.id + "' >" + item.name + "</option>";
				});
			}
			/* 解析后的数据格式如下（map）：
			  key: railInfo   value: [[线路1], [线路1], [线路1]]
			*/
			function parseRails(data) {
                for (var i = 0; i < data.length; i++) {
                    var railTmp = data[i]; // 线路信息 格式：{ classify: '', id: '', name: '', router: [{},{}] }
                    var router = railTmp.router; // 路线 [{ lat: '', lng: '', stream: 1, orderNumber: 1}]
                    delete railTmp.router; // 为了记录铁路信息
                    var railStr = JSON.stringify(railTmp); // key
                    // 线路点队列
                    var tempRailLines = parseRailRouter(router);
                    railLineMap.set(railStr, tempRailLines);
                }
			}
			
			/*
			解析铁路线(根据上下行（stream）和点序号（orderNumber）)
			router: [
						{lat: '', lng: '', stream: 0, orderNumber: 0 },
						{lat: '', lng: '', stream: 0, orderNumber: 0 }
					]
			解析后：return  [[线路1], [线路1], [线路1]]
			*/
			function parseRailRouter(router) {
				var tempRailLines = []; // 存在断点时或者分上下行时，存放多个线段
                var tempRailLine = []; // 存放每一段线路点信息
                
				var orderNumberTmp = -100; //判断是否存在断点， 存在断点时，增加一条线段
                var streamTmp = -100; // 判断是否存在上下行， stream改变时，会增加一条线段
                
				for (var j = 0; j < router.length; j++) {
                    var routerTmp = router[j]; // 点 格式：{lat: '', lng: '', stream: 1, orderNumber: 1}
                    
                    if(streamTmp == routerTmp.stream) { // stream 相等
                    		if (orderNumberTmp + 1 == routerTmp.orderNumber) { // 说明线段连续
                            orderNumberTmp = routerTmp.orderNumber;
                            tempRailLine.push([(+routerTmp.lng), (+routerTmp.lat)]);
                        	} else { // 说明存在断点了, 将该段线路存储起来，重新记录下一条线路
                            if(tempRailLine.length) {
                                tempRailLines.push(Array.from(tempRailLine));
                       		}
                            // 重置tempRailLine，来存放下一段线路
                            tempRailLine = [];
                            orderNumberTmp = routerTmp.orderNumber;
                            tempRailLine.push([(+routerTmp.lng), (+routerTmp.lat)]);
                        }
                    } else { // stream改变了, 就产生了一条新线路，将该段线路存储起来，开始记录下一条线路
                    		streamTmp = routerTmp.stream;
                    		// 将该段线路存储起来
                    		if(tempRailLine.length) {
                        		tempRailLines.push(Array.from(tempRailLine));                        			
                    		}
                    		// 重置tempRailLine，来存放下一段线路
                        tempRailLine = [];
                        orderNumberTmp = routerTmp.orderNumber;
                        tempRailLine.push([(+routerTmp.lng), (+routerTmp.lat)]);
                    }
                }
				// 将最后一段线路存放起来
                tempRailLines.push(tempRailLine);
                return tempRailLines;
			}
				
			//================================= 铁路信息 end ========================================
				
			//======================================================================================
			var mapContainer = document.getElementById('map-popup');
			var mapContent = document.getElementById('map-popup-content');
			var mapCloser = document.getElementById('map-popup-closer');
			var mapOverlay = new ol.Overlay(/** @type {olx.OverlayOptions} */
			({
				element : mapContainer,
				autoPan : true,
				positioning: 'center-center',
				autoPanAnimation : {
					duration : 250
				}
			}));
			
			mapCloser.onclick = function() {
				mapOverlay.setPosition(undefined);
				mapCloser.blur();
				return false;
			};
			map.addOverlay(mapOverlay);
			
//========================================================= 以下为重点设施操作 =========================================================================
			// 选中的重点设施
			var selectedKeyFacilities = new Set();
			var selectedFacilities = new Set();
			/* 加载重点设施 */
			function loadKeyFacility(url, method, successCb) {
				$.ajax({
					url: url,
					type: method,
					dataType: 'json',
					data: {
						'orgId': orgId,
						'railId': [],
					},
					error: ajaxErrorCb,
					success: successCb
				});
			}
			/* 周边场所 key(重点) */
			var loadKeyFacilitiesFuncKey = {
				/* 住宅*/
				"residence": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=01&&type=1', 'GET', function(data) {
						ResidenceLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'residence'
							});
							f.setStyle(residenceStyle);
							return f;
						});
						ResidenceLayer.getSource().addFeatures(features);
					});
				},
				/* 单位*/
				"company": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=02&&type=1', 'GET', function(data) {
						CompanyLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'company'
							});
							f.setStyle(companyStyle);
							return f;
						});
						CompanyLayer.getSource().addFeatures(features);
					});
				},
				/* 市场*/
				"market": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=03&&type=1', 'GET', function(data) {
						MarketLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'market'
							});
							f.setStyle(marketStyle);
							return f;
						});
						MarketLayer.getSource().addFeatures(features);
					});
				},
				/* 学校 */
				"school": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=04&&type=1', 'GET', function(data) {
						BJSchoolLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'school'
							});
							f.setStyle(schoolStyle);   
							return f;
						});
						BJSchoolLayer.getSource().addFeatures(features);
					});
				},
				/* 工矿 */
				"mining": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=05&&type=1', 'GET', function(data) {
						MiningLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'mining'
							});
							f.setStyle(miningStyle);
							return f;
						});
						MiningLayer.getSource().addFeatures(features);
					});
				},
				/* 工地 */
				"constructionSite": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=06&&type=1', 'GET', function(data) {
						ConstructionSiteLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'constructionSite'
							});
							f.setStyle(constructionSiteStyle);
							return f;
						});
						ConstructionSiteLayer.getSource().addFeatures(features);
					});
				},
				/* 休闲 */
				"leisureTime": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=07&&type=1', 'GET', function(data) {
						LeisureTimeLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'leisureTime'
							});
							f.setStyle(leisureTimeStyle);
							return f;
						});
						LeisureTimeLayer.getSource().addFeatures(features);
					});
				},
				/* 商厦*/
				"commercial": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=08&&type=1', 'GET', function(data) {
						CommercialLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'commercial'
							});
							f.setStyle(commercialStyle);
							return f;
						});
						CommercialLayer.getSource().addFeatures(features);
					});
				},
				/* 吃住*/
				"roomAndBoard": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=09&&type=1', 'GET', function(data) {
						RoomAndBoardLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'roomAndBoard'
							});
							f.setStyle(roomAndBoardStyle);
							return f;
						});
						RoomAndBoardLayer.getSource().addFeatures(features);
					});
				},
				/* 养殖*/
				"breed": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=10&&type=1', 'GET', function(data) {
						BreedLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'breed'
							});
							f.setStyle(breedStyle);
							return f;
						});
						BreedLayer.getSource().addFeatures(features);
					});
				},
				/* 种植*/
				"plant": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=11&&type=1', 'GET', function(data) {
						PlantLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'plant'
							});
							f.setStyle(plantStyle);
							return f;
						});
						PlantLayer.getSource().addFeatures(features);
					});
				},
				/* 加油站*/
				"gasStation": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=12&&type=1', 'GET', function(data) {
						GasStationLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'gasStation'
							});
							f.setStyle(gasStationStyle);
							return f;
						});
						GasStationLayer.getSource().addFeatures(features);
					});
				},
				/*河湖*/
				"riversAndLakes": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=13&&type=1', 'GET', function(data) {
						RiversAndLakesLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'riversAndLakes'
							});
							f.setStyle(riversAndLakesStyle);
							return f;
						});
						RiversAndLakesLayer.getSource().addFeatures(features);
					});
				},
			};
			/* ======================================================================================= */
			var loadKeyFacilitiesFunc = {
				'hiddenDanger': function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo', 'GET', function(data) {
						PatrolDangerLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: 'hiddenDanger'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var markerDanger = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(markerDanger.getElement()).addClass("mark")
							map.addOverlay(markerDanger)
							overlaysDanger.push(markerDanger)
							markerDanger.setPosition([(+item.lng), (+item.lat)])
							/* f.setStyle(patrolDangerStyle); */
							return f;
						});
						PatrolDangerLayer.getSource().addFeatures(features);
					});
				},
				"全部": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '全部'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						DangerAllLayer.getSource().addFeatures(features);
					});
				},
				"火灾": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo?type=01', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '火灾'
							});
							
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						FireLayer.getSource().addFeatures(features);
					});
				},
				
				"爆炸": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo?type=02', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '爆炸'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						BlastLayer.getSource().addFeatures(features);
					});
				},
				
				"侵限": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo?type=03', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '侵限'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						InvasionLayer.getSource().addFeatures(features);
					});
				},
				
				"危树": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo?type=04', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '危树'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						DangerTreeLayer.getSource().addFeatures(features);
					});
				},
				
				"损坏": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo?type=05', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '损坏'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						DamageLayer.getSource().addFeatures(features);
					});
				},
				"危建": function(successCb) {
					loadKeyFacility('map/getBJPatrolDangerInfo?type=06', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPatrolDangerPopWin',
								type: '危建'
							});
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							markerDom.id='item.id' 
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("mark")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
							return f;
						});
						WeiJianLayer.getSource().addFeatures(features);
					});
				},
				
				"broadcast": function(successCb) {
					loadKeyFacility('map/getBJBroadcastInfo', 'GET', function(data) {
						BJBroadcastLayer.getSource().clear();
						 var features = data.map(function(item) {
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							$('#bjRailMap').append(markerDom)
							var marker = new ol.Overlay({
							    position: pos,
							    positioning: 'center-center',
							    element: markerDom,
							    stopEvent: false
							});
							$(marker.getElement()).addClass("broadcast")
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition([(+item.lng), (+item.lat)])
						 	var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getBroadcastPopWin',
								type: 'broadcast'
							}); 
							f.setStyle(broadcastStyle); 
					       return f; 
						}); 
						/* var Overlay = data.map(function(item) {
							var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
							var markerDom=document.createElement('div')
							var marker = createPointOverly(item.id,item.name,'map/getBroadcastPopWin','broadcast', 'center-center',markerDom,false,pos);
							map.addOverlay(marker)
							overlays.push(marker)
							marker.setPosition(Overlay)
						}); */
						  BJBroadcastLayer.getSource().addFeatures(features); 
					});
				},
				"propaganda": function(successCb) {
					loadKeyFacility('map/getBJPropagandaInfo', 'GET', function(data) {
						BJPropagandaLayer.getSource().clear();
						var features = data.map(function(item) {
							var propaganda = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPropagandaPopWin',
								type: 'propaganda'
							});
							propaganda.setStyle(propagandaStyle);
							return propaganda;
						});
						BJPropagandaLayer.getSource().addFeatures(features);
					});
				},
				"placeDanger": function(successCb) {
					loadKeyFacility('map/getBJPlaceDangerous', 'GET', function(data) {
						BJPlaceDangerousLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPlaceDangerousPopWin',
								type: 'placeDanger'
							});
							f.setStyle(placeDangerousStyle);
							return f;
						});
						BJPlaceDangerousLayer.getSource().addFeatures(features);
					});
				},
				"guardStation": function(successCb) {
					loadKeyFacility('map/getBJGuardStationInfo', 'GET', function(data) {
						BJGuardStationLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([parseFloat(item.lng), parseFloat(item.lat)]),
								id: item.id,
								url: 'map/getGuardStationPopWin',
								type: 'guardStation'
							});
							f.setStyle(guardStationStyle);
							return f;
						});
						BJGuardStationLayer.getSource().addFeatures(features);
					});
				},
				/* "school": function(successCb) {
					loadKeyFacility('map/getBJSchool', 'GET', function(data) {
						BJSchoolLayer.getSource().clear();
						var features = data.filter(function(item) { // 没有经纬度
							return (+item.lng) && (+item.lat);
							 // return !isNaN(parseFloat(item.lng)) && !isNaN(parseFloat(item.lat));
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getSchoolPopWin',
								type: 'school'
							});
							f.setStyle(schoolStyle);
							return f;
						});
						BJSchoolLayer.getSource().addFeatures(features);
					});
				}, */
	 			"partStation": function(successCb) {
					loadKeyFacility('map/getBJPartStationInfo', 'GET', function(data) {
						partStatYard();
						var zoom = map.getView().getZoom();
						console.log('zoom战场===================狗屎',zoom);
						StationYardLayer.setVisible(true);
						BJPartStationLayer.getSource().clear();
						var features = data.filter(function(item) { // 没有经纬度
							return (+item.lng) && (+item.lat);
							 //return !isNaN(parseFloat(item.lng)) && !isNaN(parseFloat(item.lat));
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([+item.lng, +item.lat]),
								id: item.id,
								/* url: 'map/getPartStationPopWin', */
								type: 'partStation'
							});
							f.setStyle(partStationStyle);
							return f;
						});
						BJPartStationLayer.getSource().addFeatures(features);
					});
				}, 
				"hiddenTrajection": function(successCb) {
					loadKeyFacility('map/getBJHiddenTrajection', 'GET', function(data) {
						HiddenTrajectionLayer.getSource().clear();
						var features = data.filter(function(item) {
							return item.region;
						}).map(function(item) {
							var lines = JSON.parse(item.region);
							var f = createPolygonFeature(lines, item.id, item.name, 'hiddenTrajection', 'map/getHiddenTrajectionPopWin');
							f.setStyle(trajectionpolygonStyle);
							return f;
						});
						HiddenTrajectionLayer.getSource().addFeatures(features);
					});
				},
				"bridge": function(successCb) {
					loadKeyFacility('map/getBJBridge', 'GET', function(data) {
						BJBridgeLayer.getSource().clear();
						var features = data.filter(function(item) { // 没有经纬度
							return (+item.lng) && (+item.lat);
							 // return !isNaN(parseFloat(item.lng)) && !isNaN(parseFloat(item.lat));
						}).map(function(item) {
							/* console.log('item=============================================',item) */
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getBridgePopWin',
								type: 'bridge'
							});
							f.setStyle(bridgeStyle);
							return f;
						});
						BJBridgeLayer.getSource().addFeatures(features);
					});
				},
				"junction": function(successCb) {
					loadKeyFacility('map/getBJJunction', 'GET', function(data) {
						BJJunctionLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
							// return !isNaN(parseFloat(item.lng)) && !isNaN(parseFloat(item.lat));
						}).map(function(item) {
							$('#map-popup-content').html(item);
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getJunctionPopWin',
								type: 'junction'
							});
							f.setStyle(junctionStyle);
							return f;
						});
						BJJunctionLayer.getSource().addFeatures(features);
					});
					
				},
				/* "tunnel": function(successCb) {
					loadKeyFacility('map/getBJTunnel', 'GET', function(data) {
						BJTunnelLayer.getSource().clear();
						var features = data.filter(function(item) {
							return (+item.lng) && (+item.lat);
							// return !isNaN(parseFloat(item.lng)) && !isNaN(parseFloat(item.lat))
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getTunnelPopWin',
								type: 'tunnel'
							});
							f.setStyle(tunnelStyle);
							return f;
						});
						BJTunnelLayer.getSource().addFeatures(features);
					});
				}, */
				"keyPerson": function(successCb) {
					loadKeyFacility('map/getBJKeyperson', 'GET', function(data) {
						BJKeyplaceLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([parseFloat(item.lng), parseFloat(item.lat)]),
								id: item.id,
								url: 'map/getKeypersonPopWin',
								type: 'keyPerson'
							});
							f.setStyle(keyPersonStyle);
							return f;
						});
						BJKeyplaceLayer.getSource().addFeatures(features);
					});
				},
				"monitor": function(successCb) {
					loadKeyFacility('map/getBJMonitorInfo', 'GET', function(data) {
						MonitorInfoLayer.getSource().clear();
						var features = data.map(function(item) {
							var f = createPointFeature(item.id, item.name, 'map/getMonitorPopWin', 'monitor', [parseFloat(item.lng), parseFloat(item.lat)]);
							f.setStyle(monitorStyle);
							return f;
						});
						
						
						MonitorInfoLayer.getSource().addFeatures(features);
					});
				},
				"user": function(successCb) {
					loadKeyFacility('map/getAppOnlineUserInfo', 'GET', function(data) {
						AppLayer.getSource().clear();
						var features = data.filter(function(item) {
							return (+item.lng) && (+item.lat);
							// return !isNaN(parseFloat(item.lng)) && !isNaN(parseFloat(item.lat))
						}).map(function(item) {
							var f = createPointFeature(item.uid, item.userName, 'map/getUserPopWin', 'user', [(+item.lng), (+item.lat)]);
							f.setStyle(appStyle);
							return f;
						}); 
						AppLayer.getSource().addFeatures(features);
					});
				},
				/* 站场 */
				"stationYard": function(successCb) {
					loadKeyFacility('map/getBJPartStationYardInfo', 'GET', function(data) {
						StationYardLayer.getSource().clear();
						var features = data.filter(function(item) {
							return item.range;
						}).map(function(item) {
							var lines = JSON.parse(item.range);
							var f = createPolygonFeature(lines, item.id, item.name, 'stationYard', 'map/getPartStationYardPopWin');
							f.setStyle(stationYardStyle);
							return f;
						});
						StationYardLayer.getSource().addFeatures(features);
					});
				},
				/* 住宅*/
				"residence": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=01', 'GET', function(data) {
						ResidenceLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'residence'
							});
							f.setStyle(residenceStyle);
							return f;
						});
						ResidenceLayer.getSource().addFeatures(features);
					});
				},
				/* 单位*/
				"company": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=02', 'GET', function(data) {
						CompanyLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'company'
							});
							f.setStyle(companyStyle);
							return f;
						});
						CompanyLayer.getSource().addFeatures(features);
					});
				},
				/* 市场*/
				"market": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=03', 'GET', function(data) {
						MarketLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'market'
							});
							f.setStyle(marketStyle);
							return f;
						});
						MarketLayer.getSource().addFeatures(features);
					});
				},
				/* 学校 */
				"school": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=04', 'GET', function(data) {
						BJSchoolLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'school'
							});
							f.setStyle(schoolStyle);
							return f;
						});
						BJSchoolLayer.getSource().addFeatures(features);
					});
				},
				/* 工矿 */
				"mining": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=05', 'GET', function(data) {
						MiningLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'mining'
							});
							f.setStyle(miningStyle);
							return f;
						});
						MiningLayer.getSource().addFeatures(features);
					});
				},
				/* 工地 */
				"constructionSite": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=06', 'GET', function(data) {
						ConstructionSiteLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'constructionSite'
							});
							f.setStyle(constructionSiteStyle);
							return f;
						});
						ConstructionSiteLayer.getSource().addFeatures(features);
					});
				},
				/* 休闲 */
				"leisureTime": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=07', 'GET', function(data) {
						LeisureTimeLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'leisureTime'
							});
							f.setStyle(leisureTimeStyle);
							return f;
						});
						LeisureTimeLayer.getSource().addFeatures(features);
					});
				},
				/* 商厦*/
				"commercial": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=08', 'GET', function(data) {
						CommercialLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'commercial'
							});
							f.setStyle(commercialStyle);
							return f;
						});
						CommercialLayer.getSource().addFeatures(features);
					});
				},
				/* 吃住*/
				"roomAndBoard": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=09', 'GET', function(data) {
						
						
						
						RoomAndBoardLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'roomAndBoard'
							});
							f.setStyle(roomAndBoardStyle);
							return f;
						});
						RoomAndBoardLayer.getSource().addFeatures(features);
					});
				},
				/* 养殖*/
				"breed": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=10', 'GET', function(data) {
						BreedLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'breed'
							});
							f.setStyle(breedStyle);
							return f;
						});
						BreedLayer.getSource().addFeatures(features);
					});
				},
				/* 种植*/
				"plant": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=11', 'GET', function(data) {
						PlantLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'plant'
							});
							f.setStyle(plantStyle);
							return f;
						});
						PlantLayer.getSource().addFeatures(features);
					});
				},
				/* 加油站*/
				"gasStation": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=12', 'GET', function(data) {
						GasStationLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'gasStation'
							});
							f.setStyle(gasStationStyle);
							return f;
						});
						GasStationLayer.getSource().addFeatures(features);
					});
				},
				/*河湖*/
				"riversAndLakes": function(successCb) {
					loadKeyFacility('map/getBJPeripheralPlace?category=13', 'GET', function(data) {
						RiversAndLakesLayer.getSource().clear();
						var features = data.filter(function(item) { // 将没有经纬度的过滤掉
							return (+item.lng) && (+item.lat);
						}).map(function(item) {
							var f = new ol.Feature({
								geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
								id: item.id,
								url: 'map/getPeripheralPlacePopWin',
								type: 'riversAndLakes'
							});
							f.setStyle(riversAndLakesStyle);
							return f;
						});
						RiversAndLakesLayer.getSource().addFeatures(features);
					});
				},
			};
			function findLayerByType(type) {
				var layer = null;
				var layers = map.getLayers();
				for(var i = 0; i < layers.getLength(); i++) {
				    var tmpLayer = layers.item(i);
				    if(tmpLayer.get('type') === type) {
				        layer = tmpLayer;
				        break;
				    }
				}
				return layer;
			}
			
		/* 重点部位start =============================================================== */
			function toogleAllKeyParts() {
				var pList = $('#allFacilityFirstList li');
			   	var $select = $('#allFacilityFirst');
			  	if($select.data("checked")) {
					$select.data("checked", false).removeClass().addClass('allFacility');
		  		} else {
			  		$select.data("checked", true).removeClass().addClass('allFacilityOn');
			  	}
			  	var allFacilityChecked = $select.data("checked");
			  	for(var i = 0; i < pList.length; i++ ) {
				   var p = pList[i];
				   var pChecked = $(p).data("checked");
				   if(allFacilityChecked) {
					   if(!pChecked) {
						   p.click();
					   }
				   } else {
					   if(pChecked) {
						   p.click();
					   }
				   }
			   }
			}
			function tooglePartsFacility() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				var $allFacilityFirst = $("#allFacilityFirst");
				if(checked) {
					selectedKeyFacilities.delete(type);
					layer.getSource().clear();
					StationYardTwoLayer.getSource().clear();
					$('#map-popup-closer').click();
					$allFacilityFirst.data("checked", false);
					$allFacilityFirst.data("checkedNum", $allFacilityFirst.data("checkedNum") - 1);
					$allFacilityFirst.removeClass().addClass('allFacility');
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
					$allFacilityFirst.data("checkedNum", $allFacilityFirst.data("checkedNum") + 1);
					if($allFacilityFirst.data("checkedNum") === $('#allFacilityFirstList').children().length) {
						$('#allFacilityFirst').data("checked", true).removeClass().addClass('allFacilityOn');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFunc[type].call(this);
					}
				}
			}
			
			
			
			
			
			/* 隐患点 GIF */
			function tooglePartsFacilityDanger() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				var $allFacilityFirst = $("#allFacilityFirst");
				if(checked) {
					for(var i = 0; i<overlaysDanger.length;i++){
						map.removeOverlay(overlaysDanger[i])
					}
					selectedKeyFacilities.delete(type);
					layer.getSource().clear();
					StationYardTwoLayer.getSource().clear();
					$('#map-popup-closer').click();
					$allFacilityFirst.data("checked", false);
					$allFacilityFirst.data("checkedNum", $allFacilityFirst.data("checkedNum") - 1);
					$allFacilityFirst.removeClass().addClass('allFacility');
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
					$allFacilityFirst.data("checkedNum", $allFacilityFirst.data("checkedNum") + 1);
					if($allFacilityFirst.data("checkedNum") === $('#allFacilityFirstList').children().length) {
						$('#allFacilityFirst').data("checked", true).removeClass().addClass('allFacilityOn');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFunc[type].call(this);
					}
				}
			}
			
			
			/* 隧道  有样式的线段 */
			function tooglePartsFacilityTunnle() {
				var $target = $(event.target);
				var checked = $target.data('checked');
				var $allFacilityFirst = $("#allFacilityFirst");
				if(checked) {
					BJTunnelLayer.setVisible(false);
					StationYardTwoLayer.getSource().clear();
					$('#map-popup-closer').click();
					$allFacilityFirst.data("checked", false);
					$allFacilityFirst.data("checkedNum", $allFacilityFirst.data("checkedNum") - 1);
					$allFacilityFirst.removeClass().addClass('allFacility');
				} else {
					// 如果是未选中
					BJTunnelLayer.setVisible(true);
					$allFacilityFirst.data("checkedNum", $allFacilityFirst.data("checkedNum") + 1);
					if($allFacilityFirst.data("checkedNum") === $('#allFacilityFirstList').children().length) {
						$('#allFacilityFirst').data("checked", true).removeClass().addClass('allFacilityOn');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				$.get('map/getBJTunnel', {
					orgId : orgId
				}, function(data) {
					if(Array.isArray(data)) {
						var headerAndTailsArr = [];
						var features = data.filter(function(item) {
							if(item.router && item.router.length >= 2) {
								return true;
							}
							return false
						}).map(function(item) {
							var router = item.router;
							var h = new ol.Feature({
								geometry: new ol.geom.Point(router[0]),
								type: "tunnel",
								id: item.id,
								url: "map/getTunnelPopWin"
							});
							var t = new ol.Feature({
								geometry: new ol.geom.Point(router[router.length - 1]),
								type: "tunnel",
								id: item.id,
								url: "map/getTunnelPopWin"
							});
							h.setStyle(junctionStyle);
							t.setStyle(junctionStyle);
							headerAndTailsArr.push(h);
							headerAndTailsArr.push(t);
							var f = new ol.Feature({
								geometry: new ol.geom.LineString(router, 'XY'),
								type: "tunnel",
								id: item.id,
								url: "map/getTunnelPopWin"
							});
							f.setStyle(tunnleLineStyle);
							return f;
						});
						
						BJTunnelLayer.getSource().addFeatures(features);
						BJTunnelLayer.getSource().addFeatures(headerAndTailsArr);
					}
				})
			}
		/*重点部位end =============================================================== */
		
		
		/* 联防点位start =============================================================== */
			function toogleAllKeyPartsSecond() {
				var pList = $('#allFacilitySecondList li');
			   	var $select = $('#allFacilitySecond');
			  	if($select.data("checked")) {
					$select.data("checked", false).removeClass().addClass('allFacility');
		  		} else {
			  		$select.data("checked", true).removeClass().addClass('allFacilityOn');
			  	}
			  	var allFacilityChecked = $select.data("checked");
			  	for(var i = 0; i < pList.length; i++ ) {
				   var p = pList[i];
				   var pChecked = $(p).data("checked");
				   if(allFacilityChecked) {
					   if(!pChecked) {
						   p.click();
					   }
				   } else {
					   if(pChecked) {
						   p.click();
					   }
				   }
			   }
			}
			function tooglePartsFacilitySecond() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				var $allFacilitySecond = $("#allFacilitySecond");
				if(checked) {
					selectedKeyFacilities.delete(type);
					layer.getSource().clear();
					$('#map-popup-closer').click();
					$allFacilitySecond.data("checked", false);
					$allFacilitySecond.data("checkedNum", $allFacilitySecond.data("checkedNum") - 1);
					$allFacilitySecond.removeClass().addClass('allFacility');
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
					$allFacilitySecond.data("checkedNum", $allFacilitySecond.data("checkedNum") + 1);
					if($allFacilitySecond.data("checkedNum") === $('#allFacilitySecondList').children().length) {
						$('#allFacilitySecond').data("checked", true).removeClass().addClass('allFacilityOn');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFunc[type].call(this);
					}
				}
			}
			
			/* 警示柱 */
			function tooglePartsFacilitySecondBroadcast() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				var $allFacilitySecond = $("#allFacilitySecond");
				if(checked) {
					 for (var i = 0; i<overlays.length;i++){
						console.log(overlays[i]);
						map.removeOverlay(overlays[i])
					} 
					layer.getSource().clear();
					selectedKeyFacilities.delete(type);
					$('#map-popup-closer').click();
					$allFacilitySecond.data("checked", false);
					$allFacilitySecond.data("checkedNum", $allFacilitySecond.data("checkedNum") - 1);
					$allFacilitySecond.removeClass().addClass('allFacility');
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
					$allFacilitySecond.data("checkedNum", $allFacilitySecond.data("checkedNum") + 1);
					if($allFacilitySecond.data("checkedNum") === $('#allFacilitySecondList').children().length) {
						$('#allFacilitySecond').data("checked", true).removeClass().addClass('allFacilityOn');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFunc[type].call(this);
					}
				}
			}
			
			
		/*联防点位end =============================================================== */	
		
		
		
		/* 周边场所start =============================================================== */
			function toogleAllKeyPartsThird() {
				 $("#keyPlaceNumber").html('');
				 $('#allFacilityThird').data('checkedNum',0);
				 $.ajax({
		    		url:"${ctx}/exhibition/map/getOrgInfo?type=0&orgId="+adminDivisionOrgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: true,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    		},
		    		success:function(data){
	    				 var tempHtml="";
	    				 tempHtml+= 
	    					 "<ul id='allFacilityThirdList' style='float:left;'>"+
	    					 		"<li data-type='residence' onclick='tooglePartsFacilityThird()'>住宅<span>"+data.peripheral01Type+"</span></li>"+
				 					"<li data-type='company' onclick='tooglePartsFacilityThird()'>单位<span>"+data.peripheral02Type+"</span></li>"+
									"<li data-type='market' onclick='tooglePartsFacilityThird()' >市场<span>"+data.peripheral03Type+"</span></li>"+
									"<li data-type='school' onclick='tooglePartsFacilityThird()'>学校<span>"+data.peripheral04Type+"</span></li>"+ 
									"<li data-type='mining' onclick='tooglePartsFacilityThird()'>工矿<span>"+data.peripheral05Type+"</span></li>"+
									"<li data-type='constructionSite' onclick='tooglePartsFacilityThird()'>工地<span>"+data.peripheral06Type+"</span></li>"+
									"<li data-type='leisureTime' onclick='tooglePartsFacilityThird()'>休闲<span>"+data.peripheral07Type+"</span></li>"+
			   					"</ul>"+
			   					 "<div onclick='more()' class='more'>更多</div>"+
								    "<div id='more'>"+
								    	"<ul>"+
										    "<li data-type='commercial' onclick='tooglePartsFacilityThirdMore()'>商厦<span>"+data.peripheral08Type+"</span></li>"+
									    	"<li data-type='roomAndBoard' onclick='tooglePartsFacilityThirdMore()'>吃住<span>"+data.peripheral09Type+"</span></li>"+
											"<li data-type='breed' onclick='tooglePartsFacilityThirdMore()'>养殖<span>"+data.peripheral10Type+"</span></li>"+
											"<li data-type='plant' onclick='tooglePartsFacilityThirdMore()'>种植(大棚)<span>"+data.peripheral11Type+"</span></li>"+
											"<li data-type='gasStation' onclick='tooglePartsFacilityThirdMore()'>加油站<span>"+data.peripheral12Type+"</span></li>"+
											"<li data-type='riversAndLakes' onclick='tooglePartsFacilityThirdMore()'>河湖(鱼塘)<span>"+data.peripheral13Type+"</span></li>"+
								    	"</ul>"+
				    					"<div class='closeMore' onclick='closeMore()'>"+"</div>"+
		   						"</div>" ;
		    			$("#keyPlace").html(tempHtml);  
		    			var $slectKey = $('#allFacilitySecondTwo');
						var pList = $('#allFacilityThirdList li');
					   	var $select = $('#allFacilityThird');
					  	if($select.data("checked")) {
							$select.data("checked", false).removeClass().addClass('allFacilityThird');
							$slectKey.data("checked", false).removeClass().addClass('allFacilitySecond_two');
							RoomAndBoardLayer.setVisible(false);
							RiversAndLakesLayer.setVisible(false);    
							GasStationLayer.setVisible(false);      
							PolicePtationSLayer.setVisible(false);
							PoliceStationLayer.setVisible(false);
							FullTimeMemberLayer.setVisible(false);
							ResidenceLayer.setVisible(false);
							CompanyLayer.setVisible(false);
							MarketLayer.setVisible(false);
							MiningLayer.setVisible(false);
							ConstructionSiteLayer.setVisible(false);
							LeisureTimeLayer.setVisible(false);
							CommercialLayer.setVisible(false);
							RoomAndBoardLayer.setVisible(false);
							BreedLayer.setVisible(false);
							PlantLayer.setVisible(false);
							PlantLayer.setVisible(false);
							BJSchoolLayer.setVisible(false);
				  		} else {
					  		$select.data("checked", true).removeClass().addClass('allFacilityOnThird');
					  		$slectKey.data("checked", false).removeClass().addClass('allFacilitySecond_two');
					  	}
					  	var allFacilityChecked = $select.data("checked");
					  	for(var i = 0; i < pList.length; i++ ) {
						   var ps = pList[i];
						   var pChecked = $(ps).data("checked");
						   if(allFacilityChecked) {
							   if(!pChecked) {
								   ps.click();
							   }
						   } else {
							   if(pChecked) {
								   ps.click();
							   }
						   }
					   }
						
		    	 	}
				}) 
			}
			/* 周边场所 */
			function tooglePartsFacilityThird() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				var $allFacilityThird = $("#allFacilityThird");
				if(checked) {
					selectedKeyFacilities.delete(type);
					layer.getSource().clear();
					$('#map-popup-closer').click();
					$allFacilityThird.data("checked", false);
					$allFacilityThird.data("checkedNum", $allFacilityThird.data("checkedNum") - 1);
					$allFacilityThird.removeClass().addClass('allFacilityThird');
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
					$allFacilityThird.data("checkedNum", $allFacilityThird.data("checkedNum") + 1);
					if($allFacilityThird.data("checkedNum") === $('#allFacilityThirdList').children().length) {
						$('#allFacilityThird').data("checked", true).removeClass().addClass('allFacilityOnThird');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFunc[type].call(this);
					}
				}
			}
			/* 周边场所更多 */
			function tooglePartsFacilityThirdMore() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				if(checked) {
					selectedKeyFacilities.delete(type);
					layer.getSource().clear();
					$('#map-popup-closer').click();
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFunc[type].call(this);
					}
				}
			}
			/* 重点周边场所 */
			function tooglePartsFacilityThirdKey() {
				var $target = $(event.target);
				var type = $target.data('type');
				var layer = findLayerByType(type);
				var checked = $target.data('checked');
				var $allFacilityThird = $("#allFacilitySecondTwo");
				if(checked) {
					selectedKeyFacilities.delete(type);
					layer.getSource().clear();
					$('#map-popup-closer').click();
					$allFacilityThird.data("checked", false);
					$allFacilityThird.data("checkedNum", $allFacilityThird.data("checkedNum") - 1);
					$allFacilityThird.removeClass().addClass('allFacilitySecond_two');
				} else {
					// 如果是未选中
					selectedKeyFacilities.add(type);
					$allFacilityThird.data("checkedNum", $allFacilityThird.data("checkedNum") + 1);
					if($allFacilityThird.data("checkedNum") === $('#allFacilityThirdListKey').children().length) {
						$('#allFacilitySecondTwo').data("checked", true).removeClass().addClass('keyplace');
					}
				}
				$target.toggleClass('on');
				$target.data('checked', !checked);
				/* 根据type找到对应的 layer */
				if(layer) {
					layer.setVisible(!checked);
					if(layer.getVisible()) {
						loadKeyFacilitiesFuncKey[type].call(this);
					}
				}
			}
		/*周边场所end =============================================================== */	
		//================================== 行政区域 start==================================
		 	function adminDivision() {
				$('#showRailInput').val('全部铁路')
				$('.ol-popup-closer').click();
		 		BJBroadcastLayer.setVisible(false);
		 		BJPropagandaLayer.setVisible(false);
		 		BJPlaceDangerousLayer.setVisible(false);
		 		BJGuardStationLayer.setVisible(false);
		 		BJSchoolLayer.setVisible(false);
		 		BJBridgeLayer.setVisible(false);
		 		BJTunnelLayer.setVisible(false);
		 		BJJunctionLayer.setVisible(false);
		 		BJPartStationLayer.setVisible(false);
		 		BJKeyplaceLayer.setVisible(false);
		 		HiddenTrajectionLayer.setVisible(false);
		 		StationYardLayer.setVisible(false);
		 		MonitorInfoLayer.setVisible(false);
		 		PatrolDangerLayer.setVisible(false);
		 		AppLayer.setVisible(false);
		 		showAllRail();
				var element = event.target;
				var $el = $(element);
		 		var $orgId = $el.data('orgId');
		 		var $html = $el.html();
		 		$('#exAdministrativedivisionInput').val($html);
				adminDivisionOrgId = $orgId ;
		 		$('#showAllRail').data('checkedNum',0);
		 		if($el.hasClass('all-bj')) {
					if($el.parent('li').hasClass('on')) {
						$el.parent('li').removeClass().siblings('li.on').removeClass();
						BJBorderLayer.setVisible(false);
					} else {
						$el.parent('li').addClass('on').siblings('li').addClass('on');
						BJBorderLayer.setVisible(true);
					}
				} else {
					$el.parent('li').addClass('on').siblings('li.on').removeClass();
				}
				if(element.dataset.orgId != orgId) {
					orgId = element.dataset.orgId;
					setOrgMap(orgId);
					loadRailData(orgId, function() { // 根据orgId加载铁路信息
		                BJRailRouteBaseLayer.getSource().clear();
		                BJRailRouteLayer.getSource().clear();
	                    showAllRail();
	                    BJBorderLayer.setVisible(true);
					});
					selectedKeyFacilities.forEach(function(keyFacility) {
						loadKeyFacilitiesFunc[keyFacility].call(this);
					}, this);
				}
		 		/* 区域 铁路详细信息 */
		 		$.ajax({
		    		url:"${ctx}/exhibition/map/getOrgInfosel?id="+adminDivisionOrgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: true,
		    		error: ajaxErrorCb,
		    		success:function(data){
	    				 var tempHtml="";
	    				 tempHtml+= "<li>共计:<span>"+data.railCount+"</span>条</li>"+
						 			"<li>总里程:</li>"+
									"<li><span>"+data.orgRailLen+"</span></li>"+
						 			"<li>高速铁路:<span>"+data.Pub01Data+"</span>条</li>"+
						 			"<li>重载专线:<span>"+data.Pub02Data+"</span>条</li>"+
						 			"<li>普通干线:<span>"+data.Pub03Data+"</span>条</li>"+
						 			"<li>其它线路:<span>"+data.Pub05Data+"</span>条</li>"+
						 			"<li class='selected'>涉路行政区划</li>"+
									"<li>区:<span id='allBj'>"+data.pub_org+"</span>个</li>"+
									"<li>街乡镇:<span>"+data.jxz+"</span>个</li>";
									/* "<li>社区村:<span>"+2222+"</span>个</li>" */
		    			$(".administrativedivision_PwContent").html(tempHtml); 
		    		}
	    		});
		 		
		 		/* 重点部位 详细信息 */
		 		var url = "${ctx}/exhibition/map/getOrgInfosel?id="+adminDivisionOrgId;
				$.ajax({
		    		url:url,
		    		type:'POST',
		    		dataType:'json',
		    		error: ajaxErrorCb,
		    		success:function(data){
		    			/* 重点部位 总数量 */
		    			var keyPartsSize = 
		    				data.base_info_keyperson + 
		    				data.base_info_station_yard + 
		    				data.bridgeCulvertCount + 
		    				data.base_info_part_junction + 
		    				data.base_info_part_tunnel+
		    				data.base_info_part_station + 
		    				data.base_info_hidden_trajection;
		    			/* 联防点位总数量 */
		    			var jointDefensepartSize = 
		    				data.base_info_guard_station +
		    				data.base_info_propaganda +
		    				data.base_info_broadcast +
		    				data.video_monitor_info+
		    				data.base_info_place_school;
		    			/* 隐患点位总数量 */
		    			var dangerNumberAll = 
		    				data.danger01Type +
		    				data.danger02Type +
		    				data.danger03Type +
		    				data.danger04Type+
		    				data.danger05Type+
		    				data.danger06Type;
		    			/* 周边场所 */
		    			var placeAllNumber = 
		    				data.peripheral01Type+
		    				data.peripheral02Type+
		    				data.peripheral03Type+
		    				data.peripheral04Type+
		    				data.peripheral05Type+
		    				data.peripheral06Type+
		    				data.peripheral07Type+
		    				data.peripheral08Type+
		    				data.peripheral09Type+
		    				data.peripheral10Type+
		    				data.peripheral11Type+
		    				data.peripheral12Type+
		    				data.peripheral13Type;
	    				 var tempHtml="";
	    				 tempHtml+=
	    					 '<div class="keyParts">'+
	    					 '<div class="keyParts_header">'+
								'<div  class="allFacility" id="allFacilityFirst" data-checked-num="0" onclick="toogleAllKeyParts()">重点部位<span>'+keyPartsSize+'</span></div>'+
								'<div data-type="keyPerson" class="allFacilityPerson" onclick="tooglePartsFacility()">重点人<span>'+data.base_info_keyperson+'</span></div>'+
							 '</div>'+
								 "<ul class='facilitiesTwo' id='allFacilityFirstList' style='float:left;'>"+
									/* "<li data-type='stationYard' onclick='tooglePartsFacility()'>站场(<span>"+data.base_info_station_yard+"</span>)</li>"+ */
									"<li data-type='bridge' onclick='tooglePartsFacility()'>桥涵<span>"+data.bridgeCulvertCount+"</span></li>"+
									"<li data-type='junction' onclick='tooglePartsFacility()'>道口<span>"+data.base_info_part_junction+"</span></li>"+
									"<li data-type='tunnel' onclick='tooglePartsFacilityTunnle()'>隧道<span>"+data.base_info_part_tunnel+"</span></li>"+
								    "<li data-type='partStation' onclick='tooglePartsFacility()'>车站<span>"+data.base_info_part_station+"</span></li>"+ 
									"<li data-type='hiddenTrajection' onclick='tooglePartsFacility();'>易穿行<span>"+data.base_info_hidden_trajection+"</span></li>"+
									"<li data-type='hiddenDanger' id='danger' onclick='tooglePartsFacilityDanger();' style='width:110px;'>隐患点<span id='dangerNumber'>"+dangerNumberAll+"</span>"+
									"</li>"+
									"<input  id='keyPartsInput'>"+
									"<div id='keyPartsList' >"+
										"<div data-type='全部' data-number='"+dangerNumberAll+"'>全部</div>"+
										"<div data-type='火灾' data-number='"+data.danger01Type+"'>火灾</div>"+ 
										"<div data-type='爆炸' data-number='"+data.danger02Type+"'>爆炸</div>"+
										"<div data-type='侵限' data-number='"+data.danger03Type+"'>侵限</div>"+
										"<div data-type='危树' data-number='"+data.danger04Type+"' data-type-num='data.base_info_hidden_trajection'>危树</div>"+
										"<div data-type='损坏' data-number='"+data.danger05Type+"'>损坏</div>"+
										"<div data-type='危建' data-number='"+data.danger06Type+"'>危建</div>"+
									"</div>"+
								"</ul>"+ 
							'</div>'+ 
								
							'<div class="jointDefensepart">'+
				 				'<div class="jointDefensepart_header">'+
									'<div class="allFacility" id="allFacilitySecond" data-checked-num="0" onclick="toogleAllKeyPartsSecond()">联防点位<span>'+jointDefensepartSize+'</span></div>'+
								'</div>'+
									"<ul class='facilitiesOne' id='allFacilitySecondList'>"+
									 	"<li data-type='guardStation' onclick='tooglePartsFacilitySecond();'>工作站<span>"+data.base_info_guard_station+"</span></li>"+
										"<li data-type='propaganda' onclick='tooglePartsFacilitySecond();'>宣传点<span>"+data.base_info_propaganda+"</span></li>"+
										"<li data-type='broadcast' onclick='tooglePartsFacilitySecondBroadcast();'>警示柱<span>"+data.base_info_broadcast+"</span></li>"+
										"<li data-type='monitor' onclick='tooglePartsFacilitySecond()'>摄像头<span>"+data.video_monitor_info+"</span></li>"+
										"<li >派出所<span></span></li>"+
										"<li >警务站<span></span></li>"+
										"<li>专职队员<span id='userNum'></span></li>"+
										"<li data-type='user' onclick='tooglePartsFacilitySecond()'>在线干部</li>"+ 
									"</ul>"+ 
								'</div>'+ 
							
							'<div class="allFacilitySecondKey">'+
				 				'<div class="allFacilitySecondKey_header">'+
					 				'<div data-checked-num="0" class="allFacilityThird" id="allFacilityThird" onclick="toogleAllKeyPartsThird()">周边场所<span>'+placeAllNumber+'</span>(</div>'+
									'<div class="allFacilitySecond_two" id="allFacilitySecondTwo" data-checked-num="0" onclick="keyplace()">重点<span id="keyPlaceNumber"></span>&nbsp;)</div>'+
								'</div>'+
								'<div id="keyPlace">'+
									 "<ul id='allFacilityThirdList' style='float:left;'>"+
										    "<li data-type='residence' onclick='tooglePartsFacilityThird()'>住宅<span>"+data.peripheral01Type+"</span></li>"+
											"<li data-type='company' onclick='tooglePartsFacilityThird()'>单位<span>"+data.peripheral02Type+"</span></li>"+
											"<li data-type='market' onclick='tooglePartsFacilityThird()' >市场<span>"+data.peripheral03Type+"</span></li>"+
											"<li data-type='school' onclick='tooglePartsFacilityThird()'>学校<span>"+data.peripheral04Type+"</span></li>"+ 
											"<li data-type='mining' onclick='tooglePartsFacilityThird()'>工矿<span>"+data.peripheral05Type+"</span></li>"+
											"<li data-type='constructionSite' onclick='tooglePartsFacilityThird()'>工地<span>"+data.peripheral06Type+"</span></li>"+
											"<li data-type='leisureTime' onclick='tooglePartsFacilityThird()'>休闲<span>"+data.peripheral07Type+"</span></li>"+
									"</ul>"+ 
								 "<div onclick='more()' class='more'>更多</div>"+
								    "<div id='more'>"+
								    	"<ul>"+
										    "<li data-type='commercial' onclick='tooglePartsFacilityThirdMore()'>商厦<span>"+data.peripheral08Type+"</span></li>"+
									    	"<li data-type='roomAndBoard' onclick='tooglePartsFacilityThirdMore()'>吃住<span>"+data.peripheral09Type+"</span></li>"+
											"<li data-type='breed' onclick='tooglePartsFacilityThirdMore()'>养殖<span>"+data.peripheral10Type+"</span></li>"+
											"<li data-type='plant' onclick='tooglePartsFacilityThirdMore()'>种植(大棚)<span>"+data.peripheral11Type+"</span></li>"+
											"<li data-type='gasStation' onclick='tooglePartsFacilityThirdMore()'>加油站<span>"+data.peripheral12Type+"</span></li>"+
											"<li data-type='riversAndLakes' onclick='tooglePartsFacilityThirdMore()'>河湖(鱼塘)<span>"+data.peripheral13Type+"</span></li>"+
								    	"</ul>"+
								    	"<div class='closeMore' onclick='closeMore()'>"+"</div>"+
								   "</div>"+ 
								'</div>'+
							'</div>'
			 				$(".ex_map_content_Keyfacilities_content_content").html(tempHtml);
 					    	var keyPartsInput=document.getElementById('keyPartsInput');  
 					    	var keyPartsList=document.getElementById('keyPartsList');  
    					    var keyPartstext=$('#keyPartsList div:first-child').text();
    					    var $selectDanger = $('#danger');
    					    var dangerNumber = $('#dangerNumber');
    					    document.getElementById("keyPartsInput").value=keyPartstext; 
    					    var keyPartsLists=$('#keyPartsList div');  
    					    keyPartsInput.onfocus=function(){  
    					    	keyPartsList.style.display='block';  
    					    };
    					    keyPartsInput.onblur=function(){  
    					        setTimeout(function(){  
    					        	keyPartsList.style.display='none';  
    					        },200)  
    					    };
    					    for(var i=0; i<keyPartsLists.length;i++){  
    					    	keyPartsLists[i].onclick=function(){ 
									$selectDanger.data("checked", false).removeClass('on');
									PatrolDangerLayer.getSource().clear();
									FireLayer.getSource().clear();
									BlastLayer.getSource().clear();
									InvasionLayer.getSource().clear();
									DangerTreeLayer.getSource().clear();
									DamageLayer.getSource().clear();
									WeiJianLayer.getSource().clear();
									keyPartsInput.value=this.innerText; 
									var dangerText = this.innerText;
									$("#danger").data('type',dangerText);
									var $target = $(event.target);
									var $targetNumber=$target.data("number");
									dangerNumber.html($targetNumber);	
    					        }  
    					    }    
		    			}
    				});
				}
			//================================== 行政区域 end ==================================
				//setInterval("getUserNum()", 10000);
				function getUserNum() {
					$.ajax({
			    		url:"${ctx}/exhibition/map/getUserInfo",
			    		type:'GET',
			    		dataType:'json',
			    		async: true,
			    		error: function(XMLHttpRequest, textStatus, errorThrown) {
			    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			    			console.log("异常情况:["+textStatus+"]");
			    			},
			    		success:function(data){
		    				$("#userNum").html(data);
			    		}
					})
				}
				//隐患提示
				dangerLayer.setVisible(true);
				//setInterval("getMessage()", 10000);
				function getMessage() {
					$.ajax({
						url : "${ctx}/patrol/findDangers",
						type : "POST",
						dataType : 'JSON',
						error: ajaxErrorCb,
						success : function(data) {
							if(data && data.length) {
								dangerLayer.getSource().clear();
								$('#map-popup-closer').click(function(){
									$.ajax({
										url : "${ctx}/exhibition/map/dangerUpdateType",
										type : "POST",
										dataType : 'JSON',
										error: ajaxErrorCb,
										success : function(data) {
											//dangerLayer.getSource().clear();
											$('.ex_map_dangerBtnContent').css('background','url(${ctx}/resources/image/exhibition/map/ex_map_dangerBtnContent.png)');
										}
									});
								});
								var features = data.map(function(item) {
										var f = new ol.Feature({
											geometry: new ol.geom.Point([(+item.lng), (+item.lat)]),
											type: "dangerLocation",
											lnglat: item.lng + "," + item.lat,
											id: item.id,
											url: "map/getPatrolDangerPopWin"
											
										});
										
										var pos = ol.proj.fromLonLat([(+item.lng), (+item.lat)]);
										var markerDom=document.createElement('div')
										markerDom.id='item.id' 
										$('#bjRailMap').append(markerDom)
										var markerDanger = new ol.Overlay({
										    position: pos,
										    positioning: 'center-center',
										    element: markerDom,
										    stopEvent: false
										});
										$(markerDanger.getElement()).addClass("mark")
										map.addOverlay(markerDanger)
										overlaysDanger.push(markerDanger)
										markerDanger.setPosition([(+item.lng), (+item.lat)])
										f.setStyle(dangerStyle);
										return f;
								});
								dangerLayer.getSource().addFeatures(features);
								$('.ex_map_dangerBtnContent').css('background','url(${ctx}/resources/image/exhibition/map/svg/danger.gif) no-repeat');
							}
							
						}
					});
				}
				function more(){
					$('#more').show();
				}
				function closeMore(){
					$('#more').hide();
				}
				function closePwAll(){
					$('.ol-popup-closer').click();
				}
				function panagementPw(){
					$('.management').show();
				}
				function closeManagementPw(){
					$('.management').hide();
				}
				function colseMapPw(){
					$('.ol-popup-closer').click();
				}
				var a = $('.pwAll_firstUl li');
				console.log('1111111111111111111111111111111',a);
				var b = $('#aaaa');
				console.log('1111111111111111111111111111111',b);
				
				
				/* 战场  全部显示 */
				function partStatYard(){
					$.ajax({
						url : "map/getBJPartStationYardStationInfo",
						type : "GET",
						dataType : 'JSON',
						error: ajaxErrorCb,
						success : function(data) {
							StationYardTwoLayer.getSource().clear();
							var features = data.filter(function(item) {
								return item.stationYardRange;
							}).map(function(item) {
								console.log('item=========================狗屎',item)
								var lines = JSON.parse(item.stationYardRange);
								var f = createPolygonFeature(lines, item.stationYardId, item.stationYardName,'stationTwoYard');
								f.setStyle(stationYardTwoStyle);
								return f;
							});
							StationYardTwoLayer.getSource().addFeatures(features);
						}
					});
				}
				
		</script>
	</body>
</html>