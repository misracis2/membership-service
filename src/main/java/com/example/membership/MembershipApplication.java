package com.example.membership;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class MembershipApplication {


    public static void main(String[] args) {
        SpringApplication.run(MembershipApplication.class, args);
    }

}
