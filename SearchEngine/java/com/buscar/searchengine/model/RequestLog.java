package com.buscar.searchengine.model;

import java.sql.Timestamp;
import java.util.List;

public class RequestLog {
	
	private String userId;
	private Timestamp queryTime;
	private String requestType;
	private String appname;
	//Add additional attributes here and generate getters and setters
	private String title;
	private List<String> tags;
	private String region;
	private String entry;
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Timestamp queryTime) {
		this.queryTime = queryTime;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getAppname() {
		return appname;
	}
	public void setAppname(String appname) {
		this.appname = appname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	

}
