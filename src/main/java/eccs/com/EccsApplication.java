package eccs.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EccsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EccsApplication.class, args);
	}

}
