package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TicketRepository {
    @PersistenceContext
    EntityManager em;

    public void create(Ticket t) {
        em.persist(t);
    }

    public Ticket update(Ticket t) {
        return em.merge(t);
    }

    public void delete(Ticket t) {
        em.remove(t);
    }

    public Ticket findById(Object id) {
        return em.find(Ticket.class, id);
    }

    public List<Ticket> findAll() {
        TypedQuery<Ticket> q = em.createQuery("SELECT t FROM Ticket t", Ticket.class);
        return q.getResultList();
    }


}
