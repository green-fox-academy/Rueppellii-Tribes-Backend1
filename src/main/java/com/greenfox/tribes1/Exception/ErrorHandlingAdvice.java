package com.greenfox.tribes1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlingAdvice {

  @ResponseBody
  @ExceptionHandler(WrongPasswordException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ErrorMsg wrongPasswordHandler(WrongPasswordException ex){
    return new ErrorMsg(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  ErrorMsg userNotFound(UserNotFoundException ex){
    return new ErrorMsg(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ErrorMsg missingParams(MethodArgumentNotValidException ex){
    return new ErrorMsg("Missing parameters: username or password missing");
  }

  @ResponseBody
  @ExceptionHandler(UsernameTakenException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  ErrorMsg usernameTaken(UsernameTakenException ex){
    return new ErrorMsg(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(NotValidKingdomNameException.class)
  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  ErrorMsg notAcceptableHandler (NotValidKingdomNameException ex){
    return new ErrorMsg(ex.getMessage());
  }
}
