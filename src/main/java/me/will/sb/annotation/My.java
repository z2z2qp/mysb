package me.will.sb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * me.will.sb.annotation.My
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/8/20 17:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface My {
}
