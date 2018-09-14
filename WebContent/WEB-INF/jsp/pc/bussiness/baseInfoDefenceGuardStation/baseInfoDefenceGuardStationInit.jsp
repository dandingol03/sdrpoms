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
				var jqueryObj=$("#orgIdGuardStation");
		 		makeOrgTree(jqueryObj);
			});
			
			//加载datagride整个表格
			$('#baseInfoDefenceGuardStationTable').datagrid({
				title:'护路工作站基本信息列表', 							//标题
				method:'post',
				iconCls:'icon-tip', 										//图标
				singleSelect:false, 										//多选
				height:366, 												//高度
				fit:true,
				fitColumns: false, 										//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 											//奇偶行颜色不同                        
				collapsible:false,										//可折叠
				url:"baseInfo/baseInfoDefenceGuardStationQueryList",	 		//数据来源
				sortName: 'id',											//排序的列
				sortOrder: 'asc', 										//倒序
				remoteSort: true, 										//服务器端排序
				idField:'id', 											//主键字段 
				queryParams:{}, 											//查询条件
				pagination:true, 										//显示分页
				rownumbers:true, 										//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'护路工作站名称',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'profileName',title:'照片',sortable:true, hidden:false,width:110,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'adress',title:'地址',sortable:true, hidden:false,width:250,                             
							formatter:function(value,row,index){return row.adress;}                                
						},
						{field:'peopleNum',title:'人数',sortable:true, hidden:false,width:70,                             
							formatter:function(value,row,index){return row.peopleNum;}                                
						},
						{field:'serviceMode',title:'勤务模式',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.serviceMode;}                                
						},
						{field:'serviceModeName',title:'勤务模式',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.serviceModeName;}                                
						},
						{field:'patrolRange',title:'巡防范围',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.patrolRange;}                                
						},
						{field:'guardTarget',title:'守护目标',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.guardTarget;}                                
						},
						{field:'patrolTeam',title:'巡防力量',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.patrolTeam;}                                
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
						{field:'isAccept',title:'市验收挂牌',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.isAccept;}                                
						},
						{field:'isAcceptName',title:'市验收挂牌',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.isAcceptName;}                                
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
						<sec:authorize url="/baseInfo/addBaseInfoDefenceGuardStationPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoDefenceGuardStationPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoDefenceGuardStationPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoDefenceGuardStationPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoDefenceGuardStation">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoDefenceGuardStation();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoDefenceGuardStationTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addBaseInfoDefenceGuardStationPopWin(){
			showWindow({
				title:'增加护路工作站基本信息',
				href:'baseInfo/addBaseInfoDefenceGuardStationPopWin',
				width:800,
				height:420,
				onLoad: function(){
					$('#baseInfoDefenceGuardStationAddForm').form('reset');
				}
			});
		}
				
		//更新    
			function updateBaseInfoDefenceGuardStationPopWin(){
			var rows = $('#baseInfoDefenceGuardStationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新护路工作站",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个护路工作站进行更新",'info');
				return;
			}
			showWindow({
				title:'更新护路工作站基本信息',
				href:'baseInfo/updateBaseInfoDefenceGuardStationPopWin?photosName='+rows[0].photosName,
				width:800,
				height:420,
				onLoad: function(){
					$("#baseInfoDefenceGuardStationUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		
		
		//删除
		function deleteBaseInfoDefenceGuardStation(){
			var rows = $('#baseInfoDefenceGuardStationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的护路工作站",'info');
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
					$.post('baseInfo/deleteBaseInfoDefenceGuardStation'+ps,function(data){
						$('#baseInfoDefenceGuardStationTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		
		//表格查询
		function searchBaseInfoDefenceGuardStation(){
			var params = $('#baseInfoDefenceGuardStationTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoDefenceGuardStationQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoDefenceGuardStationTable').datagrid('reload');
		}
		
		//清空查询条件
		function clearBaseInfoDefenceGuardStation(){
			$('#baseInfoDefenceGuardStationQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoDefenceGuardStationQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoDefenceGuardStationQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">护路工作站名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="2%" align="right">地址：</td>
							<td width="10%" align="left">
								<input name="adress" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoDefenceGuardStation();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
							<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdGuardStation" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
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
								<a onclick="clearBaseInfoDefenceGuardStation();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoDefenceGuardStationTable"></table>
			</div>
		</div>
	</div>
</body>
