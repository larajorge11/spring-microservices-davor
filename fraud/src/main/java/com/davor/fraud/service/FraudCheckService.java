package com.davor.fraud.service;

import com.davor.fraud.model.FraudCheckHistory;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FraudCheckService {

  private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

  public boolean isFraudulentCustomer(Integer customerId) {
    fraudCheckHistoryRepository.save(FraudCheckHistory
            .builder()
        .customerId(customerId)
        .isFraudster(false)
        .createdAt(LocalDateTime.now())
        .build());

    return false;
  }
}
