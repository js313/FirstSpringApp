package com.sbear.firstapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {
    // Need to be named same as the html form names
    @NotBlank(message="Name must not be blank")
    @Size(min=3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message="Mobile Number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 DIGITS")
    private String mobileNum;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message="Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters")
    private String subject;

    @NotBlank(message="Message must not be blank")
    @Size(min = 10, max = 100, message = "Subject must be between 10 - 100 characters long")
    private String message;
}
