<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
    
		//添加车站信息
		function addBaseInfoPartStation(){
			//车站等级不能为空
			if(null==$("#level").combobox("getValues")||$("#level").combobox("getValues")==""){
				$.messager.alert('提示',"请选择车站等级",'info');
				return;
			}
			//车站性质不能为空
			if(null==$("#nature").combobox("getValues")||$("#nature").combobox("getValues")==""){
				$.messager.alert('提示',"请选择车站性质",'info');
				return;
			}
			//是否高铁站不能为空
			if(null==$("#isHighspeed").combobox("getValues")||$("#isHighspeed").combobox("getValues")==""){
				$.messager.alert('提示',"请选择是否高铁站",'info');
				return;
			}
			//验证字段
			var r = $('#baseInfoPartStationAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
    		$('#baseInfoPartStationAddForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					console.log('===data==>', data);
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						console.log('===data==>', ps);
    						$.post("baseInfo/addBaseInfoPartStation"+ps,
    								$("#baseInfoPartStationAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartStationTable').datagrid('reload');
    						}); 
    					} else {
    						$.post("baseInfo/addBaseInfoPartStation",
    								$("#baseInfoPartStationAddForm").serializeArray(),function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeWindow();
    							$('#baseInfoPartStationTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeWindow();
    		$('#baseInfoPartStationTable').datagrid('reload');
		}
			});
		}
	</script>
  	
  	<!-- 验证使用jquery-validation -->
  	<!-- 弹出添加框 -->
	<form id="baseInfoPartStationAddForm" enctype="multipart/form-data" method="post" class="operationPage">
		<table>
		<tr>
				<td width="15%" align="right">车站名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">车站等级：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="level"
					name="level" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${levelList}" var="levelListTemp">
							<option value="${levelListTemp.dictData}">${levelListTemp.dictName}</option>
						</c:forEach>
					</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">车站编号：</td>
				<td width="35%" align="left">
					<input name="number" class="easyui-validatebox textbox" data-options="required:false" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">车站性质：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="nature"
					name="nature" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${natureList}" var="natureListTemp">
							<option value="${natureListTemp.dictData}">${natureListTemp.dictName}</option>
						</c:forEach>
					</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input  name="telephone" class="easyui-validatebox textbox" data-options="required:false,validType:['mobileAndTel','mobile']"  style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">是否高铁站：</td>
				<td width="35%" align="left">
					<select id="isHighspeed" class="easyui-combobox" name="isHighspeed" 
					style="height:20px;font-size:12px;width:90%;" data-options="editable:false">
						 <option value="" disabled selected>请选择</option>    
   						 <option value="1">是</option>   
   						 <option value="0">否</option>       
					</select> 
					<a style="color:red">*</a>
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
					 data-options="editable:false,required:true">							
				        <option value="" disabled selected>请选择</option>
				        <c:forEach items="${railsInfo}" var="railInfoTmp">
					    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
				        </c:forEach>
				   	</select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">车站照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
				<td width="15%" align="right">运营状态：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="state"
					name="state" style="width: 90%;" data-options="editable:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${stateList}" var="stateListTemp">
							<option value="${stateListTemp.dictData}">${stateListTemp.dictName}</option>
						</c:forEach>
					</select>
				</td>
			<tr>
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
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input  name="lng" class="easyui-validatebox textbox" ValidType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
				<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input  name="lat" class="easyui-validatebox textbox" ValidType="number" data-options="required:true"style="height:20px;font-size:12px;width:90%;">
				<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">备注：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="remark" cols="100" rows="5"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addBaseInfoPartStation();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
