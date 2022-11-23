package com.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class JavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("this is testing");
    }
}
