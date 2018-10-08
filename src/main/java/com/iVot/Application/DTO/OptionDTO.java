package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Option;
import com.iVot.Domain.Topic;
import com.iVot.Utilities.NotFoundException;

public class OptionDTO {

    @Expose
    private Integer id;
    @Expose
    private String description;
    @Expose
    private Topic topic;

    public OptionDTO(Option option) throws NotFoundException {
        if (option == null)
            throw new NotFoundException();

        this.id = option.getId();
        this.description = option.getDescription();
        this.topic = option.getTopic();
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public Topic getTopic() {
        return topic;
    }
}
