<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			//加载机构树
			$(document).ready(function() {
			/* 	var jqueryObj=$("#orgId");
		 		makeOrgTree(jqueryObj); */
			});
			//加载datagride整个表格
			$('#dangerInfoDisposeTaskTable').datagrid({
				title:'隐患基本信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"patrol/dangerInfoDisposeQueryList",	 		//数据来源
				sortName: 'id',									//排序的列
				sortOrder: 'asc', 								//倒序
				remoteSort: true, 								//服务器端排序
				idField:'id', 									//主键字段 
				queryParams:{}, 								//查询条件
				pagination:true, 								//显示分页
				rownumbers:true, 								//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'隐患名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'description',title:'隐患描述',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.description;}                                
						},
						{field:'profileName',title:'发现照片',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'railName',title:'参考铁路线',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railName;}                                
						},
						{field:'middleStr',title:'中心里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.middleStr;}                                
						},
						{field:'orgName',title:'所属机构',sortable:true, hidden:false,width:150,  
							formatter:function(value,row,index){
								if(row.descOrgName==row.orgName){
									return row.orgName
								}else{
									return row.descOrgName+","+row.orgName;
								}
							}
						},
						{field:'lng',title:'经度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lat;}                                
						},
						{field:'charger',title:'负责人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.charger;}                                
						}, 
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
						}, 
						{field:'reportUserName',title:'上报人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.reportUserName;}                                
						}, 
						{field:'treatUserName',title:'处置人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.treatUserName;}                                
						}, 
						{field:'reportTime',title:'上报时间',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.reportTime;}                                
						}, 
						{field:'handleStatusName',title:'处理结果状态',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.handleStatusName;}                                
						}, 
						{field:'handleWay',title:'处理方式',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.handleWay;}                                
						}, 
						{field:'resultDis',title:'结果描述',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.resultDis;}                                
						}, 
						{field:'profileNames',title:'结果照片',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.profileNames;}                                
						}, 
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/patrol/updateDangerInfoDisposePopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateDangerInfoDisposePopWin();}
						},'-',
						</sec:authorize>
				         ],
			});
			
		});
		//更新    
		function updateDangerInfoDisposePopWin(){
			var rows = $('#dangerInfoDisposeTaskTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的隐患",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条隐患进行更新",'info');
				return;
			}
			showWindow({
				title:'更新隐患处置基本信息',
				href:'patrol/updateDangerInfoDisposePopWin?photossName='+rows[0].photossName,
				width:600,
				height:250,
				onLoad: function(){
					$("#dangerInfoDisposeUpdateForm").form('load', rows[0]);
				}
			});
		}
		//表格查询
		function searchPatrolManagementTask(){
			var params = $('#dangerInfoDisposeTaskTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#dangerInfoDisposeTaskQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#dangerInfoDisposeTaskTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearPatrolManagementTask(){
			$('#dangerInfoDisposeTaskQuForm .easyui-combobox').combobox('clear');
			$('#dangerInfoDisposeTaskQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height: 10%;">
			<div class="fh pl10 pr10">
				<form id="dangerInfoDisposeTaskQuForm" class="fw fh">
					<table class="fw fh">
						<tr>
							<td width="5%" align="right">名称：</td>
							<td width="10%" align="left"><input name="name"
								class="easyui-textbox" style="width: 90%;"></td>
							<td width="30%" align="left"><a
								onclick="searchPatrolManagementTask();"
								class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								onclick="clearPatrolManagementTask();" class="easyui-linkbutton"
								iconCls="icon-undo">清空</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height: 90%;">
			<div class="fh pl5 pr10">
				<table id="dangerInfoDisposeTaskTable"></table>
			</div>
		</div>
	</div>
</body>
