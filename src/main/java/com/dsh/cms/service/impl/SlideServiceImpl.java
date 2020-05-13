package com.dsh.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsh.cms.dao.SlideMapper;
import com.dsh.cms.domain.Slide;
import com.dsh.cms.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService{
	@Autowired
	private SlideMapper slideMapper;

	@Override
	public List<Slide> getSlideList() {
		// TODO Auto-generated method stub
		return slideMapper.getSlideList();
	}
	
}
