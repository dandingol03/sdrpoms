<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">

    jQuery.ajaxSetup({cache:false});
  	
    $('#mobileMenuManageResourceTable').datagrid({
		title:'移动端菜单关联资源列表', 				//标题
		method:'post',
		iconCls:'icon-tip', 						//图标
		singleSelect:false, 						//多选
		height:366, 								//高度
		fitColumns: true, 							//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, 								//奇偶行颜色不同                        
		collapsible:false,							//可折叠
		url:"mobile/menu/queryMenuManageResList", 	//数据来源
		sortName: 'resourceId',						//排序的列
		sortOrder: 'desc', 							//顺序
		remoteSort: true, 							//服务器端排序
		idField:'resourceId', 						//主键字段 
		queryParams:{}, 							//查询条件
		pagination:true, 							//显示分页
		rownumbers:false, 							//显示行号
		columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框 
				{field:'mobileMenuId',title:'菜单ID',width:20,sortable:false,hidden:true,
					formatter:function(value,row,index){return row.mobileMenuId;}
				},
				{field:'resourceId',title:'资源ID',width:20,sortable:false,hidden:true,
					formatter:function(value,row,index){return row.resourceId;}
				},
				{field:'resourceName',title:'资源名称',width:120,sortable:true,
					formatter:function(value,row,index){return row.resourceName;}
				},
				{field:'resourceString',title:'资源URL',width:150,sortable:true,
					formatter:function(value,row,index){return row.resourceString;}
				},
				{field:'enable',title:'是否可用',width:60,sortable:true,
					formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
				},
				{field:'resourceDesc',title:'资源描述',width:100,sortable:true,
					formatter:function(value,row,index){return row.resourceDesc;}
				}
				]],
		toolbar:[
				{text:'新增', iconCls:'icon-add', 
					handler:function(){addMobileMenuRelateResourceRow();}
				},'-',
				{text:'更新', iconCls:'icon-edit',
					handler:function(){updateMobileMenuRelateResourceRow();}
				},'-',
				{text:'删除', iconCls:'icon-remove',
					handler:function(){deleteMobileMenuRelateResourceRow();}
				},'-'
				],
		//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		onLoadSuccess:function(){$('#mobileMenuManageResourceTable').datagrid('clearSelections');}
	});
    
    function addMobileMenuRelateResourceRow(){
    	var treeNode = mobileMenuManageAllTree.getSelectedNodes()[0];
    	showWindow({
			title:'添加关联移动端菜单的资源',
			href:'mobile/menu/addMenuResourcePopWin',
			width:350,
			height:270,
			onLoad: function(){
				$("#addMobileMenuResourceRelationForm").form('load',{
					menuId:treeNode.id
				});
			}
		});
    }
    
    function updateMobileMenuRelateResourceRow(){
    	var rows = $('#mobileMenuManageResourceTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的资源",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个资源进行更新",'info');
			return;
		}
    	showWindow({
			title:'修改关联移动端菜单的资源',
			href:'mobile/menu/updateMenuResourcePopWin',
			width:350,
			height:270,
			onLoad: function(){
				$("#updateMobileMenuResourceRelationForm").form('load', rows[0]);
			}
		});
    }
    
    function deleteMobileMenuRelateResourceRow(){
    	$.messager.confirm('提示','确定要删除吗?',function(result){
			if(result){
				var rows = $('#mobileMenuManageResourceTable').datagrid('getSelections');
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?resourceId="+n.resourceId;
					} else {
						ps += "&resourceId="+n.resourceId;
					}	
				});
				$.post('mobile/menu/delRelateMenuResources'+ps,function(data){
					$('#mobileMenuManageResourceTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				}); 
			}
		});
    }
    
  	//菜单树变量
    var mobileMenuManageAllTree;	

    var mobileMenuManageAllSetting = {
  		async : {
  			enable : true,		 			//设置 zTree是否开启异步加载模式
  			url : "mobile/menu/getMenu", 	//Ajax 获取数据的 URL 地址
  			autoParam : [ "id" ]			//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
  		},
  		data:{ //必须使用data
  			simpleData : {
  				enable : true,
  				idKey : "id", 	//id编号命名 默认
  				pIdKey : "pId", //父id编号命名 默认
  				rootPId : 0 	//用于修正根节点父节点数据，即 pIdKey 指定的属性值
  			}
  		},
  		//回调函数
  		callback : {
  			onClick : leftClickMobileMenuManageAllTree,
  			onRightClick : rightClickMobileMenuManageAllTreePopMenu,
  			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
  			onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
  				$.messager.alert("提示","加载错误：" + XMLHttpRequest,"info");  
  			},
  			onAsyncSuccess : function(event, treeId, treeNode, msg){
  				mobileMenuManageAllTree.expandAll(true);//展开全部菜单
  			}
  		}
  	};
    
    function leftClickMobileMenuManageAllTree(event, treeId, treeNode, clickFlag) {
			$('#rightMobileMenuManageAllTreeContent').panel('open');
			$('#rightMobileMenuManageAllTreeContent').panel('resize');
			if(treeNode.isParent){
				hideItem(["#rightMobileMenuManageAllAuthTableContent"]);
			}else{
				showItem(["#rightMobileMenuManageAllAuthTableContent"]);
			}
			var treeName = treeNode.name.split("["); 
			document.getElementById("rightMobileMenuManageAllTreeInfoName").innerHTML=treeName[0];
			document.getElementById("rightMobileMenuManageAllTreeInfoURL").innerHTML=treeNode.title;
			document.getElementById("rightMobileMenuManageAllTreeInfoDesc").innerHTML=treeNode.mdesc;
			$("#rightMobileMenuManageAllTreeInfoDesc").val();
			if(treeNode.otype=="a"){
				document.getElementById("rightMobileMenuManageAllTreeInfoOType").innerHTML="Ajax";
			}else{
				document.getElementById("rightMobileMenuManageAllTreeInfoOType").innerHTML="Iframe";
			}
			if(treeNode.micon&&treeNode.micon!=''){
				document.getElementById("rightRobotMenuManageAllTreeInfoFile").innerHTML="<img height='75px' width='75px' src='${ctx}/file/showPicFile?fileId="+treeNode.micon+"'/>";
			}else{
				document.getElementById("rightRobotMenuManageAllTreeInfoFile").innerHTML="未添加";
			}
			$("#mobileMenuManageResourceTable").datagrid("resize");
			var params = $('#mobileMenuManageResourceTable').datagrid('options').queryParams;
			params["menuId"]=treeNode.id;
			$('#mobileMenuManageResourceTable').datagrid('resize');
			$('#mobileMenuManageResourceTable').datagrid('reload'); 
			
	};
    
    function rightClickMobileMenuManageAllTreePopMenu(event, treeId, treeNode) {
    	mobileMenuManageAllTree.selectNode(treeNode);
    	
    	$('#rightMobileMenuManageAllTreeContent').panel('open');
		$('#rightMobileMenuManageAllTreeContent').panel('resize');
		if(treeNode.isParent){
			hideItem(["#rightMobileMenuManageAllAuthTableContent"]);
		}else{
			showItem(["#rightMobileMenuManageAllAuthTableContent"]);
		}
		var treeName = treeNode.name.split("["); 
		document.getElementById("rightMobileMenuManageAllTreeInfoName").innerHTML=treeName[0];
		document.getElementById("rightMobileMenuManageAllTreeInfoURL").innerHTML=treeNode.title;
		document.getElementById("rightMobileMenuManageAllTreeInfoDesc").innerHTML=treeNode.mdesc;
		$("#rightMobileMenuManageAllTreeInfoDesc").val();
		if(treeNode.otype=="a"){
			document.getElementById("rightMobileMenuManageAllTreeInfoOType").innerHTML="Ajax";
		}else{
			document.getElementById("rightMobileMenuManageAllTreeInfoOType").innerHTML="Iframe";
		}
		if(treeNode.micon&&treeNode.micon!=''){
			document.getElementById("rightRobotMenuManageAllTreeInfoFile").innerHTML="<img height='75px' width='75px' src='${ctx}/file/showPicFile?fileId="+treeNode.micon+"'/>";
		}else{
			document.getElementById("rightRobotMenuManageAllTreeInfoFile").innerHTML="未添加";
		}
		$("#mobileMenuManageResourceTable").datagrid("resize");
		var params = $('#mobileMenuManageResourceTable').datagrid('options').queryParams;
		params["menuId"]=treeNode.id;
		$('#mobileMenuManageResourceTable').datagrid('resize');
		$('#mobileMenuManageResourceTable').datagrid('reload');
		
    	showMenu("mobileMenuManageRightMenu",event.clientX,event.clientY);
	};
	
	//添加新菜单节点
	function addMobileMenuManageAllMenuNode(){
		var treeNode = mobileMenuManageAllTree.getSelectedNodes()[0];
		showWindow({
			title:'添加移动端菜单',
			href:'mobile/menu/addMenuPopWin?menuPid='+treeNode.id,
			width:350,
			height:320,
			onLoad: function(){
				$("#mobileMenuManageAddMenuForm").form('load',{
					mobileMenuPid:treeNode.id
				});
			}
		});
	}
	
	//更新菜单节点
	function editMobileMenuManageAllMenuNode(){
		var treeNode = mobileMenuManageAllTree.getSelectedNodes()[0];
		var treeName = treeNode.name.split("["); 
		showWindow({
			title:'更新菜单',
			href:'mobile/menu/updateMenuPopWin',
			width:350,
			height:320,
			onLoad: function(){
				$("#mobileMenuManageUpdateMenuForm").form('load', {
					mobileMenuId:treeNode.id,
					mobileMenuName:treeName[0],
					mobileMenuUrl:treeNode.title,
					mobileMenuDesc:treeNode.mdesc,
					mobileOpenType:treeNode.otype,
					mobileMenuIcon:treeNode.micon
				});
			}
		}); 
	}
	
	//删除菜单节点
	function delMobileMenuManageAllMenuNode(){
		$.messager.confirm('提示','确定要删除吗?',function(data){
			if(data){
				var treeNode = mobileMenuManageAllTree.getSelectedNodes()[0];
				$.post("mobile/menu/delMenu?menuId="+treeNode.id, function(data){
					reloadMobileTree();
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}

	//重新加载菜单树
	function reloadMobileTree(){
		mobileMenuManageAllTree.reAsyncChildNodes(null, "refresh");
	}
	
	$(document).ready(function() {
    	//加载菜单树
		mobileMenuManageAllTree = $.fn.zTree.init($("#mobileMenuManageAllTree"), mobileMenuManageAllSetting); 
	});
	
	</script>
	<div class="operationPageMenuLeft">
		<ul id="mobileMenuManageAllTree" class="ztree"></ul>
	</div>
	<div class="operationPageMenuRight">
		<div class="mt5 mr5">
			<div id="rightMobileMenuManageAllTreeContent" class="easyui-panel" title="移动端菜单基本信息" closed='true'  class="fw" style="height:150px">
 				<table id="rightMobileMenuManageAllTreeInfoTable" class="fw fh">
 					<tr>
	 					<td width="10%" align="right">菜单名称：</td>
	 					<td id="rightMobileMenuManageAllTreeInfoName" width="10%" align="left"></td>
	 					<td width="10%" align="right">菜单排序：</td>
	 					<td id="rightMobileMenuManageAllTreeInfoDesc" width="10%" align="left"></td>
	 					<td width="10%" align="right">打开方式：</td>
	 					<td id="rightMobileMenuManageAllTreeInfoOType" width="10%" align="left"></td>
	 					<td width="10%" align="right">菜单URL：</td>
	 					<td id="rightMobileMenuManageAllTreeInfoURL"  width="30%" align="left"></td>
 					</tr>
 					<tr>
 						<td width="10%" align="right">菜单图标：</td>
	 					<td id="rightRobotMenuManageAllTreeInfoFile" colspan="7"  width="30%" align="left"></td>
 					</tr>
 				</table>
	 		</div>
	 		<div id="rightMobileMenuManageAllAuthTableContent" class="pt5 dn">
				<table id="mobileMenuManageResourceTable" name="mobileMenuManageResourceTable" class="fw" ></table>
	 		</div>
 		</div>
	</div>
	<div id="mobileMenuManageRightMenu" class="easyui-menu" style="width:150px;">
		<div id="mobileMenuManageRightMenuaddMenu" data-options="iconCls:'icon-add'"
		 onclick="addMobileMenuManageAllMenuNode();">增加菜单</div>
		<div id="menuMobileManageRightMenuupMenu" data-options="iconCls:'icon-edit'"
		 onclick="editMobileMenuManageAllMenuNode();">修改菜单</div>
		<div id="mobileMenuManageRightMenudelMenu" data-options="iconCls:'icon-remove'"
		 onclick="delMobileMenuManageAllMenuNode();">删除菜单</div>
	</div>
</body>
