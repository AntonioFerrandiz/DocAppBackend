package com.afb.DocApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DocAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocAppApplication.class, args);
	}

}
