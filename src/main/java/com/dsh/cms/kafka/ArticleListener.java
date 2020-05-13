package com.dsh.cms.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.dsh.cms.dao.ArticleMapper;
import com.dsh.cms.domain.Article;

public class ArticleListener implements MessageListener<String, String>{

	@Autowired
	ArticleMapper articleMapper;
	//就是监听消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		
		
		
		String msgJson = data.value();
		System.err.println("收到了消息!!");
		
		if(msgJson.startsWith("hits")) {
			//说明是数据库+1的消息:hits=19
			String[] split = msgJson.split("=");
			Integer id = Integer.parseInt(split[1]);
			//根据id查找文章
			Article a1 = articleMapper.getById(id);
			a1.setHits(a1.getHits()+1);
			//更新到数据库
			articleMapper.update(a1);
			
		}else{
			Article article = JSON.parseObject(msgJson, Article.class);
			
			//保存到mysql数据库
			articleMapper.insert(article);
			System.out.println("保存成功!!!");
			
		}
		
	}

}
