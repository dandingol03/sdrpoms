<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
    <script type="text/javascript">
	//更新角色
	function updateSysParam(){
		
		var r = $('#sysParamUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("sysparam/updateSysParam", $("#sysParamUpForm").serializeArray(),function(data){
			closeWindow();
			$('#sysParamTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="sysParamUpForm" class="operationPage">
		<!-- 隐藏域 -->
		<input type="hidden" name="paramId">
		<table>
			<tr>
				<td width="25%" align="right">参数编码：</td>
				<td width="75%" align="left">
					<input name="paramCode"  class="easyui-validatebox textbox"  
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">参数名称：</td>
				<td width="75%" align="left">
					<input name="paramName"  class="easyui-validatebox textbox"  
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">参数值：</td>
				<td width="75%" align="left">
					<input name="paramValue"  class="easyui-validatebox textbox"  
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">参数状态：</td>
				<td width="75%" align="left">
					<input type="radio" name="paramStatus" value="1" />使用
					<input type="radio" name="paramStatus" value="0" />禁用
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateSysParam();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
