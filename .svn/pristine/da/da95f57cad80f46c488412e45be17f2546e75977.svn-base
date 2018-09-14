package com.pc.bsp.datadictionary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.datadictionary.dao.DataDictionaryDao;
import com.pc.bsp.datadictionary.po.Dictionary;
import com.pc.bsp.org.dao.OrgDao;

@Service("dataDictionaryService")
public class DataDictionaryService {

	@Autowired
	private DataDictionaryDao dictionaryDao;
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 分页查询机构
	 * @param dgm
	 * @param org
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm,Dictionary dictionary){
		return dictionaryDao.getPageList(dgm, dictionary);
	}
	
	/**
	 * 保存数据字典
	 * @param dictionary
	 * @return
	 */
	public int addDictionary(Dictionary dictionary){
		return dictionaryDao.addDictionary(dictionary);
	}
	
	/**
	 * 更新数据字典
	 * @param dictionary
	 * @return
	 */
	public int updateDictionary(Dictionary dictionary){
		return dictionaryDao.updateDictionary(dictionary);
	}
	
	/**
	 * 批量删除字典
	 * @param idList
	 * @return
	 */
	public int[] delDicts(List<String> idList){
		return dictionaryDao.delDictBatch(idList);
	}
	
	/**
	 * 根据父节点逐级获取下级数据字典（用于ComboTree）
	 * @param pId
	 * @return
	 */
	public List<Dictionary> getComDictList(String pId){
		return dictionaryDao.getComDictList(pId);
	}
	
	/**
	 * 获取直接下级节点中id最大的，返回其最后四位字符串的整形值
	 * @param id
	 * @return
	 */
	public int getSubOrgMaxId(String id){
		String sql= "select max(id) as \"maxId\" from pub_dictionary where pid = ?";
		Map<String, Object> idMap = (Map<String, Object>)orgDao.getMap(sql, new Object[]{id});
		int dictCount = 0;
		String maxId = (String)idMap.get("maxId");
		if(null != maxId){
			dictCount = Integer.parseInt(maxId.substring(maxId.length()-4));
		}else{
			dictCount = -1;
		}
		return dictCount;
	}
	
	/**
	 * 更新是否父节点状态
	 * @param pid
	 * @return
	 */
	public int updateIsParent(String pid){
		int ret = 0;
		String sql = "select count(1) from pub_dictionary where pid = '"+pid+"'";
		int count = orgDao.getOrgCount(sql);
		if(count > 0){
			ret = dictionaryDao.updateIsParent(pid, "1");
		}else{
			ret = dictionaryDao.updateIsParent(pid, "0");
		}
		return ret;
	}
	
	/**
	 * 判断dictCode是否有重复 
	 * @param dictCode
	 * @return
	 */
	public int getSameCode(String dictCode){
		return dictionaryDao.getSameCode(dictCode);
	}
	
	/**
	 * 判断同一个PID下名称和数据是否有重复
	 * @param pid
	 * @param dictName
	 * @param dictData
	 * @return
	 */
	public int getSameDict(String pid,String dictName,String dictData){
		return dictionaryDao.getSameDict(pid,dictName,dictData);
	}
	
	/**
	 * 获取直接下级字典数
	 * @param id
	 * @return
	 */
	public int getSubDictCount(String id){
		String sql = "select count(1) from pub_dictionary where pid = '"+id+"'";
		return orgDao.getOrgCount(sql);
	}
	
	/**
	 * 数据字典树
	 */
	public List<Map<String,Object>> getDictList(String pid){
		return dictionaryDao.getDictList(pid);
	}

	/**
	 * 根据编码获取所有数据字典值
	 * @param code
	 * @return
	 */
	public List<Map<String,Object>>getAllDictByCode(String code){
		return dictionaryDao.getAllDictByCode(code);
	}
}
