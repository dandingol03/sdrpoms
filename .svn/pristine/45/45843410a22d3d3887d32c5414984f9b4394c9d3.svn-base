<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!doctype html public "-/w3c/dtd html 4.01 transitional/en" "http://www.w3.org/tr/html4/loose.dtd">

<html>
<head>
	<title>文件上传</title>
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="Simple">
</head>
<style type="text/css">
</style>

<body>

<form id="fileuploadform" enctype="multipart/form-data" method="post" action="file/fileUpLoad">
	<div style="margin:10px 5px; padding:5px 0px; border:0px solid #f0f0f0;">
	<input id="fileUploadServiceSingle" type="hidden" name="fileUploadService" value="fileService" />
	<input class="easyui-validatebox" required="true" id="btnFileSingle" type="file" name="uploadFile" />
	 <a href="#" id="btn-add" onclick="uploadFileClick();" class="easyui-linkbutton" iconCls="icon-save">上传</a>
	</div>
</form>
<a href="file/fileDownload?fileDownloadService=fileService&fileId=b7a2ef45f7e44bcdbcfb4018bbf0762c"  class="easyui-linkbutton" iconCls="icon-save">下载</a>
<script>
 function uploadFileClick(){
	 
	 var r= $("#fileuploadform").form('validate');
		
	if(!r) {
		return false;
	}
		
	 $('#fileuploadform').form('submit',{
         url:'file/fileUpLoad',
         onSubmit:function(pa){
           
         },
         success:function(data){
        	 if (typeof (data) != 'undefined') {
            	 if(data != "-1" && data != ""){
            		//data是fileId的字符串集合，逗号分割
						var strs = new Array(); //定义一数组 

						strs = data.split(","); //字符分割 
						for (i = 0; i < strs.length; i++) {
							alert(strs[i]); //分割后的字符输出 
						}
                	 $.messager.alert('提示', "文件上传成功", 'info');
                	 $('#myPopWindow').window('close');
                 }else{
                	 $.messager.alert('提示', "文件上传失败", 'info');
                 }
             }else{
            	 $.messager.alert('提示', "文件上传失败", 'info');
             }
         }
       });

   
 }
 /*function ajaxFileUploadClick() {
	 
     $.ajaxFileUpload
     (
         {
             url: 'file/fileUpLoad', //用于文件上传的服务器端请求地址
             secureuri: false, //是否需要安全协议，一般设置为false
             fileElementId: 'btnFileSingle', //文件上传域的ID
             dataType: 'text', //返回值类型 一般设置为json,这里设置成文本
             success: function (data, status)  //服务器成功响应处理函数
             {
                 //$("#img1").attr("src", data.imgurl);
                 if (typeof (data) != 'undefined') {
                	 if(data == "1"){
                    	 $.messager.alert('提示', "文件上传成功", 'info');
                    	 $('#myPopWindow').window('close');
                     }else{
                    	 $.messager.alert('提示', "文件上传失败", 'info');
                     }
                 }else{
                	 $.messager.alert('提示', "文件上传失败", 'info');
                 }
                 
             },
             error: function (data, status, e)//服务器响应失败处理函数
             {
                 alert(e);
             }
         }
     )
     return false;
 }*/

</script>
</body>

</html>