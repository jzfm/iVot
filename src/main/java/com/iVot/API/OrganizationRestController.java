package com.iVot.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iVot.Application.Controller.OrganizationController;
import com.iVot.Application.DTO.OrganizationDTO;
import com.iVot.Utilities.InvalidParamException;
import com.iVot.Utilities.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class OrganizationRestController {

    @Autowired
    private OrganizationController controller;

    private String toJson(Object object){

        Gson gson=new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    @PostMapping(value = "/organizations", produces = "application/json;charset=UTF-8")
    public String register(@RequestBody String jOrganization) throws InvalidParamException, NotFoundException {

        OrganizationDTO newOrganization = new Gson().fromJson(jOrganization, OrganizationDTO.class);

        OrganizationDTO organization = controller.registerOrganization(newOrganization);

        return toJson(organization);
    }

    @GetMapping(value = "/organizations", produces = "application/json;charset=UTF-8")
    public String listOrganizations() throws NotFoundException {

        List<OrganizationDTO> organizations = controller.listOfOrganizations();

        return toJson(organizations);
    }

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public String login(@RequestBody String jOrganization) throws NotFoundException, InvalidParamException {

        OrganizationDTO organizationToLog = new Gson().fromJson(jOrganization, OrganizationDTO.class);

        OrganizationDTO organization = controller.organizationLogging(organizationToLog);

        return toJson(organization);
    }

    @GetMapping(value = "/organizations/{organizationId}", produces = "application/json;charset=UTF-8")
    public String getOrganization(@PathVariable int organizationId) throws NotFoundException, InvalidParamException {

        OrganizationDTO organization = controller.getOrganizationById(organizationId);

        return toJson(organization);
    }

    @PutMapping(value = "/organizations/{organizationId}", produces = "application/json;charset=UTF-8")
    public String UpdateOrganization(@PathVariable int organizationId, @RequestBody String jOrganization)
            throws NotFoundException, InvalidParamException {

        OrganizationDTO organizationToUpdate = new Gson().fromJson(jOrganization, OrganizationDTO.class);

        OrganizationDTO organization = controller.updateOrganizationById(organizationId, organizationToUpdate);

        return toJson(organization);
    }

    @DeleteMapping(value="/organizations/{organizationId}",produces = "application/json;charset=UTF-8")
    public void removeOrganization(@PathVariable int organizationId) throws Exception {
        controller.removeOrganizationById(organizationId);
    }
}
