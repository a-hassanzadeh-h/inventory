package com.warehouse.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
