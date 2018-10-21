package com.iVot.Persistence.Question;

import com.iVot.Domain.Question;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Topic.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepository {

    @Autowired
    private HelperQuestionRepository repository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private EventRepository eventRepository;

    public QuestionRepository(){}

    public void save(Question question) throws InvalidParamException {
        if (question == null)
            throw new InvalidParamException();
        repository.save(question);
    }

    public void removeOptionById(int optionId) throws NotFoundException, InvalidParamException {
        if (optionId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(optionId)) {
            repository.deleteById(optionId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeOptionByIdAndTopicId(int optionId, int topicId) throws NotFoundException, InvalidParamException {
        if (optionId <= 0 || topicId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(optionId) && pollRepository.topicExistById(topicId)) {
            repository.deleteByIdAndPollId(optionId, topicId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeAllByTopicId(int topicId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (pollRepository.topicExistById(topicId)) {
            repository.deleteAllByPollId(topicId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Question> getAllOptionByTopicId(int topicId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (pollRepository.topicExistById(topicId)) {
            return repository.findAllByPollId(topicId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Question> getAllOptionByTopicIdAndOrganization(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0)
            throw new InvalidParamException();
        //if (eventRepository.getEventByIdAndOrganizationId(eventId, organizationId).getId() == topicRepository.getTopicById(topicId).getEvent().getId())
            return repository.findAllByPollId(topicId);
        //else
            //throw new NotFoundException();
    }

    public Question getOptionById(int optionId) throws NotFoundException, InvalidParamException {
        if (optionId <= 0)
            throw new InvalidParamException();
        if (repository.findById(optionId).isPresent()) {
            return repository.findById(optionId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public Question getOptionByIdAndTopicId(int optionId, int topicId, int eventId, int organization)
            throws InvalidParamException, NotFoundException {
        if (optionId <= 0 || topicId <= 0)
            throw new InvalidParamException();
        if (eventRepository.getEventByIdAndOrganizationId(eventId, organization).getId().equals(pollRepository.getTopicById(topicId).getEvent().getId())) {
            return repository.findByIdAndPollId(topicId, optionId);
        } else {
            throw new NotFoundException();
        }
    }

    public boolean optionExistById(int optionId) throws InvalidParamException {
        if (optionId <= 0)
            throw new InvalidParamException();
        return repository.existsById(optionId);
    }
}
