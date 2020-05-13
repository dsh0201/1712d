package com.dsh.cms.service;

import java.util.List;

import com.dsh.cms.domain.Category;

/**
 * 
    * @ClassName: CategoryService
    * @Description: 类别的service接口
    * @date 2020年4月2日
    *
 */
public interface CategoryService {
	/**
	 * 
	    * @Title: getCategoriesByChannelId
	    * @Description: 根据栏目id查询所有的分类
	    * @param @param cid
	    * @param @return    参数
	    * @return List<Category>    返回类型
	    * @throws
	 */
	List<Category> getCategoriesByChannelId(Integer channelId);
}
