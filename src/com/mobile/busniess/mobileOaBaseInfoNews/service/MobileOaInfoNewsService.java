/**   
 * @Package: com.pc.busniess.oaOaInfoNews.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
package com.mobile.busniess.mobileOaBaseInfoNews.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileOaBaseInfoNews.dao.MobileOaInfoNewsDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.oaBaseInfoNews.po.OaInfoNewsPo;

/**   
 * @Package: com.pc.busniess.oaOaInfoNews.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
@Service("mobileoaInfoNewsService")
public class MobileOaInfoNewsService {

	@Autowired
	private MobileOaInfoNewsDao oaInfoNewsDao;
	
	public Map<String, Object> oaInfoNewsQueryList(DataGridModel dgm,OaInfoNewsPo oaInfoNewsPo) {
			// TODO 查询信息
			//      获取当前登录用户
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PubUsers user = (PubUsers)principal;
			String orgId =user.getUserOrg();
		return oaInfoNewsDao.oaInfoNewsQueryList(dgm,oaInfoNewsPo,orgId);
	}
}
