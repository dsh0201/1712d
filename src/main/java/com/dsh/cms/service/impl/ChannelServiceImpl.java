package com.dsh.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsh.cms.dao.ChannelMapper;
import com.dsh.cms.domain.Channel;
import com.dsh.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService{
	@Autowired
	private ChannelMapper channelMapper;

	@Override
	public List<Channel> getChannelList() {
		// TODO Auto-generated method stub
		return channelMapper.getChannelList();
	}

	@Override
	public Channel getChannelById(Integer id) {
		// TODO Auto-generated method stub
		return channelMapper.getChannelById(id);
	}
	
}
