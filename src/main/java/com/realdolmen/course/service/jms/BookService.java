package com.realdolmen.course.service.jms;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.service.PersonServiceBean;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ConversationScoped
public class BookService implements Serializable{
    @Inject @Isbn
    private transient NumberGenerator numberGenerator;
    @Inject
    private PersonServiceBean ps;
    private List<Person> people;
    @Inject
    private Conversation conv;
    @Inject
    private Logger logger;

    @PostConstruct
    public void initPeopleList(){
        people = new ArrayList<>();
    }

    public NumberGenerator getGenerator(){
        return numberGenerator;
    }

    public List<Person> getPeople(){
        return this.people;
    }

    public List<Person> getSavedPeople(){
        return ps.findAll();
    }

    public void start(){
        logger.info("CONVERSATION STARTED");
        conv.begin();
    }

    public void end(){
        for (Person p: people) {
            ps.save(p);
        }
        conv.end();
    }

    public void add(){
        Person p = new Person("Willy", "Naesens");
        people.add(p);
    }


}
