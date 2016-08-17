package com.realdolmen.course.domain;

import javax.persistence.Entity;

@Entity
public class Plane extends AbstractEntity{
    private String type;

    public Plane(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
