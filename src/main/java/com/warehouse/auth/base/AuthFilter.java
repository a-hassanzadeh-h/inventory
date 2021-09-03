package com.warehouse.auth.base;

import com.warehouse.auth.base.jwt.JwtService;
import com.warehouse.auth.base.model.AuthProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AuthFilter extends BasicAuthenticationFilter {

    private final AuthProperties properties;

    private final JwtService jwtService;

    public AuthFilter(ApplicationContext context) {
        super(context.getBean(AuthenticationManager.class));
        this.properties = context.getBean(AuthProperties.class);
        this.jwtService = context.getBean(JwtService.class);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        AntPathMatcher matcher = new AntPathMatcher();
        return Arrays.stream(properties.getWhiteList()).anyMatch(p -> matcher.match(p, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String jws = request.getHeader("Authorization");

            if (!StringUtils.startsWithIgnoreCase(jws,properties.getJwt().getPrefix() + " ")){
                chain.doFilter(request,response);
                return;
            }

            Jws<Claims> claims = jwtService.parse(jws);
            Claims body = claims.getBody();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(a -> new SimpleGrantedAuthority(a.get("authority"))).collect(Collectors.toList());

            Authentication authentication = new UsernamePasswordAuthenticationToken(body.getSubject(), null, simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
