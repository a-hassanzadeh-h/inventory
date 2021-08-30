package com.warehouse.app.user;

import com.warehouse.core.base.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);
}
