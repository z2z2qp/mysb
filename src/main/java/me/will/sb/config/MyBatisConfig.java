package me.will.sb.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type My batis config.
 */
@Configuration
@MapperScan("me.will.sb.mapper")
public class MyBatisConfig {

}
