package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.PollController;
import com.iVot.Application.DTO.PollDTO;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PollRestController {

    @Autowired
    PollController pollController;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/organizations/{organizationId}/events/{eventId}/topics", produces = "application/json;charset=UTF-8")
    public String createTopic(@PathVariable int organizationId, @PathVariable int eventId, @RequestBody String jTopic) throws NotFoundException, InvalidParamException {

        PollDTO newTopic = new Gson().fromJson(jTopic, PollDTO.class);

        PollDTO topic = pollController.createTopic(eventId, organizationId, newTopic);

        return toJson(topic);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}/topics", produces = "application/json;charset=UTF-8")
    public String getAllTopicsByEvent(@PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        List<PollDTO> topicList = pollController.getAllTopicsByEvent(eventId, organizationId);

        return toJson(topicList);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}", produces = "application/json;charset=UTF-8")
    public String getTopicByIdAndEvent(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        PollDTO topic = pollController.getTopicByIdAndEventId(topicId, eventId, organizationId);

        return toJson(topic);
    }

    @PutMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}", produces = "application/json;charset=UTF-8")
    public String updateTopic(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int organizationId, @RequestBody String jTopic) throws NotFoundException, InvalidParamException {

        PollDTO topicToUpdate = new Gson().fromJson(jTopic, PollDTO.class);

        PollDTO topic = pollController.updateTopic(topicId, eventId, organizationId, topicToUpdate);

        return toJson(topic);
    }

    @DeleteMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}", produces = "application/json;charset=UTF-8")
    public String deleteTopic(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        PollDTO topic = pollController.removeTopicByIdAndEventId(topicId, eventId, organizationId);

        return toJson(topic);
    }

    @GetMapping(value = "/users/{userId}/events/{eventId}/topics", produces = "application/json;charset=UTF-8")
    public String getAllTopicsByUser(@PathVariable int eventId, @PathVariable int userId) throws NotFoundException, InvalidParamException {

        List<PollDTO> topicList = pollController.getAllTopicsByUser(userId, eventId);

        return toJson(topicList);
    }

    @GetMapping(value = "/users/{userId}/events/{eventId}/topics/{topicId}", produces = "application/json;charset=UTF-8")
    public String getTopicByIdAndUser(@PathVariable int topicId, @PathVariable int eventId, @PathVariable int userId) throws NotFoundException, InvalidParamException {

        PollDTO topic = pollController.getTopicByIdAndUserId(topicId, eventId, userId);

        return toJson(topic);
    }
}
