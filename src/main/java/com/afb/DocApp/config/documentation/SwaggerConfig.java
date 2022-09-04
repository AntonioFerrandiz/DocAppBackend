package com.afb.DocApp.config.documentation;

import com.afb.DocApp.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docappAPI(){
        List<RequestParameter> requestParameterList = new ArrayList<RequestParameter>();
        RequestParameter requestParameter = new RequestParameterBuilder()
                .name("Authorization")
                .required(false)
                .description("Header para el token JWT")
                .in(ParameterType.HEADER)
                .build();
        requestParameterList.add(requestParameter);
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.afb.DocApp"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(User.class)
                .globalRequestParameters(requestParameterList);
    }
}
