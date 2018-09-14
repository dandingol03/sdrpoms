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
			$('#oaInfoNewsTable').datagrid({
				title:'新闻信息列表', 							//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"oaInfo/oaInfoNewsQueryList",	 	        //数据来源
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
						{field:'title',title:'标题',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.title;}                                
						},
						{field:'abstracts',title:'摘要',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){
								var url="<a href=javascript:abstractsDetails()>"+row.abstracts.substring(0,10)+"</a>"
                                return url;
							}                                
						},
						{field:'userName',title:'发布者',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.userName;}                                
						},
						{field:'publishTime',title:'发布时间',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.publishTime;}                                
						},
						{field:'content',title:'内容',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){
								var url="<a href=javascript:contentDetails()>"+row.content.substring(0,10)+"</a>"
                                return url;
							}                                
						},
						{field:'showPhotos',title:'正文照片',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.showPhotos;}                                
						},
						{field:'attachment',title:'附件',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){
                                if(null==row.attachment||row.attachment==""){
                                    return row.attachmentName;
	                              }else{
	                                    return "<a target='_Blank' href='file/fileDownload?fileId="+row.attachment+"'>"+row.attachmentName+"</a>";
	                              }
							}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.remark;}                                
						}
						]],
				toolbar:[
						<sec:authorize url="/oaInfo/addOaInfoNewsPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addOaInfoNewsPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/oaInfo/updateOaInfoNewsPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateOaInfoNewsPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/oaInfo/deleteOaInfoNews">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteOaInfoNews();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#oaInfoNewsTable').datagrid('clearSelections');}
			});
			
		});
		// 摘要弹出窗
        function abstractsDetails(){
              var rows=$('#oaInfoNewsTable').datagrid("getSelections");
              if(rows.length==0){
                    $.messager.alert('提示',"请选择要查看的摘要");
                    return;
              }
               showWindow({
                    title:'摘要详情',
                    width:600,
                    height:250,
                    href: '',
                    content:"<p style='margin:10px;'>"+'标题：'+rows[0].title+"</p><p style='margin:10px;'>"+'内容：'+rows[0].abstracts+"</p>",
               })
        }
        // 内容弹出窗
        function contentDetails(){
              var rows=$('#oaInfoNewsTable').datagrid("getSelections");
              if(rows.length==0){
                    $.messager.alert('提示',"请选择要查看的内容");
                    return;
              }
               showWindow({
                    title:'内容详情',
                    width:600,
                    height:250,
                    href: '',
                    content:"<p style='margin:10px;'>"+'标题：'+rows[0].title+"</p><p style='margin:10px;'>"+'内容：'+rows[0].content+"</p>",
               })
        }	
		//新增
		function addOaInfoNewsPopWin(){
			showWindow({
				title:'增加新闻',
				href:'oaInfo/addOaInfoNewsPopWin',
				width:700,
				height:450,
				onLoad: function(){
					$('#oaInfoNewsAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateOaInfoNewsPopWin(){
			var rows = $('#oaInfoNewsTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的新闻",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条新闻进行更新",'info');
				return;
			}
			showWindow({
				title:'更新新闻',
				href:'oaInfo/updateOaInfoNewsPopWin?photosNames='+rows[0].photosNames+"&&attachmentName="+rows[0].attachmentName,
				width:700,
				height:450,
				onLoad: function(){
					$("#oaInfoNewsUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteOaInfoNews(){
			var rows = $('#oaInfoNewsTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的新闻",'info');
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
					$.post('oaInfo/deleteOaInfoNews'+ps,function(data){
						$('#oaInfoNewsTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		//表格查询
		function searchOaInfoNews(){
			var params = $('#oaInfoNewsTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#oaInfoNewsQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#oaInfoNewsTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearOaInfoNews(){
			$('#oaInfoNewsQuForm .easyui-combobox').combobox('clear');
			$('#oaInfoNewsQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="oaInfoNewsQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">标题：</td>
							<td width="10%" align="left">
								<input name="title" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchOaInfoNews();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearOaInfoNews();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="oaInfoNewsTable"></table>
			</div>
		</div>
		
		
	</div>
</body>
