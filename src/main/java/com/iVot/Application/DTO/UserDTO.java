package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Organization;
import com.iVot.Domain.User;
import com.iVot.Utilities.NotFoundException;

public class UserDTO {
    @Expose
    private Integer id;
    @Expose
    private String name, lastName, token, icon, email;
    //private String password;
    private Organization organization;
    @Expose
    private int organizationId;
    @Expose
    private String organizationName;

    public UserDTO(User user) throws NotFoundException {
        if (user == null)
            throw new NotFoundException();

        this.name = user.getName();
        this.lastName= user.getLastName();
        this.token = user.getToken();
        this.icon = user.getIcon();
        //this.password = user.getPassword();
        this.id = user.getId();
        this.organization = user.getOrganization();
        this.organizationId = user.getOrganization().getId();
        this.organizationName = user.getOrganization().getName();
        this.email = user.getEmail();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        if(name == null)
            return "";
        return name;
    }

    public String getLastName() {
        if(lastName == null)
            return "";
        return lastName;
    }

    public String getToken() {
        if(token == null)
            return "";
        return token;
    }
/*
    public String getPassword() {
        if(password == null)
            return "";
        return password;
    }
*/

    public String getEmail() {
        if (email == null)
            return "";
        return email;
    }

    public String getIcon() {
        if (icon == null)
            return "";
        return icon;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationName() {
        if (organizationName == null)
            return organizationName = "";
        return organizationName;
    }

    public Organization getOrganization() {
        return organization;
    }
}
