package com.example.lab16_ex;

import com.example.lab16_ex.domain.Agent;
import com.example.lab16_ex.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab16ExApplication implements CommandLineRunner {

    @Autowired
    private AgentRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(Lab16ExApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Agent a = new Agent();
        a.setId(1);
        a.setCountry("Germany");
        a.setName("Merkel");
        repo.save(a);

        a = new Agent();
        a.setId(2);
        a.setCountry("America");
        a.setName("Smith");
        repo.save(a);
    }
}