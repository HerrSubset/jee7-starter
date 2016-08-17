package com.realdolmen.course.domain;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TicketTest extends JpaPersistenceTest {

    private Ticket t;
    private Passenger p;
    private Flight f1;
    private Flight f2;
    private Date bd;
    private Date lf;
    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeClass
    public static void setUpClass() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        factory.close();
    }

    @Before
    public void setUp() {
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.JANUARY, 1);
        bd = c.getTime();
        c.set(2016, Calendar.MAY, 23);
        lf = c.getTime();
        p = new Passenger("123", "Joske", "Vermeulen", 5, bd, 16, PassengerType.OCCASIONAL, lf, null);
        f1 = new InternationalFlight("SN2930", bd, lf, false, " ");
        f2 = new DomesticFlight("SN3142", bd, lf, "Brussels Airlines");
        t = new Ticket(p, 99.99, f1, f2, TicketStatus.PENDING);
        entityManager().persist(p);
        entityManager().persist(f1);
        entityManager().persist(f2);
    }

    @Test
    public void creatingTicketAddsItselfToPassenger() {
        p.setTickets(new ArrayList<Ticket>());
        t.setPassenger(p);
        assertTrue(p.getTickets().contains(t));
    }

    @Test
    public void testSaveToDB() {
        entityManager().persist(p);
        entityManager().persist(t);
        Ticket ticket = entityManager().find(Ticket.class, t.getId());
        assertNotNull(ticket);
    }
}
