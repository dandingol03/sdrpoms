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
			});
			//加载datagride整个表格
			$('#dangerInfoTaskTable').datagrid({
				title:'隐患基本信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				//重复调用隐患处置查询
				url:"patrol/dangerInfoDisposeQueryList",	 	//数据来源
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
						{field:'dangeTypeName',title:'隐患分类',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.dangeTypeName;}                                
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
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/patrol/addPatrolDangerInfoPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addDangerInfoPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/updateDangerInfoPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateDangerInfoPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/deletePatrolDangerInfo">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteDangerInfo();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#dangerInfoTaskTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addDangerInfoPopWin(){
			showWindow({
				title:'增加隐患上报基本信息',
				href:'patrol/addDangerInfoPopWin',
				width:700,
				height:370,
				onLoad: function(){
					$('#patrolManagementTaskAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateDangerInfoPopWin(){
			var rows = $('#dangerInfoTaskTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的隐患上报",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条隐患上报进行更新",'info');
				return;
			}
			showWindow({
				title:'更新隐患上报基本信息',
				href:'patrol/updateDangerInfoPopWin?photosName='+rows[0].photosName,
				width:700,
				height:370,
				onLoad: function(){
					$("#dangerInfoTaskUpdateForm").form('load', rows[0]);
					$("#dangerInfoTaskUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		//删除
		function deleteDangerInfo(){
			var rows = $('#dangerInfoTaskTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的隐患上报",'info');
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
					$.post('patrol/deleteDangerInfo'+ps,function(data){
						$('#dangerInfoTaskTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		
		//表格查询
		function searchDangerInfoTask(){
			var params = $('#dangerInfoTaskTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#dangerInfoTaskQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#dangerInfoTaskTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearDangerInfoTask(){
			$('#dangerInfoTaskQuForm .easyui-combobox').combobox('clear');
			$('#dangerInfoTaskQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="dangerInfoTaskQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchDangerInfoTask();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearDangerInfoTask();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="dangerInfoTaskTable"></table>
			</div>
		</div>
	</div> 
</body>
