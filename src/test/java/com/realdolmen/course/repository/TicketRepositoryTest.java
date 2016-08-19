package com.realdolmen.course.repository;


import com.realdolmen.course.domain.*;
import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TicketRepositoryTest extends JpaPersistenceTest{
    private TicketRepository ticketRepo;
    private Passenger p;
    private Date bd;
    private Date lf;
    private Flight f1;
    private Flight f2;

    private final static Long TEST_TICKET_ID = 1000L;

    @Before
    public void setUp() {
        ticketRepo = new TicketRepository();
        ticketRepo.em = entityManager();
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.JANUARY, 1);
        bd = c.getTime();
        c.set(2016, Calendar.MAY, 23);
        lf = c.getTime();
        f1 = new InternationalFlight("SN2930", bd, lf, false, " ");
        f2 = new DomesticFlight("SN3142", bd, lf, "Brussels Airlines");
        p = new Passenger("123", "Willy", "Peeters", 5, bd, 5, PassengerType.OCCASIONAL, lf, null);
        ticketRepo.em.persist(p);
        ticketRepo.em.persist(f1);
        ticketRepo.em.persist(f2);

    }

    @Test
    public void shouldSaveATicket() {
        Ticket t = new Ticket(p, 99.99, f1, f2, TicketStatus.PENDING);
        ticketRepo.create(t);
        assertNotNull(t.getId());
    }

    @Test
    public void shouldReturnAllPassengers() {
        List<Ticket> tickets = ticketRepo.findAll();
        assertEquals(tickets.size(), 2);
    }

    @Test
    public void shouldReturnAPassenger() {
        assertNotNull(ticketRepo.findById(TEST_TICKET_ID));
    }

//    @Test
//    public void ticketCanBeRemoved() {
//        Ticket t = ticketRepo.findById(TEST_TICKET_ID);
//        assertEquals(ticketRepo.findAll().size(), 2);
//        ticketRepo.delete(t);
//        assertEquals(ticketRepo.findAll().size(), 1);
//    }
}
