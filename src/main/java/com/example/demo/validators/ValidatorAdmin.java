package com.example.demo.validators;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service(ValidatorAdmin.BEAN_ID)
public class ValidatorAdmin implements Validator{
    public static final String BEAN_ID = "ROLE_ADMIN";

    @Override
    public boolean validate(UserDetails userDetails) {
        //verify password
       return userDetails.getPassword().equals("$2a$12$tQpqf0VzIjSx42E1c.Hfeu8OlS6S/L1Sg/r3habaOgDyOE8MxwfY2");
    }
}
