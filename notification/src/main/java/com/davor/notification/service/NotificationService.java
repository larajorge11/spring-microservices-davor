package com.davor.notification.service;

import com.davor.notification.model.Notification;
import com.davor.client.notification.MessageRequest;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
  private final NotificationRepository notificationRepository;

  public NotificationService(NotificationRepository notificationRepository) {
    this.notificationRepository = notificationRepository;
  }

  public void sendNotification(MessageRequest messageRequest) {
    Notification notification = Notification
        .builder()
        .id(messageRequest.id())
        .toCustomerEmail(messageRequest.email())
        .message(messageRequest.message())
        .sentAt(LocalDateTime.now())
        .build();

    notificationRepository.saveAndFlush(notification);
  }
}
