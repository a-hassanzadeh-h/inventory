package com.warehouse.auth.base;

import com.warehouse.auth.base.model.AuthProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


public class AuthFilter extends BasicAuthenticationFilter {

    private final AuthProperties properties;

    private final AuthService authService;

    public AuthFilter(ApplicationContext context) {
        super(context.getBean(AuthenticationManager.class));
        this.properties = context.getBean(AuthProperties.class);
        this.authService = context.getBean(AuthService.class);

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

            if (!StringUtils.startsWithIgnoreCase(jws, properties.getJwt().getPrefix() + " ")) {
                chain.doFilter(request, response);
                return;
            }

            authService.authorize(jws);

            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            throw e;
        }
    }
}
