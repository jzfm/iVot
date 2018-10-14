package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.AnswerController;
import com.iVot.Application.DTO.AnswerDTO;
import com.iVot.Utilities.AlreadyVoteException;
import com.iVot.Utilities.EventIsNotAvailableException;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AnswerRestController {

    @Autowired
    AnswerController answerController;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/users/{userId}/events/{eventId}/topics/{topicId}/options/{optionId}/answer", produces = "application/json;charset=UTF-8")
    public String vote(@PathVariable int userId, @PathVariable int eventId, @PathVariable int topicId, @PathVariable int optionId, @RequestBody JSONObject jObject) throws InvalidParamException, NotFoundException, AlreadyVoteException, EventIsNotAvailableException {

        String commentary = (String)jObject.get("commentary");

        AnswerDTO vote = answerController.createAnswer(commentary, eventId, topicId, optionId, userId);

        return toJson(vote);
    }

    @GetMapping(value = "/organizations/{organizationId}/events/{eventId}/topics/{topicId}/answer", produces = "application/json;charset=UTF-8")
    public String voteResult(@PathVariable int organizationId, @PathVariable int eventId, @PathVariable int topicId) throws InvalidParamException, NotFoundException, AlreadyVoteException, EventIsNotAvailableException {

        List<String> voteResults = answerController.votesResults(topicId, eventId, organizationId);

        return toJson(voteResults);
    }
}
