package dev.project.amqp.registration.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Message(
    @JsonProperty("firstname")
    String firstname,
    @JsonProperty("lastname")
    String lastname,
    @JsonProperty("email")
    String email
) {

}
