package com.example.lab16_ex.controller;

import jakarta.validation.Valid;

import com.example.lab16_ex.Lab16ExApplication;
import com.example.lab16_ex.domain.Agent;
import com.example.lab16_ex.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgentController {

    @Autowired
    private AgentRepository repo;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new AgentValidator(repo));
    }

    @GetMapping("/agents")
    public String agents(Model model) {
        model.addAttribute("agents", repo.findAll());
        return "agents/list";
    }

    @RequestMapping("/newAgent")
    public String newAgent(Model model) {
        model.addAttribute("agent", new Agent());
        return "agents/form";
    }

    @PostMapping("/addAgent")
    public String addAgent(@Valid @ModelAttribute Agent agent, BindingResult result) {
        if (result.hasErrors()) {
            return "agents/form";
        }
        repo.save(agent);
        return "redirect:/agents";
    }

    @GetMapping("/terminate")
    public String terminate(@RequestParam int agent) {
        if (repo.findById(agent).isPresent()) {
            repo.delete(repo.findById(agent).get());
        }
        return "redirect:/agents";
    }
}