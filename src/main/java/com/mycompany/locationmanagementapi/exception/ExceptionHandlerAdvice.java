package com.mycompany.locationmanagementapi.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mycompany.locationmanagementapi.controller.LocationManagementController;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ErrorModel> handleBusinessException(BusinessException be){
		LOGGER.error("Business exception occured with errorcode "+ be.getErrModel().getErrorCode()
				+" and error Message "+be.getErrModel().getErrorMsg());
		ResponseEntity re = null;
		if(be.getErrModel().getErrorCode() == 404){
			re = new ResponseEntity<ErrorModel>(be.getErrModel(),HttpStatus.NOT_FOUND);
		}
		return re;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorModel> handleException(Exception ex){
		ErrorModel err = new ErrorModel(HttpStatus.BAD_REQUEST.value(), "The request could not be handled by the server");
		return new ResponseEntity<ErrorModel>(err,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        
        ErrorResponse err = new ErrorResponse("Validation Failed",details);
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
	}
}
