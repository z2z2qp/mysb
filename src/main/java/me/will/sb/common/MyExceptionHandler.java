package me.will.sb.common;

import me.will.sb.exception.SBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 统一异常处理
 */
@RestControllerAdvice
public class MyExceptionHandler {
    /**
     * The constant log.
     */
    public static final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    /**
     * 处理Exception 异常
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(Exception e) {
        log.warn("exception is {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }


    /**
     * 处理参数异常
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handler(MethodArgumentNotValidException e) {
        log.warn("exception is {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                                    HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * 处理自定义异常
     *
     * @param e the e
     * @return the response entity
     */
    @ExceptionHandler(SBException.class)
    public ResponseEntity<String> handler(SBException e) {
        log.warn("exception is {}", e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    }
}
