package com.dsh.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsh.cms.dao.CommentMapper;
import com.dsh.cms.domain.Comment;
import com.dsh.cms.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
    * @ClassName: CommentServiceImpl
    * @Description: 评论service接口的实现类
    * @author thyu
    * @date 2020年4月12日
    *
 */
@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public boolean insert(Comment comment) {
		// TODO Auto-generated method stub
		commentMapper.insert(comment);
		commentMapper.increaseCommentNum(comment.getArticleId(), 1);
		return true;
	}

	@Override
	public PageInfo<Comment> getCommentsByArticleId(Integer articleId, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Comment>(commentMapper.getCommentsByArticleId(articleId));
	}

	@Override
	public PageInfo<Comment> getCommentsByUserId(Integer userId, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Comment>(commentMapper.getCommentsByUserId(userId));
	}

	@Override
	public boolean deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		commentMapper.deleteComment(comment.getId());
		commentMapper.increaseCommentNum(comment.getArticleId(),-1);
		return true;
	}
	
}
