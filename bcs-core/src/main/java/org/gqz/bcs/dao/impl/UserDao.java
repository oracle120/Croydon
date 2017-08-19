package org.gqz.bcs.dao.impl;

import java.util.List;

import org.gqz.basic.dao.BaseDao;
import org.gqz.basic.model.Pager;
import org.gqz.bcs.dao.IUserDao;
import org.gqz.bcs.model.Role;
import org.gqz.bcs.model.RoleType;
import org.gqz.bcs.model.User;
import org.gqz.bcs.model.UserRole;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {

	@Override
	public List<Role> listUserRoles(int userId) {
		String hql = "select ur.role from UserRole ur where ur.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserRoleIds(int userId) {
		String hql = "select ur.role.id from UserRole ur where ur.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public UserRole loadUserRole(int userId, int roleId) {
		String hql = "select ur from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id=? and r.id=?";
		return (UserRole) this.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, roleId)
				.uniqueResult();
	}

	@Override
	public User loadByUsername(String username) {
		String hql = "from User where username=?";
		return (User) this.queryObject(hql, username);
	}

	@Override
	public List<User> listRoleUsers(int roleId) {
		String hql = "select ur.user from UserRole ur where ur.role.id=?";
		return this.list(hql, roleId);
	}

	@Override
	public List<User> listRoleUsers(RoleType roleType) {
		String hql = "select ur.user from UserRole ur where ur.role.roleType=?";
		return this.list(hql, roleType);
	}

	@Override
	public void addUserRole(User user, Role role) {
		UserRole ur = this.loadUserRole(user.getId(), role.getId());
		if (ur != null)
			return;
		ur = new UserRole();
		ur.setRole(role);
		ur.setUser(user);
		this.getSession().save(ur);
	}

	@Override
	public void deleteUserRoles(int uid) {
		String hql = "delete UserRole ur where ur.user.id=?";
		this.updateByHql(hql, uid);
	}

	@Override
	public Pager<User> findUser() {
		return this.find("from User");
	}

	@Override
	public void deleteUserRole(int uid, int rid) {
		String hql = "delete UserRole ur where ur.user.id=? and ur.role.id=?";
		this.updateByHql(hql,new Object[]{uid,rid});
	}

}
