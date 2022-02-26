package com.davor.app.service;

import com.davor.app.domain.Customer;
import com.davor.app.domain.CustomerRegistrationRequest;
import com.davor.client.fraud.FraudClient;
import com.davor.client.notification.MessageRequest;
import com.davor.client.notification.NotificationClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final NotificationClient notificationClient;

  public void registerCustomer(CustomerRegistrationRequest customerRequest) {
    Customer customer = Customer.builder()
        .firstName(customerRequest.firstName())
        .lastName(customerRequest.lastName())
        .email(customerRequest.email())
        .build();
    customerRepository.saveAndFlush(customer);
/*
USING DIRECTLY THE URL: NOW SERVICE DISCOVERY IS IN CHARGE OF DOING THAT ADDING THE NAME OF THE APPLICATION
ANOTHER PROBLEM IS THAT FraudCheckResponse was copied here from Fraud service just to keep the functionality running
but what happend if there are thousands of services, it's a headache if we copy this, we need to use Open Feign
    FraudCheckResponse restTemplateForObject = restTemplate.getForObject(
    "http://localhost:8083/api/v1/fraud-check/{customerId}",
        FraudCheckResponse.class,
        customer.getId()
    );
 */
    notificationClient.sendNotification(new MessageRequest
        (
            customer.getId(),
            customer.getEmail(),
            String.format("New customer has been created: %s-%s" , customer.getId(), customer.getFirstName())
        )
    );

    fraudClient.isFraudster(customer.getId());
  }
}
