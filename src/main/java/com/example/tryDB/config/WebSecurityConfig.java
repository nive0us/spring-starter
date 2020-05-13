package com.example.tryDB.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Allow for url prefix by api
        http.authorizeRequests()
                .antMatchers("/api/**")
                .permitAll();

        // api開頭以外的皆須auth
        http.authorizeRequests()
                .antMatchers("/**")
                .authenticated();
                // .permitAll();

        http.httpBasic();

    }
}
