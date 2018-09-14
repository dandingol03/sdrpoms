<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
	<head></head>
    
    <body>

    <script type="text/javascript">
    //加载机构树
    jQuery(function($){
  		$(document).ready(function() {
  			var jqueryObj=$("#viedoMeetingOrgId");
   		    makeOrgTree(jqueryObj);
  		});
    });
	//添加
	function updateVideoMeetingInfo(){
		var r = $('#videoMeetingInfoUpdateForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("videoMeeting/updateVideoMeetingInfo",$("#videoMeetingInfoUpdateForm").serializeArray(),function(data){
			closeWindow();
			$('#videoMeetingInfoTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="videoMeetingInfoUpdateForm"  method="post" class="operationPage">
		<table>
			<input id="id" type="hidden" name="id">
			<tr>
				<td width="20%" align="right">所属机构：</td>
				<td width="30%" align="left">
					<input id="viedoMeetingOrgId" name="orgId" style="width:150px;height:20px;" class="easyui-validatebox textbox" ><a style="color:red">*</a>
				</td>
				<td width="20%" align="right">摄像头IP地址：</td>
				<td width="30%" align="left">
					<input name="videoIp" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">摄像头用户名：</td>
				<td width="30%" align="left">
					<input name="videoUser" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
				</td>
				<td width="20%" align="right">摄像头密码：</td>
				<td width="30%" align="left">
                    <input name="videoPass" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
                    
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">摄像头端口号：</td>
				<td width="30%" align="left">
                    <input name="videoPort" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
				</td>
				<td width="20%" align="right">摄像头通道号：</td>
				<td width="30%" align="left">
					<input name="videoChannel" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,256]">
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">码流类型：</td>
				<td align="left">
						<select class="easyui-combobox" name="videoStream" style="width:150px;" data-options="editable:false">
							<option value="0">主码流</option>
							<option value="1">辅码流1</option>
							<option value="2">辅码流2</option>
							<option value="3">辅码流3</option>
		    			</select>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td width="20%" align="right">解码器IP地址：</td>
				<td width="30%" align="left">
					<input name="decodeIp" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
				</td>
				<td width="20%" align="right">解码器用户名：</td>
				<td width="30%" align="left">
					<input name="decodeUser" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">解码器密码：</td>
				<td width="30%" align="left">
                    <input name="decodePass" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
                    
				</td>
				<td width="20%" align="right">解码器端口号：</td>
				<td width="30%" align="left">
                    <input name="decodePort" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,64]">
				</td>
			</tr>
			<tr>

				<td width="20%" align="right">解码器通道号：</td>
				<td width="30%" align="left">
					<input name="decodeChannel" style="width:150px;height:20px;" class="easyui-validatebox textbox" 
					required="true" validType="chkAccount[1,256]">
				</td>
				<td width="20%" align="right"></td>
				<td align="left">
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">备注：</td>
				<td align="left" colspan="3">
					<input name="remarks" style="height:70px;width:400px;" class="easyui-textbox" data-options="multiline:true" />
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a href="#" id="btn-back" onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-back">返回</a>
					<a href="#" id="btn-add" onclick="updateVideoMeetingInfo();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
	
	</body>
	
</html> 