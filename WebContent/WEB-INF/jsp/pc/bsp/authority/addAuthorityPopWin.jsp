<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<body>
    <script type="text/javascript">
	//添加权限
	function addAuthority(){
		var r = $('#authorityAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("authority/addAuthority",$("#authorityAddForm").serializeArray(),function(data){
			$('#authorityTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
			closeWindow();
		});
	}
	</script>
  	<!-- 验证使用jquery-validation -->
	<form id="authorityAddForm" method="post" class="operationPage">
		<table>
			<tr>
				<td width="30%" align="right">权限名称：</td>
				<td width="70%" align="left">
					<input name="authorityName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" >
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">权限描述：</td>
				<td width="70%" align="left">
					<textarea class="easyui-validatebox textbox" name="authorityDesc" cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否可用：</td>
				<td width="70%" align="left">
					<input type="radio" name="enable" value="1" checked/>是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否系统权限：</td>
				<td width="70%" align="left">
					<input type="radio" name="isSys" value="1" checked/>是
					<input type="radio" name="isSys" value="0" />否
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addAuthority();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
