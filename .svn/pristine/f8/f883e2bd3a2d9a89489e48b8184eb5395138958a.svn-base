<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
    <script type="text/javascript">
    jQuery.ajaxSetup({cache:false});//ajax不缓存
    jQuery(function($){
		//加载datagride
		$('#hasRegResourceTable').datagrid({
			title:'已注册资源', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:220, 					//高度
			fit:true,
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:false,				//可折叠
			url:"authority/queryHasRegResList", 		//数据来源
			sortName: 'resourceId',			//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'resourceId', 			//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'resourceName',title:'资源名称',width:20,sortable:true,
						formatter:function(value,row,index){return row.resourceName;}
					},
					{field:'resourceType',title:'资源类型',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.resourceType == 'm'?'菜单':'按钮';}
					},
					{field:'priority',title:'优先级',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.priority;}
					},
					{field:'resourceString',title:'资源URL',width:20,sortable:true,
						formatter:function(value,row,index){return row.resourceString;}
					},
					{field:'resourceDesc',title:'资源描述',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.resourceDesc;}
					},
					{field:'enable',title:'是否可用',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
					},
					{field:'isSys',title:'是否注册',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
					}
					]],
			toolbar:[
					{text:'删除', iconCls:'icon-remove',
						handler:function(){deleteAuthResource();}
					},'-'
					],
			onDblClickRow: function (rowIndex, rowData) {
				var rows = $('#hasRegResourceTable').datagrid('getRows');
				var row = rows[rowIndex];
	    		var ps = "?resourceId="+row.resourceId;
				$.post('authority/deleteAuthResource'+ps+'&checkedAuthId=${checkedAuthId}',function(data){
					$('#notRegResourceTable').datagrid('reload');
			    	$('#hasRegResourceTable').datagrid('reload');
			    	//$.messager.alert('提示',data.mes,'info');
				});
			},
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#hasRegResourceTable').datagrid('clearSelections');}
		});
		
		//加载datagride
		$('#notRegResourceTable').datagrid({
			title:'未注册资源', 				//标题
			method:'post',
			iconCls:'icon-tip', 			//图标
			singleSelect:false, 			//多选
			height:260, 					//高度
			fit:true,
			fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
			striped: true, 					//奇偶行颜色不同                        
			collapsible:false,				//可折叠
			url:"authority/queryNotRegResList", 		//数据来源
			sortName: 'resourceId',			//排序的列
			sortOrder: 'desc', 				//顺序
			remoteSort: true, 				//服务器端排序
			idField:'resourceId', 			//主键字段 
			queryParams:{}, 				//查询条件
			pagination:true, 				//显示分页
			rownumbers:true, 				//显示行号
			columns:[[
					{field:'ck',checkbox:true,width:2}, //显示复选框 
					{field:'resourceName',title:'资源名称',width:20,sortable:true,
						formatter:function(value,row,index){return row.resourceName;}
					},
					{field:'resourceType',title:'资源类型',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.resourceType == 'm'?'菜单':'按钮';}
					},
					{field:'priority',title:'优先级',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.priority;}
					},
					{field:'resourceString',title:'资源URL',width:20,sortable:true,
						formatter:function(value,row,index){return row.resourceString;}
					},
					{field:'resourceDesc',title:'资源描述',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.resourceDesc;}
					},
					{field:'enable',title:'是否可用',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
					},
					{field:'isSys',title:'是否注册',width:20,sortable:false,hidden:true,
						formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
					}
					]],
			toolbar:[
					{text:'增加', iconCls:'icon-add',
						handler:function(){addAuthResource();}
					},'-'
					],
			onDblClickRow: function (rowIndex, rowData) {
						var rows = $('#notRegResourceTable').datagrid('getRows');
						var row = rows[rowIndex];
				    	var ps = "?resourceId="+row.resourceId;
						$.post('authority/addAuthResource'+ps+'&checkedAuthId=${checkedAuthId}',function(data){
							$('#notRegResourceTable').datagrid('reload');
					    	$('#hasRegResourceTable').datagrid('reload');
						});
					},
			//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
			onLoadSuccess:function(){$('#notRegResourceTable').datagrid('clearSelections');}
		});
		
	});

    //删除[权限－资源]对应关系
    function deleteAuthResource(){
    	var rows = $('#hasRegResourceTable').datagrid('getSelections');
    	if(rows.length==0){
			$.messager.alert('提示',"请选择你要删除的资源",'info');
			return;
		}
    	$.messager.confirm('提示','确定要删除吗?',function(result){
    		var ps = "";
			$.each(rows,function(i,n){
				if(i==0){
					ps += "?resourceId="+n.resourceId;
				} else {
					ps += "&resourceId="+n.resourceId;
				}	
			});
			
			$.post('authority/deleteAuthResource'+ps+'&checkedAuthId=${checkedAuthId}',function(data){
				$('#notRegResourceTable').datagrid('reload');
		    	$('#hasRegResourceTable').datagrid('reload');
		    	$.messager.alert('提示',data.mes,'info');
			});
    	});	
    }
    
    //增加[权限－资源]对应关系
    function addAuthResource(){
    	var rows = $('#notRegResourceTable').datagrid('getSelections');
    	if(rows.length==0){
			$.messager.alert('提示',"请选择你要添加的资源",'info');
			return;
		}
    	var ps = "";
		$.each(rows,function(i,n){
			if(i==0){
				ps += "?resourceId="+n.resourceId;
			} else {
				ps += "&resourceId="+n.resourceId;
			}	
		});
		$.post('authority/addAuthResource'+ps+'&checkedAuthId=${checkedAuthId}',function(data){
			$('#notRegResourceTable').datagrid('reload');
	    	$('#hasRegResourceTable').datagrid('reload');
	    	$.messager.alert('提示',data.mes,'info');
		});
    }
	
    $(document).ready(function(){
    	var params = $('#notRegResourceTable').datagrid('options').queryParams;
    	params["checkedAuthId"] = "${checkedAuthId}";
		$('#notRegResourceTable').datagrid('reload'); 
		params = $('#hasRegResourceTable').datagrid('options').queryParams;
		params["checkedAuthId"] = "${checkedAuthId}";
		$('#hasRegResourceTable').datagrid('reload'); 
	});
	</script>
	<div class="fw" style="height:10px"></div>
	<div class="fl h90" style="width:1%"></div> 
	<div class="fl h90" style="width:48%">
	  <table id="notRegResourceTable" class="fw fh"></table>
    </div>
    <div class="fl h90" style="width:1%"></div>
    <div class="fl h90" style="width:49%">
	  <table id="hasRegResourceTable" class="fw fh"></table>
    </div>
    <div class="fl h90" style="width:1%"></div>
    <div class="cb fw"></div>
</body>
