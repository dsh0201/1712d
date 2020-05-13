package com.dsh.cms.dao;
/**
 * 
    * @ClassName: CategoryMapper
    * @Description: 类别接口
    * @author dsh
    * @date 2020年4月1日
    *
 */

import java.util.List;

import com.dsh.cms.domain.Category;

public interface CategoryMapper {
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
