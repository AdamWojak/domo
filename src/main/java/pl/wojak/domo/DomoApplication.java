package pl.wojak.domo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DomoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomoApplication.class, args);
        System.out.println("cześć tu DomoProject");
    }

}

