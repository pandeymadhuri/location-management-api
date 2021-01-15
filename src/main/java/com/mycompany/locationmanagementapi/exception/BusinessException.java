package com.mycompany.locationmanagementapi.exception;

public class BusinessException extends Exception{

	private ErrorModel errModel;

	public ErrorModel getErrModel() {
		return errModel;
	}

	public BusinessException(ErrorModel errModel) {
		super();
		this.errModel = errModel;
	}
	
}
