package com.warehouse.auth.principal;

import com.warehouse.app.user.User;
import com.warehouse.app.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ApplicationUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public ApplicationUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try {
           User user = userService.findByUsername(username).orElseThrow();
           return new ApplicationUserDetail(user);
       }catch (NoSuchElementException e){
           throw new UsernameNotFoundException(e.getMessage(),e);
       }

    }
}
