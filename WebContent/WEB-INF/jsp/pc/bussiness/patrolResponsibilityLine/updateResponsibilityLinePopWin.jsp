<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
    var tempStart;
    var tempEnd;
   	var startKM = $('#startKM')
    var startM =$('#startM')
    var endKM =$('#endKM')
    var endM =$('#endM')
		function updateResponsibilityLine(){
			var r = $('#responsibilityLineUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			if(null==$("#railId").combobox("getValues")||$("#railId").combobox("getValues")==""){
				$.messager.alert('提示',"请选择铁路线!",'info');
				return;
			}
			var start= parseInt(startKM.val()*1000) +parseInt( startM.val());
			var end= parseInt(endKM.val()*1000) +parseInt(endM.val());
			if(parseInt(tempStart) > start || parseInt(tempEnd) < end){
				var alt="请输入范围内数据!";
				$.messager.alert('提示',alt,'info');
				return false;
			}
			//更新巡防人基本信息
		/* 	$.post("baseInfo/updateResponsibilityLine", $("#responsibilityLineUpdateForm").serializeArray(),function(data){
				closeThirdWindow();
				$('#responsibilityLineTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			}); */
			//
			showWaitingWindow();
    		$('#responsibilityLineUpdateForm').form('submit',{
    			url : 'file/fileUpLoad',
    			onSubmit : function(pa) {},
    			success : function(data) {
    				if (typeof (data) != 'undefined') {
    					if (data != "-1" && data != "") {
    						var ps = "?fileId="+data;
    						var addNumber=$("#responsibilityLineUpdateForm").serializeArray();
    						addNumber.splice(1,1);
    						$.post("baseInfo/updateResponsibilityLine"+ps,addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeThirdWindow();
    							$('#responsibilityLineTable').datagrid('reload');
    						}); 
    					} else {
    						var addNumber=$("#responsibilityLineUpdateForm").serializeArray();
    						$.post("baseInfo/updateResponsibilityLine",addNumber,function(data){
    							$.messager.alert('提示',data.mes,'info');
    							closeThirdWindow();
    							$('#responsibilityLineTable').datagrid('reload');
    						}); 
    					}
    				} else {
    					$.messager.alert('提示', "文件上传失败", 'info');
    				}
    			closeWaitingWindow();
    			}
    		});
    		closeThirdWindow();
    		$('#responsibilityLineTable').datagrid('reload');
			//
		}
		$("#railId").combobox({  
			onChange: function () {
	           	var railId = $("#railId").combobox('getValue');  
				$.post("baseInfo/findRail",{railId:railId},function(data){
					//存在null值
					if(data.startStr!=undefined && data.endStr != undefined){
						$("#textUpdate").html("范围："+data.startStr+" 至   "+data.endStr);
					}
					tempStart=parseInt(data.startKM)*1000+parseInt(data.startM) ;
					tempEnd=parseInt( data.endKM)*1000+parseInt(data.endM) ;
				});
	       	}  
	   	})  
	</script>
    
	<form id="responsibilityLineUpdateForm" method="post" enctype="multipart/form-data"  class="operationPage">
					<input id="id" type="hidden" name="id">
					<input type="hidden" name="fileId" >
					<input name="userId" hidden="hidden">
		 <table>
			<tr>
				<td width="15%" align="right">巡防人：</td>
				<td width="35%" align="left">
				<select class="easyui-combobox" id="" disabled="disabled"
					name="userName" style="width: 91%;" data-options="editable:false,required:false">
						<option value="" disabled selected>请选择</option>
						<c:forEach items="${queryPers}" var="queryPersListTemp">
							<option value="${queryPersListTemp.id}">${queryPersListTemp.name}</option>
						</c:forEach>
				</select>
				</td>
				<td width="15%" align="right">铁路线：</td>
				<td width="35%" align="left">
					<select id="railId" class="easyui-combobox" name="railId" style="width:90%;" onchange="getRailKilometer();">							
				        <option value="" disabled selected>请选择</option>
				        <c:forEach items="${railsInfo}" var="railInfoTmp">
				    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
				        </c:forEach>
				   	</select>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right"></td>
				<td width="35%" align="left">
					<h5 id="textUpdate"></h5>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">起点里程：</td>
				<td width="35%" align="left">
					<input id ="startKM" name="startKM" class="easyui-validatebox textbox" data-options="required:true"validType="number" 
					style="height:20px;font-size:12px;width:20%;" > &nbsp;K&nbsp;
					<input id ="startM" name="startM" class="easyui-validatebox textbox" data-options="required:true"validType="number" 
					style="height:20px;font-size:12px;width:30%;" >&nbsp;M
				</td>
				<td width="15%" align="right">终点里程：</td>
				<td width="35%" align="left">
					<input id="endKM" name="endKM" class="easyui-validatebox textbox" data-options="required:true"validType="number" 
					style="height:20px;font-size:12px;width:20%;" > &nbsp;K&nbsp;
					<input id="endM"  name="endM" class="easyui-validatebox textbox" data-options="required:true"validType="number" 
					style="height:20px;font-size:12px;width:30%;" >&nbsp;M
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">区段照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" value="${filesName}" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<a onclick="closeThirdWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateResponsibilityLine();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
