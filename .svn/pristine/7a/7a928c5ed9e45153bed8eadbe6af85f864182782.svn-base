package com.pc.busniess.oaDocument.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaDocument.dao.OaInfoDraftsDocumentDao;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
/**
 * 草稿箱
 * @author jwl
 * Service
 */
@Service("oaInfoDraftsDocumentService")
public class OaInfoDraftsDocumentService {

	@Autowired
	private OaInfoDraftsDocumentDao oaInfoDraftsDocumentDao;
	
	public Map<String, Object> draftsDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询草稿箱
	    	
		return oaInfoDraftsDocumentDao.draftsDocumentQueryList(dgm,oaInfoDocumentPo);
	}

	public int updateOaInfoDraftsDocument(OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 修改草稿箱
		return oaInfoDraftsDocumentDao.updateOaInfoDraftsDocument(oaInfoDocumentPo);
	}
	
	public int[] deleteOaInfoDraftsDocument(List<String> idList) {
		// TODO 删除草稿箱
		return oaInfoDraftsDocumentDao.deleteOaInfoDraftsDocument(idList);
	}

	public int deleteDrafts(OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 发送成功后的删除草稿箱 
		return oaInfoDraftsDocumentDao.deleteDrafts(oaInfoDocumentPo);
	}

}
