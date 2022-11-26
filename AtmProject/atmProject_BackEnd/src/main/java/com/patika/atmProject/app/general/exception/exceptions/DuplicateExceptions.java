package com.patika.atmProject.app.general.exception.exceptions;

import com.patika.atmProject.app.general.exception.exceptionEnums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
public class DuplicateExceptions extends BusinessExceptions{
    public DuplicateExceptions(BaseErrorMessage baseErrorMessage){
        super(baseErrorMessage);
    }
}
