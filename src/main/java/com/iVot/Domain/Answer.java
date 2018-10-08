package com.iVot.Domain;

import com.iVot.Utilities.InvalidParamException;

import javax.persistence.*;

@Entity(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answerId")
    private Integer id;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organization")
    private Organization organization;
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "even")
    private Event event;
    @ManyToOne(targetEntity = Participant.class)
    @JoinColumn(name = "participant")
    private Participant participant;
    @ManyToOne(targetEntity = Topic.class)
    @JoinColumn(name = "topic")
    private Topic topic;
    @ManyToOne(targetEntity = Option.class)
    @JoinColumn(name = "option")
    private Option option;

    public Answer(){}

    public Answer(String comment, Organization organization, Event event, Participant participant,
    Topic topic, Option option) throws InvalidParamException {
        if (comment.equals("") || comment.matches("[$&+=|<>^*-]"))
            throw new InvalidParamException();
        if (organization == null)
            throw new InvalidParamException();
        if (event == null)
            throw new InvalidParamException();
        if (participant == null)
            throw new InvalidParamException();
        if (topic == null)
            throw new InvalidParamException();
        if (option == null)
            throw new InvalidParamException();

        this.comment = comment;
        this.organization = organization;
        this.event = event;
        this.participant = participant;
        this.topic = topic;
        this.option = option;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
