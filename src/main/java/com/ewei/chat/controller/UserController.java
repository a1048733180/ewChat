package com.ewei.chat.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {
	@Autowired
	private LogService logService;
	@Autowired
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
	
}
