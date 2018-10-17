package com.iVot.Domain;

import com.iVot.Application.DTO.OrganizationDTO;
import com.iVot.Utilities.Encryptor;
import com.iVot.Utilities.InvalidParamException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "organizationId")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "icon")
    private String icon;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;

    public Organization(){}

    public Organization (OrganizationDTO organization) throws InvalidParamException {
        if (organization.getEmail().equals("") || !organization.getEmail().contains("@"))
            throw new InvalidParamException();
        if(organization.getPassword().equals(""))
            throw new InvalidParamException();
        if (organization.getName().equals("") || organization.getName().matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();
        if (organization.getDescription().equals("") || organization.getDescription().matches("[$&+,:;=?@#|'<>.^*()%!-]"))
            throw new InvalidParamException();

        this.name = organization.getName();
        this.description = organization.getDescription();
        this.email = organization.getEmail();
        this.password = Encryptor.encryptPassword(organization.getPassword());
        this.icon = organization.getIcon();
    }

    public void checkPasswordIsCorrect(String password) throws InvalidParamException {
        Encryptor.checkIfPasswordMatches(password, this.password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
