package com.dsh.cms.service;

import java.util.List;

import com.dsh.cms.domain.Slide;

/**
 * 
    * @ClassName: SlideService
    * @Description: 广告类的service接口
    * @date 2020年4月8日
    *
 */
public interface SlideService {
	/**
	 * 
	    * @Title: getSlideList
	    * @Description: 查询所有广告
	    * @param @return    参数
	    * @return List<Slide>    返回类型
	    * @throws
	 */
	List<Slide> getSlideList();
}
