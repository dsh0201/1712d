package com.dsh.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dsh.cms.domain.Comment;

/**
 * 
    * @ClassName: CommentMapper
    * @Description: 评论类的mapper接口
    * @author dsh
    * @date 2020年4月12日
    *
 */
public interface CommentMapper {
	/**
	 * 
	    * @Title: insert
	    * @Description: 发布评论
	    * @param @param comment
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(Comment comment);
	/**
	 * 
	    * @Title: getCommentsByArticleId
	    * @Description: 根据文章id获取评论信息
	    * @param @param articleId
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	List<Comment> getCommentsByArticleId(Integer articleId);
	/**
	 * 
	    * @Title: getCommentsByUserId
	    * @Description: 根据用户id获取评论信息
	    * @param @param userId
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	List<Comment> getCommentsByUserId(Integer userId);
	/**
	 * 
	    * @Title: deleteComment
	    * @Description: 删除评论
	    * @param @param id
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int deleteComment(Integer id);
	/**
	 * 
	    * @Title: updateCommentNum
	    * @Description: 更新文章的评论数
	    * @param @param articleId
	    * @param @param num
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int increaseCommentNum(@Param("articleId")Integer articleId,@Param("num")Integer num);
}
