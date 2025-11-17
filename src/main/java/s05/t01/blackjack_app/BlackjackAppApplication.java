package s05.t01.blackjack_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BlackjackAppApplication {

	public static void main(String[] args) {
        SpringApplication.run(BlackjackAppApplication.class, args);
	}
}