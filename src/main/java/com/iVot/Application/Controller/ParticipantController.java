package com.iVot.Application.Controller;

import com.iVot.Domain.Event;
import com.iVot.Domain.Participant;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Application.DTO.ParticipantDTO;
import com.iVot.Domain.User;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.Participant.ParticipantRepository;
import com.iVot.Persistence.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParticipantController {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    public ParticipantDTO createParticipant(String userEmail, int eventId, int assignedVotes, boolean representation, int organizationId) throws InvalidParamException, NotFoundException {
        User user = userRepository.getUserByEmail(userEmail);
        Event event = eventRepository.getEventByIdAndOrganizationId(eventId, organizationId);
        Participant participant = new Participant(user, event, assignedVotes, representation);
        participantRepository.save(participant);
        //TODO send an email to the participant when is created.
        return new ParticipantDTO(participant);
    }

    public ParticipantDTO getParticipantByIdAndEventId(int participantId, int eventId) throws NotFoundException, InvalidParamException {
        Event event = eventRepository.getEventById(eventId);
        Participant participant = participantRepository.getParticipantByIdAndEventId(participantId, eventId);
        return  new ParticipantDTO(participant);
    }

    public List<ParticipantDTO> getAllParticipantsByEventId(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<ParticipantDTO> participantDTOList= new ArrayList<>();
        for (Participant participant : participantRepository.getAllParticipantByEventId(eventId, organizationId)) {
            ParticipantDTO participantDTO = new ParticipantDTO(participant);
            participantDTOList.add(participantDTO);
        }
        return participantDTOList;
    }

    public ParticipantDTO deleteParticipantByIdAndEventId(int participantId, int eventId) throws InvalidParamException, NotFoundException {
        Participant participant = participantRepository.getParticipantByIdAndEventId(participantId, eventId);
        participantRepository.removeParticipantByIdAndEventId(participantId, eventId);
        return new ParticipantDTO(participant);
    }

    public List<ParticipantDTO> removeAllParticipantsByEventId(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        for (Participant participant : participantRepository.getAllParticipantByEventId(eventId, organizationId)) {
            ParticipantDTO participantDTO = new ParticipantDTO(participant);
            participantDTOList.add(participantDTO);
        }
        participantRepository.removeAllParticipantByEventId(eventId);
        return participantDTOList;
    }

    public List<ParticipantDTO> getAllParticipantByUser(int userId) throws InvalidParamException, NotFoundException {
        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        for (Participant participant : participantRepository.getAllParticipantByUserId(userId)) {
            ParticipantDTO participantDTO = new ParticipantDTO(participant);
            participantDTOList.add(participantDTO);
        }
        return participantDTOList;
    }
}
