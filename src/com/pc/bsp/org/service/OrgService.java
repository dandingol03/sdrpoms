/**
 * 
 */
package com.pc.bsp.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.org.dao.OrgDao;
import com.pc.bsp.org.po.Org;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Service("orgService")
public class OrgService{
	
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 根据当前登录用户过滤机构树
	 */
	public List<OrgDesc> getUserOrgList(String userOrg){
		return orgDao.getUserOrgList(userOrg);
	}
	
	/**
	 * 查询完整的机构树
	 */
	public List<OrgDesc> getOrgList(){
		return orgDao.getOrgList();
	}
	
	/**
	 * 分页查询机构
	 * @param dgm
	 * @param org
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm,Org org){
		return orgDao.getPageList(dgm, org);
	}
	
	/**
	 * 保存机构机构描述信息
	 * @param org
	 * @param orgDesc
	 * @return
	 */
	public int saveOrgDesc(Org org, OrgDesc orgDesc){
		orgDao.saveOrg(org);
		//orgDesc.setId(null);
		return orgDao.saveOrgDesc(orgDesc);
	}
	
	/**
	 * 保存机构
	 * @param org
	 * @return
	 */
	public int saveOrg(Org org){
		return saveOrg(org);
	}
	
	/**
	 * 更新是否父节点状态
	 * @param pid
	 * @return
	 */
	public int updateIsParent(String pid){
		int ret = 0;
		String sql = "select count(1) from pub_org_desc where parent_id = '"+pid+"'";
		int count = orgDao.getOrgCount(sql);
		if(count > 0){
			ret = orgDao.updateIsParent(pid, "1");
		}else{
			ret = orgDao.updateIsParent(pid, "0");
		}
		return ret;
	}
	
	/**
	 * 保存机构描述信息
	 * @param orgDesc
	 * @return
	 */
	public int saveDesc(OrgDesc orgDesc){
		return orgDao.saveOrgDesc(orgDesc);
	}
	
	/**
	 * 更新pub_org
	 * @param org
	 * @return
	 */
	public int updateOrg(Org org){
		return orgDao.updateOrg(org);
	}
	
	/**
	 * 更新pub_org和pub_org_desc
	 * @param org
	 * @param orgDesc
	 * @return
	 */
	public int updateOrgDesc(Org org, OrgDesc orgDesc){
		orgDao.updateOrg(org);
		return orgDao.updateOrgDesc(orgDesc);
	}
	
	/**
	 * 批量修改机构及其下属机构
	 */
	public int[] updateOrgDescs(Org org, OrgDesc orgDesc){
		//机构变更后新的id(作为原有下属机构的新pId)
		String newPID = orgDesc.getId();
		
		//根据orgId获取OrgDesc对象的id(以便查询其原来的下属机构)
		String pId = orgDao.getOrgDescByOrgId(org.getOrgId()).getId();
		//获取pId下的所有下属机构
		List<OrgDesc> odList = orgDao.getOrgDescList(pId);
		List<Object[]> objList = new ArrayList<Object[]>();
		
		String sql = "update pub_org_desc set id = ?, parent_id = ?, org_level = ? where org_id = ?";
		
		//odList第一个为原机构本身(单独更新)，不是下属机构，所以i从1开始
		for(int i = 1; i < odList.size(); i ++){
			Object[] obj = new Object[4];
			
			obj[0] = newPID+((OrgDesc)(odList.get(i))).getId().substring(pId.length());
			obj[1] = newPID+((OrgDesc)(odList.get(i))).getpId().substring(pId.length());
			obj[2] = (obj[0].toString().length())/4;	//机构级别(根据新生成的id计算)
			obj[3] = ((OrgDesc)(odList.get(i))).getOrgId();
			
			objList.add(obj);
		}
		orgDao.updateOrg(org);	//更新pub_org
		orgDao.updateOrgDesc(orgDesc);	//更新pub_org_desc
		return orgDao.updateOrgDescBatch(sql, objList);	//更新下级所有节点
	}
	
	/**
	 * 批量删除机构
	 * @param idList
	 * @return
	 */
	public int[] delOrgs(List<String> idList){
		orgDao.delOrgBatch(idList);
		return orgDao.delOrgDescBatch(idList);
	}
	
	/**
	 * 获取直接下级节点中id最大的，返回其最后四位字符串的整形值
	 * @param id
	 * @return
	 */
	public int getSubOrgMaxId(String id){
		String sql= "select max(id) as \"maxId\" from pub_org_desc where parent_id = ?";
		Map<String, Object> idMap = (Map<String, Object>)orgDao.getMap(sql, new Object[]{id});
		int orgCount = 0;
		String maxId = (String)idMap.get("maxId");
		if(null != maxId){
			orgCount = Integer.parseInt(maxId.substring(maxId.length()-4));
		}else{
			orgCount = -1;
		}
		
		return orgCount;
	}
	
	/**
	 * 获取直接下级机构数
	 * @param id
	 * @return
	 */
	public int getSubOrgCount(String id){
		String sql = "select count(1) from pub_org_desc where parent_id = '"+id+"'";
		return orgDao.getOrgCount(sql);
	}
	
	/**
	 * 根据orgId获取Org对象
	 * @param orgId
	 * @return
	 */
	public Org getOrgByOrgId(String orgId){
		return orgDao.getOrgByOrgId(orgId);
	}
	
	/**
	 * 根据orgId获取OrgDesc对象
	 * @param orgId
	 * @return
	 */
	public OrgDesc getOrgDescByOrgId(String orgId){
		return orgDao.getOrgDescByOrgId(orgId);
	}
	
	/**
	 * 根据id获取OrgDesc对象
	 * @param id
	 * @return
	 */
	public OrgDesc getOrgDescById(String id){
		return orgDao.getOrgDescById(id);
	}
	
	/**
	 * 根据父节点逐级获取下级机构（用于ComboTree）
	 * @param orgId
	 * @return
	 */
	public List<OrgDesc> getComOrgList(String orgId){
		return orgDao.getComOrgList(orgId);
	}
	
	/**
	 * 获取下级机构id字符串
	 * @param orgId
	 * @return
	 */
	public String getDescOrgStr(String orgId){
		List<OrgDesc> list=getComOrgList(orgId);
		 String orgStr="";
		    if(list!=null&&list.size()!=0){
		    	for(int i=0;i<list.size();i++){
		    		orgStr=orgStr+"'"+list.get(i).getId()+"',";
		    	}
		    	orgStr=orgStr.substring(0, orgStr.length()-1);
		    }
		return orgStr;
	}
	
	/**
	 * 根据orgId和机构层级获得该层级下所有的List<Map<String,Object>>
	 * @param orgId
	 * @param orgLevel
	 * @return
	 */
	public List<Map<String,Object>> getLevelOrgList(String orgId,String orgLevel) {
		return orgDao.getLevelOrgList(orgId,orgLevel);
	}
	/**
	 * 根据orgId和机构层级获得该层级下所有的List<String> orgId
	 * @param orgId
	 * @param orgLevel
	 * @return
	 */
	public List<String> getLevelOrgIdList(String orgId,String orgLevel) {
		return orgDao.getLevelOrgIdList(orgId,orgLevel);
	}
	
	/**
	 * 根据orgId获得机构名称
	 * @param orgId
	 * @return
	 */
	public String getOrgNameByOrgId(String orgId){
		return orgDao.getOrgNameByOrgId(orgId);
	}
	
	/**
	 * 根据当前用户获取机构层级字典
	 * @return
	 */
	public List<Map<String,Object>> getDictOrgLevelMap(){
		String userLevel="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof PubUsers) {
			OrgDesc tempOrgDesc= getOrgDescByOrgId(((PubUsers) principal).getUserOrg());	
			if(tempOrgDesc!=null)
				userLevel=tempOrgDesc.getOrgLevel();
		}
		
		List<Map<String,Object>> res = PubData.getDictList("ORG_LEVEL");
		switch(userLevel){
		case "2":
			res.remove(0);
			break;
		case "3":
			res.remove(1);
			res.remove(0);
			break;
		case "4":
			res.remove(2);
			res.remove(1);
			res.remove(0);
			break;
		case "5":
			res.remove(3);
			res.remove(2);
			res.remove(1);
			res.remove(0);
			break;
		case "1":
		default:
			return res;
		}
		return res;
	}

	/**
	 * @param userOrg
	 * @return
	 */
	public List<OrgDesc> getAreaOrgList(String userOrg) {
		// TODO Auto-generated method stub
		return orgDao.getAreaOrgList(userOrg);
	}
	public String getOrg(String lng ,String lat){
		return orgDao.getOrg(lng,lat);
	}
}
