package com.iVot.Persistence.Topic;

import com.iVot.Domain.Poll;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PollRepository {

    @Autowired
    private HelperPollRepository repository;

    @Autowired
    private EventRepository eventRepository;

    public PollRepository(){}

    public void save(Poll poll) throws InvalidParamException {
        if (poll == null)
            throw new InvalidParamException();
        repository.save(poll);
    }

    public void removeTopicById(int topicId) throws NotFoundException, InvalidParamException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(topicId)) {
            repository.deleteById(topicId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeTopicByIdAndEventId(int topicId, int eventId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0 || eventId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(topicId) && eventRepository.eventExistById(eventId)) {
            repository.deleteByIdAndEventId(topicId, eventId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeAllTopicsByEventId(int eventId) throws InvalidParamException {
        if (eventId <= 0)
            throw new InvalidParamException();
        repository.deleteAllByEventId(eventId);
    }

    public List<Poll> getAllTopicByEventIdAndOrganizationId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        if (eventRepository.getEventByIdAndOrganizationId(eventId, organizationId).getId() == eventId)
            return repository.findAllByEventId(eventId);
        else
            throw new InvalidParamException();
    }

    public List<Poll> getAllTopicByEventId(int eventId) throws InvalidParamException, NotFoundException {
        if (eventId <= 0)
            throw new InvalidParamException();
        else
            return repository.findAllByEventId(eventId);
    }

    public Poll getTopicById(int topicId) throws NotFoundException, InvalidParamException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (repository.findById(topicId).isPresent()) {
            return repository.findById(topicId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public Poll getTopicByIdAndEventIdAndOrganizationId(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        if (eventId <= 0 || topicId <= 0 || organizationId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(topicId) && eventRepository.getEventByIdAndOrganizationId(eventId, organizationId).getId() == eventId) {
            return repository.findByIdAndEventId(topicId, eventId);
        } else {
            throw new NotFoundException();
        }
    }

    public Poll getTopicByIdAndEventId(int topicId, int eventId) throws InvalidParamException, NotFoundException {
        if (eventId <= 0 || topicId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(topicId) && eventRepository.eventExistById(eventId)) {
            return repository.findByIdAndEventId(topicId, eventId);
        } else {
            throw new NotFoundException();
        }
    }

    public boolean topicExistById(int topicId) throws InvalidParamException {
        if (topicId <= 0)
            throw new InvalidParamException();
        return repository.existsById(topicId);
    }
}
