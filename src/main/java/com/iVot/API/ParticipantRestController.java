package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.ParticipantController;
import com.iVot.Application.DTO.ParticipantDTO;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ParticipantRestController {

    @Autowired
    ParticipantController participantController;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/organizations/{organizationId}/events/{eventId}/participant", produces = "application/json;charset=UTF-8")
    public String inviteParticipant(@PathVariable int organizationId, @PathVariable int eventId, @RequestBody String jParticipant) throws InvalidParamException, NotFoundException {

        ParticipantDTO newParticipant = new Gson().fromJson(jParticipant, ParticipantDTO.class);

        ParticipantDTO Participant = participantController.createParticipant(newParticipant, eventId, organizationId);

        return toJson(Participant);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}/participant", produces = "application/json;charset=UTF-8")
    public String getAllParticipantsByEvent(@PathVariable int organizationId, @PathVariable int eventId) throws InvalidParamException, NotFoundException {

        List<ParticipantDTO> participantList = participantController.getAllParticipantsByEventId(eventId, organizationId);

        return toJson(participantList);
    }

    @DeleteMapping(value = "/organizations/{organizationId}/events/{eventId}/participant", produces = "application/json;charset=UTF-8")
    public String deleteAllParticipants(@PathVariable int organizationId, @PathVariable int eventId) throws InvalidParamException, NotFoundException {

        List<ParticipantDTO> participantToDelete = participantController.removeAllParticipantsByEventId(eventId, organizationId);

        return toJson(participantToDelete);
    }

    @DeleteMapping(value = "/organizations/{organizationId}/events/{eventId}/participant/{participantId}", produces = "application/json;charset=UTF-8")
    public String deleteParticipantById(@PathVariable int participantId, @PathVariable int organizationId, @PathVariable int eventId) throws InvalidParamException, NotFoundException {

        ParticipantDTO participantToDelete = participantController.deleteParticipantByIdAndEventId(participantId, eventId);

        return toJson(participantToDelete);
    }
}
