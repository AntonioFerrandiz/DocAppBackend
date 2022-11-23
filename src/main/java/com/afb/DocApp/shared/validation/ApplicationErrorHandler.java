package com.afb.DocApp.shared.validation;

import com.afb.DocApp.shared.exception.ResourceDateException;
import com.afb.DocApp.shared.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationErrorHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceDateException.class)
    public String handleNotFound(ResourceDateException exception){
        return exception.getMessage();
    }
}
