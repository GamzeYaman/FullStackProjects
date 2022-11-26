package com.patika.atmProject.app.general.exception.exceptions;


import com.patika.atmProject.app.general.exception.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotEnoughExceptions extends BusinessExceptions{
    public NotEnoughExceptions(BaseErrorMessage baseErrorMessage){
        super(baseErrorMessage);
    }
}
