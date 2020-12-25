package me.will.sb.common;

import me.will.sb.exception.SBException;
import me.will.sb.model.resp.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handler(Exception e) {
        log.warn("exception is {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ErrorMessage(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage()));
    }


    /**
     * 处理参数异常
     *
     * @param e the e
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handler(MethodArgumentNotValidException e) {
        log.warn("exception is {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ErrorMessage(HttpStatus.EXPECTATION_FAILED.value(),
                                 e.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }

    /**
     * 处理自定义异常
     *
     * @param e the e
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(SBException.class)
    public ResponseEntity<ErrorMessage> handler(SBException e) {
        log.warn("exception is {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ErrorMessage(HttpStatus.EXPECTATION_FAILED.value(), e.getMessage()));
    }
}
