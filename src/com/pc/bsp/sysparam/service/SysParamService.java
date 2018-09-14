/**
 * 
 */
package com.pc.bsp.sysparam.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.sysparam.dao.SysParamDao;
import com.pc.bsp.sysparam.po.SysParam;

/**
 * @author simple
 *
 */
@Service("sysParamService")
public class SysParamService{
	
	@Autowired
	private SysParamDao sysParamDao;
	
	/**
	 * 分页查询系统参数
	 * @param dgm
	 * @param param
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, SysParam param){
		return sysParamDao.getPageList(dgm, param);
	}
	
	/**
	 * 保存系统参数
	 * @param param
	 * @return
	 */
	public int saveSysParam(SysParam param){
		int re = sysParamDao.saveSysParam(param);
		if(re>0){
			PubData.setData("key", param.getParamCode());
			PubData.setData("value",param.getParamValue());
			PubData.setData("status", param.getParamStatus());
		}
		return re;
	}
	
	/**
	 * 更新系统参数
	 * @param param
	 * @return
	 */
	public int updateSysParam(SysParam param){
		int re = sysParamDao.updateSysParam(param);
		if(re>0){
			PubData.setData("key", param.getParamCode());
			PubData.setData("value",param.getParamValue());
			PubData.setData("status", param.getParamStatus());
		}
		return re;
	}
	
	/**
	 * 批量删除系统参数
	 * @param idList
	 * @return
	 */
	public int[] delSysParamBatch(List<String> idList){
		return sysParamDao.delSysParamBatch(idList);
	}

}
