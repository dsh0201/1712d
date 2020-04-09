package com.yutianhao.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yutianhao.cms.dao.ArticleMapper;
import com.yutianhao.cms.domain.Article;
import com.yutianhao.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public boolean insert(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.insert(article)>0;
	}

	@Override
	public PageInfo<Article> getArticleList(Article article,Integer pageNum,Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articleList = articleMapper.getArticleList(article);
		return new PageInfo<Article>(articleList);
	}

	@Override
	public Article getById(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.getById(id);
	}

	@Override
	public boolean update(Article article) {
		// TODO Auto-generated method stub
		return articleMapper.update(article)>0;
	}
	
}
