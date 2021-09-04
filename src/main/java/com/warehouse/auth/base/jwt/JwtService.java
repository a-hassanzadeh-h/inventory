package com.warehouse.auth.base.jwt;

import com.warehouse.auth.base.model.AuthProperties;
import com.warehouse.auth.base.principal.ApplicationUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class JwtService {

    private final AuthProperties properties;

    @Autowired
    public JwtService(AuthProperties properties) {
        this.properties = properties;
    }

    public String create(Authentication authentication) {
        AuthProperties.AuthJwt jwt = properties.getJwt();
        ApplicationUserDetail userDetail= (ApplicationUserDetail) authentication.getPrincipal();
        String token = Jwts.builder()
                .setSubject(userDetail.getUser().getId().toString())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwt.getExpiration())))
                .signWith(Keys.hmacShaKeyFor(jwt.getKey().getBytes()))
                .compact();
        return this.properties.getJwt().getPrefix().concat(" " + token);
    }

    public Jws<Claims> parse(String jws) {
        AuthProperties.AuthJwt jwt = properties.getJwt();
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwt.getKey().getBytes()))
                .build()
                .parseClaimsJws(jws.replace(jwt.getPrefix(), "").replace(" ",""));
    }

}
