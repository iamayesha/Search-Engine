package com.buscar.searchengine.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.buscar.searchengine.model.Content;
import com.buscar.searchengine.service.ContentService;
import com.buscar.searchengine.service.ErrorService;
import com.buscar.searchengine.service.RequestLogService;

@RestController
@RequestMapping("/searchengine")
public class ContentController {

	    @Autowired
	    private ContentService contentService;
	    @Autowired
	    private ErrorService errorService;
	        
	    @GetMapping("/titles/{title}")
	    public Object getContentByTitle(@PathVariable("title") String title) {
	        RequestLogService requestLogService = new RequestLogService();
	        Content content=contentService.findContentByTitle(title);  
	    	requestLogService.logRequest(contentService.findContentByTitle(title), "TITLE", title);
	    	if(content!=null)
	        return content; 
	    	else
	    	return errorService.notFoundError(title);   
	    	
	    }
	    
	    @GetMapping("/tags/{tag}")
	    public List<Content> getContentByTag(@PathVariable("tag") String tag) {
	    	
	    	List<Content> content= new ArrayList<Content>();
	        content=contentService.findContentByTag(tag);
	        RequestLogService requestLogService = new RequestLogService();
	    	requestLogService.logRequest(content.get(0), "TAGS", tag);
	    	return content;   	
	    	 	
	    } 


	   	
}
