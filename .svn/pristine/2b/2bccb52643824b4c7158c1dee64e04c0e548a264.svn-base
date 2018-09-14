package com.mobile.busniess.mobileOaAddressBook.web.controller;


/**
 * pc端通讯录的路由控制
 * @author  c.xk
 * @version 1.0
 * @since   1.0
 */

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileOaAddressBook.service.MobileOaInfoAddressBookService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaAddressBook.po.OaInfoAddressBookPo;
import com.pc.busniess.oaAddressBook.service.OaInfoAddressBookService;


@Controller
public class MobileOaInfoAddressBookController {

	
	@Autowired
	private MobileOaInfoAddressBookService mobileOaInfoAddressBookService;
	@Autowired
	private OaInfoAddressBookService oaInfoAddressBookService;
	/**
	 * 查询通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param oaInfoAddressBookPo @see OaInfoAddressBookPo
	 * @return 返回查询的通讯录相关信息
	 */

	//查询通讯录信息
	@RequestMapping(value = "/mobile/oaInfo/addressBookQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoAddressBookQueryList(DataGridModel dgm, OaInfoAddressBookPo oaInfoAddressBookPo) {
		return mobileOaInfoAddressBookService.oaInfoAddressBookQueryList(dgm,oaInfoAddressBookPo); 
	}
	//查询所有用户
	@RequestMapping(value = "/mobile/oaInfo/queryUsers",method = RequestMethod.POST)
	@ResponseBody
	public  List<Map<String, Object>> queryUsers() {
		return oaInfoAddressBookService.queryPers(null); 
	}
	
}
