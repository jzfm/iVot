package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Event;
import com.iVot.Domain.Topic;
import com.iVot.Utilities.NotFoundException;

public class TopicDTO {

    @Expose
    private Integer id;
    @Expose
    private String description;
    @Expose
    private Event event;

    public TopicDTO(Topic topic) throws NotFoundException {
        if (topic == null)
            throw new NotFoundException();

        this.id = topic.getId();
        this.description = topic.getDescription();
        this.event = topic.getEvent();
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
