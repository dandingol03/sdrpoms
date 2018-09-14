<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
	<script type="text/javascript">
		
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		jQuery(function($){
			
			//加载datagride
			$('#authorityTable').datagrid({
				title:'权限列表', 				//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:false,				//可折叠
				url:"authority/queryList", 		//数据来源
				sortName: 'authorityId',		//排序的列
				sortOrder: 'desc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'authorityId', 			//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'authorityName',title:'权限名称',width:20,sortable:true,
							formatter:function(value,row,index){return row.authorityName;}
						},
						{field:'authorityDesc',title:'权限描述',width:20,sortable:false,hidden:false,
							formatter:function(value,row,index){return row.authorityDesc;}
						},
						{field:'enable',title:'是否可用',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
						},
						{field:'isSys',title:'是否系统权限',width:20,sortable:true,
							formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
						}
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addAuthorityRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateAuthorityRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteAuthorityRow();}
						},'-',
						{text:'注册菜单', iconCls:'icon-search',
							handler:function(){updateAuthResRow();}
						},'-',
						{text:'注册资源', iconCls:'icon-search',
							handler:function(){regAuthResRow();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#authorityTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addAuthorityRow(){
			showWindow({
				title:'增加权限信息',
				href:'authority/addPopWin',
				width:350,
				height:270,
				onLoad: function(){$('#authorityAddForm').form('reset');}
			});
		}
				
		//更新    
		function updateAuthorityRow(){
			var rows = $('#authorityTable').datagrid('getSelections');
			//alert(rows[0].userId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的权限",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个权限进行更新",'info');
				return;
			}
			showWindow({
				title:'更新权限信息',
				href:'authority/updatePopWin',
				width:350,
				height:270,
				onLoad: function(){
					$("#authorityUpForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteAuthorityRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#authorityTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?authorityId="+n.uid;
						} else {
							ps += "&authorityId="+n.uid;
						}	
					});
					$.post('authority/delAuthorities'+ps,function(data){
						$('#authorityTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//编辑权限菜单
		function updateAuthResRow(){
			var rows = $('#authorityTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要编辑菜单的权限",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个权限进行编辑",'info');
				return;
			}
			showWindow({
				title:'编辑权限菜单',
				href:'authority/updateResPopWin?authorityId='+rows[0].authorityId,
				width:350,
				height:490,
				onLoad: function(){
					$("#authResForm").form('load', rows[0]);
				}
			});
		}
			
		//注册权限资源
		function regAuthResRow(){
			var rows = $('#authorityTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要注册资源的权限",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个权限进行编辑",'info');
				return;
			}
			showWindow({
				title:'注册权限资源',
				href:'authority/regResPopWin?authorityId='+rows[0].authorityId,
				width:1000,
				height:450,
				onLoad: function(){
					$("#authResForm").form('load', rows[0]);
				}
			});
		}
		
		//表格查询
		function searchAuthority(){
			var params = $('#authorityTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#authorityQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#authorityTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearAuthorityForm(){
			$('#authorityQuForm').form('clear');
			//searchUser();
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="authorityQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="8%" align="right">权限名称：</td>
							<td width="20%" align="left"><input name="authorityName" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="center">
								<a onclick="searchAuthority();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
							<td width="64%" align="left">
								<a onclick="clearAuthorityForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:85%;">
			<div class="fh pl5 pr10">
				<table id="authorityTable"></table>
			</div>
		</div> 
	</div>
</body>