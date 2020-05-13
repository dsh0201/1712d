package com.dsh.cms.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsh.cms.dao.ArticleMapper;
import com.dsh.cms.dao.ArticleRespository;
import com.dsh.cms.domain.Article;

/**
 * 由于是从es索引库中查询文章,但是索引库中没有数据,
 * 因此,我们得想办法把mysql的数据,查询出来,保存到es索引库
 * @author gaofee
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ImportMysql2ES {
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	ArticleRespository articleRespository;

	@Test
	public void testImport() {
		//1.从mysql中查询数据,只能查询被审核通过的文章,还要是没有被删除的文章
		Article article = new Article();
		article.setStatus(1);
		article.setDeleted(0);
		List<Article> list = articleMapper.getArticleList(article);
		//2.把查询出来的数据统一保存到es索引库
		articleRespository.saveAll(list);
		System.err.println("导入到es索引库成功!!!!!!");
	}
}
