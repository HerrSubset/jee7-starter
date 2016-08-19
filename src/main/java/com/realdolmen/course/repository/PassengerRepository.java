package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Passenger;
import com.realdolmen.course.domain.PassengerId;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PassengerRepository {
    @PersistenceContext
    EntityManager em;

    public Passenger findById(Object id) {
        return em.find(Passenger.class, id);
    }

    public List<Passenger> findAll() {
        TypedQuery<Passenger> q = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
        return q.getResultList();
    }

    public void create(Passenger p) {
        em.persist(p);
    }

    public Passenger update(Passenger p) {
        return em.merge(p);
    }

    public void delete(Passenger p) {
        em.remove(p);
    }

    public void delete(PassengerId id) {
        Passenger p = this.findById(id);
        em.remove(p);
    }
}
