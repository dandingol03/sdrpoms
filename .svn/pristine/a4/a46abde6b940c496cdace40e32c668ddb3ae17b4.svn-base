<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<body>
	<script type="text/javascript">
	function addMobileMenuResourceRelation(){
		var r = $('#addMobileMenuResourceRelationForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("mobile/menu/addMenuResourceRelate",$("#addMobileMenuResourceRelationForm").serializeArray(),function(data){
			$('#mobileMenuManageResourceTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
			closeWindow();
		});
	}
	</script>
 	<!-- 验证使用jquery-validation -->
	<form id="addMobileMenuResourceRelationForm" method="post" class="operationPage">
		<table>
			<tr>
				<td width="25%" align="right">资源名称：</td>
				<td width="75%" align="left">
					<input name="resourceName" style="height:20px;font-size:12px;width:90%;" 
					class="easyui-validatebox textbox" data-options="required:true" >
				</td>
			</tr>
			<tr style="display:none;">
				<td colspan="2">
					<input type="hidden" name="resourceType" value="b"/>
				</td>
			</tr>
			<tr style="display:none;">
				<td colspan="2">
					<input type="hidden" name="menuId" />
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源URL：</td>
				<td width="75%" align="left">
					<input name="resourceString" class="easyui-validatebox textbox" 
					style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源描述：</td>
				<td width="75%" align="left">
					<textarea name="resourceDesc" 
					class="easyui-validatebox textbox" cols="27" rows="2"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">是否可用：</td>
				<td width="75%" align="left">
					<input type="radio" name="enable" value="1" checked/>是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();"	class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addMobileMenuResourceRelation();" class="easyui-linkbutton" iconCls="icon-save" >保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
