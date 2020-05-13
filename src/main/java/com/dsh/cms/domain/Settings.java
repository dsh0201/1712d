package com.dsh.cms.domain;

import java.io.Serializable;

/**
 * 
 * @ClassName: Settings 
 * @Description: 系统配置表
 * @date: 2020年3月31日
 */
public class Settings implements Serializable {
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//没有主键
	private String siteDomain;//网站域名
	private String siteName;//网站名称
	private Integer articleListSize;//文章没页显示的条目
	private Integer slideSize;//显示多少个广告
	private String adminUsername;//管理员账号
	private String  adminPassword;//管理员密码
	public Settings() {
		super();
	}
	public Settings(Integer id, String siteDomain, String siteName, Integer articleListSize, Integer slideSize,
			String adminUsername, String adminPassword) {
		super();
		this.id = id;
		this.siteDomain = siteDomain;
		this.siteName = siteName;
		this.articleListSize = articleListSize;
		this.slideSize = slideSize;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteDomain() {
		return siteDomain;
	}
	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getArticleListSize() {
		return articleListSize;
	}
	public void setArticleListSize(Integer articleListSize) {
		this.articleListSize = articleListSize;
	}
	public Integer getSlideSize() {
		return slideSize;
	}
	public void setSlideSize(Integer slideSize) {
		this.slideSize = slideSize;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Settings [id=" + id + ", siteDomain=" + siteDomain + ", siteName=" + siteName + ", articleListSize="
				+ articleListSize + ", slideSize=" + slideSize + ", adminUsername=" + adminUsername + ", adminPassword="
				+ adminPassword + "]";
	}
	
	
	
	
}

