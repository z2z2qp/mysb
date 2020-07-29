package me.will.sb.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("me.will.sb.mapper")
public class MyBatisConfig {
    @Bean
    public PaginationInterceptor interceptor(){
        return new PaginationInterceptor();
    }

}
