package com.realdolmen.course.domain;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@SecondaryTables({
    @SecondaryTable(name="miles"),
        @SecondaryTable(name="picture")
})
public class Passenger {
    @EmbeddedId @Valid
    private PassengerId id;

    @Size(max=50)
    private String firstName;

    @Column(table="miles")
    private Integer frequentFlyerMiles;

    @NotNull @Past @Temporal(TemporalType.DATE)
    @Column(updatable = false, nullable = false)
    private Date dateOfBirth;

    @Transient
    private Integer age;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PassengerType type;

    @Past @Temporal(TemporalType.TIMESTAMP)
    private Date lastFlight;

    @Column(table="picture")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] picture;

    @Embedded
    private Address address;

    @ElementCollection
    private List<CreditCard> creditCard = new ArrayList<>();

    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets = new ArrayList<>();



    public Passenger() {
        id = new PassengerId();
    }


    public Passenger(String ssn, String firstName, String lastName, Integer frequentFlyerMiles,
                     Date dateOfBirth, Integer age, PassengerType type, Date lastFlight, byte[] picture) {
        id = new PassengerId();
        id.setSsn(ssn);
        id.setLastName(lastName);
        this.firstName = firstName;
        this.frequentFlyerMiles = frequentFlyerMiles;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.type = type;
        this.lastFlight = lastFlight;
    }


    public PassengerId getId() {
        return id;
    }

    public void setId(PassengerId id) {
        this.id = id;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public Integer getFrequentFlyerMiles() {
        return frequentFlyerMiles;
    }

    public void setFrequentFlyerMiles(Integer frequentFlyerMiles) {
        this.frequentFlyerMiles = frequentFlyerMiles;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassengerType getType() {
        return type;
    }

    public void setType(PassengerType type) {
        this.type = type;
    }

    public Date getLastFlight() {
        return lastFlight;
    }

    public void setLastFlight(Date lastFlight) {
        this.lastFlight = lastFlight;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<CreditCard> getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(List<CreditCard> creditCard) {
        this.creditCard = creditCard;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket t) {
        tickets.add(t);
    }
}
