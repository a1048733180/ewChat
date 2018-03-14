package com.ewei.chat.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ewei.chat.service.UserService;
import com.ewei.chat.utils.DateUtil;
import com.ewei.chat.utils.DefinedUtil;
import com.ewei.chat.utils.LogUtil;
import com.ewei.chat.pojo.User;

import com.ewei.chat.service.LogService;

@Controller
public class LoginController {
	@Resource
	private UserService userService;

	@Autowired
	private LogService logService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login(Model model) {
		System.out.println("你好,这是get");
		return "login";
	}

	// 登录
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("userid") String userid, @RequestParam("password") String password,
			ModelAndView mv, HttpSession httpSession, DefinedUtil definedUtil) {
		System.out.println("测试代码 进入了login");
		User user = userService.selectUserById(userid);
		if (user != null) {
			if (!user.getPassword().equals(password)) {
				// 测试代码
				System.out.println("密码错误");
				// 向前端发送密码错误的提示
				mv.addObject("message", "密码错误，请重新输入");
				// 跳转到登录页面
				mv.setViewName("login");
			} else {
				// 测试代码
				System.out.println("登录成功");

				// 记录进入日志
				LogUtil.insert(userid, definedUtil.LOG_TYPE_LOGIN, definedUtil.LOG_DETAIL_USER_LOGIN, logService);
				// 存入httpSession中
				httpSession.setAttribute("userid", userid);
				mv.addObject("message", "登录成功，正在登录");
				user.setLasttime(DateUtil.getTime());
				userService.update(user);
				mv.addObject("user", user);
				// 跳转到主页面
				mv.setViewName("demo");
			}
		} else {
			System.out.println("账号不存在");
			mv.addObject("message", "账号不存在，请重新输入");
			mv.setViewName("redirect:/login");
		}
		return mv;
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session, RedirectAttributes attributes, DefinedUtil definedUtil) {
		session.removeAttribute("userid");
		session.removeAttribute("login_status");
		attributes.addFlashAttribute("message", definedUtil.LOGOUT_SUCCESS);
		return "redirect:/login";
	}

	// 登陆成功后跳转的界面，待修改
	@RequestMapping(value = "main")
	public ModelAndView main(ModelAndView model) {
		model.addObject("message", "123");
		System.out.println("测试代码2-main");
		model.setViewName("demo");
		return model;
	}
	
	/**
	 * 验证注册功能中的用户名是否已经被注册
	 * @param user 把前端用户名封装进user
	 * @param response	
	 * @throws Exception
	 */
	@RequestMapping(value = "valider")
	public void valider(@RequestBody User user, HttpServletResponse response) throws Exception {
		System.out.println("测试代码：" + user.getUserid());
		if (userService.selectUserById(user.getUserid()) != null) {
			response.setContentType("text/html,charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print("该用户不可以使用");
		}
	}

	/**
	 * 注册功能
	 * 
	 * @param userid
	 *            用户Id(一般情况下id不能重复)
	 * @param password
	 *            用户密码
	 * @param nickname
	 *            用户昵称
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam("userid") String userid, @RequestParam("password") String password,String nickname,ModelAndView mav, DefinedUtil definedUtil) {
		System.out.println("进入注册界面");
		//默认用户名和登陆名一样
		nickname = userid;
		User user = new User();
		user.setUserid(userid);
		user.setPassword(password);
		user.setNickname(nickname);
		userService.insert(user);
		LogUtil.insert(userid, definedUtil.LOG_TYPE_REGIST, definedUtil.LOG_DETAIL_USER_REGIST, logService);
		mav.addObject("message", "注册成功，请登录");
		mav.setViewName("login");
		System.out.println("register注册成功");
		return mav;
	}

}
