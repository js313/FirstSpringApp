package com.sbear.firstapp.model;

public record Holiday(String day, String reason, com.sbear.firstapp.model.Holiday.Type type) {
    public enum Type {
        FESTIVAL, FEDERAL
    }
}
