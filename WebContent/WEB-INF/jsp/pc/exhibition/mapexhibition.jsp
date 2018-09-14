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
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<!--  360浏览器专用 -->
		<meta name="renderer" content="ie-comp"/>
		<%@ include file="tagexlib.jsp"%>
		<link rel="shortcut icon" href="${ctx}/resources/image/project.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/map/map.css">
		<link rel="stylesheet" href="${ctx}/resources/openlayers4.5.0/ol.css" type="text/css">
		<!-- 聊天 -->
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/chat.css" />
		<!-- 底部菜单跳转后台图标 -->
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/animite/mapanimate.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/paging.css" />
		<!-- 自定义滚动条 -->
		<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/exhibition/jquery.mCustomScrollbar.css" />
		<script type="text/javascript" src="${ctx}/resources/jquery/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/jquery-ui-1.10.4.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/jquery.mCustomScrollbar.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/jquery.mousewheel.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/paging.js"></script>
		<%-- <script type="text/javascript" src="${ctx}/resources/js/exhibition/ocxfun.js"></script> --%>
		<!-- 在线队员======================================================= -->
		<script type="text/javascript" src="${ctx}/resources/RTCMultiConnection/adapter.js"></script>
		<script type="text/javascript" src="${ctx}/resources/RTCMultiConnection/FileBufferReader.js"></script>
		<script type="text/javascript" src="${ctx}/resources/RTCMultiConnection/getHTMLMediaElement.js"></script>
		<script type="text/javascript" src="${ctx}/resources/RTCMultiConnection/socket.io.js"></script>
		<script type="text/javascript" src="${ctx}/resources/RTCMultiConnection/RTCMultiConnection.js"></script>
		<script type="text/javascript" src="${ctx}/resources/RTCMultiConnection/myTool.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/chat.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/indexDb.js"></script>
	</head>
	<body>
		<!-- 地图内容 -->
		<div class="ex_main" id="bjRailMapB" unselectable="on" onselectstart="return false;" style="-moz-user-select:none;">
			<!-- <div class="ex_map_bg"></div> -->
			<!-- ex_main 外侧边框 -->
			<!-- <div class="ex_map_left_header"></div>
			<div class="ex_map_left_bottom"></div>
			<div class="ex_map_right_header"></div>
			<div class="ex_map_right_bottom"></div> -->
			<!-- <div class="ex_main_left_border_one"></div>
			<div class="ex_main_left_border_two"></div>
			<div class="ex_main_right_border_one"></div>
			<div class="ex_main_right_border_two"></div>
			<div class="ex_main_leftdw_border_one"></div>
			<div class="ex_main_leftdw_border_two"></div>
			<div class="ex_main_rightdw_border_one"></div>
			<div class="ex_main_rightdw_border_two"></div> -->
			<!-- 左上侧图标logo -->
			<div class="ex_main_leftheader"></div>
			<!-- 右上侧 菜单 -->
	     	<div class="ex_main_rightheader"> 
	     		<sec:authorize url="/baseInfo/dataRetrievalQueryList">
					<div onclick="dataSearch()" class="ex_main_rightheader_one">数据检索</div>
				</sec:authorize>
				<sec:authorize url="/baseInfo/PatrolManagementTaskQueryList">
					<div onclick="patroTeam()" class="ex_main_rightheader_two">巡防管理</div>
				</sec:authorize>
				<sec:authorize url="/pubMapRailData/CalculationLnglat">
					<div onclick="railLine()" class="ex_main_rightheader_three">线路定位</div>
				</sec:authorize>
				<!-- 退出键-->
				<div onclick="exReturn()" class="ex_main_rightheader_four"></div>
    		</div>
    		<!-- 右上侧 菜单 ----测量-->
    		<div class="ex_main_rightheader_measure">
    			<div class="ex_main_rightheader_measure_ranging" onclick="emrmRanging()">距离测量</div>
    			<div class="ex_main_rightheader_measure_surface" onclick="emrmSurface()">面积测量</div>
    		</div>
		 	<!-- 退出键-->
		   <!--  <div class="ex_main_exit"  onclick="exReturn()"></div> -->
		    <!-- 退出键提示框 -->
			<div class="ex_main_exit_pw"></div>
		    <!-- 左侧菜单栏 （手机上报隐患....） -->
		    <div class="ex_main_left_screenbtn">
		    	<div class="ex_main_left_dangerbtn" onclick="openChat()"></div>
		    	<div class="ex_main_left_dangerbtn" onclick="closeChat()"></div>
		    	<div class="ex_main_left_dangerbtn"></div>
		    </div>
		    <!-- 右侧筛选框展开按钮 -->
	     	<div class="ex_main_right_screenbtn"></div>
		    <!-- 筛选框 内容-->
		    <div class="ex_main_right_screen_content">
		    	<div class="exmrsc_content">
		    		<div class="exmrsc_content_header">
		    			<!-- 北京区域 -->
		    			<div class="exmrsc_content_header_left">
	    					<div id="exRegionInput"></div>
	    					<!-- 北京市 下拉框 -->
		    				<ul  id="exRegionOpition" class="ex_main_region_list">
		    				</ul>
	    					<div id="orgStatisticCount" class="exmrsc_content_header_left_content">
		    					<ul class="exmrsc_content_header_left_content_railcontent"></ul>
	    			 			<ul class="exmrsc_content_header_left_content_region"></ul>
    						</div>
		    			</div>
		    			<!-- 北京铁路线信息 -->
		    			<div class="exmrsc_content_header_right">
		    				<div class="exmrsc_content_header_right_header">
			    				<div id="exRailInput" class="ex_rail_input"></div>
			    				<div id="exShowAllRail" class="ex_main_show_all_rail" onclick="showAllRail();"></div>
		    				</div>
		    				<ul id="exRailOpition"  class="ex_main_rail_list"></ul>
	    					<input type="text" class="ex_main_rail_search" placeholder="请输入您要查找的铁路线">
	    					<ul class="ex_main_railList"></ul>
		    			</div>
		    		</div>
		    		<div class="ex_main_right_screen_content_content_bottom">
		    			<div class="exmrsc_frist"></div>
		    			<div class="exmrsc_second"></div>
		    			<div class="exmrsc_third">
		    				<div class="exmrsc_third_header">
		    					<div id="exmrscThirdHeaderPlace" class="exmrsc_third_header_place" onclick='setPerPlaceSelectAll()'>周边场所</div><span id='exmrscThirdHeaderPlaceCount' class='exmrsc_thirdHeader_placeCount'></span><span>(</span>
		    					<div id="exmrscThirdHeaderKeyplace" class="exmrsc_third_header_Keyplace" onclick='setPerImportPlaceSelect()'>重点</div><span  id='exmrscThirdHeaderKeyplaceCount' class='exmrsc_thirdHeader_keyplace'></span><span>)</span>
		    				</div>
		    				<div class="exmrsc_third_content">
		    					<ul></ul>
		    					<div class="exmrsc_thirdc_more_content">
			    					<div class='exmrsc_thirdc_more'>更多</div>
								    <div class='exmrsc_thirdc_morec'>
								    	<ul></ul>
								   </div>
							   </div>
		    				</div>
		    			</div>
		    		</div>
	    		</div>
		    	<div class="ex_main_right_close_screenbtn"></div>
			</div>
			<!-- 底部菜单 -->
			<div class="ex_main_bottom_menu">
				<div class="ex_main_bottom_menu_btn"></div>
				<div class="ex_main_bottom_menu_content">
					<ul id="exMainBottomMenuUl"></ul>
				</div>
			</div>
			<!-- ex_main 全部弹出层 -->
			<!-- 右上侧menu 弹出层 -->
			<!-- 数据检索   巡防管理   线路定位 -->
			<div class="ex_main_rightheader_pw1">
				<div class="ex_main_rightheader_closepw1"></div>
				<div class="ex_main_rightheader_pw1_header">
					<input type="text" id="keyword" placeholder="请输入您要查找的内容">
					<div class="ex_main_rightheader_pw1_btn" onclick="searchData();"></div>
				</div>
			</div>
			
			<!-- //巡防队伍 -->
			<div class="ex_main_rightheader_pw2">
			 	<!-- <button id="start-animation">Start Animation</button>
				<div class="ex_main_rightheader_closepw2"></div> -->
				<div class="ex_main_rightheader_text_one">巡防队伍</div>
				<div class="ex_main_rightheader_pw2_header">
					<input id="searchInput" type="text"  onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入您要查询的内容"/>
					<div class="search_btn" onclick="searchTp()" style="display:none;"></div>
				</div>
				<div class="ex_main_rightheader_pw2_content">
					<ul id="patrolTeam" style="color:#fff;text-indent:20px;"></ul>
				</div>
				<div id="patrolTeamPage" class="paging"></div>
				<div class="ex_main_rightheader_closepw2"></div>
			</div>
			
			<!-- //巡防队员 -->
			<div class="ex_main_rightheader_pw2_pep">
			<div class="ex_main_rightheader_text"></div>
				<div class="ex_main_rightheader_pw2_pep_header">
					 <input id="searchPepInput" type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入您要查询的内容"/>
					 <div class="search_btn" onclick="searchPep()" style="display:none;"></div>
				</div>
				<div class="ex_main_rightheader_pw2_pep_content">
					<ul id="patrolTeamPep" style="color:#fff;text-indent:20px;"></ul>
				</div>
				<div id="patrolTeammemberPage" class="paging"></div>
				<div class="ex_main_rightheader_closepw2_pep"></div>
			</div>
			
			<!-- 巡防轨迹 -->
			<div class="ex_main_rightheader_pw2_tjy">
			<div class="ex_main_rightheader_text_three"></div>
				<!-- <div class="ex_main_rightheader_pw2_tjy_header">
					<input type="text"/>
				</div> -->
				<div class="ex_main_rightheader_pw2_tjy_content">
					<ul id="patrolTeamTjy" style="color:#fff;text-indent:20px;"></ul>
				</div>
				<div id="patrolTjyPage" class="paging"></div>
				<div class="ex_main_rightheader_closepw2_tjy"></div>
			</div>
			
			<div class="ex_main_rightheader_pw3">
				<div class="ex_main_rightheader_closepw3"></div>
				<div class="ex_main_rightcontent_pw3">
					<div class="ex_main_rightcontent_pw3_left">
						<p>铁路线</p>
						<div class="ex_main_rightcontent_pw3_option">
							<input id="exmrpwInput"  class="ex_main_rightcontent_pw3_option_input" placeholder="铁路线选择"/>
							<ul id="exmrpwList">
							</ul>
						</div>
						<div class="ex_main_locationKm">
							<input type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" id="locationRailK"><span>K</span><input type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" id="locationRailM"><span>M</span>
						</div>
						<div class="map_locationKmBtn" onclick="railLocation()"></div>
					</div>
					<div class="ex_map_location_text"></div>
					<div class="ex_main_rightcontent_pw3_right">
						<p>地图定位</p>
						<div class="ex_main_rightcontent_pw3_right_input">
							<input id="GpsLng" type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入您要定位的经度"/>
							<input id="GpsLat" type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" placeholder="请输入您要定位的纬度"/>
						</div>
						<div class="map_locationBtn" onclick="railGPSLocation()" ></div>
					</div>
				</div>
			</div>
			<!-- 左上方及时菜单Pw -->
			<div class="ex_main_rightheader_pw4" id="emrhPw">
				<!-- <div class="ex_main_rightheader_closepw4"></div>
				<div class="ex_main_rightheader_pw4_header"></div> -->
			</div>
			<div class="ex_main_rightheader_pw5">
				<div class="ex_main_rightheader_closepw5"></div>
			</div>
			
			<!-- 区域--------区 详细列表弹出款 -->
			<div class="ex_main_rightheader_pw6">
				<div id="regionStreet" class="regionStreet">
					<ul></ul>
				</div>
				<div class="ex_main_rightheader_closepw6" onclick="closeRegionList()"></div>
			</div>
			<!-- 区域--------街道详细列表弹出款 -->
			<div class="ex_main_rightheader_pw7">
				<div class="ex_main_rightheader_pw7_content">
					<div id="emrpc7"></div>
				</div>
				<!-- <div class="ex_main_rightheader_closepw7" onclick="closeStreetList()"></div> -->
			</div>
			<!-- 右下方 经纬度提示框 -->
			<!-- <div class="ex_main_right_down"></div> -->
			<div class="ex_main_right_down_content">
				<!-- <div class="ceshiGzz" onclick="ceshiGzz()"><div class="ceshiGzz_text">工作站(测试)</div></div>
				<div class="ceshiJk" onclick="ceshiJk()"><div class="ceshiJk_text">监控(测试)</div></div> -->
				<div class="ex_mapLw" onclick="lw()" style="display:none;"><div class="ex_mapLw_text">路网</div></div>
				<div class="securityArea" onclick="securityArea()"><div class="securityArea_text">安保区</div></div>
				<div class="ex_main_right_down_content_center">
					<div id="tempLng"></div>
					<div id="tempLat"></div>
					<div id="tempBorderName"></div>
					<div id="tempJDBordertName"></div>
				</div>
			</div>
			
			
			<!-- 视频聊天Q -->
			<div class="map_chat">
				<div class="map_chat_left" >
					<div class="map_chat_top">
						<div class="map_chat_top_left"></div>
						<div class="map_chat_top_right"></div>
					</div>
					<div class="map_chat_top_search">
						<input type="text" id="searchMem">
					</div>
					<div class="map_chat_top_phone_add" onclick="chatMess()"></div>
					<div class="map_chat_left_header">
						<div class="map_chat_recond" onclick="chatRecondList()"></div>
						<div class="map_chat_mail"onclick="chatQ()"></div>
						<div onclick="chatMessList()">群聊2</div>
					</div>
					<!-- 联系人列表 -->
					<div class="map_chat_left_bottom" id="chatQLeft"></div>
					<!-- 搜索联系人列表 -->
					<div class="map_chat_search_user" id="searchUserList"></div>
					<!-- 聊天记录列表 -->
					<div class="map_chat_left_bottom_record" id="recondListB"></div>
				</div>
				
				
				<div class="map_chat_right" id="chatQRight">
					<div class="map_chat_right_top">
						<div class="map_chat_right_top_men"></div>
						<div class="map_chat_right_top_menText">套你猴子</div>
						<div class="map_chat_right_bottom" onclick="sendMessage()">
							发消息
						</div>
					</div>
					<!-- 发送消息 -聊天框 -->
					<div class="map_chat_sendMess">
						<div class="map_chat_sendMess_header">
							套你猴子
						</div>
						<div class="map_chat_sendMess_center">
							<!-- <div class="map_chat_sendMess_center_text"></div> -->
							<!-- <div id="265"><p>111111</p></div> -->
						</div>
						<div class="map_chat_sendMess_bottom">
							<div class="map_chat_sendMess_bottom_header">
								<div  class="chat_file">
									<form id="tf" enctype="multipart/form-data">
										<input onchange="searchFile()" type="file" name="file"/>
									</form>
								</div>
								<div style="width:auto;overflow:hidden;float:right;margin:0 20px 0 0;">
									<div class="chat_phone"></div>
									<div class="chat_video_phone"></div>
								</div>
								
							</div>
							<div class="map_chat_sendMess_bottom_center">
								<textarea id="chatEnterHtml" style="scrollbar-arrow-color:yellow;scrollbar-base-color:lightsalmon"></textarea>
							</div>
							<div class="map_chat_sendMess_bottom_bottom">
								<div id="chatTextEnter" onclick="chatEnter()">发送</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 群聊弹窗 -->
				<div class="ex_map_chatMess">
					<div class="ex_map_chatMess_left">
						<div class="ex_map_chatMess_left_top">
							<input type="text" id="groupChatSearch"/>
							<!-- <div onclick="chatMemSearch()">搜</div> -->
						</div>
						<div class="ex_map_chatMess_left_bottom"></div>
					</div>
					<div class="ex_map_chatMess_right">
						<div class="ex_map_chatMess_right_top">
							<div class="ex_map_chatMess_right_top_left">
								<span>你已选择</span><div class="ex_map_chatMess_right_top_left_text">0</div><span>位联系人</span>
							</div>
							<div class="ex_map_chatMess_right_close"></div>
						</div>
						<div class="ex_map_chatMess_right_center"></div>
						<div class="ex_map_chatMess_right_bottom">
							<button class="ex_map_chatMess_right_bottom_btn" onclick="uploadSure()">确定</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 视频弹窗 -->
			<div class="chat_video">
				<!-- 大的 -->
				<div class="ex_main_video_big">
					 <!-- <video class="ex_mapPw_chat_text"  id="O"></video> -->
				</div>
				<!-- 小的 -->
				<div class="ex_main_video_seml" id="barrage">
					<video id="M"></video>
				</div>
			</div>
			
			
			<!-- //视频iframe -->
			<!-- //<iframe  allow="geolocation; microphone; camera" src="https://192.168.0.118:9001/demos/Audio+Video+TextChat+FileSharing.html" id="iframeSH"></iframe> -->
			
			
		</div>
		<!--地图弹出框 -->
	  	<div id="map" class="map"></div>
	    <div id="popup" class="ol-popup">
	      <a href="#" id="popup-closer" class="ol-popup-closer"></a>
	      <div id="popup-content"></div>
	    </div>
		<script type="text/javascript" src="${ctx}/resources/openlayers4.5.0/ol.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/style.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/function.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/map.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/area.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/exhibition/map/userteam.js"></script>
		<script type="text/javascript">
			//在线队员
			var teamMember=false;
			//在线干部
			var teamCadre=false;
			var overlaysDanger=[];
			/* 定位点 id */
			var kmoigId;
			//行政区域orgID
			var adminDivisionOrgId;
			//铁路线路数据
			var railDataList;
			//铁路ID
			var railIdArray= new Array();
			//周边场所类型
			var perPlacyTypeArray = new Array();
			//ID
			var userIdM="${sessionScope.userId}"
			//警示柱定时器
			var setInterTime;
			//文件类型   单人/群聊 
			var typeO;
			//选中ID 单人
			var meId; 
			//选中房间名称
			var userRoomName;  
			//选择群聊联系人
			var chatArray=[];
			//本地储存
			var indexDb=IndexDB();
			
			$(document).ready(function(){
				
				
	       		indexDb.instance.openDB(indexDb.instance.myDB.name,indexDb.instance.myDB.version);
				map.getView().setZoom(parseFloat("${orgZoomAndCenter.Zoom}"));
				map.getView().setCenter([ parseFloat("${orgZoomAndCenter.X}"), parseFloat("${orgZoomAndCenter.Y}") ]);
				getUserOrgList();
				$('#exRegionOpition li:first-child').click();
				$('#exRailOpition li:first-child').click();
				$('.ex_main_rightheader_pw7').hide();
				//右下角经纬度窗口 
				$('.ex_main_right_down').click();
				//隐患点初始值 
				$('#keyPartsInput').html('全部');
				//街道一级  去除下拉菜单 
    			if($('#exRegionOpition li').attr('id').length == 9){
    				$('#exRegionInput').unbind('click');
    			}
				//在线队员刷新数据
				window.enabeAdapter = true;
				window.mytool = new myTool();
		        mytool.toolInit();
		        debugger
		        mytool.CLogin(userIdM);
		        teamMember=false;
		        teamCadre=false;
		        
		        
		      
			});
			$(function(){
				$('.ex_main_railList').mCustomScrollbar();
				var search_inputKm = $("#exmrpwInput");
    	    	if (search_inputKm.val() != '') {
    	    		$("#exmrpwList li:contains(" + search_inputKm.val().trim() + ")").show();
    				$("#exmrpwList li:not(:contains(" + search_inputKm.val().trim() + "))").hide();
				}
    	    	search_inputKm.on("keyup", function() {
    				if (search_inputKm.val() == '') {
    					$('#exmrpwList').show();
    				}
    				$("#exmrpwList li:contains(" + search_inputKm.val().trim() + ")").show();
    				$("#exmrpwList li:not(:contains(" + search_inputKm.val().trim() + "))").hide();
    			});
			})
			$('#keyword').focus(function(){
				$('.ex_main_rightheader_pw1_btn').show();
			});
			$('#keyword').blur(function(){
				$('.ex_main_rightheader_pw1_btn').hide();
			});
			/* 右上侧 Pw 数据检索  队伍管理  线路定位*/
			function dataSearch(){
				$('.ex_main_rightheader_one').removeClass().addClass('ex_main_rightheader_one_hover');
				$('.ex_main_rightheader_two_hover').removeClass().addClass('ex_main_rightheader_two');
				$('.ex_main_rightheader_three_hover').removeClass().addClass('ex_main_rightheader_three');
				/* 关闭右侧筛选框 */
				$('.ex_main_right_screen_content').hide();
				$('.ex_main_right_close_screenbtn').hide();
				$('.ex_main_right_screenbtn').show();
				/* 关闭右侧筛选框 end */
				$('.ex_main_rightheader_pw1').show();
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_pw3').hide();
				$('.ex_main_rightheader_pw2_pep').hide();
				$('.ex_main_rightheader_pw2_tjy').hide();
				/* 关闭 区域+街道弹窗 */
				closeRegionList();
				/* 区域------街道详细信息 */
				/* closeStreetList(); */
			}
			/* 点击巡防队伍 */
			function patroTeam(page){
				$('.ex_main_rightheader_two').removeClass().addClass('ex_main_rightheader_two_hover');
				$('.ex_main_rightheader_one_hover').removeClass().addClass('ex_main_rightheader_one');
				$('.ex_main_rightheader_three_hover').removeClass().addClass('ex_main_rightheader_three');
				/* 关闭右侧筛选框 star */
				$('.ex_main_right_screen_content').hide();
				$('.ex_main_right_close_screenbtn').hide();
				$('.ex_main_right_screenbtn').show();
				/* 关闭右侧筛选框 end */
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_pw2').show();
				$('.ex_main_rightheader_pw3').hide();
				/* 关闭 区域+街道弹窗 */
				closeRegionList();
				if (!page)
					page = 1;
				var pageRows = 10;
				
				$.ajax({
					url : '/sdrpoms/patrol/patrolTeamInfoQueryList',
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
						$('#patrolTeamPage').html('');
						var tempHtml = "";
						var rows = data.rows;
						if (rows && rows.length > 0) {
							for (var i = 0; i < rows.length; i++) {
								tempHtml += "<li data-name='"+rows[i].name+"' onclick='Patrolteammember(&quot;" + rows[i].id + "&quot;,&quot;" + rows[i].name + "&quot;)'>" + rows[i].name + "</li>"
							}
							$("#patrolTeam").html(tempHtml);
						}else{
							$("#patrolTeam").html('暂无数据!!!');
						}
						if (!data.total || data.total <= pageRows) {
							return;
						}
						$("#patrolTeamPage").paging({
							pageNo : page,
							totalPage : Math.ceil(data.total / pageRows),
							totalSize : data.total,
							callback : function(num) {
								patroTeam(num);
							}
						})
					}
				});
			}
			/* 队伍ID */
			var teamPepId;
			//巡防队员 
			function Patrolteammember(id,name,page) {
				teamPepId=id;
				/* 关闭右侧筛选框 end */
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_pw2_pep').show();
				$('.ex_main_rightheader_pw3').hide();
				$('.ex_main_rightheader_text').html(name);
				if (!page)
					page = 1; //显示第几页
				var pageRows = 10; //每页多少条记录
				//巡防队员
				$.ajax({
					url : '/sdrpoms/patrol/patrolTeamUserRelationQueryList?teamInfoId=' + id,
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
						$('#patrolTeammemberPage').html('');
						var tempHtml = "";
						var rows = data.rows;
						if (rows && rows.length > 0) {
							for (var i = 0; i < rows.length; i++) {
								tempHtml += "<li onclick='patrolTrajectory(&quot;" + rows[i].userId + "&quot;,&quot;" + rows[i].userName + "&quot;)'>" + rows[i].userName + "</li>"
							}
							$("#patrolTeamPep").html(tempHtml);
						}else{
							$("#patrolTeamPep").html('暂无数据!!!');
						}
						if (!data.total || data.total <= pageRows) {
							return;
						}
						$("#patrolTeammemberPage").paging({
							pageNo : page,
							totalPage : Math.ceil(data.total / pageRows),
							totalSize : data.total,
							callback : function(num) {
								Patrolteammember(id,name,num);
							}
						})
					}
				});
			}
			//巡防轨迹 
			function patrolTrajectory(id,name,page) {
				if (!page)
					page = 1; //显示第几页
				var pageRows = 10; //每页多少条记录
				$('.ex_main_rightheader_pw2_tjy').show();
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_pw2_pep').hide();
				$('.ex_main_rightheader_pw3').hide();
				$('#map-popup-closer').click();
				$('.ex_main_rightheader_text_three').html(name);
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
						$('#patrolTjyPage').html('');
						var tempHtml = "";
						var rows = data.rows;
						if (rows && rows.length > 0) {
							for (var i = 0; i < rows.length; i++) {
								tempHtml += "<li id="+ rows[i].id +" onclick='showPatrolTrajectory(this.id)' data-coords=" + rows[i].track + ">"
									  +"开始时间:" + rows[i].startTime +","+"结束时间:" + rows[i].endTime + 
									"</li>"
							}
							$("#patrolTeamTjy").html(tempHtml);
						}else{
							$("#patrolTeamTjy").html('暂无数据!!!');
						}
						if (!data.total || data.total <= pageRows) {
							return;
						}
						$("#patrolTjyPage").paging({
							pageNo : page,
							totalPage : Math.ceil(data.total / pageRows),
							totalSize : data.total,
							callback : function(num) {
								patrolTrajectory(id,name,num);
							}
						})
					}
				});
			}
			//搜索 队伍、队员================================================
			$('#searchInput').on("keyup", function() {
   				if ($('#searchInput').val() == '') {
   					patroTeam();
   					$('.search_btn').hide();
   				}else{
   					$('.search_btn').show();
   				}
   			});
			$('#searchPepInput').on("keyup", function() {
   				if ($('#searchPepInput').val() == '') {
   					searchPep();
   					$('.search_btn').hide();
   				}else{
   					$('.search_btn').show();
   				}
   			});
			
			//回车响应
			$("#searchInput").keypress(function (e){
			  	var code = event.keyCode;
			     	if (13 == code) {
			     		if($('#searchInput').val() == ''){
				     		return;
				     	}else{
				     		searchTp();
				     	}
			     	}
			     	
			});
			$("#searchPepInput").keypress(function (e){
			  	var code = event.keyCode;
			     	if (13 == code) {
			     		if($('#searchPepInput').val() == ''){
			     			return;
				     	}else{
				     		searchPep();
				     	}
			     	}
			});
			//巡防队伍搜索==================================================
			function searchTp(page){
				$('#patrolTeamPage').html('');
				if (!page)
					page = 1; //显示第几页
				var pageRows = 10; //每页多少条记录
				var searchHtml = $('#searchInput').val();
				$.ajax({
					url : '/sdrpoms/patrol/patrolTeamInfoQueryList',
					type : 'post',
					dataType : 'json',
					data:{
						name:searchHtml,
						page : page,
						rows : pageRows
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("响应状态:[" + XMLHttpRequest.status + "]-");
						console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
						console.log("异常情况:[" + textStatus + "]");
					},
					success : function(data) {
						$('#patrolTeamPage').html('');
						var tempHtml="";
						var rows = data.rows;
						if (rows && rows.length > 0) {
							for (var i = 0; i < data.rows.length; i++) {
									tempHtml += "<li onclick='Patrolteammember(&quot;" + data.rows[i].id + "&quot;,&quot;" + data.rows[i].name + "&quot;)'>" + data.rows[i].name + "</li>"
								}
							}
						$("#patrolTeam").html(tempHtml);
						if (!data.total || data.total <= pageRows) {
							return;
						}
						$("#patrolTeamPage").paging({
							pageNo : page,
							totalPage : Math.ceil(data.total / pageRows),
							totalSize : data.total,
							callback : function(num) {
								searchTp(num);
							}
						})
					}
				});
			}
			//巡防队员搜索=================================================================
			function searchPep(page){
				$('#patrolTeammemberPage').html('');
				if (!page)
					page = 1; //显示第几页
				var pageRows = 10; //每页多少条记录
				var searchPepHtml = $('#searchPepInput').val();
				//巡防队员
				$.ajax({
					url : '/sdrpoms/patrol/patrolTeamUserRelationQueryList?teamInfoId=' + teamPepId,
					type : 'post',
					dataType : 'json',
					data : {
						userName:searchPepHtml,
						page : page,
						rows : pageRows
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("响应状态:[" + XMLHttpRequest.status + "]-");
						console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
						console.log("异常情况:[" + textStatus + "]");
					},
					success : function(data) {
						var tempHtml = "";
						var rows = data.rows;
						if(rows.length==0){
							$("#patrolTeamPep").html('暂无数据');
						 }
						if (rows && rows.length > 0) {
							for (var i = 0; i < rows.length; i++) {
								tempHtml += "<li onclick='patrolTrajectory(&quot;" + rows[i].userId + "&quot;,&quot;" + rows[i].userName + "&quot;)'>" + rows[i].userName + "</li>"
							}
							$("#patrolTeamPep").html(tempHtml);
						}

						if (!data.total || data.total <= pageRows) {
							return;
						}
						$("#patrolTeammemberPage").paging({
							pageNo : page,
							totalPage : Math.ceil(data.total / pageRows),
							totalSize : data.total,
							callback : function(num) {
								Patrolteammember(teamPepId, num);
							}
						})
					}
				});
			}
			/* 线路定位 */
			function railLine(orgId){
				$('.ex_main_rightheader_three').removeClass().addClass('ex_main_rightheader_three_hover');
				$('.ex_main_rightheader_two_hover').removeClass().addClass('ex_main_rightheader_two');
				$('.ex_main_rightheader_one_hover').removeClass().addClass('ex_main_rightheader_one');
				/* 关闭右侧筛选框 */
				$('.ex_main_right_screen_content').hide();
				$('.ex_main_right_close_screenbtn').hide();
				$('.ex_main_right_screenbtn').show();
				/* 关闭右侧筛选框 end */
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_pw3').show();
				/* 关闭 区域+街道弹窗 */
				closeRegionList();
				/* 线路定位 */
				$.ajax({
		    		url:"${ctx}/exhibition/map/getStreamRials",
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    		},
		    		success:function(data){
		    			$("#exmrpwList").mCustomScrollbar("destroy");
		    			railDataList = data.railList;
		    			var tempRailHtml="";
	    				for(var i=0;i<data.length;i++){
	    					tempRailHtml+="<li data-rail-id='"+data[i].id+"'>"+data[i].name+"</li>";
	    				}
	    				$('#exmrpwList').html(tempRailHtml);
	        			$("#exmrpwList").mCustomScrollbar();
	        			$('#exmrpwList li').click(function(){
	        				$('#exmrpwInput').val($(this).html());
	        				$('#exmrpwInput').data("rail-id",$(this).data('rail-id'));
	        			})  
		    		}
		    	});
			}
			// 右侧筛选框 
			$('.ex_main_right_screenbtn').click(function() {
				//关闭地图弹窗
				closePw();
				$('.ex_main_rightheader_one_hover').removeClass().addClass('ex_main_rightheader_one');
				$('.ex_main_rightheader_two_hover').removeClass().addClass('ex_main_rightheader_two');
				$('.ex_main_rightheader_three_hover').removeClass().addClass('ex_main_rightheader_three');
				/* 右上侧弹出框清空 并关闭 */
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_closepw3').click();
				$('.ex_main_rightheader_pw2_pep').hide();
				$('.ex_main_rightheader_pw2_tjy').hide();
				$('.ex_main_right_screen_content').css({
					'right' : '-30%',
					'display' : 'block'
				}).animate({
					"right" : "0px"
				}, 500).show();
				$('.ex_main_right_screenbtn').hide();
				$('.ex_main_right_close_screenbtn').show();
			    //距离  面积测量  清除
				$('.ex_main_rightheader_measure_ranging_hover').removeClass().addClass('ex_main_rightheader_measure_ranging');
				$('.ex_main_rightheader_measure_surface_hover').removeClass().addClass('ex_main_rightheader_measure_surface');
				map.removeInteraction(measureDraw);
			});
			$('.ex_main_right_close_screenbtn').click(function() {
				/* 关闭 区域+街道弹窗 */
				closeRegionList();
				//关闭地图弹窗
				closePw();
				/* 右上侧弹出框清空 并关闭 */
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_pw3').hide();
				/* 关闭右侧筛选框 end */
				$('.ex_main_right_close_screenbtn').hide();
				$('.ex_main_right_screen_content').css({
					'right' : '3px',
					'display' : 'none'
				}).animate({
					"right" : "-30%"	
				}, 500).show();
				$('.ex_main_right_screenbtn').css('display', 'block').animate({
					"right" : "0px"
				}, 2000).show();
			});
			$('.ex_main_bottom_menu_btn').mouseenter(function(){
				/* 关闭 区域+街道弹窗 */
				closeRegionList();
				/* 区域------街道详细信息 */
				/* closeStreetList(); */
				var tempMenuList='';
				var requrl = "menu/getUserSecondLevelMenu";
				$.post(requrl,function(data){
					$.each(data, function (n, value) {
						if(value.name == '基础数据'){
							tempMenuList +='<li data-org-name="基础数据" data-menu-id="0001" onclick="menubar()"class="Basicdata"><span>基础数据</span></li>';
						}
						if(value.name == '办公OA'){
							tempMenuList +='<li data-org-name="办公OA" data-menu-id="0005"  onclick="menubar()" class="officeOa iconimg"><span>办公OA</span></li>';
						}
						if(value.name == '队伍管理'){
							tempMenuList +=	'<li data-org-name="队伍管理" data-menu-id="0003" onclick="menubar()" class="Patrolmanagement"><span>队伍管理</span></li>';
						}
						if(value.name == '隐患处置'){
							tempMenuList +='<li data-org-name="隐患处置" data-menu-id="0004" onclick="menubar()" class="Hiddendanger_disposal"><span>隐患处置</span></li>';
						}
						if(value.name == '视频监控'){
							tempMenuList +='<li data-org-name="视频监控" data-menu-id="0002" onclick="menubar()" class="Videosurveillance"><span>视频监控</span></li>';
						}
						if(value.name == '教育培训'){
							tempMenuList +=	'<li data-org-name="教育培训" data-menu-id="0006" onclick="menubar()" class="training iconimg"><span>教育培训</span></li>';
						}
						if(value.name == '研判分析'){
							tempMenuList +='<li data-org-name="研判分析" data-menu-id="0007" onclick="menubar()" class="Judgment_analysis "><span>研判分析</span></li>';
						}
						if(value.name == '视频会议'){
							tempMenuList +='<li data-org-name="视频会议" data-menu-id="0008" onclick="menubar()" class="Videoconferencng "><span>视频会议</span></li>';
						}
						if(value.name == '运维管理'){
							tempMenuList +='<li data-org-name="运维管理" data-menu-id="0009" onclick="menubar()" class="MochaITOM "><span>运维管理</span></li>';
						}
					});
					$('#exMainBottomMenuUl').html(tempMenuList);
				});
				/* 关闭右侧筛选框 */
				$('.ex_main_right_screen_content').hide();
				$('.ex_main_right_close_screenbtn').hide();
				$('.ex_main_right_screenbtn').show();
				/* 关闭右侧筛选框 end */
				$('.ex_main_bottom_menu').css("width","74%");
				$('.ex_main_bottom_menu_content').animate({"width":"100%"},500);
				$('.ex_main_bottom_menu_btn').removeClass().addClass('ex_main_bottom_menu_btn_hover');
			});
			$('.ex_main_bottom_menu').mouseleave(function(){
				$('.ex_main_bottom_menu').css("width","0");
				$('.ex_main_bottom_menu_content').animate({"width":"0"},500);
				$('.ex_main_bottom_menu_btn_hover').removeClass().addClass('ex_main_bottom_menu_btn');
			});
			/* 弹窗关闭 */
			$('.ex_main_rightheader_closepw1').click(function(){
				$('.ex_main_rightheader_pw1').hide();
				$('.ex_main_rightheader_one_hover').removeClass().addClass('ex_main_rightheader_one');
			});
			$('.ex_main_rightheader_closepw2').click(function(){
				$('.ex_main_rightheader_pw2').hide();
				$('.ex_main_rightheader_pw2_pep').hide();
				$('.ex_main_rightheader_pw2_tjy').hide();
				$('#searchInput').val('');
				$('#searchPepInput').val('');
				$('.ex_main_rightheader_two_hover').removeClass().addClass('ex_main_rightheader_two');
			});
			$('.ex_main_rightheader_closepw2_pep').click(function(){
				/* $('.ex_main_rightheader_pw2').hide(); */
				$('.ex_main_rightheader_pw2_pep').hide();
				$('.ex_main_rightheader_pw2_tjy').hide();
				$('#searchInput').val('');
				$('#searchPepInput').val('');
				/* $('.ex_main_rightheader_two_hover').removeClass().addClass('ex_main_rightheader_two'); */
				$('.ex_main_rightheader_pw2').show();
			});
			$('.ex_main_rightheader_closepw2_tjy').click(function(){
				$('.ex_main_rightheader_pw2').hide();
				/* $('.ex_main_rightheader_pw2_pep').hide(); */
				$('.ex_main_rightheader_pw2_tjy').hide();
				/* $('.ex_main_rightheader_two_hover').removeClass().addClass('ex_main_rightheader_two'); */
				 $('.ex_main_rightheader_pw2_pep').show();
			});
			$('.ex_main_rightheader_closepw3').click(function(){
				BJRailGPSLocationLayer.getSource().clear();
				BJRailGPSLocationLayer.setVisible(false);
				$(".ol-popup-closer").click();
				$('#exmrpwInput').val('');
				$('#locationRailK').val('');
				$('#locationRailM').val('');
				$('#GpsLng').val('');
				$('#GpsLat').val('');
				$('.ex_main_rightheader_pw3').hide();
				$('.ex_main_rightheader_three_hover').removeClass().addClass('ex_main_rightheader_three');
				$('.ex_map_location_text').html('');
				closePw();
			});
			$('.ex_main_rightheader_closepw4').click(function(){
				$('.ex_main_rightheader_pw4').hide();
			});
			$('.ex_main_rightheader_closepw5').click(function(){
				$('.ex_main_rightheader_pw5').hide();
			});
			/* 底部菜单跳转*/
			function menubar() {
				var element = event.target;
				var menuName = element.dataset.orgName;
				var menuId = element.dataset.menuId;
				window.location.href = "/sdrpoms/welcome?menuName=" + menuName + "&menuId=" + menuId;
			}
			/* 系统退出 */
			function exReturn() {
				window.location.href = "/sdrpoms/logout";
			}
			//模拟option点击事件   行政区域
		    $('#exRegionInput').click(function(){
		    	if($('.ex_main_region_list').is(':hidden')){//如果当前隐藏  
		    		$('.ex_main_region_list').slideDown(100);//那么就显示div  
	         	}else{//否则  
		            $('.ex_main_region_list').slideUp(100);//就隐藏div  
	         	}
		    });
		    $('body').click(function(e) {
			  if(e.target.id != 'exRegionInput' && e.target.id != 'exRegionOpition')
		   		if($('.ex_main_region_list').is(':visible') ) {
		     		$('.ex_main_region_list ').slideUp(100);
		   		}
			  if($('.ex_main_rightheader_pw7').is(':visible') ) {
		     		$('.ex_main_rightheader_pw7 ').hide();
		   		}
			});
			//模拟option点击事件  线路分类
		    $('#exRailInput').click(function(){
		    	if($('#exRailOpition').is(':hidden')){//如果当前隐藏  
		    		$('#exRailOpition').slideDown(100);//那么就显示div  
	         	}else{//否则  
	         		$('#exRailOpition').slideUp(100);//就隐藏div  
	         	}
		    });
		    $('body').click(function(e) {
			  if(e.target.id != 'exRailInput' && e.target.id != 'exRailOpition')
		   		if ($('#exRailOpition').is(':visible') ) {
		     		$('#exRailOpition').slideUp(100);
		   		}
			});
		   /*线路定位 仿option */
			$('#exmrpwInput').click(function(){
		    	if($('#exmrpwList').is(':hidden')){//如果当前隐藏  
		    		$('#exmrpwList').slideDown(100);//那么就显示div  
	         	}else{//否则  
	         		$('#exmrpwList').slideUp(100);//就隐藏div  
	         	}
		    });
		    $('body').click(function(e) {
			  if(e.target.id != 'exmrpwInput' && e.target.id != 'exmrpwList')
		   		if ( $('#exmrpwList').is(':visible') ) {
		     		$('#exmrpwList').slideUp(100);
		   		}
			});				
			//查找用户所在区域列表
		    function getUserOrgList(orgId){
		    	$.ajax({
		    		url:"${ctx}/exhibition/getUserOrgList?orgId="+orgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			var tempOrgHtml = "";
		    			for(var i=0;i<data.orgList.length;i++){
		    				tempOrgHtml += "<li style='cursor: pointer;' id ="+data.orgList[i].orgId+" onclick='setAdminDivision(&quot;"+data.orgList[i].orgId+"&quot;,&quot;"+data.orgList[i].orgName+"&quot;)'>"+data.orgList[i].orgName+"</li>";
		    			}
		    			$('#exRegionOpition').html(tempOrgHtml);
		    			$(".ex_main_region_list").mCustomScrollbar();
		    		}
		    	});
		    }
		    //查找用户所在区域统计信息
		    function getUserOrgListInfo(orgId){
		    	$.ajax({
		    		url:"${ctx}/exhibition/getUserOrgListInfo?orgId="+orgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			//铁路统计信息
		    			var tempRailCountHtml="";
		    			for(var i=0;i<data.railList.length;i++){
		    				if(i == 1){
		    					var totalDistancee=data.totalDistance==null?'0':data.totalDistance;
		    					tempRailCountHtml += "<li><p>总里程</p><span>"+totalDistancee+"</span><span style='color:#fff'>公里</span></li>";
		    				}
		    				tempRailCountHtml += "<li><p>"+data.railList[i].dictName+"</p><span>"+data.railList[i].dictData+"</span><span></span></li>";
		    			}
		    			$('.exmrsc_content_header_left_content_railcontent').html(tempRailCountHtml);
		    			//行政区域统计
		    			if(parseInt(data.orgCount.aeraCount)<=1){
		    				tempRailCountHtml ="<div class='exmrsc_region'>涉路行政区域</div>"+
						  	 "<li>"+
								 "<p>街乡镇</p>"+
								 "<span style='cursor: pointer;' onclick='regionList(&quot;"+adminDivisionOrgId+"&quot;,2)'>"+data.orgCount.streetCount+"</span><span></span>"+
					  		"</li>";
		    			}
		    			else{
		    				tempRailCountHtml ="<div class='exmrsc_region'>涉路行政区域</div>"+
							  "<li>"+
								 "<p>区</p>"+
								 "<span onclick='regionList(&quot;"+adminDivisionOrgId+"&quot;,1)'>"+data.orgCount.aeraCount+"</span><span></span>"+
						  	"</li>"+
						  	 "<li>"+
								 "<p>街乡镇</p>"+
								 "<span style='cursor: pointer;' onclick='regionList(&quot;"+adminDivisionOrgId+"&quot;,2)'>"+data.orgCount.streetCount+"</span><span></span>"+
					  		"</li>";
		    			}
						$('.exmrsc_content_header_left_content_region').html(tempRailCountHtml);
						//铁路类型下拉框设置
						var tempRailTypeHtml = "";
						for(var i=0;i<data.railTypeList.length;i++){
							if(i==0){
								$('#exRailInput').val(data.railTypeList[i].dictName);
							}
							tempRailTypeHtml += "<li onclick='setRailType(&quot;"+data.railTypeList[i].dictData+"&quot;,&quot;"+data.railTypeList[i].dictName+"&quot;)'>"+data.railTypeList[i].dictName+"</li>";
		    			}
		    			$('#exRailOpition').html(tempRailTypeHtml);
		    		}
		    	});
		    }
		    //查找用户所在区域铁路统计信息
		    function getUserRailListInfo(orgId){
		    	$.ajax({
		    		url:"${ctx}/exhibition/getUserRailListInfo?orgId="+orgId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			railDataList = data.railList;
		    			var tempRailHtml="";
	    				for(var i=0;i<data.railList.length;i++){
	    					tempRailHtml+="<li data-rail-id='"+data.railList[i].railId+"' onclick='setRailCheck(&quot;"+data.railList[i].railId+"&quot;)'>"+data.railList[i].railName+"</li>";
	    				}
	    				$(".ex_main_railList").mCustomScrollbar("destroy");
	    				$(".ex_main_railList").html(tempRailHtml);
	        			$(".ex_main_railList").mCustomScrollbar();
	        			//铁路线快速查询 
	        	    	var search_input = $(".ex_main_rail_search");
	        	    	if (search_input.val() != '') {
	        	    		$(".ex_main_railList li:contains(" + search_input.val().trim() + ")").show();
	        				$(".ex_main_railList li:not(:contains(" + search_input.val().trim() + "))").hide();
        				}
	        			search_input.on("keyup", function() {
	        				if (search_input.val() == '') {
	        					$('.ex_main_railList').show();
	        				}
	        				$(".ex_main_railList li:contains(" + search_input.val().trim() + ")").show();
	        				$(".ex_main_railList li:not(:contains(" + search_input.val().trim() + "))").hide();
	        			});
	        			//更新数据
	        			$("#exShowAllRail").prop("checked",true);
	        			$("#exShowAllRail").removeClass().addClass("ex_main_show_all_rail_select");
	        			railIdArray= new Array();
	        			$('.ex_main_railList li').each(function(){
	        				$(this).prop("checked",true);
	        				$(this).removeClass().addClass("rail_line_select");
	        				railIdArray.push($(this).data("rail-id"));
	        			});
	        			//刷新所包含的数据
	    		 		setBJRailWayLayer(adminDivisionOrgId,railIdArray);
	    		 		getUserKeyPartListInfo();
	    		 		getUserJDenfenseListInfoInfo();
	    		 		getUserPeripheralPlaceListInfoInfo();
		    		}
		    	});
		    }
		 	//行政区域点击
		   	function setAdminDivision(orgId,orgName){
		   		map.getView().setZoom(parseFloat("${orgZoomAndCenter.Zoom}"));
				map.getView().setCenter([ parseFloat("${orgZoomAndCenter.X}"), parseFloat("${orgZoomAndCenter.Y}") ]);
		   		adminDivisionOrgId = orgId;
				//设置地图边界
				setBJBorder(adminDivisionOrgId,"${orgId}");
				//安保区
				$('.securityArea_hover').removeClass().addClass('securityArea');
				BJSecurityAreaLayer.setVisible(false);
				
				getUserOrgListInfo(orgId);
				getUserRailListInfo(orgId);
				$('#exRegionInput').html(orgName);
				closePw();
				/* 关闭 区域+街道弹窗 */
				closeRegionList();
				/* 铁路类型 全部 */
				$('#exRailInput').html('全部线路');
				
				//清除gif
				/* for(var i = 0; i<overlaysDanger.length;i++){
					map.removeOverlay(overlaysDanger[i])
				} */
		   	}
		 	//线路类型点击
		 	function setRailType(railType,railTypeName){
		 		closePw();
		 		$('#exRailInput').html(railTypeName);
		 	 	var tempRailHtml="";
				for(var i=0;i<railDataList.length;i++){
					if(railType!="total"){
						if(railDataList[i].railType==railType){
							tempRailHtml+="<li data-rail-id='"+railDataList[i].railId+"' onclick='setRailCheck(&quot;"+railDataList[i].railId+"&quot;)' >"+railDataList[i].railName+"</li>";
						}
					}else{
						tempRailHtml+="<li data-rail-id='"+railDataList[i].railId+"' onclick='setRailCheck(&quot;"+railDataList[i].railId+"&quot;)' >"+railDataList[i].railName+"</li>";
					}
				}
				$(".ex_main_railList").mCustomScrollbar("destroy");
    			$('.ex_main_railList').html(tempRailHtml);
    			$(".ex_main_railList").mCustomScrollbar();
    			//铁路线快速查询 
    	    	var search_input = $(".ex_main_rail_search");
    	    	if (search_input.val() != '') {
    	    		$(".ex_main_railList li:contains(" + search_input.val().trim() + ")").show();
    				$(".ex_main_railList li:not(:contains(" + search_input.val().trim() + "))").hide();
				}
    			search_input.on("keyup", function() {
    				if (search_input.val() == '') {
    					$('.ex_main_railList').show();
    				}
    				$(".ex_main_railList li:contains(" + search_input.val().trim() + ")").show();
    				$(".ex_main_railList li:not(:contains(" + search_input.val().trim() + "))").hide();
    			});
    			//刷新所包含的数据
    			$("#exShowAllRail").prop("checked",true);
	        	$("#exShowAllRail").removeClass().addClass("ex_main_show_all_rail_select");
	        	railIdArray= new Array();
    		 	$('.ex_main_railList li').each(function(){
   					$(this).prop("checked",true);
    				$(this).removeClass().addClass("rail_line_select");
    				railIdArray.push($(this).data("rail-id"));
    			}); 
    		 	setBJRailWayLayer(adminDivisionOrgId,railIdArray);
    			getUserKeyPartListInfo();
		 		getUserJDenfenseListInfoInfo();
		 		getUserPeripheralPlaceListInfoInfo();
		 	}
		 	//线路点击事件
		 	function setRailCheck(railId){
		 		var isAllChecked = true;
		 		railIdArray = new Array();
		 		$('.ex_main_railList li').each(function(){
		 			if($(this).data("rail-id")==railId){
		 				if($(this).prop("checked")){
		 					$(this).prop("checked",false).removeClass();
		 				}else{
		 					$(this).prop("checked",true).removeClass().addClass("rail_line_select");
		 				}
		 			}
		 			if($(this).prop("checked")){
		 				railIdArray.push($(this).data("rail-id"));
		 			}else{
		 				isAllChecked=false;
		 			}
		 		});
		 		if(isAllChecked){
		 			$("#exShowAllRail").prop("checked",true).removeClass().addClass("ex_main_show_all_rail_select");
		 		}else{
		 			$("#exShowAllRail").prop("checked",false).removeClass().addClass("ex_main_show_all_rail");
		 		}
		 		setBJRailWayLayer(adminDivisionOrgId,railIdArray);
		 		getUserKeyPartListInfo();
		 		getUserJDenfenseListInfoInfo();
		 		getUserPeripheralPlaceListInfoInfo();
		 	}
		 	
		 	//线路全选反选
		 	function showAllRail(){
		 		railIdArray= new Array();
		 		if($("#exShowAllRail").prop("checked")){
		 			$('.ex_main_railList li').each(function(){
		 				$(this).prop("checked",false).removeClass();
			 		});
		 			$("#exShowAllRail").prop("checked",false).removeClass().addClass("ex_main_show_all_rail");
		 			//清除广播柱
	 				clearInterval(setInterTime)
		 		}else{
		 			$('.ex_main_railList li').each(function(){
		 				$(this).prop("checked",true).removeClass().addClass("rail_line_select");
		 				//添加railIdArray数组
		 				railIdArray.push($(this).data("rail-id"));
			 		});
		 			$("#exShowAllRail").prop("checked",true).removeClass().addClass("ex_main_show_all_rail_select");
		 			//清除广播柱
	 				clearInterval(setInterTime);
		 		}
		 		//刷新所包含的数据
		 		setBJRailWayLayer(adminDivisionOrgId,railIdArray);
		 		getUserKeyPartListInfo();
		 		getUserJDenfenseListInfoInfo();
		 		getUserPeripheralPlaceListInfoInfo();
		 	}
		 	//根据当前用户机构和铁路获得相关重点部位统计信息(隐患点除外)
		 	function getUserKeyPartListInfo(){
		 		var railId="";
		 		for(var i=0;i<railIdArray.length;i++){
		 			railId+="&railId="+railIdArray[i];
		 		}
		 		$.ajax({
		    		url:"${ctx}/exhibition/getUserKeyPartListInfo?orgId="+adminDivisionOrgId+railId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			var tempTotal= parseInt(data.bcCount) +
		    							parseInt(data.junctionCount) +
		    							parseInt(data.tunnelCount) +
		    							parseInt(data.stationCount) +
		    							parseInt(data.trajectionCount) +
		    							parseInt(data.dangerCount);
	    				var tempKpNumberHtml="";
	    				tempKpNumberHtml +=  "<div class='exmrsc_frist_header'>"+
    					                    	 "<div id='importPlaceCount' onclick='setMapImpotPlaceTable(&quot;importPlaceCount&quot;)' class='exmrsc_frist_Keyparts'>重点部位<span>"+tempTotal+"</span></div>"+
    					                     	"<div id='keyPersonCount' onclick='setMapImpotPlaceTable(&quot;keyPersonCount&quot;)' class='exmrsc_frist_Keyperson'>重点人<span>"+data.keyPersonCount+"</span></div>"+
    				                      	 "</div>"+
    				                     	 "<div class='exmrsc_frist_content'>"+
    					                   		"<ul class='exmrsc_frist_content_ul'>"+
													"<li id='ipBcCount' onclick='setMapImpotPlaceTable(&quot;ipBcCount&quot;)' >桥涵<span>"+data.bcCount+"</span></li>"+
													"<li id='ipJunctionCount' onclick='setMapImpotPlaceTable(&quot;ipJunctionCount&quot;)' >道口<span>"+data.junctionCount+"</span></li>"+
													"<li id='ipTunnelCount' onclick='setMapImpotPlaceTable(&quot;ipTunnelCount&quot;)' >隧道<span>"+data.tunnelCount+"</span></li>"+
													"<li id='ipStationCount' onclick='setMapImpotPlaceTable(&quot;ipStationCount&quot;)'>站场<span>"+data.stationCount+"</span></li>"+
													"<li id='ipTrajectionCount' onclick='setMapImpotPlaceTable(&quot;ipTrajectionCount&quot;)' >易穿行<span>"+data.trajectionCount+"</span></li>"+
													"<li id='ipDangerCount' style='width:25%;'>隐患点<span id='ipDangeCountSpan'>"+data.dangerCount+"</span></li>"+
													"<div id='keyPartsInput' class='exmrsc_frist_danger'></div>"+ 
													"<div id='keyPartsList'  class='ex_main_danger_list'></div>"+
												"</ul>"+
    				                 		 "</div>";
		    			$('.exmrsc_frist ').html(tempKpNumberHtml);
		    			
		    			$("#importPlaceCount").prop("checked",false);
		    			$("#keyPersonCount").prop("checked",false);
		 				$("#ipBcCount").prop("checked",false);	
		 				$("#ipJunctionCount").prop("checked",false);
		 				$("#ipTunnelCount").prop("checked",false);
		 				$("#ipStationCount").prop("checked",false);
		 				$("#ipTrajectionCount").prop("checked",false);
		 				/* $("#ipDangerCount").prop("checked",false); */
		 				$("#ipDangerCount").on("click", function(){
		 					setMapDangerTable("total");
		 				});
						//设置重点部位所有图层不可见
						BJBridgeAndCulvertLayer.setVisible(false);
						BJJunctionLayer.setVisible(false);
						BJTunnelLayer.setVisible(false);
						BJPartStationLayer.setVisible(false);
						BJTrajectionPolygonLayer.setVisible(false);
						BJKeyPersonLayer.setVisible(false);
						BJDangerPointLayer.setVisible(false);
						BJGuardStationLayer.setVisible(false);
						BJPropaGandaLayer.setVisible(false);
						BJBroadCastLayer.setVisible(false);
						BJMonitorLayer.setVisible(false);
						BJPoliceStationLayer.setVisible(false);
						BJPolicePtationLayer.setVisible(false);
						BJFullTimeMemberLayer.setVisible(false);
						BJAppLayer.setVisible(false);
		    			getPlaceDangerListInfo(railId);
		    			 //模拟option点击事件   隐患点
		    		    $('#keyPartsInput').click(function(){
		    		    	if($('#keyPartsList').is(':hidden')){//如果当前隐藏  
		    		    		$('#keyPartsList').show();//那么就显示div  
		    	         	}else{//否则  
		    		            $('#keyPartsList').hide();//就隐藏div  
		    	         	}
		    		    });
		    		    $('body').click(function(e) {
		    			  if(e.target.id != 'keyPartsInput' && e.target.id != 'keyPartsList')
		    		   		if($('#keyPartsList').is(':visible') ) {
		    		     		$('#keyPartsList').slideUp(200);
		    		   		}
		    			});
					  	//刷新地图数据============================================
		    		}
		 		});
		 	}
		 	
		 	function setMapImpotPlaceTable(type){
		 		switch(type){
		 		case "importPlaceCount":
		 			if($("#importPlaceCount").prop("checked")){
		 				$("#importPlaceCount").prop("checked",false);
		 				$("#ipBcCount").prop("checked",false).removeClass();	
		 				$("#ipJunctionCount").prop("checked",false).removeClass();
		 				$("#ipTunnelCount").prop("checked",false).removeClass();
		 				$("#ipStationCount").prop("checked",false).removeClass();
		 				$("#ipTrajectionCount").prop("checked",false).removeClass();
		 				$("#ipDangerCount").prop("checked",false).removeClass();
		 				//刷新地图数据============================================
		 				BJBridgeAndCulvertLayer.setVisible(false);
		 				BJJunctionLayer.setVisible(false);
		 				BJTunnelLayer.setVisible(false);
		 				BJPartStationLayer.setVisible(false);
		 				BJTrajectionPolygonLayer.setVisible(false);
		 				BJDangerPointLayer.setVisible(false);
		 				//地图弹窗关闭
		 				closePw();
		 				
			 		}else{
			 			//地图弹窗关闭
		 				closePw();
			 			//刷新地图数据============================================
			 			$("#importPlaceCount").prop("checked",true);
			 			$("#ipBcCount").prop("checked",true).removeClass().addClass("check");	
		 				$("#ipJunctionCount").prop("checked",true).removeClass().addClass("check");
		 				$("#ipTunnelCount").prop("checked",true).removeClass().addClass("check");
		 				$("#ipStationCount").prop("checked",true).removeClass().addClass("check");
		 				$("#ipTrajectionCount").prop("checked",true).removeClass().addClass("check");
		 				$("#ipDangerCount").prop("checked",true).removeClass().addClass("check");
		 				
		 				setBJBridgeAndCulvertLayer(adminDivisionOrgId,railIdArray);
		 				setBJBridgeAndCulvertLayer(adminDivisionOrgId,railIdArray);
		 				setJunctionLayer(adminDivisionOrgId,railIdArray);
		 				setTunnelLayer(adminDivisionOrgId,railIdArray);
		 				setPartStationLayer(adminDivisionOrgId,railIdArray);
		 				setTrajectionPolygonLayer(adminDivisionOrgId,railIdArray);
		 				//刷新隐患点
		 				setDangerPointLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		case "keyPersonCount":
		 			if($("#keyPersonCount").prop("checked")){
		 				$("#keyPersonCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyperson");
		 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
		 				BJKeyPersonLayer.setVisible(false);
		 				closePw();
			 		}else{
			 			$("#keyPersonCount").prop("checked",true).removeClass().addClass("exmrsc_frist_Keyperson_check");
			 			setKeyPersonLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		case "ipBcCount":
		 			if($("#ipBcCount").prop("checked")){
		 				$("#ipBcCount").prop("checked",false).removeClass();
		 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
		 				BJBridgeAndCulvertLayer.setVisible(false);
		 				closePw();
			 		}else{
			 			$("#ipBcCount").prop("checked",true).removeClass().addClass("check");
			 			setBJBridgeAndCulvertLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		case "ipJunctionCount":
		 			if($("#ipJunctionCount").prop("checked")){
		 				$("#ipJunctionCount").prop("checked",false).removeClass();
		 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
		 				BJJunctionLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#ipJunctionCount").prop("checked",true).removeClass().addClass("check");
			 			setJunctionLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		case "ipTunnelCount":
		 			if($("#ipTunnelCount").prop("checked")){
		 				$("#ipTunnelCount").prop("checked",false).removeClass();
		 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
		 				BJTunnelLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#ipTunnelCount").prop("checked",true).removeClass().addClass("check");
			 			setTunnelLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		case "ipStationCount":
		 			if($("#ipStationCount").prop("checked")){
		 				$("#ipStationCount").prop("checked",false).removeClass();
		 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
		 				BJPartStationLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#ipStationCount").prop("checked",true).removeClass().addClass("check");
			 			setPartStationLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		case "ipTrajectionCount":
		 			if($("#ipTrajectionCount").prop("checked")){
		 				$("#ipTrajectionCount").prop("checked",false).removeClass();
		 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
		 				BJTrajectionPolygonLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#ipTrajectionCount").prop("checked",true).removeClass().addClass("check");
			 			setTrajectionPolygonLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		}
		 		if($("#ipBcCount").prop("checked") &&
	 					$("#ipJunctionCount").prop("checked") &&
	 					$("#ipTunnelCount").prop("checked") &&
	 					$("#ipStationCount").prop("checked") &&
	 					$("#ipTrajectionCount").prop("checked") &&
	 					$("#ipDangerCount").prop("checked")){
	 				$("#importPlaceCount").prop("checked",true).removeClass().addClass("exmrsc_frist_Keyparts_check");
	 			}else{
	 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
	 				closePw();
	 			}
		 	}
		 	
		 	//根据当前用户机构和铁路获得隐患点类型统计
		 	function getPlaceDangerListInfo(railId){
		 		$.ajax({
		    		url:"${ctx}/exhibition/getPlaceDangerListInfo?orgId="+adminDivisionOrgId+railId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			 $('#keyPartsInput').html('全部');
		    			 var tempDgNumberHtml="";
		    				for(var i=0;i<data.dangerTypeList.length;i++){
		    					tempDgNumberHtml+="<div onclick='getPlaceDangerListInfoCount(&quot;"+data.dangerTypeList[i].dictName+"&quot;,&quot;"+data.dangerTypeList[i].dictCount+"&quot;,&quot;"+data.dangerTypeList[i].dictData+"&quot;)' data-dictdata='"+data.dangerTypeList[i].dictData+"' data-dictcount='"+data.dangerTypeList[i].dictCount+"'>"+data.dangerTypeList[i].dictName+"</div>";
		    				}
							$('.ex_main_danger_list ').html(tempDgNumberHtml);
		    		}
		 		});
		 	}
		 	//隐患点 selector 类型 数量 
		 	function getPlaceDangerListInfoCount(name,count,type){
		 		closePw();
		 		$('.ex_main_danger_list div').each(function(){
			 		$('#keyPartsInput').html(name);
	 				$('#ipDangeCountSpan').html(count);
		 		});
		 		//判断是否选中
		 		$("#ipDangerCount").off("click");
		 		$("#ipDangerCount").on("click", function(){
		 			setMapDangerTable(type);
		 		});
		 		if($("#ipDangerCount").prop("checked")){
		 			//刷新地图数据============================================
		 			switch(type){
			 		case "total":
		 				setDangerPointLayer(adminDivisionOrgId,railIdArray);
			 			break;
			 		default:
			 			setDangerPointLayer(adminDivisionOrgId,railIdArray,type);
			 			break;
			 		} 
		 		}
		 	}
		 	//隐患点checkbox点击
		 	function setMapDangerTable(type){
		 		if($("#ipDangerCount").prop("checked")){
		 			$("#ipDangerCount").prop("checked",false).removeClass();
		 			BJDangerPointLayer.setVisible(false);	
		 		}else{
		 			$("#ipDangerCount").prop("checked",true).removeClass().addClass("check");
		 			switch(type){
			 		case "total":
		 				setDangerPointLayer(adminDivisionOrgId,railIdArray);
			 			break;
			 		default:
			 			setDangerPointLayer(adminDivisionOrgId,railIdArray,type);
			 			break;
			 		} 
		 		}
		 		/* if($("#importPlaceCount").prop("checked")){
		 			$("#ipDangerCount").prop("checked",true).addClass('check');
		 			
		 		}else{
		 			$("#ipDangerCount").prop("checked",false).removeClass();
		 		} */
		 		if($("#ipBcCount").prop("checked") &&
	 					$("#ipJunctionCount").prop("checked") &&
	 					$("#ipTunnelCount").prop("checked") &&
	 					$("#ipStationCount").prop("checked") &&
	 					$("#ipTrajectionCount").prop("checked") &&
	 					$("#ipDangerCount").prop("checked")){
	 				$("#importPlaceCount").prop("checked",true).removeClass().addClass("exmrsc_frist_Keyparts_check");
	 			}else{
	 				$("#importPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_frist_Keyparts");
	 				closePw();
	 			}
		 	}
		 	//根据区域显示联防点位
		    function getUserJDenfenseListInfoInfo(){
		    	var railId="";
		 		for(var i=0;i<railIdArray.length;i++){
		 			railId+="&railId="+railIdArray[i];
		 		}
		    	$.ajax({
		    		url:"${ctx}/exhibition/getUserJDenfenseListInfo?orgId="+adminDivisionOrgId+railId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			var tempJdfNumber="";
		    			var tempTotal=  parseInt(data.stationCount) +
										parseInt(data.broadcastCount) +
										parseInt(data.propagandaCount) +
										parseInt(data.monitorCount) +
										parseInt(data.policeHouseCount) +
										parseInt(data.policeStationCount) +
										parseInt(data.teamUserCount)+
										parseInt(data.userCount);
	    			    tempJdfNumber +=
				    			    	"<div class='exmrsc_second_header'>"+
			    							"<div class='exmrsc_second_defense'  id='JDPlaceCount' onclick='setMapJDTable(&quot;JDPlaceCount&quot;)'>联防点位<span>"+tempTotal+"</span></div>"+
			    						"</div>"+
			    						"<div class='exmrsc_second_content'>"+
			    						"<ul>"+
											"<li id='jdStationCount' onclick='setMapJDTable(&quot;jdStationCount&quot;)' >工作站<span>"+data.stationCount+"</span></li>"+
											"<li id='jdPropagandaCount' onclick='setMapJDTable(&quot;jdPropagandaCount&quot;)' >宣传点<span>"+data.propagandaCount+"</span></li>"+
											"<li id='jdBroadcastCount' onclick='setMapJDTable(&quot;jdBroadcastCount&quot;)'>广播柱<span>"+data.broadcastCount+"</span></li>"+
											"<li id='jdMonitorCount' onclick='setMapJDTable(&quot;jdMonitorCount&quot;)' >摄像头<span>"+data.monitorCount+"</span></li>"+
											"<li id='jdPoliceHouseCount' onclick='setMapJDTable(&quot;jdPoliceHouseCount&quot;)' >派出所<span>"+data.policeHouseCount+"</span></li>"+
											"<li id='jdPoliceStationCount' onclick='setMapJDTable(&quot;jdPoliceStationCount&quot;)' >警务站<span>"+data.policeStationCount+"</span></li>"+
											"<li id='jdTeamUserCount' onclick='setMapJDTable(&quot;jdTeamUserCount&quot;)' >在线队员<span id='teamNumber'>"+data.teamUserCount+"</span></li>"+
											"<li id='jdUserCount' onclick='setMapJDTable(&quot;jdUserCount&quot;)' >在线干部<span id='teamCadreNumber'>"+data.userCount+"</span></li>";
										"</ul>"+
			    						"</div>";
	    			    	
		    			$('.exmrsc_second').html(tempJdfNumber);
		    			$("#jdStationCount").prop("checked",false);
		    			$("#jdBroadcastCount").prop("checked",false);
		 				$("#jdPropagandaCount").prop("checked",false);	
		 				$("#jdMonitorCount").prop("checked",false);	
		 				$("#jdPoliceHouseCount").prop("checked",false);
		 				$("#jdPoliceStationCount").prop("checked",false);
		 				$("#jdTeamUserCount").prop("checked",false);
		 				$("#jdUserCount").prop("checked",false);
		 				//清除广播柱
		 				clearInterval(setInterTime);
		 				//广播柱清除
		 				/* for(var i = 0; i<overlaysDanger.length;i++){
							map.removeOverlay(overlaysDanger[i])
						}; */
		    		}
		    	});
		    }
		    function setMapJDTable(type){
				switch(type){
		 		case "JDPlaceCount":
		 			if($("#JDPlaceCount").prop("checked")){
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass('exmrsc_second_defense');
		 				$('#jdStationCount').prop("checked",false).removeClass();	
		 				$("#jdBroadcastCount").prop("checked",false).removeClass();	
		 				$("#jdPropagandaCount").prop("checked",false).removeClass();
		 				$("#jdMonitorCount").prop("checked",false).removeClass();
		 				$("#jdPoliceHouseCount").prop("checked",false).removeClass();
		 				$("#jdPoliceStationCount").prop("checked",false).removeClass();
		 				$("#jdTeamUserCount").prop("checked",false).removeClass();
		 				$("#jdUserCount").prop("checked",false).removeClass();
		 				//刷新地图数据============================================
		 				BJGuardStationLayer.setVisible(false);
		 				BJBroadCastLayer.setVisible(false);
		 				BJGuardStationLayer.setVisible(false);
		 				BJPropaGandaLayer.setVisible(false);
		 				BJMonitorLayer.setVisible(false);
		 				BJPoliceStationLayer.setVisible(false);
		 				BJPolicePtationLayer.setVisible(false);
		 				BJFullTimeMemberLayer.setVisible(false);
		 				BJAppLayer.setVisible(false);
		 				teamMember=false;
		 				teamCadre=false;
		 				//地图弹窗关闭
		 				closePw();
		 				//广播柱清除
		 				clearInterval(setInterTime);
		 				/* for(var i = 0; i<overlaysDanger.length;i++){
							map.removeOverlay(overlaysDanger[i])
						} */
			 		}else{
			 			//地图弹窗关闭
		 				closePw();
			 			//刷新地图数据============================================
			 			$("#JDPlaceCount").prop("checked",true).removeClass().addClass("exmrsc_second_defense_hover");
			 			$('#jdStationCount').prop("checked",true).removeClass().addClass("check");	
			 			$("#jdBroadcastCount").prop("checked",true).removeClass().addClass("check");
			 			$("#jdPropagandaCount").prop("checked",true).removeClass().addClass("check");
			 			$("#jdMonitorCount").prop("checked",true).removeClass().addClass("check");
			 			$("#jdPoliceHouseCount").prop("checked",true).removeClass().addClass("check");
			 			$("#jdPoliceStationCount").prop("checked",true).removeClass().addClass("check");
			 			$("#jdTeamUserCount").prop("checked",true).removeClass().addClass("check");
			 			$("#jdUserCount").prop("checked",true).removeClass().addClass("check");
			 			setGuardStationLayer(adminDivisionOrgId,railIdArray);
			 			setBroadCastLayer(adminDivisionOrgId,railIdArray);
			 			setPropaGandaLayer(adminDivisionOrgId,railIdArray);
			 			setMonitorLayer(adminDivisionOrgId,railIdArray);
			 			setPoliceStationLayer(adminDivisionOrgId,railIdArray);
			 			setPolicePtationLayer(adminDivisionOrgId,railIdArray);
			 			setFullTimeMemberLayer(adminDivisionOrgId,railIdArray);
			 			setAppLayer(adminDivisionOrgId,railIdArray);
			 			teamMember=true;
			 			teamCadre=true;
			 		}
		 			break;
		 		//工作站
		 		case "jdStationCount":
		 			if($("#jdStationCount").prop("checked")){
		 				$("#jdStationCount").prop("checked",false).removeClass();
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
		 				BJGuardStationLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#jdStationCount").prop("checked",true).removeClass().addClass("check");
			 			setGuardStationLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		//广播警示柱
		 		case "jdBroadcastCount":
		 			if($("#jdBroadcastCount").prop("checked")){
		 				$("#jdBroadcastCount").prop("checked",false).removeClass();
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
		 				BJBroadCastLayer.setVisible(false);
		 				/* for(var i = 0; i<overlaysDanger.length;i++){
							map.removeOverlay(overlaysDanger[i])
						} */
		 				closePw();
						//清除广播柱
		 				clearInterval(setInterTime);
		 			
		 			}else{
			 			$("#jdBroadcastCount").prop("checked",true).removeClass().addClass("check");
			 			setBroadCastLayer(adminDivisionOrgId,railIdArray)
			 			setInterTime = setInterval("setBroadCastLayer(adminDivisionOrgId,railIdArray)",5000)
			 		}
	 			break;
		 		//宣传点
		 		case "jdPropagandaCount":
		 			if($("#jdPropagandaCount").prop("checked")){
		 				$("#jdPropagandaCount").prop("checked",false).removeClass();
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
		 				BJPropaGandaLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#jdPropagandaCount").prop("checked",true).removeClass().addClass("check");
			 			setPropaGandaLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		//摄像头
		 		case "jdMonitorCount":
		 			if($("#jdMonitorCount").prop("checked")){
		 				$("#jdMonitorCount").prop("checked",false).removeClass();
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
		 				BJMonitorLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#jdMonitorCount").prop("checked",true).removeClass().addClass("check");
			 			setMonitorLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
		 		//派出所
		 		case "jdPoliceHouseCount":
		 			if($("#jdPoliceHouseCount").prop("checked")){
		 				$("#jdPoliceHouseCount").prop("checked",false).removeClass();
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
		 				BJPoliceStationLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#jdPoliceHouseCount").prop("checked",true).removeClass().addClass("check");
			 			setPoliceStationLayer(adminDivisionOrgId,railIdArray);
			 		}
		 			break;
				
		 		//警务站
				case "jdPoliceStationCount":
		 			if($("#jdPoliceStationCount").prop("checked")){
		 				$("#jdPoliceStationCount").prop("checked",false).removeClass();
		 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
		 				BJPolicePtationLayer.setVisible(false);
		 				closePw();
		 			}else{
			 			$("#jdPoliceStationCount").prop("checked",true).removeClass().addClass("check");
			 			setPolicePtationLayer(adminDivisionOrgId,railIdArray);
			 		}
	 				break;
	 			//专职队员
		    	case "jdTeamUserCount":
		 			if($("#jdTeamUserCount").prop("checked")){
		 				if(teamMember){
		 					$("#jdTeamUserCount").prop("checked",false).removeClass();
			 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
			 				BJFullTimeMemberLayer.setVisible(false);
			 				closePw();
			 				teamMember=false;
		 				}
		 			}else{
			 			$("#jdTeamUserCount").prop("checked",true).removeClass().addClass("check");
			 			setFullTimeMemberLayer(adminDivisionOrgId,railIdArray);
			 			teamMember=true;
			 		}
		 			break;
		 		//在线干部
	 			case "jdUserCount":
		 			if($("#jdUserCount").prop("checked")){
		 				if(teamCadre){
			 				$("#jdUserCount").prop("checked",false).removeClass();
			 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
			 				BJAppLayer.setVisible(false);	
			 				closePw();
			 				teamCadre=false;
		 				}
		 			}else{
			 			$("#jdUserCount").prop("checked",true).removeClass().addClass("check");
			 			setAppLayer(adminDivisionOrgId,railIdArray);
			 			teamCadre=true;
			 		}
		 			break;
		 		}
				if($("#jdStationCount").prop("checked") &&
	 					$("#jdBroadcastCount").prop("checked") &&
	 					$("#jdPropagandaCount").prop("checked") &&
	 					$("#jdMonitorCount").prop("checked") &&
	 					$("#jdPoliceHouseCount").prop("checked") &&
	 					$("#jdPoliceStationCount").prop("checked")&&
	 					$("#jdTeamUserCount").prop("checked")&&
	 					$("#jdUserCount").prop("checked")
				){
	 				$("#JDPlaceCount").prop("checked",true).removeClass().addClass("exmrsc_second_defense_hover");
	 			}else{
	 				$("#JDPlaceCount").prop("checked",false).removeClass().addClass("exmrsc_second_defense");
	 				closePw();
	 			}
		    }
		    // 根据区域显示周边场所
			function getUserPeripheralPlaceListInfoInfo(){
				var railId="";
		 		for(var i=0;i<railIdArray.length;i++){
		 			railId+="&railId="+railIdArray[i];
		 		}
			 	$.ajax({
			 		url:"${ctx}/exhibition/getUserPeripheralPlaceListInfo?orgId="+adminDivisionOrgId+railId,
			 		type:'POST',
			 		dataType:'json',
			 		async: false,
			 		error: function(XMLHttpRequest, textStatus, errorThrown) {
			 			console.log("响应状态:["+XMLHttpRequest.status+"]-");
			 			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
			 			console.log("异常情况:["+textStatus+"]");
			 			},
			 		success:function(data){
			 			perPlacyTypeArray = new Array();
			 			//周边场所
			 			var tempPerPlaceNumber="";
			 			//更多
			 			var tempPerPlaceNumberMore="";
			 			$("#exmrscThirdHeaderPlaceCount").html(data.perPlaceTypeList[0].dictCount);
			 			$("#exmrscThirdHeaderKeyplaceCount").html(data.perPlaceTypeList[0].dictImportCount);
			 			for(var i=1;i<data.perPlaceTypeList.length;i++){
			 				if(i<8){
			 					tempPerPlaceNumber += "<li data-perplacetype='"+data.perPlaceTypeList[i].dictData+"' "+
			 							"data-count='"+data.perPlaceTypeList[i].dictCount+"' "+
			 							"data-import-count='"+data.perPlaceTypeList[i].dictImportCount+"' "+
			 							"onclick='setPerPlaceType(&quot;"+data.perPlaceTypeList[i].dictData+"&quot;)'>"+
			 							data.perPlaceTypeList[i].dictName+"<span>"+data.perPlaceTypeList[i].dictCount+"</span></li>";
			 					$('.exmrsc_third_content ul').html(tempPerPlaceNumber);
			 				}else{
			 					tempPerPlaceNumberMore += "<li data-perplacetype='"+data.perPlaceTypeList[i].dictData+"' "+
			 					"data-count='"+data.perPlaceTypeList[i].dictCount+"' "+
	 							"data-import-count='"+data.perPlaceTypeList[i].dictImportCount+"' "+
			 					"onclick='setPerPlaceType(&quot;"+data.perPlaceTypeList[i].dictData+"&quot;)'>"+
			 					data.perPlaceTypeList[i].dictName+"<span>"+data.perPlaceTypeList[i].dictCount+"</span></li>";
			 					$('.exmrsc_thirdc_morec ul').html(tempPerPlaceNumberMore);
			 				   /* 右侧菜单 周边场所更多 */
			 				    $('.exmrsc_thirdc_more').mouseenter(function(){
			 				    	$('.exmrsc_thirdc_morec').show();
			 				    });
			 				    $('.exmrsc_thirdc_more_content').mouseleave(function(){
			 				    	$('.exmrsc_thirdc_morec').hide();
			 				    });
			 				}
			 			}
			 			$("#exmrscThirdHeaderPlace").removeClass().addClass("exmrsc_third_header_Keyplace").prop('check',false);
			 			$("#exmrscThirdHeaderKeyplace").removeClass().addClass("exmrsc_third_header_Keyplace").prop('check',false);
			 			$('.exmrsc_third_content li').each(function(){
			 				$(this).prop('check',false);
			 			});
			 			BJPeripheralPlaceLayer.setVisible(false);
			 		}	
			 	});
			}
			  //周边场所点击
		    function setPerPlaceType(perPlaceType){
		    	perPlacyTypeArray = new Array();
		    	perPlaceTypeSelectAll = true;
		    	perPlaceIsRefresh = false;
		    	$('.exmrsc_third_content ul li').each(function(){
		    		if($(this).data("perplacetype")==perPlaceType){
		    			if($(this).prop('check')){
		    				$(this).prop('check',false).removeClass();
		    				closePw();
		    			}else{
		    				$(this).prop('check',true).removeClass().addClass("select");
		    			}
		    		}
		    		if($(this).prop('check')){
		    			perPlaceIsRefresh = true;
		    			perPlacyTypeArray.push($(this).data('perplacetype'));
		    		}else{
		    			perPlaceTypeSelectAll = false;
		    		}
	 			});
		    	if(perPlaceTypeSelectAll){
	    			$("#exmrscThirdHeaderPlace").prop('check',true).removeClass().addClass("exmrsc_third_header_Keyplace_hover");
	    		}else{
	    			$("#exmrscThirdHeaderPlace").prop('check',false).removeClass().addClass("exmrsc_third_header_Keyplace");
	    		}
		    	if(perPlaceIsRefresh){
		    		if($("#exmrscThirdHeaderKeyplace").prop('check')){
		    			//刷新数据
		    			setPeripheralPlaceLayer(adminDivisionOrgId,railIdArray,perPlacyTypeArray,"1");
		    		}else{
		    			//刷新数据
		    			setPeripheralPlaceLayer(adminDivisionOrgId,railIdArray,perPlacyTypeArray);
		    		}
		    	}else{
		    		BJPeripheralPlaceLayer.setVisible(false);
		    	}
		    }
		    //
		    function setPerPlaceSelectAll(){
		    	perPlacyTypeArray = new Array();
		    	if($("#exmrscThirdHeaderPlace").prop('check')){
		    		//关闭地图弹窗
		    		closePw();
		    		$("#exmrscThirdHeaderPlace").prop('check',false).removeClass().addClass("exmrsc_third_header_Keyplace");
		    		$('.exmrsc_third_content ul li').each(function(){
		    			$(this).prop('check',false).removeClass();
		    		});
		    		//刷新数据
		    		BJPeripheralPlaceLayer.setVisible(false);
		    	}else{
		    		//关闭地图弹窗
		    		closePw();
		    		$("#exmrscThirdHeaderPlace").prop('check',true).removeClass().addClass("exmrsc_third_header_Keyplace_hover");
		    		$('.exmrsc_third_content ul li').each(function(){
		    			$(this).prop('check',true).removeClass().addClass("select");
		    			perPlacyTypeArray.push($(this).data("perplacetype"));
		    		});
		    		//刷新数据
		    		if($("#exmrscThirdHeaderKeyplace").prop('check')){
		    			//刷新数据
		    			setPeripheralPlaceLayer(adminDivisionOrgId,railIdArray,perPlacyTypeArray,"1");
		    		}else{
		    			//刷新数据
		    			setPeripheralPlaceLayer(adminDivisionOrgId,railIdArray,perPlacyTypeArray);
		    		}
		    	}
		    }
		    //设置重点选中 并跟新相关周边场所重点个数
		    function setPerImportPlaceSelect(){
		    	var perPlaceIsRefresh = false;
		    	$('.exmrsc_third_content ul li').each(function(){
		    		if($(this).prop('check')){
		    			perPlaceIsRefresh = true;
		    		}
	 			});
		    	if($("#exmrscThirdHeaderKeyplace").prop('check')){
		    		//关闭地图弹窗
		    		closePw();
		    		$("#exmrscThirdHeaderKeyplace").prop('check',false).removeClass().addClass("exmrsc_third_header_Keyplace");
		    		$('.exmrsc_third_content ul li').each(function(){
		    			$(this).children('span').html($(this).data("count"));
		    		});
		    		//刷新数据
		    		if(perPlaceIsRefresh){
		    			setPeripheralPlaceLayer(adminDivisionOrgId,railIdArray,perPlacyTypeArray);
		    		}
		    	}else{
		    		//关闭地图弹窗
		    		closePw();
		    		$("#exmrscThirdHeaderKeyplace").prop('check',true).removeClass().addClass("exmrsc_third_header_Keyplace_hover");
		    		$('.exmrsc_third_content ul li').each(function(){
		    			$(this).children('span').html($(this).data("import-count"));
		    		});
		    		//刷新数据
		    		if(perPlaceIsRefresh){
		    			setPeripheralPlaceLayer(adminDivisionOrgId,railIdArray,perPlacyTypeArray,"1");
		    		}
		    	}
		    }
		 //即时菜单 弹窗关闭
		 function closePw(){
			 $(".ex_main_rightheader_pw4").fadeOut('slow');
			 $(".ol-popup-closer").click();
			 BJRailLocationLayer.setVisible(false);
			 BJRailGPSLocationLayer.setVisible(false);
			 BJPatrolTrajectoryLayer.setVisible(false);
			 BJPartStationSignalLayer.setVisible(false);
		 }
		$(".exmrsc_content_header_left_content").mCustomScrollbar();
		/* 区域 ------区 详细列表弹出框 */
		function regionList(orgId,level){
			$.ajax({
	    		url:"${ctx}/exhibition/map/getUserOrgListInfo?orgId="+orgId+"&orgLevel="+level,
	    		type:'POST',
	    		dataType:'json',
	    		async: false,
	    		error: function(XMLHttpRequest, textStatus, errorThrown) {
	    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
	    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
	    			console.log("异常情况:["+textStatus+"]");
	    			},
	    		success:function(data){
	    			var tempRegionAndStreetHtml = "";
	    			for(var i=0;i<data.result.length;i++){
	    				tempRegionAndStreetHtml += "<li>"+data.result[i].orgName+"</li>";
	    			}
	    			$('#regionStreet ul').html(tempRegionAndStreetHtml);
	    			$("#regionStreet").mCustomScrollbar();
	    		}
	    	});
			$('.ex_main_rightheader_pw7').hide();
			$('.ex_main_rightheader_pw6').show();
		}
		/* 区域 ------区 详细列表弹出框 关闭 */
		function closeRegionList(){
			$('.ex_main_rightheader_pw6').hide();
		}
		$(function(){
			if("${orgId}"=="110"){
				$('#exRegionOpition li').each(function(){ 
					$(this).mouseover(function(){
						if($(this).attr('id')!="110"){
							show($(this).attr('id'),2);
						}
						$('.ex_main_rightheader_pw7_content').mCustomScrollbar();
					})	
					$('#exRegionOpition li:first-child').unbind("mouseover");
				})
				$('.ex_main_rightheader_pw7').mouseleave(function(){
					$('.ex_main_rightheader_pw7').hide()
				})
			}
		})
		
		function show(orgId,level){
			$.ajax({
	    		url:"${ctx}/exhibition/map/getUserOrgListInfo?orgId="+orgId+"&orgLevel="+level,
	    		type:'POST',
	    		dataType:'json',
	    		async: false,
	    		error: function(XMLHttpRequest, textStatus, errorThrown) {
	    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
	    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
	    			console.log("异常情况:["+textStatus+"]");
	    		},
	    		success:function(data){
	    			var tempOrgHtml = "";
	    			for(var i=0;i<data.result.length;i++){
	    				tempOrgHtml += "<div onclick='setAdminDivision(&quot;"+data.result[i].orgId+"&quot;,&quot;"+data.result[i].orgName+"&quot;)'>"+data.result[i].orgName+"</div>";
	    			}
	    			$('.ex_main_rightheader_pw7').show();
	    			$('#emrpc7').html(tempOrgHtml);
	    			$('.ex_main_rightheader_pw7_content').mCustomScrollbar();
	    		}
	    	});
		}
		//距离测量
		function emrmRanging(){
			if($('.ex_main_rightheader_measure_ranging').hasClass('ex_main_rightheader_measure_ranging')){
				$('.ex_main_rightheader_measure_ranging').removeClass().addClass('ex_main_rightheader_measure_ranging_hover');
				$('.ex_main_rightheader_measure_surface_hover').removeClass().addClass('ex_main_rightheader_measure_surface');
				//选中
				map.removeInteraction(measureDraw);
				addInteraction("0");
				
			}else{
				$('.ex_main_rightheader_measure_ranging_hover').removeClass().addClass('ex_main_rightheader_measure_ranging');
				$('.ex_main_rightheader_measure_surface_hover').removeClass().addClass('ex_main_rightheader_measure_surface');
				//清除
				map.removeInteraction(measureDraw);
			}
		}
		//面积测量
		function emrmSurface(){
			if($('.ex_main_rightheader_measure_surface').hasClass('ex_main_rightheader_measure_surface')){
				$('.ex_main_rightheader_measure_surface').removeClass().addClass('ex_main_rightheader_measure_surface_hover');
				$('.ex_main_rightheader_measure_ranging_hover').removeClass().addClass('ex_main_rightheader_measure_ranging');
				//选中
				map.removeInteraction(measureDraw);
				addInteraction("1");
			}else{
				$('.ex_main_rightheader_measure_surface_hover').removeClass().addClass('ex_main_rightheader_measure_surface');
				$('.ex_main_rightheader_measure_ranging_hover').removeClass().addClass('ex_main_rightheader_measure_ranging');
				//清除
				map.removeInteraction(measureDraw);
			}
		} 
		//线路定位GPS
		function railGPSLocation(){
			var lng =$('#GpsLng').val();
			var lat =$('#GpsLat').val();
			$.ajax({
	    		url:"${ctx}/org/checkOrgId",
	    		type:'POST',
	    		dataType:'json',
	    		async: false,
	    		data:{lng:lng,lat:lat},
	    		error: function(XMLHttpRequest, textStatus, errorThrown) {
	    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
	    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
	    			console.log("异常情况:["+textStatus+"]");
	    		},
	    		success:function(data){
	    			if(data.mes){
	    				alert(data.mes);
	    			}else{
	    				BJRailGPSLocationLayer.getSource().clear();
	    				var GPSLocation = new ol.Feature({
	    					geometry: new ol.geom.Point([lng,lat]),
	    					lng:lng,
	    					lat:lat
	    				});
	    				GPSLocation.setStyle(railGPSLocationStyle);
	    				BJRailGPSLocationLayer.getSource().addFeature(GPSLocation);
	    				BJRailGPSLocationLayer.setVisible(true);
	    				
	    				coordinate = [lng,lat];
	    				var tempBorderFeatures = BJBorderSource.getFeaturesAtCoordinate(coordinate);
	    				var tempJDBorderFeatures = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
	    				
	    				var name =tempBorderFeatures[0].get("ORG_NAME")+","+tempJDBorderFeatures[0].get("ORG_NAME");
	    				$('#popup-content').html(name);
	    				overlay.setPosition(coordinate); 
	    				$('.ol-popup').css('bottom','20px');
	    			}
	    		}
	    	});
		
		}
		//线路定位KM
		function railLocation(){
			var  k = document.getElementById('locationRailK').value;
			k = parseFloat(k);
			/* if(isNaN(k)) {
				alert("请输入数字");
				return;
			} */
			k = isNaN(k) ? 0 : k;
			var m = document.getElementById('locationRailM').value;
			m = parseFloat(m);
			/* if(isNaN(m)) {
				k="";
				m="";
				alert("请输入数字");
				return;
			} */
			m = isNaN(m) ? 0 : m;
			var  z = k * 1000 + m;
			var s = k+ m/1000;
			var railId = $('#exmrpwInput').data('rail-id');
			var railLocationName = $('#exmrpwInput').val();
			var name;
			if($('#exmrpwInput').val()==''){
				alert('请选择铁路线');
				if(k == ''){
					$('#locationRailK').val('0');
				}
				if(m == ''){
					$('#locationRailM').val('0');
				}
			}else{
				$.ajax({
					url : '${ctx}/exhibition/map/CalculationLnglat',
					type : 'post',
					data: {
						'km': z,
						'id': railId,
					},
					dataType : 'json',
					error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
	    			},
					success : function(data) {
						console.log('----------',s)
						 if(data.type=="locationNo"){
							$('.ex_map_location_text').html(data.name+"起点:"+data.startStr+",终点:"+data.endStr);
						}else if(data.type=="location"){
							$('.ex_map_location_text').html('');
							BJRailLocationLayer.getSource().clear();
							var kmLocationLocation = new ol.Feature({
								geometry: new ol.geom.Point([data.lng,data.lat]),
								name:railLocationName,
								lng:data.lng,
								lat:data.lat
							});
							kmLocationLocation.setStyle(railLocationStyle);
							BJRailLocationLayer.getSource().addFeature(kmLocationLocation);
							BJRailLocationLayer.setVisible(true);
							coordinate = [data.lng,data.lat];
		    				var locationQName = BJBorderSource.getFeaturesAtCoordinate(coordinate);
		    				var locationJName = BJJDBorderSource.getFeaturesAtCoordinate(coordinate);
		    				name =locationQName[0].get("ORG_NAME")+","+locationJName[0].get("ORG_NAME");
							coordinate = [data.lng,data.lat];
							var centerPt = map.getPixelFromCoordinate(coordinate);
							mapView.animate({center: map.getCoordinateFromPixel(centerPt),duration: 500});
							$('#popup-content').html(railLocationName);
							overlay.setPosition(coordinate); 
							$('.ol-popup').css('bottom','20px');
							$.ajax({
								url : "${ctx}/exhibition/map/getKmLocationRailPopWin",
								type : "GET",
								dataType : 'html',
								data : {
									"id" : railId,
									"km":z,
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) {
									console.log("响应状态:["+XMLHttpRequest.status+"]-");
									console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
									console.log("异常情况:["+textStatus+"]");
								}, 
								success : function(data) {
									$(".ex_main_rightheader_pw4").html(data);
									$('#kmLocationName').html(railLocationName);
									$(".ex_main_rightheader_pw4").fadeIn('slow');
									$('#QK').html(name);
									$('#Lkm').html(s);
								}
							});	
						}
					}
				})
			}
			
		}
		//安保区
		function securityArea(){
			if($('.securityArea').hasClass('securityArea')){
				$('.securityArea').removeClass().addClass('securityArea_hover');
				//设置安保区
				setBJSecurityAreaLayer(adminDivisionOrgId);
			}else if($('.securityArea_hover').hasClass('securityArea_hover')){
				$('.securityArea_hover').removeClass().addClass('securityArea');
				BJSecurityAreaLayer.setVisible(false);
			}
		}
		//路网
		function lw(){
			if($('.ex_mapLw').hasClass('ex_mapLw')){
				$('.ex_mapLw').removeClass().addClass('ex_mapLw_hover');
				BJLWLayer.setVisible(true);
			}else if($('.ex_mapLw_hover').hasClass('ex_mapLw_hover')){
				$('.ex_mapLw_hover').removeClass().addClass('ex_mapLw');
				BJLWLayer.setVisible(false);
			}
		}
		//视频聊天联系人=================================================================================================
	    //联系人列表
		function chatQ(){
			 $.ajax({
					url : '${ctx}/exhibition/map/findPeoples',
					type : 'post',
					dataType : 'json',
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						console.log("响应状态:[" + XMLHttpRequest.status + "]-");
						console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
						console.log("异常情况:[" + textStatus + "]");
					},
					success : function(data) {
						$("#chatQLeft").mCustomScrollbar("destroy");
						$('.map_chat_left_bottom_record').hide();
						$('.map_chat_left_bottom').show();
						var chatQHtml="";
						console.log('-------------------',data)
						for(var i=0;i<data.length;i++){
							chatQHtml+= "<div style='cursor: pointer;height:30px;line-height:30px;margin:3px 0;padding:10px;' onclick='chatSection(&quot;"+data[i].id+"&quot;,&quot;"+data[i].NAME+"&quot;,1)'>"+
											"<div style='width:30px;height:30px;padding:0;margin:0;background:url(${ctx}/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
											"</div>"+
											"<span class='map_chat_span'>"+data[i].NAME+"</span>"+
										"</div>";
						}
						$('#chatQLeft').html(chatQHtml);
						$('#chatQLeft').mCustomScrollbar();
					}
				});	
		}
		
		//点击联系人
		function chatSection(id,name,type){
			$('.map_chat_sendMess').hide();
			$('.map_chat_right_top').show();
			$('.ex_map_chatMess').hide();
			$('#chatQRight').show();
			$('.map_chat_right_top_menText').html(name);
			meId = id;
			console.log('=========1111名称',name);
			//类别 单人/群聊
			typeO=type;
			//群聊房间名称
			userRoomName = name;
		}
		
		recondListUser = [];
		//点击联系人弹窗的发送消息
		function sendMessage(){
			var meIdList = $('.map_chat_sendMess_center #'+meId);
			if(meIdList.length==0 ){
				$('.map_chat_sendMess_center').append(
						 "<div class='map_chat_sendMess_center_text' id='" + meId  +"'>"+"</div>"
				)
			}
			var lastUserId = $('.map_chat_sendMess_center').data('current-id');
			if(lastUserId) {
				$('#' + lastUserId).hide();
			}
			$('#' + meId).show();
			$('.map_chat_sendMess_center').data('current-id', meId);
			$('.map_chat_sendMess').show();
			$('.map_chat_right_top').hide();
			$('.map_chat_sendMess_header').html($('.map_chat_right_top_menText').html());
			var checkName = $('.map_chat_right_top_menText').html();
			$('.map_chat_left_bottom').hide();
			//判断聊天记录是否存在该人=======================================================================
			$('.map_chat_left_bottom_record').show();
			var recondDivList = $('#recondListB div');
				if(recondListUser.indexOf(meId) != -1 ){
					recondList(meId,checkName) 
				}else{
					$('.map_chat_left_bottom_record').append(
							 "<div data-recond-id='"+meId+"' data-recond-name='"+checkName+"' style='cursor: pointer;height:30px;line-height:30px;margin:3px 0;padding:10px;' onclick='recondList(&quot;"+meId+"&quot;,&quot;"+checkName+"&quot;)'>"+
								"<p style='width:30px;height:30px;padding:0;margin:0;background:url(${ctx}/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
								"</p>"+
								"<span class='map_chat_span'>"+checkName+"</span>"+
							"</div>"
					);
					recondListUser.push(meId);
				}
			}
		//发送聊天消息===================================================================================
		document.onkeydown=function(e){
		    if(e.keyCode == 13 && e.ctrlKey){
		        // 这里实现换行
		        document.getElementById("chatEnterHtml").value += "\n";
		    }else if(e.keyCode == 13){
		        // 避免回车键换行
		        e.preventDefault();
		        // 下面写你的发送消息的代码
		        chatEnter();
		    }
		}
		function chatEnter(){
			var chatEnterHtml = $('#chatEnterHtml').val();
			mytool.sendMessage(chatEnterHtml,meId);
			$('#'+meId).append("<p style='width:100%;text-align:right;word-wrap: break-word;'>"+chatEnterHtml+"</p>");
			$('#chatEnterHtml').val('');
			var scrollHeight = $('.map_chat_sendMess_center_text').prop("scrollHeight");
			$('.map_chat_sendMess_center_text').scrollTop(scrollHeight,200);
			if(typeO == 1){
				var data=[{ 
			        sender_id:userIdM,    //发送人ID 
			        room_id:meId,        //接收ID userRoomName
			        /* room_name:userRoomName, */
			        send_date:new Date(),
			        content:chatEnterHtml,
			        room_user_ids:null,
			        type:'text',
			        chat_type:1,
			    }];
			}else if (typeO == 2){
				var data=[{ 
			        sender_id:userIdM,           //发送人ID 
			        room_id:parseInt(meId),      //接收ID userRoomName
			        room_name:userRoomName,
			        send_date:new Date(),
			        content:chatEnterHtml,
			        room_user_ids:null,
			        type:'text',
			        chat_type:2,
			    }];
			}
			indexDb.instance.addData(indexDb.instance.myDB.db,indexDb.instance.myDB.ojstore.name,data);
		}
		//聊天记录列表显示
		function chatRecondList(){
			mytool.appendRecond();
			$('.map_chat_left_bottom ').hide();
			$('.map_chat_left_bottom_record ').show();
		}
		//聊天记录 点击
		function recondList(id,name){
			/* $('.map_chat_sendMess_center').data('current-id', meId); */
			$('.map_chat_right').show();
			$('.map_chat_sendMess').show();
			$('.map_chat_right_top').hide();
			$('.map_chat_sendMess_header').html(name);
			var userDiv = $('.map_chat_sendMess_center div');
			for(var i=0;i<userDiv.length;i++){
				var userA = userDiv[i];
				var userAId = $(userA).attr('id');
				if(id == userAId){
					meId = id;
					$('#'+userAId).show();
				}else{
					meId = id;
					$('#'+userAId).hide();
				}
			}
		}
		//群聊联系人========================================================
		function chatMess(){
			$.ajax({
				url : '${ctx}/exhibition/map/findPeoples',
				type : 'post',
				dataType : 'json',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("响应状态:[" + XMLHttpRequest.status + "]-");
					console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
					console.log("异常情况:[" + textStatus + "]");
				},
				success : function(data) {
					$('.ex_map_chatMess').show();
					var chatQHtml="";
					console.log('-------------------',data)
					for(var i=0;i<data.length;i++){
						chatQHtml+= "<div class='map_chat_div' id="+data[i].id+" onclick='chatMessAppend(&quot;"+data[i].id+"&quot;,&quot;"+data[i].NAME+"&quot;,this)'>"+
										"<div style='width:30px;height:30px;padding:0;margin:0;background:url(${ctx}/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
										"</div>"+
										"<span class='map_chat_span'>"+data[i].NAME+"</span>"+
									"</div>";
					}
					$('.ex_map_chatMess_left_bottom').html(chatQHtml);
					$(".ex_map_chatMess_left_bottom").mCustomScrollbar();
				}
			});	
		}
		/* chatArray.push('111111'); */
		function chatMessAppend(id,name,evt){
			var checkEvt=$(evt);
			var checkId = $(checkEvt).attr("id")
			if($('#'+checkId).hasClass('map_chat_div')){
				$('#'+checkId).prop("checked",false).removeClass().addClass('map_chat_div_hover')
				$('.ex_map_chatMess_right_center').append('<div class="rightDIV" id='+checkId+'>'+name+'<span class="rightDivt"onclick="closeDiv('+checkId+',this)">'+'</span>'+'</div>');
				chatArray.push(checkId);
				console.log('---已选中',chatArray);
				var divLength = $('.ex_map_chatMess_right_center').children("div").length;
				var divHeight = $('.ex_map_chatMess_right_center').children("div").height();
				if(divHeight > $('.ex_map_chatMess_right_center').height()){
					$('.ex_map_chatMess_right_center').mCustomScrollbar();
				}
				$('.ex_map_chatMess_right_top_left_text').html(divLength);
				if(divLength>=3){
					$('.ex_map_chatMess_right_bottom_btn').removeClass().addClass('ex_map_chatMess_right_bottom_btn_hover')
				}
				if(divLengthT<3){
					$('.ex_map_chatMess_right_bottom_btn_hover').removeClass().addClass('ex_map_chatMess_right_bottom_btn')
				}
	        }else{
	        	$('#'+checkId).prop("checked",true).removeClass().addClass('map_chat_div');
	        	var divList= $('.ex_map_chatMess_right_center div');
	        	for(var i=0;i<divList.length;i++){
	        		var thisDIv = divList[i];
	        		if($(thisDIv).attr('id') == checkId){
	        			$(thisDIv).remove();
	        			chatArray.splice($.inArray('$(thisDIv).attr("id")',chatArray),1);
		        		console.log('--未选中',chatArray)
	        		}
	        		var divLengthT = $('.ex_map_chatMess_right_center').children("div").length
	        	}
	        	$('.ex_map_chatMess_right_top_left_text').html(divLengthT)
	        	if(divLengthT>=3){
					$('.ex_map_chatMess_right_bottom_btn').removeClass().addClass('ex_map_chatMess_right_bottom_btn_hover')
				}
	        	if(divLengthT<3){
					$('.ex_map_chatMess_right_bottom_btn_hover').removeClass().addClass('ex_map_chatMess_right_bottom_btn')
				}
	        }
		}
		//关闭群聊框
		$('.ex_map_chatMess_right_close').click(function(){
			$('.ex_map_chatMess').hide();
			//清除选中联系人
			chatArray=[];
			$('.rightDivt').click();
		})
		//删除选中人
		function closeDiv(id,evt){
			var checkEvt=$(evt);
			$(checkEvt).parent().remove();
			var divLengthRem = $('.ex_map_chatMess_right_center').children("div").length;
			$('.ex_map_chatMess_right_top_left_text').html(divLengthRem);
			if(divLengthRem<3){
				$('.ex_map_chatMess_right_bottom_btn_hover').removeClass().addClass('ex_map_chatMess_right_bottom_btn')
			}
			var divList= $('.ex_map_chatMess_left_bottom div');
        	for(var i=0;i<divList.length;i++){
        		var thisDIv = divList[i];
        		if($(thisDIv).attr('id') == $(checkEvt).parent().attr('id')){
        			$(thisDIv).removeClass().addClass('map_chat_div');
        			chatArray.splice($.inArray('$(thisDIv).attr("id")',chatArray),1);
        			console.log('---shanchu',chatArray)
        		}
        	}
		}
		//联系人搜索==================================================================================
		$('#searchMem').on("keyup", function() {
			if ($('#searchMem').val() == '') {
				$('#searchUserList').hide();
				$('#searchUserList').html('');
			}else{
				searchMem();
			}
		});
		function searchMem(){
			var memName = $('#searchMem').val();
			$.ajax({
				url : '${ctx}/exhibition/map/findPeoples?name='+memName,
				type : 'post',
				dataType : 'json',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("响应状态:[" + XMLHttpRequest.status + "]-");
					console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
					console.log("异常情况:[" + textStatus + "]");
				},
				success : function(data) {
					$('#searchUserList').show();
					$("#searchUserList").mCustomScrollbar("destroy");
					var chatQHtml="";
					console.log('-------------------',data)
					for(var i=0;i<data.length;i++){
						chatQHtml+= "<div style='cursor: pointer;height:30px;line-height:30px;margin:3px 0;padding:10px;' onclick='chatSection(&quot;"+data[i].id+"&quot;,&quot;"+data[i].NAME+"&quot;)'>"+
										"<div style='width:30px;height:30px;padding:0;margin:0;background:url(${ctx}/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
										"</div>"+
										"<span class='map_chat_span'>"+data[i].NAME+"</span>"+
									"</div>";
					}
					$('#searchUserList').html(chatQHtml);
					$('#searchUserList').mCustomScrollbar();
				}
			});		
		}
		//搜索群聊联系人===========================================================================
		$('#groupChatSearch').on("keyup", function() {
			chatMemSearch();
			if ($('#groupChatSearch').val() == '') {
				chatMemSearch();
			}
		});
		function chatMemSearch(){
			var name=$('#groupChatSearch').val();
			$.ajax({
				url : '${ctx}/exhibition/map/findPeoples?name='+name,
				type : 'post',
				dataType : 'json',
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("响应状态:[" + XMLHttpRequest.status + "]-");
					console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
					console.log("异常情况:[" + textStatus + "]");
				},
				success : function(data) {
					$(".ex_map_chatMess_left_bottom").mCustomScrollbar("destroy");
					var chatQHtml="";
					for(var i=0;i<data.length;i++){
						chatQHtml+= "<div class='map_chat_div' id="+data[i].id+" style='height:30px;line-height:30px;' onclick='chatMessAppend(&quot;"+data[i].id+"&quot;,&quot;"+data[i].NAME+"&quot;,this)'>"+
										"<div style='width:30px;height:30px;padding:0;margin:0;background:url(${ctx}/resources/image/houzi.jpg) no-repeat;background-size:100% 100%;float:left;'>"+
										"</div>"+
										"<span class='map_chat_span>"+data[i].NAME+"</span>"+
									"</div>";
					}
					$('.ex_map_chatMess_left_bottom').html(chatQHtml);
					$(".ex_map_chatMess_left_bottom").mCustomScrollbar();
				}
			});	
		}
		
		
		//群聊列表
		function chatMessList(){
			/* $("#chatQLeft").mCustomScrollbar("destroy"); */
		/* 	mytool.getUserRoom("user1"); */
			  mytool.getUserRoom(userIdM);
			$("#chatQLeft").mCustomScrollbar();
			$('.map_chat_left_bottom_record').hide()
		}
		
		//群聊上传
		function uploadSure(){
			var divLength = $('.ex_map_chatMess_right_center').children("div").length
			alert(divLength)
			$('.ex_map_chatMess_right_top_left_text').html(divLength);
			if(divLength>=3){
				$('.ex_map_chatMess_right_bottom_btn').attr('disabled',false).removeClass().addClass('ex_map_chatMess_right_bottom_btn_hover');
				console.log('xuanzhongshuzu----',chatArray)
			    mytool.openRoom(chatArray); 
         		$('.chat_video').show();
         		
			}else if(divLength<3){
				$('.ex_map_chatMess_right_bottom_btn_hover').attr('disabled',true).removeClass().addClass('ex_map_chatMess_right_bottom_btn');
				alert('请选择至少3位人进行群聊')
			}
			
		}
		
		
		function openChat(){
			$('.map_chat').show();
			 /* indexDb.instance.query('content'); */
			
		}
		function closeChat(){
			$('.map_chat').hide();
		}
		
		
		/* 单人视频聊天 */
		$('.chat_video_phone').click(function(){
			console.log('-----点击单人ID',meId)
         	if(typeO==1){
         		var meIdA = [];
         		meIdA.push(meId)
             	mytool.openRoom(meIdA);
	       		$('.chat_video').show();
         	}else if(typeO==2){
         		/* mytool.getGroupUsers(userRoomName); */
         		$('#iframeSH').css('display','block');
         		var myElement = document.createElement("iframe");
         		myElement.src='https://rtcmulticonnection.herokuapp.com/demos/Video-Conferencing.html';
         		myElement.allow="geolocation; microphone; camera";
         		myElement.id="child"
         		$('.ex_main').append(myElement);
         	//document.getElementById('iframeSH').contentWindow.postMessage("主页面消息", "http://b.com/iframepage.html")
         	//document.getElementById('iframeSH').contentWindow.postMessage("主页面消息", "https://192.168.0.118:9001/demos/Audio+Video+TextChat+FileSharing.html")
         	window.addEventListener('message', function (event) {
                console.log('got iframe message -> ' + event.data);
                var message=event.data
                if(message.action)
                {
                    switch(message.action)
                    {
                        case 'ready':
                        $('#child')[0].contentWindow.postMessage("user10_钉钉2",
                        "https://rtcmulticonnection.herokuapp.com/demos/Video-Conferencing.html")
                        break;
                        case 'quit':
                        $('#child').remove()
                        break;
                    }
                }
  
            }, false);
         	
         	}
		})
		
		
		//选择文件
		window.searchFile=function searchFile(){
		   var form = new FormData(document.getElementById("tf"));
		   if(typeO==1){
			     form.append('sender',userIdM);     //发送人ID
	             form.append('receiver',meId);     //接收人ID/群名
	             form.append('type',0);           // 文件类型
	             form.append('chatType',typeO);  //类型 单人 1
        	}else if(typeO==2){
        		 form.append('sender',userIdM);           //发送人ID
                 form.append('receiver',userRoomName);   //接收人ID/群名
                 form.append('type',0);                 // 文件类型
                 form.append('chatType',typeO);        //类型 群聊 2
        	}
           $.ajax({
			   url : 'https://58.56.100.10:9001/file-upload',
			   type : 'post',
			   dataType:'json',
               data:form,
               processData:false,
               contentType:false,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log("响应状态:[" + XMLHttpRequest.status + "]-");
					console.log("完成状态:[" + XMLHttpRequest.readyState + "]-");
					console.log("异常情况:[" + textStatus + "]");
				},
				success : function(data) {
					$('.map_chat_sendMess_center_text').append("<div onclick='download(&quot;"+data.url+"&quot;)'class='chat_img' data-chat-url='"+data.url+"'>"+"</div>")
				}
			});
		}
	    download = function (urlT) {
            var url = 'https://58.56.100.10:9001/file-download?filePath='+urlT;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', url, true);    // 也可以使用POST方式，根据接口
            xhr.responseType = "blob";  // 返回类型blob
            // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
            xhr.onload = function () {
                // 请求完成
                if (this.status === 200) {
                	 var blob = this.response
                     var contentType = this.getResponseHeader('Content-Type')
                     var filename = urlT
                	 if ((filename.indexOf('.mp4') != -1)||filename.indexOf('.avi') != -1) {
                         //创建video标签
                         var video = document.createElement('video')
                         video.src = window.URL.createObjectURL(blob)
                          video.controls='controls'
                         $('.map_chat_sendMess_center_text').append(video)
                         video.width = 200
                     }else if((filename.indexOf('.jpg')!=-1)||(filename.indexOf('.png')!=-1))//图片
                     {
                         var img=document.createElement('img')
                         img.src = window.URL.createObjectURL(blob)
                           img.width = 200
                         $('.map_chat_sendMess_center_text').append(img)
                         
                     }
                }
            };
            // 发送ajax请求
            xhr.send();
        }
		
		//视频窗口拖动
		$('#M').click(function(){
			$('.ex_main_video_seml').removeClass().addClass('ex_main_video_big_hover');
			$('.ex_main_video_big').removeClass().addClass('ex_main_video_seml_hover');
		})
		$('#O').click(function(){
			$('.ex_main_video_big_hover').removeClass().addClass('ex_main_video_seml');
			$('.ex_main_video_seml_hover').removeClass().addClass('ex_main_video_big');
		})
	$(document).ready(function(){
        $(".chat_video").mousedown(function(e){ //e鼠标事件
            $(this).css("cursor","move");//改变鼠标指针的形状
            var offset = $(this).offset();//DIV在页面的位置
            var x = e.pageX - offset.left;//获得鼠标指针离DIV元素左边界的距离
            var y = e.pageY - offset.top;//获得鼠标指针离DIV元素上边界的距离
            $(document).bind("mousemove",function(ev){ //绑定鼠标的移动事件，因为光标在DIV元素外面也要有效果，所以要用doucment的事件，而不用DIV元素的事件
                $(".chat_video").stop();//加上这个之后
 
                var _x = ev.pageX - x;//获得X轴方向移动的值
                var _y = ev.pageY - y;//获得Y轴方向移动的值
 
                $(".chat_video").animate({left:_x+"px",top:_y+"px"},10);
            });
        });
 
        $(document).mouseup(function(){
            $(".chat_video").css("cursor","default");
            $(this).unbind("mousemove");
        });
    })
	/* 	//工作站测试
		function ceshiGzz(){
			if($('.ceshiGzz').hasClass('ceshiGzz')){
				$('.ceshiGzz').removeClass().addClass('ceshiGzz_hover');
				setGzzLayer();
				ceshiGZZLayer.setVisible(true);
			}else if($('.ceshiGzz_hover').hasClass('ceshiGzz_hover')){
				$('.ceshiGzz_hover').removeClass().addClass('ceshiGzz');
				ceshiGZZLayer.setVisible(false);
			}
		}
		//jiankong测试
		function ceshiJk(){
			if($('.ceshiJk').hasClass('ceshiJk')){
				$('.ceshiJk').removeClass().addClass('ceshiJk_hover');
				setJkLayer();
				ceshiJKLayer.setVisible(true);
			}else if($('.ceshiJk_hover').hasClass('ceshiJk_hover')){
				$('.ceshiJk_hover').removeClass().addClass('ceshiJk');
				ceshiJKLayer.setVisible(false);
			}
		} */
		/* 区域  街道 详细信息
			function streetList(){
				$('.ex_main_rightheader_pw7').show();
				$(".ex_main_rightheader_pw7 ul").mCustomScrollbar();
			}
			function closeStreetList(){
				$('.ex_main_rightheader_pw7').hide();
			} 
			//右下角弹窗 
			$('.ex_main_right_down').click(function(){
	         	if($('.ex_main_right_down_content').is(':hidden'))
	         	{
	           		$('.ex_main_right_down_content').slideDown('slow');  
	           		$(this).removeClass().addClass('ex_main_right_down_hover');
	         	}else{
		          	$('.ex_main_right_down_content').slideUp('slow');
	           		$(this).removeClass().addClass('ex_main_right_down');
	           	}
			})*/ 
			//右上方推出提示框 
			/* $('.ex_main_rightheader_four').mouseover(function(){
				closeRegionList();
	           	$('.ex_main_exit_pw').slideDown('slow');  
			})
			$('.ex_main_rightheader_four').mouseout(function(){
				closeRegionList();
	           	$('.ex_main_exit_pw').slideUp('slow');  
			}) */
		/* 	var tjyId;
			function aaa(id){
				tjyId = id;
				console.log('aaaaaaaaaaaaa',tjyId)
			} */
			
		</script>
	</body>
</html>