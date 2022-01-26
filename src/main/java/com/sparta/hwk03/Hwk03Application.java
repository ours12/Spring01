package com.sparta.hwk03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Hwk03Application {

    public static void main(String[] args) {
        SpringApplication.run(Hwk03Application.class, args);
    }

}
