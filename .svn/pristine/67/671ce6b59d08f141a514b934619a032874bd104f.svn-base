<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    
    <script type="text/javascript">

    jQuery(function($){
  		//加载机构树
  		$(document).ready(function() {
  			var jqueryObj=$("#roleLevel");
	 		makeOrgTree(jqueryObj);
  		});
  		
    });

	//更新角色
	function updateRole(){
		var r = $('#roleUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("role/updateRole", $("#roleUpForm").serializeArray(),function(data){
			closeWindow();
			$('#roleTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="roleUpForm" method="post" class="operationPage">
		<!-- 隐藏域 -->
		<input type="hidden" name="roleId">
		<table>
			<tr>
				<td width="30%" align="right">角色名称：</td>
				<td width="70%" align="left">
					<input name="roleName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">角色级别：</td>
				<td width="70%" align="left">
					<input id="roleLevel" type="text" name="roleLevel" 
					style="height:20px;font-size:12px;width:90%;"><a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">角色描述：</td>
				<td width="70%" align="left">
					<textarea class="easyui-validatebox textbox" name="roleDesc" cols="27" rows="4"></textarea>
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
				<td width="30%" align="right">是否系统角色：</td>
				<td width="70%" align="left">
					<input type="radio" name="isSys" value="1" />是
					<input type="radio" name="isSys" value="0" />否
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateRole();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>

</body>