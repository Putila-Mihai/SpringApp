package com.example.demo.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FactoryValidator {

    private final Map<String, Validator> validatorProvider;

    public Validator getValidator(String role) {
        return validatorProvider.get(role);
    }

}
