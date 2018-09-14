package com.pc.exhibition.chat.dao;



import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;

@Repository("ChatDao")
public class ChatDao {
	@Autowired
	private DBUtil util;

	public List<Map<String, Object>> findPeoples(String name) {
		// TODO Auto-generated method stub
		StringBuffer sqlappend=new StringBuffer();
		sqlappend.append("SELECT USER_ID as id,USER_NAME as NAME,firstPinyin(USER_NAME)as szm from pub_users ");
//		sqlappend.append("pub_org o,pub_org_desc od where od.org_id = o.org_id UNION ALL SELECT u.USER_ID AS id,u.USER_NAME AS NAME,'4' AS ORG_LEVEL ");
//		sqlappend.append("FROM pub_users u WHERE u.USER_ID IN ( SELECT c.user_id FROM patrol_team_user_relation c) ) AS aa ");
		if(StringUtils.isNotBlank(name)){
			sqlappend.append("where USER_NAME like '%"+name+"%'");
		}
		sqlappend.append("order by convert(substr(USER_NAME,1,1) using 'GBK') ");
		return util.getMapList(sqlappend.toString(),new Object[]{});
		
	}

//	/**
//	 * @return
//	 */
//	public List<Map<String, Object>> findRoomChat() {
//		// TODO Auto-generated method stub
//		String sql="SELECT room_id as roomId,m.name from (SELECT count(user_id) as userCount,room_id from mobile_chat_relationship GROUP BY room_id)as e "
//				+ "LEFT JOIN mobile_chat_room_info m on m.id=e.room_id where userCount>2 ";
//		return util.getMapList(sql,new Object[]{});
//	}
	public List<Map<String, Object>> findRoomChat(String userId) {
		// TODO Auto-generated method stub
		String sql="SELECT i.name,i.create_date as createDate,r.room_id FROM `mobile_chat_relationship` r ,mobile_chat_room_info i where r.room_id=i.id and r.type ='2' and r.user_id=? ";
		return util.getMapList(sql,new Object[]{userId});
	}
	/**
	 * @param objList 
	 * @return
	 */
	public int[] addRoomPer(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String sql="insert into mobile_chat_relationship (room_id,user_id,user_join_date) values (?,?,?)";
		return util.batchOperate(sql, objList);
	}
}
