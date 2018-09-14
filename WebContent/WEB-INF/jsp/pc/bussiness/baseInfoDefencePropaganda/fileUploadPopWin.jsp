<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
    <script type="text/javascript">
	//添加流程图
	function addFlowChart(){
		var r = $('#flowChartAddForm').form('validate');
		if(!r) {
			return false;
		}
		showWaitingWindow();
		$('#flowChartAddForm').form('submit',{
			url : 'file/fileUpLoad',
			onSubmit : function(pa) {},
			success : function(data) {
				console.log('===data==>', data);
				if (typeof (data) != 'undefined') {
					if (data != "-1" && data != "") {
						$.messager.alert('提示',"图片上传成功",data.mes,'info');
					} else {
						$.messager.alert('提示', "图片上传失败", 'info');
					}
				} else {
					$.messager.alert('提示', "图片上传失败", 'info');
				}
			}
		});
		closeWaitingWindow();
		/* $('#flowChartTable').datagrid('reload'); */
		closeWindow();
	}
	</script>
  	<!-- 验证使用jquery-validation -->
	<form id="flowChartAddForm" enctype="multipart/form-data" method="post" action="file/fileUpLoad" class="operationPage">
		<input name="type" type='hidden' style="width:90%;">
		<table>
			<tr>
				<td width="20%" align="right">图片上传：</td>
				<td width="80%" align="left">
					<input class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,,image/png',prompt:'请选择记录图片...',buttonText:'请选择图片',multiple:false,required:true" style='width:90%'/>
					<a style="color:red">*</a>
				</td>
			</tr>			
			<tr>
				<td colspan="2" align="center">
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addFlowChart();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
