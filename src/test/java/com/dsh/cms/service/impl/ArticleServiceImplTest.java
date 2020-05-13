package com.dsh.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.dsh.cms.domain.Article;
import com.dsh.cms.service.ArticleService;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ArticleServiceImplTest extends SpringJunit{
	
	@Resource
	private ArticleService articleService;
	@Test
	public void testInsert() {
		Article article = new Article();
		article.setTitle("中国队出线了");
		article.setContent("受疫情影响恢复时间待定");
		article.setPicture("aaa.png");
		article.setChannelId(1);
		article.setCategoryId(2);
		article.setUserId(6);
		article.setHits(0);
		article.setHot(0);
		article.setStatus(1);
		article.setDeleted(0);
		article.setContentType(0);
		article.setCreated(new Date());
		articleService.insert(article);
	}

	@Test
	public void testGetArticleList() {
		PageInfo<Article> info = articleService.getArticleList(null, 1, 10);
		List<Article> list = info.getList();
		for (Article article : list) {
			System.out.println("===================================");
			System.out.println(article);
		}
	}
	@Test
	public void testGson() {
		Gson gson = new Gson();
		//gson.toJson(src);
		JsonArray jsonArray = new JsonParser().parse("asd").getAsJsonArray();
		for (JsonElement jsonElement : jsonArray) {
			Article article = gson.fromJson(jsonElement, Article.class);
		}
	}
}
