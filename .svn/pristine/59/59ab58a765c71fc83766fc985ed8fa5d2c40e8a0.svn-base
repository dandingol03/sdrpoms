<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		//添加信息
		function addBaseInfoHiddenTrajection(){
			//行人易穿行状态
			if(null==$("#addStatus").combobox("getValues")||$("#addStatus").combobox("getValues")==""){
				$.messager.alert('提示',"请选择行人易穿行状态",'info');
				return;
			}
			//验证字段
			var r = $('#baseInfoHiddenTrajectionAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoHiddenTrajectionAddForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?photos="+data;
    						console.log('===data==>', ps);
    						$.post("baseInfo/addHiddenTrajection"+ps,
    							$("#baseInfoHiddenTrajectionAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoHiddenTrajectionTable').datagrid('reload');
    						}); 
    					} else {
    						$.post("baseInfo/addHiddenTrajection",
    							$("#baseInfoHiddenTrajectionAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoHiddenTrajectionTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoHiddenTrajectionTable').datagrid('reload');
		}});
		}
	</script>
  	<!-- 验证使用jquery-validation -->
	<form id="baseInfoHiddenTrajectionAddForm" enctype="multipart/form-data" method="post" class="operationPage">
		<table>
			<tr>
				<td width="20%" align="right">行人易穿行名称：</td>
				<td width="30%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="20%" align="right">行人易穿行状态：</td>
				<td width="30%" align="left">
					<select id="addStatus" class="easyui-combobox" name="status" style="width:90%;" data-options="editable:false">							
		        		<option value="" disabled selected>请选择</option>
	        			<c:forEach items="${statusList}" var="statusListTemp">
			    		<option value="${statusListTemp.dictData}">${statusListTemp.dictName}</option>
	       				</c:forEach>
		  			</select>
		  			<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">地址：</td>
				<td width="30%" align="left">
					<input  name="address" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="20%" align="right">护栏情况：</td>
				<td width="30%" align="left">
		 			<select id="condition" class="easyui-combobox" name="conditions" style="width:90%;" data-options="editable:false">							
		       			 <option value="" disabled selected>请选择</option>
		       			 <c:forEach items="${conditionList}" var="conditionListTemp">
			    			<option value="${conditionListTemp.dictData}">${conditionListTemp.dictName}</option>
		        		</c:forEach>
		 			 </select>
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">联系电话：</td>
				<td width="30%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="required:false,validType:['mobileAndTel','mobile']" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="20%" align="right">部位照片：</td>
				<td width="30%" align="left">
					<input id="photosName" name="uploadFile" class="easyui-filebox" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true"  style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="20%" align="right">经度：</td>
				<td width="30%" align="left">
					<input  name="lng" class="easyui-validatebox textbox" validType="number" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="20%" align="right">纬度：</td>
				<td width="30%" align="left">
					<input  name="lat" class="easyui-validatebox textbox" validType="number" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="80%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="65" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addBaseInfoHiddenTrajection();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
