package com.pc.bsp.datadictionary.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.datadictionary.po.Dictionary;
import com.pc.bsp.datadictionary.service.DataDictionaryService;
import com.pc.bsp.org.po.ComboTreeModel;
import com.pc.bsp.org.util.GenOrgDescID;


@Controller
public class DataDictionaryController {
	
	private static Logger logger = Logger.getLogger(DataDictionaryController.class);
	
	@Autowired
	private DataDictionaryService dictionaryService;
	
	/**
	 * 获取字典树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/comTree")
	@ResponseBody
	public void comDictTree(HttpServletResponse response, @RequestParam String pid) {
		List<Dictionary> comOrg = dictionaryService.getComDictList(pid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			logger.error(e);
		}
		List<ComboTreeModel> list = new ArrayList<ComboTreeModel>();

		for (int i = 0; i < comOrg.size(); i++) {

			ComboTreeModel ctm = new ComboTreeModel();
			ctm.setId(comOrg.get(i).getId());
			ctm.setText(comOrg.get(i).getDictCode()+"["+comOrg.get(i).getDictName()+"]");

			String isParent = comOrg.get(i).getIsParent();
			if (isParent.equals("1")) {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("closed");
				list.add(ctm);
			} else {
				List<ComboTreeModel> children = new ArrayList<ComboTreeModel>();
				ctm.setChildren(children);
				ctm.setState("open");
				list.add(ctm);
			}
		}
		String json = JSONArray.fromObject(list).toString();// 转化为JSON
		pw.write(json);// 返回前台
	}
	
	/**
	 * 通过菜单进入数据字典页面
	 */
	@RequestMapping(value="/datadictionary",method=RequestMethod.GET)
    public String redirectDataDictionaryPage(){
        return "pc/bsp/dictionary/dictionary";
    }

	/**
	 * 默认分页查询数据字典
	 * 
	 * @param dgm
	 * @param Dictionary
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(DataGridModel dgm, Dictionary dictionary) {
		return dictionaryService.getPageList(dgm, dictionary);
	}
	
	/**
	 * 进入数据字典添加页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/addPopWin", method = RequestMethod.GET)
	public String popWin4Add() {
		return "pc/bsp/dictionary/addDictPopWin";
	}

	/**
	 * 保存添加字典
	 * 
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/addDictionary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addDictionary(Dictionary dictionary) {
		String pId = dictionary.getPid();
		String id = GenOrgDescID.get4NewId(pId, dictionaryService.getSubOrgMaxId(pId)); 
		String dictLevel = (id.length() / 4)-1 + ""; 
		String isParent = "0"; 
		dictionary.setId(id);
		dictionary.setDictLevel(dictLevel);
		dictionary.setIsParent(isParent);
		Map<String, String> map = new HashMap<String, String>();
		if (dictionaryService.addDictionary(dictionary) > 0) {
			dictionaryService.updateIsParent(pId);
			map.put("mes", "添加数据字典成功");
		} else {
			map.put("mes", "添加数据字典失败");
		}

		return map;
	}
	
	/**
	 * 进入数据字典更新页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/updatePopWin", method = RequestMethod.GET)
	public String popWin4Update() {
		return "pc/bsp/dictionary/updateDictPopWin";
	}

	/**
	 * 保存添加字典
	 * 
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/updateDictionary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateDictionary(Dictionary dictionary) {

		Map<String, String> map = new HashMap<String, String>();
		if (dictionaryService.updateDictionary(dictionary) > 0) {
			map.put("mes", "更新数据字典成功");
		} else {
			map.put("mes", "更新数据字典失败");
		}

		return map;
	}
	
	/**
	 * 判断是否重复(增加)
	 * 
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/isUpdateHadDictionary", method = RequestMethod.POST)
	@ResponseBody
	public int isUpdateHadDictionary(Dictionary dictionary) {
		int re = dictionaryService.getSameCode(dictionary.getDictCode());
		if (dictionaryService.getSameCode(dictionary.getDictCode())!=1) {
			return re;
		} 
		re = dictionaryService.getSameDict(dictionary.getPid(),
				dictionary.getDictName(), dictionary.getDictData());
		if (re> 0) {
			return re;
		}
		return 0;
	}
	
	/**
	 * 判断是否重复(更新)
	 * 
	 * @param dictionary
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/isHadDictionary", method = RequestMethod.POST)
	@ResponseBody
	public int isHadDictionary(Dictionary dictionary) {
		int re = dictionaryService.getSameCode(dictionary.getDictCode());
		if (dictionaryService.getSameCode(dictionary.getDictCode())> 0) {
			return re;
		} 
		re = dictionaryService.getSameDict(dictionary.getPid(),
				dictionary.getDictName(), dictionary.getDictData());
		if (re> 0) {
			return re;
		}
		return 0;
	}
	
	/**
	 * 进入数据字典查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/treePopWin", method = RequestMethod.GET)
	public String popWin4Tree() {
		return "pc/bsp/dictionary/treeDictPopWin";
	}
	
	/**
	 * 获得数据字典树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/datadictionary/getDictTree")
	@ResponseBody
	public List<Map<String,Object>> getDictTree() {
		return dictionaryService.getDictList("0");
	}
	
	/**
	 * 批量删除机构
	 * 
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/datadictionary/delDictionary", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delDictionary(@RequestParam("dictId") List<String> dictIdList) {

		Map<String, String> map = new HashMap<String, String>();
		try {
			List<String> delList = new ArrayList<String>(); 
			String pidStr = "";

			String pid = "";
			for (int i = 0; i < dictIdList.size(); i++) {
				String dictId = dictIdList.get(i);

				if (dictionaryService.getSubDictCount(dictId) > 0) {
					map.put("mes", "不能删除，请先删除该编码的下属编码");
					return map;
				} else {
					delList.add(dictId);
					if (!pidStr.contains(pid)) {
						pidStr += pid + ",";
					}
				}
			}

			if (delList.size() > 0) {
				int[] result = dictionaryService.delDicts(dictIdList);
				int sum = 0;
				for (int j = 0; j < result.length; j++) {
					sum += result[j];
				}

				if (!pidStr.equals("")) {
					String[] pids = pidStr.split(",");
					for (int i = 0; i < pids.length; i++) {
						dictionaryService.updateIsParent(pids[i]);
					}
				}

				if (sum == dictIdList.size()) {
					map.put("mes", "删除成功[" + sum + "]条字典记录");
				} else {
					map.put("mes", "删除字典失败");
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除字典失败",e);
			}
			map.put("mes", "删除字典失败");
		}
		return map;// 重定向
	}
}
