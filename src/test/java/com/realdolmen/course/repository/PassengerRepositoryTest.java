package com.realdolmen.course.repository;


import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;
import com.realdolmen.course.domain.PassengerType;
import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PassengerRepositoryTest extends JpaPersistenceTest{
    private PassengerRepository passengerRepo;
    private Date bd;
    private Date lf;

    private static final PassengerId TEST_PERSON_ID = new PassengerId("30000", "Van Dam");


    @Before
    public void initializeRepository() {
        passengerRepo = new PassengerRepository();
        passengerRepo.em = entityManager();
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.JANUARY, 1);
        bd = c.getTime();
        c.set(2016, Calendar.MAY, 23);
        lf = c.getTime();
    }

    @Test
    public void shouldSaveAPassenger() {
        Passenger p = new Passenger("123", "Willy", "Peeters", 5, bd, 5, PassengerType.OCCASIONAL, lf, null);
        passengerRepo.create(p);
        assertNotNull(p.getId());
    }

    @Test
    public void shouldReturnAllPassengers() {
        List<Passenger> all = passengerRepo.findAll();
        assertEquals(all.size(), 3);
    }

    @Test
    public void shouldReturnAPassenger() {
        assertNotNull(passengerRepo.findById(TEST_PERSON_ID));
    }

    @Test
    public void passengerCanBeRemoved() {
        Passenger p = passengerRepo.findById(TEST_PERSON_ID);
        passengerRepo.delete(p);
        List<Passenger> all = passengerRepo.findAll();
        assertEquals(all.size(), 2);
    }
}
