package com.dsh.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsh.cms.dao.CategoryMapper;
import com.dsh.cms.domain.Category;
import com.dsh.cms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<Category> getCategoriesByChannelId(Integer channelId) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoriesByChannelId(channelId);
	}
	
}
