package com.example.auctionhouse_webapplication.repo;

import com.example.auctionhouse_webapplication.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}