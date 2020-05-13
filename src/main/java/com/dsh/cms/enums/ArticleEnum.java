package com.dsh.cms.enums;
/**
 * 
    * @ClassName: ArticleEnum
    * @Description: 表示文章类型的枚举类
    * @date 2020年4月13日
    *
 */
public enum ArticleEnum {
	HTML(0),VOTE(1),PIC(2);
	private Integer code;
	private ArticleEnum(Integer code) {
		this.setCode(code);
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
}
