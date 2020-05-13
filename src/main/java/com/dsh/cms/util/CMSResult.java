package com.dsh.cms.util;
/**
 * 
    * @ClassName: CMSResult
    * @Description: 统一结果对象
    * @date 2020年4月9日
    *
 */
public class CMSResult<T> {
	// 返回结果的状态
	private Integer code;
	private String msg;
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
