package com.iVot.Application.Controller;

import com.iVot.Domain.*;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Application.DTO.AnswerDTO;
import com.iVot.Persistence.Answers.AnswerRepository;
import com.iVot.Persistence.Topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TopicRepository topicRepository;

    public AnswerDTO createAnswer(String comment, Organization organization, Event event, Participant participant,
                                  Topic topic, Option option) throws Exception {
        if (!participant.getEvent().getId().equals(event.getId()))
            throw new InvalidParamException();
        if (!answerRepository.existByTopicIdAndParticipantId(topic.getId(), participant.getId())) {
            Answer answer = new Answer(comment, organization, event, participant, topic, option);
            return new AnswerDTO(answer);
        }else{
            throw new Exception();//TODO custom exception for 'you already vote in this topic' message
        }
    }

    public List<AnswerDTO> getAllAnswersByTopicId(int topicId) throws InvalidParamException, NotFoundException {
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (Answer answer : answerRepository.getAllAnswerByTopicId(topicId)) {
            AnswerDTO answerDTO = new AnswerDTO(answer);
            answerDTOList.add(answerDTO);
        }
        return answerDTOList;
    }

    public AnswerDTO getAnswerByIdAndParticipantId(int answerId, int participantId) throws InvalidParamException, NotFoundException {
        Answer answer = answerRepository.getAnswerByIdAndParticipantId(answerId, participantId);
        return new AnswerDTO(answer);
    }
}
