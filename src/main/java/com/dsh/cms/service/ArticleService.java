package com.dsh.cms.service;

import com.dsh.cms.domain.Article;
import com.github.pagehelper.PageInfo;

/**
 * 
    * @ClassName: ArticleService
    * @Description: 文章类的service接口
    * @date 2020年3月31日
    *
 */
public interface ArticleService {
	/**
	 * 
	    * @Title: insert
	    * @Description: 增加文章
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	boolean insert(Article article);
	
	/**
	 * 
	    * @Title: getArticleList
	    * @Description: 根据条件查询文章
	    * @param @param article,封装了查询条件
	    * @param @return    参数
	    * @return List<Article>    返回类型
	    * @throws
	 */
	PageInfo<Article> getArticleList(Article article,Integer pageNum,Integer pageSize);
	/**
	 * 
	    * @Title: getById
	    * @Description: 根据id查询文章
	    * @param @param id
	    * @param @return    参数
	    * @return Article    返回类型
	    * @throws
	 */
	Article getById(Integer id);
	/**
	 * 
	    * @Title: insert
	    * @Description: 修改文章
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	boolean update(Article article);
}
