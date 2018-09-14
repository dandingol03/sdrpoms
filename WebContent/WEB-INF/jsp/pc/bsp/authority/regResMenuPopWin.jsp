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
  		  	check: {
				enable: true,
				chkboxType: { "Y" : "ps", "N" : "ps"},
				chkStyle: "checkbox"
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
  		  			alert("加载错误：" + XMLHttpRequest);  
  		  		},
  		  		onAsyncSuccess : function(event, treeId, treeNode, msg){
  		  			//加载选中节点
  		  			$(function(){
  		  				$.ajax({
  		  					type:'POST',
  		  					url:"authority/getMyRes?checkedAuthId=${checkedAuthId}",
  		  					success:function(data){
  		  						var arTree = $.fn.zTree.getZTreeObj("authResTree");
  		  						$.each(data, function(n, val) {
  	  		  						arTree.checkNode(arTree.getNodeByParam("id", val.id, null), true, true);
  		              			});
  		  					}
  		  				});
  		  			});
  		  		}
  		  	}
  		};
		  	
  		$(document).ready(function() {
  			$.fn.zTree.init($("#authResTree"), setting);
  		});
	});

    //保存[权限-资源]对应关系
    function upAuthRes(){
        var arTree = $.fn.zTree.getZTreeObj("authResTree");
        var nodes = arTree.getCheckedNodes(true);
        var resIds = "";
        for (var i = 0; i < nodes.length; i++) {
            if(nodes[i].id.length > 3 && nodes[i].id.substring(0,3) == "res"){
            	resIds += nodes[i].id + ",";
            }
        }
        $.post('authority/updateAuthRes?resourceIds='+resIds+'&checkedAuthId=${checkedAuthId}', $("#authResForm").serializeArray(), function(data){
        	closeWindow();
			$.messager.alert('提示',data.mes,'info');
		});
    }
	</script>
	
	<form id="authResForm" method="post" class="operationPage" style="margin-top:0;padding-top:0;">
    	<table style="border-collapse:collapse;">
    		<tr>
    			<td style="margin-top:5px;padding-top:5px;text-align:left">
	    			当前权限：<input class="invisibleButton" name="authorityName" style="width:75%;"><input id="authorityId" name="authorityId" type="hidden">
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
					<ul id="authResTree" class="ztree" ></ul>
				</td>
	    	</tr>	
	    	<tr>
	    		<td>
	    			<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
	    			&nbsp;&nbsp;&nbsp;&nbsp;
	    			<a onclick="upAuthRes();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
	    		</td>
    		</tr>
		</table>
	</form>
</body>
