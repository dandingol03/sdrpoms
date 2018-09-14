package com.pc.exhibition.chat.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.security.po.PubUsers;
import com.pc.exhibition.chat.dao.ChatDao;


@Service("ChatService")
public class ChatService{
	@Autowired
	private ChatDao chatDao;
	
	public List<Map<String, Object>> findPeoples(String name) {
		// TODO Auto-generated method stub
		return chatDao.findPeoples(name);
	}
	
	public List<Map<String, Object>> findRoomChat() {
		// TODO Auto-generated method stub
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String userId = user.getUserId();
		return chatDao.findRoomChat(userId);
	}
	
	public int addRoomPer(List<Object[]> objList) {
		// TODO Auto-generated method stub
		int result=0;
		if(objList.size() > 0){
			int[] saveCount = chatDao.addRoomPer(objList);
			for(int i = 0; i < saveCount.length; i ++){
				 result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
}
