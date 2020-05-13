package com.dsh.cms.service.impl;


import com.dsh.cms.dao.CollectMapper;
import com.dsh.cms.domain.Collect;
import com.dsh.cms.service.CollectService;
import com.dsh.cms.util.CMSException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thyu.common.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CollectServiceImpl implements CollectService{
	@Autowired
	private CollectMapper collectMapper;

	@Override
	public boolean insert(Collect collect) {
		// TODO Auto-generated method stub
		String url = collect.getUrl();
		if(!StringUtil.isHttpUrl(url)) {
			throw new CMSException("收藏地址为非法地址!");
		}
		return collectMapper.insert(collect)>0;
	}

	@Override
	public PageInfo<Collect> getCollectsByUserId(Integer userId, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Collect>(collectMapper.getCollectsByUserId(userId));
	}

	@Override
	public boolean deleteCollectById(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.deleteCollectById(id)>0;
	}

	@Override
	public boolean deleteCollectByUAId(Integer userId, Integer articleId) {
		// TODO Auto-generated method stub
		return collectMapper.deleteCollectByUAId(userId, articleId)>0;
	}

	@Override
	public boolean isCollected(Integer userId, Integer articleId) {
		// TODO Auto-generated method stub
		return collectMapper.isCollected(userId, articleId)>0;
	}
	
}
