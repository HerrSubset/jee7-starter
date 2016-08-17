package com.realdolmen.course.domain;


import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import static org.junit.Assert.assertEquals;

public class PassengerTest extends JpaPersistenceTest {
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
    }


    @Test
    public void nameLongerThanFiftyCharactersIsInvalid() {

        p.setFirstName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        p.getId().setLastName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Set<ConstraintViolation<Passenger>> violations = validator.validate(p);
        assertEquals(2, violations.size());
    }


    @Test
    public void testProperNameIsValid() {
        Set<ConstraintViolation<Passenger>> violations = validator.validate(p);
        assertEquals(0, violations.size());
    }

    @Test
    public void passengerTypeIsNotNull() {
        p.setType(null);
        Set<ConstraintViolation<Passenger>> violations = validator.validate(p);
        assertEquals(1, violations.size());
    }

    @Test
    public void ssnIsNotNull() {
        p.getId().setSsn(null);
        Set<ConstraintViolation<Passenger>> violations = validator.validate(p);
        assertEquals(1, violations.size());
    }

    @Test
    public void dateOfBirthHasPassed() {
        Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.JANUARY, 2);
        p.setDateOfBirth(c.getTime());
        Set<ConstraintViolation<Passenger>> violations = validator.validate(p);
        assertEquals(1, violations.size());
    }

    @Test
    public void lastFlightIsInThePast() {
        Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.JANUARY, 2);
        p.setLastFlight(c.getTime());
        Set<ConstraintViolation<Passenger>> violations = validator.validate(p);
        assertEquals(1, violations.size());
    }

    @Test
    public void testSavePassengerStoresToDB() {
        entityManager().persist(p);
        Passenger passenger = entityManager().find(Passenger.class, p.getId());
        assertNotNull(passenger);
    }

    @Test
    public void ssnCantBeUpdated() {
        entityManager().persist(p);
        entityManager().flush();
        assertEquals(p.getId().getSsn(), "123");
        p.getId().setSsn("122");
        Passenger passenger = entityManager().find(Passenger.class, new PassengerId("123", p.getId().getLastName()));
        assertNotNull(passenger);
        assertNotNull(passenger.getId());
        assertEquals(passenger.getId().getSsn(), "123");
    }

    @Test
    public void testSetAddress() {
        entityManager().persist(p);
        Passenger passenger = entityManager().find(Passenger.class, p.getId());
        assertNull(passenger.getAddress());

        Address a = new Address("Liersesteenweg 81", "","Aarschot", "3200", "Belgium");
        p.setAddress(a);
        passenger = entityManager().find(Passenger.class, p.getId());
        assertNotNull(passenger.getAddress());
    }

    @Test
    public void testSetCreditCard() {

    }


}
