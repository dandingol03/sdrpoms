/**   
 * @Package: com.pc.busniess.oaOaInfoNews.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
package com.pc.busniess.oaBaseInfoNews.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.oaBaseInfoNews.dao.OaInfoNewsDao;
import com.pc.busniess.oaBaseInfoNews.po.OaInfoNewsPo;

/**   
 * @Package: com.pc.busniess.oaOaInfoNews.service 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:11 
 */
@Service("oaInfoNewsService")
public class OaInfoNewsService {

	@Autowired
	private OaInfoNewsDao oaInfoNewsDao;
	
	public Map<String, Object> oaInfoNewsQueryList(DataGridModel dgm,OaInfoNewsPo oaInfoNewsPo) {
			// TODO 查询信息
			//      获取当前登录用户
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PubUsers user = (PubUsers)principal;
			String orgId =user.getUserOrg();
		return oaInfoNewsDao.oaInfoNewsQueryList(dgm,oaInfoNewsPo,orgId);
	}
	public int addOaInfoNews(OaInfoNewsPo oaInfoNewsPo) {
			// TODO 添加信息
			String id = NextID.getNextID("news");
			oaInfoNewsPo.setId(id);
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			PubUsers user = (PubUsers)principal;
			String userId = user.getUserId();
			oaInfoNewsPo.setEditor(userId); //用户id
		return oaInfoNewsDao.addOaInfoNews(oaInfoNewsPo);
	}
	public int updateOaInfoNews(OaInfoNewsPo oaInfoNewsPo) {
			// TODO 修改信息
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String userId = user.getUserId();
		oaInfoNewsPo.setEditor(userId); //用户id
		return oaInfoNewsDao.updateOaInfoNews(oaInfoNewsPo);
	}
	public int[] deleteOaInfoNews(List<String> idList) {
			// TODO 删除信息
		return oaInfoNewsDao.deleteOaInfoNews(idList);
	}
}
