package com.iVot.Application.Controller;

import com.iVot.Application.DTO.TopicDTO;
import com.iVot.Domain.Event;
import com.iVot.Domain.Participant;
import com.iVot.Domain.Topic;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Persistence.Topic.TopicRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ParticipantRepository participantRepository;

    public TopicDTO createTopic(int eventId, int organizationId, TopicDTO topicDTO) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        Topic topic = new Topic(topicDTO.getDescription(), event);
        topicRepository.save(topic);
        return new TopicDTO(topic);
    }
    /*
        public TopicDTO getTopicById(int topicId) throws NotFoundException, InvalidParamException {
            Topic topic = topicRepository.getTopicById(topicId);
            return new TopicDTO(topic);
        }
    */
    public List<TopicDTO> getAllTopicsByEvent(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<TopicDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topicRepository.getAllTopicByEventIdAndOrganizationId(eventId, organizationId)){
            TopicDTO topicDTO = new TopicDTO(topic);
            topicDTOList.add(topicDTO);
        }
        return topicDTOList;
    }

    public List<TopicDTO> getAllTopicsByUser(int userId, int eventId) throws InvalidParamException, NotFoundException {
        List<TopicDTO> topicDTOSList = new ArrayList<>();
        Participant participant = participantRepository.getParticipantByUserIdAndEventId(userId, eventId);
            for (Topic topic : topicRepository.getAllTopicByEventId(participant.getEvent().getId())) {
                topicDTOSList.add(new TopicDTO(topic));
            }
        return topicDTOSList;
    }

    public TopicDTO getTopicByIdAndEventId(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Topic topic = topicRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        return new TopicDTO(topic);
    }

    public TopicDTO getTopicByIdAndUserId(int topicId, int eventId, int userId) throws InvalidParamException, NotFoundException {
        Participant participant = participantRepository.getParticipantByUserIdAndEventId(userId, eventId);
        Topic topic = topicRepository.getTopicByIdAndEventId(topicId, participant.getEvent().getId());
        return new TopicDTO(topic);
    }

    public TopicDTO updateTopic(int topicId, int eventId, int organizationId, TopicDTO topicToUpdate) throws InvalidParamException, NotFoundException {
        Topic topic = topicRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        if (!topicToUpdate.getDescription().equals(""))
            topic.setDescription(topicToUpdate.getDescription());
        return new TopicDTO(topic);
    }
    /*
        public TopicDTO removeTopicById(int topicId) throws NotFoundException, InvalidParamException {
            Topic topic = topicRepository.getTopicById(topicId);
            topicRepository.removeTopicById(topicId);
            return new TopicDTO(topic);
        }
    */
    public TopicDTO removeTopicByIdAndEventId(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Topic topic = topicRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        topicRepository.removeTopicByIdAndEventId(topicId, eventId);
        return new TopicDTO(topic);
    }
/*
    public List<TopicDTO> removeAllTopicsByEventId(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<TopicDTO> topicDTOList = new ArrayList<>();
        for (Topic topic : topicRepository.getAllTopicByEventId(eventId, organizationId)) {
            TopicDTO topicDTO = new TopicDTO(topic);
            topicDTOList.add(topicDTO);
        }
        topicRepository.removeAllTopicsByEventId(eventId);
        return topicDTOList;
    }
    */
}
