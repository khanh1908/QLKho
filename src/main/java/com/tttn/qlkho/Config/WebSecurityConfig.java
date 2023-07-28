package com.tttn.qlkho.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
     protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/**").permitAll()
            //     // .antMatchers("/users").hasRole("ADMIN")
            //     .anyRequest().authenticated();
            // .cors().and()
            // .csrf().disable()
            // .authorizeRequests()
                // .antMatchers("/auth/register", "/auth/login").permitAll() // Các yêu cầu không cần xác thực
                // .antMatchers("/User/**").authenticated() // Yêu cầu /User/** cần xác thực
                .anyRequest().permitAll();
            // .and()
            //     .formLogin()
            //     .defaultSuccessUrl("/dashboard");
    }
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth
    //         .inMemoryAuthentication()
    //             .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
    //             .and()
    //             .withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN");
    // }
}
