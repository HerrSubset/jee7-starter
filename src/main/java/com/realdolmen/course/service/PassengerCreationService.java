package com.realdolmen.course.service;

import com.realdolmen.course.domain.*;
import com.realdolmen.course.repository.PassengerRepository;
import com.realdolmen.course.repository.TicketRepository;

import javax.annotation.Resource;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSPasswordCredential;
import javax.jms.Queue;
import java.util.Date;

@Stateful
public class PassengerCreationService implements PassengerCreationServiceRemote {
    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    @JMSPasswordCredential(userName = "administrator", password="Azerty123!")
    private JMSContext context;

    @Resource(lookup = "java:jboss/exported/rd/queues/TicketQueue")
    private Queue queue;

    private Passenger passenger = new Passenger();
    private PassengerId id = new PassengerId();
    private Ticket ticket;

    @Inject
    private PassengerRepository passengerRepo;

    @Inject
    private TicketRepository ticketRepo;

    @Override
    public void addPassenger(String firstName, String lastName, PassengerType type, Date birthDate, String ssn) {
        passenger.setFirstName(firstName);
        passenger.setDateOfBirth(birthDate);
        passenger.setType(type);
        id.setLastName(lastName);
        id.setSsn(ssn);
    }

    @Override
    public void addAddress(String street1, String street2, String city, String zipCode, String country) {
        passenger.setAddress( new Address(street1, street2, city, zipCode, country));
    }

    @Override
    public void addCreditCard(String number, String expiryDate, Integer controlNumber, CreditCardType type) {
        passenger.addCreditCard(new CreditCard(number, expiryDate, controlNumber, type));
    }

    @Override
    public void addTicket(Double price, Flight outFlight, Flight returnFlight, TicketStatus status) {
        ticket = new Ticket(null, price, outFlight, returnFlight, status);
    }

    @Override
    @Remove
    public void checkOut() {
        passenger.setId(id);
        passengerRepo.create(passenger);
        this.ticket.setPassenger(passenger);
        ticketRepo.create(this.ticket);
        context.createProducer().send(queue, this.ticket);
    }
}
