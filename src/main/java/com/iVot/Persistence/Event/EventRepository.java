package com.iVot.Persistence.Event;

import com.iVot.Domain.Event;
import com.iVot.Persistence.Organization.OrganizationRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    @Autowired
    private HelperEventRepository repository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public EventRepository(){}

    public void save(Event event) throws InvalidParamException {
        if (event == null)
            throw new InvalidParamException();
        if (repository.existsById(event.getId())) {
            throw new InvalidParamException();
        } else {
            repository.save(event);
        }
    }

    public void removeEventById(int eventId) throws NotFoundException, InvalidParamException {
        if (eventId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(eventId)) {
            repository.deleteById(eventId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeEventByIdAndOrganizationId(int eventId, int organizationId) throws NotFoundException, InvalidParamException {
        if (eventId <= 0 || organizationId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(eventId) && organizationRepository.organizationExistById(organizationId)){
            repository.deleteByIdAndOrganizationId(eventId, organizationId);
        } else {
            throw new NotFoundException();
        }
    }

    public void removeAllEventsByOrganization(int organizationId) throws NotFoundException, InvalidParamException {
        if (organizationId <= 0)
            throw new InvalidParamException();
        if (organizationRepository.organizationExistById(organizationId)) {
            repository.deleteAllByOrganizationId(organizationId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Event> getAllEventByOrganization(int organizationId) throws InvalidParamException, NotFoundException {
        if (organizationId <= 0)
            throw new InvalidParamException();
        if (organizationRepository.organizationExistById(organizationId)) {
            return repository.findAllByOrganizationId(organizationId);
        }else{
            throw new NotFoundException();
        }
    }

    public Event getEventById(int eventId) throws NotFoundException, InvalidParamException {
        if (eventId <= 0)
            throw new InvalidParamException();
        if (repository.findById(eventId).isPresent()) {
            return repository.findById(eventId).get();
        } else {
            throw new NotFoundException();
        }
    }

    public Event getEventByIdAndOrganizationId(int eventId, int organizationId) throws InvalidParamException, NotFoundException {
        if (eventId <= 0 || organizationId <= 0)
            throw new InvalidParamException();
        if (repository.existsById(eventId) && organizationRepository.organizationExistById(organizationId)) {
            return repository.findByIdAndOrganizationId(eventId, organizationId);
        } else {
            throw new NotFoundException();
        }
    }

    public boolean eventExistById(int eventId) throws InvalidParamException {
        if (eventId <= 0)
            throw new InvalidParamException();
        return repository.existsById(eventId);
    }
}