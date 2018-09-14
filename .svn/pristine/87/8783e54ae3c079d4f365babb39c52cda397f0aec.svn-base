<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		//添加监控信息
		function addvideoMonitorInfo(){
			//验证字段
			var r = $('#videoMonitorInfoAddForm').form('validate');
			if(!r) {
				return false;
			}
			var uAcc = $("#username");
			if(uAcc[0].value.length > 0){
				/* if(!/^[\w]+$/.test(uAcc[0].value)){
					$.messager.alert('提示',"账号只能是英文字母、数字及下划线的组合",'info');
					return;
				} */
				var result = $.ajax({
					url: 'video/chkExist',  
	                data: 'username='+uAcc[0].value,  
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
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			$.post("baseInfo/addVideoMonitorInfo",$("#videoMonitorInfoAddForm").serializeArray(),function(data){
				closeWindow();
				$('#videoMonitorInfoTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="videoMonitorInfoAddForm" method="post" class="operationPage">
		<table>
					<tr>
				<td width="15%" align="right">监控探头名称：</td>
				<td width="35%" align="left">
					<input  name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">监控探头编号：</td>
				<td width="35%" align="left">
					<input name="number" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">监控部位：</td>
				<td width="35%" align="left">
					<input  name="monitoringPosition" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">接入部门：</td>
				<td width="35%" align="left">
					<input  name="accessDepartment" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">管理部门：</td>
				<td width="35%" align="left">
					<input  name="administration" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">负责人：</td>
				<td width="35%" align="left">
					<input  name="charger" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">用户名：</td>
				<td width="35%" align="left">
					<input id="username" name="username" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">密码：</td>
				<td width="35%" align="left">
					<input id="pwd" name="pwd" type="password" validType="equalTo['#pwd']" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
			
			<tr>
				<td width="15%" align="right">密码重复：</td>
				<td width="35%" align="left">
					<input id="reUserPassword" name="reUserPassword" type="password" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="equalTo['#pwd']">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">端口号：</td>
				<td width="35%" align="left">
					<input  name="port" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
		
			<tr>
				<td width="15%" align="right">通道ID：</td>
				<td width="35%" align="left">
					<input  name="channel" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">ip地址：</td>
				<td width="35%" align="left">
					<input  name="ip" class="easyui-validatebox textbox" validType="ip" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="required:false,validType:['mobileAndTel','mobile']"
					 style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input  name="adress" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<!-- videoType -->
			<tr>
				<td width="15%" align="right">监控类型：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" name="videoType" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${videoTypeList}" var="videoTypeList">
							<option value="${videoTypeList.dictData}">${videoTypeList.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox" validType="number"  data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox" validType="number" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
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
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addvideoMonitorInfo();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
