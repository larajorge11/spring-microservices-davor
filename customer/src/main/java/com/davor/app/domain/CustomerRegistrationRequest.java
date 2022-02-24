package com.davor.app.domain;

public record CustomerRegistrationRequest(
    String firstName, String lastName, String email
) {

}
