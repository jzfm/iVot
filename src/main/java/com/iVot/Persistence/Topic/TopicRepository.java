package com.iVot.Persistence.Topic;

import com.iVot.Domain.Topic;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicRepository {

    @Autowired
    private HelperTopicRepository repository;

    @Autowired
    private EventRepository eventRepository;

    public TopicRepository(){}

    public void save(Topic topic) throws InvalidParamException {
        if (topic == null)
            throw new InvalidParamException();
        if (repository.existsById(topic.getId())) {
            throw new InvalidParamException();
        } else {
            repository.save(topic);
        }
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

    public List<Topic> getAllTopicByEventId(int eventId) {
        return repository.findAllByEventId(eventId);
    }

    public Topic getTopicById(int topicId) throws NotFoundException, InvalidParamException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (repository.findById(topicId).isPresent()) {
            return repository.findById(topicId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public Topic getTopicByIdAndEventId(int topicId, int eventId)
            throws InvalidParamException, NotFoundException {
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
