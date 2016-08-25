package com.realdolmen.course.web.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;

@Named
@RequestScoped
public class FinancialResultsBean {
    private HashMap<String, Integer> results = new HashMap<>();

    public FinancialResultsBean(){

    }


    @PostConstruct
    public void setUp(){
        results.put("Infrastructure", 500);
        results.put("Sales", -1200);
        results.put("Education", 1500);
        results.put("Marketing", 320);
        results.put("Java", 600);
        results.put(".NET", -250);
    }

    public HashMap<String, Integer> getResults() {
        return this.results;
    }
}
