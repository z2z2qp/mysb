package me.will.sb.aspect;

import me.will.sb.annotation.ServiceLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * me.will.sb.aspect.MethodLog
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020 /8/25 16:29
 */
@Aspect
@Component
public class MethodLogAspect {
    private static final Logger log = LoggerFactory.getLogger(MethodLogAspect.class);

    /**
     * 计算方法执行时间
     *
     * @param pjp     the pjp
     * @param timeLog the time log
     * @return the object
     * @throws Throwable the throwable
     */
    @Around(value = "@annotation(timeLog)")
    public Object time(ProceedingJoinPoint pjp, ServiceLog timeLog) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed(pjp.getArgs());
        log.info("method [{}] use time {}ms", timeLog.name(), System.currentTimeMillis() - start);
        return result;
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
