package com.adamkaz7.usermanager.controller;

import com.adamkaz7.usermanager.command.CreateUserCommand;
import com.adamkaz7.usermanager.command.UpdateUserCommand;
import com.adamkaz7.usermanager.model.User;
import com.adamkaz7.usermanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping(params = {"from", "to"})
    public List<User> findRegisteredBetween(@RequestParam LocalDate from, @RequestParam LocalDate to) {
        return userService.findRegisteredBetween(from, to);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody CreateUserCommand command) {
        return userService.addUser(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUser(@PathVariable UUID id) {
        userService.removeUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody UpdateUserCommand command) {
        return userService.updateUser(id, command);
    }
}
