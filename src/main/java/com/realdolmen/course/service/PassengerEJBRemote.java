package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PassengerEJBRemote {
    List<Passenger> findPassengers();
    Passenger findPassengerById(PassengerId id );
    void createPassenger(Passenger p);
    void deletePassenger(Passenger p);
    void deletePassenger(PassengerId id);
    Passenger updatePassenger(Passenger p);
}
