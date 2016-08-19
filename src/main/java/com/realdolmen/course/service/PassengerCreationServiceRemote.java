package com.realdolmen.course.service;

import com.realdolmen.course.domain.CreditCardType;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.domain.TicketStatus;

import javax.ejb.Remote;
import java.util.Date;

@Remote
public interface PassengerCreationServiceRemote {
    void addPassenger(String firstName, String lastName, PassengerType type, Date birthDate, String ssn);
    void addAddress(String street1, String street2, String city, String zipCode, String country);
    void addCreditCard(String number, String expiryDate, Integer controlNumber, CreditCardType type);
    void addTicket(Double price, Flight outFlight, Flight returnFlight, TicketStatus status);
    void checkOut();
}
