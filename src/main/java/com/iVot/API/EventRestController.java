package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.EventController;
import com.iVot.Application.DTO.EventDTO;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class EventRestController {

    @Autowired
    private EventController eventController;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/organizations/{organizationId}/events", produces = "application/json;charset=UTF-8")
    public String createEvent(@PathVariable int organizationId, @RequestBody String jEvent) throws InvalidParamException, NotFoundException {

        EventDTO newEvent = new Gson().fromJson(jEvent, EventDTO.class);

        EventDTO event = eventController.createEvent(newEvent, organizationId);

        return toJson(event);
    }

    @GetMapping(value = "/organizations/{organizationId}/events", produces = "application/json;charset=UTF-8")
    public String getAllEvensByOrganization(@PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        List<EventDTO> eventList = eventController.getAllEventsByOrganization(organizationId);

        return toJson(eventList);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}", produces = "application/json;charset=UTF-8")
    public String getEventByIdAndOrganizationId(@PathVariable int eventId, @PathVariable int organizationId) throws InvalidParamException, NotFoundException {

        EventDTO event = eventController.getEventByIdAndOrganizationId(eventId, organizationId);

        return toJson(event);
    }

    @PutMapping(value = "/organizations/{organizationId}/events/{eventId}", produces = "application/json;charset=UTF-8")
    public String updateEvent(@PathVariable int eventId, @PathVariable int organizationId, @RequestBody String jEventToUpdate) throws NotFoundException, InvalidParamException {

        EventDTO eventToUpdate = new Gson().fromJson(jEventToUpdate, EventDTO.class);

        EventDTO event = eventController.updateEventByIdAndOrganizationId(eventId, organizationId, eventToUpdate);

        return toJson(event);
    }

    @PutMapping(value = "/organizations/{organizationId}/events/{eventId}/post", produces = "application/json;charset=UTF-8")
    public String postEvent(@PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        EventDTO eventToPost = eventController.postEvent(eventId, organizationId);

        return toJson(eventToPost);
    }

    @PutMapping(value = "/organizations/{organizationId}/events/{eventId}/close", produces = "application/json;charset=UTF-8")
    public String closeEvent(@PathVariable int eventId, @PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        EventDTO eventToClose = eventController.closeEvent(eventId, organizationId);

        return toJson(eventToClose);
    }

    @PutMapping(value = "/organizations/{organizationId}/events/{eventId}/pdf", produces = "application/json;charset=UTF-8")
    public String closeEvent(@PathVariable int eventId, @PathVariable int organizationId, @RequestBody String jEvent) throws NotFoundException, InvalidParamException {

        EventDTO eventToUpdatePdf = new Gson().fromJson(jEvent, EventDTO.class);

        EventDTO event = eventController.uploadPdfFile(eventId, organizationId, eventToUpdatePdf);

        return toJson(event);
    }

    @DeleteMapping(value = "/organizations/{organizationId}/events/{eventId}", produces = "application/json;charset=UTF-8")
    public String deleteEvent(@PathVariable int eventId, @PathVariable int organizationId) throws InvalidParamException, NotFoundException {

        EventDTO eventToDelete = eventController.removeEventByIdAndOrganizationId(eventId, organizationId);

        return toJson(eventToDelete);
    }

    @GetMapping(value = "/users/{userId}/events", produces = "application/json;charset=UTF-8")
    public String getAllEventsByUser(@PathVariable int userId) throws InvalidParamException, NotFoundException {

        List<EventDTO> eventList = eventController.getAllEventsByUser(userId);

        return toJson(eventList);
    }
}
