package com.iVot.Persistence.Organization;

import com.iVot.Domain.Organization;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationRepository {

    @Autowired
    private HelperOrganizationRepository repository;

    public void save (Organization organization) throws InvalidParamException {
        if (organization == null)
            throw new InvalidParamException();
        if (repository.existsByEmail(organization.getEmail())) {
            throw new InvalidParamException();
        }else{
            repository.save(organization);
        }
    }

    public void removeOrganization(int organizationId) throws NotFoundException, InvalidParamException {
        if (organizationId == 0)
            throw new InvalidParamException();
        if (repository.existsById(organizationId)) {
            repository.deleteById(organizationId);
        }else{
            throw new NotFoundException();
        }
    }

    public void removeOrganizationByEmail(String email) throws NotFoundException, InvalidParamException {
        if (email.equals("") || !email.matches("@"))
            throw new InvalidParamException();
        if (repository.existsByEmail(email)) {
            repository.deleteByEmail(email);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Organization> getAllOrganization () {return (List<Organization>) repository.findAll();}

    public Organization getOrganizationById(int organizationId) throws NotFoundException, InvalidParamException {
        if (organizationId == 0)
            throw new InvalidParamException();
        if (repository.findById(organizationId).isPresent()){
            return repository.findById(organizationId).get();
        }else{
            throw new NotFoundException();
        }
    }

    public Organization getOrganizationByEmail(String email) throws InvalidParamException, NotFoundException {
        if (email.equals("") || !email.matches("@"))
            throw new InvalidParamException();
        if (repository.existsByEmail(email)){
            return repository.findByEmail(email);
        }else{
            throw new NotFoundException();
        }
    }

    public boolean organizationExistById(int organizationId) throws InvalidParamException {
        if (organizationId <= 0)
            throw new InvalidParamException();
        return repository.existsById(organizationId);
    }
}
