package org.gqz.bcs.dao;

import java.util.List;

import org.gqz.basic.dao.IBaseDao;
import org.gqz.basic.model.Pager;
import org.gqz.bcs.model.Issue;

public interface IIssueDao extends IBaseDao<Issue> {

	public List<Issue> listIssuse();
	
	public Pager<Issue> findIssue();
}
