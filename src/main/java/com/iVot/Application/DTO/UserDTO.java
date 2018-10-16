package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Organization;
import com.iVot.Domain.User;
import com.iVot.Utilities.NotFoundException;

public class UserDTO {
    @Expose
    private Integer id;
    @Expose
    private String name, lastName, email, icon;
    private String password;
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
        this.email = user.getEmail();
        this.icon = user.getIcon();
        this.password = user.getPassword();
        this.id = user.getId();
        this.organization = user.getOrganization();
        this.organizationId = user.getOrganization().getId();
        this.organizationName = user.getOrganization().getName();
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

    public String getEmail() {
        if(email == null)
            return "";
        return email;
    }

    public String getPassword() {
        if(password == null)
            return "";
        return password;
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
        return organizationName;
    }

    public Organization getOrganization() {
        return organization;
    }
}
