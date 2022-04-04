package kadeykin.example.bookmanagement.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {


  @Bean
  public Docket swaggerConfiguration() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("kadeykin.example.bookmanagement")) // Пакет сканирования Swagger
            .paths(PathSelectors.any())
            .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Simple Rest API for books management")
            .contact(new Contact("Kadeykin Nikita", "https://github.com/kadeykinnv", "kadeykin.nikita@gmail.com"))
            .description("Rest Service for control books and authors, uses Spring boot, Jpa ORM")
            .version("1.0")
            .build();

  }
}
