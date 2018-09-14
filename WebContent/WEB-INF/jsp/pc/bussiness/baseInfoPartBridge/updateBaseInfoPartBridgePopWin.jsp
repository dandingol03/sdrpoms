<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateBaseInfoPartBridge(){
			var r = $('#baseInfoPartBridgeUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("#lng").val(),lat:$("#lat").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoPartBridgeUpdateForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						console.log('===data==>', ps);
    						var addNumber=$("#baseInfoPartBridgeUpdateForm").serializeArray();
    						addNumber.splice(1,1);
    						$.post("baseInfo/updateBaseInfoPartBridge"+ps,addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartBridgeTable').datagrid('reload');
    						}); 
    					} else {
    						var addNumber=$("#baseInfoPartBridgeUpdateForm").serializeArray();
    						$.post("baseInfo/updateBaseInfoPartBridge",addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartBridgeTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoPartBridgeTable').datagrid('reload');
    	}
			});
		}
	</script>
    <!--弹出的更新框结构 -->
	<form id="baseInfoPartBridgeUpdateForm" enctype="multipart/form-data" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="fileId">
		<table>
			<tr>
				<td width="15%" align="right">桥梁名称：</td>
				<td width="35%" align="left">
					<input  name="name" class="easyui-validatebox textbox"  data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">桥梁类别：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="classify"
					name="classify" style="width: 90%;" data-options="required:false,editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${classifyList}" var="classifyListTemp">
							<option value="${classifyListTemp.dictData}">${classifyListTemp.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">桥梁编号：</td>
				<td width="35%" align="left">
					<input name="number" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">隶属铁路局：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="railBureau"
					name="railBureau" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railBureauList}" var="railBureauListTemp">
							<option value="${railBureauListTemp.dictData}">${railBureauListTemp.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">中心里程：</td>
				<td width="35%" align="left">
					<input name="middleKM" class="easyui-validatebox textbox" validType="integ" 
					style="height:20px;font-size:12px;width:20%;" > &nbspK&nbsp
					<input name="middleM" class="easyui-validatebox textbox" validType="integ" 
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
					<input name="length" class="easyui-validatebox textbox" ValidType="number" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">行别：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="stream"
					name=stream style="width: 90%;" data-options="required:false,editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railStreamList}" var="railStreamListTemp">
							<option value="${railStreamListTemp.dictData}">${railStreamListTemp.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="required:false,validType:['mobileAndTel','mobile']" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">守护情况：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="guardStatus"
						name="guardStatus" style="width: 90%;" data-options="required:true,editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railGuardStatusList}" var="railGuardStatusListTemp">
							<option value="${railGuardStatusListTemp.dictData}">${railGuardStatusListTemp.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">设计用途：</td>
				<td width="35%" align="left">
					<input name="purpose" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">是否铁路公路共用：</td>
				<td width="35%" align="left">
					<select id="isShared" class="easyui-combobox" data-options="editable:false" name="isShared" style="height:20px;font-size:12px;width:90%;">
						 <option value="" disabled selected>请选择</option>    
   						 <option value="1">是</option>   
   						 <option value="0">否</option>       
					</select> 
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">桥梁照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${photosName}" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
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
					<input id="lng" name="lng" class="easyui-validatebox textbox" ValidType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input id="lat"  name="lat" class="easyui-validatebox textbox" ValidType="number" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark"  cols="50" rows="4"></textarea>
				</td>
			</tr>
			<tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateBaseInfoPartBridge();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
