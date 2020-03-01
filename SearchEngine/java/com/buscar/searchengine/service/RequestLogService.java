package com.buscar.searchengine.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buscar.searchengine.model.Content;
import com.buscar.searchengine.model.RequestLog;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestLogService {
	RestClient restClient=null;;
	public static final Logger logger = LoggerFactory.getLogger(RequestLogService.class);

	public RequestLogService() {
		restClient= getElasticSearchConnection();
	}
	
	public RestClient getElasticSearchConnection() {
		try {
			RestClient restClient = RestClient.builder(
					new HttpHost("localhost", 9200, "http")).build();
			return restClient;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void logRequest(Content content, String requestType, String value)
	{
		RequestLog requestLogModel=this.getRequestLog(content,requestType,value);
		this.addRequestLog(requestLogModel);
	}
	
	public RequestLog addRequestLog(RequestLog requestLog) {	
		try {
			if(restClient == null) {
				logger.info("Failed to push log:"+ requestLog.toString());
				return requestLog;
			}
			Request request = new Request("POST","/requestsearch/_doc/");
			ObjectMapper obj = new ObjectMapper(); 
			String requestlogString= obj.writeValueAsString(requestLog);
			request.setEntity(new NStringEntity(requestlogString, ContentType.APPLICATION_JSON));
			Response response = restClient.performRequest(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode >=400) {	//check for unsuccessful requestlog entry
				logger.info("Failed to push request log:"+ requestLog.toString());
				return requestLog;
			}
			String responseBody = EntityUtils.toString(response.getEntity()); 
			logger.info("Successfully pushed request log:"+ responseBody);
			return requestLog;
		}catch(Exception e) {
			return requestLog;
		}
	}

	public RequestLog getRequestLog(Content content, String requestType, String value) {
		RequestLog requestLogModel=new RequestLog();

    		if(content !=null && "TITLE".equals(requestType)) {
    			requestLogModel.setAppname("searchengine");
    			requestLogModel.setTitle(content.getTitle());
    			requestLogModel.setTags(content.getMetadata().getTags());
    			requestLogModel.setQueryTime(new Timestamp(System.currentTimeMillis()));
    			requestLogModel.setRequestType(requestType);
    			requestLogModel.setRegion(content.getMetadata().getRegion());
    			requestLogModel.setEntry("HIT");
    			return requestLogModel;
    		}
    		else if(content !=null && "TAGS".equals(requestType)) {
    			List<String> tags= new ArrayList<String>();
    			tags.add(value);
    			requestLogModel.setRequestType(requestType);
    			requestLogModel.setAppname("searchengine");
    			requestLogModel.setTags(tags);
    			requestLogModel.setQueryTime(new Timestamp(System.currentTimeMillis()));
    			requestLogModel.setTitle(null);
    			requestLogModel.setRegion(null);
    			requestLogModel.setEntry("HIT");
    			return requestLogModel;
    		}
    		else if("TITLE".equals(requestType)) {
	        	requestLogModel.setEntry("MISS");
	        	requestLogModel.setRequestType("TITLE");
				requestLogModel.setAppname("searchengine");
				requestLogModel.setTags(null);
				requestLogModel.setQueryTime(new Timestamp(System.currentTimeMillis()));
				requestLogModel.setTitle(value);
				requestLogModel.setRegion(null);
	        	this.addRequestLog(requestLogModel);
    		}
    		else if("TAGS".equals(requestType)) {
    			List<String> tags= new ArrayList<String>();
    			tags.add(value);
    			requestLogModel.setEntry("MISS");
    			requestLogModel.setRequestType("TAGS");
    			requestLogModel.setAppname("searchengine");
    			requestLogModel.setTags(tags);
    			requestLogModel.setQueryTime(new Timestamp(System.currentTimeMillis()));
    			requestLogModel.setTitle(null);
    			requestLogModel.setRegion(null);
    			this.addRequestLog(requestLogModel);
    		}
    	
		return requestLogModel;
	}
	

}


