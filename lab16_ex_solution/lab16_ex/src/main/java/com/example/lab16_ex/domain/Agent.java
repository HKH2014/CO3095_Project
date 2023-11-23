package com.example.lab16_ex.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Agent {

    @Id
    private int id;
    private String name;
    private String country;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private List<MissionReport> reports;

    public List<MissionReport> getReports() {
        return reports;
    }

    public void setReports(List<MissionReport> reports) {
        this.reports = reports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}