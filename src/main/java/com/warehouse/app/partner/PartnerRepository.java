package com.warehouse.app.partner;

import com.warehouse.app.user.User;
import com.warehouse.core.base.BaseRepository;

import java.util.Optional;

public interface PartnerRepository extends BaseRepository<Partner> {

    Optional<Partner> findByUser(User user);
}
