package s05.t01.blackjack_app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomSwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Blackjack Game API")
                        .version("v1.0")
                        .description("Online blackjack game developed at Barcelona's ITAcademy.")
                        .contact(new Contact()
                                .name("Daniel Caldito Serrano")
                                .email("dcs1990x@gmail.com")
                                .url("https://github.com/dcs1990x")));
    }
}