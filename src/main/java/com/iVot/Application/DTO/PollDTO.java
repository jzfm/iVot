package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Event;
import com.iVot.Domain.Poll;
import com.iVot.Utilities.NotFoundException;

public class PollDTO {

    @Expose
    private Integer id;
    @Expose
    private String description;
    private Event event;

    public PollDTO(Poll poll) throws NotFoundException {
        if (poll == null)
            throw new NotFoundException();

        this.id = poll.getId();
        this.description = poll.getDescription();
        this.event = poll.getEvent();
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public Event getEvent() {
        return event;
    }
}
