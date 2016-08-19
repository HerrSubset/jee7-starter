package com.realdolmen.course.service;


import com.realdolmen.course.domain.Flight;

import javax.ejb.Remote;

@Remote
public interface FlightReminderServiceRemote {
    void createFlight(Flight f);
}
