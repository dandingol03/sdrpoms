/**   
 * @Package: com.pc.exhibition.map.web.controller 
 * @author: jwl   
 * @date: 2018年4月2日 下午2:34:35 
 */
package com.pc.exhibition.chat.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.NextID;
import com.pc.exhibition.chat.service.ChatService;

@Controller
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	//查找聊天用户
	@RequestMapping(value = "/exhibition/map/findPeoples", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> findPeoples(@RequestParam(value="name",required=false) String name) {
		return chatService.findPeoples(name);
	}
	//查找群聊
	@RequestMapping(value = "/exhibition/map/findRoomChat", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> findRoomChat() {
		return chatService.findRoomChat();
	}
	//聊天拉取成员并存贮房间默认名称及创建日期等信息
	@RequestMapping(value = "/exhibition/map/addRoomPer", method = RequestMethod.POST)
	@ResponseBody
	public int addRoomPer(@RequestParam(value="id[]",required=false) String[] id) {
//		String nextId = NextID.getNextID("room");
		String nextId ="123"; 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date day=new Date();
        String date=df.format(day);
		List<Object[]> objList = new ArrayList<Object[]>();
		for (int i = 0; i < id.length; i++) {
			//组装插入数据
			Object[] obj = new Object[3];
			obj[0] = nextId;
			obj[1] = id[i];
			obj[2] = date;
			objList.add(obj);
		}	
		return chatService.addRoomPer(objList);
	}
	//加载最后聊天内容列表
	//发送消息1-1   1-n，如果没有房间创建房间及创建日期等信息 ，聊天内容存储
	//socket  推送消息给未读人员  
	//读到消息后修改room_user_ids    为空时删除整条记录
}