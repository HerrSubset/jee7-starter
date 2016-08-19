package com.realdolmen.course.service;


import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.List;

public class PassengerEJBRemoteTest extends RemoteIntegrationTest {
    @Test
    public void testPassengerServiceCanBeAccessedRemotely() throws Exception {
        PassengerEJBRemote service = lookup("jee7-starter/PassengerEJB!com.realdolmen.course.service.PassengerEJBRemote");
        List<Passenger> passengers = service.findPassengers();
        assertEquals(passengers.size(), 3);
        for (Passenger p : passengers) {
            logger.trace("Retrieved passenger: " + p);
        }
    }
}
