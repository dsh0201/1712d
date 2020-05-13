package com.dsh.cms.vo;

import java.io.Serializable;

/**
 * 
    * @ClassName: ArticlePicVO
    * @Description: 表示图片配文章的每个图片信息
    * @date 2020年4月13日
    *
 */
public class ArticlePicVO implements Serializable{
	
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;
	private String src;//图片上传地址
	private String info;//图片信息
	public ArticlePicVO() {
		super();
	}
	public ArticlePicVO(String src, String info) {
		super();
		this.src = src;
		this.info = info;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "ArticlePicVO [src=" + src + ", info=" + info + "]";
	}
	
}
