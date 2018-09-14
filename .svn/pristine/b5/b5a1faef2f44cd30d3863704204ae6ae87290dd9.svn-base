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
				var jqueryObj=$("#orgId");
		 		makeOrgTree(jqueryObj);
			});
			 /* 这里需要解释一下：此处的selectedRail是为了将在baseInfoRailInit.jsp中选中的铁路信息传递过来  */
			var selectedRail = {};
			
			//加载datagride整个表格
			$('#pubMapRailDataTable').datagrid({
				title:'线路坐标基本信息列表', 					//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"",	 										//数据来源
				sortName: 'orderNumber',						//排序的列
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
						{field:'orderNumber',title:'点序号',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.orderNumber;}                                
						},
						{field:'lng',title:'经度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.lat;}                                
						},
						{field:'kilometerMark',title:'公里标',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.kilometerMark;}                                
						},
						{field:'streamName',title:'行别',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.streamName;}                                
						},
						{field:'orgId',title:'机构ID',width:30,sortable:false,hidden:true, //机构描述id隐藏
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
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
					/* 	<sec:authorize url="/baseInfo/addBaseInfoRailDataPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addPubMapRailDataPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoRailDataPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updatePubMapRailDataPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoRailData">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deletePubMapRailData();}
						},'-',
						</sec:authorize> */
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#pubMapRailDataTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增线路信息
		function addPubMapRailDataPopWin(){
			showSecondWindow({
				title:'增加线路基本信息',
				href:'baseInfo/addBaseInfoRailDataPopWin',
				width:600,
				height:300,
				onLoad: function(){
					/* 添加线路页面需要知道相关的铁路信息 */
					var f = $('#pubMapRailDataAddForm');
					f.form('reset');
					f.form('load', {
						railName: selectedRail.name,
						railId: selectedRail.id
					});
				}
			});
		}
				
		//更新线路信息
		function updatePubMapRailDataPopWin(){
			var rows = $('#pubMapRailDataTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的线路地图",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条线路进行更新",'info');
				return;
			}
			showSecondWindow({
				title:'更新线路基本信息',
				href:'baseInfo/updateBaseInfoRailDataPopWin',
				width:600,
				height:300,
				onLoad: function(){
					var f = $('#pubMapRailDataUpdateForm');
					f.form('load', rows[0]);
					f.form('load', {
						orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}],
						railName: selectedRail.name,
						railId: selectedRail.id
					});
				}
			});
		}
				
		//删除线路信息
		function deletePubMapRailData(){
			var rows = $('#pubMapRailDataTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的线路",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"请逐条删除线路",'info');
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
					$.post('baseInfo/deleteBaseInfoRailData'+ps,function(data){
						$('#pubMapRailDataTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchPubMapRailData(){
			var params = $('#pubMapRailDataTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#pubMapRailDataQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#pubMapRailDataTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearPubMapRailData(){
			$('#pubMapRailDataQuForm .easyui-combobox').combobox('clear');
			$('#pubMapRailDataQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="pubMapRailDataQuForm" class="fw fh">
						<input type="hidden" name="railName">
						<input type="hidden" name="railId">
					<table  class="fw fh">
						<tr>
							<td width="7%" align="right">所属机构：</td>
							<td width="17%" align="left">
								<input id="orgId" name="orgId" class="easyui-textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
							</td>
							
							<td width="10%" align="center" >
								<a onclick="searchPubMapRailData();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							<td width="40%" align="left">
								<a onclick="clearPubMapRailData();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="pubMapRailDataTable"></table>
			</div>
		</div>
	</div>
</body>
