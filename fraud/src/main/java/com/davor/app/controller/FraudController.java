package com.davor.app.controller;

import com.davor.app.model.FraudCheckResponse;
import com.davor.app.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

  private final FraudCheckService fraudCheckService;

  @GetMapping(path = "{customerId}")
  public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
    log.info("Fraud check request for customer {}", customerId);
    boolean fraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
    return new FraudCheckResponse(fraudulentCustomer);
  }
}
