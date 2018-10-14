package com.iVot.Domain;

import com.iVot.Utilities.InvalidParamException;

import javax.persistence.*;

@Entity(name = "Participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "participantId")
    private Integer id;
    @Column(name = "assignedVotes")
    private int assignedVotes;
    @Column(name = "representation")
    private boolean representation;
    @ManyToOne(targetEntity = Event.class)
    @JoinColumn(name ="eventId")
    private Event event;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "userId")
    private User user;

    public Participant(){}

    public Participant(User user, Event event, int assignedVotes, boolean representation) throws InvalidParamException {
        if (user == null)
            throw new InvalidParamException();
        if (event == null)
            throw new InvalidParamException();
        if(assignedVotes == 0)
            throw new InvalidParamException();
        this.user = user;
        this.event = event;
        this.assignedVotes = assignedVotes;
        this.representation = representation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAssignedVotes() {
        return assignedVotes;
    }

    public void setAssignedVotes(int assignedVotes) {
        this.assignedVotes = assignedVotes;
    }

    public boolean isRepresentation() {
        return representation;
    }

    public void setRepresentation(boolean representation) {
        this.representation = representation;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}