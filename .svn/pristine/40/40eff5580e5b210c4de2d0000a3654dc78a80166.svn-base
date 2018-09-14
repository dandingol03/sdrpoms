// 地图页面的点击事件（不包括地图）

/*function exReturn() {
	window.location.href = "sdrpoms/welcome";
}*/
function menubar() {
	var element = event.target;
	var menuName = element.dataset.orgName;
	var menuId = element.dataset.menuId;
	window.location.href = "/sdrpoms/welcome?menuName=" + menuName + "&menuId=" + menuId;
}

function exReturn() {
	window.location.href = "/sdrpoms/logout";
}

/**
 * ajax 错误处理（统一回调）
 */
function ajaxErrorCb(XMLHttpRequest, textStatus, errorThrown) {
	console.log("响应状态:["+ XMLHttpRequest.status+ "]-");
	console.log("完成状态:["+ XMLHttpRequest.readyState+ "]-");
	console.log("异常情况:["+ textStatus + "]");
}