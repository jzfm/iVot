package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.QuestionController;
import com.iVot.Application.DTO.QuestionDTO;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class QuestionRestController {

    @Autowired
    private QuestionController questionController;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/options", produces = "application/json;charset=UTF-8")
    public String createOption(@PathVariable int organizationId, @PathVariable int eventId, @PathVariable int topicId, @RequestBody String jOption) throws NotFoundException, InvalidParamException {

        QuestionDTO newOption = new Gson().fromJson(jOption, QuestionDTO.class);

        QuestionDTO option = questionController.createOption(topicId, eventId, organizationId, newOption);

        return toJson(option);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/options", produces = "application/json;charset=UTF-8")
    public String getAllOption(@PathVariable int organizationId, @PathVariable int eventId, @PathVariable int topicId) throws NotFoundException, InvalidParamException {

        List<QuestionDTO> optionList = questionController.getAllOptionsByTopicIdAndOrganization(topicId, eventId, organizationId);

        return toJson(optionList);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/options/{optionId}", produces = "application/json;charset=UTF-8")
    public String getOptionByIdAndTopic(@PathVariable int organizationId, @PathVariable int eventId, @PathVariable int topicId, @PathVariable int optionId) throws NotFoundException, InvalidParamException {

        QuestionDTO option = questionController.getOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);

        return toJson(option);
    }

    @PutMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/options/{optionId}", produces = "application/json;charset=UTF-8")
    public String updateOption(@PathVariable int optionId, @PathVariable int topicId, @PathVariable int eventId, @PathVariable int organizationId, @RequestBody String jOption) throws NotFoundException, InvalidParamException {

        QuestionDTO optionToUpdate = new Gson().fromJson(jOption, QuestionDTO.class);

        QuestionDTO option = questionController.updateOption(optionId, topicId, eventId, organizationId, optionToUpdate);

        return toJson(option);
    }

    @DeleteMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/options", produces = "application/json;charset=UTF-8")
    public String deleteAllOptions(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        List<QuestionDTO> deletedOptionsList = questionController.removeAllOptionsByTopicId(topicId, eventId, organizationId);

        return toJson(deletedOptionsList);
    }

    @DeleteMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/options/{optionId}", produces = "application/json;charset=UTF-8")
    public String deleteOptionById(@PathVariable int optionId, @PathVariable int topicId, @PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        QuestionDTO optionToDelete = questionController.removeOptionByIdAndTopicId(optionId, topicId, eventId, organizationId);

        return toJson(optionToDelete);
    }

    @GetMapping(value = "/users/{userId}/events/{eventId}/topics/{topicId}/options", produces = "application/json;charset=UTF-8")
    public String getAllOptionsByUser(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int userId) throws NotFoundException, InvalidParamException {

        List<QuestionDTO> optionList = questionController.getAllOptionsByTopicId(topicId);

        return toJson(optionList);
    }

    @GetMapping(value = "/users/{userId}/events/{eventId}/topics/{topicId}/options/{optionId}", produces = "application/json;charset=UTF-8")
    public String getTopicByIdAndUser(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int userId, @PathVariable int optionId) throws NotFoundException, InvalidParamException {

        QuestionDTO option = questionController.getOptionById(optionId);

        return toJson(option);
    }
}
