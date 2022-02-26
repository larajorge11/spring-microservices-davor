package com.davor.customer.service;

import com.davor.amqp.RabbitMQMessageProducer;
import com.davor.customer.domain.Customer;
import com.davor.customer.domain.CustomerRegistrationRequest;
import com.davor.client.fraud.FraudClient;
import com.davor.client.notification.MessageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer messageProducer;

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
/*
THIS SECTION IT'S NOT LONGER TO BE USED DIRECTLY, NOW THE REPLACEMENT SHOULD BE CALLING THE MQ BROKER
        notificationClient.sendNotification(new MessageRequest
        (
            customer.getId(),
            customer.getEmail(),
            String.format("New customer has been created: %s-%s" , customer.getId(), customer.getFirstName())
        )
    );*/
    MessageRequest messageRequest = new MessageRequest
        (
            customer.getId(),
            customer.getEmail(),
            String.format("New customer has been created: %s-%s", customer.getId(),
                customer.getFirstName())
        );
    fraudClient.isFraudster(customer.getId());
    messageProducer
        .publish(messageRequest, "internal.exchange", "internal.notification.routing-key");

  }
}
