package com.dsh.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsh.cms.dao.UserMapper;
import com.dsh.cms.domain.User;
import com.dsh.cms.service.UserService;
import com.dsh.cms.util.CMSException;
import com.dsh.cms.util.MD5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thyu.common.utils.StringUtil;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageInfo<User> getUserList(User user, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<User> userList = userMapper.getUserList(user);
		return new PageInfo<User>(userList);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		//如果lock字段是空，说明这个更新用户的行为而不是管理员的封禁/解禁行为，设置最近更新时间
		if(null==user.getLocked()) {
			user.setUpdated(new Date());
		}
		return userMapper.updateUser(user)>0;
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(id);
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.getUserByName(username);
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		//用户名不能为空
		if(!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		//用户名长度
		if(user.getUsername().length()<2 || user.getUsername().length()>6) {
			throw new CMSException("用户名长度应为2到6之间");
		}
		//密码不能为空
		if(!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		//密码长度
		if(user.getPassword().length()<6 || user.getPassword().length()>12) {
			throw new CMSException("密码长度应为6到12位之间");
		}
		//确认密码是否为空
		if(!StringUtil.hasText(user.getRepassword())) {
			throw new CMSException("确认密码不能为空");
		}
		//两次密码是否一致
		if(!user.getPassword().equals(user.getRepassword())) {
			throw new CMSException("两次密码输入不一致");
		}
		if(null!=(userMapper.getUserByName(user.getUsername()))) {
			throw new CMSException("用户名已存在!");
		}
		user.setNickname(user.getUsername());
		user.setCreated(new Date());
		user.setPassword(MD5Util.encode(user.getPassword()));
		return userMapper.insert(user)>0;
	}

	@Override
	public User login(User user) {
		// 用户名不能为空
		if (!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		// 密码不能为空
		if (!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		User loginUser = userMapper.getUserByName(user.getUsername());
		if(null==loginUser || !MD5Util.encode(user.getPassword()).equals(loginUser.getPassword())) {
			throw new CMSException("用户名或密码错误!");
		}
		if(loginUser.getLocked()==1) {
			throw new CMSException("已被封禁，请联系管理员!");
		}
		return loginUser;
	}

	@Override
	public User adminLogin(User user) {
		// 用户名不能为空
		if (!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		// 密码不能为空
		if (!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		User loginUser = userMapper.getUserByName(user.getUsername());
		if(null==loginUser || loginUser.getRole()==0 || !MD5Util.encode(user.getPassword()).equals(loginUser.getPassword())) {
			throw new CMSException("用户名或密码错误!");
		}
		return loginUser;
	}
	
}
