<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/main.css">
</head>
<body>
	<script type="text/javascript">
	//添加资源
	function addResource(){
		if(!$('#resourceAddForm').form('validate')) {
			return false;
		}
		$.post("resource/addResource",$("#resourceAddForm").serializeArray(),function(data){
			$('#resourceTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
			closeWindow();
		});
	}
	</script>
 	
	<form id="resourceAddForm" method="post" class="operationPage">
		<table>
			<tr >
				<td width="25%" align="right">资源名称：</td>
				<td width="75%" align="left">
					<input name="resourceName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr style="display:none;">
				<td colspan="2">
					<input type="hidden" name="resourceType" value="b"/>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源URL：</td>
				<td width="75%" align="left">
					<input class="easyui-validatebox textbox" name="resourceString" 
					style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源描述：</td>
				<td width="75%" align="left">
					<textarea class="easyui-validatebox textbox"  name="resourceDesc" cols="27" rows="2" ></textarea>
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
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addResource();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
