package com.realdolmen.course.service;


import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Stateless
public class PaymentService implements PaymentServiceRemote  {

    @Override
    @Asynchronous
    public Future<String> payByCreditCard() throws InterruptedException {
        Thread.sleep(5000);
        return new AsyncResult<String>("Payment success");
    }
}
