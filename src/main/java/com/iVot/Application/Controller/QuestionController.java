package com.iVot.Application.Controller;

import com.iVot.Application.DTO.QuestionDTO;
import com.iVot.Domain.Question;
import com.iVot.Domain.Poll;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.Question.QuestionRepository;
import com.iVot.Persistence.Topic.PollRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    QuestionRepository questionRepository;

    public QuestionDTO createOption(int topicId, int eventId, int organizationId, QuestionDTO questionDTO) throws NotFoundException, InvalidParamException {
        Poll poll = pollRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        Question question = new Question(questionDTO.getDescription(), poll);
        questionRepository.save(question);
        return new QuestionDTO(question);
    }

    public QuestionDTO getOptionById(int optionId) throws NotFoundException, InvalidParamException {
        Question question = questionRepository.getOptionById(optionId);
        return new QuestionDTO(question);
    }

    public List<QuestionDTO> getAllOptionsByTopicIdAndOrganization(int topicId, int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionRepository.getAllOptionByTopicIdAndOrganization(topicId, eventId, organizationId)){
            QuestionDTO questionDTO = new QuestionDTO(question);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<QuestionDTO> getAllOptionsByTopicId(int topicId) throws NotFoundException, InvalidParamException {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionRepository.getAllOptionByTopicId(topicId)){
            QuestionDTO questionDTO = new QuestionDTO(question);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public QuestionDTO getOptionByIdAndTopicId(int optionId, int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Question question = questionRepository.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);
        return new QuestionDTO(question);
    }

    public QuestionDTO updateOption(int optionId, int topicId, int eventId, int organizationId, QuestionDTO optionToUpdate) throws InvalidParamException, NotFoundException {
        Question question = questionRepository.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);
        if (!optionToUpdate.getDescription().equals(""))
            question.setDescription(optionToUpdate.getDescription());
        return new QuestionDTO(question);
    }

    public QuestionDTO removeOptionById(int optionId) throws NotFoundException, InvalidParamException {
        Question question = questionRepository.getOptionById(optionId);
        questionRepository.removeOptionById(optionId);
        return new QuestionDTO(question);
    }

    public QuestionDTO removeOptionByIdAndTopicId(int optionId , int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Question question = questionRepository.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);
        questionRepository.removeOptionByIdAndTopicId(optionId, topicId);
        return new QuestionDTO(question);
    }

    public List<QuestionDTO> removeAllOptionsByTopicId(int topicId, int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionRepository.getAllOptionByTopicIdAndOrganization(topicId, eventId, organizationId)) {
            QuestionDTO questionDTO = new QuestionDTO(question);
            questionDTOList.add(questionDTO);
        }
        questionRepository.removeAllByTopicId(topicId);
        return questionDTOList;
    }
}
