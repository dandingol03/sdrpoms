/**
 * 
 */
package com.pc.bsp.org.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.org.po.Org;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Repository("orgDao")
public class OrgDao {

	@Autowired
	private DBUtil util;

	/**
	 * 根据当前登录用户过滤机构树
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> getUserOrgList(String userOrg) {
		String sql = "select " +
				"od.id as \"id\", " +
				"od.parent_id as \"pId\", " +
				"o.org_name as \"name\", " +
				"o.org_id as \"orgId\", " +
				"od.is_parent as \"isParent\", " +
				"od.open as \"open\" " +
				"from pub_org o, pub_org_desc od " +
				"where od.org_id = o.org_id and od.id like " +
				"(select concat(id, '%') from pub_org_desc where org_id = ?)";
		return (List<OrgDesc>) util.getObjList(sql, new Object[] { userOrg }, OrgDesc.class);
	}

	/**
	 * 查询完整的机构树
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> getOrgList() {
		String sql = "select " +
				"od.id as \"id\", " +
				"od.parent_id as \"pId\", " +
				"o.org_name as \"name\", " +
				"od.is_parent as \"isParent\", " +
				"od.open as \"open\" " +
				"from pub_org o, pub_org_desc od " +
				"where od.org_id = o.org_id";
		return (List<OrgDesc>) util.getObjList(sql, new Object[] {}, OrgDesc.class);
	}

	/**
	 * 分页查询机构
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Org org) {

		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_org o, pub_org_desc od where o.org_id = od.org_id");

		// 获取结果集
		String quSql = "select " +
				"o.org_id as \"uid\", " +
				"o.org_id as \"orgId\", " +
				"o.org_code as \"orgCode\", " +
				"o.org_name as \"orgName\", " +
				"o.enable as \"enable\", " +
				"o.org_address as \"orgAddress\", " +
				"o.org_desc as \"orgDesc\", " +
				"o.chief_name as \"orgChiefName\", " +
				"o.chief_contact as \"orgChiefContact\", " +
				"o.chief_idnum as \"orgChiefIdNum\", " +
				"o.org_reserve1 as \"orgReserve1\", " +
				"o.org_reserve2 as \"orgReserve2\", " +
				"od.id as \"id\", " +
				"od.org_level as \"orgLevel\", " +
				"od.parent_id as \"pId\", " +
				"od.is_parent as \"isParent\", " +
				"od.open as \"open\" " +
				"from pub_org o, pub_org_desc od " +
				"where o.org_id = od.org_id";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		OrgDesc orgDesc = null;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号(userOrg)获取机构描述对象
			orgDesc = util.getOrgDescByOrgId(((PubUsers) principal).getUserOrg());
		} else {
			// 如果登录用户session过期，则返回空结果集
			return result;
		}

		// 点击查询按钮时组装查询语句
		if (null != org.getOrgId()) {
			if (!org.getOrgId().equals("")) {
				OrgDesc tempDesc = getOrgDescByOrgId(org.getOrgId());
				org.setOrgId(tempDesc.getId());
				sqlSb.append(" and od.id like :orgId");
				params.put("orgId", org.getOrgId() + "%");
				sumSql.append(" and od.id like '").append(org.getOrgId()).append("%'");
			} else { // 如果清空查询条件或不选择机构，也根据登录用户的当前机构进行过滤
				if (null != orgDesc) {
					sqlSb.append(" and od.id like :userOrg");
					params.put("userOrg", orgDesc.getId() + "%");
					sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
				} else { // 如果机构描述对象获取失败
					return result;
				}
			}
			if (null != org.getOrgName() && !org.getOrgName().equals("")) {
				sqlSb.append(" and o.org_name like :orgName");
				params.put("orgName", "%" + org.getOrgName() + "%");
				sumSql.append(" and o.org_name like '%").append(org.getOrgName()).append("%'");
			}
		} else { // 点击菜单进入页面时，根据当前登录用户所在的机构进行查询
			if (null != orgDesc) {
				sqlSb.append(" and od.id like :userOrg");
				params.put("userOrg", orgDesc.getId() + "%");
				sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
			} else {
				// 如果机构描述对象为null，返回空结果集
				return result;
			}
		}

		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;

	}

	/**
	 * 保存机构信息
	 */
	public int saveOrg(Org org) {
		String sql = "insert into pub_org " +
				"(org_id, " +
				"org_code, " +
				"org_name, " +
				"enable, " +
				"org_address, " +
				"org_desc, " +
				"chief_name, " +
				"chief_contact, " +
				"chief_idnum, " +
				"org_reserve1, " +
				"org_reserve2) " +
				"values " +
				"(:orgId, " +
				":orgCode, " +
				":orgName, " +
				":enable, " +
				":orgAddress, " +
				":orgDesc, " +
				":orgChiefName, " +
				":orgChiefContact, " +
				":orgChiefIdNum, " +
				":orgReserve1, " +
				":orgReserve2)";
		return util.editObject(sql, org);
	}

	/**
	 * 保存机构描述信息
	 */
	public int saveOrgDesc(OrgDesc orgDesc) {
		String sql = "insert into pub_org_desc " +
				"(id, " +
				"org_id, " +
				"org_level, " +
				"parent_id, " +
				"is_parent, " +
				"open) " +
				"values " +
				"(:id, " +
				":orgId, " +
				":orgLevel, " +
				":pId, " +
				":isParent, " +
				":open)";
		return util.editObject(sql, orgDesc);
	}

	/**
	 * 更新机构信息
	 */
	public int updateOrg(Org org) {
		String sql = "update pub_org set " +
				"org_code = :orgCode, " +
				"org_name = :orgName, " +
				"enable = :enable, " +
				"org_address = :orgAddress, " +
				"org_desc = :orgDesc, " +
				"chief_name = :orgChiefName, " +
				"chief_contact = :orgChiefContact, " +
				"chief_idnum = :orgChiefIdNum, " +
				"org_reserve1 = :orgReserve1, " +
				"org_reserve2 = :orgReserve2 " +
				"where org_id = :orgId";
		return util.editObject(sql, org);
	}

	/**
	 * 更新机构描述信息
	 */
	public int updateOrgDesc(OrgDesc orgDesc) {
		String sql = "update pub_org_desc set " +
				"id = :id, " +
				"org_level = :orgLevel, " +
				"parent_id = :pId, " +
				"open = :open "
				+ "where org_id = :orgId";
		return util.editObject(sql, orgDesc);
	}

	/**
	 * 更新是否父节点状态
	 * 
	 * @param pid
	 * @param isParent
	 * @return
	 */
	public int updateIsParent(String pid, String isParent) {
		String upSql = "update pub_org_desc set is_parent = '" + isParent + "' where id = '" + pid + "'";
		return util.editObject(upSql, null);
	}

	/**
	 * 根据orgId批量删除机构信息
	 * 
	 * @param ids
	 * @return
	 */
	public int[] delOrgBatch(List<String> ids) {
		String sql = "delete from pub_org where org_id = ?";
		return util.batchDelete(sql, ids);
	}

	/**
	 * 根据orgId批量删除机构描述信息
	 * 
	 * @param ids
	 * @return
	 */
	public int[] delOrgDescBatch(List<String> ids) {
		String sql = "delete from pub_org_desc where org_id = ?";
		return util.batchDelete(sql, ids);
	}

	/**
	 * 根据pId查询下属所有机构列表
	 * 
	 * @param pId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> getOrgDescList(String pId) {
		String sql = "select " +
				"id as \"id\", " +
				"org_id as \"org_id\", " +
				"org_level as \"org_level\", " +
				"parent_id as \"pId\", " +
				"is_parent as \"is_parent\", " +
				"open as \"open\" " +
				"from pub_org_desc where id like ?";
		return (List<OrgDesc>) util.getObjList(sql, new Object[] { pId + "%" }, OrgDesc.class);
	}

	/**
	 * 批量更新OrgDesc
	 * 
	 * @param obj
	 * @return
	 */
	public int[] updateOrgDescBatch(String sql, List<Object[]> obj) {
		return util.batchOperate(sql, obj);
	}

	/**
	 * 根据orgId获取Org对象
	 * 
	 * @param orgId
	 * @return
	 */
	public Org getOrgByOrgId(String orgId) {
		String sql = "select * from pub_org where org_id = ?";
		return (Org) util.getObject(sql, new Object[] { orgId }, Org.class);
	}

	/**
	 * 根据orgId获取OrgDesc对象
	 * 
	 * @param orgId
	 * @return
	 */
	public OrgDesc getOrgDescByOrgId(String orgId) {
		String sql = "select " +
				"id as \"id\", " +
				"org_id as \"org_id\", " +
				"org_level as \"org_level\", " +
				"parent_id as \"pId\", " +
				"is_parent as \"is_parent\", " +
				"open as \"open\" " +
				"from pub_org_desc where org_id = ?";
		return (OrgDesc) util.getObject(sql, new Object[] { orgId }, OrgDesc.class);
	}

	/**
	 * 根据id获取OrgDesc对象
	 * 
	 * @param id
	 * @return
	 */
	public OrgDesc getOrgDescById(String id) {
		return (OrgDesc) util.getOrgDescById(id);
	}

	/**
	 * 获取机构数量(查询条件自己写sql)
	 * 
	 * @param sql
	 * @return
	 */
	public int getOrgCount(String sql) {
		return util.getObjCount(sql);
	}

	/**
	 * 获取指定的属性值
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMap(String sql, Object[] obj) {
		return (Map<String, Object>) util.getMap(sql, obj);
	}

	/**
	 * 根据父节点逐级获取下级机构（用于ComboTree）
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> getComOrgList(String orgId) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String sql = "";
		OrgDesc orgDesc = null;
		if ("0".equals(orgId)) {
			// 根据当前登录用户机构过滤
			if (principal instanceof PubUsers) {
				String userOrg = ((PubUsers) principal).getUserOrg();
				orgDesc = util.getOrgDescByOrgId(userOrg);
				orgId = orgDesc.getOrgId();
			} else {
				// 如果用户尚未登录，默认取根节点
				orgId = "110";
			}
			sql = "select " +
					"od.org_id as \"id\", " +
					"od.parent_id as \"pId\", " +
					"o.org_name as \"name\", " +
					"od.is_parent as \"isParent\", " +
					"od.open as \"open\" " +
					"from pub_org o, pub_org_desc od " +
					"where od.org_id = o.org_id and od.org_id = ?";
		} else {
			sql = "select " +
					"od.org_id as \"id\", " +
					"o.org_name as \"name\", " +
					"od.is_parent as \"isParent\", " +
					"od.open as \"open\" " +
					"from pub_org o, pub_org_desc od " +
					"where od.org_id = o.org_id " +
					"and od.parent_id = (select id from pub_org_desc t where t.org_id = ?)";
		}
		return (List<OrgDesc>) util.getObjList(sql, new Object[] { orgId }, OrgDesc.class);
	}
	
	//根据机构ID获得下级机构某一层级的机构内容
	public List<Map<String,Object>> getLevelOrgList(String orgId,String orgLevel) {
		if(null==orgId||orgId.equals("")||orgId.equals("0")){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof PubUsers) {
				String userOrg = ((PubUsers) principal).getUserOrg();
				orgId = util.getOrgDescByOrgId(userOrg).getOrgId();
			}else{
				return new ArrayList<Map<String,Object>>();
			}
		}
		orgId = CommonUtil.delZero(orgId);
		String sql = "select " +
				"od.org_id as \"id\", " +
				"o.org_name as \"name\" " +
				"from pub_org o, pub_org_desc od " +
				"where od.org_id = o.org_id " +
				"and od.parent_id like ? and org_level=?";
		return util.getMapList(sql, new Object[] { orgId+"%", orgLevel});
	}
	
	//根据机构ID获得下级机构某一层级的机构ID List<String>
	public List<String> getLevelOrgIdList(String orgId,String orgLevel) {
		ArrayList<String> re = new ArrayList<String>();
		if(null==orgId||orgId.equals("")||orgId.equals("0")){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof PubUsers) {
				String userOrg = ((PubUsers) principal).getUserOrg();
				orgId = util.getOrgDescByOrgId(userOrg).getOrgId();
			}else{
				return re;
			}
		}
		orgId = CommonUtil.delZero(orgId);
		String sql = "select " +
				"od.org_id as \"id\" " +
				"from pub_org o, pub_org_desc od " +
				"where od.org_id = o.org_id " +
				"and od.parent_id like ? and org_level=?";
		List<Map<String,Object>> temp = util.getMapList(sql, new Object[] { orgId+"%", orgLevel});
		if(null!=temp&&temp.size()>0){
			for(int i=0;i<temp.size();i++){
				re.add(temp.get(i).get("id").toString());
			}
			return re;
		}else{
			return re;
		}
	}
		
	//根据机构ID获得下级机构某一层级的机构内容
	public String getOrgNameByOrgId(String orgId) {
		String sql = "select " +
				"org_name as \"orgName\" " +
				"from pub_org  " +
				"where org_id=?";
		List<Map<String,Object>> temp = util.getMapList(sql, new Object[] {orgId});
		if(null!=temp&&temp.size()>0){
			return temp.get(0).get("orgName").toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 根据orgId和别名获得查询Sql 只能独立条件 或者and 不能or
	 * @param orgId
	 * @param aliasId
	 * @return and (aliasId=orgId1 or aliasId=orgId2) and 
	 */
	public String getOrgListStringByOrgId(String orgId,String aliasId){
		String sql = "select od.org_id from pub_org_desc od " +
				"where od.id like (select concat(id, '%') from pub_org_desc where org_id = :orgId)";
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("orgId", orgId);
		List<Map<String,Object>> orgIdList = util.getMapList(sql, paramMap);
		if(orgIdList.size()==0){
			return "(1=1)";
		}
		String result = "(";
		for(int i=0;i<orgIdList.size();i++){
			result += " or "+aliasId + "='" + orgIdList.get(i).get("org_id").toString()+"'" ;
		}
		result = result.replaceFirst("or", "");
		result +=")";
		return result;
	}

	/**
	 * @param userOrg
	 * @return
	 */
	public List<OrgDesc> getAreaOrgList(String userOrg) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap=new HashMap<String,Object>();
		StringBuffer appendSql = new StringBuffer();
		String sql = "select " +
				"od.id as \"id\", " +
				"od.parent_id as \"pId\", " +
				"o.org_name as \"name\", " +
				"o.org_id as \"orgId\", " +
				"od.is_parent as \"isParent\", " +
				"od.open as \"open\" " +
				"from pub_org o, pub_org_desc od " +
				"where od.org_id = o.org_id and IS_PARENT='1' ";
		
		if(!StringUtils.equals("org0000", userOrg)) {  
			appendSql.append(" and o.org_id=:id ");
			paramMap.put("id", userOrg);
		}
		return (List<OrgDesc>) util.getObjList(sql+appendSql,paramMap, OrgDesc.class);
	}

	/**
	 * @param lng
	 * @param lat
	 * @return
	 */
	public String getOrg(String lng, String lat) {
		// TODO Auto-generated method stub
		String point="POINT("+lng+" "+lat+")";
		String sql = 
				"select p.org_id as orgId "+
				"from  pub_org p "+
				"where p.layer='街道' and st_intersects(st_geomfromtext(CONCAT (?)),p.the_geom)=1 " ;
		List<Map<String, Object>> list=util.getMapList(sql, new Object[]{point});
		if(list!=null&&list.size()!=0){
			return list.get(0).get("orgId").toString();
		}else{
			return "";
		}
	}
}
