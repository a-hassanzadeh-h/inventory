package com.warehouse.app.user;

import com.warehouse.core.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService<User,UserRepository> {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository userRepository, UserRepository repository){
        super(userRepository);
        this.repository = repository;
    }

    public Optional<User> findByUsername(String username){
        return repository.findByUsername(username);
    }
}
