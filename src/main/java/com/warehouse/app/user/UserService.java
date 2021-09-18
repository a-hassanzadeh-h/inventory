package com.warehouse.app.user;

import com.warehouse.auth.base.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String password) {
        if (StringUtils.isEmpty(password))
            throw new RuntimeException("invalid password");
        else
            return passwordEncoder.encode(password);
    }

    public void populate() {
        if (this.count() > 0)
            return;
        else
            this.create(
                    User.builder()
                            .username("admin")
                            .password("12345678")
                            .role(UserRole.ADMIN)
                            .build()
            );
    }

    public User create(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return repository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public long count() {
        return repository.count();
    }
}
