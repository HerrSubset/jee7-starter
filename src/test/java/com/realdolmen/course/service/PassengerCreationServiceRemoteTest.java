package com.realdolmen.course.service;


import com.realdolmen.course.domain.*;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.Date;

public class PassengerCreationServiceRemoteTest extends RemoteIntegrationTest {
    @Test
    public void mustCreatePerson() throws Exception {
        PassengerCreationServiceRemote service = lookup("jee7-starter/PassengerCreationService!com.realdolmen.course.service.PassengerCreationServiceRemote");
        PassengerEJBRemote passengerService = lookup("jee7-starter/PassengerEJB!com.realdolmen.course.service.PassengerEJBRemote");

        service.addAddress("Liersesteenweg 1", "", "Aarschot", "3200", "Belgium");
        service.addCreditCard("231", "oejf", 123, CreditCardType.VISA);
        service.addPassenger("Marcel", "Lubbermans", PassengerType.OCCASIONAL, new Date(), "abc");
        Flight f = new DomesticFlight("1", new Date(),new Date(), "KLM");
        service.addTicket(8.0, f, f, TicketStatus.PENDING);
        service.checkOut();

        PassengerId id = new PassengerId("abc", "Lubbermans");
        assertNotNull(passengerService.findPassengerById(id));
    }
}
