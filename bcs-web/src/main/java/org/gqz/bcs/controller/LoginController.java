package org.gqz.bcs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.gqz.bcs.model.User;
import org.gqz.bcs.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/login")
public class LoginController {

	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}
	
	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login/login";
	}
	
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password,Model model,HttpSession session) {
		System.out.println(username + "----------" + password);
		User loginUser = userService.login(username, password);
		return "redirect:/admin/issue/issues";
	}*/
	
}
