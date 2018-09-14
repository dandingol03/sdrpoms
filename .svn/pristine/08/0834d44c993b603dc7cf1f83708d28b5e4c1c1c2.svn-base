package com.mobile.busniess.mobileOaDocument.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileOaDocument.dao.MobileOaInfoDelectedDocumentDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;

/**
 * 回收站
 * @author jwl
 *	Service
 */
@Service("mobileOaInfoDelectedDocumentService")
public class MobileOaInfoDelectedDocumentService {

	@Autowired
	private MobileOaInfoDelectedDocumentDao oaInfoDelectedDocumentDao;
	
	public Map<String, Object> deletedocumentQueryList(DataGridModel dgm,OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询删除的邮件
	    
		return oaInfoDelectedDocumentDao.deletedocumentQueryList(dgm,oaInfoDocumentPo);
	}
}
