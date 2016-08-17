package com.realdolmen.course.domain;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DomesticFlight extends Flight {
    private String airlineCompany;

    @ElementCollection
    private List<String> airlineReferences = new ArrayList<>();

    public DomesticFlight() {

    }

    public DomesticFlight(String number, Date departureTime, Date arrivalTime, String airlineCompany) {
        super(number, departureTime, arrivalTime);
        this.airlineCompany = airlineCompany;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public List<String> getAirlineReferences() {
        return airlineReferences;
    }

    public void setAirlineReferences(List<String> airlineReferences) {
        this.airlineReferences = airlineReferences;
    }
}
