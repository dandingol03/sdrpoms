<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updatePartolUserBlongTeam(){
			var r = $('#partolUserBlongTeamUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			var teamId = $("#teamInfoId").combobox('getValue'); 
			//更新巡防人基本信息
			$.post("baseInfo/updatePatrolUserBlongTeam?teamId="+teamId, $("#partolUserBlongTeamUpdateForm").serializeArray(),function(data){
				closeSecondWindow();
				$('#partolTeamUserRelationTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}
	</script>
    
	<form id="partolUserBlongTeamUpdateForm" method="post" class="operationPage">
		<input id="id" type="hidden" name="userId">
		<input id="id" type="hidden" name="teamInfoId">
		<table>
			<tr>
				<td width="15%" align="right">姓名：</td>
				<td width="35%" align="left">
					<input  name="userName" disabled="disabled" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" >
					
				</td>
				<td width="15%" align="right">所属队伍：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="teamInfoId"
						name="teamInfoId" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${teamInfo}" var="teamInfo">
							<option value="${teamInfo.id}">${teamInfo.name}</option>
						</c:forEach>
					</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeSecondWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updatePartolUserBlongTeam();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
