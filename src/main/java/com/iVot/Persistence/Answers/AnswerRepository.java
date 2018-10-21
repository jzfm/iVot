package com.iVot.Persistence.Answers;

import com.iVot.Domain.Answer;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Question.QuestionRepository;
import com.iVot.Persistence.Topic.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AnswerRepository {

    @Autowired
    private HelperAnswerRepository repository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    public AnswerRepository(){}

    public void save(Answer answer) throws InvalidParamException {
        if (answer == null)
            throw new InvalidParamException();
        repository.save(answer);
    }

    public void removeAnswerIdAndOptionId(int answerId, int optionId) throws NotFoundException, InvalidParamException {
        if (answerId <= 0 || optionId <= 0)
            throw new InvalidParamException();
        if (questionRepository.optionExistById(answerId) && repository.existsById(answerId)) {
            repository.deleteById(answerId);
        } else {
            throw new NotFoundException();
        }
    }

    public Answer getAnswerByIdAndOptionId(int answerId, int optionId) throws NotFoundException, InvalidParamException {
        if (answerId <= 0 || optionId <= 0)
            throw new InvalidParamException();
        if (repository.findById(answerId).isPresent() && questionRepository.optionExistById(optionId)) {
            return repository.findById(answerId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public List<Answer> getAllAnswerByTopicId(int topicId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (pollRepository.topicExistById(topicId)) {
            return repository.findAllByPollId(topicId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Answer> getAllAnswerByOptionId(int optionId) throws InvalidParamException, NotFoundException {
        if (optionId <= 0)
            throw new InvalidParamException();
        if (questionRepository.optionExistById(optionId)){
            return repository.findAllByQuestionId(optionId);
        }else{
            throw new NotFoundException();
        }
    }

    public Answer getAnswerByIdAndParticipantId(int answerId, int participantId) throws InvalidParamException, NotFoundException {
        if (answerId <= 0 || participantId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(answerId) && participantRepository.participantExistById(participantId)){
            return repository.findByIdAndParticipantId(answerId, participantId);
        }else{
            throw new NotFoundException();
        }
    }

    public boolean existByTopicIdAndParticipantId(int topicId, int participantId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0 || participantId <=0)
            throw new InvalidParamException();
        if (pollRepository.topicExistById(topicId) && participantRepository.participantExistById(participantId)){
            return repository.existsByPollIdAndParticipantId(topicId, participantId);
        }else{
            throw new NotFoundException();
        }
    }
}
