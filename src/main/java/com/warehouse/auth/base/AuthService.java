package com.warehouse.auth.base;

import com.warehouse.app.user.User;
import com.warehouse.app.user.UserService;
import com.warehouse.auth.base.jwt.JwtService;
import com.warehouse.auth.base.model.AuthenticationRequest;
import com.warehouse.auth.base.model.AuthenticationResponse;
import com.warehouse.auth.base.principal.ApplicationUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtService jwtService;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse register(AuthenticationRequest auth) {
        User user = User.
                builder().
                username(auth.getUsername()).
                password(passwordEncoder.encode(auth.getPassword()))
                .role(auth.getRole())
                .build();
        User created = userService.create(user);
        ApplicationUserDetail userDetail = new ApplicationUserDetail(created);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, auth.getPassword(), userDetail.getAuthorities());
        return response(this.jwtService.create(authentication));

    }

    //todo why my custom exception didnt throw
    public AuthenticationResponse login(AuthenticationRequest auth) throws AuthenticationException {
        User user = userService.findByUsername(auth.getUsername()).orElseThrow();

        Authentication authentication = new UsernamePasswordAuthenticationToken(new ApplicationUserDetail(user), auth.getPassword());
        try {
            authenticationManager.authenticate(authentication);
            return response(this.jwtService.create(authentication));

        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    public void authorize(String jws) {

        Jws<Claims> claims = jwtService.parse(jws);
        Claims body = claims.getBody();
        User user = userService.findById(Long.parseLong(body.getSubject()));

        AuthContext.setUser(user);
    }

    private AuthenticationResponse response(String token) {
        return AuthenticationResponse.builder()
                .Authorization(token)
                .build();
    }
}
