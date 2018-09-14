<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateBaseInfoDefenceGuardStation(){
			var r = $('#baseInfoDefenceGuardStationUpdateForm').form('validate');
			
			if(!r) {
				return false;
			}
			
			$.post("baseInfo/checkGuardOrgId", $("#baseInfoDefenceGuardStationUpdateForm").serializeArray(),function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
    				showWaitingWindow();
    	    		$('#baseInfoDefenceGuardStationUpdateForm').form('submit',{
    	    			url : 'file/fileUpLoad',
    	    			onSubmit : function(pa) {},
    	    			success : function(data) {
    	    				if (typeof (data) != 'undefined') {
    	    					console.log('===data==>', data);
    	    					if (data != "-1" && data != "") {
    	    						var ps = "?photos="+data;
    	    						console.log('===data==>', ps);
    	    						var addNumber=$("#baseInfoDefenceGuardStationUpdateForm").serializeArray();
    	    						addNumber.splice(1,1);
    	    						$.post("baseInfo/updateBaseInfoDefenceGuardStation"+ps,addNumber,function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoDefenceGuardStationTable').datagrid('reload');
    	    						}); 
    	    					} else {
    	    						var addNumber=$("#baseInfoDefenceGuardStationUpdateForm").serializeArray();
    	    						$.post("baseInfo/updateBaseInfoDefenceGuardStation",addNumber,function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoDefenceGuardStationTable').datagrid('reload');
    	    						}); 
    	    					}
    	    				} else {
    	    					$.messager.alert('提示', "文件上传失败", 'info');
    	    				}
    	    			closeWaitingWindow();
    	    			}
    	    		});
    	    		closeWindow();
    	    		$('#baseInfoDefenceGuardStationTable').datagrid('reload');
    			}
    		});
			
		}
	</script>
    
	<form id="baseInfoDefenceGuardStationUpdateForm"enctype="multipart/form-data"method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="photos" >
		<table>
			<tr>
				<td width="15%" align="right">护路工作站名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input  name="adress" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">人数：</td>
				<td width="35%" align="left">
					<input  name="peopleNum" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;" validType="integ">
				</td>
				<td width="15%" align="right">勤务模式：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="serviceModeTwo" name="serviceMode" data-options="editable:false" style="width:91%;height:23px;" >							
					        <option value="" >请选择</option>
					        <c:forEach items="${serviceMode}" var="serviceModeListTemp">
						    	<option value="${serviceModeListTemp.dictData}">${serviceModeListTemp.dictName}</option>
					        </c:forEach>
					  </select>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">巡防范围：</td>
				<td width="35%" align="left">
					<input  name="patrolRange" class="easyui-validatebox textbox" style="height:20px;font-size:12px;width:90%;">
				</td>
			
				<td width="15%" align="right">守护目标：</td>
				<td width="35%" align="left">
					<input  name="guardTarget" class="easyui-validatebox textbox"  style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">巡防力量：</td>
				<td width="35%" align="left">
					<input  name="patrolTeam" class="easyui-validatebox textbox" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">市验收挂牌：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" name="isAccept" id="isAccept2"
							style="width:91%;" data-options="editable:false">							
						<option value="" >请选择</option>
						<c:forEach items="${isAcceptList}" var="isAcceptList">
						   <option value="${isAcceptList.dictData}">${isAcceptList.dictName}</option>
						</c:forEach>
					</select> 
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
					<input  name="lat" class="easyui-validatebox textbox"  data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="number">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">照片：</td>
				<td width="35%" align="left">
				<input name="uploadFile" id="photosNames" value="${photosName}" class="easyui-filebox" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true"  style="height:20px;font-size:12px;width:90%;">
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
					<a onclick="updateBaseInfoDefenceGuardStation();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
		</table>
	</form>
</body>
