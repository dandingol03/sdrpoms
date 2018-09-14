<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<!-- 隐患处置  修改-->
    <script type="text/javascript">
	function updateDangerInfoDispose(){
		//非空校验
		var r = $('#dangerInfoDisposeUpdateForm').form('validate');
		if(!r) {
			return false;
		}
	/* 	//结果
		if(null==$("#resultDis").combobox("getValues")||$("#resultDis").combobox("getValues")==""){
			$.messager.alert('提示',"请选处理结果",'info');
			return;
		} */
		showWaitingWindow();
		$('#dangerInfoDisposeUpdateForm').form('submit',{
			url : 'file/fileUpLoad',
			onSubmit : function(pa) {},
			success : function(data) {
				if (typeof (data) != 'undefined') {
					console.log('===data==>', data);
					if (data != "-1" && data != "") {
						var ps = "?photoss="+data;
						console.log('===data==>', ps);
						var dangerInfoDispose= $("#dangerInfoDisposeUpdateForm").serializeArray();
						dangerInfoDispose.splice(1,1);//photoss
						$.post("patrol/updateDangerInfoDispose"+ps,dangerInfoDispose,function(data){
							$.messager.alert('提示',data.mes,'info');
							closeWindow();
							$('#dangerInfoDisposeTaskTable').datagrid('reload');
						}); 
					} else {
						console.log($("#dangerInfoDisposeUpdateForm").serializeArray());
						$.post("patrol/updateDangerInfoDispose",
								$("#dangerInfoDisposeUpdateForm").serializeArray(),function(data){
							$.messager.alert('提示',data.mes,'info');
							closeWindow();
							$('#dangerInfoDisposeTaskTable').datagrid('reload');
						}); 
					}
				} else {
					$.messager.alert('提示', "文件上传失败", 'info');
				}
			closeWaitingWindow();
			}
		});
		closeWindow();
		$('#dangerInfoDisposeTaskTable').datagrid('reload');
	}
	</script>
      <script type="text/javascript">
    </script>
	<form id="dangerInfoDisposeUpdateForm" enctype="multipart/form-data"method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="photoss" >
		<table>
				<tr>
					<td width="15%" align="right">处理方式：</td>
					<td width="35%" align="left">
						<input name="handleWay" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
					</td>
					<td width="15%" align="right">处理结果：</td>
					<td width="35%" align="left">
				<select class="easyui-combobox" id=""
					name="handleStatus" style="width: 90%;" data-options="editable:false,required:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${patrolHandleStatusList}" var="partStationListTemp">
							<option value="${partStationListTemp.dictData}">${partStationListTemp.dictName}</option>
						</c:forEach>
				</select>
					</td>
				</tr>
			
				<tr>
					<td width="15%" align="right">结果描述：</td>
					<td colspan="3" width="85%" align="left">
						<textarea class="easyui-validatebox textbox" id="resultDis"name="resultDis" cols="50" rows="4"></textarea>
					</td>
				</tr>
				<tr> 
					<td width="15%" align="right">照片：</td>
					<td width="35%" align="left">
						<input id="photossName"value="${photosName}" name="uploadFile" class="easyui-filebox" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true"  style="height:20px;font-size:12px;width:90%;">
					</td>
				</tr>
			<tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateDangerInfoDispose();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
