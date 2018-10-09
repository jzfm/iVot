package com.iVot.Persistence.User;

import com.iVot.Domain.User;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Persistence.Organization.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    private HelperUserRepository repository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public UserRepository(){}

    public void save (User user) throws InvalidParamException {
        if (user == null)
            throw new InvalidParamException();
        if (repository.existsByEmail(user.getEmail())) {
            throw new InvalidParamException();
        }else{
            repository.save(user);
        }
    }

    public void removeUser(int userId) throws NotFoundException, InvalidParamException {
        if (userId == 0)
            throw new InvalidParamException();
        if (repository.existsById(userId)) {
            repository.deleteById(userId);
        }else{
            throw new NotFoundException();
        }
    }

    public void removeUserByEmail(String email) throws NotFoundException, InvalidParamException {
        if (email.equals("") || !email.contains("@") || !email.contains(".com"))
            throw new InvalidParamException();
        if (repository.existsByEmail(email)) {
            repository.deleteByEmail(email);
        }else{
            throw new NotFoundException();
        }
    }

    public List<User> getAllUsers () throws NotFoundException {
        if (repository.count() <= 0)
            throw new NotFoundException();
        return (List<User>) repository.findAll();
    }

    public List<User> findAllUsersByOrganizationId(int organizationId) throws InvalidParamException, NotFoundException {
        if (organizationId <= 0)
            throw new InvalidParamException();
        if (organizationRepository.organizationExistById(organizationId)){
            return repository.findAllByOrganizationId(organizationId);
        }else{
            throw new NotFoundException();
        }
    }

    public List<User> getAllUsersByOrganization(int organizationId) throws InvalidParamException, NotFoundException {
        if (organizationId <= 0)
            throw new InvalidParamException();
        if (organizationRepository.organizationExistById(organizationId)) {
            return repository.findAllByOrganizationId(organizationId);
        }else{
            throw new NotFoundException();
        }
    }

    public User getUserById(int userId) throws NotFoundException, InvalidParamException {
        if (userId == 0)
            throw new InvalidParamException();
        if (repository.findById(userId).isPresent()){
            return repository.findById(userId).get();
        }else{
            throw new NotFoundException();
        }
    }

    public User getUserByEmail(String email) throws InvalidParamException, NotFoundException {
        if (email.equals("") || !email.contains("@") || !email.contains(".com"))
            throw new InvalidParamException();
        if (repository.existsByEmail(email)){
            return repository.findByEmail(email);
        }else{
            throw new NotFoundException();
        }
    }

    public boolean userExistById(int userId) throws InvalidParamException {
        if (userId <= 0)
            throw new InvalidParamException();
        return repository.existsById(userId);
    }
}