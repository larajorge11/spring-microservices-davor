package com.davor.app.controller;

import com.davor.app.service.NotificationService;
import com.davor.client.notification.MessageRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
  private final NotificationService notificationService;

  @PostMapping
  public void sendNotification(@RequestBody MessageRequest messageRequest) {
    log.info("Notification Request {}", messageRequest);
    notificationService.sendNotification(messageRequest);
  }
}
