package com.adamkaz7.usermanager.command;

public record CreateUserCommand(String firstName, String lastName, String email) {
}
