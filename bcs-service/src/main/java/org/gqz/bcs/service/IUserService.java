package org.gqz.bcs.service;

import java.util.List;

import org.gqz.basic.model.Pager;
import org.gqz.bcs.model.Role;
import org.gqz.bcs.model.User;

public interface IUserService {
	/**
	 * 添加用户，需要判断用户名是否存在，如果存在抛出异常
	 * @param user 用户对象
	 * @param rids 用户的所有角色信息
	 * @param gids 用户的所有组信息（目前没有group，该方法暂时废弃）
	 */
	public void add(User user,Integer[]rids,Integer[]gids);
	
	/**
	 * 添加用户，需要判断用户名是否存在，如果存在抛出异常
	 * @param user 用户对象
	 * @param rids 用户的所有角色信息
	 */
	public void add(User user,Integer[]rids);
	
	/**
	 * 删除用户，注意需要把用户和角色和组的对应关系删除
	 * 如果用户存在相应的文章不能删除
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
	 * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除
	 * 对于group而已同样要做这个操作(目前没有group，所以该方法暂时废弃)
	 * @param user
	 * @param rids
	 * @param gids
	 */
	public void update(User user,Integer[] rids,Integer[] gids);
	
	/**
	 * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
	 * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除
	 * @param user
	 * @param rids
	 */
	public void update(User user,Integer[] rids);
	
	public void update(User user);
	/**
	 * 更新密码方法
	 * @param uid
	 * @param oldPwd
	 * @param newPwd
	 */
	public void updatePwd(int uid,String oldPwd,String newPwd);
	/**
	 * 更新用户的状态
	 * @param id
	 */
	public void updateStatus(int id);
	/**
	 * 列表用户
	 */
	public Pager<User> findUser();
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public User load(int id);
	/**
	 * 获取用户的所有角色信息
	 * @param id
	 * @return
	 */
	public List<Role> listUserRoles(int id);
	/**
	 * 获取用户的所有组信息
	 * @param id
	 * @return
	 */
	/*public List<Group> listUserGroups(int id);*/
	
	public List<Integer> listUserRoleIds(int id);
	
	/*public List<Integer> listUserGroupIds(int id);*/
	
	/*public List<User> listGroupUsers(int gid);*/
	public List<User> listRoleUsers(int rid);
	
	/**
	 * 根据用户名和密码获取用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username,String password);
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);
}
