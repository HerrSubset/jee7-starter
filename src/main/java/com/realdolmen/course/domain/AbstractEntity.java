package com.realdolmen.course.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@MappedSuperclass
public class AbstractEntity implements Serializable{
    @Id @GeneratedValue
    private Long id;

    @Version
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update")
    private Date lastUpdate;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @PrePersist
    @PreUpdate
    public void updateLastUpdateDate() {
        this.lastUpdate = new Date();
    }
}
