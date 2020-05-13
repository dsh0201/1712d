package com.dsh.cms.dao;
/**
 * 
    * @ClassName: UserMapper
    * @Description: 用户类接口
    * @author dsh
    * @date 2020年4月1日
    *
 */

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dsh.cms.domain.User;

public interface UserMapper {
	/**
	 * 
	    * @Title: getUserList
	    * @Description: 根据用户名查询用户列表
	    * @param @param username
	    * @param @return    参数
	    * @return List<User>    返回类型
	    * @throws
	 */
	List<User> getUserList(User user);
	/**
	 * 
	    * @Title: updateUser
	    * @Description: 修改用户信息
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int updateUser(User user);
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
	    * @Title: insert
	    * @Description: 插入用户
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(User user);
}
