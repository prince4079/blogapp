package com.sparx.blogapplication.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparx.blogapplication.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse response=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		Map<String,String> resp=new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}
	@ExceptionHandler(InterceptorGenratedException.class)
	public ResponseEntity<ApiResponse> interceptorExceptionHandler(InterceptorGenratedException ex){
		ApiResponse response=new ApiResponse();
//		System.out.println("the message from the exception "+ex.getMessage());
		String message="the access is denied by the Interceptor Method ";
		response.setMessage(message);
		return new ResponseEntity<ApiResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
