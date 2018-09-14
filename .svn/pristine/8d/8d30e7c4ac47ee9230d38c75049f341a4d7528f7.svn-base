<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		//添加广播警示柱信息
		function addBaseInfoDefenceBroadcast(){
			//验证字段
			if(null==$("#status2").combobox("getValues")||$("#status2").combobox("getValues")==""){
				$.messager.alert('提示',"请选择广播柱状态",'info');
				return;
			}
			if(null==$("#broType2").combobox("getValues")||$("#broType2").combobox("getValues")==""){
				$.messager.alert('提示',"请选择广播柱型号",'info');
				return;
			}
			var r = $('#baseInfoDefenceBroadcastAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("baseInfo/checkBroOrgId", $("#baseInfoDefenceBroadcastAddForm").serializeArray(),function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
    				showWaitingWindow();
    	    		$('#baseInfoDefenceBroadcastAddForm').form('submit',{
    	    			url : 'file/fileUpLoad',
    	    			onSubmit : function(pa) {},
    	    			success : function(data) {
    	    				if (typeof (data) != 'undefined') {
    	    					console.log('===data==>', data);
    	    					if (data != "-1" && data != "") {
    	    						var ps = "?photos="+data;
    	    						console.log('===data==>', ps);
    	    						$.post("baseInfo/addBaseInfoDefenceBroadcast"+ps,
    	    								$("#baseInfoDefenceBroadcastAddForm").serializeArray(),function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoDefenceBroadcastTable').datagrid('reload');
    	    						}); 
    	    					} else {
    	    						$.post("baseInfo/addBaseInfoDefenceBroadcast",
    	    								$("#baseInfoDefenceBroadcastAddForm").serializeArray(),function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoDefenceBroadcastTable').datagrid('reload');
    	    						}); 
    	    					}
    	    				} else {
    	    					$.messager.alert('提示', "文件上传失败", 'info');
    	    				}
    	    				closeWaitingWindow();
    	    			}
    	    		});
    	    		closeWindow();
    	    		$('#baseInfoDefenceBroadcastTable').datagrid('reload');
    			}
    		});
    		
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="baseInfoDefenceBroadcastAddForm" method="post" enctype="multipart/form-data"class="operationPage">
		<table>
			<tr>
				<td width="15%" align="right">广播警示柱名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">广播警示柱编号：</td>
				<td width="35%" align="left">
					<input  name="number" class="easyui-validatebox textbox" data-options="required:true"  style="height:20px;font-size:12px;width:90%;" >
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input  name="adress" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">广播警示柱状态：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" name="status" id="status2"
							style="width:91%;" data-options="editable:false">							
						<option value="" >请选择</option>
						<c:forEach items="${baseInfoDefenceBroadcastList}" var="baseInfoDefenceBroadcastList">
						   <option value="${baseInfoDefenceBroadcastList.dictData}">${baseInfoDefenceBroadcastList.dictName}</option>
						</c:forEach>
					</select> 
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">语音播报：</td>
				<td width="35%" align="left">
					<input  name="voicebroadcast" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">单点广播：</td>
				<td width="35%" align="left">
					<input  name="broadcasting" class="easyui-validatebox textbox" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">摄像头ID：</td>
				<td width="35%" align="left">
					<input  name="monitorId" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;" validType="number">
				</td>
				<td width="15%" align="right">广播警示柱型号：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" name="broType" id="broType2"
							style="width:91%;" data-options="editable:false">							
						<option value="" >请选择</option>
						<c:forEach items="${baseInfoDefenceBroadcastTypeList}" var="baseInfoDefenceBroadcastTypeList">
						   <option value="${baseInfoDefenceBroadcastTypeList.dictData}">${baseInfoDefenceBroadcastTypeList.dictName}</option>
						</c:forEach>
					</select> 
					<a style="color:red">*</a>
				</td>
				
			</tr>
			<tr>
				<td width="15%" align="right">负责人：</td>
				<td width="35%" align="left">
					<input  name="charger" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="validType:['mobileAndTel','mobile']" style="height:20px;font-size:12px;width:90%;" >
				</td>
			</tr>
			<tr>
			 <!-- validType="ip" -->
				<td width="15%" align="right">IP：</td>
				<td width="35%" align="left">
					<input  name="ip" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;" >
				</td>
				<td width="15%" align="right">端口：</td>
				<td width="35%" align="left">
					<input  name="port" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;" validType="number">
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
				<td width="15%" align="right">照片：</td>
				<td width="30%" align="left">
					<input class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
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
					<a onclick="addBaseInfoDefenceBroadcast();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
