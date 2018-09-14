<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	 <script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		jQuery(function($){
			//加载机构树
			$(document).ready(function() {
				//页面准备完毕
				var jqueryObj=$("#orgIdTask");
		 		makeOrgTree(jqueryObj);
			});
			//加载datagride整个表格
			$('#patrolManagementTaskTable').datagrid({
				title:'巡防队伍信息', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"patrol/patrolTeamInfoQueryList",	 		//数据来源
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
						{field:'name',title:'巡防队伍名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'charger',title:'负责人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.charger;}                                
						},
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
						},
						{field:'orgId',title:'所属机构',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.orgId;}                                
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
						]],
				toolbar:[
						<sec:authorize url="/patrol/addPatrolTeamInfoPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addPatrolTeamInfoPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/updatePatrolTeamInfoPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updatePatrolManagementTaskPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/deletePatrolTeamInfo">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deletePatrolTeamInfo();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/patrolTeamUserRelationInit">
						{  text:'查看队伍人员信息', iconCls:'icon-tip',
							handler:function(){showPartrolTeamUserRelation();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#patrolManagementTaskTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addPatrolTeamInfoPopWin(){
			showWindow({
				title:'增加巡防队伍信息',
				href:'patrol/addPatrolTeamInfoPopWin',
				width:650,
				height:200,
				onLoad: function(){
					$('#patrolManagementTaskAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updatePatrolManagementTaskPopWin(){
			var rows = $('#patrolManagementTaskTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的巡防",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条巡防进行更新",'info');
				return;
			}
			showWindow({
				title:'更新巡防队伍信息',
				href:'patrol/updatePatrolTeamInfoPopWin',
				width:650,
				height:200,
				onLoad: function(){
					$("#patrolManagementTaskUpdateForm").form('load', rows[0]);
					$("#patrolManagementTaskUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		//删除
		function deletePatrolTeamInfo(){
			var rows = $('#patrolManagementTaskTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的巡防队伍信息",'info');
				return;
			}
			if(rows.length>1){
				$.messager.alert('提示',"请逐条删除巡防队伍信息",'info');
				return;
			}
			$.messager.confirm('提示','您确定要删除吗?',function(result){
				if (result){
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?idList="+n.id;
						} else {
							ps += "&idList="+n.id;
						}	
					});
					$.post('patrol/deletePatrolTeamInfo'+ps,function(data){
						$('#patrolManagementTaskTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		// 展示巡防队伍关系
		function showPartrolTeamUserRelation() {
			var rows = $('#patrolManagementTaskTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的队伍",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个队伍进行查看",'info');
				return;
			}
			showWindow({
				title:'队伍人员信息',
				href:'patrol/patrolTeamUserRelationInit',
				width:1000,
				height:600
				,onLoad: function(){
				    var opts = $("#partolTeamUserRelationTable").datagrid("options");
				    opts.url = "patrol/patrolTeamUserRelationQueryList?teamInfoId=" + rows[0].id;
				    $("#partolTeamUserRelationTable").datagrid("load");
				    selectedTeamUser = rows[0];
				}
			});
		}
		//表格查询
		function searchPatrolManagementTask(){
			var params = $('#patrolManagementTaskTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#patrolManagementTaskQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#patrolManagementTaskTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearPatrolManagementTask(){
			$('#patrolManagementTaskQuForm .easyui-combobox').combobox('clear');
			$('#patrolManagementTaskQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="patrolManagementTaskQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="5%" align="right">所属机构：</td>
							<td width="10%" align="left">
								<input id="orgIdTask" name="orgId" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchPatrolManagementTask();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearPatrolManagementTask();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="patrolManagementTaskTable"></table>
			</div>
		</div>
	</div> 
</body>
