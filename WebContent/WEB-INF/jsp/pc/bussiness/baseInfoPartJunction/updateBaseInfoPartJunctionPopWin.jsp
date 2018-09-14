<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateBaseInfoPartJunction(){
			var r = $('#baseInfoPartJunctionUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoPartJunctionUpdateForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						console.log('===data==>', ps);
    						var addNumber=$("#baseInfoPartJunctionUpdateForm").serializeArray();
    						addNumber.splice(1,1);
    						$.post("baseInfo/updateBaseInfoPartJunction"+ps,addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartJunctionTable').datagrid('reload');
    						}); 
    					} else {
    						var addNumber=$("#baseInfoPartJunctionUpdateForm").serializeArray();
    						$.post("baseInfo/updateBaseInfoPartJunction",addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartJunctionTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoPartJunctionTable').datagrid('reload');
		}
			});
		}
	</script>
    
	<form id="baseInfoPartJunctionUpdateForm"  enctype="multipart/form-data" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="fileId">
		<table>
		<tr>
				<td width="15%" align="right">道口名称：</td>
				<td width="35%" align="left">
					<input  name="name" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;" >
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">道路类别：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="RoadClassify"
					name="roadClassify" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${roadClassifyList}" var="roadClassifyList">
							<option value="${roadClassifyList.dictData}">${roadClassifyList.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">道口编号：</td>
				<td width="35%" align="left">
					<input validType="number" name=number class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">行别：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="Stream"
					name="stream" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railStreamList}" var="railStreamListTemp">
							<option value="${railStreamListTemp.dictData}">${railStreamListTemp.dictName}</option>
						</c:forEach>
				</select>
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
					<select id="RailId" class="easyui-combobox" name="railId" style="width:90%;"
					 data-options="editable:false">							
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
					style="height:20px;font-size:12px;width:90%;" validType="number">
				</td>
				<td width="15%" align="right">是否有无限高：</td>
				<td width="35%" align="left">
					<select id="IsHeightLimit" name=isHeightLimit class="easyui-combobox" 
					style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<option value="1">是</option>   
						<option value="0">否</option>   
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">铺面宽度：</td>
				<td width="35%" align="left">
					<input validType="number" name=width class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">运营状态：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="State"
					name="state" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${stateList}" var="stateList">
							<option value="${stateList.dictData}">${stateList.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">昼夜列车数：</td>
				<td width="35%" align="left">
					<input validType="number" name=trainNum class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">铺面材料：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="Material"
					name="material" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${materialList}" var="materialList">
							<option value="${materialList.dictData}">${materialList.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" 
					style="height:20px;font-size:12px;width:90%;" 
					data-options="required:false,validType:['mobileAndTel','mobile']">
				</td>
				<td width="15%" align="right">守护情况：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="GuardStatus"
					name="guardStatus" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${guardStreamList}" var="guardStreamList">
							<option value="${guardStreamList.dictData}">${guardStreamList.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">隶属铁路局(公司)：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="RailBureau"
					name="railBureau" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${railBureauList}" var="railBureauList">
							<option value="${railBureauList.dictData}">${railBureauList.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td width="15%" align="right">道口等级：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="Level"
					name="level" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${levelList}" var="levelList">
							<option value="${levelList.dictData}">${levelList.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox"  data-options="required:true"
					style="height:20px;font-size:12px;width:90%;" validType="number">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox"  data-options="required:true"
					style="height:20px;font-size:12px;width:90%;" validType="number">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">道口照片：</td>
				<td width="35%" align="left">
					<input id="photosName" value="${photosName }" class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="35" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="80" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateBaseInfoPartJunction();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
		
	</form>
</body>
