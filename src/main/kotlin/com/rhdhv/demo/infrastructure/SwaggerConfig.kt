package com.rhdhv.demo.infrastructure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.io.InputStream

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.rhdhv"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData())
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, emptyList())
            .globalResponseMessage(RequestMethod.POST, emptyList())
            .globalResponseMessage(RequestMethod.PUT, emptyList())
            .globalResponseMessage(RequestMethod.DELETE, emptyList())
            .ignoredParameterTypes(Resource::class.java, InputStream::class.java)

    private fun metaData(): ApiInfo {
        return ApiInfoBuilder()
                .title("Demo API")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(Contact("Royal HaskoningDHV", "https://www.royalhaskoningdhv.com", "contact@royalhaskoningdhv.com"))
                .build()
    }

}
