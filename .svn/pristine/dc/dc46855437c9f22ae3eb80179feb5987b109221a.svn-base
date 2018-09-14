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
		//添加公文信息
		function addOaInfoOutBoxDocument(index){
			//验证字段
			var r = $('#oaInfoOutBoxDocumentAddForm').form('validate');
			if(!r) {
				return false;
			}
			if(null==$("#state").combobox("getValues")||$("#state").combobox("getValues")==""){
				$.messager.alert('提示',"邮件类型不能为空！",'info');
				return;
			}
			showWaitingWindow();
			var ind = index;
			$('#oaInfoOutBoxDocumentAddForm').form('submit',{
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
									//存到草稿箱
									var oaInfoOutBoxDocument=$("#oaInfoOutBoxDocumentAddForm").serializeArray();
									oaInfoOutBoxDocument.splice(1,1);//布局动了必须修改
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
									console.log(oaInfoOutBoxDocument);
									$.post("oaInfo/addOutBoxDocument"+ps,oaInfoOutBoxDocument,function(data){
										$.messager.alert('提示',data.mes,'info');
										closeWindow();
										$('#oaInfoOutBoxDocumentTable').datagrid('reload');
									});
								}
								if(ind=='1'){
									var oaInfoOutBoxDocuments=$("#oaInfoOutBoxDocumentAddForm").serializeArray();
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
									console.log(oaInfoOutBoxDocument);
									$.post("oaInfo/addOutBoxDocument"+ps,oaInfoOutBoxDocuments,function(data){
										$.messager.alert('提示',data.mes,'info');
										closeWindow();
										$('#oaInfoOutBoxDocumentTable').datagrid('reload');
									});
								}
						} else {
							if(ind=='2'){
								//存到草稿箱
								var oaInfoOutBoxDocument=$("#oaInfoOutBoxDocumentAddForm").serializeArray();
								oaInfoOutBoxDocument.splice(1,1);//布局动了必须修改
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
								console.log(oaInfoOutBoxDocument);
								$.post("oaInfo/addOutBoxDocument",oaInfoOutBoxDocument,function(data){
									$.messager.alert('提示',data.mes,'info');
									closeWindow();
									$('#oaInfoOutBoxDocumentTable').datagrid('reload');
								});
							}
							if(ind=='1'){
								var oaInfoOutBoxDocuments=$("#oaInfoOutBoxDocumentAddForm").serializeArray();
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
								console.log(oaInfoOutBoxDocument);
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
			$('#oaInfoOutBoxDocumentTable').datagrid('reload');
			closeWindow();
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="oaInfoOutBoxDocumentAddForm" enctype="multipart/form-data"  method="post" class="operationPage">
		<input type="hidden" name="receiveUserId" value="${sessionScope.userId}">
		<input type="hidden" id="isDrafts" name="isDrafts" value="1">
		<textarea name="editor" id="editor" cols="30" rows="10"></textarea>
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
				<td width="15%" align="right">收件人：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" multiple="multiple" size="2" id=""
					name="sendUserId" style="width: 90%;" data-options="editable:false,required:true">
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
					<input  class="easyui-filebox" name="uploadFile" data-options="prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style="height:50px;font-size:12px;width:90%;">
				</td>
			</tr>
				
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addOaInfoOutBoxDocument(1);" class="easyui-linkbutton" iconCls="icon-save">发送</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addOaInfoOutBoxDocument(2);" class="easyui-linkbutton" iconCls="icon-save">存草稿</a>
				</td>
			</tr>
		</table>
	</form>
</body>
