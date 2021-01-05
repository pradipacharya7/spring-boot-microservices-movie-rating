package com.practice.ratingdata.ratingdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RatingDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingDataApplication.class, args);
    }

}
