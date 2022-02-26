package com.davor.app.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Notification {
  @Id
  @SequenceGenerator(
      name = "notification_seq_id",
      sequenceName = "notification_seq_id"
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "notification_seq_id"
  )
  private Integer id;
  private Integer toCustomerId;
  private String toCustomerEmail;
  private String sender;
  private String message;
  private LocalDateTime sentAt;
}
