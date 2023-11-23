package dev.project.amqp.registration.user.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(

    @NotBlank
    String firstname,
    @NotBlank
    String lastname,
    @NotBlank
    @Email
    String email
) {

}
