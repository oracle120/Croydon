package org.gqz.bcs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.gqz.bcs.dto.UserDto;
import org.gqz.bcs.model.Role;
import org.gqz.bcs.model.RoleType;
import org.gqz.bcs.model.User;
import org.gqz.bcs.service.IRoleService;
import org.gqz.bcs.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	
	private IUserService userService;
	private IRoleService roleService;
	
	public IRoleService getRoleService() {
		return roleService;
	}
	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/users")
	public String list(Model model) {
		model.addAttribute("datas",userService.findUser());
		return "user/list";
	}
	
	private void initAddUser(Model model) {
		model.addAttribute("roles",roleService.listRole());
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("userDto",new UserDto());//user,user
		initAddUser(model);
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid UserDto userDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			initAddUser(model);
			return "user/add";
		}
		userService.add(userDto.getUser(), userDto.getRoleIds());
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		User u = userService.load(id);
		model.addAttribute("userDto",new UserDto(u,
				userService.listUserRoleIds(id)));//user,user
		initAddUser(model);
		return "user/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid UserDto userDto,BindingResult br,Model model) {
		if(br.hasErrors()) {
			System.out.println(br.hasErrors());
			initAddUser(model);
			return "user/update";
		}
		User ou = userService.load(id);
		ou.setNickname(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		ou.setEmail(userDto.getEmail());
		ou.setStatus(userDto.getStatus());
		userService.update(ou, userDto.getRoleIds());
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/updateStatus/{id}",method=RequestMethod.GET)
	public String updateStatus(@PathVariable int id) {
		userService.updateStatus(id);
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model) {
		model.addAttribute(userService.load(id));
		model.addAttribute("rs",userService.listUserRoles(id));
		return "user/show";
	}
	
	@RequestMapping("/showSelf")
	public String showSelf(Model model,HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		model.addAttribute(user);
		model.addAttribute("rs",userService.listUserRoles(user.getId()));
		return "user/show";
	}
	
	@RequestMapping(value="/updatePwd",method=RequestMethod.GET)
	public String updatePwd(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(u);
		return "user/updatePwd";
	}
	
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(int id,String oldPwd,String password) {
		userService.updatePwd(id, oldPwd, password);
		return "redirect:/admin/user/showSelf";
	}
	
	/*@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public String updatePwd(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(u);
		return "user/updatePwd";
	}*/
	
	
	@RequestMapping(value="/updateSelf",method=RequestMethod.GET)
	public String updateSelf(Model model,HttpSession session) {
		User u = (User)session.getAttribute("loginUser");
		model.addAttribute(new UserDto(u));
		return "user/updateSelf";
	}
	
	@RequestMapping(value="/updateSelf",method=RequestMethod.POST)
	public String updateSelf(@Valid UserDto userDto,BindingResult br,Model model,HttpSession session) {
		if(br.hasErrors()) {
			return "user/updateSelf";
		}
		User ou = userService.load(userDto.getId());
		ou.setNickname(userDto.getNickname());
		ou.setPhone(userDto.getPhone());
		ou.setEmail(userDto.getEmail());
		userService.update(ou);
		session.setAttribute("loginUser", ou);
		return "redirect:/admin/user/showSelf";
	}
	
	@RequestMapping("/listChannels/{uid}")
	public String listChannels(@PathVariable int uid,Model model) {
		model.addAttribute(userService.load(uid));
		List<Role> rs = userService.listUserRoles(uid);
		for(Role r:rs) {
			if(r.getRoleType()==RoleType.ROLE_ADMIN) {
				model.addAttribute("isAdmin",1);
			}
		}
		return "/user/listChannel";
	}
	
}
