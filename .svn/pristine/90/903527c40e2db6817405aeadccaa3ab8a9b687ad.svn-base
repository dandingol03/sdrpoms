package com.pc.busniess.oaAddressBook.web.controller;


/**
 * pc端通讯录的路由控制
 * @author  c.xk
 * @version 1.0
 * @since   1.0
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaAddressBook.po.OaInfoAddressBookPo;
import com.pc.busniess.oaAddressBook.service.OaInfoAddressBookService;


@Controller
public class OaInfoAddressBookController {

	private static Logger logger = Logger.getLogger(OaInfoAddressBookController.class);
	
	@Autowired
	private OaInfoAddressBookService oaInfoAddressBookService;
	
	/**
	 * 打开通讯录信息页面
	 * @param req  请求体 @see HttpServletRequest
	 * @param dgm 分页及排序信息
	 * @param oaInfoAddressBookPo 筛选条件
	 * @return 通讯录信息页面
	 */
	//打开通讯录信息页面
	@RequestMapping(value="/oaInfo/addressBookInit",method=RequestMethod.GET)
	public String oaInfoAddressBookInit(HttpServletRequest req){
		return "pc/bussiness/oaInfoAddressBook/oaInfoAddressBookInit";
		                                    
	}
	/**
	 * 查询通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param oaInfoAddressBookPo @see OaInfoAddressBookPo
	 * @return 返回查询的通讯录相关信息
	 */

	//查询通讯录信息
	@RequestMapping(value = "/oaInfo/addressBookQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoAddressBookQueryList(DataGridModel dgm, OaInfoAddressBookPo oaInfoAddressBookPo) {
		return oaInfoAddressBookService.oaInfoAddressBookQueryList(dgm,oaInfoAddressBookPo); 
	}
	
	/**
	 * 弹出通讯录信息添加页面
	 * @param req 请求体 @see HttpServletRequest
	 * @return 返回添加通讯录信息的页面
	 */
	//弹出通讯录信息添加页面
	@RequestMapping(value="/oaInfo/addAddressBookPopWin",method=RequestMethod.GET)
	public String addBaseInfoRailPopWin(HttpServletRequest req){
		List<Map<String, Object>> list=oaInfoAddressBookService.queryPers(null);
		if(list.size()==0){
			req.setAttribute("prompt","*请您添加用户信息后再添加通讯录信息!");
		}
		req.setAttribute("queryPers",list );
	    return "pc/bussiness/oaInfoAddressBook/addOaInfoAddressBookPopWin";
	}
	
	/**
	 * 保存通讯录信息
	 * @param oaInfoAddressBookPo
	 * @return
	 */
	//保存通讯录信息
	@RequestMapping(value = "/oaInfo/addAddressBook",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOaInfoAddressBook(OaInfoAddressBookPo oaInfoAddressBookPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoAddressBookService.addOaInfoAddressBook(oaInfoAddressBookPo) > 0){
				map.put("mes", "新增通讯录信息成功");
			}else{
				map.put("mes", "新增通讯录信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增通讯录信息失败",e);
			}
			map.put("mes", "新增通讯录信息失败");
		}
		return map; 
	}
	
	
	/**
	 * 弹出修改通讯录信息添加页面
	 * @param req
	 * @return
	 */
	//弹出修改通讯录信息添加页面
	@RequestMapping(value="/oaInfo/updateAddressBookPopWin",method=RequestMethod.GET)
	public String updateOaInfoAddressBookPopWin(HttpServletRequest req,@RequestParam("userId")String userId){
		req.setAttribute("queryPers", oaInfoAddressBookService.queryPers(userId));
		return "pc/bussiness/oaInfoAddressBook/updateOaInfoAddressBookPopWin";
	}
	/**
	 * 保存修改通讯录信息
	 * @param oaInfoAddressBookPo
	 * @return
	 */
	//保存修改通讯录信息
	@RequestMapping(value = "/oaInfo/updateAddressBook",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateOaInfoAddressBook(OaInfoAddressBookPo oaInfoAddressBookPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoAddressBookService.updateOaInfoAddressBook(oaInfoAddressBookPo) > 0){
				map.put("mes", "更新通讯录信息成功");
			}else{
				map.put("mes", "更新通讯录信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改通讯录信息失败",e);
			}
			map.put("mes", "修改通讯录信息失败");
		}
		return map; 
	}
	/**
	 * 删除通讯录信息
	 * @param idList
	 * @return
	 */
	//删除通讯录信息
	@RequestMapping(value="/oaInfo/deleteAddressBook",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteOaInfoAddressBook(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = oaInfoAddressBookService.deleteOaInfoAddressBook(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条通讯录信息");
			}else{
				map.put("mes", "删除通讯录信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除通讯录信息失败",e);
			}
			map.put("mes", "删除通讯录信息失败");
		}
		return map;
	}
	
	//查询用户信息
	@RequestMapping(value = "/oaInfo/findbyUserId",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> findbyUserId(DataGridModel dgm, @RequestParam("userId") String userId) {
		return oaInfoAddressBookService.findbyUserId(userId); 
	}
}
