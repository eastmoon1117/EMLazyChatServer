package com.jared.emlazychat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jared.emlazychat.User;
import com.jared.emlazychat.UserService;
import com.jared.emlazychat.ClientAccount;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class LoginControl {
	private final static int	LOGIN_PASSWORD_ERROR	= 100;
	private final static int	LOGIN_ACCOUNT_MISS		= 101;

	private final static int	REGISTER_ACCOUNT_EXIST	= 150;
	
	UserService					userService;
    
    @RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
	    System.out.println("login:"+account+":"+password);
		//if (account == null || password == null) {

		if (account == null || password == null 
				|| (!account.equals("test")) || (!password.equals("123"))) {
			map.put("flag", false);
			map.put("errorCode", LOGIN_ACCOUNT_MISS);
			map.put("errorString", "用户不存在");
		} else {
			User user = new User();
			user.setAccount(account);
			user.setArea("hangzhou");
			user.setSign("哈哈");
		    user.setName("eastmoon");
		    user.setSex(0);
			user.setToken(UUID.randomUUID().toString());			
			map.put("flag", true);
			map.put("data", ClientAccount.toAccount(user));
			/*
			User user = userService.findUserByAccount(account);
			if (user == null) {
				map.put("flag", false);
				map.put("errorCode", LOGIN_ACCOUNT_MISS);
				map.put("errorString", "用户不存在");
			} else {
				if (password.equals(user.getPassword())) {
					user.setToken(UUID.randomUUID().toString());
					userService.updateToken(user);

					user = userService.findUserByAccount(account);
					
					map.put("flag", true);
					map.put("data", ClientAccount.toAccount(user));
				} else {
					map.put("flag", false);
					map.put("errorCode", LOGIN_PASSWORD_ERROR);
					map.put("errorString", "用户密码错误");
				}
			}
			*/
		}
		return new Gson().toJson(map);
	}
	
}
