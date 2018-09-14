<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<body>
    <script type="text/javascript">
	$(function(){
		$('#allAuths').datalist({
    		onDblClickCell: function(index,field,value){
    			var rows = $('#allAuths').datalist('getRows');    // get current page rows
    			var row = rows[index];
    			$('#myAuths').datalist('insertRow',{
					 index: 0,  
					 row: row
					});
    			 $('#allAuths').datalist('deleteRow', index); 
    		}
    	});
    	
    	$('#myAuths').datalist({
    		onDblClickCell: function(index,field,value){
    			var rows = $('#myAuths').datalist('getRows');    // get current page rows
    			var row = rows[index];
    			$('#allAuths').datalist('insertRow',{
					 index: 0,  
					 row: row
					});
		        $('#myAuths').datalist('deleteRow', index);  
    		}
    	});
	});
	
	function addAuth(){
		var rows = $('#allAuths').datalist('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#myAuths').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			for (var i = 0; i < rows.length; i++) {
		         var rowIndex = $('#allAuths').datalist('getRowIndex', rows[i]);
		         $('#allAuths').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	function addAllAuths(){
		var rows = $('#allAuths').datalist('getRows');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#myAuths').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			var allRowLength = rows.length;
			for (var i = 0; i < allRowLength; i++) {
		         var rowIndex = $('#allAuths').datalist('getRowIndex', rows[i]);
		         $('#allAuths').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	function removeAuth(){
		var rows = $('#myAuths').datalist('getSelections');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#allAuths').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			for (var i = 0; i < rows.length; i++) {
		         var rowIndex = $('#myAuths').datalist('getRowIndex', rows[i]);
		         $('#myAuths').datalist('deleteRow', rowIndex);  
		 	}
		}
	}
	
	function removeAllAuths(){
		var rows = $('#myAuths').datalist('getRows');
		if(rows.length>0){
			for(var i=0;i<rows.length;i++){
				$('#allAuths').datalist('insertRow',{
					 index: 0,  
					 row: rows[i]
					});
			}
			var allRowLength = rows.length;
			for (var i = 0; i < allRowLength; i++) {
		         var rowIndex = $('#myAuths').datalist('getRowIndex', rows[i]);
		         $('#myAuths').datalist('deleteRow', rowIndex);  
		 	}
		}
	}

	//更新角色权限
	function upRoleAuth(){
		var chooseOpts = "";
		var rows = $('#myAuths').datalist('getRows');
		for(var i=0;i<rows.length;i++){
			chooseOpts += rows[i].value+',';
		}
		//更新
		$.post("role/updateRoleAuth?authIds="+chooseOpts+"&roleId=${checkedRoleId}", $("#roleAuthForm").serializeArray(),function(data){
			closeWindow();
			$.messager.alert('提示',data.mes,'info');
		});
	}
	
	</script>
	
	<form id="roleAuthForm" method="post" class="fw fh">
  		<div class="pt5 pl10" style="height:5%;">
  			当前角色：<input style="border:none" name="roleName">
  		</div>
  		<div class="fl" style="width:2%;height:85%;"></div>
  		<div class="fl" style="width:44%;height:85%;">
      		<div id="allAuths" class="easyui-datalist" data-options="singleSelect: false" style="width:100%;height:100%;">
      			<c:forEach items="${OtherAuths}" var="auth" varStatus="status">
       				<li value="${auth.id}">${auth.name}</li>
       			</c:forEach>
      		</div>
  		</div>
  
  		<div class="fl" style="width:8%;height:85%;">
  			<div style="width:100%;height:25%;"></div>
   			<div style="width:100%;height:10%;">
   				<a onclick="addAuth();" class="easyui-linkbutton" style="width:100%;">添加&rsaquo;</a>
   			</div>
   			<div style="width:100%;height:10%;">
   				<a onclick="addAllAuths();" class="easyui-linkbutton" style="width:100%;">全部添加&raquo;</a>
   			</div>
   			<div style="width:100%;height:10%;">
   				<a onclick="removeAuth();" class="easyui-linkbutton" style="width:100%;">&lsaquo;移除</a>
   			</div>
   			<div style="width:100%;height:10%;">
   				<a onclick="removeAllAuths();" class="easyui-linkbutton" style="width:100%;">&laquo;全部移除</a>
   			</div>
  		</div>

  		<div class="fl" style="width:44%;height:85%;">
      		<div id="myAuths" class="easyui-datalist" data-options="singleSelect: false"  style="width:100%;height:100%;">
      			<c:forEach items="${MyAuths}" var="auth" varStatus="status">
       				<li value="${auth.id}">${auth.name}</li>
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
						<a style="width:30%" onclick="upRoleAuth();" class="easyui-linkbutton" 
						iconCls="icon-save">保存</a>
					</td>
				</tr>
			</table>
    	</div>
    
	</form>
</body>
