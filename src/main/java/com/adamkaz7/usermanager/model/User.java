package com.adamkaz7.usermanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@ToString
public class User {
    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private final LocalDate registeredAt;

    public void update(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
