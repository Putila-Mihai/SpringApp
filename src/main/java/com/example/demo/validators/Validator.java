package com.example.demo.validators;

import org.springframework.security.core.userdetails.UserDetails;

public interface Validator {
    boolean validate(UserDetails userDetails);
}
