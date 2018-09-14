<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateBaseInfoPoliceStation(){
			var r = $('#baseInfoPoliceStationUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("#lng").val(),lat:$("#lat").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoPoliceStationUpdateForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						console.log('===data==>', ps);
    						var addNumber=$("#baseInfoPoliceStationUpdateForm").serializeArray();
    						addNumber.splice(1,1);
    						$.post("baseInfo/updateBaseInfoPoliceStation"+ps,addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPoliceStationTable').datagrid('reload');
    						}); 
    					} else {
    						var addNumber=$("#baseInfoPoliceStationUpdateForm").serializeArray();
    						$.post("baseInfo/updateBaseInfoPoliceStation",addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPoliceStationTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoPoliceStationTable').datagrid('reload');
    	}
			});
		}
	</script>
    
	<form id="baseInfoPoliceStationUpdateForm" enctype="multipart/form-data" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="fileId">
		<table>
			<tr>
				<td width="15%" align="right">警务站名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">警务站照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${photosName}" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">负责人：</td>
				<td width="35%" align="left">
					<input name="charger" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input name="telephone" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input name="adress" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">管辖范围：</td>
				<td width="35%" align="left">
					<input name="jurisdiction" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  id="lng" name="lng" class="easyui-validatebox textbox" validType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
				<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input id="lat" name="lat" class="easyui-validatebox textbox" validType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
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
					<a onclick="updateBaseInfoPoliceStation();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
