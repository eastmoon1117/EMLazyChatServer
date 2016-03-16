package com.jared.emlazychat.control;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Use;

import com.jared.emlazychat.pojo.User;
import com.jared.emlazychat.service.UserService;
import com.jared.emlazychat.vo.ClientAccount;

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
	
	@Autowired
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
			/*User user = new User();
			user.setAccount(account);
			user.setArea("hangzhou");
			user.setSign("别驻足，梦想要不停追逐!");
		    user.setName("eastmoon");
		    user.setSex(2);
			user.setToken(UUID.randomUUID().toString());			
			map.put("flag", true);
			map.put("data", ClientAccount.toAccount(user));
			*/
			System.out.println("login:"+account+":"+password);

			User user = userService.findUserByAccount(account);
			if (user == null) {
				map.put("flag", false);
				map.put("errorCode", LOGIN_ACCOUNT_MISS);
				map.put("errorString", "用户不存在");
			} else {
				if (password.equals(user.getPassword())) {
					user.setToken(UUID.randomUUID().toString());
					userService.updateToken(user);
	    			System.out.println("login:"+user.getAccount()+":"+password);

					user = userService.findUserByAccount(account);
					
					map.put("flag", true);
					map.put("data", ClientAccount.toAccount(user));
				} else {
					map.put("flag", false);
					map.put("errorCode", LOGIN_PASSWORD_ERROR);
					map.put("errorString", "用户密码错误");
				}
			}/**/
		}
		return new Gson().toJson(map);
	}
    
    @RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(String account, String password) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	if(account == null || password == null) {
    		map.put("flag", false);
    		map.put("errorCode", REGISTER_ACCOUNT_EXIST);
    		map.put("errorString", "用户名已存在");
    	} else {
    		if(!userService.isExist(account)) {
    			map.put("flag", true);
    			User user = userService.addUser(account, password);
    			user.setToken(UUID.randomUUID().toString());
    			userService.updateToken(user);
    			user = userService.findUserByAccount(account);
    			map.put("data", ClientAccount.toAccount(user));
    		    System.out.println("addUser"+":"+user.getAccount()+":"+user.getPassword());
    		} else {
    			map.put("flag", false);
    			map.put("errorCode", REGISTER_ACCOUNT_EXIST);
    			map.put("errorString", "用户名已经存在");
    		}
    	}
    	
		return new Gson().toJson(map);
    }
    
}
