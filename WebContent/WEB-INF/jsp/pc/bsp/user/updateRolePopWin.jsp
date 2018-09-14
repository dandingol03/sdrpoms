<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<body>
    <script type="text/javascript">
    jQuery(function($){
    	$('#allRoles').datalist({
    		onDblClickCell: function(index,field,value){
    			var rows = $('#allRoles').datalist('getRows');    // get current page rows
    			var row = rows[index];
    			$('#myRoles').datalist('insertRow',{
					 index: 0,  
					 row: row
					});
    			 $('#allRoles').datalist('deleteRow', index); 
    		}
    	});
    	
    	$('#myRoles').datalist({
    		onDblClickCell: function(index,field,value){
    			var rows = $('#myRoles').datalist('getRows');    // get current page rows
    			var row = rows[index];
    			$('#allRoles').datalist('insertRow',{
					 index: 0,  
					 row: row
					});
		        $('#myRoles').datalist('deleteRow', index);  
    		}
    	});
	});

	function addRole(){
		var rows = $('#allRoles').datalist('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#myRoles').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			for (var i = 0; i < rows.length; i++) {
		         var rowIndex = $('#allRoles').datalist('getRowIndex', rows[i]);
		         $('#allRoles').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	function addAllRoles(){
		var rows = $('#allRoles').datalist('getRows');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#myRoles').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			var allRowLength = rows.length;
			for (var i = 0; i < allRowLength; i++) {
		         var rowIndex = $('#allRoles').datalist('getRowIndex', rows[i]);
		         $('#allRoles').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	function removeRole(){
		var rows = $('#myRoles').datalist('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#allRoles').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			for (var i = 0; i < rows.length; i++) {
		         var rowIndex = $('#myRoles').datalist('getRowIndex', rows[i]);
		         $('#myRoles').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	function removeAllRoles(){
		var rows = $('#myRoles').datalist('getRows');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#allRoles').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			var allRowLength = rows.length;
			for (var i = 0; i < allRowLength; i++) {
		         var rowIndex = $('#myRoles').datalist('getRowIndex', rows[i]);
		         $('#myRoles').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	//更新用户角色
	function upUserRole(){
		var chooseOpts = "";
		var rows = $('#myRoles').datalist('getRows');
		for(var i=0;i<rows.length;i++){
			chooseOpts += rows[i].value+',';
		}
		//更新
		$.post("user/updateUserRole?roleIds="+chooseOpts+"&userId=${checkedUserId}", $("#userRoleForm").serializeArray(),function(data){
			closeWindow();
			$.messager.alert('提示',data.mes,'info');
		});
	}
	
	</script>
	<form id="userRoleForm" method="post" class="fw fh">
   		<div class="pt5 pl10" style="height:5%;">
   			当前用户：<input style="border:none" name="userAccount">
   		</div>
   		<div class="fl" style="width:2%;height:85%;"></div>
   		<div class="fl" style="width:44%;height:85%;">
       		<div id="allRoles" class="easyui-datalist" data-options="singleSelect: false" style="width:100%;height:100%;">
       			<c:forEach items="${OtherRoles}" var="role" varStatus="status">
       				<li value="${role.id}">${role.name}</li>
       			</c:forEach>
       		</div>
   		</div>
   		
   		<div class="fl" style="width:8%;height:85%;">
   			<div style="width:100%;height:25%;"></div>
   			<div style="width:100%;height:10%;">
   				<a onclick="addRole();" class="easyui-linkbutton" style="width:100%;">添加&rsaquo;</a>
   			</div>
   			<div style="width:100%;height:10%;">
   				<a onclick="addAllRoles();" class="easyui-linkbutton" style="width:100%;">全部添加&raquo;</a>
   			</div>
   			<div style="width:100%;height:10%;">
   				<a onclick="removeRole();" class="easyui-linkbutton" style="width:100%;">&lsaquo;移除</a>
   			</div>
   			<div style="width:100%;height:10%;">
   				<a onclick="removeAllRoles();" class="easyui-linkbutton" style="width:100%;">&laquo;全部移除</a>
   			</div>
   			<div style="width:100%;height:25%;"></div>
   		</div>
		
   		<div class="fl" style="width:44%;height:85%;">
       		<div id="myRoles" class="easyui-datalist" data-options="singleSelect: false"  style="width:100%;height:100%;">
       			<c:forEach items="${MyRoles}" var="role" varStatus="status">
       				<li value="${role.id}">${role.name}</li>
       			</c:forEach>
       		</div>
   		</div>
   		<div class="fl" style="width:2%;height:85%;"></div>
		<div class="cb fw pt5">
			<table class="fw fh">
				<tr>
					<td style="width:45%;" align="right">
						<a style="width:30%" onclick="closeWindow();" class="easyui-linkbutton" 
						iconCls="icon-cancel">返回</a>
					</td>
					<td style="width:10%;"></td>
					<td style="width:45%;" align="left">
						<a style="width:30%" onclick="upUserRole();" class="easyui-linkbutton" 
						iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
    	</div>
	</form>
</body>