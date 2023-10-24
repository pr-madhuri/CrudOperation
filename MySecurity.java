package com.ProductSimulation.product.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
public class MySecurity {

    @Bean
    public InMemoryUserDetailsManager createUser(){
        UserDetails admin= User.builder()
                .username("madhuri")
                .password("{noop}madhuri123")
                .roles("ADMIN","endUser")
                .build();
        UserDetails user=User.builder()
                .username("user")
                .password("{noop}user123")
                .roles("endUser")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }


    @Bean
    public DefaultSecurityFilterChain fileterChain(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests(config->
                  config
                          .requestMatchers("/displayUserProduct").hasRole("endUser")
                          . requestMatchers("/displayProduct").hasRole("ADMIN")
                           .anyRequest().authenticated()
         )
                 .formLogin(Customizer.withDefaults())
                 .exceptionHandling(config->
                         config.accessDeniedPage("/noAccess"));

         return http.build();
    }
}
