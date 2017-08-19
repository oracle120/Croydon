/**
 * 
 */
package org.gqz.bcs.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.gqz.basic.model.Pager;
import org.gqz.bcs.dao.IIssueDao;
import org.gqz.bcs.model.Issue;
import org.springframework.stereotype.Service;

/**
 * @author jinhuer168
 *
 */
@Service("issueService")
public class IssueService implements IIssueService {

	private IIssueDao issueDao;

	public IIssueDao getIssueDao() {
		return issueDao;
	}
	
	@Inject
	public void setIssueDao(IIssueDao issueDao) {
		this.issueDao = issueDao;
	}

	@Override
	public void add(Issue issue) {
//		Issue is = issueDao.load(issue.getTitle());
//		if (null != is)
//		{
//			throw new BcsException("添加用户对象已存在，不能重复添加！");
//		}
		issue.setDate(new Date());
		System.out.println("----------------->" + issue.getDate());
		issueDao.add(issue);
	}

	@Override
	public void delete(int id) {
		issueDao.delete(id);
		System.out.println("delete-----------");
	}

	@Override
	public Issue load(int id) {
		return issueDao.load(id);
	}

	@Override
	public void update(Issue issue) {
		issueDao.update(issue);
	}

	@Override
	public List<Issue> listIssue() {
		return issueDao.listIssuse();
	}

	@Override
	public Pager<Issue> findIssue() {
		return issueDao.findIssue();
	}

}
