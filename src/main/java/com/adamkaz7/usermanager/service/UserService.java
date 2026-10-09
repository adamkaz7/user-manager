package com.adamkaz7.usermanager.service;

import com.adamkaz7.usermanager.command.CreateUserCommand;
import com.adamkaz7.usermanager.command.UpdateUserCommand;
import com.adamkaz7.usermanager.model.User;
import com.adamkaz7.usermanager.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User addUser(@NonNull CreateUserCommand command) {
        User user = new User(
                UUID.randomUUID(),
                command.firstName(),
                command.lastName(),
                command.email(),
                LocalDate.now()
        );
        return userRepository.add(user);
    }

    public void removeUser(@NonNull UUID id) {
        userRepository.deleteById(id).orElseThrow(
                () -> new NoSuchElementException("User not found: " + id)
        );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserById(@NonNull UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
    }

    public User updateUser(@NonNull UUID id, @NonNull UpdateUserCommand command) {
        User user = getUserById(id);
        user.update(
                command.firstName(),
                command.lastName(),
                command.email()
        );
        return user;
    }

    public List<User> findRegisteredBetween(@NonNull LocalDate from, @NonNull LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("From must be before to");
        }
        return userRepository.findRegisteredBetween(from, to);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
