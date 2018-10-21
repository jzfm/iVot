package com.iVot.Application.Controller;

import com.iVot.Domain.*;
import com.iVot.Persistence.Question.QuestionRepository;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Utilities.AlreadyVoteException;
import com.iVot.Utilities.EventIsNotAvailableException;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Application.DTO.AnswerDTO;
import com.iVot.Persistence.Answers.AnswerRepository;
import com.iVot.Persistence.Topic.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    QuestionRepository questionRepository;

    public AnswerDTO createAnswer(AnswerDTO answerDTO, int eventId, int topicId, int optionId, int userId) throws AlreadyVoteException, InvalidParamException, NotFoundException, EventIsNotAvailableException {
        Participant participant = participantRepository.getParticipantByUserIdAndEventId(userId, eventId);
        Event event = participant.getEvent();
        if (event.isPost() && !event.isClose()) {
            Poll poll = pollRepository.getTopicByIdAndEventId(topicId, eventId);
            Question question = questionRepository.getOptionById(optionId);
            if (!participant.getEvent().getId().equals(event.getId()))
                throw new InvalidParamException();
            if (!answerRepository.existByTopicIdAndParticipantId(poll.getId(), participant.getId())) {
                Answer answer = new Answer(answerDTO.getComment(), event, participant, poll, question);
                answerRepository.save(answer);
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
        for (Question question : questionRepository.getAllOptionByTopicIdAndOrganization(topicId, eventId, organizationId)){
            for (Answer answer : answerRepository.getAllAnswerByOptionId(question.getId())) {
                voteSum = answer.getParticipant().getAssignedVotes() + voteSum;
            }
            votesCount.add(question.getDescription() + ":" + voteSum.toString());
            voteSum = 0;
        }
        return votesCount;
    }

    public AnswerDTO getAnswerByIdAndParticipantId(int answerId, int participantId) throws InvalidParamException, NotFoundException {
        Answer answer = answerRepository.getAnswerByIdAndParticipantId(answerId, participantId);
        return new AnswerDTO(answer);
    }
}
