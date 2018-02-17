package com.ewei.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping(value="login")
	private String login(Model model) {
		System.out.println("你好");
		return "login";
	}
}
