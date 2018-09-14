<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateOaInfoNews(){
			var r = $('#oaInfoNewsUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			showWaitingWindow();
			var tempOutCome="";
			var tempFile="";
			$('#oaInfoNewsUpdateForm').form('submit',{
				url : 'file/fileUpLoad',
				onSubmit : function(pa) {},
				success : function(data) {
					if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
							tempOutCome=data;
							$('#oaInfoNewsUpdateForm').form('submit',{
								url : 'file/fileUpLoadTwo',
								onSubmit : function(pa) {},
								success : function(data) {
									if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
										console.log(data);
										tempFile=data;
										var forecastUpdateForm=$("#oaInfoNewsUpdateForm").serializeArray();
										forecastUpdateForm.splice(1,1);
										forecastUpdateForm.splice(1,1);
										$.post("oaInfo/updateOaInfoNews?photos="+tempOutCome+"&attachment="+tempFile,
												forecastUpdateForm,function(data){
											closeWaitingWindow();
											$.messager.alert('提示',data.mes,'info');
											$('#oaInfoNewsTable').datagrid('reload');	
											closeWindow();
											return;
										});
										
									}else{
										var forecastUpdateForm=$("#oaInfoNewsUpdateForm").serializeArray();
										forecastUpdateForm.splice(1,1);
										$.post("oaInfo/updateOaInfoNews?photos="+tempOutCome,
												forecastUpdateForm,function(data){
											closeWaitingWindow();
											$.messager.alert('提示',data.mes,'info');
											$('#oaInfoNewsTable').datagrid('reload');	
											closeWindow();
											return;
										});
									}
								}
							});
					}else{
						$('#oaInfoNewsUpdateForm').form('submit',{
							url : 'file/fileUpLoadTwo',
							onSubmit : function(pa) {},
							success : function(data) {
								if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
									tempFile=data;
									var forecastUpdateForm=$("#oaInfoNewsUpdateForm").serializeArray();
									forecastUpdateForm.splice(2,1);
									$.post("oaInfo/updateOaInfoNews?attachment="+tempFile,forecastUpdateForm,function(data){
										closeWaitingWindow();
										$.messager.alert('提示',data.mes,'info');
										$('#oaInfoNewsTable').datagrid('reload');	
										closeWindow();
										return;
									});
								}else{
									$.post("oaInfo/updateOaInfoNews",
											$("#oaInfoNewsUpdateForm").serializeArray(),function(data){
										closeWaitingWindow();
										$.messager.alert('提示',data.mes,'info');
										$('#oaInfoNewsTable').datagrid('reload');	
										closeWindow();
										return;
									});
								}
							}
						});
					}			
				}
			});
		}
	</script>
    
	<form id="oaInfoNewsUpdateForm" enctype="multipart/form-data"  method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="photos">
		<input type="hidden" name="attachment">
		<table>
			<tr>
				<td width="15%" align="right">标题：</td>
				<td width="35%" align="left">
					<input  name="title" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">发布时间：</td>
				<td colspan="3" width="35%" align="left">
					<input id="newsTime" name="publishTime"  data-options="required:true,editable:false"  class="easyui-datebox" class="Wdate" type="text"
					style="width:90%;" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">摘要：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="abstracts" cols="75" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">内容：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="content" cols="75" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${photosNames}" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
				<td width="15%" align="right">附件：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${attachmentName}" name="uploadFileTwo" data-options="prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:false" style='width:90%;'/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="75" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateOaInfoNews();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>       
			</tr>
		</table>
	</form>
</body>
