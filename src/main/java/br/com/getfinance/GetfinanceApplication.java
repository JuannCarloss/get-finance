package br.com.getfinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GetfinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetfinanceApplication.class, args);
	}

}
