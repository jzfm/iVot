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
    @Expose
    private User user;
    @Expose
    private Event event;

    public ParticipantDTO(Participant participant) throws NotFoundException {
        if (participant == null)
            throw new NotFoundException();

        this.id = participant.getId();
        this.assignedVotes = participant.getAssignedVotes();
        this.representation = participant.isRepresentation();
        this.user = participant.getUser();
        this.event = participant.getEvent();
    }

    public Integer getId() {
        return id;
    }

    public Integer getAssignedVotes() {
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
}
