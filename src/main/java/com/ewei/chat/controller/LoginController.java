package com.ewei.chat.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ewei.chat.service.LogService;


@Controller
public class LoginController {
	@Autowired
	private LogService logService;
	@RequestMapping(value="login")
	private String login(Model model) {
		System.out.println(logService);
		System.out.println("你好");
		return "login";
	}
}
