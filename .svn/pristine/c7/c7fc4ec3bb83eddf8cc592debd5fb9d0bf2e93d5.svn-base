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
			//加载datagride整个表格
			$('#videoMonitorInfoTable').datagrid({
				title:'监控基本信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"baseInfo/videoMonitorInfoQueryList",	 		//数据来源
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
						{field:'name',title:'监控探头名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'number',title:'监控探头编号',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.number;}                                
						},
						{field:'monitoringPosition',title:'监控部位',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.monitoringPosition;}                                
						},
						{field:'accessDepartment',title:'接入部门',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.accessDepartment;}                                
						},
						{field:'adress',title:'地址',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.adress;}                                
						},
						{field:'administration',title:'管理部门',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.administration;}                                
						},
						{field:'charger',title:'负责人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.charger;}                                
						},
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
						},
						{field:'username',title:'用户名',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.username;}                                
						},
						{field:'pwd',title:'密码',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.pwd;}                                
						},
						{field:'channel',title:'通道ID',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.channel;}                                
						},
						{field:'ip',title:'ip地址',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.ip;}                                
						},
						{field:'port',title:'端口号',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.port;}                                
						},
						{field:'videoTypeName',title:'监控类型',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.videoTypeName;}                                
						},
						{field:'orgId',title:'所属机构ID',sortable:true, hidden:true,width:100,                             
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
						{field:'lng',title:'经度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lat;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addVideoMonitorInfoPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addvideoMonitorInfoPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateVideoMonitorInfoPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updatevideoMonitorInfoPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteVideoMonitorInfo">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deletevideoMonitorInfo();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#videoMonitorInfoTable').datagrid('clearSelections');}
			});
			
		});
		//新增
		function addvideoMonitorInfoPopWin(){
			showWindow({
				title:'增加监控基本信息',
				href:'baseInfo/addVideoMonitorInfoPopWin',
				width:700,
				height:455,
				onLoad: function(){
					$('#videoMonitorInfoAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updatevideoMonitorInfoPopWin(){
			var rows = $('#videoMonitorInfoTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的监控",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条监控进行更新",'info');
				return;
			}
			showWindow({
				title:'更新监控基本信息',
				href:'baseInfo/updateVideoMonitorInfoPopWin',
				width:700,
				height:455,
				onLoad: function(){
					$("#videoMonitorInfoUpdateForm").form('load', rows[0]);
					$("#videoMonitorInfoUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		//删除
		function deletevideoMonitorInfo(){
			var rows = $('#videoMonitorInfoTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的监控",'info');
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
					$.post('baseInfo/deleteVideoMonitorInfo'+ps,function(data){
						$('#videoMonitorInfoTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchVideoMonitorInfo(){
			var params = $('#videoMonitorInfoTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#videoMonitorInfoQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#videoMonitorInfoTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearVideoMonitorInfo(){
			$('#videoMonitorInfoQuForm .easyui-combobox').combobox('clear');
			$('#videoMonitorInfoQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="videoMonitorInfoQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="5%" align="right">编号：</td>
							<td width="10%" align="left">
								<input name="number" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchVideoMonitorInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearVideoMonitorInfo();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="videoMonitorInfoTable"></table>
			</div>
		</div>
	</div>
</body>
