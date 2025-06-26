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
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id =?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/products/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/products/view").hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/products/modify/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/products/modify/id/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/order/view").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/order/make").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/cart/view").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.POST,"/cart/add").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/cart/delete/**").hasRole("CUSTOMER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .build();
    }


}
