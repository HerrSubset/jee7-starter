package com.realdolmen.course.service;


import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import org.junit.Test;

import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class PaymentServiceTest extends RemoteIntegrationTest {
    @Test
    public void testPayByCreditCard() throws Exception {
        PaymentServiceRemote service = lookup("jee7-starter/PaymentService!com.realdolmen.course.service.PaymentServiceRemote");
        Future<String> result = service.payByCreditCard();
        assertEquals(result.get(), "Payment success");
    }
}
