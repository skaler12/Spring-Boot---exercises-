package pl.poznanski.spring_boot_exercises.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

//konfiguracja Swagera
@Configuration
@EnableSwagger2
public class SwagerConfig {
    //pod jakim endpointem i z jakiej paczki ma sie wyswietlac endpoint na Swager UI oraz dokumentacji.
    //Wywolanie metody createapiinfo
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/app/**")) //jakie endpointy ma obslugiwac swager, wrzucic do dokumentacji
                .build().apiInfo(createApiInfo());
    }
    private ApiInfo createApiInfo() {
        return new ApiInfo("Users API",
                "Users database",
                "1.00",
                "http://itpoznanski.pl",
                new Contact("Grzegorz", "http://itpoznanski.pl", "grzegorz@itpoznanski.pl"),
                "my own licence",
                "http://itpoznanski.pl",
                Collections.emptyList()
        );
    }
}
