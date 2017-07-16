/**
 * 
 */
package org.gqz.bcs.service;

import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.expect;

import javax.inject.Inject;

import org.gqz.basic.model.Pager;
import org.gqz.bcs.dao.IIssueDao;
import org.gqz.bcs.model.Issue;
import org.gqz.bcs.service.IIssueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jinhuer168
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-beans.xml")
public class TestIssueService {
	@Inject
	private IIssueDao issueDao;
	@Inject
	private IIssueService issueService;
	
	//private User baseUser = new User(1, "admin1", "123", "admin1", "admin1@","123",0);

	@Test
	public void testDelete() {
		reset(issueDao);
		
		int id = 2;
		issueDao.delete(id);
		expectLastCall();

		replay(issueDao);
		issueService.delete(id);
		verify(issueDao);
	}
	
	/*
	@Test(expected=CmsException.class)
	public void testUpdateStatusNoUser() {
		reset(userDao);
		
		int uid = 1;
		
		expect(userDao.load(uid)).andReturn(null);
		userDao.update(baseUser);
		expectLastCall();
		replay(userDao);
		userService.updateStatus(uid);
		Assert.assertEquals(baseUser.getStatus(), 1);
		verify(userDao);
		
	}
	
	@Test
	public void testUpdateStatus() {
		reset(userDao);
		
		int uid = 1;
		
		expect(userDao.load(uid)).andReturn(baseUser);
		userDao.update(baseUser);
		expectLastCall();
		replay(userDao);
		userService.updateStatus(uid);
		Assert.assertEquals(baseUser.getStatus(), 1);
		verify(userDao);
		
	}
	*/
	
	@Test
	public void testFindIssue() {
		reset(issueDao);
		expect(issueDao.findIssue()).andReturn(new Pager<Issue>());
		replay(issueDao);
		issueService.findIssue();
		verify(issueDao);
		
	}
	/*
	@Test
	public void testAdd1() {
		reset(userDao, roleDao, groupDao);

		Integer[] rids = {1,2};
		Integer[] gids = {2,3};
		
		Role r = new Role(1,"ADMIN",RoleType.ROLE_ADMIN);
		Group g = new Group(2, "group2");
		
		expect(userDao.loadByUsername("admin1")).andReturn(null);
		expect(userDao.add(baseUser)).andReturn(baseUser);
		
		expect(roleDao.load(rids[0])).andReturn(r);
		userDao.addUserRole(baseUser, r);
		expectLastCall();
		
		r.setId(2);
		expect(roleDao.load(rids[1])).andReturn(r);
		userDao.addUserRole(baseUser, r);
		expectLastCall();
		
		expect(groupDao.load(gids[0])).andReturn(g);
		userDao.addUserGroup(baseUser, g);
		expectLastCall();
		
		g.setId(3);
		expect(groupDao.load(gids[1])).andReturn(g);
		userDao.addUserGroup(baseUser, g);
		expectLastCall();
		
		replay(userDao, roleDao, groupDao);
		userService.add(baseUser, rids, gids);
		verify(userDao, roleDao, groupDao);
	}
	
	@Test
	public void testAdd2() {
		reset(userDao, roleDao, groupDao);

		Integer[] rids = {1,2,5,6};
		Integer[] gids = {2,3,4,5};
		
		Role r = new Role(1,"ADMIN",RoleType.ROLE_ADMIN);
		Group g = new Group(2, "group2");
		
		expect(userDao.loadByUsername("admin1")).andReturn(null);
		expect(userDao.add(baseUser)).andReturn(baseUser);
		// EasyMock.gt(0),动态参数，只要大于0的都可以
		expect(roleDao.load(EasyMock.gt(0))).andReturn(r).times(4);
		userDao.addUserRole(baseUser, r);
		expectLastCall().times(4);
		
		expect(groupDao.load(EasyMock.gt(0))).andReturn(g).times(4);
		userDao.addUserGroup(baseUser, g);
		expectLastCall().times(4);
		
		replay(userDao, roleDao, groupDao);
		userService.add(baseUser, rids, gids);
		verify(userDao, roleDao, groupDao);
	}
	
	@Test(expected=CmsException.class)
	public void testAddNoGroup() {
		reset(userDao, roleDao, groupDao);

		Integer[] rids = {1,2,5,6};
		Integer[] gids = {2,3,4,5};
		
		Role r = new Role(1,"ADMIN",RoleType.ROLE_ADMIN);
		Group g = new Group(2, "group2");
		
		expect(userDao.loadByUsername("admin1")).andReturn(null);
		expect(userDao.add(baseUser)).andReturn(baseUser);
		// EasyMock.gt(0),动态参数，只要大于0的都可以
		expect(roleDao.load(EasyMock.gt(0))).andReturn(r).times(4);
		userDao.addUserRole(baseUser, r);
		expectLastCall().times(4);
		
		expect(groupDao.load(EasyMock.gt(0))).andReturn(null).times(4);
		userDao.addUserGroup(baseUser, g);
		expectLastCall().times(4);
		
		replay(userDao, roleDao, groupDao);
		userService.add(baseUser, rids, gids);
		verify(userDao, roleDao, groupDao);
	}
	
	@Test(expected=CmsException.class)
	public void testAddNoRole() {
		reset(userDao, roleDao, groupDao);

		Integer[] rids = {1,2,5,6};
		Integer[] gids = {2,3,4,5};
		
		Role r = new Role(1,"ADMIN",RoleType.ROLE_ADMIN);
		Group g = new Group(2, "group2");
		
		expect(userDao.loadByUsername("admin1")).andReturn(null);
		expect(userDao.add(baseUser)).andReturn(baseUser);
		// EasyMock.gt(0),动态参数，只要大于0的都可以
		expect(roleDao.load(EasyMock.gt(0))).andReturn(null).times(4);
		userDao.addUserRole(baseUser, r);
		expectLastCall().times(4);
		
		expect(groupDao.load(EasyMock.gt(0))).andReturn(g).times(4);
		userDao.addUserGroup(baseUser, g);
		expectLastCall().times(4);
		
		replay(userDao, roleDao, groupDao);
		userService.add(baseUser, rids, gids);
		verify(userDao, roleDao, groupDao);
	}
	
	@Test(expected=CmsException.class)
	public void testAddHasUser() {
		reset(userDao, roleDao, groupDao);

		Integer[] rids = {1,2,5,6};
		Integer[] gids = {2,3,4,5};
		
		Role r = new Role(1,"ADMIN",RoleType.ROLE_ADMIN);
		Group g = new Group(2, "group2");
		
		expect(userDao.loadByUsername("admin1")).andReturn(baseUser);
		
		replay(userDao, roleDao, groupDao);
		userService.add(baseUser, rids, gids);
		verify(userDao, roleDao, groupDao);
	}
	

	@Test
	public void testUpdateUser() {
		reset(userDao, roleDao, groupDao);

		Integer[] rids = {1,2};
		Integer[] gids = {1,2};
		List<Integer> erids = Arrays.asList(2,3);
		List<Integer> egids = Arrays.asList(2,3);
		Group g = new Group();
		Role r = new Role();
		
		expect(userDao.listUserRoleIds(baseUser.getId())).andReturn(erids);
		expect(userDao.listUserGroupIds(baseUser.getId())).andReturn(egids);
		expect(roleDao.load(1)).andReturn(r);
		userDao.addUserRole(baseUser, r);
		expectLastCall();
		expect(groupDao.load(1)).andReturn(g);
		userDao.addUserGroup(baseUser, g);
		expectLastCall();
		
		userDao.deleteUserRole(baseUser.getId(), 3);
		userDao.deleteUserGroup(baseUser.getId(), 3);

		replay(userDao, roleDao, groupDao);
		userService.update(baseUser, rids, gids);
		verify(userDao, roleDao, groupDao);
	}
	*/
}
