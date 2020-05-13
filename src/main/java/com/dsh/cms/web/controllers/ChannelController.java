package com.dsh.cms.web.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsh.cms.domain.Category;
import com.dsh.cms.domain.Channel;
import com.dsh.cms.service.CategoryService;
import com.dsh.cms.service.ChannelService;

/**
 * 
    * @ClassName: ChannelController
    * @Description: 栏目及分类
    * @date 2020年4月2日
    *
 */
@Controller
@RequestMapping("channel")
public class ChannelController {
	@Resource
	private ChannelService channelService;
	@Resource
	private CategoryService categoryService;
	/**
	 * 
	    * @Title: channels
	    * @Description: 返回所有的栏目的json数据
	    * @param @return    参数
	    * @return List<Channel>    返回类型
	    * @throws
	 */
	@RequestMapping("channels")
	@ResponseBody
	public List<Channel> channels(){
		return channelService.getChannelList();
	}
	/**
	 * 
	    * @Title: categories
	    * @Description: 根据栏目获取分类
	    * @param @param channelId
	    * @param @return    参数
	    * @return List<Category>    返回类型
	    * @throws
	 */
	@RequestMapping("categories")
	@ResponseBody
	public List<Category> categories(Integer channelId){
		return categoryService.getCategoriesByChannelId(channelId);
	}
}
