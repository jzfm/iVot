package com.iVot.Application.Controller;

import com.iVot.Domain.Event;
import com.iVot.Domain.Option;
import com.iVot.Domain.Organization;
import com.iVot.Domain.Topic;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Application.DTO.EventDTO;
import com.iVot.Application.DTO.OptionDTO;
import com.iVot.Application.DTO.TopicDTO;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.Option.OptionRepository;
import com.iVot.Persistence.Topic.TopicRepository;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    OptionRepository optionRepository;

    public EventDTO createEvent(String name, String description, String icon, String pdfFile, Organization organization) throws Exception {
        Event event = new Event(name, description, organization);
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public List<EventDTO> getAllEventsByOrganization(int organizationId) throws NotFoundException, InvalidParamException {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventRepository.getAllEventByOrganization(organizationId)) {
            EventDTO eventDTO = new EventDTO(event);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    public EventDTO getEventById(int eventId) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        return new EventDTO(event);
    }

    public EventDTO getEventByIdAndOrganizationId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        return new EventDTO(event);
    }

    public EventDTO updateEventById(int eventId, EventDTO eventToUpdate) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        if (!eventToUpdate.getName().equals(""))
            event.setName(eventToUpdate.getName());
        if (!eventToUpdate.getDescription().equals(""))
            event.setDescription(eventToUpdate.getName());
        if (!eventToUpdate.getIcon().equals(""))
            event.setIcon(eventToUpdate.getIcon());
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO updateEventByIdAndOrganizationId(int eventId, int organizationId,  EventDTO eventToUpdate) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (!eventToUpdate.getName().equals(""))
            event.setName(eventToUpdate.getName());
        if (!eventToUpdate.getDescription().equals(""))
            event.setDescription(eventToUpdate.getName());
        if (!eventToUpdate.getIcon().equals(""))
            event.setIcon(eventToUpdate.getIcon());
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO postEvent(int eventId, int organizationId, EventDTO eventToPost) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (eventToPost.isPost())
            event.setPost(eventToPost.isPost());
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO closeEvent(int eventId, int organizationId, String pdfFile, EventDTO eventToClose) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (eventToClose.isClose())
            event.setClose(eventToClose.isClose());
        if (!eventToClose.getPdfFile().equals(""))
            event.setPdfFile(pdfFile);
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO uploadPdfFile(int eventId, int organizationId, String pdfFile, EventDTO eventToUpdate) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (!eventToUpdate.getPdfFile().equals(""))
            event.setPdfFile(pdfFile);
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO removeById(int eventId) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        eventRepository.removeEventById(eventId);
        return new EventDTO(event);
    }

    public EventDTO removeEventByIdAndOrganizationId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        eventRepository.removeEventByIdAndOrganizationId(eventId, organizationId);
        return new EventDTO(event);
    }

    public List<EventDTO> removeAllEventsByOrganizationId(int organizationId) throws NotFoundException, InvalidParamException {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventRepository.getAllEventByOrganization(organizationId)) {
            EventDTO eventDTO = new EventDTO(event);
            eventDTOList.add(eventDTO);
        }
        eventRepository.removeAllEventsByOrganization(organizationId);
        return eventDTOList;
    }

    public TopicDTO createTopic(int eventId, String description) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        Topic topic = new Topic(description, event);
        topicRepository.save(topic);
        return new TopicDTO(topic);
    }

    public TopicDTO getTopicById(int topicId) throws NotFoundException, InvalidParamException {
        Topic topic = topicRepository.getTopicById(topicId);
        return new TopicDTO(topic);
    }

    public TopicDTO getTopicByIdAndEventId(int topicId, int eventId) throws InvalidParamException, NotFoundException {
        Topic topic = topicRepository.getTopicByIdAndEventId(topicId, eventId);
        return new TopicDTO(topic);
    }

    public List<TopicDTO> getAllTopicsByEvent(int eventId) throws NotFoundException {
        List<TopicDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topicRepository.getAllTopicByEventId(eventId)){
            TopicDTO topicDTO = new TopicDTO(topic);
            topicDTOList.add(topicDTO);
        }
        return topicDTOList;
    }

    public TopicDTO updateTopic(int topicId, int eventId, TopicDTO topicToUpdate) throws InvalidParamException, NotFoundException {
        Topic topic = topicRepository.getTopicByIdAndEventId(topicId, eventId);
        if (!topicToUpdate.getDescription().equals(""))
            topic.setDescription(topicToUpdate.getDescription());
        return new TopicDTO(topic);
    }

    public TopicDTO removeTopicById(int topicId) throws NotFoundException, InvalidParamException {
        Topic topic = topicRepository.getTopicById(topicId);
        topicRepository.removeTopicById(topicId);
        return new TopicDTO(topic);
    }

    public TopicDTO removeTopicByIdAndEventId(int topicId, int eventId) throws InvalidParamException, NotFoundException {
        Topic topic = topicRepository.getTopicByIdAndEventId(topicId, eventId);
        topicRepository.removeTopicByIdAndEventId(topicId, eventId);
        return new TopicDTO(topic);
    }

    public List<TopicDTO> removeAllTopicsByEventId(int eventId) throws NotFoundException, InvalidParamException {
        List<TopicDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topicRepository.getAllTopicByEventId(eventId)) {
            TopicDTO topicDTO = new TopicDTO(topic);
            topicDTOList.add(topicDTO);
        }
        topicRepository.removeAllTopicsByEventId(eventId);
        return topicDTOList;
    }

    public OptionDTO createOption(String description, int topicId) throws NotFoundException, InvalidParamException {
        Topic topic = topicRepository.getTopicById(topicId);
        Option option = new Option(description, topic);
        optionRepository.save(option);
        return new OptionDTO(option);
    }

    public OptionDTO getOptionById(int optionId) throws NotFoundException, InvalidParamException {
        Option option = optionRepository.getOptionById(optionId);
        return new OptionDTO(option);
    }

    public OptionDTO getOptionByIdAndTopicId(int optionId, int topicId) throws InvalidParamException, NotFoundException {
        Option option = optionRepository.getOptionByIdAndTopicId(topicId, topicId);
        return new OptionDTO(option);
    }

    public List<OptionDTO> getAllOptionsByTopicId(int topicId) throws NotFoundException, InvalidParamException {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (Option option : optionRepository.getAllOptionByTopicId(topicId)){
            OptionDTO optionDTO = new OptionDTO(option);
            optionDTOList.add(optionDTO);
        }
        return optionDTOList;
    }

    public OptionDTO updateOption(int optionId, int topicId, OptionDTO optionToUpdate) throws InvalidParamException, NotFoundException {
        Option option = optionRepository.getOptionByIdAndTopicId(optionId, topicId);
        if (!optionToUpdate.getDescription().equals(""))
            option.setDescription(optionToUpdate.getDescription());
        return new OptionDTO(option);
    }

    public OptionDTO removeOptionById(int optionId) throws NotFoundException, InvalidParamException {
        Option option = optionRepository.getOptionById(optionId);
        optionRepository.removeOptionById(optionId);
        return new OptionDTO(option);
    }

    public OptionDTO removeOptionByIdAndTopicId(int optionId , int topicId) throws InvalidParamException, NotFoundException {
        Option option = optionRepository.getOptionByIdAndTopicId(optionId, topicId);
        optionRepository.removeOptionByIdAndTopicId(optionId, topicId);
        return new OptionDTO(option);
    }

    public List<OptionDTO> removeAllOptionsByTopicId(int topicId) throws NotFoundException, InvalidParamException {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        for (Option option : optionRepository.getAllOptionByTopicId(topicId)) {
            OptionDTO optionDTO = new OptionDTO(option);
            optionDTOList.add(optionDTO);
        }
        optionRepository.removeAllByTopicId(topicId);
        return optionDTOList;
    }
}
