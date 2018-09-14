package com.pc.busniess.oaDocument.web.controller;

/**
 * 发送箱
 * @author jwl
 *Controller
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.bsp.common.util.PubData;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
import com.pc.busniess.oaDocument.service.OaInfoOutBoxDocumentService;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;

@Controller
public class OaInfoOutBoxDocumentController {
	private static Logger logger = Logger.getLogger(OaInfoOutBoxDocumentController.class);
	@Autowired
	private OaInfoOutBoxDocumentService oaInfoOutBoxDocumentService;
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	@Autowired
	HttpSession session;//注入session 获取userId
	/**
	 * 打开发件箱页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/oaInfo/outBoxDocumentInit",method=RequestMethod.GET)
	public String baseInfoRailInit(HttpServletRequest req){
		return "pc/bussiness/oaInfoOutBoxDocument/outBoxDocumentInit";
	}
	/**
	 * 打开发件箱详细信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="oaInfoOutBox/detailedInformation",method=RequestMethod.GET)
	public String detailedInformation(HttpServletRequest req){
		return "/pc/bussiness/oaInfoOutBoxDocument/outInfoDetailedInformation";
	}
	/**
	 * 查询查询发件箱
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/oaInfo/outBoxDocumentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> OutBoxDocumentQueryList(DataGridModel dgm,  OaInfoDocumentPo oaInfoDocumentPo) {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			oaInfoDocumentPo.setReceiveUserId(userId);//发件人
		return oaInfoOutBoxDocumentService.OutBoxDocumentQueryList(dgm,oaInfoDocumentPo); 
	}
	/**
	 * 弹出发件箱添加页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/oaInfo/addOutBoxDocumentPopWin",method=RequestMethod.GET)
	public String addOaInfoOutBoxDocumentPopWin(HttpServletRequest req){
		//收件人        队伍业务层调用查询user信息
		req.setAttribute("queryPers", partrolTeamUserRelationService.queryPers());
		List<Map<String,Object>> wjflList = PubData.getDictList("WJFL");
		req.setAttribute("wjflList",wjflList);
	    return "pc/bussiness/oaInfoOutBoxDocument/addOaInfoOutBoxDocumentPopWin";
	}
	/**
	 * 保存发件箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/oaInfo/addOutBoxDocument",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOaInfoDocument( OaInfoDocumentPo oaInfoDocumentPo) {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day=new Date();
        String date=df.format(day);
        oaInfoDocumentPo.setSendTime(date);
		try {
			if("0".equals(oaInfoDocumentPo.getIsDrafts())){
				if(oaInfoOutBoxDocumentService.addOaInfoOutBoxDocumentD(oaInfoDocumentPo) > 0){
					map.put("mes", "保存草稿箱成功");
				}else{
					map.put("mes", "保存草稿箱失败");
				}
			}else{
				if(oaInfoOutBoxDocumentService.addOaInfoOutBoxDocument(oaInfoDocumentPo) > 0){
					map.put("mes", "发送成功");
				}else{
					map.put("mes", "发送失败");
				}
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("失败",e);
			}
			map.put("mes", "失败");
		}
		return map; 
	}
	/**
	 * 删除发件箱
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/oaInfo/deleteOutBoxDocument",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteOaInfoOutBoxDocument(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = oaInfoOutBoxDocumentService.deleteOaInfoOutBoxDocument(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条邮件");
			}else{
				map.put("mes", "删除邮件失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除邮件失败",e);
			}
			map.put("mes", "删除邮件失败");
		}
		return map;
	}
	
}
