package pl.edu.agh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.edu.agh.weplay.config.Properties;

@SpringBootApplication
@EnableConfigurationProperties({Properties.class})
public class WePlayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WePlayApplication.class, args);
	}
}
