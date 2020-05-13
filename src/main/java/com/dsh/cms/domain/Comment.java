package com.dsh.cms.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 
    * @ClassName: Comment
    * @Description: 评论类
    * @date 2020年4月12日
    *
 */
public class Comment implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;
	private Integer id;//自增主键
	private String content;//评论内容
	private Integer userId;//评论人的id
	private Integer articleId;//评论文章的id
	private Date created;
	private User user;
	private Article article;
	public Comment() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", userId=" + userId + ", articleId=" + articleId
				+ ", created=" + created + ", user=" + user + ", article=" + article + "]";
	}
	
}
