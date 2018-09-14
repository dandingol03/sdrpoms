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
			$('#responsibilityLineTable').datagrid({
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
						{field:'railName',title:'铁路名',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railName;}                                
						},
						{field:'teamInfoName',title:'队伍名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.teamInfoName;}                                
						},
						{field:'startStr',title:'起点里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.startStr;}                                
						},
						{field:'endStr',title:'终点里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.endStr;}                                
						},
						{field:'profileName',title:'区段照片',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addResponsibilityLinePopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addResponsibilityLinePopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateResponsibilityLinePopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateResponsibilityLinePopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteResponsibilityLine">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteResponsibilityLine();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#responsibilityLineTable').datagrid('clearSelections');}
			});
			
		});
		//新增
		function addResponsibilityLinePopWin(){
			showThirdWindow({
				title:'增加责任区段基本信息',
				href:'baseInfo/addResponsibilityLinePopWin',
				width:700,
				height:230,
				onLoad: function(){
					var f = $('#responsibilityLineAddForm');
					f.form('reset');
					f.form('load', {
						userName: selectedUser.userName, 
						userId: selectedUser.userId
					}); 
				}
			});
		}
				
		//更新    
		function updateResponsibilityLinePopWin(){
			var rows = $('#responsibilityLineTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的记录",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条记录进行更新",'info');
				return;
			}
			showThirdWindow({
				title:'更新监控基本信息',
				href:'baseInfo/updateResponsibilityLinePopWin?filesName='+rows[0].filesName,
				width:700,
				height:230,
				onLoad: function(){
					var f = $('#responsibilityLineUpdateForm');
					f.form('load', rows[0]);
					f.form('load', {
						userName: selectedUser.userName, 
						userId: selectedUser.userId
					}); 
				}
			});
		}	
		//删除
		function deleteResponsibilityLine(){
			var rows = $('#responsibilityLineTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的信息",'info');
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
					$.post('baseInfo/deleteResponsibilityLine'+ps,function(data){
						$('#responsibilityLineTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchResponsibilityLine(){
			var params = $('#responsibilityLineTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#responsibilityLineQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#responsibilityLineTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearResponsibilityLine(){
			$('#responsibilityLineQuForm .easyui-combobox').combobox('clear');
			$('#responsibilityLineQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:15%;">
			<div class="fh pl10 pr10">
				<form id="responsibilityLineQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="15%" align="right">铁路名称：</td>
							<td width="35%" align="left">
								<input name="railName" class="easyui-textbox" style="width:50%;">
							</td>
							<td width="50%" align="center" >
								<a onclick="searchResponsibilityLine();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearResponsibilityLine();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:80%;">
			<div class="fh pl5 pr10">
				<table id="responsibilityLineTable"></table>
			</div>
		</div>
	</div>
</body>
