package com.ecommercenext.nextecommerce.security;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){

        UserDetails alex = User.builder().username("alex").password("{noop}123").roles("CUSTOMER").build();

        UserDetails admin = User.builder().username("admin").password("{noop}admin123").roles("ADMIN").build();

        InMemoryUserDetailsManager userManager = new InMemoryUserDetailsManager(alex,admin);
        return userManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http.csrf(csrf-> csrf.disable())
                //.authorizeHttpRequests(auth -> auth.requestMatchers("/api/products/add")
                    //    .hasRole("ADMIN")
               // .requestMatchers("/api/products/view")
               //         .hasAnyRole("CUSTOMER","ADMIN"))
               // .httpBasic(Customizer.withDefaults())
                .build();
    }


}
