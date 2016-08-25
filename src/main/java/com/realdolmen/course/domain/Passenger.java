package com.realdolmen.course.domain;


import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@SecondaryTables({
    @SecondaryTable(name="miles"),
        @SecondaryTable(name="picture")
})
public class Passenger implements Serializable {
    @EmbeddedId @Valid
    private PassengerId id = new PassengerId();

    @Size(max=50) @NotBlank
    private String firstName;

    @Column(table="miles")
    private Integer frequentFlyerMiles;

    @NotNull @Temporal(TemporalType.DATE)
    @Column(updatable = false, nullable = false)
    @Past
    private Date dateOfBirth;

    @Transient
    private Integer age;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PassengerType type = PassengerType.OCCASIONAL;

    @Temporal(TemporalType.TIMESTAMP)
    @Past
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    @Embedded
    @Valid
    private AccountNumber accountNumber;



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
        System.out.println("Setting first name to " + firstName);
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

    public void addCreditCard(CreditCard c) {
        creditCard.add(c);
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    @PreUpdate
    @PrePersist
    public void updateDateLastUpdated() {
        this.lastUpdate = new Date();
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", frequentFlyerMiles=" + frequentFlyerMiles +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", type=" + type +
                ", lastFlight=" + lastFlight +
                ", picture=" + Arrays.toString(picture) +
                ", address=" + address +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
