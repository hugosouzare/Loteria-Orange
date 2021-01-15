package com.loteriaorange.loteriaorange.services.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.loteriaorange.loteriaorange.exceptions.ApostadorException;

@ControllerAdvice
public class ApostadorExceptionHandler {

	@ExceptionHandler(ApostadorException.class)
	public ResponseEntity<Error> objectNotFound(ApostadorException e, HttpServletRequest request) {
		Error erro = new Error(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
