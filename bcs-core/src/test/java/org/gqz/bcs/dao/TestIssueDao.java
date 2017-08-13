package org.gqz.bcs.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.gqz.bcs.model.Issue;
import org.gqz.bcs.test.util.AbstractDbUnitTestCase;
import org.gqz.bcs.test.util.EntitiesHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestIssueDao extends AbstractDbUnitTestCase {

	@Inject
	private SessionFactory sessionFactory;

	@Inject
	private IIssueDao issueDao;

	@Before
	public void setUp() throws SQLException, IOException, DatabaseUnitException {
		/*Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));

		this.backupAllTable();
		
		IDataSet ds = createDateSet("gqz_issue");
		DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);*/
	}

	
	@Test
	public void testListUserRoles() throws DatabaseUnitException, SQLException {
		
		/*List<Issue> actuals = Arrays.asList(new Issue(1, "title_1", "description", "solution", "userid"),
				new Issue(2, "title_2", "description", "solution", "userid"),
				new Issue(3, "title_3", "description", "solution", "userid"));
		List<Issue> issues = issueDao.listIssuse();
		
		EntitiesHelper.assertIssues(issues, actuals);*/
	}
	
	/**
	@Test
	public void testAddUserGroup() throws DatabaseUnitException, SQLException {
		
		Group group = groupDao.load(1);
		User user = userDao.load(1);
		userDao.addUserGroup(user, group);
		
		UserGroup ug = userDao.loadUserGroup(1, 1);
		assertNotNull(ug);
		assertEquals(ug.getGroup().getId(), 1);
		assertEquals(ug.getUser().getId(), 1);
	}
	
	@Test
	public void testAddUserRole() throws DatabaseUnitException, SQLException {

		Role role = roleDao.load(1);
		User user = userDao.load(1);
		userDao.addUserRole(user, role);
		
		UserRole ur = userDao.loadUserRole(1, 1);
		assertNotNull(ur);
		assertEquals(ur.getRole().getId(), 1);
		assertEquals(ur.getUser().getId(), 1);
	}
	
	@Test
	public void testDeleteUserRoles() throws DatabaseUnitException, SQLException {

		int uid = 2;
		userDao.deleteUserRoles(uid);
		List<Role> urs = userDao.listUserRoles(uid);
		assertTrue(urs.size() <= 0);
	}
	
	@Test
	public void testDeleteUserRole() throws DatabaseUnitException, SQLException {

		int uid = 1;
		int rid = 1;
		userDao.deleteUserRole(uid,rid);
		assertNull(userDao.loadUserRole(uid, rid));
	}
	
	@Test
	public void testDeleteUserGroup() throws DatabaseUnitException, SQLException {

		int uid = 1;
		int gid = 2;
		userDao.deleteUserRole(uid,gid);
		assertNull(userDao.loadUserGroup(uid, gid));
	}
	*/
	
	@After
	public void tearDown() throws FileNotFoundException, DatabaseUnitException, SQLException {
		/*SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		this.resumeTable();*/
	}
}
