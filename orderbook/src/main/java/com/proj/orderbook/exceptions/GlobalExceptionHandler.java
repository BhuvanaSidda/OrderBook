package com.proj.orderbook.exceptions;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = OrdersNotFoundException.class)
	public ErrorMessages handleTableNotFoundException(OrdersNotFoundException ex) {
		ErrorMessages objError = new ErrorMessages();
		objError.setErrorMessage(ex.getErrorMessage());
		objError.setErrorOccuredTime(
				ex.getErrorOccuredTime().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
		return objError;
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed", details);

		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
