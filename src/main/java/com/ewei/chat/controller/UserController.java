package com.ewei.chat.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.annotation.Resource;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ewei.chat.pojo.User;
import com.ewei.chat.service.LogService;
import com.ewei.chat.service.UserService;
import com.ewei.chat.utils.DefinedUtil;
import com.ewei.chat.utils.LogUtil;

@Controller
@SessionAttributes("userid")
public class UserController {
	@Autowired
	private LogService logService;
	@Resource
	private UserService userService;
	@RequestMapping(value = "{userid}/upload")
	public String upload(@PathVariable("userid") String userid, MultipartFile file, 
			RedirectAttributes attributes,DefinedUtil definedUtil) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		String fileName = userid + "." + ext;
		try {
			file.transferTo(new File(("E:\\upload\\" + fileName)));
			User user = userService.selectUserById(userid);
			user.setProfilehead(fileName);
			boolean flag = userService.update(user);
			if (flag) {
				LogUtil.insert(userid, definedUtil.LOG_TYPE_UPDATE, definedUtil.LOG_DETAIL_UPDATE_PROFILEHEAD,
						logService);
				attributes.addFlashAttribute("message", "[" + userid + "]头像更新成功!");
				System.out.println("头像更新成功");
			} else {
				attributes.addFlashAttribute("error", "[" + userid + "]头像更新失败!");
				System.out.println("头像更新失败");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error", "[" + userid + "]头像更新失败!");
		}
		return "redirect:/{userid}/config";
	}
	
    @RequestMapping(value = "{userid}/config")
    public ModelAndView setting(@PathVariable("userid") String userid){
        ModelAndView view = new ModelAndView("info-setting");
        User user = userService.selectUserById(userid);
        view.addObject("user", user);
        return view;
    }
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
