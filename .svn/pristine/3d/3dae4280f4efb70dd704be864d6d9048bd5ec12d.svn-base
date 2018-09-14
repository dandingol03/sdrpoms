/**   
 * @Package: com.pc.bsp.common.util 
 * @author: jwl   
 * @date: 2018年4月23日 下午2:32:21 
 */
package com.pc.bsp.common.util;


/**   
 * @Package: com.pc.bsp.common.util 
 * @author: jwl   
 * @date: 2018年4月23日 下午2:32:21 
 */
public class JurisdictionAppendSql {
	 public static String appendSql(String orgId){
			StringBuffer appendSql = new StringBuffer();
			appendSql.append(" and t.org_id in "
					+ "(SELECT "
					+ " od.ORG_ID "
					+ " FROM "
					+ " pub_org o, "
					+ " pub_org_desc od "
					+ " WHERE "
					+ " od.org_id = o.org_id "
					+ " AND od.id LIKE ( "
					+ " SELECT "
					+ " concat(id, '%') "
					+ " FROM "
					+ " pub_org_desc pod"
					+ " WHERE "
					+ " pod.org_id = '"+orgId+"' ))"
					+ " ");
			return appendSql.toString();
	 }
	 public static String appendSql(){
			StringBuffer appendSql = new StringBuffer();
			appendSql.append(" and t.org_id in "
					+ "(SELECT "
					+ " od.ORG_ID "
					+ " FROM "
					+ " pub_org o, "
					+ " pub_org_desc od "
					+ " WHERE "
					+ " od.org_id = o.org_id "
					+ " AND od.id LIKE ( "
					+ " SELECT "
					+ " concat(id, '%') "
					+ " FROM "
					+ " pub_org_desc pod"
					+ " WHERE "
					+ " pod.org_id = :orgId ))"
					+ " ");
			return appendSql.toString();
	 }
}
