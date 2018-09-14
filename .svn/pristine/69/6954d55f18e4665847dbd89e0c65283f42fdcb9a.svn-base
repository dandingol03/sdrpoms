<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
<script type="text/javascript">
	var rowListThree; 
	/* console.log(JSON.stringify(rowListThree)); */
	if(rowListThree[0].title!=undefined){
		$('#title').append('<p style="width: 100%;line-height: 15px;font-size: 16px;color: black;text-align: center;">'+rowListThree[0].title+'</p>'); 
	}
	if(rowListThree[0].hairUnit!=undefined){
		$('#hairUnit').append('<p style="line-height: 7px;text-align: center;">发件单位：'+rowListThree[0].hairUnit+'</p>'); 
	}
	if(rowListThree[0].sendTime!=undefined){
		$('#sendTime').append('<p style="line-height: 7px;text-align: center;">收件日期：'+rowListThree[0].sendTime+'</p>'); 
	}
	if(rowListThree[0].receiveUserIdName!=undefined){
		$('#receiveUserIdName').append('<p style="line-height: 7px;text-align: center;">发件人：'+rowListThree[0].receiveUserIdName+'</p>'); 
	}
	if(rowListThree[0].editor!=undefined){
		$('#editor').append(rowListThree[0].editor); 
	}
	if(rowListThree[0].fileDownload!=undefined){
		$('#file').append('<p style="padding: 10px 10px;">'+rowListThree[0].fileDownload+','+'</p>'); 
	}
	/* $('#fileName').append(rowListThree[0].fileName);   */
</script>
		<div class="main" style="width: 800px;height: 600px;margin: 0 auto;">
		<!-- 标题 start -->
		<div class="main_header" id="title" style="width: 100%;height: 30px;margin-top: 16px;margin-bottom: 10px;border-bottom:1px dashed #ccc;">
		</div>
		<!-- 标题 end -->
		<!-- 三个 start -->
		<div class="main_nav" style="width: 60%;height: 15px;margin-bottom: 10px;margin:0 auto;">
			<div class="main_nav_one" id="hairUnit" style="width: 25%;float: left;color:#2C84C3;"></div>
			<div class="main_nav_one" id="sendTime"style="width: 50%;float: left;color:#2C84C3;"></div>
			<div class="main_nav_one" id="receiveUserIdName"style="width: 25%;float: left;color:#2C84C3;"></div>
		</div>
		<!-- 三个 end -->
		<!-- 内容 start -->
		<div class="main_content" id="editor" style="overflow:auto;width: 100%;height: 370px;border:1px dashed #ccc;">
		</div>
		<!-- 内容 end -->
		<!-- 底部 start -->
		
		<div class="main_footer" id="file" style="width: 100%;height: 60px;margin-top: 10px;">
		<p style="color:red">友情提示 : 请点击下载</p>
		</div>
		<!-- 底部 end -->
	</div>
</body>
