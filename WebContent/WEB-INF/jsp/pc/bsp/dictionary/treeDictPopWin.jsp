<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">
  	jQuery(function($){
  		var setting = {
  			async : {
  				enable : true, 			
  				url : "datadictionary/getDictTree", 
  				autoParam : [ "id" ]
  			},
  			data:{ //必须使用data
  				simpleData : {
  				    enable : true,
  				    idKey : "id", 	
  				    pIdKey : "pId", 
  				    rootPId : 0 
  				}
  			},
  			//回调函数
  			callback : {
  				onClick : function(event, treeId, treeNode, clickFlag) {
  				},
  				//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
  				onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
  					$.messager.alert("提示","加载错误：" + XMLHttpRequest,"info");  
  				},
  				onAsyncSuccess : function(event, treeId, treeNode, msg){
  				}
  			}
  		};

  		//渲染
  		$(document).ready(function() {
  		    //加载机构树
  			$.fn.zTree.init($("#loadDictTree"), setting);
  		});
	});
	</script>
	
	<div class="fw fh">
		<ul id="loadDictTree" class="ztree" class="fw fh"></ul>
	</div>
	
</body>
	
