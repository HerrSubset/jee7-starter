package com.realdolmen.course.service;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.repository.PassengerRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PassengerEJBTest {

    @Mock
    private PassengerRepository repository;

    @InjectMocks
    private PassengerEJB service = new PassengerEJB();

    @Before
    public void setupMox() {
        Passenger p = new Passenger("a", "theo", "theofielsen", 1, new Date(), 1, PassengerType.OCCASIONAL, new Date(), null);
        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(p);
        when(repository.findAll()).thenReturn(passengerList);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void shouldFindAllPassengers() {
        List<Passenger> passengers = service.findPassengers();
        assertNotNull(passengers);
        assertFalse(passengers.isEmpty());
        verify(repository).findAll();
    }

    @Test
    public void shouldCreateAPerson() {
        Passenger p = new Passenger("a", "theo", "theofielsen", 1, new Date(), 1, PassengerType.OCCASIONAL, new Date(), null);
        service.createPassenger(p);
        verify(repository).create(same(p));
    }

    @Test
    public void shouldRemovePerson() {
        Passenger p = new Passenger("a", "theo", "theofielsen", 1, new Date(), 1, PassengerType.OCCASIONAL, new Date(), null);
        service.deletePassenger(p);
        verify(repository).delete(p);
    }
}
