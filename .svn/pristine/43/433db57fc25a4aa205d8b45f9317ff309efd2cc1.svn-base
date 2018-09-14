<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<body>

	<script type="text/javascript">
	
	//更新资源
	function updateResource(){
		var r = $('#resourceUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("resource/updateResource", $("#resourceUpForm").serializeArray(),function(data){
			closeWindow();
			$('#resourceTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
   
	<form id="resourceUpForm" method="post" class="operationPage">
		<!-- 隐藏域 -->
		<input type="hidden" name="resourceId">
		<table>
			<tr>
				<td width="25%" align="right">资源名称：</td>
				<td width="75%" align="left">
					<input name="resourceName" class="easyui-validatebox textbox"  
					style="height:20px;font-size:12px;width:90%;" data-options="required:true" >
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
					<input name="resourceString" class="easyui-validatebox textbox" 
					style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">资源描述：</td>
				<td width="75%" align="left">
					<textarea name="resourceDesc"  class="easyui-validatebox textbox" cols="25" rows="2"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">是否可用：</td>
				<td width="75%" align="left">
					<input type="radio" name="enable" value="1" />是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateResource();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
	
