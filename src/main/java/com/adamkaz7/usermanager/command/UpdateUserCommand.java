package com.adamkaz7.usermanager.command;

public record UpdateUserCommand(
        String firstName,
        String lastName,
        String email
) {
}
