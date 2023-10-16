package me.will.sb.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.will.sb.entity.Log;
import me.will.sb.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Objects;

/**
 * 日志切片
 */
@Aspect
@Component
public class LogInterceptor {

    private static final Logger loger = LoggerFactory.getLogger(LogInterceptor.class);
    private final LogService logService;
    public LogInterceptor(LogService logService) {
        this.logService = logService;
    }

    /**
     * 接口请求
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("execution(public * me.will.sb.controller..*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        loger.info("args【{}】", obj2Str(pjp.getArgs()));
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var request = Objects.requireNonNull(attributes, "ServletRequestAttributes is null").getRequest();
        var param = request.getParameterMap();
        loger.info("\nrequest url 【{}】\nparam【{}】", request.getRequestURL(), obj2Str(param));
        var log = new Log(pjp.getSignature().getName(),pjp.getArgs());
        var result =  pjp.proceed(pjp.getArgs());
        log.setResult(result);
        logService.save(log);
        return result;
    }


    private String obj2Str(Object obj) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}