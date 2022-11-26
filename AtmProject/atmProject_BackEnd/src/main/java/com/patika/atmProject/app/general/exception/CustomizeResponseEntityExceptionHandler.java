package com.patika.atmProject.app.general.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, message, description);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


     }
