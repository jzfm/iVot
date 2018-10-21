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
    @ManyToOne(targetEntity = Topic.class)
    @JoinColumn(name = "topicId")
    private Topic topic;

    public Question(){}

    public Question(String description, Topic topic) throws InvalidParamException {
        if (description.equals("") || description.matches("[$&+=|<>^*-]"))
            throw new InvalidParamException();
        if (topic == null)
            throw new InvalidParamException();

        this.description = description;
        this.topic = topic;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopicId(Topic topic) {
        this.topic = topic;
    }
}
