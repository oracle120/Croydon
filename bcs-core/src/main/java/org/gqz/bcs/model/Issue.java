package org.gqz.bcs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "gqz_issue")
public class Issue {
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
	 * create time of the issue
	 */
	private Date date;

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param solution
	 * @param userid
	 * @param date
	 */
	public Issue(int id, String title, String description, String solution, String userid, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.solution = solution;
		this.userid = userid;
		this.date = date;
	}

	/**
	 * @param id
	 * @param title
	 * @param description
	 * @param solution
	 * @param userid
	 */
	public Issue(int id, String title, String description, String solution, String userid) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.solution = solution;
		this.userid = userid;
	}



	public Issue() {
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull(message = "The issue title can not null")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull(message = "The issue description can not null")
	public String getDescription() {
		return description;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
