package com.sbear.firstapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "grade")
public class Grade extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int gradeId;

    @NotBlank(message = "Name cannot be blank")
    @Size(min=3, message = "Name must be at least 3 characters long")
    String name;

    @OneToMany(mappedBy = "grade", fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH,targetEntity = Person.class)
    private Set<Person> persons;
}
