package org.iesvdm.ventassringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String manejarException(Exception exception) {

        log.info("AQUI " + exception.getMessage());

        return "error";
    }


    @ExceptionHandler(RuntimeException.class)
    public String manejarExceptionRuntime(RuntimeException exception) {

        log.info( "AQUI 2 " + exception.getMessage());

        return "error";
    }

}
