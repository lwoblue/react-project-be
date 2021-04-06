package com.react.sample.service;

import java.util.Map;

public interface MainSlideService {

	public Map<String, Object> searchSlideImageList() throws Exception;

	public void insertSlideImageList(Map<String, Object> paramMap) throws Exception;

	public void updateSlideImageList(Map<String, Object> paramMap) throws Exception;

	public void deleteSlideImageList(Map<String, Object> paramMap) throws Exception;

}
