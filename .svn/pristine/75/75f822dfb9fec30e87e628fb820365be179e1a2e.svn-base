<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">
<%
String userAcc = request.getSession().getAttribute("userAccount").toString();
String userName = request.getSession().getAttribute("userName").toString();
%>
<html>
	<head>
	    <title>首都铁路护路联防综合业务信息管理系统</title>
	    <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="JHVersion1.1-layout"> 
		<link rel="shortcut icon" href="${ctx}/resources/image/project.ico" type="image/x-icon" />
		<!--  360浏览器专用 -->
		<meta name="renderer" content="webkit"/>
		<style>
			.layoutWaitingWindow{
				position: fixed;
				position:absolute;
				z-index:99999999;
				margin: 0 auto;
				border:none;
				background-color:none;
				overflow:hidden;
				display:none;
			}
			.phone{
				width: 80px;
			    height: 40px;
			    float: left;
			    background: url(/sdrpoms/resources/image/layout/icon/phone.png)left 0 top 0 no-repeat;
			    cursor: pointer;
			    line-height: 40px;
			    color: #fff;
			    margin:-11px auto;
			    font-size:16px;
			    text-indent:23px;
			}
			.er_hover{
				position:absolute;
				top:0;
				right:600px;
				display:none;
				background:red;width:100px;height:100px;
			 	background:url(/sdrpoms/resources/image/layout/icon/er.png) no-repeat;
			 	background-size:100% 100%;
			}
			.layoutTopRight {
			    float: right;
			    width: auto;
			    padding-top: 30px;
			    font-size: 14px;
			    right: 20px;
			    overflow: hidden;
			    position: absolute;
			    right: 0;
		}
		#layoutPopPicVideoWindow img{width:100%;height:100%;}
		</style>
		<%@ include file="taglib.jsp" %>
	    <SCRIPT type="text/javascript">
		    //ajax不缓存
		    jQuery.ajaxSetup({cache:false});
		    //菜单树变量
		    var layoutMenuTree;
			//菜单树参数设定
			var menuTreeSetting = {
				//必须使用data
				data:{ 
					simpleData : {
						enable : true,
						idKey : "id", 	//id编号命名 默认
						pIdKey : "pId", //父id编号命名 默认
						rootPId : 0 	//用于修正根节点父节点数据，即 pIdKey 指定的属性值
					}
				},
				//回调函数
				callback : {
					//单击树形菜单，打开新页面至tab页中(此处将资源url放在了[title]属性中,放在[url]属性中时会打开两遍url连接)
					onClick : CilckLayoutMenuTreeNode,
					onRightClick : CilckLayoutMenuTreeNode,
					//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
					onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
						$.messager.alert("提示","加载错误：" + XMLHttpRequest,"info");  
					},
					onAsyncSuccess : function(event, treeId, treeNode, msg){
						layoutMenuTree.expandAll(true);
					}
				}
			};
			
			//点击菜单节点
			function CilckLayoutMenuTreeNode(event, treeId, treeNode){
				if(treeNode.title != undefined){
					var param = treeNode.title.split("_");
					param[1] = '${ctx}'+param[1];
					if(param[0] == "a"){
						//将url添加到tab页中(适用于Ajax不刷新页面)
						/* alert(param[1]);
						alert(treeNode.name); */
						OpenAjax(treeNode.name, param[1]);
					}else if (param[0] == "i") {
						//将iframe添加到tab页中(适用于页面较复杂的刷新页面)
						var iframe = document.createElement("iframe");
						iframe.src = param[1];  
						iframe.frameBorder = 0;   
						iframe.height = '100%';   
						iframe.width = '100%'; 
						OpenIframe(treeNode.name, iframe);
					}
				}
			};
			
			//在右边center区域打开菜单，新增tab(Iframe方式)
			function OpenIframe(text, iframe) {
				if ($("#layouTabs").tabs('exists', text)) {
					$('#layouTabs').tabs('select', text);
				} else {
					$('#layouTabs').tabs('add', {
						title : text,
						closable : true,
						content : iframe
					});
				}
			};
		
			//在右边center区域打开菜单，新增tab(Ajax方式)
			function OpenAjax(text, url) {
				if ($("#layouTabs").tabs('exists', text)) {
					$('#layouTabs').tabs('select', text);
				} else {
					$('#layouTabs').tabs('add', {
						title : text,
						closable : true,
						href : url
					});
				}
			};
			    
			//几个关闭事件的实现
			function CloseTab(menu, type) {
				var curTabTitle = $(menu).data("tabTitle");
				var tabs = $("#layouTabs");
				if (type == "closeThisLayouTabs") {
					if(curTabTitle=="首页")
						return;
					tabs.tabs("close", curTabTitle);
					return;
				}
				
				var allTabs = tabs.tabs("tabs");
				var closeTabsTitle = [];
				
				$.each(allTabs, function () {
					var opt = $(this).panel("options");
					if (opt.closable && opt.title != curTabTitle && type === "closeOtherLayoutTabs") {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === "closeAllLayoutTabs") {
						closeTabsTitle.push(opt.title);
					}
				});
				for (var i = 0; i < closeTabsTitle.length; i++) {
					tabs.tabs("close", closeTabsTitle[i]);
				}
			};
			
			//页面初始化=============================
			$(document).ready(function(){
				//绑定tabs的右键菜单
			    $("#layouTabs").tabs({
			        onContextMenu : function (e, title) {
			            e.preventDefault();
			            $('#layoutTabsMenu').menu('show', {
			                left : e.pageX,
			                top : e.pageY
			            }).data("tabTitle", title);
			        }
			    });	
				
			    //实例化menu的onClick事件
			    $("#layoutTabsMenu").menu({
			        onClick : function (item) {
			            CloseTab(this, item.name);
			        }
			    });
			    
				//首先加载横向菜单
				//加载第二级菜单树
				var requrl = "menu/getUserSecondLevelMenu";
				$.post(requrl,function(data){
					$.each(data, function (n, value) {
						if(""=="${menuName}"){
							if(n == 0){ 
							   $("#layoutTopMenuBar").append(
									   "<li class='selected' menuid='"+value.id+
									   "' menupid='"+value.pId+"'><span>"+value.name+"</span></li>");
			    				var requrl = "menu/getUserMenuListBySecondLevelMenu?secondLevelMenuId="+value.id;
			    				//加载左侧菜单
			    				$.post(requrl,function(data){
			    					layoutMenuTree = $.fn.zTree.init($("#layoutMenuTree"), menuTreeSetting,data);
			    					layoutMenuTree.expandAll(true);
			    				});
							}else{
								$("#layoutTopMenuBar").append("<li menuid='"+value.id+"' menupid='"+value.pId+"' ><span>"+value.name+"</span></li>");
							}      
						}else{
						
							if(value.name == "${menuName}"){ 
								   $("#layoutTopMenuBar").append(
										   "<li class='selected' menuid='"+value.id+
										   "' menupid='"+value.pId+"'><span>"+value.name+"</span></li>");
				    				var requrl = "menu/getUserMenuListBySecondLevelMenu?secondLevelMenuId="+value.id;
				    				//加载左侧菜单
				    				$.post(requrl,function(data){
				    					layoutMenuTree = $.fn.zTree.init($("#layoutMenuTree"), menuTreeSetting,data);
				    					layoutMenuTree.expandAll(true);
				    				});
								}else{
									$("#layoutTopMenuBar").append("<li menuid='"+value.id+"' menupid='"+value.pId+"' ><span>"+value.name+"</span></li>");
									
								} 
						}
						     
				    });
				
					/* window.onload=function(){
						
						OpenAjax("铁路线路基本信息", "/sdrpoms/baseInfo/baseInfoRailInit");
					} */
					//横向菜单点击事件
					$(".layoutTopMenu ul li").bind({
		    			mouseenter:function(){
		    				if($(this).attr('class')=='selected'){
		    					return;
		    				}
		    				$(this).addClass('hover');
		    			},
		    			mouseleave:function(){
		    				$(this).removeClass('hover');
		    			},
		    			click:function(){
		    				$(".layoutTopMenu ul li").removeClass('selected');
		    				$(this).removeClass().addClass('selected');
		    				var menuid = $(this).attr('menuid');
		    				selectedMenuItem(menuid);
		    			}
		    		});
				});
			});
			
			// 选中菜单操作
			function selectedMenuItem(menuid) {
				var requrl = "menu/getUserMenuListBySecondLevelMenu?secondLevelMenuId="+menuid;
				//加载左侧菜单
				switch(menuid){
				case "0001":
					//基础数据
					OpenAjax("铁路线路基本信息", "/sdrpoms/baseInfo/baseInfoRailInit");
					break;
				case "0002":
					//视频监控
					OpenAjax("监控探头基本信息", "/sdrpoms/baseInfo/videoMonitorInfoInit");
					break;
				case "0003":
					//队伍管理
					OpenAjax("巡防队伍管理", "/sdrpoms/patrol/patrolTeamInfoInit");
					break;
				case "0004":
					//隐患处置
					OpenAjax("隐患上报", "/sdrpoms/patrol/patrolDangerInfoInit");
					break;
				case "0005":
					//办公OA
					OpenAjax("收件箱", "/sdrpoms/oaInfo/documentInit");
					break;
				case "0006":
					//教育培训
					/* OpenAjax("培训资料管理", "/sdrpoms/trainMaterial/trainMaterialInit"); */
					break;
				case "0007":
					//研判分析
					
					break;
				case "0008":
					//视频会议
					
					break;
				case "0000":
					//运维管理
					
					break;
				}
				$.post(requrl,function(data){
					layoutMenuTree = $.fn.zTree.init($("#layoutMenuTree"), menuTreeSetting,data);
					layoutMenuTree.expandAll(true);
				});
			}
			
			$(function() {
				selectedMenuItem('${selectedMenuId}');
			});
			
			function changePwd(){
				jQuery("#layoutPopWindow").window({
					title:'修改密码',
					href:'user/updateLoginPwd',
					width:350,
					height:200,
					onLoad: function(){$('#pwdUpForm').form('reset');}
				});
			}
			function changeUserInfo(){
				jQuery("#layoutPopWindow").window({
					title:'修改用户信息',
					href:'user/loginMsg4Update',
					width:350,
					height:380,
					onLoad: function(){$('#msgUpForm').form('reset');}
				});
			}
			function delZero(delOrgId){
				if (null == delOrgId || delOrgId.length!= 12) {
					return delOrgId;
				}
				var delProvinceId = delOrgId.substring(0, 2);
				var delCityId = delOrgId.substring(2, 4);
				var delCountyId = delOrgId.substring(4, 6);
				var delTownId = delOrgId.substring(6, 9);
				var delVillageId = delOrgId.substring(9, 12);
				if(delCityId=="00")
					return delProvinceId;
				if(delCountyId=="00")
					return delProvinceId+delCityId;
				if(delTownId=="000")
					return delProvinceId+delCityId+delCountyId;
				if(delVillageId=="000")
					return delProvinceId+delCityId+delCountyId+delTownId;
				return delOrgId;
			}
			//=============================
		</SCRIPT>
	</head>

	<body class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',border:false" style="height:110px;" >
			<div class="layoutTop">
				<div class="layoutTopLeft ">
				</div>
				<div class="layoutTopRight ">
					<div class="phone" >
							二维码
					</div>
					<ul style="width:auto;float:right;">
						<sec:authorize url="/exhibition/welcome">
							<li ><a href="${ctx}/exhibition/welcome"><img src="${ctx}/resources/image/layout/icon/icon-analysis.png"></a></li>
							<li><a href="${ctx}/exhibition/welcome">管理平台 </a></li>
						</sec:authorize>
						<li ><a><img src="${ctx}/resources/image/layout/icon/icon_user_info.png"></a></li>
						<li><a>用户：<%=userAcc%> [<%=userName%>]</a></li>
						
						<li ><a href="javascript:changePwd();">
							<img src="${ctx}/resources/image/layout/icon/icon_change_password.png" alt="修改密码">
							</a>
						</li>
						<li><a href="javascript:changePwd();">修改密码 </a></li>
						<li ><a href="javascript:changeUserInfo();">
							<img src="${ctx}/resources/image/layout/icon/icon_change_user_info.png" alt="修改账户信息">
							</a>
						</li>
						<li><a href="javascript:changeUserInfo();">账户信息 </a></li>
						
						<li ><a href="${ctx}/logout"> 
							<img src="${ctx}/resources/image/layout/icon/icon_exit.png" alt="退出系统" />
							</a>
						</li>
						<li><a href="${ctx}/logout"> 退出系统 </a></li>
					</ul>
				</div>
			</div>
			
			<div class="layoutTopMenu">		
				<ul id="layoutTopMenuBar" ></ul>		
			</div> 
		</div>
		<div data-options="region:'west',split:true" title="功能菜单"style="width: 200px;">
			<div>
				<ul id="layoutMenuTree" class="ztree"></ul>
			</div>
	    </div>
	    <div data-options="region:'center'">
	    	<div class="easyui-tabs" fit="true" border="false" id="layouTabs">
	      		<div title="首页" style="padding:5px;background:url('${ctx}/resources/image/bg_layout.png') center center no-repeat; ">
	      		</div>
	    	</div>
	    	<div class="er_hover"></div>
	    </div>
	    
	    <div id="layoutTabsMenu" class="easyui-menu" style="width:120px;">  
	    	<div name="closeThisLayouTabs">关闭</div>  
	    	<div name="closeOtherLayoutTabs">关闭其他</div>  
	    	<div name="closeAllLayoutTabs">关闭所有</div>
	  	</div>
	  	<div id="layoutPopPicVideoWindow" modal="true" minimizable="false" maximizable="false"  cache="false" 
	  		collapsible="false" resizable="true" style="margin: 0px;padding: 0px;overflow: auto;">
		</div>
		
	  	<div id="layoutPopWindow" modal="true" minimizable="false" maximizable="false"  cache="false" 
	  		collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;">
	  	</div>
	  	
	  	<div id="layoutWaitingWindow" class="layoutWaitingWindow">
			<img src="${ctx}/resources/image/bg_waiting.gif" style="width:67px;height:67px;"></img>  	
		</div>
		
		<div id="layoutPopSecondWindow" modal="true" minimizable="false" maximizable="false"  cache="false" 
	  		collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;">
		</div>
		
		<div id="layoutPopThirdWindow" modal="true" minimizable="false" maximizable="false"  cache="false" 
	  		collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;">
		</div>
		
		<div id="layoutPopfourWindow" modal="true" minimizable="false" maximizable="false"  cache="false" 
	  		collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;">
		</div>
	  	<script type="text/javascript">
	  		$('.phone').mouseover(function(){
	  			$('.er_hover').css('display','block')
	  		})
	  		$('.phone').mouseout(function(){
	  			$('.er_hover').css('display','none')
	  		})
	  		//禁止回退BackSpace
	  		function banBackSpace(e){     
			    var ev = e || window.event;//获取event对象     
			    var obj = ev.target || ev.srcElement;//获取事件源     
			      
			    var t = obj.type || obj.getAttribute('type');//获取事件源类型    
			      
			    //获取作为判断条件的事件类型  
			    var vReadOnly = obj.getAttribute('readonly');  
			    var vEnabled = obj.getAttribute('enabled');  
			    //处理null值情况  
			    vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
			    vEnabled = (vEnabled == null) ? true : vEnabled;  
			      
			    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
			    //并且readonly属性为true或enabled属性为false的，则退格键失效  
			    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
			                && (vReadOnly==true || vEnabled!=true))?true:false;  
			     
			    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
			    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
			                ?true:false;          
			    //判断  
			    if(flag2){  
			        return false;  
			    }  
			    if(flag1){     
			        return false;     
			    }     
			} 
	  		//禁止后退键 作用于Firefox、Opera  
	  		document.onkeypress=banBackSpace;  
	  		//禁止后退键  作用于IE、Chrome  
	  		document.onkeydown=banBackSpace;  
			/* window.onload=function(){
				document.getElementsByTagName("body")[0].onkeydown =function(event){
	    			//判断按键为backSpace键
					if(event.keyCode==8){
						console.log(1);
	       				//获取按键按下时光标做指向的element  
	                	var elem = event.srcElement || event.currentTarget;
	       				//判断是否需要阻止按下键盘的事件默认传递  
	       				var name = elem.nodeName;  
						if(name!='INPUT' && name!='TEXTAREA'){
						    return _stopIt(event);  
						}  
						var type_e = elem.type.toUpperCase();
						if(name=='INPUT' && (type_e!='TEXT' && type_e!='TEXTAREA' && type_e!='PASSWORD' && type_e!='FILE')){
							return _stopIt(event);
						}
						if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){
							return _stopIt(event);  
	                	}  
	        		}  
	    		};
			}; 
			function _stopIt(e){  
		        if(e.returnValue){  
		            e.returnValue = false ;  
		        }  
		        if(e.preventDefault){  
		            e.preventDefault();  
		        }                 
		        return false;  
			} */
	  		//全局函数
	  		//弹出图片视频窗口
			function showPicVedioWindow(fileId){
				var options ={title:"资料详情",
							  href:"file/showFile?fileId="+fileId,
							  width:800,
							  height:600,
							  top:($(window).height() - 600) * 0.5,
							  left:($(window).width() - 800) * 0.5
							  };
				jQuery("#layoutPopPicVideoWindow").window(options);
			}
			//关闭图片视频窗口
			function closePicVedioWindow(){
				$("#layoutPopPicVideoWindow").window('close');
			}
			//弹出窗口 1
			function showWindow(options){
				options.top=($(window).height() - options.height) * 0.5;
				options.left=($(window).width() - options.width) * 0.5; 
				jQuery("#layoutPopWindow").window(options);
			}
			//关闭弹出窗口 1
			function closeWindow(){
				$("#layoutPopWindow").window('close');
			}
			//弹出窗口 2
			function showSecondWindow(options){
				options.top=($(window).height() - options.height) * 0.5;
				options.left=($(window).width() - options.width) * 0.5; 
				jQuery("#layoutPopSecondWindow").window(options);
			}
			//关闭弹出窗口 2
			function closeSecondWindow(){
				$("#layoutPopSecondWindow").window('close');
			}
			//弹出窗口 3
			function showThirdWindow(options){
				options.top=($(window).height() - options.height) * 0.5;
				options.left=($(window).width() - options.width) * 0.5; 
				jQuery("#layoutPopThirdWindow").window(options);
			}
			//关闭弹出窗口 3
			function closeThirdWindow(){
				$("#layoutPopThirdWindow").window('close');
			}
			//弹出窗口 4
			function showFourWindow(options){
				options.top=($(window).height() - options.height) * 0.5;
				options.left=($(window).width() - options.width) * 0.5; 
				jQuery("#layoutPopFourWindow").window(options);
			}
			//关闭弹出窗口 4
			function closeFourWindow(){
				$("#layoutPopFourWindow").window('close');
			}
			//弹出菜单窗口
			function showMenu(id,popX,popY){
				$("#"+id).menu('show', {left:popX,top:popY});
			}
			//显示隐藏域
			function showItem(itemArray){
		        for(var i = 0; i<itemArray.length; i++){
		            $(itemArray[i]).show();
		        }
		    }
			//隐藏隐藏域
			function hideItem(itemArray) {
		    	for(var i = 0; i<itemArray.length; i++){
		            $(itemArray[i]).hide();
		    	}
			}
			
			//弹出等待窗口
			function showWaitingWindow(){
				$("#layoutWaitingWindow").css('width',$(window).width());
				$("#layoutWaitingWindow").css('height',$(window).height());
				$("#layoutWaitingWindow img").css('margin-top',($(window).height()-67)/2);
				$("#layoutWaitingWindow img").css('margin-left',($(window).width()-67)/2);
				$("#layoutWaitingWindow").css('display','block');
			}
			//关闭等待窗口
			function closeWaitingWindow(){
				$("#layoutWaitingWindow").css('display','none');
			}	
			
			//导出报表
			function exportExcelFile(url,params,fileName){
				$.ajax({
		    		url:url,
		    		type:"POST",
		    		dataType:'json',
		    		data:params,
		    		scriptCharset:'utf-8',
		    		cache:false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			closeWaitingWindow();
		    		},
		    		beforeSend:function(){
		    			showWaitingWindow();
		    		},
		    		success:function(data){
		    			closeWaitingWindow();
		    			if(data.fileId == null || data.fileId == "-1"){
							$.messager.alert('提示','导出'+fileName+'excel失败','info');
						}else{
							//注意中文编码 
							window.location.href="${ctx}/excelfile/excelFileDownload?fileName="+fileName+"&fileId=" + data.fileId;
						}
		    		}
		    	});
			}
			//系统消息
			setInterval("getMessage()", 5000) ;
				function getMessage(){
		    	$.post('${ctx}/oaInfo/anUnreadMailCount',{},function(data){
		    		 if(data>0){
		    			var strContent="<a href=javascript:selectedMenuItem('0005')>您有"+data+"条新消息,请到点击查询您的邮件！</a>";
		    	     	$.messager.show({
		    	    		title:'系统消息',
		    	    		msg:strContent,
		    	    		timeout:5000,
		    	    		showType:'slide'
		    	    	}); 
		    		} 
		    	},'json');    		
			} 
	  	</script>
	</body>
</html>