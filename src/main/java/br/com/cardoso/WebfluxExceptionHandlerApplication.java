package br.com.cardoso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class WebfluxExceptionHandlerApplication {

	public static void main(String[] args) {
		BlockHound.install();
		SpringApplication.run(WebfluxExceptionHandlerApplication.class, args);
	}

}
