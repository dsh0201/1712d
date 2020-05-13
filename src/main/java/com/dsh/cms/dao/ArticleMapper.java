package com.dsh.cms.dao;

import java.util.List;

import com.dsh.cms.domain.Article;

/**
 * 
    * @ClassName: ArticalMapper
    * @Description: 文章类的接口
    * @author dsh
    * @date 2020年3月31日
    *
 */
public interface ArticleMapper {
	/**
	 * 
	    * @Title: insert
	    * @Description: 增加文章
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int insert(Article article);
	/**
	 * 
	    * @Title: getArticleList
	    * @Description: 根据条件查询文章
	    * @param @param article,封装了查询条件
	    * @param @return    参数
	    * @return List<Article>    返回类型
	    * @throws
	 */
	List<Article> getArticleList(Article article);
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
	    * @Title: update
	    * @Description: 修改文章
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	int update(Article article);
}
