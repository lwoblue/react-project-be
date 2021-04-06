package com.react.sample.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainSlideMapper {
	List<Map<String, Object>>  searchSlideImageList();

	public void insertSlideImageList(Map<String, Object> paramMap);

	public void updateSlideImageList(Map<String, Object> paramMap);

	public void deleteSlideImageList(Map<String, Object> paramMap);
	
}
