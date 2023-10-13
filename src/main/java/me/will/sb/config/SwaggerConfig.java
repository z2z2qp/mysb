package me.will.sb.config;

import cn.hutool.core.util.RandomUtil;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;

/**
 * The type Swagger config.
 */
@Profile("dev")
@Configuration
@EnableKnife4j
public class SwaggerConfig {

    /**
     * 创建 docket对象
     *
     * @return the OpenApi
     */
    @Bean
    public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags() != null) {
                openApi.getTags().forEach(tag -> {
                    var map = new HashMap<String, Object>();
                    map.put("x-order", RandomUtil.randomInt(0, 100));
                    tag.setExtensions(map);
                });
            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("接口文档")
                        .version("1.0")
                        .description("文档描述")
                        .termsOfService("http://abc.xyz")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://abc.xyz")
                        )
                );
    }

}