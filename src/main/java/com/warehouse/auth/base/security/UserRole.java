package com.warehouse.auth.base.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.warehouse.auth.base.security.UserPermission.*;


public enum UserRole {
    CUSTOMER(new HashSet<UserPermission>(Arrays.asList(INVOICE_READ, INVOICE_WRITE))),
    VENDOR(new HashSet<UserPermission>(Arrays.asList(BILL_READ, BILL_WRITE))),
    USER(new HashSet<UserPermission>(Arrays.asList(PRODUCT_READ, PRODUCT_WRITE, BILL_READ, BILL_WRITE, INVOICE_READ, INVOICE_WRITE)));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
