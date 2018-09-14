package com.mobile.busniess.mobileOaAddressBook.service;

/**
 * pc端通讯录service
 * @author  c.xk
 * @version 1.0
 * @since   1.0
 */
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.busniess.mobileOaAddressBook.dao.MobileOaInfoAddressBookDao;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.oaAddressBook.po.OaInfoAddressBookPo;



@Service("mobileOaInfoAddressBookService")
public class MobileOaInfoAddressBookService {

	@Autowired
	private MobileOaInfoAddressBookDao oaInfoAddressBookDao;
	
	/**
	 * 查询通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param oaInfoAddressBookPo @see OaInfoAddressBookPo
	 * @return 返回查询的通讯录相关信息
	 */

	public Map<String, Object> oaInfoAddressBookQueryList(DataGridModel dgm, OaInfoAddressBookPo oaInfoAddressBookPo) {
		// TODO 查询信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String userId = user.getUserId();
		return oaInfoAddressBookDao.oaInfoAddressBookQueryList(dgm,oaInfoAddressBookPo,userId);
	}                               
}
