package com.pc.busniess.oaDocument.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaDocument.dao.OaInfoDelectedDocumentDao;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;

/**
 * 回收站
 * @author jwl
 *	Service
 */
@Service("oaInfoDelectedDocumentService")
public class OaInfoDelectedDocumentService {

	@Autowired
	private OaInfoDelectedDocumentDao oaInfoDelectedDocumentDao;
	
	public Map<String, Object> deletedocumentQueryList(DataGridModel dgm,OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询删除的邮件
	    
		return oaInfoDelectedDocumentDao.deletedocumentQueryList(dgm,oaInfoDocumentPo);
	}
}