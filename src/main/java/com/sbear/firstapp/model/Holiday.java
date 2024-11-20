package com.sbear.firstapp.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity {
    String day;
    String reason;
    Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
