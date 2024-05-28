package com.devskiller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BooksApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BooksApp.class);
        app.run(args);
    }
}
