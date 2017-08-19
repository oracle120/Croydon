package org.gqz.bcs.dao.impl;


import java.util.List;

import org.gqz.basic.dao.BaseDao;
import org.gqz.bcs.dao.IRoleDao;
import org.gqz.bcs.model.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}

	@Override
	public void deleteRoleUsers(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=?",rid);
	}


}
