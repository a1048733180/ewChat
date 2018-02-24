package com.ewei.chat.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.ewei.chat.pojo.User;
import com.ewei.chat.service.UserService;

@Controller
@SessionAttributes("userid")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 个人资料展示
	 * @param userid 用户id,根据用户id查询数据
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/{userid}/message")
	public ModelAndView message(@PathVariable("userid") String userid,ModelAndView mav) {
		User user = userService.selectUserById(userid);
		//测试代码
		if(user == null) {
			System.out.println("user为空");
			mav.setViewName("login");
			return mav;
		}
		mav.addObject("user", user);
		mav.setViewName("setting");//info-setting是展示个人信息的页面,到时候看前端页面
		return mav;
	}
	
	/**
	 * 修改个人资料
	 * @param userid
	 * @param user 读取前端文本框的数据封装成User传回后台
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/{userid}/modify",method=RequestMethod.POST)
	public ModelAndView modify(@PathVariable("userid") String userid,User user,ModelAndView mav) {
		boolean a = userService.update(user);
		if(!a) {
			System.out.println("测试代码：update失败");
			mav.setViewName("login");
		}
		mav.addObject("message","更新成功");
		mav.setViewName("setting");
		return mav;
	}
	
	/**
	 * 
	 * @param userid 用户id
	 * @param password 原密码
	 * @param npassword 新密码
	 * @param mav
	 * @return 新密码和原密码相同时也更新成功
	 *
	 */
	@RequestMapping(value="/{userid}/modifypassword")
	public ModelAndView modifyPassword(@PathVariable("userid") String userid,@RequestParam("password") String password,@RequestParam("npassword") String npassword,ModelAndView mav) {
		//判断用户输入的原密码是否正确
		User user = userService.selectUserById(userid);
		System.out.println(user.getPassword());
		System.out.println(password);
		if(!user.getPassword().equals(password)) {
			System.out.println("密码错误");
			mav.addObject("message","密码错误，请重新输入");
			mav.setViewName("redirect:/{userid}/seepass");//返回修改密码的界面
		}else {
			user.setPassword(npassword);
			if(userService.update(user)) {
				mav.addObject("message","修改密码成功");
				System.out.println("测试代码—修改密码成功");
				mav.setViewName("redirect:/{userid}/seepass");//返回修改密码的界面
			}
		}
		return mav;
	} 
	
	/**
	 * 修改密码页面
	 * @param mav
	 * @return
	 */
	@RequestMapping(value="/{userid}/seepass",method=RequestMethod.GET)
	public ModelAndView seePass(ModelAndView mav) {
		mav.setViewName("seepass");
		return mav;
	}
}
