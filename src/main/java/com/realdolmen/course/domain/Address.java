package com.realdolmen.course.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
    private String street1;
    private String street2;
    private String city;
    private String zipCode;
    private String country;

    public Address() {

    }

    public Address(String street1, String street2, String city, String zipCode, String country) {
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (street1 != null ? !street1.equals(address.street1) : address.street1 != null) return false;
        if (street2 != null ? !street2.equals(address.street2) : address.street2 != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null) return false;
        return country != null ? country.equals(address.country) : address.country == null;

    }

    @Override
    public int hashCode() {
        int result = street1 != null ? street1.hashCode() : 0;
        result = 31 * result + (street2 != null ? street2.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
