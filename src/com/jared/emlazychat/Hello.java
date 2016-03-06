package com.jared.emlazychat;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.servlet.ModelAndView;
@Controller
public class Hello {
	
	public Hello() {}  
    
	@RequestMapping(value = "/test/{user}", method = RequestMethod.GET)  
    public ModelAndView myMethod(HttpServletRequest request,HttpServletResponse response,   
            @PathVariable("user") String user, ModelMap modelMap) throws Exception {  
        modelMap.put("loginUser", user);  
        return new ModelAndView("/login/hello", modelMap);  
    }  
    
    @RequestMapping("index")  
    public ModelAndView index(){  
        //创建模型跟视图，用于渲染页面。并且指定要返回的页面为welcome页面  
        ModelAndView mav = new ModelAndView("welcome");  
        return mav;  
    }  
}
