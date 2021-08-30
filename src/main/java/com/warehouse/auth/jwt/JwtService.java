package com.warehouse.auth.jwt;

import com.warehouse.auth.model.AuthProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwt.getExpiration())))
                .signWith(Keys.hmacShaKeyFor(jwt.getKey().getBytes()))
                .compact();
        return this.properties.getJwt().getPrefix().concat(" " + token);
    }

    public Authentication parse(String jws){
        AuthProperties.AuthJwt jwt = properties.getJwt();
        String subject = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwt.getKey().getBytes()))
                .build()
                .parseClaimsJws(jws)
                .getBody()
                .getSubject();
        return new UsernamePasswordAuthenticationToken(subject,null);
    }

}
