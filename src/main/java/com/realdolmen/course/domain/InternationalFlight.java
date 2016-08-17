package com.realdolmen.course.domain;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class InternationalFlight extends Flight {
    private boolean blacklisted = false;
    private String regulations = "";

    public InternationalFlight() {

    }

    public InternationalFlight(String number, Date departureTime, Date arrivalTime, boolean blacklisted, String regulations) {
        super(number, departureTime, arrivalTime);
        this.blacklisted = blacklisted;
        this.regulations = regulations;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    public String getRegulations() {
        return regulations;
    }

    public void setRegulations(String regulations) {
        this.regulations = regulations;
    }
}
