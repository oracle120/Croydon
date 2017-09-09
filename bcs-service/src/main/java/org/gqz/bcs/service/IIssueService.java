/**
 * 
 */
package org.gqz.bcs.service;

import java.util.List;

import org.gqz.basic.model.Pager;
import org.gqz.bcs.model.Issue;

/**
 * @author jinhuer168
 *
 */
public interface IIssueService {
	
	/**
	 * 新增问题
	 * @param issue
	 */
	public void add(Issue issue);

	/**
	 * 删除问题
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 根据id，获取问题信息
	 * @param id
	 * @return
	 */
	public Issue load(int id);
	
	/**
	 * 根据文章id，或者该文章所属用户
	 * @param id
	 * @return
	 */
	public String getUserByid(int id);

	/**
	 * 更新问题
	 * @param issue
	 */
	public void update(Issue issue);

	/**
	 * 问题列表
	 * @return
	 */
	public List<Issue> listIssue();

	/**
	 * 分页查找问题
	 * @return
	 */
	public Pager<Issue> findIssue();

}
