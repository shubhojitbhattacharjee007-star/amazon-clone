package com.amazonclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AmazonCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonCloneApplication.class, args);
    }
}
