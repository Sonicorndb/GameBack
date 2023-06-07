package es.david.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedHeaders("GET", "POST", "PUT",
						"DELETE", "Content-Type", "X-Requested-With", "accept", "Authorization", "Origin",
						"Access-Control-Request-Method", "Access-Control-Request-Headers");
			}
		};
	}
}
