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
	  		$(document).ready(function() {
	  		});
		});
    
		//添加铁路信息
		function addBaseInfoPeripheralPlace(){
			//类别
			if(null==$("#category").combobox("getValues")||$("#category").combobox("getValues")==""){
				$.messager.alert('提示',"请选择类别",'info');
				return;
			}
			//验证字段
			var r = $('#baseInfoPeripheralPlaceAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
    		showWaitingWindow();
    		$('#baseInfoPeripheralPlaceAddForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?photos="+data;
    						console.log('===data==>', ps);
    						$.post("baseInfo/addBaseInfoPeripheralPlace"+ps,
    								$("#baseInfoPeripheralPlaceAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPeripheralPlaceTable').datagrid('reload');
    						}); 
    					} else {
    						$.post("baseInfo/addBaseInfoPeripheralPlace",
    								$("#baseInfoPeripheralPlaceAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPeripheralPlaceTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoPeripheralPlaceTable').datagrid('reload');
		}});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="baseInfoPeripheralPlaceAddForm" enctype="multipart/form-data"  method="post" class="operationPage">
		<table>
			 <tr>
				<td width="15%" align="right">名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;" >
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">类别：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox"
					id="category" name="category" style="width: 90%;" data-options="editable:false,required:true">
						<option value="" disabled selected >请选择</option>
						<c:forEach items="${peripherallList}" var="peripherallListTemp">
							<option value="${peripherallListTemp.dictData}">${peripherallListTemp.dictName}</option>
						</c:forEach>
				</select>
				<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input  name="address" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;" >
				</td>
				<td width="15%" align="right">负责人：</td>
				<td width="35%" align="left">
					<input  name="charger" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="required:false,validType:['mobileAndTel','mobile']" style="height:20px;font-size:12px;width:90%;" >
				</td>
				<td width="15%" align="right">照片：</td>
				<td width="35%" align="left">
					<input name="uploadFile" id="photosName" class="easyui-filebox" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true"  style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">是否重点：</td>
				<td width="35%" align="left">
					<input type="radio" name="type"  value="1" checked/>是
					<input type="radio" name="type"  value="2" />否
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="number">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="number">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">基本情况描述：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="description" cols="50" rows="4"></textarea>
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
					<a onclick="addBaseInfoPeripheralPlace();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
