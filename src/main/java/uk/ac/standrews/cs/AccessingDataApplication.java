package uk.ac.standrews.cs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class AccessingDataApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccessingDataApplication.class, args);
	}
}
