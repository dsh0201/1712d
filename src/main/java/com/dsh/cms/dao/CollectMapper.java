package com.dsh.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dsh.cms.domain.Collect;

/**
 * 
    * @ClassName: CollectMapper
    * @Description: 收藏夹类接口
    * @author dsh
    * @date 2020年4月12日
    *
 */
public interface CollectMapper {
	/**
	 * 
	    * @Title: insert
	    * @Description: 谈价一条收藏记录
	    * @param @param collect
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(Collect collect);
	/**
	 * 
	    * @Title: getCollectsByUserId
	    * @Description: 根据用户id查询收藏记录
	    * @param @param userId
	    * @param @return    参数
	    * @return List<Collect>    返回类型
	    * @throws
	 */
	List<Collect> getCollectsByUserId(Integer userId);
	/**
	 * 
	    * @Title: deleteCollectById
	    * @Description: 根据id删除收藏记录
	    * @param @param id
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int deleteCollectById(Integer id);
	/**
	 * 
	    * @Title: deleteCollectByUAId
	    * @Description: 根据用户id和文章id删除收藏记录
	    * @param @param userId
	    * @param @param articleId
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int deleteCollectByUAId(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
	/**
	 * 
	    * @Title: isCollected
	    * @Description: 查询某篇文章是否被某个用户收藏
	    * @param @param userId
	    * @param @param articleId
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int isCollected(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
}
