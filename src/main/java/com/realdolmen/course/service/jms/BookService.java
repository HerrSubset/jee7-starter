package com.realdolmen.course.service.jms;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PSTBB36 on 8/08/2016.
 */
@Named
@ConversationScoped
public class BookService implements Serializable{
    @Inject @Isbn
    private transient NumberGenerator numberGenerator;
    private ArrayList<String> items = new ArrayList<>();

    @Inject
    private Conversation conv;

    public NumberGenerator getGenerator(){
        return numberGenerator;
    }

    public List<String> getItems(){
        return this.items;
    }

    public void start(){
        conv.begin();
        System.out.println("Start");
    }

    public void end(){
        conv.end();
        System.out.println("End");
    }

    public void add(){
        items.add("Hola Pola!");
    }


}
