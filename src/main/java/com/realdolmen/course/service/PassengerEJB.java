package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.repository.PassengerRepository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class PassengerEJB implements  PassengerEJBRemote, Serializable {

    @Inject
    PassengerRepository passengerRepo;

    public PassengerEJB(){

    }

    @Override
    public List<Passenger> findPassengers(){
        return passengerRepo.findAll();
    }

    @Override
    public Passenger findPassengerById(PassengerId id ) {
        return passengerRepo.findById(id);
    }

    @Override
    public void createPassenger(Passenger p) {
        passengerRepo.create(p);
    }

    @Override
    public void deletePassenger(Passenger p) {
        passengerRepo.delete(p);
    }

    @Override
    public Passenger updatePassenger(Passenger p) {
        return passengerRepo.update(p);
    }

    @Override
    public void deletePassenger(PassengerId id) {
        Passenger p = passengerRepo.findById(id);
        passengerRepo.delete(p);
    }
}
