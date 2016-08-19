package com.realdolmen.course.service;


import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import java.util.concurrent.Future;

@Remote
public interface PaymentServiceRemote {
    @Asynchronous
    Future<String> payByCreditCard() throws InterruptedException;
}
