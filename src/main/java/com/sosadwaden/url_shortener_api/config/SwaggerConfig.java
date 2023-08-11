package com.sosadwaden.url_shortener_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*
    Docket предоставляет первичную конфигурацию API со значениями по умолчанию и удобными методами настройки.
     */
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sosadwaden."))
                .build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("URL-Shortener API")
                .description("API reference for developers")
                .version("1.0")
                .build();
    }


}
