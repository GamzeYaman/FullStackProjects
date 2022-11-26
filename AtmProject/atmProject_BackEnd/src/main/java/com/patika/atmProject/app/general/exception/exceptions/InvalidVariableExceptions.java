package com.patika.atmProject.app.general.exception.exceptions;

import com.patika.atmProject.app.general.exception.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidVariableExceptions extends BusinessExceptions {
    public InvalidVariableExceptions(BaseErrorMessage baseErrorMessage){
        super(baseErrorMessage);
    }
}
