package com.warehouse.auth.base;

import com.warehouse.auth.base.model.AuthProperties;
import com.warehouse.auth.base.principal.ApplicationUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationContext context;

    private final AuthProperties properties;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationUserDetailService applicationUserDetailService;

    @Autowired
    public AuthConfig(ApplicationContext context, AuthProperties properties, PasswordEncoder passwordEncoder, ApplicationUserDetailService applicationUserDetailService) {
        this.context = context;
        this.properties = properties;
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDetailService = applicationUserDetailService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(properties.getWhiteList())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(new AuthFilter(context))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                eraseCredentials(false).
                userDetailsService(applicationUserDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
