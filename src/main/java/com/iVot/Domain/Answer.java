package com.iVot.Domain;

import com.iVot.Utilities.InvalidParamException;

import javax.persistence.*;

@Entity(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "answerId")
    private Integer id;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "organizationId")
    private Organization organization;
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name = "eventId")
    private Event event;
    @ManyToOne(targetEntity = Participant.class)
    @JoinColumn(name = "participantId")
    private Participant participant;
    @ManyToOne(targetEntity = Topic.class)
    @JoinColumn(name = "topicId")
    private Topic topic;
    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "questionId")
    private Question question;

    public Answer(){}

    public Answer(String comment, Event event, Participant participant,
    Topic topic, Question question) throws InvalidParamException {
        if (comment.equals("") || comment.matches("[$&+=|<>^*-]"))
            throw new InvalidParamException();
        if (event == null)
            throw new InvalidParamException();
        if (participant == null)
            throw new InvalidParamException();
        if (topic == null)
            throw new InvalidParamException();
        if (question == null)
            throw new InvalidParamException();

        this.comment = comment;
        //this.organization = event.getOrganization();
        this.event = event;
        this.participant = participant;
        this.topic = topic;
        this.question = question;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
