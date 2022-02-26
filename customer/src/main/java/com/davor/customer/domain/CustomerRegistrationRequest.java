package com.davor.customer.domain;

public record CustomerRegistrationRequest(
    String firstName, String lastName, String email
) {

}
