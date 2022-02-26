package com.davor.notification;

import com.davor.amqp.RabbitMQMessageProducer;
import com.davor.notification.configuration.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
    "com.davor.notification",
    "com.davor.amqp"
})
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.davor.client")
public class NotificationApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotificationApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(
      RabbitMQMessageProducer producer,
      NotificationConfig config) {
    return args -> {
      producer.publish(new Person("Davor", "laradavor11@gmail.com"), config.getInternalExchange(), config.getRoutingKeys());
    };
  }

  record Person(String name, String email){}
}
