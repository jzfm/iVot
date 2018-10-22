package com.iVot.Application.Controller;

import com.iVot.Application.DTO.PollDTO;
import com.iVot.Domain.Event;
import com.iVot.Domain.Participant;
import com.iVot.Domain.Poll;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Persistence.Topic.PollRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PollController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    ParticipantRepository participantRepository;

    public PollDTO createTopic(int eventId, int organizationId, PollDTO pollDTO) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        Poll poll = new Poll(pollDTO.getDescription(), event);
        pollRepository.save(poll);
        return new PollDTO(poll);
    }
    /*
        public PollDTO getTopicById(int topicId) throws NotFoundException, InvalidParamException {
            Poll topic = topicRepository.getTopicById(topicId);
            return new PollDTO(topic);
        }
    */
    public List<PollDTO> getAllTopicsByEvent(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<PollDTO> pollDTOList = new ArrayList<>();
        for (Poll poll : pollRepository.getAllTopicByEventIdAndOrganizationId(eventId, organizationId)){
            PollDTO pollDTO = new PollDTO(poll);
            pollDTOList.add(pollDTO);
        }
        return pollDTOList;
    }

    public List<PollDTO> getAllTopicsByUser(int userId, int eventId) throws InvalidParamException, NotFoundException {
        List<PollDTO> pollDTOSList = new ArrayList<>();
        Participant participant = participantRepository.getParticipantByUserIdAndEventId(userId, eventId);
            for (Poll poll : pollRepository.getAllTopicByEventId(participant.getEvent().getId())) {
                pollDTOSList.add(new PollDTO(poll));
            }
        return pollDTOSList;
    }

    public PollDTO getTopicByIdAndEventId(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Poll poll = pollRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        return new PollDTO(poll);
    }

    public PollDTO getTopicByIdAndUserId(int topicId, int eventId, int userId) throws InvalidParamException, NotFoundException {
        Participant participant = participantRepository.getParticipantByUserIdAndEventId(userId, eventId);
        Poll poll = pollRepository.getTopicByIdAndEventId(topicId, participant.getEvent().getId());
        return new PollDTO(poll);
    }

    public PollDTO updateTopic(int topicId, int eventId, int organizationId, PollDTO topicToUpdate) throws InvalidParamException, NotFoundException {
        Poll poll = pollRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        if (!topicToUpdate.getDescription().equals(""))
            poll.setDescription(topicToUpdate.getDescription());
        pollRepository.save(poll);
        return new PollDTO(poll);
    }
    /*
        public PollDTO removeTopicById(int topicId) throws NotFoundException, InvalidParamException {
            Poll topic = topicRepository.getTopicById(topicId);
            topicRepository.removeTopicById(topicId);
            return new PollDTO(topic);
        }
    */
    public PollDTO removeTopicByIdAndEventId(int topicId, int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Poll poll = pollRepository.getTopicByIdAndEventIdAndOrganizationId(topicId, eventId, organizationId);
        pollRepository.removeTopicByIdAndEventId(topicId, eventId);
        return new PollDTO(poll);
    }
/*
    public List<PollDTO> removeAllTopicsByEventId(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<PollDTO> topicDTOList = new ArrayList<>();
        for (Poll topic : topicRepository.getAllTopicByEventId(eventId, organizationId)) {
            PollDTO topicDTO = new PollDTO(topic);
            topicDTOList.add(topicDTO);
        }
        topicRepository.removeAllTopicsByEventId(eventId);
        return topicDTOList;
    }
    */
}
