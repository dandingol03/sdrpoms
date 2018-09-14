<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateBaseInfoPartTunnel(){
			var r = $('#baseInfoPartTunnelUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
			var tempOutCome="";
			var tempFile="";
			$('#baseInfoPartTunnelUpdateForm').form('submit',{
				url : 'file/fileUpLoad',
				onSubmit : function(pa) {},
				success : function(data) {
					if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
							tempOutCome=data;
							$('#baseInfoPartTunnelUpdateForm').form('submit',{
								url : 'file/fileUpLoadTwo',
								onSubmit : function(pa) {},
								success : function(data) {
									if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
										console.log(data);
										tempFile=data;
										var forecastUpdateForm=$("#baseInfoPartTunnelUpdateForm").serializeArray();
										forecastUpdateForm.splice(1,1);
										forecastUpdateForm.splice(1,1);
										$.post("baseInfo/updateBaseInfoPartTunnel?fileId="+tempOutCome+"&photos="+tempFile,
												forecastUpdateForm,function(data){
											closeWaitingWindow();
											$.messager.alert('提示',data.mes,'info');
											$('#baseInfoPartTunnelTable').datagrid('reload');	
											closeWindow();
											return;
										});
										
									}else{
										var forecastUpdateForm=$("#baseInfoPartTunnelUpdateForm").serializeArray();
										forecastUpdateForm.splice(1,1);
										$.post("baseInfo/updateBaseInfoPartTunnel?fileId="+tempOutCome,
												forecastUpdateForm,function(data){
											closeWaitingWindow();
											$.messager.alert('提示',data.mes,'info');
											$('#baseInfoPartTunnelTable').datagrid('reload');	
											closeWindow();
											return;
										});
									}
								}
							});
					}else{
						$('#baseInfoPartTunnelUpdateForm').form('submit',{
							url : 'file/fileUpLoadTwo',
							onSubmit : function(pa) {},
							success : function(data) {
								if (typeof (data) != 'undefined'&&data != "-1" && data != "") {
									tempFile=data;
									var forecastUpdateForm=$("#baseInfoPartTunnelUpdateForm").serializeArray();
									forecastUpdateForm.splice(2,1);
									$.post("baseInfo/updateBaseInfoPartTunnel?photos="+tempFile,forecastUpdateForm,function(data){
										closeWaitingWindow();
										$.messager.alert('提示',data.mes,'info');
										$('#baseInfoPartTunnelTable').datagrid('reload');	
										closeWindow();
										return;
									});
								}else{
									$.post("baseInfo/updateBaseInfoPartTunnel",
											$("#baseInfoPartTunnelUpdateForm").serializeArray(),function(data){
										closeWaitingWindow();
										$.messager.alert('提示',data.mes,'info');
										$('#baseInfoPartTunnelTable').datagrid('reload');	
										closeWindow();
										return;
									});
								}
							}
						});
					}			
				}
			});
		}});
		}
	</script>
    
	<form id="baseInfoPartTunnelUpdateForm" enctype="multipart/form-data"  method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="fileId">
		<input type="hidden" name="photos">
		<table>
		<tr>
					<tr>
				<td width="15%" align="right">隧道名称：</td>
				<td width="35%" align="left">
					<input  name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				
				<td width="15%" align="right">行别：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="Stream"
					name="stream" style="width: 90%;" data-options="editable:false,required:false" >
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railStreamList}" var="railStreamListTemp">
							<option value="${railStreamListTemp.dictData}">${railStreamListTemp.dictName}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">隧道编号：</td>
				<td width="35%" align="left">
					<input name="number" class="easyui-validatebox textbox" 
					data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" 
					style="height:20px;font-size:12px;width:90%;"
					data-options="required:false,validType:['mobileAndTel','mobile']">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">中心里程：</td>
				<td width="35%" align="left">
					<input name="middleKM" class="easyui-validatebox textbox" validType="number" 
					style="height:20px;font-size:12px;width:20%;" > &nbspK&nbsp
					<input name="middleM" class="easyui-validatebox textbox" validType="number"
					style="height:20px;font-size:12px;width:30%;" >&nbspM
				</td>
				<td width="15%" align="right">参考铁路线：</td>
				<td width="35%" align="left">
					<input id="railName" name="railName" type="hidden">
					<select id="railId" class="easyui-combobox" name="railId" style="width:90%;"
					 data-options="editable:false,required:false">							
				        <option value="" disabled selected>请选择</option>
				        <c:forEach items="${railsInfo}" var="railInfoTmp">
					    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
				        </c:forEach>
				   	</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">长度(m)：</td>
				<td width="35%" align="left">
					<input  name="length" class="easyui-validatebox textbox" 
					data-options="required:false" style="height:20px;font-size:12px;width:90%;" validType="number">
				</td>

			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox"  
					style="height:20px;font-size:12px;width:90%;" data-options="required:true"validType="number">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox"  
					style="height:20px;font-size:12px;width:90%;"data-options="required:true" validType="number">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">隧道入口：</td>
				<td width="35%" align="left">
					<input id="photosName" value="${photosName }" class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
				<td width="15%" align="right">隧道出口：</td>
				<td width="35%" align="left">
					<input id="fileName" value="${filePhotosName }"class="easyui-filebox" name="uploadFileTwo" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="65" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateBaseInfoPartTunnel();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>       
			</tr>
		</table>
	</form>
</body>
