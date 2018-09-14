<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
		    $("input[name=userGender][value=${userGender}]").attr("checked",true);
		});
		
		//修改个人信息
		function updateMsg(){
			var result = $.ajax({
				url: '${ctx}/user/updateUserMsg',
	            data: $("#msgUpForm").serializeArray(),  
	            type: 'post',  
	            dataType: 'json',  
	            async: false,  
	            cache: false
				}).responseText;
			if(result == 1){
				$.messager.alert('提示',"用户修改成功",'info');
			}else{
				$.messager.alert('提示',"用户修改失败",'info');
			}
		}
		
	</script>
	
	<form id="msgUpForm" class="operationPage">
		<input name="userAccount" type="hidden" value="${userAccount}">
		<table>
			<tr>
				<td width="30%" align="right">姓名：</td>
				<td width="70%" align="left">
					<input name="userName" value="${row.userName}" class="easyui-validatebox textbox"  
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">性别：</td>
				<td width="70%" align="left">
					<input type="radio" name="userGender" value="1" />男
					<input type="radio" name="userGender" value="2" />女
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">出生日期：</td>
				<td width="70%" align="left">
					<input name="userBirthday" class="Wdate" type="text" 
					style="width:90%;" value="${userBirthday}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">联系电话：</td>
				<td width="70%" align="left">
					<input name="userTelephone" value="${row.userTelephone}" class="easyui-validatebox textbox"  
					data-options="required:true,validType:['mobileAndTel']" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">邮件地址：</td>
				<td width="70%" align="left">
					<input name="mail" value="${row.mail}" class="easyui-validatebox textbox"  
					data-options="required:true,validType:['email']" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">QQ或微信：</td>
				<td width="70%" align="left">
					<input name="qqWeixin" value="${row.qqWeixin}" class="easyui-validatebox textbox"  
					data-options="required:true,validType:['email']" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="30%" align="right">备注：</td>
				<td width="70%" align="left">
					<textarea style="font-size:12px;" class="easyui-validatebox textbox"  cols="30" rows="5">${row.userDesc}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateMsg();" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
				</td>
			</tr>
		</table>
	</form>

</body>
