package me.will.sb.config;

import cn.dev33.satoken.spring.SaTokenSetup;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.dev33.satoken.annotation.SaCheckInterceptor;
/**
 * 鉴权配置，监听所有请求
 */
@SaTokenSetup//启用鉴权
@Configuration
public class SaTokenConfig implements WebMvcConfigurer{
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaCheckInterceptor()).addPathPatterns("/**");
    }
}
