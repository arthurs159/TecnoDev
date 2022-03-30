package br.com.tecnodev.tecnodev.course;

import java.util.Arrays;

public enum Status {
    PRIVATE("PRIVADA"),
    PUBLIC("PÚBLICA");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Status enumCheckDescription(String value){
        return Arrays.stream(Status.values())
                .filter(status -> status.getDescription().equals(value))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Status not found " + value));
    }
}
