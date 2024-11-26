package com.sbear.firstapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Roles extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int roleId;

    String roleName;
}
