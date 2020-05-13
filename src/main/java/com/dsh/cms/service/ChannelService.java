package com.dsh.cms.service;

import java.util.List;

import com.dsh.cms.domain.Channel;

/**
 * 
    * @ClassName: ChannelService
    * @Description: 栏目类的service接口
    * @date 2020年4月2日
    *
 */
public interface ChannelService {
	/**
	 * 
	    * @Title: getChannelList
	    * @Description: 查询所有的栏目
	    * @param @return    参数
	    * @return List<Channel>    返回类型
	    * @throws
	 */
	List<Channel> getChannelList();
	/**
	 * 
	    * @Title: getChannelById
	    * @Description: 根据id查询栏目名称
	    * @param @param id
	    * @param @return    参数
	    * @return Channel    返回类型
	    * @throws
	 */
	Channel getChannelById(Integer id);
}
