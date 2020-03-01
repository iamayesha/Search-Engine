package com.buscar.searchengine.repository;

import java.util.*;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.buscar.searchengine.model.*;

@Repository
	public interface ContentRepository extends MongoRepository<Content, String> {

	    public Content findByTitle(String title);
	    
	    @Query(value="{'metadata.tags':?0}")
	    public List<Content> findByTag(String tag);
	    
	    
	    @Query(value="{'metadata.region':?0}")
	    public List<Content> findByRegion(String region);


	}
