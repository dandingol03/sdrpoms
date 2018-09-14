<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
	//更新数据字典
	function updateDict(){
		var result = $.ajax({
			url: '${ctx}/datadictionary/isUpdateHadDictionary',
            data: $("#dictUpdateForm").serializeArray(),
            type: 'post',  
            dataType: 'json',  
            async: false,  
            cache: false
			}).responseText;
		if(result > 0){
			$.messager.alert('提示',"字典有重复，请重新输入",'info');
			return;
		}
		var r = $('#dictUpdateForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("datadictionary/updateDictionary",$("#dictUpdateForm").serializeArray(),function(data){
			closeWindow();
			$('#dataDictionaryTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="dictUpdateForm" method="post" class="operationPage">
		<input name="id" class="easyui-validatebox textbox" type='hidden'
			data-options="required:true" style="height:20px;font-size:12px;width:90%;">
		<input name="pid" class="easyui-validatebox textbox"  type='hidden'
			data-options="required:true" style="height:20px;font-size:12px;width:90%;">
		<table>
			<tr>
				<td width="25%" align="right">字典编码：</td>
				<td width="75%" align="left">
					<input name="dictCode" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">字典名称：</td>
				<td width="75%" align="left">
					<input name="dictName"  class="easyui-textbox" 
					data-options="required:true" style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">字典值：</td>
				<td width="75%" align="left">
					<input name="dictData"  class="easyui-textbox" 
					data-options="required:true" style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">字典描述：</td>
				<td width="75%" align="left">
					<textarea name="remark"   class="easyui-validatebox textbox"  cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr >
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateDict();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
