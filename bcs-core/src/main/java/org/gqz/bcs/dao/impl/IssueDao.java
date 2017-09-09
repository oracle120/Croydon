package org.gqz.bcs.dao.impl;

import java.util.List;

import org.gqz.basic.dao.BaseDao;
import org.gqz.basic.model.Pager;
import org.gqz.bcs.dao.IIssueDao;
import org.gqz.bcs.model.Issue;
import org.springframework.stereotype.Repository;

@Repository("IssueDao")
public class IssueDao extends BaseDao<Issue> implements IIssueDao {

	@Override
	public List<Issue> listIssuse() {
		return this.list("from Issue");
	}

	@Override
	public Pager<Issue> findIssue() {
		return this.find("from Issue");
	}

	@Override
	public String getUserById(int id) {
		String hql = "select gi.userid from Issue gi where gi.id=?";
		
		return (String) this.queryObject(hql, id);
	}

}
