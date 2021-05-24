package me.will.sb.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import me.will.sb.annotation.OpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * The type Swagger config.
 */
@Profile("dev")
@Configuration
@EnableKnife4j
@EnableOpenApi
public class SwaggerConfig {

    /**
     * 创建 docket对象
     *
     * @return the docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("OpenApi")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.will.sb"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(OpenApi.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket AllDocket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("All")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.will.sb"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
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