<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
    
		//添加重点人信息
		function addBaseInfoKeyperson(){
			//验证字段
			var r = $('#baseInfoKeypersonAddForm').form('validate');
			if(!r) {
				return false;
			}
			$.post("baseInfo/checkPersonOrgId", $("#baseInfoKeypersonAddForm").serializeArray(),function(data){
    			if(data.mes!=""){
    				$.messager.alert('提示',data.mes,'info');
    			}else{
    				showWaitingWindow();
    	    		$('#baseInfoKeypersonAddForm').form('submit',{
    	    			url : 'file/fileUpLoad',
    	    			onSubmit : function(pa) {},
    	    			success : function(data) {
    	    				if (typeof (data) != 'undefined') {
    	    					console.log('===data==>', data);
    	    					if (data != "-1" && data != "") {
    	    						var ps = "?photos="+data;
    	    						console.log('===data==>', ps);
    	    						$.post("baseInfo/addBaseInfoKeyperson"+ps,
    	    								$("#baseInfoKeypersonAddForm").serializeArray(),function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoKeypersonTable').datagrid('reload');
    	    						}); 
    	    					} else {
    	    						$.post("baseInfo/addBaseInfoKeyperson",
    	    								$("#baseInfoKeypersonAddForm").serializeArray(),function(data){
    	    							$.messager.alert('提示',data.mes,'info');
    	    							closeWindow();
    	    							$('#baseInfoKeypersonTable').datagrid('reload');
    	    						}); 
    	    					}
    	    				} else {
    	    					$.messager.alert('提示', "文件上传失败", 'info');
    	    				}
    	    			closeWaitingWindow();
    	    			}
    	    		});
    	    		closeWindow();
    	    		$('#baseInfoKeypersonTable').datagrid('reload');
    			}
    		});
		}
		
	</script>
  	
  	<!-- 验证使用jquery-validation -->
	<form id="baseInfoKeypersonAddForm" enctype="multipart/form-data" method="post" class="operationPage">
		<table>
			<tr>
				<td width="15%" align="right">身份证号：</td>
				<td width="35%" align="left">
					<input name="idNumber"  class="easyui-validatebox textbox" data-options="required:true"  style="height:20px;font-size:12px;width:90%;"  validType="idCode"></input>
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">姓名：</td>
				<td width="35%" align="left">
					<input  name="name" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;"></input>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">性别：</td>
				<td width="35%" align="left">
					<input type="radio" name="gender"  value="1" checked/>男
					<input type="radio" name="gender"  value="2" />女
					<a style="color:red">*</a>
				</td>
				<td width="15%" align="right">年龄：</td>
				<td width="35%" align="left">
					<input  name="age" class="easyui-validatebox textbox" data-options="required:true"    style="height:20px;font-size:12px;width:90%;"  validType="integer"></input>
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">身高(cm)：</td>
				<td width="35%" align="left">
					<input  name="height" class="easyui-validatebox textbox" style="height:20px;font-size:12px;width:90%;" validType="integer"></input>
				
				</td>
			
				<td width="15%" align="right">体重(kg)：</td>
				<td width="35%" align="left">
					<input class="easyui-validatebox textbox" name="weight" style="height:20px;font-size:12px;width:90%;"validType="integer"></input>
				</td>
			</tr>
		<tr>
			
				<td width="15%" align="right">职业：</td>
				<td width="35%" align="left">
					<input class="easyui-validatebox textbox" name="job" style="height:20px;font-size:12px;width:90%;"></input>
				</td>
			
			
				<td width="15%" align="right">联系电话：</td>
				<td width="35%"  align="left">
					<input class="easyui-validatebox textbox" name="telephone" style="height:20px;font-size:12px;width:90%;" data-options="validType:['mobileAndTel','mobile']" ></input>
				</td>
			</tr>
			
			<tr>
		
				<td width="15%" align="right">现居地址：</td>
				<td width="35%"  align="left">
					<input class="easyui-validatebox textbox" name="presentAddress" style="height:20px;font-size:12px;width:90%;"></input>
				</td>
			
			
				<td width="15%" align="right">家庭人数：</td>
				<td width="35%"  align="left">
					<input class="easyui-validatebox textbox" name="familyNum" style="height:20px;font-size:12px;width:90%;" validType="integer" ></input>
				</td>
			</tr>
		
			<tr>
				<td width="15%" align="right">家庭成员1：</td>
				<td width="35%" align="left">
					<input class="easyui-validatebox textbox" name="member1" style="height:20px;font-size:12px;width:90%;"></input>
				</td>
		
			
				<td width="15%" align="right">家庭成员2：</td>
				<td width="35%"  align="left">
					<input class="easyui-validatebox textbox" name="member2" style="height:20px;font-size:12px;width:90%;"></input>
				</td>
			</tr>
			<tr>
			
				<td width="15%" align="right">家庭地址：</td>
				<td width="35%"  align="left">
					<input class="easyui-validatebox textbox" name="familyAdress" style="height:20px;font-size:12px;width:90%;"></input>
				</td>
			
				<td width="15%" align="right">家庭联系电话：</td>
				<td width="35%"  align="left">
					<input class="easyui-validatebox textbox" name="familyTelephone" style="height:20px;font-size:12px;width:90%;" data-options="validType:['mobileAndTel','mobile']"></input>
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
					<input  name="lat" class="easyui-validatebox textbox" data-options="required:true" style="height:20px;font-size:12px;width:90%;" validType="number">
					<a style="color:red">*</a>
				</td>
			</tr>
			<tr>
				<td width="15%" align="right">是否签订安全协议：</td>
				<td width="35%" align="left">
					<select class="easyui-combobox" id="isSigned" name="isSigned" data-options="editable:false" style="width:91%;height:23px;" >							
					        <option value="" >请选择</option>
					        <option value="01" >是</option>
					        <option value="02" >否</option>
					  </select>
				</td>
				<td width="15%" align="right">重点人照片：</td>
				<td width="35%" align="left">
					<input class="easyui-filebox" name="uploadFile" data-options="accept:'image/jpeg,image/gif,image/jpg,image/png',prompt:'请选择记录文件...',buttonText:'请选择文件',multiple:true" style='width:90%'/>
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
					<a onclick="addBaseInfoKeyperson();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
