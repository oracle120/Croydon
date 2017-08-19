package org.gqz.bcs.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.gqz.basic.model.Pager;
import org.gqz.bcs.dao.IRoleDao;
import org.gqz.bcs.dao.IUserDao;
import org.gqz.bcs.model.BcsException;
import org.gqz.bcs.model.Role;
import org.gqz.bcs.model.User;
import org.gqz.bcs.service.IUserService;
import org.gqz.bcs.utils.SecurityUtil;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {

	private IUserDao userDao;
	private IRoleDao roleDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	@Inject
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Inject
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {
		// TODO 暂时没有gid
	}

	@Override
	public void delete(int id) {
		// TODO 需要进行用户是否有文章的判断

		// 1、删除用户管理的组对象
		// userDao.deleteUserGroups(id);
		// 2、删除用户管理的角色对象
		userDao.deleteUserRoles(id);
		userDao.delete(id);
	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {
		// TODO 暂时没有group

	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void updatePwd(int uid, String oldPwd, String newPwd) {
		try {
			User u = userDao.load(uid);
			if (!SecurityUtil.md5(u.getUsername(), oldPwd).equals(u.getPassword())) {
				throw new BcsException("原始密码输入不正确");
			}
			u.setPassword(SecurityUtil.md5(u.getUsername(), newPwd));
			userDao.update(u);
		} catch (NoSuchAlgorithmException e) {
			throw new BcsException("更新密码失败:" + e.getMessage());
		}
	}

	@Override
	public void updateStatus(int id) {
		User u = userDao.load(id);
		if (u == null)
			throw new BcsException("修改状态的用户不存在");
		if (u.getStatus() == 0)
			u.setStatus(1);
		else
			u.setStatus(0);
		userDao.update(u);
	}

	@Override
	public Pager<User> findUser() {
		return userDao.findUser();
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public List<Role> listUserRoles(int id) {
		return userDao.listUserRoles(id);
	}

	@Override
	public List<Integer> listUserRoleIds(int id) {
		return userDao.listUserRoleIds(id);
	}

	@Override
	public List<User> listRoleUsers(int rid) {
		return userDao.listRoleUsers(rid);
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.loadByUsername(username);
		if (user == null)
			throw new BcsException("用户名或者密码不正确");
		try {
			if (!SecurityUtil.md5(username, password).equals(user.getPassword())) {
				throw new BcsException("用户名或者密码不正确");
			}
		} catch (NoSuchAlgorithmException e) {
			throw new BcsException("密码加密失败:" + e.getMessage());
		}
		if (user.getStatus() == 0)
			throw new BcsException("用户已经停用，请与管理员联系");
		return user;
	}

	@Override
	public void update(User user, Integer[] rids) {
		// 1、获取用户已经存在的组id和角色id
		List<Integer> erids = userDao.listUserRoleIds(user.getId());
		// 2、判断，如果erids中不存在rids就要进行添加
		for (Integer rid : rids) {
			if (!erids.contains(rid)) {
				addUserRole(user, rid);
			}
		}
		// 3、进行删除
		for (Integer erid : erids) {
			if (!ArrayUtils.contains(rids, erid)) {
				userDao.deleteUserRole(user.getId(), erid);
			}
		}

	}

	@Override
	public void add(User user, Integer[] rids) {
		User tu = userDao.loadByUsername(user.getUsername());
		if (tu != null)
			throw new BcsException("添加的用户对象已经存在，不能添加");
		user.setCreateDate(new Date());
		try {
			user.setPassword(SecurityUtil.md5(user.getUsername(), user.getPassword()));
		} catch (NoSuchAlgorithmException e) {
			throw new BcsException("密码加密失败:" + e.getMessage());
		}
		userDao.add(user);
		// 添加角色对象
		for (Integer rid : rids) {
			this.addUserRole(user, rid);
		}
	}

	private void addUserRole(User user, int rid) {
		// 1、检查角色对象是否存在，如果不存在，就抛出异常
		Role role = roleDao.load(rid);
		if (role == null)
			throw new BcsException("要添加的用户角色不存在");
		// 2、检查用户角色对象是否已经存在，如果存在，就不添加
		userDao.addUserRole(user, role);
	}
}
