<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">

    jQuery.ajaxSetup({cache:false});
  	
    $('#menuManageResourceTable').datagrid({
		title:'菜单关联资源列表', 				//标题
		method:'post',
		iconCls:'icon-tip', 				//图标
		singleSelect:false, 				//多选
		height:366, 						//高度
		fitColumns: true, 					//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
		striped: true, 						//奇偶行颜色不同                        
		collapsible:false,					//可折叠
		url:"menu/queryMenuManageResList", 	//数据来源
		sortName: 'resourceId',				//排序的列
		sortOrder: 'desc', 					//顺序
		remoteSort: true, 					//服务器端排序
		idField:'resourceId', 				//主键字段 
		queryParams:{}, 					//查询条件
		pagination:true, 					//显示分页
		rownumbers:false, 					//显示行号
		columns:[[
				{field:'ck',checkbox:true,width:2}, //显示复选框 
				{field:'menuId',title:'菜单ID',width:20,sortable:false,hidden:true,
					formatter:function(value,row,index){return row.menuId;}
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
					handler:function(){addMenuRelateResourceRow();}
				},'-',
				{text:'更新', iconCls:'icon-edit',
					handler:function(){updateMenuRelateResourceRow();}
				},'-',
				{text:'删除', iconCls:'icon-remove',
					handler:function(){deleteMenuRelateResourceRow();}
				},'-'
				],
		//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		onLoadSuccess:function(){$('#menuManageResourceTable').datagrid('clearSelections');}
	});
    
    function addMenuRelateResourceRow(){
    	var treeNode = menuManageAllTree.getSelectedNodes()[0];
    	showWindow({
			title:'添加关联菜单的资源',
			href:'menu/addMenuResourcePopWin',
			width:350,
			height:270,
			onLoad: function(){
				$("#addMenuResourceRelationForm").form('load',{
					menuId:treeNode.id
				});
			}
		});
    }
    
    function updateMenuRelateResourceRow(){
    	var rows = $('#menuManageResourceTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择你要更新的资源",'info');
			return;
		}
		if(rows.length > 1){
			$.messager.alert('提示',"只能选择一个资源进行更新",'info');
			return;
		}
    	showWindow({
			title:'修改关联菜单的资源',
			href:'menu/updateMenuResourcePopWin',
			width:350,
			height:270,
			onLoad: function(){
				$("#updateMenuResourceRelationForm").form('load', rows[0]);
			}
		});
    }
    
    function deleteMenuRelateResourceRow(){
    	$.messager.confirm('提示','确定要删除吗?',function(result){
			if(result){
				var rows = $('#menuManageResourceTable').datagrid('getSelections');
				var ps = "";
				$.each(rows,function(i,n){
					if(i==0){
						ps += "?resourceId="+n.resourceId;
					} else {
						ps += "&resourceId="+n.resourceId;
					}	
				});
				$.post('menu/delRelateMenuResources'+ps,function(data){
					$('#menuManageResourceTable').datagrid('reload');
					$.messager.alert('提示',data.mes,'info');
				}); 
			}
		});
    }
  	//菜单树变量
    var menuManageAllTree;	

    var menuManageAllSetting = {
  		async : {
  			enable : true, 			//设置 zTree是否开启异步加载模式
  			url : "menu/getMenu", 	//Ajax 获取数据的 URL 地址
  			autoParam : [ "id" ]	//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
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
  			onClick : leftClickMenuManageAllTree,
  			onRightClick : rightClickMenuManageAllTreePopMenu,
  			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
  			onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
  				$.messager.alert("提示","加载错误：" + XMLHttpRequest,"info");  
  			},
  			onAsyncSuccess : function(event, treeId, treeNode, msg){
  				menuManageAllTree.expandAll(true);//展开全部菜单
  			}
  		}
  	};
    
    function leftClickMenuManageAllTree(event, treeId, treeNode, clickFlag) {
			$('#rightMenuManageAllTreeContent').panel('open');
			$('#rightMenuManageAllTreeContent').panel('resize');
			if(treeNode.isParent){
				hideItem(["#rightMenuManageAllAuthTableContent"]);
			}else{
				showItem(["#rightMenuManageAllAuthTableContent"]);
			}
			var treeName = treeNode.name.split("["); 
			document.getElementById("rightMenuManageAllTreeInfoName").innerHTML=treeName[0];
			document.getElementById("rightMenuManageAllTreeInfoURL").innerHTML=treeNode.title;
			document.getElementById("rightMenuManageAllTreeInfoDesc").innerHTML=treeNode.mdesc;
			$("#rightMenuManageAllTreeInfoDesc").val();
			if(treeNode.otype=="a")
				document.getElementById("rightMenuManageAllTreeInfoOType").innerHTML="Ajax";
			else
				document.getElementById("rightMenuManageAllTreeInfoOType").innerHTML="Iframe";
			$("#menuManageResourceTable").datagrid("resize");
			var params = $('#menuManageResourceTable').datagrid('options').queryParams;
			params["menuId"]=treeNode.id;
			$('#menuManageResourceTable').datagrid('resize');
			$('#menuManageResourceTable').datagrid('reload'); 
			
	};
    
    function rightClickMenuManageAllTreePopMenu(event, treeId, treeNode) {
    	menuManageAllTree.selectNode(treeNode);
    	
    	$('#rightMenuManageAllTreeContent').panel('open');
		$('#rightMenuManageAllTreeContent').panel('resize');
		if(treeNode.isParent){
			hideItem(["#rightMenuManageAllAuthTableContent"]);
		}else{
			showItem(["#rightMenuManageAllAuthTableContent"]);
		}
		var treeName = treeNode.name.split("["); 
		document.getElementById("rightMenuManageAllTreeInfoName").innerHTML=treeName[0];
		document.getElementById("rightMenuManageAllTreeInfoURL").innerHTML=treeNode.title;
		document.getElementById("rightMenuManageAllTreeInfoDesc").innerHTML=treeNode.mdesc;
		$("#rightMenuManageAllTreeInfoDesc").val();
		if(treeNode.otype=="a")
			document.getElementById("rightMenuManageAllTreeInfoOType").innerHTML="Ajax";
		else
			document.getElementById("rightMenuManageAllTreeInfoOType").innerHTML="Iframe";
		$("#menuManageResourceTable").datagrid("resize");
		var params = $('#menuManageResourceTable').datagrid('options').queryParams;
		params["menuId"]=treeNode.id;
		$('#menuManageResourceTable').datagrid('resize');
		$('#menuManageResourceTable').datagrid('reload');
		
    	showMenu("menuManageRightMenu",event.clientX,event.clientY);
	};
	
	//添加新菜单节点
	function addMenuManageAllMenuNode(){
		var treeNode = menuManageAllTree.getSelectedNodes()[0];
		showWindow({
			title:'添加菜单',
			href:'menu/addMenuPopWin?menuPid='+treeNode.id,
			width:350,
			height:200,
			onLoad: function(){
				$("#menuManageAddMenuForm").form('load',{
					menuPid:treeNode.id
				});
			}
		});
	}
	
	//更新菜单节点
	function editMenuManageAllMenuNode(){
		var treeNode = menuManageAllTree.getSelectedNodes()[0];
		var treeName = treeNode.name.split("["); 
		showWindow({
			title:'更新菜单',
			href:'menu/updateMenuPopWin',
			width:350,
			height:200,
			onLoad: function(){
				$("#menuManageUpdateMenuForm").form('load', {
					menuId:treeNode.id,
					menuName:treeName[0],
					menuUrl:treeNode.title,
					menuDesc:treeNode.mdesc,
					openType:treeNode.otype
				});
			}
		}); 
	}
	
	//删除菜单节点
	function delMenuManageAllMenuNode(){
		$.messager.confirm('提示','确定要删除吗?',function(data){
			if(data){
				var treeNode = menuManageAllTree.getSelectedNodes()[0];
				$.post("menu/delMenu?menuId="+treeNode.id, function(data){
					reloadTree();
					$.messager.alert('提示',data.mes,'info');
				});
			}
		});
	}

	//重新加载菜单树
	function reloadTree(){
		menuManageAllTree.reAsyncChildNodes(null, "refresh");
	}
	
	$(document).ready(function() {
    	//加载菜单树
		menuManageAllTree = $.fn.zTree.init($("#menuManageAllTree"), menuManageAllSetting); 
	});
	
	</script>
	<div class="operationPageMenuLeft">
		<ul id="menuManageAllTree" class="ztree"></ul>
	</div>
	<div class="operationPageMenuRight">
		<div class="mt5 mr5">
			<div id="rightMenuManageAllTreeContent" class="easyui-panel" title="菜单基本信息" closed='true'  class="fw" style="height:70px">
 				<table id="rightMenuManageAllTreeInfoTable" class="fw fh">
 					<tr>
	 					<td width="10%" align="right">菜单名称：</td>
	 					<td id="rightMenuManageAllTreeInfoName" width="10%" align="left"></td>
	 					<td width="10%" align="right">菜单排序：</td>
	 					<td id="rightMenuManageAllTreeInfoDesc" width="10%" align="left"></td>
	 					<td width="10%" align="right">打开方式：</td>
	 					<td id="rightMenuManageAllTreeInfoOType" width="10%" align="left"></td>
	 					<td width="10%" align="right">菜单URL：</td>
	 					<td id="rightMenuManageAllTreeInfoURL"  width="30%" align="left"></td>
 					</tr>
 				</table>
	 		</div>
	 		<div id="rightMenuManageAllAuthTableContent" class="pt5 dn">
				<table id="menuManageResourceTable" name="menuManageResourceTable" class="fw" ></table>
	 		</div>
 		</div>
	</div>
	<div id="menuManageRightMenu" class="easyui-menu" style="width:150px;">
		<div id="menuManageRightMenuaddMenu" data-options="iconCls:'icon-add'"
		 onclick="addMenuManageAllMenuNode();">增加菜单</div>
		<div id="menuManageRightMenuupMenu" data-options="iconCls:'icon-edit'"
		 onclick="editMenuManageAllMenuNode();">修改菜单</div>
		<div id="menuManageRightMenudelMenu" data-options="iconCls:'icon-remove'"
		 onclick="delMenuManageAllMenuNode();">删除菜单</div>
	</div>
</body>
