package com.dsh.cms.dao;
/**
 * 
    * @ClassName: SlideMapper
    * @Description: 幻灯片类接口
    * @author dsh
    * @date 2020年4月1日
    *
 */

import java.util.List;

import com.dsh.cms.domain.Slide;

public interface SlideMapper {
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
