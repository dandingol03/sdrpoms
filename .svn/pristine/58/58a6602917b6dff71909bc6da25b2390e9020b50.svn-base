<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<!-- 巡防添加 -->
    <script type="text/javascript">
    jQuery(function($){
    	//加载机构树
		$(document).ready(function() {
			var jqueryObj=$("#orgId1");
	 		makeOrgTree(jqueryObj);
		});
	});
		//添加
		function addPatrolManagementTask(){
			//验证字段
			var r = $('#patrolManagementTaskAddForm').form('validate');
			if(!r) {
				return false;
			}
			
			$.post("baseInfo/addPatrolManagementTask",$("#patrolManagementTaskAddForm").serializeArray(),function(data){
				closeWindow();
				$('#patrolManagementTaskTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="patrolManagementTaskAddForm" method="post" class="operationPage">
		<table>
			<tr>
				<td width="15%" align="right">巡防队伍名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">负责人：</td>
				<td width="35%" align="left">
					<input name="charger" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>	</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input name="telephone" class="easyui-validatebox textbox" data-options="required:true,validType:['mobileAndTel','mobile']" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">所属机构：</td>
				<td width="35%" align="left">
					<input id="orgId1" type="text" name="orgId" class="easyui-textbox " 
					data-options="required:true" style="width:90%;">
				</td>
				</tr>
			<tr>
				<td colspan="4" align="center"><a onclick="closeWindow();"
					class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a onclick="addPatrolManagementTask();"
					class="easyui-linkbutton" iconCls="icon-save">保存</a></td>
			</tr>
		</table>
	</form>
</body>
