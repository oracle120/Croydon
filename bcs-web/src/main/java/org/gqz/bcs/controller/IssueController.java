package org.gqz.bcs.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.gqz.bcs.dto.IssueDto;
import org.gqz.bcs.model.Issue;
import org.gqz.bcs.service.IIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/issue")
public class IssueController {
	
	private IIssueService issueService;

	public IIssueService getIssueService() {
		return issueService;
	}

	@Inject
	public void setIssueService(IIssueService issueService) {
		this.issueService = issueService;
	}

	@RequestMapping("/issues")
	public String list(Model model) {
		model.addAttribute("issues", issueService.findIssue());
		return "issue/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("issueDto", new IssueDto());
		return "issue/add";
	}   
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@Valid IssueDto issueDto, BindingResult br, Model model) {
//		if (br.hasErrors()) {
//			//initAddUser(model);
///			return "issue/add";
//		}
		issueService.add(issueDto.getIssue());
		
		return "redirect:/admin/issue/issues";
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable int id) {
		issueService.delete(id);
		return "redirect:/admin/issue/issues";
	} 
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable int id, Model model) {
		Issue u = issueService.load(id);
		model.addAttribute("issueDto",new IssueDto(u));
		return "issue/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable int id, @Valid IssueDto issueDto, BindingResult br, Model model) {
//		if (br.hasErrors()) {
//			initAddUser(model);
//			return "user/update";
//		}
		Issue ou = issueService.load(id);
		ou.setDescription(issueDto.getDescription());
		ou.setSolution(issueDto.getSolution());
		ou.setTitle(issueDto.getTitle());
		ou.setUserid(issueDto.getUserid());
		issueService.update(ou);
		return "redirect:/admin/issue/issues";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable int id, Model model) {
		model.addAttribute(issueService.load(id));
		model.addAttribute("islogin", "yes");
		return "issue/show";
	}
}
