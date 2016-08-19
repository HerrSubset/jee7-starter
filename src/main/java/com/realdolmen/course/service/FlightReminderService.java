package com.realdolmen.course.service;

import com.realdolmen.course.domain.Flight;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FlightReminderService implements FlightReminderServiceRemote {
    @Resource
    TimerService timerService;

    @PersistenceContext
    EntityManager em;


    @Override
    public void createFlight(Flight f) {
        em.persist(f);
        ScheduleExpression departure = new ScheduleExpression().second("*/30").minute("*").hour("*");
        timerService.createCalendarTimer(departure, new TimerConfig(f, false));
    }

    @Timeout
    public void sentReminderMail(Timer timer) {
        System.out.println("It's time to leave");
    }
}
