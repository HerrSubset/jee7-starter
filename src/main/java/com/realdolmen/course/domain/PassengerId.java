package com.realdolmen.course.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
public class PassengerId implements Serializable{
    @NotNull
    @Column(updatable = false)
    @NotBlank
    private String ssn;

    @NotNull
    @Size(max=50)
    @NotBlank
    private String lastName;

    public PassengerId() {
    }

    public PassengerId(String ssn, String lastName) {
        this.ssn = ssn;
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassengerId that = (PassengerId) o;

        if (ssn != null ? !ssn.equals(that.ssn) : that.ssn != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

    }

    @Override
    public int hashCode() {
        int result = ssn != null ? ssn.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PassengerId{" +
                "ssn='" + ssn + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
