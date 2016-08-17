package com.realdolmen.course.domain;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Date;

public class FlightTest extends JpaPersistenceTest{
    private Flight f;
    private Passenger p;
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
        f = new InternationalFlight("SN4142", bd, lf, false, "");
    }


    @Test
    public void testSaveInternationalFlightToDB(){
        entityManager().persist(p);
        InternationalFlight f = new InternationalFlight("SN8472", bd, lf, false, " ");
        entityManager().persist(f);
        InternationalFlight internationalFlight = entityManager().find(InternationalFlight.class, f.getId());
        assertNotNull(internationalFlight);
    }

    @Test
    public void testSaveDomesticFlightToDB() {
        entityManager().persist(p);
        DomesticFlight f = new DomesticFlight("SN3142", bd, lf, "Brussels Airlines");
        entityManager().persist(f);
        DomesticFlight domesticFlight = entityManager().find(DomesticFlight.class, f.getId());
        assertNotNull(domesticFlight);
    }


    @Test
    public void addPlaneToFlightPersistsInDB() {
        assertNull(f.getPlane());
        Plane plane = new Plane("Airbus A310");
        entityManager().persist(plane);
        f.setPlane(plane);
        entityManager().persist(f);
        Flight flight = entityManager().find(Flight.class, f.getId());
        assertNotNull(flight.getPlane());
    }


    @Test
    public void addDepartureAirportToFlightPersistsInDB() {
        //TODO
    }

    @Test
    public void addArrivalAirportToFlightPersistsInDB() {
        //TODO
    }


}
