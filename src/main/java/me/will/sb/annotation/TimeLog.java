package me.will.sb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * me.will.sb.annotation.TimeLog
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/8/25 16:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TimeLog {

    /**
     * method 名称
     *
     * @return the string
     */
    String name() default "";
}
