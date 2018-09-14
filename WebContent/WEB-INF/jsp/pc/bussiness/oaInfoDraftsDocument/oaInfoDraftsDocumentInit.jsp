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
			$('#oaInfoDraftsDocumentTable').datagrid({
				title:'草稿箱', 						            //标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"oaInfo/draftsDocumentQueryList",		    //数据来源
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
						{field:'sendTime',title:'发件日期',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.sendTime;}                                
						},
						{field:'file',title:'操作',width:120,sortable:false,
							formatter:function(value,row,index){
								var url="<a href=javascript:draftsDetailedInformation()>查看详细</a>";
								return url;
							}
						}, 
						]],
				toolbar:[
						<sec:authorize url="/oaInfo/updateDraftsDocumentPopWin">
						{  text:'编辑邮件', iconCls:'icon-remove',
							handler:function(){updateOaInfoDraftsDocumentPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/oaInfo/deleteDraftsDocument">
						{  text:'删除邮件', iconCls:'icon-remove',
							handler:function(){deleteOaInfoDraftsDocument();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#oaInfoDraftsDocumentTable').datagrid('clearSelections');}
			});
			
		});
		//查询详情信息
		function draftsDetailedInformation(){
			var rows = $('#oaInfoDraftsDocumentTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请不要选择的邮件",'info');
				return;
			}
			showWindow({
				title:'查询详情信息',
				href:'oaInfoDrafts/detailedInformation',
				width:900,
				height:600,
				onOpen: function(){
					 rowListTwo=rows;
				},
				onClose: function(){
					$('#oaInfoDraftsDocumentTable').datagrid('reload');  
				},
			});
		}
				
		//更新    
		function updateOaInfoDraftsDocumentPopWin(){
			var rows = $('#oaInfoDraftsDocumentTable').datagrid('getSelections');
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
				href:'oaInfo/updateDraftsDocumentPopWin?fileName='+rows[0].fileName,
				width:900,
				height:600,
				onLoad: function(){
					$("#oaInfoDraftsDocumentUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteOaInfoDraftsDocument(){
			var rows = $('#oaInfoDraftsDocumentTable').datagrid('getSelections');
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
					$.post('oaInfo/deleteDraftsDocument'+ps,function(data){
						$('#oaInfoDraftsDocumentTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchOaInfoDraftsDocument(){
			var params = $('#oaInfoDraftsDocumentTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#oaInfoDraftsDocumentQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#oaInfoDraftsDocumentTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearOaInfoDraftsDocument(){
			$('#oaInfoDraftsDocumentQuForm .easyui-combobox').combobox('clear');
			$('#oaInfoDraftsDocumentQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="oaInfoDraftsDocumentQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">标题：</td>
							<td width="10%" align="left">
								<input name="title" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchOaInfoDraftsDocument();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearOaInfoDraftsDocument();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="oaInfoDraftsDocumentTable"></table>
			</div>
		</div>
	</div>
</body>
