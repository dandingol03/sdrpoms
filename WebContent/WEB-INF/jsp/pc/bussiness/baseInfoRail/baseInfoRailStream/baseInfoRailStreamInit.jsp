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
			//加载datagride整个表格
			$('#baseInfoRailStreamTable').datagrid({
				title:'铁路明细', 							//标题
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
						{field:'railName',title:'铁路名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railName;}                                
						},
						{field:'startStr',title:'起点里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.startStr;}                                
						},
						{field:'endStr',title:'终点里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.endStr;}                                
						},
						{field:'streamName',title:'行别',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.streamName;}                                
						},
						{field:'startAddress',title:'起点位置',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.startAddress;}                                
						},
						{field:'endAddress',title:'终点位置',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.endAddress;}                                
						}
						]],
				toolbar:[
							<sec:authorize url="/baseInfo/baseInfoRailDataInit">
							{  text:'线路坐标信息', iconCls:'icon-tip',
								handler:function(){showPubMapRailData();}
							},'-',
							</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoRailStreamTable').datagrid('clearSelections');}
			});
			
		});
		// 展示铁路地图信息
		function showPubMapRailData() {
			var rows = $('#baseInfoRailStreamTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的铁路",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条铁路进行查看",'info');
				return;
			}
			showSecondWindow({
				title:'线路坐标基本信息',
				href:'baseInfo/baseInfoRailDataInit',
				width:800,
				height:600
				,onLoad: function(){
					console.log('=====>1=====');
					/* 根据选中baseInfoRailId 加载数据 */
				    var opts = $("#pubMapRailDataTable").datagrid("options");
				    opts.url = "baseInfo/baseInfoRailDataQueryList?baseInfoRailId=" + rows[0].id;
				    $("#pubMapRailDataTable").datagrid("load");
				    /* 这里需要解释一下：此处的selectedRail是在 pubMapRailDataInit.jsp中声明的，为了将选中的铁路信息传递过去  */
				    selectedRail = rows[0];
				}
			});
		}
		//表格查询
		function searchBaseInfoRailStream(){
			var params = $('#baseInfoRailStreamTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoRailStreamQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoRailStreamTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoRailStream(){
			$('#baseInfoRailStreamQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoRailStreamQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoRailStreamQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">铁路名称：</td>
							<td width="10%" align="left">
								<input name="railName" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoRailStream();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearBaseInfoRailStream();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoRailStreamTable"></table>
			</div>
		</div>
	</div>
</body>
