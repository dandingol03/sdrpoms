package com.pc.busniess.oaAddressBook.service;

/**
 * pc端通讯录service
 * @author  c.xk
 * @version 1.0
 * @since   1.0
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.busniess.oaAddressBook.dao.OaInfoAddressBookDao;
import com.pc.busniess.oaAddressBook.po.OaInfoAddressBookPo;



@Service("oaInfoAddressBookService")
public class OaInfoAddressBookService {

	@Autowired
	private OaInfoAddressBookDao oaInfoAddressBookDao;
	
	/**
	 * 查询通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param oaInfoAddressBookPo @see OaInfoAddressBookPo
	 * @return 返回查询的通讯录相关信息
	 */

	public Map<String, Object> oaInfoAddressBookQueryList(DataGridModel dgm, OaInfoAddressBookPo oaInfoAddressBookPo) {
		// TODO 查询信息
	    	
		return oaInfoAddressBookDao.oaInfoAddressBookQueryList(dgm,oaInfoAddressBookPo);
	}                               

	/**
	 * 通讯录信息添加
	 * @param req 请求体 @see HttpServletRequest
	 * @return 返回添加通讯录信息
	 */
	public int addOaInfoAddressBook(OaInfoAddressBookPo oaInfoAddressBookPo) {
		// TODO 添加信息
		String id = NextID.getNextID("oaad");
		oaInfoAddressBookPo.setId(id);
		return oaInfoAddressBookDao.addOaInfoAddressBook(oaInfoAddressBookPo);
	}
	/**
	 * 弹出修改通讯录信息添加
	 * @param oaInfoAddressBookPo
	 * @return
	 */
	public int updateOaInfoAddressBook(OaInfoAddressBookPo oaInfoAddressBookPo) {
		// TODO 修改信息
		return oaInfoAddressBookDao.updateOaInfoAddressBook(oaInfoAddressBookPo);
	}
	
	/**
	 * 删除通讯录信息
	 * @param idlist
	 * @return
	 */
	public int[] deleteOaInfoAddressBook(List<String> idList) {
		// TODO 删除信息
		return oaInfoAddressBookDao.deleteOaInfoAddressBook(idList);
	}

	/**
	 * @return
	 */
	public List<Map<String, Object>> queryPers(String userId) {
		// TODO 参考人
		return oaInfoAddressBookDao.queryPers(userId);
	}
	
	//查询用户信息
	public Map<String, Object> findbyUserId(String userId) {
		// TODO Auto-generated method stub
		return oaInfoAddressBookDao.findbyUserId(userId);
	}
}
