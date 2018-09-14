<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
  		jQuery(function($){
  			$(document).ready(function() {
  				var jqueryObj=$("#addOrgName");
		 		makeOrgTree(jqueryObj);
  			});
		});
	
	//添加机构
	function addOrg(){
		var orgTree = $('#addOrgName').combotree("tree");
		var orgSelected = orgTree.tree('getSelected');
		if(null== orgSelected){
			alert("所属机构不能为空！");
			return false;
		}
		var r = $('#orgAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("org/addOrg",$("#orgAddForm").serializeArray(),function(data){
			closeWindow();
			$('#orgTable').datagrid('reload');
			//机构增加后重新加载查询条件中的机构树
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="orgAddForm" method="post" class="operationPage">
		<table>
			<tr>
				<td width="25%" align="right">机构名称：</td>
				<td width="75%" align="left">
					<input name="orgName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right"><a style="color:red">*</a>上级机构：</td>
				<td width="75%" align="left">
					<input id="addOrgName" type="text" name="orgId" class="easyui-textbox" 
					data-options="required:true" style="width:90%;"><a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构代码：</td>
				<td width="75%" align="left">
					<input name="orgCode"  class="easyui-textbox" 
								style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构描述：</td>
				<td width="75%" align="left">
					<textarea name="orgDesc"   class="easyui-validatebox textbox"  cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构地址：</td>
				<td width="75%" align="left">
					<textarea name="orgAddress" class="easyui-validatebox textbox"  cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">是否可用：</td>
				<td width="75%" align="left">
					<input type="radio" name="enable" value="1" checked/>是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">预留1：</td>
				<td width="75%" align="left">
					<input name="orgReserve1" class="easyui-textbox" style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">预留2：</td>
				<td width="75%" align="left">
					<input name="orgReserve2" class="easyui-textbox" style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr >
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addOrg();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
