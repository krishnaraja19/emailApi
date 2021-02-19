package com.refactor.maillauncher.exception;


import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.refactor.maillauncher.entities.ErrorDetails;


@ControllerAdvice
public class MailExceptionHandler{
	private static final Logger LOGGER = LoggerFactory.getLogger(MailExceptionHandler.class);
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
	    String name = ex.getParameterName();
	    ErrorDetails errorDetails = new ErrorDetails(new Date(),name+" is missing",ex.getMessage());
	    LOGGER.error("Required Parameter is missing : ",ex.getMessage());
        return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleGlobalException(Exception ex,WebRequest request) {
    	ErrorDetails errorDetails = new ErrorDetails(new Date(),"Internal API Error",request.getDescription(false));
    	LOGGER.error("Exception : ",ex.getMessage());
        return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
