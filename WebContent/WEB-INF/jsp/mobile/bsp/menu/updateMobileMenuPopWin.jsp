<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script	>
	function updateMobileMenuManageTreeMenu(){
		var r = $('#mobileMenuManageUpdateMenuForm').form('validate');
		if(!r) {
			return false;
		}
		var menuName = $("#mobileMenuName").val();
		if(menuName == ""){
			$.messager.alert("提示","[菜单名称]不能为空","info");
			return false;
		}
		$('#mobileMenuManageUpdateMenuForm').form('submit',{
			url : 'file/fileUpLoad',
			onSubmit : function(pa) {},
			success : function(data) {
				if (typeof (data) != 'undefined') {
					if (data != "-1" && data != "") {
						var ps='';
						var tempFileIdList = data.split(",");
						if(tempFileIdList.length==1){
							$("#mobileMenuUpdateIcon").val(tempFileIdList[0])
						}
						
						$.post("mobile/menu/updateMenu",$("#mobileMenuManageUpdateMenuForm").serializeArray(),function(data){
							reloadMobileTree();
							$.messager.alert('提示',data.mes,'info');
							closeWindow();
						});
					}else{
						$.post("mobile/menu/updateMenu",$("#mobileMenuManageUpdateMenuForm").serializeArray(),function(data){
							reloadMobileTree();
							$.messager.alert('提示',data.mes,'info');
							closeWindow();
						});
					}
				} else {
					$.post("mobile/menu/updateMenu",$("#mobileMenuManageUpdateMenuForm").serializeArray(),function(data){
						reloadMobileTree();
						$.messager.alert('提示',data.mes,'info');
						closeWindow();
					});
					$.messager.alert('提示', "文件上传失败", 'info');
					closeWindow();
				}
			}
		});
	}
	
	</script>
	<form id="mobileMenuManageUpdateMenuForm"enctype="multipart/form-data" method="post" action="file/fileUpLoad" class="operationPage">
		<table style="font-size:12px;" width="100%">
			<tr>
				<td width="40%" align="right">菜单名称：</td>
				<td width="60%" align="left">
					<input id="mobileMenuId" name="mobileMenuId" type="hidden" >
					<input id="mobileMenuUpdateIcon" name="mobileMenuIcon" type="hidden" >
					<input id="mobileMenuName" name="mobileMenuName" class="easyui-validatebox textbox" 
					data-options="required:'true'" style="height:20px;" >
				</td>
			</tr>
			
			<tr>
				<td width="40%" align="right">菜单URL地址：</td>
				<td width="60%" align="left">
					<input id="mobileMenuUrl" maxlength="40" name="mobileMenuUrl" 
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
					<input id="mobileMenuDesc" name="mobileMenuDesc" 
					 class="easyui-validatebox textbox" data-options="validType:'number'" style="height:20px;">
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">打开方式：</td>
				<td width="60%" align="left">
					<input type="radio" name="mobileOpenType" id="mobileOpenType" value="a" />[ajax]&nbsp;&nbsp;
					<input type="radio" name="mobileOpenType" id="mobileOpenType" value="i" />[iframe]
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">菜单图片：</td>
				<td width="60%" align="left" >
					<input id="updateMobileMenuIconId" class="easyui-filebox" name="uploadFile" data-options="accept:'image/png',prompt:'请选择图标文件...',buttonText:'请选择文件',multiple:false" style='width:90%'/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateMobileMenuManageTreeMenu();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
