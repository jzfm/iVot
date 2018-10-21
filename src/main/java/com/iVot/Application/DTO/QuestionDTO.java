package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Question;
import com.iVot.Domain.Poll;
import com.iVot.Utilities.NotFoundException;

public class QuestionDTO {

    @Expose
    private Integer id;
    @Expose
    private String description;
    private Poll poll;

    public QuestionDTO(Question question) throws NotFoundException {
        if (question == null)
            throw new NotFoundException();

        this.id = question.getId();
        this.description = question.getDescription();
        this.poll = question.getPoll();
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        if (description == null)
            return "";
        return description;
    }

    public Poll getPoll() {
        return poll;
    }
}
