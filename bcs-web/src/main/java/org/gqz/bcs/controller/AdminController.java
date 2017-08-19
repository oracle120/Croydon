/**
 * 
 */
package org.gqz.bcs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jinhuer168
 *
 */
@Controller
public class AdminController {
	@RequestMapping(value = {"/admin", "/"})
	public String index() {
		return "redirect:/admin/issue/issues";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "redirect:/admin/login/login";
	}
}
