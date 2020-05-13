package com.dsh.cms.service;

import com.dsh.cms.domain.User;
import com.github.pagehelper.PageInfo;
/**
 * 
    * @ClassName: UserService
    * @Description: 用户的service接口
    * @date 2020年4月6日
    *
 */
public interface UserService {
	/**
	 * 
	    * @Title: getUserList
	    * @Description: 根据用户名分页查询用户信息
	    * @param @param user
	    * @param @param pageNum
	    * @param @param pageSize
	    * @param @return    参数
	    * @return PageInfo<User>    返回类型
	    * @throws
	 */
	PageInfo<User> getUserList(User user,Integer pageNum,Integer pageSize);
	/**
	 * 
	    * @Title: updateUser
	    * @Description: 修改用户信息
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	boolean updateUser(User user);
	/**
	 * 
	    * @Title: getUserById
	    * @Description: 根据id获得user对象
	    * @param @param id
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User getUserById(Integer id);
	/**
	 * 
	    * @Title: getUserByName
	    * @Description: 根据用户名获取用户
	    * @param @param username
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User getUserByName(String username);
	/**
	 * 
	    * @Title: register
	    * @Description: 插入用户
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	boolean register(User user);
	/**
	 * 
	    * @Title: login
	    * @Description: 登录
	    * @param @param user
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User login(User user);
	/**
	 * 
	    * @Title: adminLogin
	    * @Description: 管理员登录
	    * @param @param user
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	User adminLogin(User user);
}
