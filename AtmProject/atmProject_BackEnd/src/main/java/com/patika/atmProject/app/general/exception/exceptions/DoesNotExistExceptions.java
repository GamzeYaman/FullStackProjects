package com.patika.atmProject.app.general.exception.exceptions;

import com.patika.atmProject.app.general.exception.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class DoesNotExistExceptions extends BusinessExceptions{
    public DoesNotExistExceptions(BaseErrorMessage baseErrorMessage){
        super(baseErrorMessage);
    }
}
