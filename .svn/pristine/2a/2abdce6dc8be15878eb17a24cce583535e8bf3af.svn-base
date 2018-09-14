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
				var jqueryObj=$("#orgIdBroadcast");
		 		makeOrgTree(jqueryObj);
			});
			//加载datagride整个表格
			$('#baseInfoDefenceBroadcastTable').datagrid({
				title:'广播警示柱基本信息表', 									//标题
				method:'post',
				iconCls:'icon-tip', 												//图标
				singleSelect:false, 												//多选
				height:366, 														//高度
				fit:true,
				fitColumns: false, 												//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 													//奇偶行颜色不同                        
				collapsible:false,												//可折叠
				url:"baseInfo/baseInfoDefenceBroadcastQueryList",	 			//数据来源
				sortName: 'id',													//排序的列
				sortOrder: 'asc', 												//倒序
				remoteSort: true, 												//服务器端排序
				idField:'id', 													//主键字段 
				queryParams:{}, 													//查询条件
				pagination:true, 												//显示分页
				rownumbers:true, 												//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'广播警示柱名称',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'number',title:'广播警示柱编号',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.number;}                                
						},
						{field:'ip',title:'IP',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.ip;}                                
						},
						{field:'port',title:'端口',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.port;}                                
						},
						{field:'broType',title:'广播警示柱型号',sortable:true, hidden:true,width:110,                             
							formatter:function(value,row,index){return row.broType;}                                
						},
						{field:'broTypeName',title:'广播警示柱型号',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.broTypeName;}                                
						},
						{field:'profileName',title:'照片',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'adress',title:'地址',sortable:true, hidden:false,width:250,                             
							formatter:function(value,row,index){return row.adress;}                                
						},
						{field:'status',title:'广播警示柱状态',sortable:true, hidden:true,width:110,                             
							formatter:function(value,row,index){return row.status;}                                
						},
						{field:'statusName',title:'广播警示柱状态',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.statusName;}                                
						},
						{field:'voicebroadcast',title:'语音播报',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.voicebroadcast;}                                
						},
						{field:'broadcasting',title:'单点广播',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.broadcasting;}                                
						},
						{field:'monitorId',title:'摄像头ID',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.monitorId;}                                
						},
						{field:'charger',title:'负责人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.charger;}                                
						},
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
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
						{field:'lng',title:'经度',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lat;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoDefenceBroadcastPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoDefenceBroadcastPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoDefenceBroadcastPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoDefenceBroadcastPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoDefenceBroadcast">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoDefenceBroadcast();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoDefenceBroadcastTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addBaseInfoDefenceBroadcastPopWin(){
			showWindow({
				title:'增加广播警示柱基本信息',
				href:'baseInfo/addBaseInfoDefenceBroadcastPopWin',
				width:800,
				height:450,
				onLoad: function(){
					$('#baseInfoDefenceBroadcastAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateBaseInfoDefenceBroadcastPopWin(){
			var rows = $('#baseInfoDefenceBroadcastTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的广播警示柱",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个广播警示柱进行更新",'info');
				return;
			}
			showWindow({
				title:'更新广播警示柱基本信息',
				href:'baseInfo/updateBaseInfoDefenceBroadcastPopWin?photosName='+rows[0].photosName,
				width:800,
				height:450,
				onLoad: function(){
					$("#baseInfoDefenceBroadcastUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteBaseInfoDefenceBroadcast(){
			var rows = $('#baseInfoDefenceBroadcastTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的广播警示柱",'info');
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
					$.post('baseInfo/deleteBaseInfoDefenceBroadcast'+ps,function(data){
						$('#baseInfoDefenceBroadcastTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		//表格查询
		function searchBaseInfoDefenceBroadcast(){
			var params = $('#baseInfoDefenceBroadcastTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoDefenceBroadcastQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoDefenceBroadcastTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoDefenceBroadcast(){
			$('#baseInfoDefenceBroadcastQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoDefenceBroadcastQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoDefenceBroadcastQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="2%" align="right">名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="2%" align="right">地址：</td>
							<td width="10%" align="left">
								<input name="adress" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoDefenceBroadcast();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdBroadcast" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
							</td>
							<td width="10%" align="right">铁路周边：</td>
							<td width="25%" align="left">
								<select id="railId" class="easyui-combobox" name="railId" style="width:90%;"
								 data-options="editable:true,required:false">		
								 <option value="请选择" disabled selected></option>					
							        <c:forEach items="${rails}" var="railInfoTmp">
								    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
							        </c:forEach>
							   	</select>
							</td>
							<td width="30%" align="left" >
								<a onclick="clearBaseInfoDefenceBroadcast();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoDefenceBroadcastTable"></table>
			</div>
		</div>
	</div>
</body>