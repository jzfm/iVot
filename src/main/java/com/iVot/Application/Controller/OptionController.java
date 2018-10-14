package com.iVot.Application.Controller;

import com.iVot.Application.DTO.OptionDTO;
import com.iVot.Domain.Option;
import com.iVot.Domain.Participant;
import com.iVot.Domain.Topic;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.Option.OptionRepository;
import com.iVot.Persistence.Topic.TopicRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OptionController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    OptionRepository optionRepository;

    public OptionDTO createOption(int topicId, int eventId, int organizationId, OptionDTO optionDTO) throws NotFoundException, InvalidParamException {
        Topic topic = topicRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        Option option = new Option(optionDTO.getDescription(), topic);
        optionRepository.save(option);
        return new OptionDTO(option);
    }

    public OptionDTO getOptionById(int optionId) throws NotFoundException, InvalidParamException {
        Option option = optionRepository.getOptionById(optionId);
        return new OptionDTO(option);
    }

    public List<OptionDTO> getAllOptionsByTopicIdAndOrganization(int topicId, int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (Option option : optionRepository.getAllOptionByTopicIdAndOrganization(topicId, eventId, organizationId)){
            OptionDTO optionDTO = new OptionDTO(option);
            optionDTOList.add(optionDTO);
        }
        return optionDTOList;
    }

    public List<OptionDTO> getAllOptionsByTopicId(int topicId) throws NotFoundException, InvalidParamException {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (Option option : optionRepository.getAllOptionByTopicId(topicId)){
            OptionDTO optionDTO = new OptionDTO(option);
            optionDTOList.add(optionDTO);
        }
        return optionDTOList;
    }

    public OptionDTO getOptionByIdAndTopicId(int optionId, int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Option option = optionRepository.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);
        return new OptionDTO(option);
    }

    public OptionDTO updateOption(int optionId, int topicId, int eventId, int organizationId, OptionDTO optionToUpdate) throws InvalidParamException, NotFoundException {
        Option option = optionRepository.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);
        if (!optionToUpdate.getDescription().equals(""))
            option.setDescription(optionToUpdate.getDescription());
        return new OptionDTO(option);
    }

    public OptionDTO removeOptionById(int optionId) throws NotFoundException, InvalidParamException {
        Option option = optionRepository.getOptionById(optionId);
        optionRepository.removeOptionById(optionId);
        return new OptionDTO(option);
    }

    public OptionDTO removeOptionByIdAndTopicId(int optionId , int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Option option = optionRepository.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);
        optionRepository.removeOptionByIdAndTopicId(optionId, topicId);
        return new OptionDTO(option);
    }

    public List<OptionDTO> removeAllOptionsByTopicId(int topicId, int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (Option option : optionRepository.getAllOptionByTopicIdAndOrganization(topicId, eventId, organizationId)) {
            OptionDTO optionDTO = new OptionDTO(option);
            optionDTOList.add(optionDTO);
        }
        optionRepository.removeAllByTopicId(topicId);
        return optionDTOList;
    }
}
