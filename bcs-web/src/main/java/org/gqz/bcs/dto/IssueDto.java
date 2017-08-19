package org.gqz.bcs.dto;

import org.gqz.bcs.model.Issue;
import org.hibernate.validator.constraints.NotEmpty;

public class IssueDto {
	/**
	 * issue id
	 */
	private int id;
	
	/**
	 * issue title
	 */
	private String title;
	
	/**
	 * issue description
	 */
	private String description;
	
	/**
	 * issue solution
	 */
	private String solution;
	
	/**
	 * the user who create the issue and solution
	 */
	private String userid;
	
	
	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param solution
	 * @param userid
	 * @param date
	 */
	public IssueDto(Issue issue) {
		this.setId(issue.getId());
		this.setTitle(issue.getTitle());
		this.setDescription(issue.getDescription());
		this.setSolution(issue.getSolution());
		this.setUserid(issue.getUserid());
	}

	/**
	 * 
	 */
	public IssueDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	@NotEmpty(message = "Title can not be null")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	@NotEmpty(message = "Description can not be null")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	
	public Issue getIssue() {
		Issue issue = new Issue();
		
		issue.setId(this.getId());
		issue.setTitle(this.getTitle());
		issue.setDescription(this.getDescription());
		issue.setSolution(this.getSolution());
		issue.setUserid(this.getUserid());
		
		return issue;
	}
}
