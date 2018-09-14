<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
    
		//添加隧道信息
		function addOaInfoNews(){
			//验证字段
			var r = $('#oaInfoNewsAddForm').form('validate');
			if(!r) {
				return false;
			}
			showWaitingWindow();
			var tempOutCome="";
			var tempFile="";
			$('#oaInfoNewsAddForm').form('submit',{
				url : 'file/fileUpLoad',
				onSubmit : function(pa) {},
				success : function(data) {
					if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
							tempOutCome=data;
							$('#oaInfoNewsAddForm').form('submit',{
								url : 'file/fileUpLoadTwo',
								onSubmit : function(pa) {},
								success : function(data) {
									if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
										tempFile=data;
										$.post("oaInfo/addOaInfoNews?photos="+tempOutCome+"&attachment="+tempFile,
												$("#oaInfoNewsAddForm").serializeArray(),function(data){
											closeWaitingWindow();
											$.messager.alert('提示',data.mes,'info');
											$('#oaInfoNewsTable').datagrid('reload');	
											closeWindow();
											return;
										});
									}else{
										$.post("oaInfo/addOaInfoNews?photos="+tempOutCome,
												$("#oaInfoNewsAddForm").serializeArray(),function(data){
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
						$('#oaInfoNewsAddForm').form('submit',{
							url : 'file/fileUpLoadTwo',
							onSubmit : function(pa) {},
							success : function(data) {
								if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
									tempFile=data;	
									$.post("oaInfo/addOaInfoNews?attachment="+tempFile,
											$("#oaInfoNewsAddForm").serializeArray(),function(data){
										closeWaitingWindow();
										$.messager.alert('提示',data.mes,'info');
										$('#oaInfoNewsTable').datagrid('reload');	
										closeWindow();
										return;
									});
								}else{
									$.post("oaInfo/addOaInfoNews",
											$("#oaInfoNewsAddForm").serializeArray(),function(data){
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
  	
  	<!-- 验证使用jquery-validation -->
	<form id="oaInfoNewsAddForm" enctype="multipart/form-data" method="post" class="operationPage">
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
					<input class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
				<td width="15%" align="right">附件：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox"   name="uploadFileTwo" data-options="prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:false" style='width:90%;'/>
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
					<a onclick="addOaInfoNews();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
