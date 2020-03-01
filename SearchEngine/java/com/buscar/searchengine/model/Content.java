package com.buscar.searchengine.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "contents")
public class Content {
	private String title;
	private Metadata metadata;
	private String status;
	@Id
	private String id;
	private String date;
	
	
	public Content(String title, Metadata metadata, String status, String id, String date) {
		super();
		this.title = title;
		this.metadata = metadata;
		this.status = status;
		this.id = id;
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Content [title=" + title + ", metadata=" + metadata + ", status=" + status + ", id=" + id + ", date="
				+ date + "]";
	}
	
}
