package com.sbear.firstapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "contact_msg")   // Only need to do this if class name is different from table name, it compares by removing special characters and without casing
@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs", query = "SELECT c FROM Contact c WHERE c.status = :status"),
        @NamedQuery(name = "Contact.updateMsgStatus", query = "UPDATE Contact c SET c.status = ?1 WHERE c.status = :status"),
})  // There's also "NamedNativeQueries"
public class Contact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")    // Again, DEMO
    private int contactId;

    // Need to be named same as the html form names
    @NotBlank(message="Name must not be blank")
    @Size(min=3, message = "Name must be at least 3 characters long")
    @Column(name = "name")  // just as an example, no need to annotate this if column name and variable name are same
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

    private String status;
}
