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
				var jqueryObj=$("#userOrg");
		 		makeOrgTree(jqueryObj);
			});
			
			//加载datagride
			$('#userTable').datagrid({
				title:'用户列表', 				//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:false,				//可折叠
				url:"user/queryList", 			//数据来源
				sortName: 'userId',				//排序的列
				sortOrder: 'asc', 				//倒序
				remoteSort: true, 				//服务器端排序
				idField:'userId', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'userAccount',title:'用户账号',width:20,sortable:true,                              
							formatter:function(value,row,index){return row.userAccount;} //需要formatter一下才能显示正确的数据                                
						},
						{field:'userPassword',title:'密码',width:20,sortable:false,hidden:true,	//密码隐藏
							formatter:function(value,row,index){return row.userPassword;}                          
						},
						{field:'userName',title:'用户姓名',width:20,sortable:true,                              
							formatter:function(value,row,index){return row.userName;}                          
						},
						{field:'userGender',title:'用户性别',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.userGender == '1'?'男':'女';}
						},
						{field:'userBirthday',title:'出生日期',width:30,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.userBirthday;}
						},
						{field:'userOrg',title:'机构代码',width:30,sortable:false,hidden:true, //机构代码隐藏
							formatter:function(value,row,index){return row.userOrg;}
						},
						{field:'orgId',title:'机构ID',width:30,sortable:false,hidden:true, //机构描述id隐藏
							formatter:function(value,row,index){return row.orgId;}
						},
						{field:'userDepartment',title:'所属部门ID',width:20,sortable:true, hidden:true,                             
							formatter:function(value,row,index){return row.userDepartment;}                          
						},
						{field:'userDepartmentName',title:'所属部门',width:20,sortable:true, hidden:false,                             
							formatter:function(value,row,index){return row.userDepartmentName;}                          
						},
						{field:'orgName',title:'所属机构',width:20,sortable:true,
							formatter:function(value,row,index){return row.orgName;}
						},
						{field:'userDuty',title:'职务',width:20,sortable:false,
							formatter:function(value,row,index){return row.userDuty;}
						},
						{field:'userTelephone',title:'联系电话',width:20,sortable:false,
							formatter:function(value,row,index){return row.userTelephone;}
						},
						{field:'mail',title:'邮件地址',width:30,sortable:false,
							formatter:function(value,row,index){return row.mail;}
						},
						{field:'qqWeixin',title:'QQ或微信',width:20,sortable:false,
							formatter:function(value,row,index){return row.qqWeixin;}
						},
						{field:'userDesc',title:'用户描述',width:20,sortable:false,
							formatter:function(value,row,index){return row.userDesc;}
						},
						{field:'enable',title:'用户状态',width:10,sortable:false,
							formatter:function(value,row,index){return row.enable == '0'?'禁用':'正常';}
						},
						{field:'isSys',title:'是否系统用户',width:10,sortable:false,
							formatter:function(value,row,index){return row.isSys == '0'?'否':'是';}
						},
						{field:'lng',title:'经度',width:20,sortable:false,
							formatter:function(value,row,index){return row.lng;}
						},
						{field:'lat',title:'纬度',width:20,sortable:false,
							formatter:function(value,row,index){return row.lat;}
						}
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addUserRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateUserRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteUserRow();}
						},'-',
						{text:'角色管理', iconCls:'icon-man',
							handler:function(){updateUserRoleRow();}
						},'-',
						{text:'重置密码', iconCls:'icon-edit',
							handler:function(){changeUserPasswordRow();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#userTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addUserRow(){
			showWindow({
				title:'增加用户信息',
				href:'user/addPopWin',
				width:700,
				height:440,
				onLoad: function(){$('#userAddForm').form('reset');}
			});
		}
				
		//更新    
		function updateUserRow(){
			var rows = $('#userTable').datagrid('getSelections');
			//alert(rows[0].userId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的用户",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一位用户进行更新",'info');
				return;
			}
			showWindow({
				title:'更新用户信息',
				href:'user/updatePopWin',
				width:700,
				height:440,
				onLoad: function(){
					$("#userUpForm").form('load', rows[0]);
					$("#userUpForm").form('load', {userOrg:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		//删除
		function deleteUserRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#userTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?userId="+n.uid;
						} else {
							ps += "&userId="+n.uid;
						}	
					});
					$.post('user/delUsers'+ps,function(data){
						$('#userTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//编辑用户角色
		function updateUserRoleRow(){
			var rows = $('#userTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要编辑角色的用户",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一位用户进行编辑",'info');
				return;
			}
			showWindow({
				title:'编辑用户角色',
				href:'user/updateRolePopWin?userId='+rows[0].userId,
				width:1000,
				height:500,
				onLoad: function(){
					$("#userRoleForm").form('load', rows[0]);
				}
			});
		}
		
		//重置用户密码
		function changeUserPasswordRow(){
			var rows = $('#userTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要重置密码的用户",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一位用户进行重置",'info');
				return;
			}
			showWindow({
				title:'重置密码',
				href:'user/updateUserPwd',
				width:350,
				height:200,
				onLoad: function(){
					$('#pwdUserUpForm').form('load', rows[0]);
					}
			});
		}
		
		//表格查询
		function searchUser(){
			var params = $('#userTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#userQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#userTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearUserForm(){
			$('#userQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:15%;">
			<div class="fh pl10 pr10">
				<form id="userQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="8%" align="right">账号：</td>
							<td width="17%" align="left"><input name="userAccount" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="right">姓名：</td>
							<td width="12%" align="left"><input name="userName" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="right">性别：</td>
							<td width="12%" align="left">
								<select class="easyui-combobox" name="userGender" style="width:90%;" data-options="editable:false">
				        			<option value="">请选择</option>
				        			<option value="1">男</option>
				        			<option value="2">女</option>
				    			</select>
							</td>
							<td width="35%" align="left">
								<a onclick="clearUserForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>                        
						</tr>
						<tr>
							<td align="right">所属机构：</td>
							<td align="left">
								<input id="userOrg" type="text" name="userOrg" class="easyui-textbox" style="width:90%;">
							</td>
							<td align="right">用户状态：</td>
							<td align="left">
								<select class="easyui-combobox" name="enable" style="width:90%;" data-options="editable:false">
									<option value="">全部</option>
				        			<option value="1">正常</option>
				        			<option value="0">禁用</option>
				    			</select>
							</td>
							<td align="right">出生日期：</td>
							<td align="left"><input name="userBirthday" class="Wdate" type="text" style="width:90%;" 
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
							<td align="left">
								<a  onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:80%;">
			<div class="fh pl5 pr10">
				<table id="userTable"></table>
			</div>
		</div>
	</div>
</body>
