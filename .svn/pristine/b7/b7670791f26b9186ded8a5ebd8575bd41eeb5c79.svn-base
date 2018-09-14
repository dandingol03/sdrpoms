<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
	<script type="text/javascript">
		
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		jQuery(function($){
			
			//加载datagride
			$('#roleTable').datagrid({
				title:'角色列表', 				//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:false,				//可折叠
				url:"role/queryList", 			//数据来源
				sortName: 'roleId',				//排序的列
				sortOrder: 'desc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'roleId', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'roleName',title:'角色名称',width:20,sortable:true,
							formatter:function(value,row,index){return row.roleName;}
						},
						{field:'roleDesc',title:'角色描述',width:20,sortable:false,
							formatter:function(value,row,index){return row.roleDesc;}
						},
						{field:'roleLevel',title:'角色级别',width:20,sortable:true,
							formatter:function(value,row,index){return row.roleLevel;}
						},
						{field:'enable',title:'是否可用',width:20,sortable:true,
							formatter:function(value,row,index){return row.enable == '0'?'否':'是';}                          
						},
						{field:'isSys',title:'是否系统角色',width:20,sortable:true,
							formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
						}
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addRoleRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateRoleRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteRoleRow();}
						},'-',
						{text:'权限管理', iconCls:'icon-tip',
							handler:function(){updateRoleAuthRow();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#roleTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addRoleRow(){
			showWindow({
				title:'增加角色信息',
				href:'role/addPopWin',
				width:350,
				height:320,
				onLoad: function(){$('#roleAddForm').form('reset');}
			});
		}
				
		//更新    
		function updateRoleRow(){
			var rows = $('#roleTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的角色",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个角色进行更新",'info');
				return;
			}
			showWindow({
				title:'更新角色信息',
				href:'role/updatePopWin',
				width:350,
				height:320,
				onLoad: function(){
					$("#roleUpForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteRoleRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#roleTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?roleId="+n.uid;
						} else {
							ps += "&roleId="+n.uid;
						}	
					});
					$.post('role/delRoles'+ps,function(data){
						$('#roleTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//编辑角色权限
		function updateRoleAuthRow(){
			var rows = $('#roleTable').datagrid('getSelections');
			//alert(rows[0].userId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要编辑权限的角色",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个角色进行编辑",'info');
				return;
			}
			showWindow({
				title:'编辑角色权限',
				href:'role/updateAuthPopWin?roleId='+rows[0].roleId,
				width:1000,
				height:480,
				onLoad: function(){
					$("#roleAuthForm").form('load', rows[0]);
				}
			});
		}
				
		//表格查询
		function searchRole(){
			var params = $('#roleTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#roleQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#roleTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearRoleForm(){
			$('#roleQuForm').form('clear');
			//searchUser();
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="roleQuForm" class="fw fh">
					<table class="fw fh">
						<tr>
							<td width="8%" align="left">角色名称：</td>
							<td width="20%" align="left"><input name="roleName" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="left">
								<a onclick="searchRole();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
							<td width="64%" align="left">
								<a onclick="clearRoleForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:85%;">
			<div class="fh pl5 pr10">
				<table id="roleTable"></table>
			</div>
		</div>
	</div>
</body>
