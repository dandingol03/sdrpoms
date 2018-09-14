package com.pc.bsp.security.listener;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.pc.bsp.common.util.PubData;
import com.pc.bsp.user.service.UserService;


@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private static Logger logger = Logger.getLogger(AuthenticationFailureListener.class);
	
	@Autowired
	private UserService userService;
	
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		String userName = e.getAuthentication().getName();
		logger.info("当前登录失败的用户账号为:["+userName+"]");
		if(null != userName && !userName.equals("")){
			//获取登录失败次数
			Map<String, Object> map = null;
			try {
				map = userService.getErrTimes(userName);
			} catch (Exception errorExcepiton) {
				logger.error(errorExcepiton);
			}
			if(null != map){
				int errTimes = Integer.parseInt((map.get("err_times") == null)?"0":map.get("err_times").toString());
				String isEnable = "1";
				if(errTimes < Integer.parseInt(PubData.getData("ERROR_TIMES").toString())){	
					errTimes ++;
					userService.updateEnable(userName, errTimes+"", isEnable);
					logger.info("["+userName+"]保存登录次数:["+errTimes+"],用户状态:["+isEnable+"]成功!");
				}else{
					isEnable = "0";
					userService.updateEnable(userName, errTimes+"", isEnable);
					logger.info("["+userName+"]锁定!");
				}
			}
		}
	}
}
