package com.dsh.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.dsh.cms.domain.Article;

/**
 * 继承ElasticSearchRespository接口,就具备了简单的CRUD操作
 * @author dsh
 *
 */
public interface ArticleRespository extends ElasticsearchRepository<Article, Integer>{

	List<Article> findByTitleOrContent(String key, String key2);

}
