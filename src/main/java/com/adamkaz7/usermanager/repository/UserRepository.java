package com.adamkaz7.usermanager.repository;

import com.adamkaz7.usermanager.model.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public User add(User user) {
        if (users.containsKey(user.getId())) {
            throw new IllegalArgumentException("User " + user.getId() + " already exists");
        }
        users.put(user.getId(), user);
        return user;
    }

    public List<User> findAll() {
        return List.copyOf(users.values());
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    public List<User> findRegisteredBetween(LocalDate from, LocalDate to) {
        return users.values().stream()
                .filter(user -> !user.getRegisteredAt().isBefore(from) && !user.getRegisteredAt().isAfter(to))
                .toList();
    }

    public Optional<User> deleteById(UUID id) {
        return Optional.ofNullable(users.remove(id));
    }
}
