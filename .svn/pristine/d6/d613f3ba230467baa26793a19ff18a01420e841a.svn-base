<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
	    jQuery(function($){
	  	//校验账号
		$.extend($.fn.validatebox.defaults.rules, {
		    chkAccount: {
		        validator: function(value,param){
	        		if(value.length < param[0]){
	        			$.fn.validatebox.defaults.rules.chkAccount.message = '账号不能少于'+param[0]+'位';
		        	}else{
		        		if(!/^[\w]+$/.test(value)){
							$.fn.validatebox.defaults.rules.chkAccount.message = '账号只能是英文字母、数字及下划线的组合';
							return false;
						}else{
							if(value.length > param[1]){
								$.fn.validatebox.defaults.rules.chkAccount.message = '账号长度不能超过'+param[1]+'位';
							}else{
								return true;
							}
						}
			        }
		        }
		    }
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
	});
		//判断用户账号是否存在
		function chkExist(){
			var uAcc = $("#userAccount");
			if(uAcc[0].value.length > 0){
				var result = $.ajax({
					url: 'user/chkExist',  
		               data: 'userAccount='+uAcc[0].value,  
		               type: 'post',  
		               dataType: 'json',  
		               async: false,  
		               cache: false
					}).responseText;
				if(result == 'true'){
					$.messager.alert('提示',"["+uAcc[0].value+"]已经存在,请更换新的账号",'info');
					uAcc.val("");
					uAcc.focus();
				}
			}
		}
    //添加
		function addPartolTeamUserRelation(){
			var uAcc = $("#userAccount");
			if(uAcc[0].value.length > 0){
				if(!/^[\w]+$/.test(uAcc[0].value)){
					$.messager.alert('提示',"账号只能是英文字母、数字及下划线的组合",'info');
					return;
				}
				var result = $.ajax({
					url: 'user/chkExist',  
	                data: 'userAccount='+uAcc[0].value,  
	                type: 'post',  
	                dataType: 'json',  
	                async: false,  
	                cache: false
					}).responseText;
				if(result == 'true'){
					$.messager.alert('提示',"["+uAcc[0].value+"]已经存在,请更换新的账号",'info');
					uAcc.val("");
					uAcc.focus();
					return;
				}
			}
			var r = $('#partolTeamUserRelationAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("baseInfo/addPartrolTeamUserRelation",$("#partolTeamUserRelationAddForm").serializeArray(),function(data){
				closeSecondWindow();
				$('#partolTeamUserRelationTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="partolTeamUserRelationAddForm" method="post" class="operationPage">
	  <input type="hidden" name="teamInfoName">
		<input type="hidden" name="teamInfoId">
		<input type="hidden" name="userOrg">
		 <table>
			<tr>
				<td width="15%" align="right">姓名：</td>
				<td width="35%" align="left">
					<input  name="userName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" >
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">账号：</td>
				<td width="35%" align="left">
					<input id="userAccount" name="userAccount" class="easyui-validatebox textbox"
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" onblur="chkExist()">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">密码：</td>
				<td width="35%" align="left">
					<input id="userPassword" name="userPassword" type="password"  class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">密码重复：</td>
				<td width="35%" align="left">
					<input id="reUserPassword" name="reUserPassword" type="password" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="equalTo['#userPassword']">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">性别：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="dutyId"
					name="userGender" style="width: 91%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${taskGender}" var="taskGender">
							<option value="${taskGender.dictData}">${taskGender.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input name="userTelephone" class="easyui-validatebox textbox" data-options="required:true,validType:['mobileAndTel','mobile']" 
					style="height: 20px; font-size: 12px; width: 90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">出生日期：</td>
				<td width="35%" align="left">
					<input  name="userBirthday"  data-options="editable:false" class="easyui-datebox"style="height:23px;font-size:12px;width:90%;" >	
				</td>
				<td width="15%" align="right">文化程度：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="dutyId"
					name="education" style="width: 91%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${education}" var="education">
							<option value="${education.dictData}">${education.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">户口性质：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" 
					name="registration" style="width: 91%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${registerrd}" var="registerrd">
							<option value="${registerrd.dictData}">${registerrd.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td width="15%" align="right">户籍地：</td>
				<td width="35%" align="left">
					<input  name="domicile" class="easyui-validatebox textbox" style="height:20px;font-size:12px;width:90%;" >
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">居住地：</td>
				<td width="35%" align="left">
					<input  name="residence" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;" >
				</td>
				<td width="15%" align="right">职务：</td>
				<td width="35%" align="left">
					<input  name="userDuty" class="easyui-validatebox textbox" 
					data-options="required:false" style="height:20px;font-size:12px;width:90%;" >
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">身份证号：</td>
				<td width="35%" align="left">
					<input  name="idNumber" class="easyui-validatebox textbox" validType="idCode" style="height:20px;font-size:12px;width:90%;">					
				</td>
				<td width="15%" align="right">管理单位：</td>
				<td width="35%" align="left">
					<input  name="managementUnit" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;" >
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="50" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeSecondWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addPartolTeamUserRelation();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
