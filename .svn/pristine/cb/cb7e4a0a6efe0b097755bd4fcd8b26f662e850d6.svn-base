<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
	//更新权限
	function updateAuthority(){
		
		var r = $('#authorityUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("authority/updateAuthority", $("#authorityUpForm").serializeArray(),function(data){
			$.messager.alert('提示',data.mes,'info');
			$('#authorityTable').datagrid('reload');
			closeWindow();
		});
	}
	</script>
    
	<form id="authorityUpForm" method="post" class="operationPage">
		<!-- 隐藏域 -->
		<input type="hidden" name="authorityId">
		<table>
			<tr>
				<td width="30%" align="right">权限名称：</td>
				<td width="70%" align="left">
					<input name="authorityName" class="easyui-validatebox textbox" 
						style="height:20px;font-size:12px;width:90%;" data-options="required:true">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">权限描述：</td>
				<td width="70%" align="left">
					<textarea style="font-size:12px;" name="authorityDesc" class="easyui-validatebox textbox" cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否可用：</td>
				<td width="70%" align="left">
					<input type="radio" name="enable" value="1" />是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">是否系统权限：</td>
				<td width="70%" align="left">
					<input type="radio" name="isSys" value="1" />是
					<input type="radio" name="isSys" value="0" />否
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateAuthority();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
