package com.warehouse.auth.security;

import com.warehouse.auth.jwt.JwtService;
import com.warehouse.auth.model.AuthProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


public class LoginFilter extends BasicAuthenticationFilter {

    private final AuthProperties properties;

    private final JwtService jwtService;

    public LoginFilter(ApplicationContext context) {
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

            Authentication authentication = jwtService.parse(jws);

            AuthenticationManager authenticationManager = getAuthenticationManager();
            authenticationManager.authenticate(authentication);
        }catch (Exception e){

        }
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        super.onSuccessfulAuthentication(request, response, authResult);
    }
}
