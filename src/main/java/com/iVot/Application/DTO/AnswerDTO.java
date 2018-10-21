package com.iVot.Application.DTO;


import com.google.gson.annotations.Expose;
import com.iVot.Domain.*;
import com.iVot.Utilities.InvalidParamException;


public class AnswerDTO {

    @Expose
    private Integer id;
    @Expose
    private String comment;
    private Organization organization;
    private Event event;
    private Participant participant;
    private Topic topic;
    private Question question;
    @Expose
    private String participantName, participantEmail, participantOrganization;

    public AnswerDTO(Answer answer) throws InvalidParamException {
        if (answer == null)
            throw new InvalidParamException();

        this.id = answer.getId();
        this.comment = answer.getComment();
        this.organization = answer.getOrganization();
        this.event = answer.getEvent();
        this.participant = answer.getParticipant();
        this.topic = answer.getTopic();
        this.question = answer.getQuestion();
        this.participantName = participant.getUser().getName();
        this.participantEmail = participant.getUser().getEmail();
        this.participantOrganization = participant.getUser().getOrganization().getName();
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

    public Question getQuestion() {
        return question;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public String getParticipantOrganization() {
        return participantOrganization;
    }
}
