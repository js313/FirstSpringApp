package com.sbear.firstapp.model;

import lombok.Data;

@Data
public class Contact {
    // Need to be named same as the html form names
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
