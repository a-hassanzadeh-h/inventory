package com.warehouse.auth;

import com.warehouse.app.partner.Partner;
import com.warehouse.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    private String username;

    private String email;

    @OneToOne(mappedBy = "user")
    private Partner partner;

    public User(String username){
        this.username = username;
    }
}
