package ke.co.apollo.otp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String BASE_RESOURCES_PACKAGE = "ke.co.apollo.otp.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_RESOURCES_PACKAGE)).build()
                .apiInfo(apiInfo())
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        return builder
                .title("APOLLO GROUP")
                .description("APA OTP APIs")
                .version("1.0")
                .contact(this.getContact()).build();
    }

    private Contact getContact() {
        return new Contact("APA Insurance", "https://www.apainsurance.org", "apa.digital@apollo.co.ke");
    }


}
