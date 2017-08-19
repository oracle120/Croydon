package org.gqz.bcs.dao;

import java.util.List;

import org.gqz.basic.dao.IBaseDao;
import org.gqz.bcs.model.Role;

public interface IRoleDao extends IBaseDao<Role> {
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
