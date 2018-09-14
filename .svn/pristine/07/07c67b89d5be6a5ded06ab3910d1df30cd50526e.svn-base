/**   
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
package com.mobile.busniess.mobileOaBaseInfoAnnouncement.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileOaBaseInfoAnnouncement.service.MobileOaInfoAnnouncementService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaBaseInfoAnnouncement.po.OaInfoAnnouncementPo;

/**   
 * 通知 公告
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
@Controller
public class MobileOaInfoAnnouncementController {

	@Autowired
	private MobileOaInfoAnnouncementService oaInfoAnnouncementService;
	
	//结果集查询  ---权限
	@RequestMapping(value = "/mobile/oaInfo/oaInfoAnnouncementQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoAnnouncementQueryList(DataGridModel dgm,
			OaInfoAnnouncementPo oaInfoAnnouncementPo) {
		return oaInfoAnnouncementService.oaInfoAnnouncementQueryList(dgm,oaInfoAnnouncementPo); 
	}
}
