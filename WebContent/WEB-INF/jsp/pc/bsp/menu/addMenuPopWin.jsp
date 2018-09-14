<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script	>
	function addMenuManageTreeMenu(){
		var r = $('#menuManageAddMenuForm').form('validate');
		if(!r) {
			return false;
		}
		var menuName = $("#menuName").val();
		if(menuName == ""){
			$.messager.alert("提示","[菜单名称]不能为空","info");
			return false;
		}
		
		$.post("menu/addMenu",$("#menuManageAddMenuForm").serializeArray(),function(data){
			reloadTree();			//重新加载树
			$.messager.alert('提示',data.mes,'info');
			closeWindow();
		});

	}
	
	</script>
   	<form id="menuManageAddMenuForm" method="post" style="margin:0;text-align: center;">
		<table width="100%" style="font-size:12px;">
			<tr>
				<td width="40%" align="right">菜单名称：</td>
				<td width="60%" align="left">
					<input id="menuPid" name="menuPid" type="hidden" >
					<input id="menuName" name="menuName" 
					class="easyui-validatebox textbox" 
					data-options="required:'true'" style="height:20px;">
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">菜单URL地址：</td>
				<td width="60%" align="left">
					<input id="menuUrl" maxlength="40" name="menuUrl"  
					class="easyui-validatebox textbox" style="height:20px;">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<a style="color:red; text-decoration:none;">如果是目录菜单，则URL为空！</a>
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">菜单排序：</td>
				<td width="60%" align="left">
					<input id="menuDesc" name="menuDesc" 
					class="easyui-validatebox textbox" data-options="validType:'number'" style="height:20px;">
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">打开方式：</td>
				<td width="60%" align="left">
					<input type="radio" name="openType" id="openType" value="a" checked/>[ajax]&nbsp;&nbsp;
					<input type="radio" name="openType" id="openType" value="i" />[iframe]
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addMenuManageTreeMenu();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>