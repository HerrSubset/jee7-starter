package com.realdolmen.course.service.jms;

import com.realdolmen.course.domain.Ticket;
import com.realdolmen.course.repository.TicketRepository;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/rd/queues/TicketQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class TicketSenderMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            Ticket t = message.getBody(Ticket.class);
            System.out.println("New Ticket Created");
            System.out.println("Passenger: " + t.getPassenger().getFirstName() + " " + t.getPassenger().getId().getLastName());
            System.out.println("Status: " + t.getStatus().toString());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
