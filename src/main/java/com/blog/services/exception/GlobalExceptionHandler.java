package com.blog.services.exception;

import com.blog.services.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException exception)
    {
        String message=exception.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>  handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        Map<String,String > respo=new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error)->{
          String fieldName=((FieldError)error).getField();
          String message= error.getDefaultMessage();
            respo.put(fieldName,message);
        });

        String message=exception.getMessage();
        return new ResponseEntity<>(respo,HttpStatus.BAD_REQUEST);
    }
}
