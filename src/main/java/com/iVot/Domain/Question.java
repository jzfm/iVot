package com.iVot.Domain;

import com.iVot.Utilities.InvalidParamException;

import javax.persistence.*;

@Entity(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "questionId")
    private Integer id;
    @Column(name = "description")
    private String description;
    @ManyToOne(targetEntity = Poll.class)
    @JoinColumn(name = "pollId")
    private Poll poll;

    public Question(){}

    public Question(String description, Poll poll) throws InvalidParamException {
        if (description.equals("") || description.matches("[$&+=|<>^*-]"))
            throw new InvalidParamException();
        if (poll == null)
            throw new InvalidParamException();

        this.description = description;
        this.poll = poll;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setTopicId(Poll poll) {
        this.poll = poll;
    }
}
