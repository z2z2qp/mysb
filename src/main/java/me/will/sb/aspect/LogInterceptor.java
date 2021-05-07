package me.will.sb.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * 日志切片
 */
@Aspect
@Component
public class LogInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LogInterceptor.class);

    /**
     * 接口请求
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("execution(public * me.will.sb.controller..*.*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        log.info("args【{}】", obj2Str(pjp.getArgs()));
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var request = Objects.requireNonNull(attributes, "ServletRequestAttributes is null").getRequest();
        var param = request.getParameterMap();
        log.info("\nrequest url 【{}】\nparam【{}】", request.getRequestURL(), obj2Str(param));

        return pjp.proceed(pjp.getArgs());
    }


    private String obj2Str(Object obj) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}