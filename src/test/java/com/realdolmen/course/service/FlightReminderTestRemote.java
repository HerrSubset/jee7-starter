package com.realdolmen.course.service;


import com.realdolmen.course.domain.DomesticFlight;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.Date;

public class FlightReminderTestRemote extends RemoteIntegrationTest{

    @Test
    public void methodShouldPrintToConsole() throws Exception{
        FlightReminderServiceRemote service = lookup("jee7-starter/FlightReminderService!com.realdolmen.course.service.FlightReminderServiceRemote");
        Flight f = new DomesticFlight("1", new Date(), new Date(), "Brussels Airlines");
        service.createFlight(f);
    }
}
