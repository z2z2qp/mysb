package me.will.sb.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config.
 */
@Profile("dev")
@Configuration
@EnableKnife4j
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建 docket对象
     *
     * @return the docket
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.will.sb"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("我的spring boot 接口文档")
                .version("1.0.1")
                .contact(contact())
                .description("接口描述文档")
                .build();
    }

    private Contact contact(){
        return new Contact("Will","","zjbwill@gamil.com");
    }
    
}