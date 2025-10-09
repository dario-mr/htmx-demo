package com.dariom.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

  @ResponseStatus(INTERNAL_SERVER_ERROR)
  @ExceptionHandler(RuntimeException.class)
  public String handleException(RuntimeException exception, Model model) {
    log.error(exception.getMessage(), exception);
    model.addAttribute("message", exception.getMessage());
    return "fragments/error :: error(message=${message})";
  }
}
