package io.com.pacifico.JavaApplication.rest.controller;

import io.com.pacifico.JavaApplication.exception.RegraNegocioException;
import io.com.pacifico.JavaApplication.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(RegraNegocioException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors ResponseRegraNegocioException(RegraNegocioException ex) {
    String errorMessage = ex.getMessage();
    return new ApiErrors(errorMessage);
  }
}
