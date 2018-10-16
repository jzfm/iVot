package com.iVot.Persistence.Participant;

import com.iVot.Domain.Participant;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Event.EventRepository;
import com.iVot.Persistence.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParticipantRepository {

    @Autowired
    private HelperParticipantRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public ParticipantRepository(){}
    //They can invite the same user to the same event multiple times, this will break the app, look for a solution.
    public void save(Participant participant) throws InvalidParamException {
        if (participant == null)
            throw new InvalidParamException();
        if (repository.existsById(participant.getId())) {
            throw new InvalidParamException();
        } else {
            repository.save(participant);
        }
    }

    public void removeParticipantByIdAndEventId(int participantId, int eventId) throws NotFoundException, InvalidParamException {
        if (participantId <= 0 || eventId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(participantId) && eventRepository.eventExistById(eventId)) {
            repository.deleteById(participantId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeAllParticipantByEventId(int eventId) throws InvalidParamException, NotFoundException {
        if (eventId <= 0)
            throw new InvalidParamException();
        if (eventRepository.eventExistById(eventId)) {
            repository.deleteAllByEventId(eventId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Participant> getAllParticipantByEventId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        if (eventId <= 0)
            throw new InvalidParamException();
        if (eventRepository.getEventByIdAndOrganizationId(eventId, organizationId).getId() == eventId){
            return repository.findAllByEventId(eventId);
        }else{
            throw new NotFoundException();
        }
    }

    public Participant getParticipantById(int participantId) throws NotFoundException, InvalidParamException {
        if (participantId <= 0)
            throw new InvalidParamException();
        if (repository.findById(participantId).isPresent()) {
            return repository.findById(participantId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public Participant getParticipantByIdAndEventId(int participantId, int eventId)
            throws InvalidParamException, NotFoundException {
        if (participantId <= 0 || eventId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(participantId) && eventRepository.eventExistById(eventId)) {
            return repository.findByIdAndEventId(participantId, eventId);
        } else {
            throw new NotFoundException();
        }
    }

    public Participant getParticipantByUserIdAndEventId(int userId, int eventId) throws InvalidParamException, NotFoundException {
        if (userId <= 0 || eventId <= 0)
            throw new InvalidParamException();
        if (userRepository.userExistById(userId) && eventRepository.eventExistById(eventId))
            return repository.findByUserIdAndEventId(userId, eventId);
        else
            throw new NotFoundException();
    }

    public List<Participant> getAllParticipantByUserId(int userId) throws InvalidParamException, NotFoundException {
        if (userId <= 0)
            throw new InvalidParamException();
        if (userRepository.userExistById(userId)){
            return repository.findAllByUserId(userId);
        }else{
            throw new NotFoundException();
        }
    }

    public boolean participantExistById(int participantId) throws InvalidParamException {
        if (participantId <= 0)
            throw new InvalidParamException();
        return repository.existsById(participantId);
    }
}
