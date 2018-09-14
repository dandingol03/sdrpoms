<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
		function updateOaInfoAddressBook(){
			var r = $('#oaInfoAddressBookUpdateForm').form('validate');
			if(!r) {
				return false;
			}
			//更新通讯录基本信息
			$.post("oaInfo/updateAddressBook", $("#oaInfoAddressBookUpdateForm").serializeArray(),function(data){
				closeWindow();
				$('#oaInfoAddressBookTable').datagrid('reload');  
				$.messager.alert('提示',data.mes,'info');
			});
		}
		
		$('#userIdUpdate').combobox({
            onSelect:function(rec){
            	//获取下拉中的数据
            	var userId=rec.value;
		    	$.ajax({
		    		url:"${ctx}/oaInfo/findbyUserId?userId="+userId,
		    		type:'POST',
		    		dataType:'json',
		    		async: false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			},
		    		success:function(data){
		    			var jqueryObj=$("#addressBookorgs-update");
				 		makeOrgTree(jqueryObj,data.orgName);
				 		$("#mail").val(data.mail);
		    			$("#telephone").val(data.telephone);
		    			$("#QW").val(data.QW);
		    			$("#duty").val(data.duty);   
		    		}
		    	});
            }
	    });
	</script>
    
	<form id="oaInfoAddressBookUpdateForm" method="post" class="operationPage">
		<input id="id" type="hidden" name="id">
		<table>	
           <tr>
        		<td width="15%" align="right">姓名：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox"  size="2" id="userIdUpdate"
					name="userId" style="width: 90%;" data-options="editable:false,required:true">
						<c:forEach items="${queryPers}" var="queryPersListTemp">
							<option value="${queryPersListTemp.id}">${queryPersListTemp.name}</option>
						</c:forEach>
				</select>
					<a style="color:red">*</a>
				</td>
           		<td width="15%" align="right">单位名称：</td>
				<td width="35%" align="left">
					<input  name="unitName" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
            </tr>
			<tr>
				<td width="15%" align="right">地址：</td>
				<td width="35%" align="left">
					<input  name="adress" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
				</td>
           	    <td width="15%" align="right">邮编：</td>
				<td width="35%" align="left">
					<input  name="postalcode" class="easyui-validatebox textbox" data-options="" style="height:20px;font-size:12px;width:90%;"  validType="ZIP">
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">职务：</td>
				<td width="35%" align="left">
					<input id="duty"  class="easyui-validatebox textbox" data-options="editable:false,required:false" style="height:20px;font-size:12px;width:90%;">
					<a style="color:blue;">*</a>
				</td>
           	    <td width="15%" align="right">联系电话：</td>
				<td width="35%" align="left">
					<input id="telephone"  class="easyui-validatebox textbox" data-options="editable:false,required:false" style="height:20px;font-size:12px;width:90%;">
					<a style="color:blue;">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">邮箱：</td>
				<td width="35%" align="left">
					 <input id="mail"  class="easyui-validatebox textbox" data-options="editable:false,required:false" style="height:20px;font-size:12px;width:90%;">
					<a style="color:blue;">*</a>
				</td>
           	    <td width="15%" align="right">qq或微信：</td>
				<td width="35%" align="left">
					<input id="QW"  class="easyui-validatebox textbox" data-options="editable:false,required:false" style="height:20px;font-size:12px;width:90%;">
					<a style="color:blue;">*</a>
				</td>
			</tr>
			<tr>
           	    <td width="15%" align="right">所属机构：</td>
				<td width="35%" align="left">
					<input id="addressBookorgs-update" disabled="disabled" class="easyui-validatebox textbox" data-options="editable:false,required:false" 
					style="width:90%;" >
					<a style="color:blue;">*</a>
				</td>
				 <td colspan="2" align="center">
           	 	 	<a style="color:blue;">蓝色 * 为用户信息,需到用户管理修改! </a>
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
					<a onclick="updateOaInfoAddressBook();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
