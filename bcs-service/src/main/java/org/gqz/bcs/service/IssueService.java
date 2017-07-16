/**
 * 
 */
package org.gqz.bcs.service;

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
		issueDao.add(issue);
	}

	@Override
	public void delete(int id) {
		issueDao.delete(id);
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
