package com.example.auctionhouse_webapplication.service;

import com.example.auctionhouse_webapplication.model.Report;
import com.example.auctionhouse_webapplication.repo.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
//public class ReportService {
//    private final ReportRepository reportRepository;
//
//    @Autowired
//    public ReportService(ReportRepository reportRepository) {
//        this.reportRepository = reportRepository;
//    }
//
//    public List<Report> getAllReports() {
//        return (List<Report>) reportRepository.findAll();
//    }
//
//    public List<Report> searchReportsByTitle(String title) {
//        return reportRepository.findByTitleContaining(title);
//}}
