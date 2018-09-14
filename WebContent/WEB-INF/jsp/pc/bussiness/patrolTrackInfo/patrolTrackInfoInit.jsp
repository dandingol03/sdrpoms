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
			});
			 /* 这里需要解释一下：此处的selectedTeamUser是为了将在baseInfoRailInit.jsp中选中的铁路信息传递过来  */
			 var selectedUser = {};
			//加载datagride整个表格
			$('#patrolTrackInfoTable').datagrid({
				title:'巡防轨迹信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"",											//数据来源
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
						{field:'userName',title:'巡防人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.userName;}                                
						},
						{field:'track',title:'巡防轨迹',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.track;}                                
						},
						{field:'startTime',title:'任务开始时间',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.startTime;}                                
						},
						
						{field:'endTime',title:'任务完成时间',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.endTime;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/deletePatrolTrackInfo">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deletePatrolTrackInfo();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#patrolTrackInfoTable').datagrid('clearSelections');}
			});
			
		});
				
		//删除
		function deletePatrolTrackInfo(){
			var rows = $('#patrolTrackInfoTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的巡防轨迹",'info');
				return;
			}
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?idList="+n.id;
						} else {
							ps += "&idList="+n.id;
						}	
					});
					$.post('baseInfo/deletePatrolTrackInfo'+ps,function(data){
						$('#patrolTrackInfoTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchPatrolTrackInfo(){
			var params = $('#patrolTrackInfoTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#patrolTrackInfoQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#patrolTrackInfoTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearPatrolTrackInfo(){
			$('#patrolTrackInfoQuForm .easyui-combobox').combobox('clear');
			$('#patrolTrackInfoQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:15%;">
			<div class="fh pl10 pr10">
				<form id="patrolTrackInfoQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="15%" align="right">任务开始时间：</td>
							<td width="20%" align="left">
								<input name="startTime" class="easyui-datebox" style="width:90%;">
							</td>
							<td width="15%" align="right">任务结束时间：</td>
							<td width="20%" align="left">
								<input name="endTime" class="easyui-datebox" style="width:90%;">
							</td>
							<td width="30%" align="center" >
								<a onclick="searchPatrolTrackInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearPatrolTrackInfo();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:80%;">
			<div class="fh pl5 pr10">
				<table id="patrolTrackInfoTable"></table>
			</div>
		</div>
	</div>
</body>
