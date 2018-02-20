package com.ewei.chat.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ewei.chat.pojo.Log;
import com.ewei.chat.service.LogService;

@Controller
public class LogController {
	@Autowired
	private LogService logService;
	@RequestMapping(value="{userid}/log")
//	    @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中
//	    @RequestParam用于将请求参数区数据映射到功能处理方法的参数上。
	public ModelAndView selectAll(@PathVariable("userid") String userid, @RequestParam(defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView("log");
		int pageSize = 5;
		List<Log> list = logService.selectLogByUserid(userid, page, pageSize);
		int count = logService.selectCountByUserid(userid,pageSize);
		mav.addObject("list",list);
		mav.addObject("count",count);
		return mav;
	}
	
}
