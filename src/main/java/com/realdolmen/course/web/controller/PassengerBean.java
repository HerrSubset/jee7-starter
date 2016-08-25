package com.realdolmen.course.web.controller;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.service.PassengerEJB;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class PassengerBean implements Serializable {
    @Inject
    private PassengerEJB ps;

    private Passenger passenger;
    private boolean isEditable = true;
    private String ssn;
    private String lastName;
    private String userNameFilter;
    private List<Passenger> passengers = null;

    public PassengerBean() {

    }


    public void setUp() {
        if (this.ssn != null && this.lastName != null && !this.ssn.trim().isEmpty() && !this.lastName.trim().isEmpty()) {
            PassengerId id = new PassengerId(this.ssn, this.lastName);
            this.passenger = ps.findPassengerById(id);
        }
    }

    public String getUserNameFilter() {
        return userNameFilter;
    }

    public void setUserNameFilter(String userNameFilter) {
        this.userNameFilter = userNameFilter;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean getIsInEditMode() {
        return this.ssn != null && this.lastName != null;
    }
    public Passenger getPassenger() {
        if (getIsInEditMode() && passenger == null ) {
            setUp();
            System.out.println("getPassenger if 1");
        } else if (!getIsInEditMode() && passenger == null){
            this.passenger = new Passenger();
            System.out.println("getPassenger if 2");
        }
        System.out.println("Reached getPassenger");
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public List<Passenger> getPassengers() {
        if (this.passengers == null || this.passengers.isEmpty()) {
            this.passengers = ps.findPassengers();
        }

        return this.passengers;
    }

    public String register() {
        this.isEditable = false;
        return "/passenger/register";
    }

    public boolean getIsEditable() {
        return this.isEditable;
    }

    public void setIsEditable(boolean editable) {
        this.isEditable = editable;
    }


    public String submit() {
        ps.createPassenger(this.passenger);
        this.isEditable = true;
        System.out.println("Creating passenger " + this.passenger );
        resetFields();
        return "/passenger/done?faces-redirect=true";
    }

    public String save() {
        this.passenger = ps.updatePassenger(this.passenger);
        System.out.println("Saving passenger " + this.passenger );
        this.resetFields();
        return "/passenger/all?faces-redirect=true";
    }

    private void resetFields() {
        this.setSsn(null);
        this.setLastName(null);
        this.setPassenger(null);
    }

    public void test(javax.faces.event.ActionEvent ev) {
        System.out.println(ev.toString());
    }

    public void searchPassengers(AjaxBehaviorEvent event) {
        System.out.println("searching passengers");

        List<Passenger> passengers = ps.findPassengers();
        List<Passenger> tmp = new ArrayList<>();

        for (Passenger p : passengers) {
            if (p.getFirstName().contains(userNameFilter)) {
                tmp.add(p);
            }
        }

        this.passengers = tmp;
    }
}
