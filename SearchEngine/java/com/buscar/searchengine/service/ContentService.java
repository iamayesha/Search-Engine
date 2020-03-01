package com.buscar.searchengine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buscar.searchengine.model.*;
import com.buscar.searchengine.repository.*;

@Service
public class ContentService {
	
	@Autowired
	private ContentRepository contentrepository;
	
	//@Override
	public List<Content> findContentByTag(String tag){
		return contentrepository.findByTag(tag);
	}
	
	public Content findContentByTitle(String title){
		return contentrepository.findByTitle(title);
	}
	
	public List<Content> findContentByRegion(String region){
		return contentrepository.findByRegion(region);
	}
}
