import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerConfig {
    @Value("\${swagger.enable}")
    private val enable = false

    @Value("\${swagger.host}")
    private val host: String? = null

    @Bean
    open fun openAPI(): OpenAPI {
        return if (enable) {
            OpenAPI()
                .info(apiInfo())
                .servers(listOf(Server().url(host ?: "/")))
        } else {
            OpenAPI()
                .info(Info().title("接口列表").version("1.0"))
                .servers(emptyList())
        }
    }
//
//    @Bean
//    open fun publicApi(): GroupedOpenApi {
//        return GroupedOpenApi.builder()
//            .group("api")
//            .pathsToMatch("/**")
//            .packagesToScan("com.qwer")  // 替换成你的包路径
//            .addOpenApiMethodFilter { method ->
//                method.declaringClass.isAnnotationPresent(Tag::class.java)
//            }
//            .build()
//    }
    private fun apiInfo(): Info {
        return Info()
            .title("接口列表")
            .version("1.0")
    }
}