package com.iVot.Persistence.Option;

import com.iVot.Domain.Option;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepository {

    @Autowired
    private HelperOptionRepository repository;

    @Autowired
    private TopicRepository topicRepository;

    public OptionRepository(){}

    public void save(Option option) throws InvalidParamException {
        if (option == null)
            throw new InvalidParamException();
        if (repository.existsById(option.getId())) {
            throw new InvalidParamException();
        } else {
            repository.save(option);
        }
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
        if (repository.existsById(optionId) && topicRepository.topicExistById(topicId)) {
            repository.removeByIdAndTopicId(optionId, topicId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeAllByTopicId(int topicId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (topicRepository.topicExistById(topicId)) {
            repository.deleteAllByTopicId(topicId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Option> getAllOptionByTopicId(int topicId) throws InvalidParamException, NotFoundException {
        if (topicId <= 0)
            throw new InvalidParamException();
        if (topicRepository.topicExistById(topicId)) {
            return repository.findAllByTopicId(topicId);
        }else{
            throw new NotFoundException();
        }
    }

    public Option getOptionById(int optionId) throws NotFoundException, InvalidParamException {
        if (optionId <= 0)
            throw new InvalidParamException();
        if (repository.findById(optionId).isPresent()) {
            return repository.findById(optionId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public Option getOptionByIdAndTopicId(int optionId, int topicId)
            throws InvalidParamException, NotFoundException {
        if (optionId <= 0 || topicId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(topicId) && topicRepository.topicExistById(topicId)) {
            return repository.findByIdAndTopicId(topicId, optionId);
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
