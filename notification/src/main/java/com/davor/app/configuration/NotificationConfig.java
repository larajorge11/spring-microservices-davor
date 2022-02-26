package com.davor.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

  @Value("${rabbitmq.exchanges.internal}")
  private String internalExhange;

  @Value("${rabbitmq.queues.notification}")
  private String notificationQueue;

  @Value("${rabbitmq.routing-keys.internal-notification}")
  private String routingKeys;

  public String getInternalExhange() {
    return internalExhange;
  }

  public String getNotificationQueue() {
    return notificationQueue;
  }

  public String getRoutingKeys() {
    return routingKeys;
  }
}
