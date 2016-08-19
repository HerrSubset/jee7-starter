package com.realdolmen.course.service.jms;


import com.realdolmen.course.domain.*;
import com.realdolmen.course.utilities.integration.RemoteJmsTest;
import org.junit.Before;
import org.junit.Test;

import javax.jms.*;
import java.util.Date;

public class TicketSenderMDBTest extends RemoteJmsTest {

    @Before
    public void initializeJms() throws Exception {
        ConnectionFactory connectionFactory = lookup("jms/RemoteConnectionFactory");
        Queue queue = lookup("rd/queues/TicketQueue");
        connection = connectionFactory.createConnection("administrator", "Azerty123!");
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(queue);
    }

    @Test
    public void shouldSendATicket() throws JMSException {
        Passenger p = new Passenger("a", "Sherlock", "Holmes", 4, new Date(), 1, PassengerType.OCCASIONAL, new Date(), null);
        Flight f = new DomesticFlight("1", new Date(), new Date(), "SN");
        Ticket t = new Ticket(p, 1.0, f, f, TicketStatus.PENDING);

        ObjectMessage message = session.createObjectMessage(t);
        producer.send(message);
    }
}
