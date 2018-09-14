<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
    
    <script type="text/javascript">
    
  	jQuery(function($){

  		//加载机构树
  		$(document).ready(function() {
  			var jqueryObj=$("#addUserOrg");
	 		makeOrgTree(jqueryObj);
  		});

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

	//添加用户
	function addUser(){
		if(null==$("#userDepartmentAdd").combobox("getValues")||$("#userDepartmentAdd").combobox("getValues")==""){
			$.messager.alert('提示',"请选择所属部门",'info');
			return;
		}
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
		
		var orgTree = $('#addUserOrg').combotree("tree");
		var orgSelected = orgTree.tree('getSelected');
		if(null== orgSelected){
			$.messager.alert("提示","所属机构不能为空！","info");
			return false;
		}
		var r = $('#userAddForm').form('validate');
		if(!r) {
			return false;
		}
		$.post("user/addUser",$("#userAddForm").serializeArray(),function(data){
			closeWindow();
			$('#userTable').datagrid('reload');  
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="userAddForm" method="post" class="operationPage">
		<table>
			<tr>
				<td width="15%" align="right">账号：</td>
				<td width="35%" align="left">
					<input id="userAccount" name="userAccount" class="easyui-validatebox textbox"
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" onblur="chkExist()">
				</td>
				<td width="15%" align="right">姓名：</td>
				<td width="35%" align="left">
					<input name="userName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">密码：</td>
				<td width="35%" align="left">
					<input id="userPassword" name="userPassword" type="password"  class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">密码重复：</td>
				<td width="35%" align="left">
					<input id="reUserPassword" name="reUserPassword" type="password" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="equalTo['#userPassword']">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">性别：</td>
				<td width="35%" align="left">
					<input type="radio" name="userGender" value="1" checked/>男
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
					<input id="addUserOrg" type="text" name="userOrg" class="easyui-textbox" 
					data-options="required:true" style="width:90%;"><a style="color:red">*</a>
				</td>
				<td width="15%" align="right">所属部门：</td>
				<td width="200px" align="left">
				 	<select class="easyui-combobox" name="userDepartment"  id='userDepartmentAdd'
				  	 style="width:90%;" data-options="editable:false,required:true">							
				        <option value="">请选择</option>
				        <c:forEach items="${departmentList}" var="departmentTemp">
					    	<option value="${departmentTemp.dictData}">${departmentTemp.dictName}</option>
				        </c:forEach>
				   	</select>
				   	<a style="color:red">*</a>
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
					<input name="mail" class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;" maxlength="32" validType="email">
				</td>
				<td width="15%" align="right">QQ或微信：</td>
				<td width="35%" align="left">
					<input name="qqWeixin"class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;" maxlength="16">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input name="lng" class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;"  validType="number" >
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input name="lat"class="easyui-validatebox textbox"
					style="height:20px;font-size:12px;width:90%;"  validType="number">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">用户状态：</td>
				<td width="35%" align="left">
					<input type="radio" name="enable" value="1" checked />正常
					<input type="radio" name="enable" value="0" />禁用
				</td>
				<td width="15%" align="right">是否系统用户：</td>
				<td width="35%" align="left">
					<input type="radio" name="isSys" value="1" checked/>是&nbsp;&nbsp;
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
					<a onclick="addUser();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
