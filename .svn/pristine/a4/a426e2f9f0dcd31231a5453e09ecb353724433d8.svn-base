<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		jQuery(function($){
			
			//加载datagride
			$('#resourceTable').datagrid({
				title:'资源列表', 				//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				/* collapsible:true,			//可折叠 */
				url:"resource/queryList", 		//数据来源
				sortName: 'resourceId',			//排序的列
				sortOrder: 'desc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'resourceId', 			//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				pageSize:10,					//加载的条数
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
						{field:'resourceDesc',title:'资源描述',width:20,sortable:true,
							formatter:function(value,row,index){return row.resourceDesc;}
						},
						{field:'enable',title:'是否可用',width:20,sortable:true,
							formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
						},
						{field:'isSys',title:'是否注册',width:20,sortable:true,
							formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
						}
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addResourceRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateResourceRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteResourceRow();}
						},'-',
						{text:'资源注册菜单', iconCls:'icon-redo',
							handler:function(){regResRow();}
						},'-',
						{text:'查看菜单资源树', iconCls:'icon-search',
							handler:function(){resTreeRow();}
						},'-',
						{text:'资源注册移动端菜单', iconCls:'icon-redo',
							handler:function(){regMobileResRow();}
						},'-',
						{text:'查看移动端菜单资源树', iconCls:'icon-search',
							handler:function(){resMobileTreeRow();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#resourceTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addResourceRow(){
			showWindow({
				title:'增加资源信息',
				href:'resource/addPopWin',
				width:350,
				height:270,
				onLoad: function(){$('#resourceAddForm').form('reset');}
			});
		}
				
		//更新    
		function updateResourceRow(){
			var rows = $('#resourceTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的资源",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个资源进行更新",'info');
				return;
			}
			showWindow({
				title:'更新资源信息',
				href:'resource/updatePopWin',
				width:350,
				height:270,
				onLoad: function(){
					$("#resourceUpForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteResourceRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#resourceTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?resourceId="+n.uid;
						} else {
							ps += "&resourceId="+n.uid;
						}	
					});
					$.post('resource/delResources'+ps,function(data){
						$('#resourceTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//注册资源
		function regResRow(){
			var rows = $('#resourceTable').datagrid('getSelections');
			//alert(rows[0].resourceId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要注册的资源",'info');
				return;
			}
			
			var resIds = "";
			$.each(rows,function(i,n){
				resIds += n.uid+",";
			});
			rows[0].resourceId = resIds;
			
			showWindow({
				title:'注册资源',
				href:'resource/regResPopWin',
				width:350,
				height:480,
				onLoad: function(){
					$("#regResForm").form('load', rows[0]);
				}
			});
		}
	
		//查看注册资源树
		function resTreeRow(){
			showWindow({
				title:'查看注册资源树',
				href:'resource/resTreePopWin',
				width:350,
				height:480,
				onLoad: function(){}
			});
		}
		
		//注册移动端资源
		function regMobileResRow(){
			var rows = $('#resourceTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要注册的资源",'info');
				return;
			}
			var resIds = "";
			$.each(rows,function(i,n){
				resIds += n.uid+",";
			});
			rows[0].resourceId = resIds;
			
			showWindow({
				title:'注册移动端资源',
				href:'resource/regResMobilePopWin',
				width:350,
				height:480,
				onLoad: function(){
					$("#regResMobileForm").form('load', rows[0]);
				}
			});
		}
	
		//查看注册资源树
		function resMobileTreeRow(){
			showWindow({
				title:'查看注册移动端资源树',
				href:'resource/resMobileTreePopWin',
				width:350,
				height:480,
				onLoad: function(){}
			});
		}
				
		//表格查询
		function searchResource(){
			var params = $('#resourceTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#resourceQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#resourceTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearResourceForm(){
			$('#resourceQuForm').form('clear');
			//searchUser();
		}
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="resourceQuForm" class="fw fh">
					<table class="fw fh">
						<tr>
							<td width="8%" align="right">资源名称：</td>
							<td width="20%" align="left"><input name="resourceName" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="right">资源URL：</td>
							<td width="20%" align="left"><input name="resourceString" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="center">
								<a onclick="searchResource();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
							<td width="8%" align="center">
								<a onclick="clearResourceForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
							<td  width="28%"></td>
						</tr>
					</table>
				</form> 
			</div>
		</div>
		<div class="fw" style="height:85%;">
			<div class="fh pl5 pr10">
				<table id="resourceTable" class="fw fh"></table>
			</div>
		</div> 
	</div>
</body>
