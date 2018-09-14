<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<script type="text/javascript" src="${ctx}/resources/video/ie8/videojs-ie8.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/video/video.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/video/lang/zh-CN.js"></script>
<script type="text/javascript">
  videojs.options.flash.swf= "${ctx}/resources/video/video-js.swf";
</script>
</head>
<body>
	<div style='width:100%;height:100%'>
		<video id="jh_video_player" class="video-js vjs-default-skin" controls preload="none" style="width:100%;height:100%"
			poster="${ctx}/resources/image/icon/icon-exit.png" >
	    	<source src="file/showVideoFile?fileId=${fileId}" type="video/mp4">   
		</video>
	</div>
	<script type="text/javascript">
		var jhvideoPlayer = null;
		$(document).ready(function(){
			if(!jhvideoPlayer){
				jhvideoPlayer = videojs("jh_video_player");
			}
			jhvideoPlayer.play();
		});
	</script>
</body>
