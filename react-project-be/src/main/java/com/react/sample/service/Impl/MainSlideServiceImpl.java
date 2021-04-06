package com.react.sample.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.sample.mapper.MainSlideMapper;
import com.react.sample.service.MainSlideService;

@Service(value = "MainSlideService")
public class MainSlideServiceImpl implements MainSlideService {

	@Autowired
	MainSlideMapper mainSlideMapper;

	@Override
	public Map<String, Object> searchSlideImageList() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultData", mainSlideMapper.searchSlideImageList());
		return resultMap;
	}

	@Override
	public void insertSlideImageList(Map<String, Object> paramMap) throws Exception {
		mainSlideMapper.insertSlideImageList(paramMap);
	}

	@Override
	public void updateSlideImageList(Map<String, Object> paramMap) throws Exception {
		mainSlideMapper.updateSlideImageList(paramMap);
	}

	@Override
	public void deleteSlideImageList(Map<String, Object> paramMap) throws Exception {
		mainSlideMapper.deleteSlideImageList(paramMap);
	}

}
