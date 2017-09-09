package org.gqz.bcs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gqz.bcs.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/admin/login/login?logout";
	}
	
	/*@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String username, String password,Model model,HttpSession session) {
		System.out.println(username + "----------" + password);
		User loginUser = userService.login(username, password);
		return "redirect:/admin/issue/issues";
	}*/
	
}
