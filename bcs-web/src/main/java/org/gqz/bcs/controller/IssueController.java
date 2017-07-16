package org.gqz.bcs.controller;

import javax.inject.Inject;

import org.gqz.bcs.service.IIssueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
