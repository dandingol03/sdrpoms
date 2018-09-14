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
		function updateBaseInfoPartCulvert(){
			var r = $('#baseInfoPartCulvertUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoPartCulvertUpdateForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						console.log('===data==>', ps);
    						var addNumber=$("#baseInfoPartCulvertUpdateForm").serializeArray();
    						addNumber.splice(1,1);
    						$.post("baseInfo/updatePartCulvert"+ps,addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartCulvertTable').datagrid('reload');
    						}); 
    					} else {
    						var addNumber=$("#baseInfoPartCulvertUpdateForm").serializeArray();
    						$.post("baseInfo/updatePartCulvert",addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartCulvertTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoPartCulvertTable').datagrid('reload');
    	}
			});
		}
	</script>
    
	<form id="baseInfoPartCulvertUpdateForm" enctype="multipart/form-data" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="fileId">
		<table>
		<tr>
				<td width="15%" align="right">涵洞名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				
				<td width="15%" align="right">涵洞分类：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="addClassify" name="classify" style="width:90%;"  data-options="editable:false">							
		   				<option value="" disabled selected>请选择</option>
		    			<c:forEach items="${classifyList}" var="classifyListTemp">
							<option value="${classifyListTemp.dictData}">${classifyListTemp.dictName}</option>
		    			</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">涵洞编号：</td>
				<td width="35%" align="left">
					<input  name="number" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">长度(m)：</td>
				<td width="35%" align="left">
					<input  name="length" class="easyui-validatebox textbox" data-options="" validType="number"
					style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">中心里程：</td>
				<td width="35%" align="left">
					<input name="middleKM" class="easyui-validatebox textbox" validType="integ" data-options=""
					style="height:20px;font-size:12px;width:20%;" >&nbsp;K&nbsp;
					<input name="middleM" class="easyui-validatebox textbox" validType="integ" data-options=""
					style="height:20px;font-size:12px;width:30%;" >&nbsp;M
				</td>
				<td width="15%" align="right">参考铁路线：</td>
				<td width="35%" align="left">
					<input id="railName" name="railName" type="hidden">
					<select id="railId" class="easyui-combobox" name="railId" style="width:90%;"
					 data-options="editable:false">							
				        <option value="" disabled selected>请选择</option>
				        <c:forEach items="${railsInfo}" var="railInfoTmp">
				    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
				        </c:forEach>
				   	</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">功能：</td>
				<td width="35%" align="left">
					<input  name="culvertFunction" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">行别：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="addStream" name="stream" style="width:90%;" data-options="editable:false">							
					    <option value="" disabled selected>请选择</option>
					    <c:forEach items="${streamList}" var="streamListTemp">
							<option value="${streamListTemp.dictData}">${streamListTemp.dictName}</option>
					    </c:forEach>
					</select>
				</td>
			</tr>
			<tr>				
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="validType:['mobileAndTel','mobile']" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">守护情况：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="addGuardStatus" name="guardStatus" style="width:90%;" data-options="editable:false">							
					    <option value="" disabled selected>请选择</option>
					    <c:forEach items="${guardStatusList}" var="guardStatusListTemp">
							<option value="${guardStatusListTemp.dictData}">${guardStatusListTemp.dictName}</option>
					    </c:forEach>
					</select>
				</td>	
			</tr>
			<tr>
				<td width="15%" align="right">宽度(m)：</td>
				<td width="35%" align="left">
					<input  name="width" class="easyui-validatebox textbox" data-options="" validType="number" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">高度(m)：</td>
				<td width="35%" align="left">
					<input  name="height" class="easyui-validatebox textbox" data-options="" validType="number" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">内径(m)：</td>
				<td width="35%" align="left">
					<input  name="inradium" class="easyui-validatebox textbox" data-options="" validType="number" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">穿跨形式：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="crossSpan"
					name="crossSpan" style="width: 90%;" data-options="required:false,editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${crossSpanList}" var="crossSpanListTemp">
							<option value="${crossSpanListTemp.dictData}">${crossSpanListTemp.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox" validType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox" validType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">涵洞照片：</td>
				<td width="35%" align="left">
					<input id="photosName" value="${photosName }"class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
				<td width="15%" align="right">是否积水：</td>
				<td width="35%" align="left">
					<input type="radio" name="isSeeper" value="1" checked/>是&nbsp;&nbsp;
					<input type="radio" name="isSeeper" value="0" />否
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="50" rows="4"></textarea>
				</td>
			</tr>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateBaseInfoPartCulvert();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
