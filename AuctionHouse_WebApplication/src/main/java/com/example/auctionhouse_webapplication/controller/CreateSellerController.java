package com.example.auctionhouse_webapplication.controller;

import com.example.auctionhouse_webapplication.model.Report;
import com.example.auctionhouse_webapplication.repo.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

public class CreateSellerController {


    //Controller -> Service -> Repository
    private ReportRepository reportRepository;

//    @Autowired
//    public void AdminController(ReportService reportService) {
//        this.reportService = reportService;
//    }

//    @GetMapping("/reports")
//    public String viewReports(Model model) {
//        return "reports";
//}}



    @GetMapping("/reports/view")
    public String reports() {
        return "index.html";
    }

    @RequestMapping("/reports/add")
    public String add(Model model) {
        model.addAttribute("reports",new Report());
        return "reports.html";
    }

    @RequestMapping("/reports/save")
    public String save(@ModelAttribute ("reports") Report reports) {

        reportRepository.save(reports);
        return "redirect:/index";
    }



}
