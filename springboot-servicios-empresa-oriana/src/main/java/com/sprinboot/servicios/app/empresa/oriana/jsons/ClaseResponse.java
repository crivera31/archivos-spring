package com.sprinboot.servicios.app.empresa.oriana.jsons;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class ClaseResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus code;
	private T data;
	
	
	public ClaseResponse( T data,HttpStatus code) {
		this.code = code;
		this.data = data;
	}
	
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	

}
