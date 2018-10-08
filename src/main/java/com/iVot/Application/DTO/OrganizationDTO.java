package com.iVot.Application.DTO;

import com.google.gson.annotations.Expose;
import com.iVot.Domain.Organization;
import com.iVot.Utilities.NotFoundException;

public class OrganizationDTO {
    @Expose
    private Integer id;
    @Expose
    private String name, email, icon, description;
    private String password;

    public OrganizationDTO(Organization organization) throws NotFoundException {
        if (organization == null)
            throw new NotFoundException();

        this.name = organization.getName();
        this.email = organization.getEmail();
        this.password = organization.getPassword();
        this.description = organization.getDescription();
        this.icon = organization.getIcon();
        this.id = organization.getId();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        if (name == null)
            return name = "";
        return name;
    }

    public String getEmail() {
        if (email == null)
            return email = "";
        return email;
    }

    public String getIcon() {
        if (icon == null)
            return icon = "";
        return icon;
    }

    public String getDescription() {
        if (description == null)
            return description = "";
        return description;
    }

    public String getPassword() {
        if (password == null)
            return password = "";
        return password;
    }
}
