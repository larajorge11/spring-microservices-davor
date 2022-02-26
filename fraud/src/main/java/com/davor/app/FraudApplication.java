package com.davor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.davor.client")
public class FraudApplication {

  public static void main(String[] args) {
    SpringApplication.run(FraudApplication.class, args);
  }
}
