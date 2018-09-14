<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
  	jQuery(function($){
		$(document).ready(function() {
	 		$("#upOrgName").combotree({
	 			url:"org/comTree?pid=0",
	 		    valueField: 'id',
	 		    textField: 'text',
	 		    required: true,
	 		    editable: false,
	 		    onBeforeExpand:function(node){
	 		    	$("#upOrgName").combotree("tree").tree("options").url="org/comTree?pid="+node.id;
	 		    },
	 		    onLoadSuccess: function (node, data) {
	 		    	$("#upOrgName").combotree('tree').tree("collapseAll");
	 		    	if("${orgDescPId}"!="0")
	 		    		$("#upOrgName").combotree("setValue",{id:"${orgPaId}",text:"${orgPaName}"});
	 		    }
	 		});
		});
	});

	//更新机构
	function updateOrg(){
		var upDesc = -1;
		var id = "9999";
		if("${orgDescPId}"!="0"){
			id = $("#orgDescId").val();			//机构id
			//[上级机构]没有修改
			var orgPNode = $('#upOrgName').combotree("getValue");	
			if(orgPNode=="${orgPaId}"){
				upDesc = 0;
			}else{
				upDesc = 1;
			}
		}else{
			upDesc=0;
		}
		var r = $('#orgUpForm').form('validate');
		if(!r) {
			return false;
		}
		//更新
		$.post("org/updateOrg?updesc="+upDesc+"&oldDescId="+id, $("#orgUpForm").serializeArray(),function(data){
			closeWindow();
			$('#orgTable').datagrid('reload');
			//机构更新后重新加载查询条件中的机构树
			$.messager.alert('提示',data.mes,'info');
		});
	}
	</script>
    
	<form id="orgUpForm" method="post" class="operationPage">
		<!-- 隐藏域 -->
		<input id="orgId" type="hidden" name="orgId">
		<input id="orgDescId" type="hidden" name="id">
		<input id="orgDescPId" type="hidden" name="pId">
		<table>
			<tr>
				<td width="25%" align="right">机构名称：</td>
				<td width="75%" align="left">
					<input name="orgName" class="easyui-validatebox textbox" 
					data-options="required:true" style="height:20px;font-size:12px;width:90%;">
				</td>
			</tr>
			<c:choose>
				<c:when test="${orgDescPId!=0}">
					<tr>
						<td width="25%" align="right">上级机构：</td>
						<td width="75%" align="left">
							<input id="upOrgName" type="text" name="orgPName" class="easyui-combotree" 
							style="width:90%;"><a style="color:red">*</a>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr style="display:none">
						<td width="25%" align="right">上级机构：</td>
						<td width="75%" align="left">
							<input id="upOrgName" type="text" name="orgPName" value="0" class="easyui-combotree" 
							style="width:90%;"><a style="color:red">*</a>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<tr>
				<td width="25%" align="right">机构代码：</td>
				<td width="75%" align="left">
					<input name="orgCode"  class="easyui-textbox" 
								style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构描述：</td>
				<td width="75%" align="left">
					<textarea name="orgDesc"   class="easyui-validatebox textbox"  cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr>
				<td width="25%" align="right">机构地址：</td>
				<td width="75%" align="left">
					<textarea name="orgAddress" class="easyui-validatebox textbox"  cols="27" rows="4"></textarea>
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">是否可用：</td>
				<td width="75%" align="left">
					<input type="radio" name="enable" value="1" checked/>是
					<input type="radio" name="enable" value="0" />否
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">预留1：</td>
				<td width="75%" align="left">
					<input name="orgReserve1" class="easyui-textbox" style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr style="display:none;">
				<td width="25%" align="right">预留2：</td>
				<td width="75%" align="left">
					<input name="orgReserve2" class="easyui-textbox" style="width:90%;" maxlength="32">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="updateOrg();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>