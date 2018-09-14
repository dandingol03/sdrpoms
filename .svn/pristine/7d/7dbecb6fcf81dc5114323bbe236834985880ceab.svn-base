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
			$('#oaInfoDocumentTable').datagrid({
				
				title:'收件箱', 						            //标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 						    //多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"oaInfo/documentQueryList",	 		        //数据来源
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
						{field:'receiveUserIdName',title:'发件人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.receiveUserIdName;}                                
						},
						{field:'hairUnit',title:'发件单位',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.hairUnit;}                                
						},
						{field:'sendTime',title:'收件日期',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.sendTime;}                                
						},
						{field:'isApproved',title:'状态',width:70,sortable:false,
							formatter:function(value,row,index){return row.isApproved == '0'?'已阅':'未阅读';}
						},
						{field:'file',title:'操作',width:120,sortable:false,
							formatter:function(value,row,index){
								var url="<a href=javascript:oaInfoDocumentSelect()>查看详细</a>";
								return url;
							}
						}, 
						]],
				toolbar:[
						<sec:authorize url="/oaInfo/deleteDocument">
						{  text:'删除邮件', iconCls:'icon-remove',
							handler:function(){deleteOaInfoDocument();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#oaInfoDocumentTable').datagrid('clearSelections');}
			});
			
		});
		//查询详情信息
		function oaInfoDocumentSelect(){
			var rows = $('#oaInfoDocumentTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请不要选择的邮件",'info');
				return;
			}
			showWindow({
				title:'查询详情信息',
				href:'oaInfoDocument/detailedInformation',
				width:900,
				height:600,
				onLoad: function(){
					
					var row=rows[0];
					if(row){
						row.isApproved="0";
						console.log(row);
						// console.log(JSON.stringify(rows));
						$.post("oaInfo/updateDraftsDocument",row,function(data){
						}); 
					}
					
				},
				onOpen: function(){
					 rowListThree=rows;
				},
				onClose: function(){
					$('#oaInfoDocumentTable').datagrid('reload');  
				},
			});
		}
				
		//新增
		function addOaInfoDocumentPopWin(){
			showWindow({
				title:'增加邮件基本信息',
				href:'oaInfo/addDocumentPopWin',
				width:600,
				height:430,
				onLoad: function(){
					$('#oaInfoDocumentAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateOaInfoDocumentPopWin(){
			var rows = $('#oaInfoDocumentTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的邮件",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条邮件进行更新",'info');
				return;
			}
			showWindow({
				title:'更新邮件基本信息',
				href:'oaInfo/updateDocumentPopWin',
				width:600,
				height:430,
				onLoad: function(){
					$("#oaInfoDocumentUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteOaInfoDocument(){
			var rows = $('#oaInfoDocumentTable').datagrid('getSelections');
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
					$.post('oaInfo/deleteDocument'+ps,function(data){
						$('#oaInfoDocumentTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchOaInfoDocument(){
			var params = $('#oaInfoDocumentTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#oaInfoDocumentQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#oaInfoDocumentTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearOaInfoDocument(){
			$('#oaInfoDocumentQuForm .easyui-combobox').combobox('clear');
			$('#oaInfoDocumentQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="oaInfoDocumentQuForm" class="fw fh">
					 <table  class="fw fh">
						<tr>
							<td width="5%" align="right">标题：</td>
							<td width="10%" align="left">
								<input name="title" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchOaInfoDocument();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearOaInfoDocument();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table> 
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="oaInfoDocumentTable"></table>
			</div>
		</div>
	</div>
</body>
