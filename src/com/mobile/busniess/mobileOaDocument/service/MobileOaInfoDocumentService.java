package com.mobile.busniess.mobileOaDocument.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileOaDocument.dao.MobileOaInfoDocumentDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
/**
 * 收件箱
 * @author jwl
 *	Service
 */
@Service("mobileOaInfoDocumentService")
public class MobileOaInfoDocumentService {

	@Autowired
	private MobileOaInfoDocumentDao oaInfoDocumentDao;
	
	public Map<String, Object> oaInfoDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询收件箱
	    
		return oaInfoDocumentDao.oaInfoDocumentQueryList(dgm,oaInfoDocumentPo);
	}
	
	public int[] deleteOaInfoDocument(List<String> idList) {
		// TODO 删除收件
		return oaInfoDocumentDao.deleteOaInfoDocument(idList);
	}
	public List<Map<String, Object>> queryPers() {
		// TODO 发送人的查询
		List<Map<String, Object>> list = oaInfoDocumentDao.queryPers();
		return list;
	}

	public Integer anUnreadMailCount(
		OaInfoDocumentPo oaInfoDocumentPo) {
	    // TODO Auto-generated method stub
	    return oaInfoDocumentDao.anUnreadMailCount(oaInfoDocumentPo);
	}
}
