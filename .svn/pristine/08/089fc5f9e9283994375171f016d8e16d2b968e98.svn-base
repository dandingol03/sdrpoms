<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
  <script type="text/javascript">
	     CKEDITOR.replace('editor');
	  </script>
    <script type="text/javascript">
	    jQuery(function($){
	  		$(document).ready(function() {
	  			
	  		});
		});
		function updateOaInfoDraftsDocument(index){
			var r = $('#oaInfoDraftsDocumentUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			if(null==$("#state").combobox("getValues")||$("#state").combobox("getValues")==""){
				$.messager.alert('提示',"邮件类型不能为空！",'info');
				return;
			}
			showWaitingWindow();
			var ind = index;
			$('#oaInfoDraftsDocumentUpdateForm').form('submit',{
				url : 'file/fileUpLoad',
				onSubmit : function(pa) {},
				success : function(data) {
					if (typeof (data) != 'undefined') {
						if (data != "-1" && data != "") {
							var ps='';
							var tempFileIdList = data.split(",");
							for (var i=0;i<tempFileIdList.length; i++) {
								if(i==0){
									ps += "?file="+tempFileIdList[i];
								} else {
									ps += "&file="+tempFileIdList[i];
								}	
							}
							if(ind=='2'){
								//存到草稿箱  isDrafts默认值不是草稿箱
								var oaInfoOutBoxDocument=$("#oaInfoDraftsDocumentUpdateForm").serializeArray();
								oaInfoOutBoxDocument.splice(3,1);//isDrafts
								oaInfoOutBoxDocument.splice(3,1);//删除旧的file
								console.log(oaInfoOutBoxDocument);	
								var isDrafts='0';
								var isDeleteOut='0';
								var sendState='1';
								var isDelete='0';
								var isApproved='1';
								oaInfoOutBoxDocument.push(
										{
										name: 'isDrafts',
										value: isDrafts
										},
										{
										name: 'isDeleteOut',
										value: isDeleteOut
										},
										{
										name: 'isDelete',
										value: isDelete
										},
										{
										name: 'sendState',
										value: sendState
										},
											{
											name: 'isApproved',
											value: isApproved
											}
										);
								$.post("oaInfo/updateDraftsDocument"+ps,oaInfoOutBoxDocument,function(data){
									$.messager.alert('提示',data.mes,'info');
									closeWindow();
									$('#oaInfoOutBoxDocumentTable').datagrid('reload');
								});
							}
							if(ind=='1'){//发送邮件（带文件） isDrafts默认值不是草稿箱
								var oaInfoOutBoxDocuments=$("#oaInfoDraftsDocumentUpdateForm").serializeArray();
								oaInfoOutBoxDocuments.splice(4,1);//删除旧的file
								console.log(oaInfoOutBoxDocuments);
								var isDeleteOut='0';
								var sendState='0';
								var isDelete='0';
								var isApproved='1';
								oaInfoOutBoxDocuments.push(
										{
										name: 'isDeleteOut',
										value: isDeleteOut
										},
										{
										name: 'isDelete',
										value: isDelete
										},
										{
										name: 'sendState',
										value: sendState
										},
											{
											name: 'isApproved',
											value: isApproved
											}
										);
								$.post('oaInfo/deleteDrafts',oaInfoOutBoxDocuments,function(data){
									$('#oaInfoDraftsDocumentTable').datagrid('reload');
								});
								$.post("oaInfo/addOutBoxDocument"+ps,oaInfoOutBoxDocuments,function(data){
									$.messager.alert('提示',data.mes,'info');
									closeWindow();
									$('#oaInfoOutBoxDocumentTable').datagrid('reload');
								});
								
							}
						} else {
							if(ind=='2'){
								//存到草稿箱（不带文件） isDrafts默认值不是草稿箱
								var oaInfoOutBoxDocument=$("#oaInfoDraftsDocumentUpdateForm").serializeArray();
								oaInfoOutBoxDocument.splice(3,1);//isDrafts
								console.log(oaInfoOutBoxDocument);	
								var isDrafts='0';
								var isDelete='0';
								var sendState='1';
								var isDeleteOut='0';
								var isApproved='1';
								oaInfoOutBoxDocument.push(
										{
										name: 'isDrafts',
										value: isDrafts
										},
										{
										name: 'isDeleteOut',
										value: isDeleteOut
										},
										{
										name: 'isDelete',
										value: isDelete
										},
										{
										name: 'sendState',
										value: sendState
										},
										{
										name: 'isApproved',
										value: isApproved
										}
										);
								$.post("oaInfo/updateDraftsDocument",oaInfoOutBoxDocument,function(data){
									$.messager.alert('提示',data.mes,'info');
									closeWindow();
									$('#oaInfoOutBoxDocumentTable').datagrid('reload');
								});
							}
							if(ind=='1'){//发送邮件（不带文件）isDrafts默认值不是草稿箱
								var oaInfoOutBoxDocuments=$("#oaInfoDraftsDocumentUpdateForm").serializeArray();
								console.log(oaInfoOutBoxDocuments);								
								var isDeleteOut='0';
								var sendState='0';
								var isDelete='0';
								var isApproved='1';
								oaInfoOutBoxDocuments.push(
										{
										name: 'isDeleteOut',
										value: isDeleteOut
										},
										{
										name: 'isDelete',
										value: isDelete
										},
										{
										name: 'sendState',
										value: sendState
										},
											{
											name: 'isApproved',
											value: isApproved
											}
										);
								$.post('oaInfo/deleteDrafts',oaInfoOutBoxDocuments,function(data){
									$('#oaInfoDraftsDocumentTable').datagrid('reload');
								});
								$.post("oaInfo/addOutBoxDocument",oaInfoOutBoxDocuments,function(data){
									$.messager.alert('提示',data.mes,'info');
									closeWindow();
									$('#oaInfoOutBoxDocumentTable').datagrid('reload');
								});
							}
						}
					} else {
						$.messager.alert('提示', "文件上传失败", 'info');
					}
				closeWaitingWindow();
				}
			});
			$('#oaInfoDraftsDocumentTable').datagrid('reload');
			closeWindow();
		}
			
	</script>
	<form id="oaInfoDraftsDocumentUpdateForm" enctype="multipart/form-data" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<textarea name="editor" id="editor" cols="30" rows="10"></textarea>
		<input type="hidden" name="receiveUserId" value="${sessionScope.userId}">
		<input type="hidden" name="isDrafts" value="1">
		 <input type="hidden" name="file" >
		<table>
			<tr>
				<td width="15%" align="right">标题：</td>
				<td width="35%" align="left">
					<input name="title" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">类型：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="state"
					name="state" style="width: 90%;" data-options="editable:false,required:true">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${wjflList}" var="wjflListTemp">
							<option value="${wjflListTemp.dictData}">${wjflListTemp.dictName}</option>
						</c:forEach>
				</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">发件单位：</td>
				<td width="35%" align="left">
					<input  name="hairUnit" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">发送时间：</td>
				<td width="35%" align="left">
				<input id="sendTime" name="sendTime" required="required" class="easyui-datebox" >	
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
   			    <td width="15%" align="right">收件人：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" multiple="multiple" size="2" id="state"
					name="sendUserId" style="width: 90%;" data-options="editable:false,required:true" >
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${queryPers}" var="queryPersListTemp">
							<option value="${queryPersListTemp.id}">${queryPersListTemp.name}</option>
						</c:forEach>
				</select>
					<a style="color:red">*</a>
				</td> 
			</tr>
			<tr>
				<td width="15%" align="right">备注文件附件：</td>
				<td width="35%" align="left">
					<input id="fileName" value="${photosName}" class="easyui-filebox" name="uploadFile" data-options="prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style="height:50px;weight:300px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateOaInfoDraftsDocument(1);" class="easyui-linkbutton" iconCls="icon-save">发送</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateOaInfoDraftsDocument(2);" class="easyui-linkbutton" iconCls="icon-save">存草稿</a>
				</td>
			</tr>
		</table>
	</form>
</body>
