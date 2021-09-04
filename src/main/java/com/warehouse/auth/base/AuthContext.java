package com.warehouse.auth.base;

import com.warehouse.app.user.User;
import com.warehouse.auth.base.principal.ApplicationUserDetail;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthContext {

    public static Optional<User> getUser() {
        try {
            ApplicationUserDetail userDetail = (ApplicationUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userDetail.getUser();

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public static void setUser(User user) {
        ApplicationUserDetail userDetail = new ApplicationUserDetail(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
