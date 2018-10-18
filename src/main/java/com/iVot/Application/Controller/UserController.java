package com.iVot.Application.Controller;

import com.iVot.Domain.Organization;
import com.iVot.Persistence.Organization.OrganizationRepository;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import com.iVot.Application.DTO.UserDTO;
import com.iVot.Domain.User;
import com.iVot.Persistence.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
/*
    public UserDTO createUser(UserDTO userDTO) throws InvalidParamException, NotFoundException {
        User user = new User(userDTO);
        userRepository.save(user);
        return new UserDTO(user);
    }
*/
    public UserDTO createUser(UserDTO userDTO, int organizationId) throws InvalidParamException, NotFoundException {
        Organization organization = organizationRepository.getOrganizationById(organizationId);
        User user = new User(userDTO, organization);
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO userLogging(UserDTO userDTO) throws InvalidParamException, NotFoundException {
        User user = userRepository.getUserByEmail(userDTO.getToken());
        //user.checkPasswordIsCorrect(userDTO.getPassword());
        return new UserDTO(user);
    }

    public UserDTO getUserByEmail(String email) throws InvalidParamException, NotFoundException {
        User user = userRepository.getUserByEmail(email);
        return new UserDTO(user);
    }

    public UserDTO getUserById(int userId) throws NotFoundException, InvalidParamException {
        User user = userRepository.getUserById(userId);
        return new UserDTO(user);
    }

    public List<UserDTO> getAllUsers() throws NotFoundException {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userRepository.getAllUsers()) {
            UserDTO userDTO = new UserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public List<UserDTO> getAllUsersByOrganization(int organizationId) throws NotFoundException, InvalidParamException {
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userRepository.getAllUsersByOrganization(organizationId)) {
            UserDTO userDTO = new UserDTO(user);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO updateUserById(int userId, UserDTO userToUpdate) throws NotFoundException, InvalidParamException {
        User user = userRepository.getUserById(userId);
        if (!userToUpdate.getToken().equals("") && userToUpdate.getToken().contains("@")
                && userToUpdate.getToken().contains(".com"))
            user.setToken(userToUpdate.getToken());
        if (!userToUpdate.getName().equals(""))
            user.setName(userToUpdate.getName());
        if (!userToUpdate.getLastName().equals(""))
            user.setLastName(userToUpdate.getLastName());
        /*if (!userToUpdate.getPassword().equals(""))
            user.setPassword(userToUpdate.getPassword());*/
        if (!userToUpdate.getIcon().equals(""))
            user.setIcon(userToUpdate.getIcon());
        if (userToUpdate.getOrganization() != null)
            user.setOrganization(userToUpdate.getOrganization());
        removeUserById(userId);
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO updateUserByEmail(String email, UserDTO userToUpdate) throws NotFoundException, InvalidParamException {
        User user = userRepository.getUserByEmail(email);
        if (!userToUpdate.getToken().equals("") && userToUpdate.getToken().contains("@")
                && userToUpdate.getToken().contains(".com"))
            user.setToken(userToUpdate.getToken());
        if (!userToUpdate.getName().equals(""))
            user.setName(userToUpdate.getName());
        if (!userToUpdate.getLastName().equals(""))
            user.setLastName(userToUpdate.getLastName());
        /*if (!userToUpdate.getPassword().equals(""))
            user.setPassword(userToUpdate.getPassword());*/
        if (!userToUpdate.getIcon().equals(""))
            user.setIcon(userToUpdate.getIcon());
        if (userToUpdate.getOrganization() != null)
            user.setOrganization(userToUpdate.getOrganization());
        userRepository.save(user);
        return new UserDTO(user);
    }

    public UserDTO removeUserById(int userId) throws NotFoundException, InvalidParamException {
        User user = userRepository.getUserById(userId);
        userRepository.removeUser(userId);
        return new UserDTO(user);
    }

    public UserDTO removeUserByEmail(String email) throws InvalidParamException, NotFoundException {
        User user = userRepository.getUserByEmail(email);
        userRepository.removeUserByEmail(email);
        return new UserDTO(user);
    }
}
