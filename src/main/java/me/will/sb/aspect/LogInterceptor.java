package me.will.sb.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.will.sb.annotation.ServiceLog;
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
        var request = Objects.requireNonNull(attributes).getRequest();
        var param = request.getParameterMap();
        log.info("param【{}】", obj2Str(param));

        return pjp.proceed(pjp.getArgs());
    }

    private String obj2Str(Object obj) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * service 日志
     *
     * @param pjp        the pjp
     * @param serviceLog the service log
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "@annotation(serviceLog)")
    public Object log(ProceedingJoinPoint pjp, ServiceLog serviceLog) throws Throwable {
        log.info("方法\n{}.{}({})",
                pjp.getSignature().getDeclaringTypeName(),
                serviceLog.name(),
                toParam(pjp.getArgs()));
        return pjp.proceed(pjp.getArgs());
    }

    private String toParam(Object[] objs) {
        if (Objects.isNull(objs) || objs.length == 0) {
            return "";
        }
        var sb = new StringBuilder();
        for (var i = 0; i < objs.length; i++) {
            sb.append(objs[i].getClass().getSimpleName())
                    .append(" ")
                    .append("arg")
                    .append(i);
            if (i < objs.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}