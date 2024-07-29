package com.qwer.config

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
open class SwaggerConfig {
    @Value("\${swagger.enable}")
    private val enable = false

    @Value("\${swagger.host}")
    private val host: String? = null

    @Bean
    open fun createRestApi(): Docket {
        val docket: Docket = if (enable) {
            Docket(DocumentationType.OAS_30)
                .host(host)
                .apiInfo(apiInfo())
                .select() // 标示只有被 @Api 标注的才能生成API.
                .apis(RequestHandlerSelectors.withClassAnnotation(Api::class.java))
                .paths(PathSelectors.any())
                .build()
        } else {
            Docket(DocumentationType.OAS_30)
                .host(host)
                .apiInfo(ApiInfo.DEFAULT)
                .select()
                .apis(RequestHandlerSelectors.none())
                .build()
        }
        return docket
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("接口列表") // 大标题
            .version("1.0") // 版本
            .build()
    }
}