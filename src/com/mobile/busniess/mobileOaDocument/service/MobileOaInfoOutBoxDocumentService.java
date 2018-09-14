package com.mobile.busniess.mobileOaDocument.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileOaDocument.dao.MobileOaInfoOutBoxDocumentDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
/**
 * 发件箱
 * @author jwl
 * service
 */
@Service("mobileOaInfoOutBoxDocumentService")
public class MobileOaInfoOutBoxDocumentService {

	@Autowired
	private MobileOaInfoOutBoxDocumentDao oaInfoOutBoxDocumentDao;
	/**
	 * 查询发件箱
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	public Map<String, Object> OutBoxDocumentQueryList(DataGridModel dgm,
			OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询发件箱
		return oaInfoOutBoxDocumentDao.OutBoxDocumentQueryList(dgm,
				oaInfoDocumentPo);
	}
	/**
	 * 添加发件箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	public int addOaInfoOutBoxDocument(OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 添加发件箱
		String receiveUserId = oaInfoDocumentPo.getSendUserId();
		String receiveUserIdArr[] = receiveUserId.split(",");
		int succeed[] = new int[receiveUserIdArr.length];
		for (int i = 0; i < receiveUserIdArr.length; i++) {
			String id = NextID.getNextID("oa");
			oaInfoDocumentPo.setId(id);
			oaInfoDocumentPo.setSendUserId(receiveUserIdArr[i]);
			succeed[i] = oaInfoOutBoxDocumentDao
					.addOaInfoOutBoxDocument(oaInfoDocumentPo);
		}
		for (int k = 0; k < succeed.length; k++) {
			if (succeed[k] < 0) {
				return -1;
			}
		}
		return 1;
	}
	/**
	 * 添加发件箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	public int addOaInfoOutBoxDocumentD(OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 添加发件箱
		String id = NextID.getNextID("oa");
		oaInfoDocumentPo.setId(id);
		return oaInfoOutBoxDocumentDao.addOaInfoOutBoxDocument(oaInfoDocumentPo);
	}
	/**
	 * 删除发件箱
	 * @param idList
	 * @return
	 */
	public int[] deleteOaInfoOutBoxDocument(List<String> idList) {
		// TODO 删除发件箱
		return oaInfoOutBoxDocumentDao.deleteOaInfoOutBoxDocument(idList);
	}
}
