package com.realdolmen.course.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket extends AbstractEntity{

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumns(
            {@JoinColumn(name = "passenger_ssn", nullable = false, referencedColumnName = "ssn"),
             @JoinColumn(name = "passenger_lastName", nullable = false, referencedColumnName = "lastName")}
    )
    private Passenger passenger;

    private Double price;

    @NotNull
    @ManyToOne(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinColumn(name = "out_fk", nullable = false)
    private Flight outFlight;

    @NotNull
    @ManyToOne(cascade = {
        CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinColumn(name = "return_fk", nullable = false)
    private Flight returnFlight;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    public Ticket() {

    }

    public Ticket(Passenger passenger, Double price, Flight outFlight, Flight returnFlight, TicketStatus status) {
        this.outFlight = outFlight;
        this.returnFlight = returnFlight;
        this.status = status;
        this.setPassenger(passenger);
        this.price = price;
    }


    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Flight getOutFlight() {
        return outFlight;
    }

    public void setOutFlight(Flight outFlight) {
        this.outFlight = outFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
