package com.sbear.firstapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "holidays")   // Need to use this because of the 's'
public class Holiday extends BaseEntity {
    @Id
    String day;
    String reason;
    @Enumerated(EnumType.STRING)
    Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
