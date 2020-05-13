package com.dsh.cms.service;

import org.apache.ibatis.annotations.Param;

import com.dsh.cms.domain.Collect;
import com.github.pagehelper.PageInfo;

/**
 * 
    * @ClassName: CollectService
    * @Description: 收藏记录类接口
    * @date 2020年4月12日
    *
 */
public interface CollectService {
	/**
	 * 
	    * @Title: insert
	    * @Description: 谈价一条收藏记录
	    * @param @param collect
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	boolean insert(Collect collect);
	/**
	 * 
	    * @Title: getCollectsByUserId
	    * @Description: 根据用户id分页查询收藏记录
	    * @param @param userId
	    * @param @param pageNum
	    * @param @param pageSize
	    * @param @return    参数
	    * @return PageInfo<Collect>    返回类型
	    * @throws
	 */
	PageInfo<Collect> getCollectsByUserId(Integer userId,Integer pageNum,Integer pageSize);
	/**
	 * 
	    * @Title: deleteCollectById
	    * @Description: 根据id删除收藏记录
	    * @param @param id
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	boolean deleteCollectById(Integer id);
	/**
	 * 
	    * @Title: deleteCollectByUAId
	    * @Description: 根据用户id和文章id删除收藏记录
	    * @param @param userId
	    * @param @param articleId
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	boolean deleteCollectByUAId(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
	/**
	 * 
	    * @Title: isCollected
	    * @Description: 查询某篇文章是否被某个用户收藏
	    * @param @param userId
	    * @param @param articleId
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	boolean isCollected(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
}
