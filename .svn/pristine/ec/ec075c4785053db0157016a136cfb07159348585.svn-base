<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
  		jQuery(function($){
  			$(document).ready(function() {
  				var jqueryObj=$("#addDictCode");
  				makeDictTree(jqueryObj);
  			});
		});
	
	//添加数据字典
	function addDict(){
		var dictTree = $('#addDictCode').combotree("tree");
		var dictSelected = dictTree.tree('getSelected');
		if(null== dictSelected){
			$.messager.alert("提示","所属字典上级不能为空！","info");
			return false;
		}
		var result = $.ajax({
			url: '${ctx}/datadictionary/isHadDictionary',
            data: $("#dictAddForm").serializeArray(),
            type: 'post',  
            dataType: 'json',  
            async: false,  
            cache: false
			}).responseText;
		if(result > 0){
			$.messager.alert('提示',"字典有重复，请重新输入",'info');
			return;
		}
		var r = $('#dictAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("datadictionary/addDictionary",$("#dictAddForm").serializeArray(),function(data){
			closeWindow();
			$('#dataDictionaryTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="dictAddForm" method="post" class="operationPage">
		<table>
			<tr>
				<td width="25%" align="right">字典编码：</td>
				<td width="75%" align="left">
					<input name="dictCode" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right"><a style="color:red">*</a>上级编码：</td>
				<td width="75%" align="left">
					<input id="addDictCode" type="text" name="pid" class="easyui-textbox" 
					data-options="required:true" style="width:90%;"><a style="color:red">*</a>
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
					<a onclick="addDict();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
