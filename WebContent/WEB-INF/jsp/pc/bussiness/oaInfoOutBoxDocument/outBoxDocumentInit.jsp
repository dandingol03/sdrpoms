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
			//加载datagride整个表格
			$('#oaInfoOutBoxDocumentTable').datagrid({
				title:'发件箱', 						    		//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"oaInfo/outBoxDocumentQueryList",	 		//数据来源
				sortName: 'id',									//排序的列
				sortOrder: 'asc', 								//倒序
				remoteSort: true, 								//服务器端排序
				idField:'id', 									//主键字段 
				queryParams:{}, 								//查询条件
				pagination:true, 								//显示分页
				rownumbers:true, 								//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2},     //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'title',title:'标题',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.title;}                                
						},
						{field:'stateName',title:'类型',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.stateName;}                                
						},
						{field:'sendUserIdName',title:'收件人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.sendUserIdName;}                                
						},
						{field:'hairUnit',title:'发件单位',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.hairUnit;}                                
						},
						{field:'sendTime',title:'发件日期',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.sendTime;}                                
						},
						{field:'sendState',title:'发送状态',width:70,sortable:false,
							formatter:function(value,row,index){return row.sendState == '0'?'已发送':'未发送';}
						},
						{field:'file',title:'操作',width:120,sortable:false,
							formatter:function(value,row,index){
								var url="<a href=javascript:outInfoDetailedInformation()>查看详细</a>";
								return url;
							}
						}, 
						]],
				toolbar:[
						<sec:authorize url="/oaInfo/addOutBoxDocumentPopWin">
						{  text:'写邮件', iconCls:'icon-add',
							handler:function(){addOaInfoOutBoxDocumentPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/oaInfo/deleteOutBoxDocument">
						{  text:'删除邮件', iconCls:'icon-remove',
							handler:function(){deleteOutBoxDocument();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#oaInfoOutBoxDocumentTable').datagrid('clearSelections');}
			});
			
		});
		//查询详情信息
		function outInfoDetailedInformation(){
			var rows = $('#oaInfoOutBoxDocumentTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请不要选择的邮件",'info');
				return;
			}
			showWindow({
				title:'查询详情信息',
				href:'oaInfoOutBox/detailedInformation',
				width:900,
				height:600,
				onOpen: function(){
					 rowList=rows;
				},
				onClose: function(){
					$('#oaInfoOutBoxDocumentTable').datagrid('reload');  
				},
			});
		}
				
		//新增
		function addOaInfoOutBoxDocumentPopWin(){
			showWindow({
				title:'邮件发送',
				href:'oaInfo/addOutBoxDocumentPopWin',
				width:900,
				height:550,
				onLoad: function(){
					$('#oaInfoOutBoxDocumentAddForm').form('reset');
				},
			});
		}
				
		//删除
		function deleteOutBoxDocument(){
			var rows = $('#oaInfoOutBoxDocumentTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的邮件",'info');
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
					$.post('oaInfo/deleteOutBoxDocument'+ps,function(data){
						$('#oaInfoOutBoxDocumentTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchOaInfoOutBoxDocument(){
			var params = $('#oaInfoOutBoxDocumentTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#oaInfoOutBoxDocumentQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#oaInfoOutBoxDocumentTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearOaInfoOutBoxDocument(){
			$('#oaInfoOutBoxDocumentQuForm .easyui-combobox').combobox('clear');
			$('#oaInfoOutBoxDocumentQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="oaInfoOutBoxDocumentQuForm" class="fw fh">
					  <table  class="fw fh">
						<tr>
							<td width="5%" align="right">标题：</td>
							<td width="10%" align="left">
								<input name="title" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchOaInfoOutBoxDocument();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearOaInfoOutBoxDocument();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table> 
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="oaInfoOutBoxDocumentTable"></table>
			</div>
		</div>
	</div>
</body>
