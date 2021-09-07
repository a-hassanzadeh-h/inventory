package com.warehouse.app.user;

import com.sun.istack.NotNull;
import com.warehouse.app.partner.Partner;
import com.warehouse.auth.base.security.UserRole;
import com.warehouse.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private Partner partner;

    public User(String username){
        this.username = username;
    }
}
