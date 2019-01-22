package com.greenfox.tribes1.Kingdom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotValidKingdomNameException {

    @ResponseBody
    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    RestError notAcceptableHandler (UnsupportedOperationException ex){
        return new RestError(ex.getMessage());
    }
}
