package com.warehouse.app.user;

import com.sun.istack.NotNull;
import com.warehouse.app.partner.Partner;
import com.warehouse.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    private String username;

    @NotNull
    private String password;

    // todo change from string to UserRole
    private String role;

    @OneToOne(mappedBy = "user")
    private Partner partner;

    public User(String username){
        this.username = username;
    }
}
