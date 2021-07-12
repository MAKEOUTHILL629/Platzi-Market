package com.platzi.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//Notacion del contexto en spring
@EnableSwagger2//vamos a activar swagger
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)//Tipo de documentacion que se va a utilizar
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.platzi.market.web.controller"))
                .build();
    }
}
