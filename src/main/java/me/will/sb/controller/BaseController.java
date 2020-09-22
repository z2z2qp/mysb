package me.will.sb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * me.will.sb.controller.BaseController
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/9/22 16:39
 */
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @ResponseBody
    @ExceptionHandler
    public ResponseEntity<String> exp(Throwable t) {
        log.warn("catch an exception :{}", t.getMessage(), t);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(t.getMessage());
    }
}
