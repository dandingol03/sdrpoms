<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
    
		//添加铁路信息
		function addPubMapRailData(){
			if(null==$("#addStream").combobox("getValues")||$("#addStream").combobox("getValues")==""){
				$.messager.alert('提示',"请选择行别",'info');
				return;
			}
			
			//验证字段
			var r = $('#pubMapRailDataAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			$.post("pubMapRailData/addPubMapRailData",$("#pubMapRailDataAddForm").serializeArray(),function(data){
				closeSecondWindow();
				$('#pubMapRailDataTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="pubMapRailDataAddForm" method="post" class="operationPage">
		<input type="hidden" name="railName">
		<input type="hidden" name="railId">
		<table>
			<tr>
				<td width="15%" align="right">点序号：</td>
				<td width="35%" align="left">
					<input name="orderNumber" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">行别：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="addStream"
					name="stream" style="width: 90%;" data-options="editable:false,required:true">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${streams}" var="streamsList">
							<option value="${streamsList.dictData}">${streamsList.dictName}</option>
						</c:forEach>
					</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input name="lng" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input name="lat" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">公里标：</td>
				<td width="35%" align="left">
					<input name="kilometerMark" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
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
					<a onclick="addPubMapRailData();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
