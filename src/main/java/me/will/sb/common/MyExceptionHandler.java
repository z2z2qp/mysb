package me.will.sb.common;

import me.will.sb.exception.SBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {
    public static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public HttpResult<String> handler(Exception e) {
        log.warn("exception is {}", e.getMessage(), e);
        return HttpResult.FAIL(HttpResult.EXCEPTION, e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResult<String> handler(MethodArgumentNotValidException e) {
        log.warn("exception is {}", e.getMessage(), e);
        return HttpResult.FAIL(HttpResult.PARAM_ERROR, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(SBException.class)
    public HttpResult<String> handler(SBException e) {
        log.warn("exception is {}", e.getMessage(), e);
        return HttpResult.FAIL(HttpResult.MY_EXCEPTION, e.getMessage());
    }
}
