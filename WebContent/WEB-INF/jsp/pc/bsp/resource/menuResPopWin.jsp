<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
    <script type="text/javascript">

  	jQuery(function($){
  		var setting = {
  		  	async : {
  		  		enable : true, 				//设置 zTree是否开启异步加载模式
  		  		url : "menu/getMenuAndRes", //Ajax 获取数据的 URL 地址
  		  		autoParam : [ "id" ]		//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
  		  	},
  		  	data:{ //必须使用data
  		  		simpleData : {
  		  			enable : true,
  		  			idKey : "id", 	//id编号命名 默认
  		  			pIdKey : "pId", //父id编号命名 默认
  		  			rootPId : 0 	//用于修正根节点父节点数据，即 pIdKey 指定的属性值
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
  		  			//alert("加载成功!");
  		  		}
  		  	}
  		};
		  	
  		$(document).ready(function() {
  		    //加载（菜单+资源）树
  			$.fn.zTree.init($("#menuResTree"), setting);
  		});
	});
	
	</script>
	
	<div class="fw fh">
		<ul id="menuResTree" class="ztree" class="fw fh"></ul>
	</div>
	
</body>
	
