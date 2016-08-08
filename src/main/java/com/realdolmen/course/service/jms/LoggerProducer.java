package com.realdolmen.course.service.jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {
    @Produces
    public Logger create(InjectionPoint ip){
        return LoggerFactory.getLogger(ip.getBean().getBeanClass());
    }
}
