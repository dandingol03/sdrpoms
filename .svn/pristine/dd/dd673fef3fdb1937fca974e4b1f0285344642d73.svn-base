<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<!-- 隐患上报修改 -->
    <script type="text/javascript">
    jQuery(function($){
    	//加载机构树
		$(document).ready(function() {
					var jqueryObj=$("#orgIdD");
			 		makeOrgTree(jqueryObj);
				});
			});
    	//非空校验
		function updateDangerInfoTask(){
			var r = $('#dangerInfoTaskUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("org/checkOrgId",{lng:$("input[name='lng']").val(),lat:$("input[name='lat']").val()},function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
			showWaitingWindow();
			$('#dangerInfoTaskUpdateForm').form('submit',{
				url : 'file/fileUpLoad',
				onSubmit : function(pa) {},
				success : function(data) {
					if (typeof (data) != 'undefined') {
						console.log('===data==>', data);
						if (data != "-1" && data != "") {
							var ps = "?photos="+data;
							console.log('===data==>', ps);
						var	dangerInfoTask=$("#dangerInfoTaskUpdateForm").serializeArray();
						dangerInfoTask.splice(1,1);//photos
							$.post("patrol/updateDangerInfo"+ps,dangerInfoTask,function(data){
								$.messager.alert('提示',data.mes,'info');
								closeWindow();
								$('#dangerInfoTaskTable').datagrid('reload');
							}); 
						} else {
							$.post("patrol/updateDangerInfo",
									$("#dangerInfoTaskUpdateForm").serializeArray(),function(data){
								$.messager.alert('提示',data.mes,'info');
								closeWindow();
								$('#dangerInfoTaskTable').datagrid('reload');
							}); 
						}
					} else {
						$.messager.alert('提示', "文件上传失败", 'info');
					}
				closeWaitingWindow();
				}
			});
			closeWindow();
			$('#dangerInfoTaskTable').datagrid('reload');
		}});
		}
	</script>
       <script type="text/javascript">
    </script>
	<form id="dangerInfoTaskUpdateForm" enctype="multipart/form-data"method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<input type="hidden" name="photos" >
		<input name="handleStatus" type="hidden" value="01">
		<input name="type" type="hidden" value="1">
		<input type="hidden" name="reportUserId"  value="${sessionScope.userId}">
		<table>
			<tr>
				<td width="15%" align="right">隐患名称：</td>
				<td width="35%" align="left">
					<input name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
				<td width="15%" align="right">上报时间：</td>
				<td width="35%" align="left">
				<input id="reportTime" name="reportTime" required="required" class="easyui-datebox" >	
				</td>
			</tr>
			<tr>
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
				<td width="15%" align="right">中心里程：</td>
				<td width="35%" align="left">
					<input name="middleKM" class="easyui-validatebox textbox" validType="number" data-options="required:true"
					style="height:20px;font-size:12px;width:39%;" > &nbsp;K&nbsp;
					<input name="middleM" class="easyui-validatebox textbox" validType="number" data-options="required:true"
					style="height:20px;font-size:12px;width:39%;" >&nbsp;M
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input id="" type="text" name="telephone" class="easyui-textbox " 
					data-options="required:false,validType:['mobileAndTel','mobile']" style="width:90%;">
				</td>
				<td width="15%" align="right">隐患分类：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="dangerType"
					name="dangerType" style="width: 90%;" data-options="editable:false,required:true">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${dangerTypeList}" var="dangerTypeListTemp">
							<option value="${dangerTypeListTemp.dictData}">${dangerTypeListTemp.dictName}</option>
						</c:forEach>
				</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">经度：</td>
				<td width="35%" align="left">
					<input id="" type="text" name="lng" class="easyui-textbox " 
					data-options="required:true" style="width:90%;">
				</td>
				<td width="15%" align="right">纬度：</td>
				<td width="35%" align="left">
					<input id="" type="text" name="lat" class="easyui-textbox " 
					data-options="required:true" style="width:90%;">
				</td>
			</tr>
			<tr> 
				<td width="15%" align="right">负责人：</td>
				<td width="35%" align="left">
					<input id="" type="text" name="charger" class="easyui-textbox " 
					data-options="required:false" style="width:90%;">
				</td>
				<td width="15%" align="right">照片：</td>
				<td width="35%" align="left">
					<input id="photosName" name="uploadFile" value="${photosName}" class="easyui-filebox" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true"  style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">隐患描述：</td>
				<td colspan="3" width="85%" align="left">
					<textarea class="easyui-validatebox textbox" name="description" cols="50" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateDangerInfoTask();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
