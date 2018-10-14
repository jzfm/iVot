package com.iVot.Application.Controller;

import com.iVot.Domain.*;
import com.iVot.Persistence.Organization.OrganizationRepository;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Application.DTO.EventDTO;
import com.iVot.Application.DTO.OptionDTO;
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
    ParticipantRepository participantRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    public EventDTO createEvent(EventDTO eventDTO, int organizationId) throws InvalidParamException, NotFoundException {
        Organization organization = organizationRepository.getOrganizationById(organizationId);
        Event event = new Event(eventDTO, organization);
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

    public List<EventDTO> getAllEventsByUser(int userId) throws InvalidParamException, NotFoundException {
        List<EventDTO> eventDTOSList = new ArrayList<>();
        for (Participant participant : participantRepository.getAllParticipantByUserId(userId)) {
            EventDTO eventDTO = new EventDTO(participant.getEvent());
            eventDTOSList.add(eventDTO);
        }
        return eventDTOSList;
    }
/*
    public EventDTO getEventById(int eventId) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        return new EventDTO(event);
    }
*/
    public EventDTO getEventByIdAndOrganizationId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        return new EventDTO(event);
    }
/*
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
*/
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

    public EventDTO postEvent(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (!event.isPost())
            event.setPost(true);
        else
            throw new InvalidParamException();
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO closeEvent(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (!event.isClose())
            event.setClose(true);
        else
            throw new InvalidParamException();
        eventRepository.save(event);
        return new EventDTO(event);
    }

    public EventDTO uploadPdfFile(int eventId, int organizationId, EventDTO eventToUpdate) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        if (!eventToUpdate.getPdfFile().equals(""))
            event.setPdfFile(eventToUpdate.getPdfFile());
        eventRepository.save(event);
        return new EventDTO(event);
    }
/*
    public EventDTO removeById(int eventId) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        eventRepository.removeEventById(eventId);
        return new EventDTO(event);
    }
*/
    public EventDTO removeEventByIdAndOrganizationId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        eventRepository.removeEventByIdAndOrganizationId(eventId, organizationId);
        return new EventDTO(event);
    }
/*
    public List<EventDTO> removeAllEventsByOrganizationId(int organizationId) throws NotFoundException, InvalidParamException {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (Event event : eventRepository.getAllEventByOrganization(organizationId)) {
            EventDTO eventDTO = new EventDTO(event);
            eventDTOList.add(eventDTO);
        }
        eventRepository.removeAllEventsByOrganization(organizationId);
        return eventDTOList;
    }
*/
}
