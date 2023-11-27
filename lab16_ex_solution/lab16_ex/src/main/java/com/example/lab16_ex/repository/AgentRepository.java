package com.example.lab16_ex.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.lab16_ex.domain.Agent;

public interface AgentRepository extends CrudRepository<Agent, Integer> {

}
