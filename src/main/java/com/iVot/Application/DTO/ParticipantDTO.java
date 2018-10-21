package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Event;
import com.iVot.Domain.Participant;
import com.iVot.Domain.User;
import com.iVot.Utilities.NotFoundException;

public class ParticipantDTO {

    @Expose
    private Integer id, assignedVotes;
    @Expose
    private boolean representation;
    private User user;
    private Event event;
    @Expose
    private String userEmail;

    public ParticipantDTO(Participant participant) throws NotFoundException {
        if (participant == null)
            throw new NotFoundException();

        this.id = participant.getId();
        this.assignedVotes = participant.getAssignedVotes();
        this.representation = participant.isRepresentation();
        this.user = participant.getUser();
        this.event = participant.getEvent();
        this.userEmail = participant.getUser().getEmail();
    }

    public Integer getId() {
        return id;
    }

    public Integer getAssignedVotes() {
        if (assignedVotes <= 0)
            return assignedVotes = 1;
        return assignedVotes;
    }

    public boolean isRepresentation() {
        return representation;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public String getUserEmail() {
        if (userEmail == null)
            return "";
        return userEmail;
    }
}
