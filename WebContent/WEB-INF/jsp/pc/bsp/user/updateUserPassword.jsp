<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		//修改密码
		function updatePwd(){
			var r = $('#pwdUpForm').form('validate');
			if(!r) {
				return false;
			}
			var userAccount = $("#userAccount");
			var newPwd = $("#userPwd");
			var reNewPwd = $("#reUserPassword");
			if(newPwd.val()!=reNewPwd.val())
			{
				$.messager.alert('提示',"两次密码不一致！",'info');
				return false;
			}
			if(newPwd.val().length > 0){
				var result = $.ajax({
					url: '${ctx}/user/updateUserNewPwd',
	                data: 'newPassword='+newPwd.val()+"&userAccount="+userAccount.val(),  
	                type: 'post',  
	                dataType: 'json',  
	                async: false,  
	                cache: false
					}).responseText;
				if(result == 1){
					closeWindow();	
					$.messager.alert('提示',"密码修改成功",'info');
				}else{
					closeWindow();
					$.messager.alert('提示',"密码修改失败",'info');
				}
			}
		}
		
	</script>
	<form id="pwdUserUpForm"  class="operationPage">
		<input id="userAccount" name="userAccount" type='hidden' class="easyui-textbox" ></input>
		<table>
			<tr>
				<td width="30%" align="right">新密码：</td>
				<td width="70%" align="left">
					<input id="userPwd" name="userPwd" type="password" class="easyui-validatebox textbox"  
					 data-options="required:true,validType:['minLength[6]','NOCHS']" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">重复新密码：</td>
				<td width="70%" align="left">
					<input id="reUserPassword" name="reUserPassword" type="password" class="easyui-validatebox textbox"  
					data-options="required:true,validType:['equalTo[&quot#userPwd&quot]']" style="height:20px;font-size:12px;width:90%;"
					>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updatePwd();" class="easyui-linkbutton" iconCls="icon-save">修改</a>
				</td>
			</tr>
		</table>
	</form>
</body>
