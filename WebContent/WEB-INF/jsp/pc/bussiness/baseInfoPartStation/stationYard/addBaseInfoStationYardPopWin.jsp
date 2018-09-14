<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
	    jQuery(function($){
	  		$(document).ready(function() {
	  			
	  		});
		});
    
		//添加站场信息
		function addBaseInfoStationYard(){
			//验证字段
			var r = $('#baseInfoStationYardAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoStationYardAddForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						console.log('===data==>', ps);
    						$.post("baseInfo/addBaseInfoStationYard"+ps,
    								$("#baseInfoStationYardAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeSecondWindow();
    							$('#baseInfoStationYardTable').datagrid('reload');
    						}); 
    					} else {
    						$.post("baseInfo/addBaseInfoStationYard",
    								$("#baseInfoStationYardAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeSecondWindow();
    							$('#baseInfoStationYardTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeSecondWindow();
    		$('#baseInfoStationYardTable').datagrid('reload');
		}
			});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
  	<!-- 弹出添加框 -->
	<form id="baseInfoStationYardAddForm" enctype="multipart/form-data" method="post" class="operationPage">
		<input type="hidden" name="stationId">
		<table>
			<tr>
				<td width="15%" align="right">站场名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">站场照片：</td>
				<td width="30%" align="left">
					<input class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox" ValidType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
				<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox" ValidType="number" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
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
					<a onclick="closeSecondWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addBaseInfoStationYard();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
