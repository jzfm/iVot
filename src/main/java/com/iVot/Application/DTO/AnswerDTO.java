package com.iVot.Application.DTO;


import com.google.gson.annotations.Expose;
import com.iVot.Domain.*;
import com.iVot.Utilities.InvalidParamException;


public class AnswerDTO {

    @Expose
    private Integer id;
    @Expose
    private String comment;
    @Expose
    private Organization organization;
    @Expose
    private Event event;
    @Expose
    private Participant participant;
    @Expose
    private Topic topic;
    @Expose
    private Option option;

    public AnswerDTO(Answer answer) throws InvalidParamException {
        if (answer == null)
            throw new InvalidParamException();

        this.id = answer.getId();
        this.comment = answer.getComment();
        this.organization = answer.getOrganization();
        this.event = answer.getEvent();
        this.participant = answer.getParticipant();
        this.topic = answer.getTopic();
        this.option = answer.getOption();
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        if (comment == null)
            comment = "";
        return comment;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Event getEvent() {
        return event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Topic getTopic() {
        return topic;
    }

    public Option getOption() {
        return option;
    }
}
