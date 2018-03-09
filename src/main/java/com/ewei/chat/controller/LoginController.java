package com.ewei.chat.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ewei.chat.service.UserService;
import com.ewei.chat.utils.DefinedUtil;
import com.ewei.chat.utils.LogUtil;
import com.mysql.jdbc.log.LogUtils;
import com.ewei.chat.pojo.User;

import com.ewei.chat.service.LogService;


@Controller
public class LoginController {
	@Resource
	private UserService userService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	private String login(Model model) {
		System.out.println("你好,这是get");
		return "login";
	}
	
	//登录
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("userid") String userid,@RequestParam("password") String password,ModelAndView mv,HttpSession httpSession,DefinedUtil definedUtil) { 
		System.out.println("测试代码 进入了login");
		User user = userService.selectUserById(userid);
		if(user != null) {
			if(!user.getPassword().equals(password)) {
				//测试代码
				System.out.println("密码错误");
				//向前端发送密码错误的提示
				mv.addObject("message","密码错误，请重新输入");
				//跳转到登录页面
				mv.setViewName("login");
			}else {
				//测试代码
				System.out.println("登录成功");

				//	记录进入日志
				LogUtil.insert(userid, definedUtil.LOG_TYPE_LOGIN, definedUtil.LOG_DETAIL_USER_LOGIN, logService);
				//存入httpSession中
				httpSession.setAttribute("userid", userid);
				mv.addObject("message","登录成功，正在登录");
				mv.addObject("user",user);
				//跳转到主页面
				mv.setViewName("main");
			}		
		}else {
			System.out.println("账号不存在");
			mv.addObject("message","账号不存在，请重新输入");
			mv.setViewName("login");
		}
		return mv;
	}
	
	//登陆成功后跳转的界面，待修改
	@RequestMapping(value="main")
	public ModelAndView main(ModelAndView model) {
		model.addObject("message","123");
		System.out.println("测试代码2-main");
		model.setViewName("main");
		return model;
	}
	
	
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public ModelAndView register(ModelAndView mav) {
		mav.setViewName("register");
		return mav;
	}
	/**
	 * 注册功能
	 * @param userid 用户Id(一般情况下id不能重复)
	 * @param password 用户密码
	 * @param nickname 用户昵称
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public ModelAndView register(@RequestParam("userid") String userid,@RequestParam("password") String password,@RequestParam("nickname") String nickname,ModelAndView mav,DefinedUtil definedUtil) {
		User user = new User();
		//校验用户名是否在数据库中已经存在，若存在则返回错误信息
		if(userService.selectUserById(userid) != null) {
			mav.addObject("message","用户名已被注册");//后期可在前端输入用户名后直接校验
			mav.setViewName("register"); 
			System.out.println("测试代码3，register注册失败，用户名已被注册");
			return mav;
		}
		user.setUserid(userid);
		user.setPassword(password);
		user.setNickname(nickname);
		userService.insert(user);
		LogUtil.insert(userid, definedUtil.LOG_TYPE_REGIST,definedUtil.LOG_DETAIL_USER_REGIST , logService);
		mav.addObject("message","注册成功，请登录");
		mav.setViewName("login");
		System.out.println("register注册成功");
		return mav;
	}
	
}
