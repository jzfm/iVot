package com.iVot.Application.Controller;

import com.iVot.Domain.*;
import com.iVot.Persistence.Option.OptionRepository;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Utilities.AlreadyVoteException;
import com.iVot.Utilities.EventIsNotAvailableException;
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

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    OptionRepository optionRepository;

    public AnswerDTO createAnswer(String comment, int eventId, int topicId, int optionId, int userId) throws AlreadyVoteException, InvalidParamException, NotFoundException, EventIsNotAvailableException {
        Participant participant = participantRepository.getParticipantByUserIdAndEventId(userId, eventId);
        Event event = participant.getEvent();
        if (event.isPost() && !event.isClose()) {
            Topic topic = topicRepository.getTopicByIdAndEventId(topicId, eventId);
            Option option = optionRepository.getOptionById(optionId);
            if (!participant.getEvent().getId().equals(event.getId()))
                throw new InvalidParamException();
            if (!answerRepository.existByTopicIdAndParticipantId(topic.getId(), participant.getId())) {
                Answer answer = new Answer(comment, event, participant, topic, option);
                return new AnswerDTO(answer);
            } else {
                throw new AlreadyVoteException();
            }
        }else{
            throw new EventIsNotAvailableException();
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

    public List<String> votesResults(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        List<String> votesCount = new ArrayList<>();
        Integer voteSum = 0;
        for (Option option : optionRepository.getAllOptionByTopicIdAndOrganization(topicId, eventId, organizationId)){
            for (Answer answer : answerRepository.getAllAnswerByOptionId(option.getId())) {
                voteSum = answer.getParticipant().getAssignedVotes() + voteSum;
            }
            votesCount.add(option.getDescription() + ":" + voteSum.toString());
            voteSum = 0;
        }
        return votesCount;
    }

    public AnswerDTO getAnswerByIdAndParticipantId(int answerId, int participantId) throws InvalidParamException, NotFoundException {
        Answer answer = answerRepository.getAnswerByIdAndParticipantId(answerId, participantId);
        return new AnswerDTO(answer);
    }
}
