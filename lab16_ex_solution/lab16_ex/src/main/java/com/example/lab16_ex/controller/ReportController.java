package com.example.lab16_ex.controller;

import java.util.Optional;

import com.example.lab16_ex.domain.Agent;
import com.example.lab16_ex.domain.MissionReport;
import com.example.lab16_ex.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReportController {

    @Autowired
    private AgentRepository repo;

    @RequestMapping("reports")
    public String listReports(@RequestParam Integer agent, Model m) {
        Optional<Agent> a = repo.findById(agent);
        if (a.isEmpty()) {
            return "redirect:/";
        }
        m.addAttribute("reports", a.get().getReports());
        m.addAttribute("agent", agent);
        return "reports/list";
    }

    @GetMapping("newReport")
    public String newReport(@RequestParam Integer agent, Model m) {
        m.addAttribute("report", new MissionReport());
        m.addAttribute("agent", agent);
        return "reports/form";
    }


    @PostMapping("addReport")
    public String addReport(@RequestParam Integer agent, @ModelAttribute MissionReport report, Model m) {
        Optional<Agent> a = repo.findById(agent);
        if (a.isEmpty()) {
            return "redirect:/";
        }
        a.get().getReports().add(report);
        repo.save(a.get());
        m.addAttribute("reports", a.get().getReports());
        m.addAttribute("agent", agent);
        return "reports/list";
    }
}
