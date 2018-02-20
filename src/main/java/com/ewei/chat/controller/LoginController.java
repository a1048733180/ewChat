package com.ewei.chat.controller;


import javax.annotation.Resource;





import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> upstream/development
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ewei.chat.service.UserService;
import com.ewei.chat.pojo.User;

import com.ewei.chat.service.LogService;


@Controller
public class LoginController {

	
	@Resource
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	private String login(Model model) {
		System.out.println("你好,这是get");
		return "login";
	}
	
	//登录
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("userid") String userid,@RequestParam("password") String password,ModelAndView mv) { 
		System.out.println("测试代码 进入了login");
		User user = userService.selectUserById(userid);
		if(user != null) {
			if(!user.getPassword().equals(password)) {
				//测试代码
				System.out.println("密码错误");
				//向前端发送密码错误的提示
				mv.addObject("message","密码错误，请重新输入");
				//跳转到登录页面
				mv.setViewName("forward:/login");
			}else {
				//测试代码
				System.out.println("登录成功");
				mv.addObject("message","登录成功，正在登录");
				//跳转到主页面
				mv.setViewName("redirect:/main");
			}		
		}else {
			System.out.println("账号不存在");
			mv.addObject("message","账号不存在，请重新输入");
			mv.setViewName("forward:/login");
		}
		return mv;
	}
	
}
