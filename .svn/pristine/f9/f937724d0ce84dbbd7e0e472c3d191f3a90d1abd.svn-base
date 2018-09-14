<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>铁路线</title>
<style>
	.ex_main_rightheader_pw4_fristul{border-bottom:none;}
	.ex_main_rightheader_pw4_secondul{
		background:url(${ctx}/resources/image/exhibition/map/legend/railPwbg.png) left -2px top 29px  no-repeat;
	}
	.ex_main_rightheader_pw4_thirdul{
		width:calc(100% - 40px);
		height:auto !important;
		padding:20px !important;
		overflow:hidden;  
		border-bottom:1px solid rgb(10,46,61);
	}
	.ex_main_rightheader_pw4_fourul{
		width:calc(100% - 40px);
		height:auto !important;
		padding:20px !important;
		border-bottom:1px solid rgb(10,46,61);
	}
	.ex_main_rightheader_pw4_fiveul{
		width:calc(100% - 40px);
		height:auto!important;
		padding:20px !important;
	}
	.ex_main_rightheader_pw4_thirdul li:nth-child(1){
		width:94% !important;
		height:30px !important;
		margin:0;
	}
	.ex_main_rightheader_pw4_thirdul li{
		width:24% !important;
		float:left!important;
		padding:0 !important;
		height:auto;
		margin:2px 0;
	}
	.ex_main_rightheader_pw4_fourul li:nth-child(1){
		width:94% !important;
		height:30px !important;
	}
	.ex_main_rightheader_pw4_fourul li{
		width:26% !important;
		float:left!important;
		margin: 3px 0 !important;
		padding:0 !important;
	}
	.ex_main_rightheader_pw4_fiveul li:nth-child(1){
		width:94% !important;
		height:30px !important;
		margin:0;
	}
	.ex_main_rightheader_pw4_fiveul li{
		width:24% !important;
		float:left!important;
		padding:0 !important;
		margin:2px 0;
	}
	.ex_main_rightheader_pw4_content ul li p{
		display:block;
		float:left;
		width:auto !important;
		font-size:16px;
		font-family: monospace;
	}
	.ex_main_rightheader_pw4_content ul li span{
		display: block;
		float:left;
	}
	.ex_main_rightheader_pw4_fiveul li span{
		font-size:14px;
		font-family: monospace;
		line-height:20px;
	}
	
	.ex_main_rightheader_pw4_header{
	 	background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_header.png) no-repeat;
	 	background-size:100% 100%;
 	}
 	.ex_main_rightheader_pw4_content{
 		background:url(${ctx}/resources/image/exhibition/mappw/ex_map_pw_content.png) no-repeat;
 		background-size:100% 100%;
 	}
 	.ex_main_rightheader_closepw4{
 		background:url(${ctx}/resources/image/exhibition/main/closeBtn.png) no-repeat !important;
 		background-size:100% 100%;
 		width:40px!important;
 		height:40px!important;
 		position: absolute!important;
 		right:0!important;
 		top:0!important;
 	}
 	.ex_main_rightheader_pw4{height:100% !important;}
</style>
</head>
<body>
<div class="ex_main_rightheader_pw4_header" id="railListQ">
	<img alt="" src="${ctx}/resources/image/exhibition/map/legend/railPwbg.png">
	<span id="railName"></span>
	<div class="ex_main_rightheader_closepw4" onclick="closePw()"></div>
</div>
<div class="ex_main_rightheader_pw4_content">
	<ul class="ex_main_rightheader_pw4_fristul">
		<li><p>线路类别:</p><span>${classifyName}</span></li>
		<li>
			<ul id="TJQN"></ul>
		</li>
		<li>
			<ul id="railSE" ></ul>
		</li>
	</ul> 
	<ul class="ex_main_rightheader_pw4_secondul">
		<li><p>定位点位置:</p><span id="countryName"></span><span id="streetName"></span></li>
		<li >
			<ul id="locationKm"></ul>
		</li>
	</ul>
	<ul class="ex_main_rightheader_pw4_thirdul">
		<li><p>重点部位:</p>
			<span>${railKeyPartCount.stationCount+
				    railKeyPartCount.bcCount+
				    railKeyPartCount.junctionCount+
				    railKeyPartCount.dangerCount+
				    railKeyPartCount.tunnelCount+
				    railKeyPartCount.trajectionCount}
			</span>
		</li>
		<c:choose>
			<c:when test="${railKeyPartCount.bcCount == 0}">
				<li style="display:none"><p>桥涵:</p><span>${railKeyPartCount.bcCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railKeyPartCount.bcCount > 0}">
					<li><p>桥涵:</p><span>${railKeyPartCount.bcCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railKeyPartCount.junctionCount == 0}">
				<li><p>道口:</p><span>${railKeyPartCount.junctionCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railKeyPartCount.junctionCount > 0}">
					<li><p>道口:</p><span>${railKeyPartCount.junctionCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railKeyPartCount.tunnelCount == 0}">
				<li style="display:none;"><p>隧道:</p><span>${railKeyPartCount.tunnelCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railKeyPartCount.tunnelCount > 0}">
					<li><p>隧道:</p><span>${railKeyPartCount.tunnelCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railKeyPartCount.stationCount == 0}">
				<li style="display:none;"><p>站场:</p><span>${railKeyPartCount.stationCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railKeyPartCount.stationCount > 0}">
					<li><p>站场:</p><span>${railKeyPartCount.stationCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railKeyPartCount.trajectionCount == 0}">
				<li style="display:none;"><p>易穿行:</p><span>${railKeyPartCount.trajectionCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railKeyPartCount.trajectionCount > 0}">
					<li><p>易穿行:</p><span>${railKeyPartCount.trajectionCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railKeyPartCount.dangerCount == 0}">
				<li style="display:none"><p>隐患点:</p><span>${railKeyPartCount.dangerCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railKeyPartCount.dangerCount > 0}">
					<li><p>隐患点:</p><span>${railKeyPartCount.dangerCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
	<ul class="ex_main_rightheader_pw4_fourul">
		<li><p>联防点位:</p>
			<span>${railJDenfenseCount.stationCount+
				    railJDenfenseCount.propagandaCount+
				    railJDenfenseCount.broadcastCount+
				    railJDenfenseCount.monitorCount+
				    railJDenfenseCount.teamUserCount+
				    railJDenfenseCount.policeHouseCount+
				    railJDenfenseCount.policeStationCount+
				    railJDenfenseCount.userCount}
			</span>
		</li>
		<c:choose>
			<c:when test="${railJDenfenseCount.stationCount == 0}">
				<li style="display:none"><p>工作站:</p><span>${railJDenfenseCount.stationCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.stationCount > 0}">
					<li><p>工作站:</p><span>${railJDenfenseCount.stationCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${railJDenfenseCount.propagandaCount == 0}">
				<li style="display:none;"><p>宣传点:</p><span>${railJDenfenseCount.propagandaCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.propagandaCount > 0}">
					<li><p>宣传点:</p><span>${railJDenfenseCount.propagandaCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railJDenfenseCount.broadcastCount == 0}">
				<li style="display:none"><p>广播柱:</p><span>${railJDenfenseCount.broadcastCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.broadcastCount > 0}">
					<li><p>广播柱:</p><span>${railJDenfenseCount.broadcastCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${railJDenfenseCount.monitorCount == 0}">
				<li style="display:none"><p>摄像头:</p><span>${railJDenfenseCount.monitorCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.monitorCount > 0}">
					<li><p>摄像头:</p><span>${railJDenfenseCount.monitorCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${railJDenfenseCount.policeHouseCount == 0}">
				<li style="display:none"><p>派出所:</p><span>${railJDenfenseCount.policeHouseCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.policeHouseCount > 0}">
					<li><p>派出所:</p><span>${railJDenfenseCount.policeHouseCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${railJDenfenseCount.policeStationCount == 0}">
				<li style="display:none"><p>警务站:</p><span>${railJDenfenseCount.policeStationCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.policeStationCount > 0}">
					<li><p>警务站:</p><span>${railJDenfenseCount.policeStationCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${railJDenfenseCount.teamUserCount == 0}">
				<li style="display:none"><p>在线队员:</p><span>${railJDenfenseCount.teamUserCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.teamUserCount > 0}">
					<li><p>在线队员:</p><span>${railJDenfenseCount.teamUserCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${railJDenfenseCount.userCount == 0}">
				<li style="display:none"><p>在线干部:</p><span>${railJDenfenseCount.userCount}</span></li>
			</c:when>
			<c:otherwise>
				<c:if test="${railJDenfenseCount.userCount > 0}">
					<li><p>在线干部:</p><span>${railJDenfenseCount.userCount}</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
	<ul class="ex_main_rightheader_pw4_fiveul">
		<li><p>周边场所(重点):</p><span id="allCount"></span></li>
		<li><p>学校:</p><span id="school"></span></li> 
		<li><p>市场:</p><span id="market"></span></li>
		<li><p>厂矿:</p><span id="factoryAndMine"></span></li>
		<li><p>住宅:</p><span id="zhuzhai"></span></li>
		<li><p>单位:</p><span id="danwei"></span></li>
		<li><p>工地:</p><span id="gongdi"></span></li>
		<li><p>休闲:</p><span id="xiuxian"></span></li>
		<li><p>商厦:</p><span id="shangsha"></span></li>
		<li><p>吃住:</p><span id="chizhu"></span></li>
		<li><p>养殖:</p><span id="yangzhi"></span></li>
		<li><p>种植:</p><span id="zhongzhi"></span></li>
		<li><p>加油站:</p><span id="jiayouzhan"></span></li>
		<li><p>河湖:</p><span id="hehu"></span></li>
		<li><p>其它:</p><span id="qita"></span></li>
	</ul>
</div>
<script type="text/javascript" src="${ctx}/resources/js/exhibition/pwLo.js"></script>
<script type="text/javascript">
		var railList=${rails};
		var tempRailHtmlF="";
		$('#railSE').html(tempRailHtmlF);
		var railListD=${railsInfo};
		var tempRailHtmlS="";
		var tempLocationHtml="";
		var i = 0;
		for(var j = 0;j<railListD.length;j++){
			if(railListD[j].km != undefined){
				var adname=railListD[j].railName;
				var acname=railListD[j].km;
			}
		 	var orgRegionC="";
			var orgRName = railListD[j].railOrgCountMap.citys;
			for (var it=0;it<orgRName.length;it++){
				orgRegionC+=orgRName[it].cityOrgNames+",";
			} 
			orgRegionC=orgRegionC.substring(0,orgRegionC.length-1);
			while(railList){
				console.log(railList)
				tempRailHtmlS+="<li id='railNameC'>"+
									"<p>线路名称:</p>"+
									"<span id='railNameB'>"+railListD[j].railName+"</span>"+
								"</li>"+ 
								"<li>"+
									"<p>辖内里程:</p>"+
									"<span>"+railListD[j].railOrgKMMap+"</span>"+
								"</li>"+
								"<li>"+
									"<p>途径区:</p>"+
									"<span>"+orgRegionC+"</span>"+
								"</li>" +
								"<li>"+
									"<p>途径街乡镇:</p>"+
									"<span>"+railListD[j].railOrgCountMap.streetCount+"</span>"+
								"</li>"+
								"<li>"+
									"<p>"+"起点:</p>"+
									"<span>"+railList[i].startAddress+"</span>"+
								"</li>"+
								"<li>"+
									"<p>"+"终点:</p>"+
									"<span>"+railList[i].endAddress+"</span>"+
								"</li>";
					if(railListD[j].km != undefined){
						var adname=railListD[j].railName;
						var acname=railListD[j].km;
						tempLocationHtml +=	"<li>"+
												"<p>"+adname+"定位点公里标:</p>"+
												"<span>"+acname+"</span>"+
											"</li>"
					}
					++i;
				break;
			}
		}
		$('#TJQN').html(tempRailHtmlS);
		$('#locationKm').html(tempLocationHtml);
		var zoom = map.getView().getZoom();
		if(zoom>=11){
			$('#railName').html('${railName}');
		}else{
			$('#railName').html('${name}');
		}
		$(function(){
			var railNameX = $('#railNameB').html();
			var railName = $('#railName').html();
			if(railNameX == railName){
				$('#railNameC').css('display','none')
			}
			var  peripheral =${railPeripheralPlaceCount};
			var  allCount = 0;
			for(var i=0; i<peripheral.perPlaceTypeList.length;i++){
				switch(peripheral.perPlaceTypeList[i].dictData){
					case "01":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#zhuzhai').parent().css('display','none')
						}else{
							$('#zhuzhai').parent().css('display','block')
						}
						$('#zhuzhai').html(peripheral.perPlaceTypeList[i].dictImportCount)
						allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
						break;
					case "02":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#danwei').parent().css('display','none')
						}else{
							$('#danwei').parent().css('display','block')
						}
						$('#danwei').html(peripheral.perPlaceTypeList[i].dictImportCount)
						allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
						break;
					case "03":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#market').parent().css('display','none')
						}else{
							$('#market').parent().css('display','block')
						}
						$('#market').html(peripheral.perPlaceTypeList[i].dictImportCount)
						allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
						break;	
					case "04":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#school').parent().css('display','none')
						}else{
							$('#school').parent().css('display','block')
						}
						$('#school').html(peripheral.perPlaceTypeList[i].dictImportCount)
						allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
						break;
					case "05":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#factoryAndMine').parent().css('display','none')
						}else{
							$('#factoryAndMine').parent().css('display','block')
						}
						$('#factoryAndMine').html(peripheral.perPlaceTypeList[i].dictImportCount)
						allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
						break;
					case "06":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#gongdi').parent().css('display','none')
						}else{
							$('#gongdi').parent().css('display','block')
						}
					    $('#gongdi').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "07":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#xiuxian').parent().css('display','none')
						}else{
							$('#xiuxian').parent().css('display','block')
						}
					    $('#xiuxian').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "08":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#shangsha').parent().css('display','none')
						}else{
							$('#shangsha').parent().css('display','block')
						}
					    $('#shangsha').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "09":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#chizhu').parent().css('display','none')
						}else{
							$('#chizhu').parent().css('display','block')
						}
					    $('#chizhu').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "10":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#yangzhi').parent().css('display','none')
						}else{
							$('#yangzhi').parent().css('display','block')
						}
					    $('#yangzhi').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "11":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#zhongzhi').parent().css('display','none')
						}else{
							$('#zhongzhi').parent().css('display','block')
						}
					    $('#zhongzhi').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "12":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#jiayouzhan').parent().css('display','none')
						}else{
							$('#jiayouzhan').parent().css('display','block')
						}
					    $('#jiayouzhan').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "13":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#hehu').parent().css('display','none')
						}else{
							$('#hehu').parent().css('display','block')
						}
					    $('#hehu').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
					case "14":
						if(peripheral.perPlaceTypeList[i].dictImportCount == 0){
							$('#qita').parent().css('display','none')
						}else{
							$('#qita').parent().css('display','block')
						}
					    $('#qita').html(peripheral.perPlaceTypeList[i].dictImportCount)
					    allCount+=parseInt(peripheral.perPlaceTypeList[i].dictImportCount)
					    break;
				}
			}
			$('#allCount').html(allCount);
			if(allCount == 0){
				$('#allCount').parent().css('display','none');
			}else{
				$('#allCount').parent().css('display','block');
			}
		})
</script>
</body>
</html>