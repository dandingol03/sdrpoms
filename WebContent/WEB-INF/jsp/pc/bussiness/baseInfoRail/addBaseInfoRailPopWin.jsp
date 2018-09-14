<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
	    jQuery(function($){
	  		$(document).ready(function() {
	  			
	  		});
		});
		//添加铁路信息
		function addBaseInfoRail(){
			if(null==$("#addClassify").combobox("getValues")||$("#addClassify").combobox("getValues")==""){
				$.messager.alert('提示',"请选择分类",'info');
				return;
			}
			if(null==$("#addRailLevel").combobox("getValues")||$("#addRailLevel").combobox("getValues")==""){
				$.messager.alert('提示',"请选择线别",'info');
				return;
			}
			
			//验证字段
			var r = $('#baseInfoRailAddForm').form('validate');
			if(!r) {
				return false;
			}
			//计算长度
			var start= parseInt($("#startKM").val()*1000) +parseInt( $("#startM").val());
			var end= parseInt($("#endKM").val()*1000) +parseInt( $("#endM").val());
			var length=end-start;
			var baseInfoRail = $("#baseInfoRailAddForm").serializeArray();
			baseInfoRail.push({
				name: 'length',
				value: length
			});
			/* console.log(baseInfoRail); */
			$.post("baseInfo/addBaseInfoRail", baseInfoRail, function(data){
				closeWindow();
				$('#baseInfoRailTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}
		

	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="baseInfoRailAddForm" method="post" class="operationPage"">
		<table>
			<tr>
				<td width="15%" align="right">铁路名称：</td>
				<td width="35%" align="left">
					<input name="name" id="name" class="easyui-validatebox textbox"  data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">铁路编号：</td>
				<td width="35%" align="left">
					<input name="number" id="number" class="easyui-validatebox textbox"  data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">铁路分类：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="addClassify"
					name="classify" style="width: 90%;" data-options="editable:false,required:true">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${classifyList}" var="classifyListTemp">
							<option value="${classifyListTemp.dictData}">${classifyListTemp.dictName}</option>
						</c:forEach>
				</select>
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">线别：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="addRailLevel"
					name="railLevel" style="width: 90%;" data-options="editable:false,required:true">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railLevelList}" var="railLevelListTemp">
							<option value="${railLevelListTemp.dictData}">${railLevelListTemp.dictName}</option>
						</c:forEach>
				</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">起点里程：</td>
				<td width="35%" align="left">
					<input id ="startKM" name="startKM" class="easyui-validatebox textbox" validType="number" 
					style="height:20px;font-size:12px;width:20%;" > &nbsp;K&nbsp;
					<input id ="startM" name="startM" class="easyui-validatebox textbox" validType="number" 
					style="height:20px;font-size:12px;width:30%;" >&nbsp;M
				</td>
				<td width="15%" align="right">终点里程：</td>
				<td width="35%" align="left">
					<input id="endKM" name="endKM" class="easyui-validatebox textbox" validType="number" 
					style="height:20px;font-size:12px;width:20%;" > &nbsp;K&nbsp;
					<input id="endM"  name="endM" class="easyui-validatebox textbox" validType="number" 
					style="height:20px;font-size:12px;width:30%;" >&nbsp;M
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">起点位置：</td>
				<td width="35%" align="left">
					<input name="startId" id="startId" class="easyui-validatebox textbox"  data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">终点位置：</td>
				<td width="35%" align="left">
					<input name="endId" id="endId" class="easyui-validatebox textbox"  data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">运营状态：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="addState"
					name="state" style="width: 90%;" data-options="editable:false,required:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${operationStateList}" var="operationStateListTemp">
							<option value="${operationStateListTemp.dictData}">${operationStateListTemp.dictName}</option>
						</c:forEach>
				</select>
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
					<a onclick="addBaseInfoRail();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
