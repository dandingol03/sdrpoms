<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateOaInfoAnnouncement(){
			var r = $('#oaAnnouncementUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			showWaitingWindow();
				var tempOutCome="";
				var tempFile="";
				var tempThree="";
				$('#oaAnnouncementUpdateForm').form('submit',{
					url : 'file/fileUpLoad',
					onSubmit : function(pa) {},
					success : function(data) {
						tempOutCome=data;
						if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
								$('#oaAnnouncementUpdateForm').form('submit',{
									url : 'file/fileUpLoadTwo',
									onSubmit : function(pa) {},
									success : function(data) {
										console.log(data);
										tempFile=data;
										if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
											$('#oaAnnouncementUpdateForm').form('submit',{
												url : 'file/fileUpLoadThree',
												onSubmit : function(pa) {},
												success : function(data) {
														console.log(data);
														tempThree=data;	
													if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
														var oaAnnouncementUpdateForm=$("#oaAnnouncementUpdateForm").serializeArray();
														oaAnnouncementUpdateForm.splice(1,1);
														oaAnnouncementUpdateForm.splice(1,1);
														oaAnnouncementUpdateForm.splice(1,1);
														console.log(oaAnnouncementUpdateForm);
														console.log("1,2,3");
														$.post("oaInfo/updateOaInfoAnnouncement?photos="+tempFile+"&bcPhotos="+tempOutCome+"&attachment="+tempThree,
																oaAnnouncementUpdateForm,function(data){
															closeWaitingWindow();
															$.messager.alert('提示',data.mes,'info');
															$('#oaAnnouncementTable').datagrid('reload');	
															closeWindow();
															return;
														});
													}else{
														console.log("1,2");
														var oaAnnouncementUpdateForm=$("#oaAnnouncementUpdateForm").serializeArray();
														oaAnnouncementUpdateForm.splice(1,1);
														oaAnnouncementUpdateForm.splice(1,1);
														$.post("oaInfo/updateOaInfoAnnouncement?photos="+tempFile+"&bcPhotos="+tempOutCome,
																oaAnnouncementUpdateForm,function(data){
															closeWaitingWindow();
															$.messager.alert('提示',data.mes,'info');
															$('#oaAnnouncementTable').datagrid('reload');	
															closeWindow();
															return;
														});
													}
												}
											});
											
										}else{
											console.log("1,3");
											var oaAnnouncementUpdateForm=$("#oaAnnouncementUpdateForm").serializeArray();
											oaAnnouncementUpdateForm.splice(2,1);
											$.post("oaInfo/updateOaInfoAnnouncement?bcPhotos="+tempOutCome,
													oaAnnouncementUpdateForm,function(data){
												closeWaitingWindow();
												$.messager.alert('提示',data.mes,'info');
												$('#oaAnnouncementTable').datagrid('reload');	
												closeWindow();
												return;
											});
										}
									}
								});
						}else{
							$('#oaAnnouncementUpdateForm').form('submit',{
								url : 'file/fileUpLoadTwo',
								onSubmit : function(pa) {},
								success : function(data) {
									tempFile=data;
									if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
										$('#oaAnnouncementUpdateForm').form('submit',{
											url : 'file/fileUpLoadThree',
											onSubmit : function(pa) {},
											success : function(data) {
												tempThree=data;
												if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
													var oaAnnouncementUpdateForm=$("#oaAnnouncementUpdateForm").serializeArray();
													oaAnnouncementUpdateForm.splice(1,1);
													oaAnnouncementUpdateForm.splice(2,1);
													console.log("2,3");
													console.log(oaAnnouncementUpdateForm);
													$.post("oaInfo/updateOaInfoAnnouncement?photos="+tempFile+"&attachment="+tempThree,oaAnnouncementUpdateForm,function(data){
														closeWaitingWindow();
														$.messager.alert('提示',data.mes,'info');
														$('#oaAnnouncementTable').datagrid('reload');	
														closeWindow();
														return;
												});
												}else{
													console.log("2");
													var oaAnnouncementUpdateForm=$("#oaAnnouncementUpdateForm").serializeArray();
													oaAnnouncementUpdateForm.splice(1,1);
													$.post("oaInfo/updateOaInfoAnnouncement?photos="+tempFile,oaAnnouncementUpdateForm,function(data){
														closeWaitingWindow();
														$.messager.alert('提示',data.mes,'info');
														$('#oaAnnouncementTable').datagrid('reload');	
														closeWindow();
														return;
												});
											}
										}
										});
									}else{
										$('#oaAnnouncementUpdateForm').form('submit',{
											url : 'file/fileUpLoadThree',
											onSubmit : function(pa) {},
											success : function(data) {
												if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
												console.log("3");
												tempThree=data;
												var oaAnnouncementUpdateForm = $("#oaAnnouncementUpdateForm").serializeArray();
												oaAnnouncementUpdateForm.splice(3,1);
												$.post("oaInfo/updateOaInfoAnnouncement?attachment="+tempThree,
														oaAnnouncementUpdateForm,function(data){
													closeWaitingWindow();
													$.messager.alert('提示',data.mes,'info');
													$('#oaAnnouncementTable').datagrid('reload');	
													closeWindow();
													return;
												});
											}else{
												console.log("all");
												$.post("oaInfo/updateOaInfoAnnouncement",
														$("#oaAnnouncementUpdateForm").serializeArray(),function(data){
													closeWaitingWindow();
													$.messager.alert('提示',data.mes,'info');
													$('#oaAnnouncementTable').datagrid('reload');	
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
				}
			});
		}
	</script>
    
	<form id="oaAnnouncementUpdateForm" enctype="multipart/form-data"  method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="photos">
		<input type="hidden" name="bcPhotos">
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
					<input class="easyui-filebox" value="${photosNames}" name="uploadFileTwo" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
				<td width="15%" align="right">轮播照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${bcPhotoNames}" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true,required:true" style='width:90%'/>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">附件：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${attachmentName}" name="uploadFileThree" data-options="prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:false" style='width:90%;'/>
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
					<a onclick="updateOaInfoAnnouncement();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>       
			</tr>
		</table>
	</form>
</body>
