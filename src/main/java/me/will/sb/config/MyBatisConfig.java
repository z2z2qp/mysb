package me.will.sb.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type My batis config.
 */
@Configuration
@MapperScan("me.will.sb.mapper")
public class MyBatisConfig {
    /**
     * mybatis 分页插件
     *
     * @return the pagination interceptor
     */
    @Bean
    public PaginationInnerInterceptor interceptor() {
        return new PaginationInnerInterceptor();
    }

}
