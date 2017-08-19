package org.gqz.bcs.controller;

import org.gqz.bcs.dto.IssueDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/login")
public class LoginController {

	@RequestMapping("/login")
	public String list(Model model) {
		
		model.addAttribute("userDto", new IssueDto());
		
		return "login/login";
	}
	
}
