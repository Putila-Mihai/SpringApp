package com.example.demo.security;

import com.example.demo.controller.WebSocketController;
import com.example.demo.validators.FactoryValidator;
import com.example.demo.validators.Validator;
import com.example.demo.validators.ValidatorUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {
    private final FactoryValidator factoryValidator;
    private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    public WebSecurityConfig(FactoryValidator factoryValidator) {
        this.factoryValidator = factoryValidator;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests -> {

                    authorizeRequests.requestMatchers("/stats").permitAll();
                    authorizeRequests.requestMatchers("/addUser").permitAll();
                    authorizeRequests.requestMatchers("/subs").permitAll();
                    authorizeRequests.anyRequest().authenticated();

                })
//                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("user").password("$2a$12$tQpqf0VzIjSx42E1c.Hfeu8OlS6S/L1Sg/r3habaOgDyOE8MxwfY2").roles(String.valueOf(Roles.USER)).build();
        UserDetails user2 = User.builder().username("user2").password("$2a$12$tQpqf0VzIjSx42E1c.Hfeu8OlS6S/L1Sg/r3habaOgDyOE8MxwfY2").roles(String.valueOf(Roles.USER)).build();
        UserDetails admin = User.builder().username("admin").password("$2a$12$tQpqf0VzIjSx42E1c.Hfeu8OlS6S/L1Sg/r3habaOgDyOE8MxwfY2").roles(String.valueOf(Roles.ADMIN)).build();

        Pattern pattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = pattern.matcher(user1.getAuthorities().toString());
        String roleUser = "", roleAdmin = "";
        if (matcher.find()) {
            roleUser = matcher.group(1);
            logger.info("Role: " + roleUser);
        }
        matcher = pattern.matcher(admin.getAuthorities().toString());
        if (matcher.find()) {
            roleAdmin = matcher.group(1);
            logger.info("Role: " + roleAdmin);
        }
        Validator validatorUser = factoryValidator.getValidator(roleUser);
        Validator validatorAdmin = factoryValidator.getValidator(roleAdmin);
        logger.info("User validator: " + validatorUser.validate(user1));
        logger.info("Admin validator: " + validatorAdmin.validate(admin));
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    ;

}
