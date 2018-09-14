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
			$('#oaInfoDelectedDocumentTable').datagrid({
				title:'回收站', 						           //标题
				method:'post',
				iconCls:'icon-tip', 						   //图标
				singleSelect:false, 						   //多选
				height:366, 								   //高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"oaInfo/deletedDocumentQueryList",	 		//数据来源
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
						{field:'hairUnit',title:'发件单位',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.hairUnit;}                                
						},
						{field:'sendTime',title:'发件日期',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.sendTime;}                                
						},
						{field:'isApprovedOainfo',title:'状态',width:70,sortable:false,
							formatter:function(value,row,index){
							 if(row.isApprovedOainfo == '2'){
									return '收件';
									}else if(row.isApprovedOainfo == '3'){
										return '发件';
										}else if(row.isApprovedOainfo == '4'){
											return '草稿';}
							}
						},
						/* {field:'',title:'操作',width:70,sortable:false,
							formatter:function(value,row,index){return row.}
						}, */

						]],
				toolbar:[
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#oaInfoDelectedDocumentTable').datagrid('clearSelections');}
			});
			
		});
				
	
		//表格查询
		function searchOaInfoDelectedDocument(){
			var params = $('#oaInfoDelectedDocumentTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#oaInfoDelectedDocumentQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#oaInfoDelectedDocumentTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearDelectedOaInfoDocument(){
			$('#oaInfoDelectedDocumentQuForm .easyui-combobox').combobox('clear');
			$('#oaInfoDelectedDocumentQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="oaInfoDelectedDocumentQuForm" class="fw fh">
					 <table  class="fw fh">
						<tr>
							<td width="5%" align="right">标题：</td>
							<td width="10%" align="left">
								<input name="title" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchOaInfoDelectedDocument();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearDelectedOaInfoDocument();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table> 
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="oaInfoDelectedDocumentTable"></table>
			</div>
		</div>
	</div>
</body>
