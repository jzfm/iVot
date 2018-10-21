package com.iVot.Domain;

import com.iVot.Utilities.InvalidParamException;

import javax.persistence.*;

@Entity(name = "Poll")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pollId")
    private Integer id;
    @Column(name = "description")
    private String description;
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "eventId")
    private Event event;

    public Poll(){}

    public Poll(String description, Event event) throws InvalidParamException {
        if (description.equals("") || description.matches("[$&+=|<>^*-]"))
            throw new InvalidParamException();
        if (event == null)
            throw new InvalidParamException();

        this.description = description;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) { this.event = event; }
}