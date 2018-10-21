package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Question;
import com.iVot.Domain.Topic;
import com.iVot.Utilities.NotFoundException;

public class QuestionDTO {

    @Expose
    private Integer id;
    @Expose
    private String description;
    private Topic topic;

    public QuestionDTO(Question question) throws NotFoundException {
        if (question == null)
            throw new NotFoundException();

        this.id = question.getId();
        this.description = question.getDescription();
        this.topic = question.getTopic();
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
