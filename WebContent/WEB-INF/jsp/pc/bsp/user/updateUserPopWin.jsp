<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
    
    <script type="text/javascript">
  	jQuery(function($){
  	  	
  		//加载机构树
  		$(document).ready(function() {
  			var jqueryObj=$("#upUserOrgName");
	 		makeOrgTree(jqueryObj);
  		});

		//校验密码
		$.extend($.fn.validatebox.defaults.rules, {
		    chkPwd: {
		        validator: function(value,param){
	        		if(value.length < param[0]){
	        			$.fn.validatebox.defaults.rules.chkPwd.message = '密码不能少于'+param[0]+'位';
		        	}else{
		        		if(RegExp("[\\u4E00-\\u9FFF]+", "g").test(value)){
							$.fn.validatebox.defaults.rules.chkPwd.message = '密码不能出现汉字';
							return false;
						}else{
							if(value.length > param[1]){
								$.fn.validatebox.defaults.rules.chkPwd.message = '密码长度不能超过'+param[1]+'位';
							}else{
								return true;
							}
						}
			        }
		        }
		    }
		});
		
		//判断密码输入是否一致
		$.extend($.fn.validatebox.defaults.rules, {
		    equalTo: {
		        validator: function(value,param){
		            return value == $(param[0]).val();
		        },
		        message: '密码不一致'
		    }
		});
		
	});

	//更新用户
	function updateUser(){
		if(null==$("#userDepartmentUpdate").combobox("getValues")||$("#userDepartmentUpdate").combobox("getValues")==""){
			$.messager.alert('提示',"请选择所属部门",'info');
			return;
		}
		//判断是否需要更新密码
		var oldpwd = $("#oldpwd")[0].value;
		var newpwd = $("#newpwd")[0].value;
		var uppwd = true;
		if(oldpwd == newpwd){
			uppwd = false;
		}
		//校验其他输入项
		var r = $('#userUpForm').form('validate');
		if(!r) {
			return false;
		}
		
		//更新
		$.post("user/updateUser?uppwd="+uppwd, $("#userUpForm").serializeArray(),function(data){
			closeWindow();
			$('#userTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="userUpForm" method="post" class="operationPage">
		<!-- 隐藏域，存放用户主键 -->
		<input type="hidden" name="userId">
		<input id="oldpwd" type="hidden" name="userPassword">
		<input type="hidden" name="userAccount" >
		<table>
			<tr>
				<td width="15%" align="right">姓名：</td>
				<td width="35%" align="left">
					<input name="userName" class="easyui-validatebox textbox"
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right"></td>
				<td width="35%" align="left">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">密码：</td>
				<td width="35%" align="left">
					<input id="newpwd" name="userPassword" type="password" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">密码重复：</td>
				<td width="35%" align="left">
					<input id="reUserPassword" name="reUserPassword" type="password" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="equalTo['#newpwd']">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">性别：</td>
				<td width="35%" align="left">
					<input type="radio" name="userGender" value="1" />男
					<input type="radio" name="userGender" value="2" />女
				</td>
				<td width="15%" align="right">出生日期：</td>
				<td width="35%" align="left">
					<input name="userBirthday" class="Wdate" type="text" 
					style="width:90%;" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">所属机构：</td>
				<td width="35%" align="left">
					<input id="upUserOrgName" type="text" name="userOrg" class="easyui-textbox" 
					data-options="required:true" style="width:90%;"><a style="color:red">*</a>
				</td>
				<td width="15%" align="right">所属部门：</td>
				<td width="35%" align="left">
				 	<select class="easyui-combobox" id='userDepartmentUpdate' name="userDepartment" id="userDepartmentUpdate"
				  	 style="width:90%;" data-options="editable:false,required:true">							
				        <option value="">请选择</option>
				        <c:forEach items="${departmentList}" var="departmentTemp">
					    	<option value="${departmentTemp.dictData}">${departmentTemp.dictName}</option>
				        </c:forEach>
				   	</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">职务：</td>
				<td width="35%" align="left">
					<input name="userDuty" class="easyui-validatebox textbox" 
					style="height:20px;font-size:12px;width:90%;" maxlength="16">
				</td>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input name="userTelephone" class="easyui-validatebox textbox" 
					style="height:20px;font-size:12px;width:90%;" maxlength="16" validType="mobileAndTel">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">邮件地址：</td>
				<td width="35%" align="left">
					<input name="mail"  class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;" maxlength="32" validType="email">
				</td>
				<td width="15%" align="right">QQ或微信：</td>
				<td width="35%" align="left">
					<input name="qqWeixin"  class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;" maxlength="16">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input name="lng"  class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;" maxlength="32" validType="number">
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input name="lat"  class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;" maxlength="32" validType="number">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">用户状态：</td>
				<td width="35%" align="left">
					<input type="radio" name="enable" value="1" />正常&nbsp;&nbsp;
					<input type="radio" name="enable" value="0" />禁用
				</td>
				<td width="15%" align="right">是否系统用户：</td>
				<td width="35%" align="left">
					<input type="radio" name="isSys" value="1" />是&nbsp;&nbsp;
					<input type="radio" name="isSys" value="0" />否
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">用户描述：</td>
				<td width="85%" colspan="3"  align="left">
					<textarea class="easyui-validatebox textbox" name="userDesc" cols="64" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
