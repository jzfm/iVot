package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.ParticipantController;
import com.iVot.Application.Controller.UserController;
import com.iVot.Application.DTO.UserDTO;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserRestController {

    @Autowired
    private UserController userController;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/organizations/{organizationId}/users", produces = "application/json;charset=UTF-8")
    public String register(@PathVariable int organizationId, @RequestBody String jUser) throws InvalidParamException, NotFoundException {

        UserDTO newUser = new Gson().fromJson(jUser, UserDTO.class);

        UserDTO user = userController.createUser(newUser, organizationId);

        return toJson(user);
    }

    @GetMapping(value = "/users", produces = "application/json;charset=UTF-8")
    public String listUsers() throws NotFoundException {

        List<UserDTO> users = userController.getAllUsers();

        return toJson(users);
    }

    @GetMapping(value = "/organizations/{organizationId}/users", produces = "application/json;charset=UTF-8")
    public String listUsersByOrganization(@PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        List<UserDTO> users = userController.getAllUsersByOrganization(organizationId);

        return toJson(users);
    }

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String jUser) throws NotFoundException, InvalidParamException {

        UserDTO userToLog = new Gson().fromJson(jUser, UserDTO.class);

        UserDTO user = userController.userLogging(userToLog);

        return toJson(user);
    }

    @GetMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
    public String getUser(@PathVariable int userId) throws NotFoundException, InvalidParamException {

        UserDTO user = userController.getUserById(userId);

        return toJson(user);
    }

    @PutMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
    public String UpdateUser(@PathVariable int userId, @RequestBody String jUser)
            throws NotFoundException, InvalidParamException {

        UserDTO userToUpdate = new Gson().fromJson(jUser, UserDTO.class);

        UserDTO user = userController.updateUserById(userId, userToUpdate);

        return toJson(user);
    }

    @DeleteMapping(value="/users/{userId}",produces = "application/json;charset=UTF-8")
    public String removeUser(@PathVariable int userId) throws Exception {

        return toJson(userController.removeUserById(userId));

    }
}
