package com.example.lab16_ex.controller;

import com.example.lab16_ex.Lab16ExApplication;
import com.example.lab16_ex.domain.Agent;
import com.example.lab16_ex.repository.AgentRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AgentValidator implements Validator {

    private AgentRepository repo;

    public AgentValidator(AgentRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Agent.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Agent a = (Agent) target;

        if (repo.findById(a.getId()).isPresent()) {
            errors.rejectValue("id", "", "ID already in use.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Your agent needs a name!");

        if ("UK".equals(a.getCountry())) {
            errors.rejectValue("country", "", "MI-6 deploy agents only in foreign countries");
        }

    }
}
