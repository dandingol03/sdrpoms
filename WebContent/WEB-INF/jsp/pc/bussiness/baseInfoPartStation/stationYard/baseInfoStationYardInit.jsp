<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			$(document).ready(function() {
				//页面准备完毕
				
			});
			 var selectedStation = {};
			//加载datagride整个表格(datagride以数据表格的形式进行展示)
			$('#baseInfoStationYardTable').datagrid({
				title:'站场基本信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"",	//数据来源
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
						{field:'name',title:'站场名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'profileName',title:'站场照片',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'lng',title:'经度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lat;}                                
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
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],//添加显示行
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoPartStationYardRelPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoStationYardPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoPartStationYardRelPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoStationYardPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoPartStationYardRel">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoStationYard();}
						},'-',
						</sec:authorize>
						],//添加按钮
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoStationYardTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增(弹出窗口)
		function addBaseInfoStationYardPopWin(){
			showSecondWindow({
				title:'增加站场基本信息',
				href:'baseInfo/addBaseInfoPartStationYardRelPopWin',
				width:700,
				height:240,
				onLoad: function(){
					var f = $('#baseInfoStationYardAddForm');
					f.form('reset');
					f.form('load', {
						stationName: selectedStation.name, 
						stationId: selectedStation.id
					}); 
				}
			});
		}
				
		//更新(弹出窗口)    
		function updateBaseInfoStationYardPopWin(){
			var rows = $('#baseInfoStationYardTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的站场",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条站场进行更新",'info');
				return;
			}
			showSecondWindow({
				title:'更新站场基本信息',
				href:'baseInfo/updateBaseInfoPartStationYardRelPopWin?photosName='+rows[0].photosName,
				width:700,
				height:240,
				onLoad: function(){
					var f = $('#baseInfoStationYardUpdateForm');
					f.form('load', rows[0]);
					f.form('load', {
						stationName: selectedStation.name, 
						stationId: selectedStation.id
					}); 
				}
			});
		}
				
		//删除(弹出窗口)
		function deleteBaseInfoStationYard(){
			var rows = $('#baseInfoStationYardTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的站场",'info');
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
					$.post('baseInfo/deleteBaseInfoPartStationYardRel'+ps,function(data){
						$('#baseInfoStationYardTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询(弹出窗口)
		function searchBaseInfoStationYard(){
			var params = $('#baseInfoStationYardTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoStationYardQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoStationYardTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoStationYard(){
			$('#baseInfoStationYardQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoStationYardQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:15%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoStationYardQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="10%" align="right">站场名称：</td>
							<td width="15%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="50%" align="center" >
							
								<a onclick="searchBaseInfoStationYard();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearBaseInfoStationYard();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:80%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoStationYardTable"></table>
			</div>
		</div>
	</div>
</body>
