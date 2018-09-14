<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});    // ajax不缓存
		jQuery(function($){
			//加载机构树
			$(document).ready(function() {
				var jqueryObj=$("#orgIdPoliceHouse");
		 		makeOrgTree(jqueryObj);
			});
			
			//加载datagride整个表格
			$('#baseInfoDefencePoliceHouseTable').datagrid({
				title:'派出所基本信息列表', 						// 标题
				method:'post',
				iconCls:'icon-tip', 							// 图标
				singleSelect:false, 							// 多选
				height:366, 									// 高度
				fit:true,
				fitColumns: false, 								// 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									// 奇偶行颜色不同                        
				collapsible:false,								// 可折叠
				url:"baseInfo/baseInfoDefencePoliceHouseQueryList",            // 数据来源
				sortName: 'id',									// 排序的列
				sortOrder: 'asc', 								// 倒序
				remoteSort: true, 								// 服务器端排序
				idField:'id', 									// 主键字段 
				queryParams:{}, 								// 查询条件
				pagination:true, 								// 显示分页
				rownumbers:true, 								// 显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2},     // 显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'派出所名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'adress',title:'地址 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.adress;}                                
						},
						{field:'charger',title:'负责人 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.charger;}                                
						},
						{field:'telephone',title:'联系电话 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.telephone;}                                
						},
						{field:'jurisdiction',title:'管辖范围 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.jurisdiction;}                                
						},
						{field:'lng',title:'经度 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度 ',sortable:true, hidden:false,width:150,                             
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
						{field:'profileName',title:'派出所照片',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoDefencePoliceHousePopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoDefencePoliceHousePopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoDefencePoliceHousePopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoDefencePoliceHousePopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoDefencePoliceHouse">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoDefencePoliceHouse();}
						},'-',
						</sec:authorize>
						],
				// 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoDefencePoliceHouseTable').datagrid('clearSelections');}
			});
			
		});
				
		// 新增
		function addBaseInfoDefencePoliceHousePopWin(){
			showWindow({
				title:'增加派出所基本信息',
				href:'baseInfo/addBaseInfoDefencePoliceHousePopWin',
				width:800,
				height:310,
				onLoad: function(){
					$('#baseInfoDefencePoliceHouseAddForm').form('reset');
				}
			});
		}
				
		// 更新    
		function updateBaseInfoDefencePoliceHousePopWin(){
			var rows = $('#baseInfoDefencePoliceHouseTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的派出所",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条派出所进行更新",'info');
				return;
			}
			showWindow({
				title:'更新派出所基本信息',
				href:'baseInfo/updateBaseInfoDefencePoliceHousePopWin?photosName='+rows[0].photosName,
				width:800,
				height:310,
				onLoad: function(){
					$("#baseInfoDefencePoliceHouseUpdateForm").form('load',rows[0]);
					$("#baseInfoDefencePoliceHouseUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		// 删除
		function deleteBaseInfoDefencePoliceHouse(){
			var rows = $('#baseInfoDefencePoliceHouseTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的派出所",'info');
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
					$.post('baseInfo/deleteBaseInfoDefencePoliceHouse'+ps,function(data){
						$('#baseInfoDefencePoliceHouseTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		// 表格查询
		function searchBaseInfoDefencePoliceHouse(){
			var params = $('#baseInfoDefencePoliceHouseTable').datagrid('options').queryParams;
			// 先取得 datagrid 的查询参数
			var fields =$('#baseInfoDefencePoliceHouseQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoDefencePoliceHouseTable').datagrid('reload');
		}
				
		// 清空查询条件
		function clearBaseInfoDefencePoliceHouse(){
			$('#baseInfoDefencePoliceHouseQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoDefencePoliceHouseQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoDefencePoliceHouseQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdPoliceHouse" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoDefencePoliceHouse();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
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
							<td width="10%" align="right"> </td>
							<td width="25%" align="left">
								
							</td>
							<td width="30%" align="left" >
								<a onclick="clearBaseInfoDefencePoliceHouse();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoDefencePoliceHouseTable"></table>
			</div>
		</div>
	</div>
</body>
