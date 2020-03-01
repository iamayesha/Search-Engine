package com.buscar.searchengine.service;


import com.buscar.searchengine.model.ErrorObject;

public class ErrorService {

	public ErrorObject notFoundError(String errorMessage) {
		return new ErrorObject("No results found for :" +errorMessage);
	}

}
