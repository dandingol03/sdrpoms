/**
 * 
 */
package com.pc.bsp.org.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.pc.bsp.common.util.GetJDBCConnection;
import com.pc.bsp.common.util.NextID;

/**
 * @author Administrator
 *
 */
public class ImpOrgData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try{
			
			File file = new File("D:\\org.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			List<String> dataList = new ArrayList<String>();
			String ss = null;
			br.readLine();	//跳过第一行
			ss = br.readLine();
			while(ss != null && !ss.trim().equals("")){
				//System.out.println(ss);
				dataList.add(ss);
				ss = br.readLine();
			}
			
			br.close();
			fr.close();
			
			
			Connection conn = GetJDBCConnection.getJDBCConnection();
			
			String orgSql = "insert into pub_org (org_id, org_code, org_name, enable, org_address, org_desc, org_reserve1, org_reserve2) " +
					 "values (?, '', ?, '1', '', '', '', '')";
			String descSql = "insert into pub_org_desc (id, org_id, org_level, parent_id, is_parent, open) values " +
					 "(?, ?, ?, ?, ?, '')";
			
			PreparedStatement pstmt1 = conn.prepareStatement(orgSql);
			PreparedStatement pstmt2 = conn.prepareStatement(descSql);
			
			String orgId = "";
			String orgName = "";
			String id = "";
			String orgLevel = "";
			String pId = "";
			String isParent = "";
			String[] data = new String[3];
			for(int i = 0; i < dataList.size(); i ++){
				data = dataList.get(i).toString().split(",");
				data[1] = data[1].replaceAll("\"", "");
				data[2] = data[2].replaceAll("\"", "");
				//System.out.println("["+data[1]+"]-["+data[2]+"]");
				
				orgId = NextID.getNextID("org");
				orgName = data[2];
				id = data[1];
				orgLevel = id.length()/4+"";
				pId = id.substring(0, id.length()-4);
				
				
				//计算当前机构码是否父节点（与后续机构码依次对比，前提是所有机构码已经按照升序或降序分组排列）
				isParent = "0";
				String[] tmpData = new String[3];
				for(int j = i+1; j < dataList.size(); j ++){
					tmpData = dataList.get(j).toString().split(",");
					tmpData[1] = tmpData[1].replaceAll("\"", "");
					//如果后续机构码的父节点机构码有与之相同的，则认为当前机构码是父节点
					if(tmpData[1].substring(0, tmpData[1].length()-4).equals(id)){
						isParent = "1";
						break;
					}
				}
				
				System.out.println("["+(i+1)+"]:["+orgId+"]-["+id+"]-["+orgName+"]-["+orgLevel+"]-["+pId+"]-["+isParent+"]");
				
				pstmt1.setString(1, orgId);
				pstmt1.setString(2, orgName);
				
				pstmt2.setString(1, id);
				pstmt2.setString(2, orgId);
				pstmt2.setString(3, orgLevel);
				pstmt2.setString(4, pId);
				pstmt2.setString(5, isParent);
				
				if(pstmt1.executeUpdate() > 0){
					pstmt2.executeUpdate();
				}
				
			}
			
			conn.close();

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
