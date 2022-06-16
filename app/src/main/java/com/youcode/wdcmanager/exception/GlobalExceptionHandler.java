package com.youcode.wdcmanager.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handle(Exception exception, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse
                .sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }


}
