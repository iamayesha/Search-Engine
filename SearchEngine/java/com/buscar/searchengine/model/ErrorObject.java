package com.buscar.searchengine.model;

import java.util.Date;

public class ErrorObject {

	private int Status;
	private String ErrorMessage;
	private Date time;
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public Date getTime() {
		return time;
	}
	public ErrorObject(String errorMessage) {
		this.Status=404;
		this.ErrorMessage = errorMessage;
		this.time= new Date();
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
