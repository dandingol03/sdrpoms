<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateBaseInfoDefencePropaganda(){
			if($("#proTime2").val()==null||$("#proTime2").val()==""){
				$.messager.alert('提示',"请填写布建时间",'info');
				return;
			}
			if(null==$("#proType2").combobox("getValues")||$("#proType2").combobox("getValues")==""){
				$.messager.alert('提示',"请选择布建形式",'info');
				return;
			}
			var r = $('#baseInfoDefencePropagandaUpdateForm').form('validate');
    		if(!r) {
    			return false;
    		}
    		$.post("baseInfo/checkProOrgId", $("#baseInfoDefencePropagandaUpdateForm").serializeArray(),function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
    				showWaitingWindow();
    	    		$('#baseInfoDefencePropagandaUpdateForm').form('submit',{
    	    			url : 'file/fileUpLoad',
    	    			onSubmit : function(pa) {},
    	    			success : function(data) {
    	    				if (typeof (data) != 'undefined') {
    	    					console.log('===data==>', data);
    	    					if (data != "-1" && data != "") {
    	    						var ps = "?photos="+data;
    	    						console.log('===data==>', ps);
    	    					var	baseInfoDefencePropaganda=$("#baseInfoDefencePropagandaUpdateForm").serializeArray();
    	    					baseInfoDefencePropaganda.splice(1,1);//photos
    	    						$.post("baseInfo/updateBaseInfoDefencePropaganda"+ps,baseInfoDefencePropaganda,function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoDefencePropagandaTable').datagrid('reload');
    	    						}); 
    	    					} else {
    	    						$.post("baseInfo/updateBaseInfoDefencePropaganda",
    	    								$("#baseInfoDefencePropagandaUpdateForm").serializeArray(),function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoDefencePropagandaTable').datagrid('reload');
    	    						}); 
    	    					}
    	    				} else {
    	    					$.messager.alert('提示', "文件上传失败", 'info');
    	    				}
    	    			closeWaitingWindow();
    	    			}
    	    		});
    	    		closeWindow();
    	    		$('#baseInfoDefencePropagandaTable').datagrid('reload');
    			}
    		});
		}
	</script>
	<form id="baseInfoDefencePropagandaUpdateForm" enctype="multipart/form-data" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		 <input type="hidden" name="photos" >
		<table>
			<tr>
				<td width="15%" align="right">护路宣传点名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;" >
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">护路宣传点编号：</td>
				<td width="35%" align="left">
					<input  name="number" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;" >
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input  name="adress" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
			<td width="15%" align="right">图片：</td>
				<td width="35%" align="left">
					<input id="photosName"  name="uploadFile" value="${photosName}" class="easyui-filebox" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true"  style="height:23px;font-size:12px;width:91%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">布建时间：</td> 
				<td width="35%" align="left">
					<input id="proTime2" name="proTime" class="Wdate" type="text" style="width:90%;" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					<a style="color:red">*</a> 
				</td>
				<td width="15%" align="right">布建形式：</td>
				<td width="35%" align="left">
					 <select class="easyui-combobox" name="proType" id="proType2" style="width: 90%;" data-options="editable:false,required:true">
							<option value="" >请选择</option>
							<c:forEach items="${proTypeList}" var="proTypeListTemp">
								<option value="${proTypeListTemp.dictData}">${proTypeListTemp.dictName}</option>
							</c:forEach>
				</select>
					<a style="color:red">*</a> 
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">建管单位：</td>
				<td width="35%" align="left">
					<input  name="constructionUnit" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;" >
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
				<td width="15%" align="right">内容：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="content" cols="50" rows="4"></textarea>
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
					<a onclick="updateBaseInfoDefencePropaganda();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>