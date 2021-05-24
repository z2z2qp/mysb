package me.will.sb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * me.will.sb.annotation.OpenApi
 * 用于控制不同方法在swagger输出
 * 在非dev和test启动时是只输出含有该注解的接口
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2021/5/24 14:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OpenApi {
}
